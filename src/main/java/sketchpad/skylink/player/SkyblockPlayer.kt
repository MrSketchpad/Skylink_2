package sketchpad.skylink.player

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import sketchpad.skylink.Skylink
import sketchpad.skylink.item.ItemBuilder
import sketchpad.skylink.item.SkyblockItem
import sketchpad.skylink.stats.*
import sketchpad.skylink.utils.formatWithCommas
import sketchpad.skylink.utils.item.toSkyblockItem
import sketchpad.skylink.utils.text.aqua
import sketchpad.skylink.utils.text.green
import sketchpad.skylink.utils.text.red
import kotlin.math.exp

class SkyblockPlayer(val p: Player): SkyblockEntity(p, StatBuilder().health(100.0).intelligence(100.0).speed(100.0).generate()) {
    companion object {
        /**
         * Returns [p]'s corresponding SkyblockPlayer
         */
        fun of(p: Player): SkyblockPlayer {
            if (Skylink.players.containsKey(p.uniqueId)) {
                return Skylink.players[p.uniqueId]!!
            } else {
                throw IllegalStateException("${p.name} is not registered in the Player registry. Please report this.")
            }
        }
    }

    private var leftText = ""
    private var leftTextExpires = 0
    private var centerText = ""
    private var centerTextExpires = 0
    private var rightText = ""
    private var rightTextExpires = 0

    init {
        if (Skylink.players.containsKey(p.uniqueId)) {
            throw IllegalStateException("You cannot create more than one SkyblockPlayer object for a single player! Use SkyblockPlayer.of() instead.")
        }
    }

    fun setLeftMessage(msg: String, expiresAfter: Int) {
        leftText = msg
        leftTextExpires = Bukkit.getCurrentTick()+expiresAfter*20
    }
    fun setCenterMessage(msg: String, expiresAfter: Int) {
        centerText = msg
        centerTextExpires = Bukkit.getCurrentTick()+expiresAfter*20
    }
    fun setRightMessage(msg: String, expiresAfter: Int) {
        rightText = msg
        rightTextExpires = Bukkit.getCurrentTick()+expiresAfter*20
    }

    /**
     * Sends the player [msg] through chat
     */
    fun tell(msg: String) {
        p.sendMessage(msg)
    }

    override fun startClock() {
        healthProcessID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Skylink.INSTANCE, {
            val newHealthValue = getRegenStat(RegenerativeStat.HEALTH)+1.5+getStat(Stat.HEALTH)*healthMultiplier
            setRegenStat(RegenerativeStat.HEALTH, Math.min(newHealthValue, getStat(Stat.HEALTH)))
            displayStats()
        }, 0, healthInterval.toLong()*20)

        manaProcessID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Skylink.INSTANCE, {
            val newManaValue = getRegenStat(RegenerativeStat.MANA)+(getStat(Stat.INTELLIGENCE)*manaMultiplier)
            setRegenStat(RegenerativeStat.MANA, Math.min(newManaValue, getStat(Stat.INTELLIGENCE)))
            displayStats()
        }, 0, manaInterval.toLong()*20)

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Skylink.INSTANCE, {
            updateStats()
            updateAttributes()
        }, 0, 10)
    }

    /**
     * Displays the player's stats to their action bar
     */
    fun displayStats() {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent(
            // Displays health
            (if (leftText != "" && leftTextExpires>Bukkit.getCurrentTick()) leftText else
            red("${getRegenStat(RegenerativeStat.HEALTH).formatWithCommas(true)}/${getStat(Stat.HEALTH).formatWithCommas(true)}❤")) + "      " +
                    // Displays defense
                    (if (centerText != "" && centerTextExpires>Bukkit.getCurrentTick()) centerText else
                    green("${getStat(Stat.DEFENSE).formatWithCommas(true)}❈ Defense")) + "      " +
                    // Displays mana
                    (if (rightText != "" && rightTextExpires>Bukkit.getCurrentTick()) rightText else
                    aqua("${getRegenStat(RegenerativeStat.MANA).formatWithCommas(true)}/${getStat(Stat.INTELLIGENCE).formatWithCommas(true)}✎ Mana"))
        ))
    }

    /**
     * Updates the player's attributes to align with their stats.
     */
    override fun updateAttributes() {
        super.updateAttributes()
        p.walkSpeed = Math.min(1f, (0.2*(getStat(Stat.SPEED)/100)).toFloat())
    }
}