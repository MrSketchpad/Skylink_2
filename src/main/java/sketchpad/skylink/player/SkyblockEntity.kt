package sketchpad.skylink.player

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
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

open class SkyblockEntity(val e: LivingEntity, val baseStats: StatHolder): RegenStatHolder() {
    companion object {
        /**
         * Returns [e]'s corresponding SkyblockEntity
         */
        fun of(e: LivingEntity): SkyblockEntity {
            if (Skylink.mobs.containsKey(e.uniqueId)) {
                return Skylink.mobs[e.uniqueId]!!
            } else {
                val mob = SkyblockEntity(e, StatBuilder().health(e.health*5000).generate())
                Skylink.mobs[e.uniqueId] = mob
                return mob
            }
        }
    }
    init {
        if (Skylink.mobs.containsKey(e.uniqueId)) {
            throw IllegalStateException("You cannot create more than one SkyblockEntity object for a single entity! Use SkyblockEntity.of() instead.")
        }
    }

    /**
     * Teleports entity [distance] blocks forward in the direction they are facing
     */
    fun teleport(distance: Int) {
        val loc = e.location
        val dir = loc.direction
        dir.normalize()
        dir.multiply(distance)
        loc.add(dir)
        e.teleport(loc)
    }

    override fun startClock() {
        healthProcessID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Skylink.INSTANCE, {
            val newHealthValue = getRegenStat(RegenerativeStat.HEALTH)+1.5+getStat(Stat.HEALTH)*healthMultiplier
            setRegenStat(RegenerativeStat.HEALTH, Math.min(newHealthValue, getStat(Stat.HEALTH)))
        }, 0, healthInterval.toLong()*20)

        manaProcessID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Skylink.INSTANCE, {
            val newManaValue = getRegenStat(RegenerativeStat.MANA)+(getStat(Stat.INTELLIGENCE)*manaMultiplier)
            setRegenStat(RegenerativeStat.MANA, Math.min(newManaValue, getStat(Stat.INTELLIGENCE)))
        }, 0, manaInterval.toLong()*20)

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Skylink.INSTANCE, {
            updateStats()
            updateAttributes()
        }, 0, 10)
    }


    fun getMainHand(): SkyblockItem {
        if (e.equipment == null) return ItemBuilder(Material.STONE).generate().getCleanItem()
        if (e.equipment!!.itemInMainHand.type==Material.AIR) {
            return ItemBuilder(Material.STONE).generate().getCleanItem()
        }
        return e.equipment!!.itemInMainHand.toSkyblockItem()
    }
    fun getOffHand(): SkyblockItem {
        if (e.equipment == null) return ItemBuilder(Material.STONE).generate().getCleanItem()
        if (e.equipment!!.itemInOffHand.type==Material.AIR) {
            return ItemBuilder(Material.STONE).generate().getCleanItem()
        }
        return e.equipment!!.itemInOffHand.toSkyblockItem()
    }
    fun getHelmet(): SkyblockItem {
        if (e.equipment != null && e.equipment!!.helmet != null) {
            return e.equipment!!.helmet!!.toSkyblockItem()
        }
        return ItemBuilder(Material.STONE).generate().getCleanItem()
    }
    fun getChestplate(): SkyblockItem {
        if (e.equipment != null && e.equipment!!.chestplate != null) {
            return e.equipment!!.chestplate!!.toSkyblockItem()
        }
        return ItemBuilder(Material.STONE).generate().getCleanItem()
    }
    fun getLeggings(): SkyblockItem {
        if (e.equipment != null && e.equipment!!.leggings != null) {
            return e.equipment!!.leggings!!.toSkyblockItem()
        }
        return ItemBuilder(Material.STONE).generate().getCleanItem()
    }
    fun getBoots(): SkyblockItem {
        if (e.equipment != null && e.equipment!!.boots != null) {
            return e.equipment!!.boots!!.toSkyblockItem()
        }
        return ItemBuilder(Material.STONE).generate().getCleanItem()
    }

    /**
     * Updates the entity's stats to account for changes in gear
     */
    fun updateStats() {
        resetStats()
        replaceWithOther(baseStats)
        replaceWithOther(combine(getMainHand().getStats()))
        replaceWithOther(combine(getOffHand().getStats()))
        replaceWithOther(combine(getHelmet().getStats()))
        replaceWithOther(combine(getChestplate().getStats()))
        replaceWithOther(combine(getLeggings().getStats()))
        replaceWithOther(combine(getBoots().getStats()))
    }

    /**
     * Updates the entity's attributes to align with their stats.
     */
    open fun updateAttributes() {
        e.maxHealth = 20+(40*((getStat(Stat.HEALTH)-100)/2500))
        e.health = Math.min(e.maxHealth, e.maxHealth*(getRegenStat(RegenerativeStat.HEALTH)/getStat(Stat.HEALTH)))
    }
}