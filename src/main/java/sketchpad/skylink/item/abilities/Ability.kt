package sketchpad.skylink.item.abilities

import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import sketchpad.skylink.player.SkyblockPlayer
import sketchpad.skylink.stats.RegenerativeStat
import sketchpad.skylink.utils.formatWithCommas
import sketchpad.skylink.utils.text.*

open class Ability(var name: String,
                   var trigger: AbilityTrigger,
                   var sneak: Boolean,
                   var toggle: Boolean,
                   var manaCost: Int,
                   var healthCost: Int,
                   var cooldown: Int,
                   var description: String) {
    constructor(ability: Ability): this(ability.name, ability.trigger, ability.sneak, ability.toggle, ability.manaCost, ability.healthCost, ability.cooldown, ability.description)

    /**
     * The function that gets called when the ability is used. Should be overridden.
     */
    open fun action(p: SkyblockPlayer) {}

    /**
     * Returns the ability's description
     */
    fun getLore(): List<String> {
        val lore = mutableListOf<String>()
        lore.add(gold("Item Ability: $name ${yellow(bold("${trigger.name.replace("_", " ")}${if (sneak) " + SNEAK" else ""}"))}"))
        lore.addAll(formatForDescription(description))
        if (manaCost != 0) lore.add(darkGray("Mana Cost: ${darkAqua(manaCost.formatWithCommas())}"))
        if (healthCost != 0) lore.add(darkGray("Health Cost: ${red(healthCost.formatWithCommas())}"))
        if (cooldown != 0) lore.add(darkGray("Cooldown: ${green(cooldown.formatWithCommas())}"))
        return lore
    }

    /**
     * Activates the ability if all requirements are met
     */
    fun activate(sb: SkyblockPlayer) {
        val mainHand = sb.p.inventory.itemInMainHand
        var activate = false
        if (mainHand.type != Material.AIR) {
            val nbt = NBTItem(mainHand)
            if (nbt.hasKey(name)) {
                val timePassed = ((Bukkit.getCurrentTick()-nbt.getInteger(name))/20)
                if (timePassed>=cooldown) {
                    activate = true
                } else sb.tell(red("This ability is on cooldown for ${(Math.max(1, timePassed)).formatWithCommas()}s."))
            } else activate = true
            if (activate) {
                nbt.setInteger(name, Bukkit.getCurrentTick())
                sb.p.inventory.setItemInMainHand(nbt.item)
                if (sb.getRegenStat(RegenerativeStat.MANA)>=manaCost) {
                    if (sb.getRegenStat(RegenerativeStat.HEALTH)>=healthCost) {
                        sb.addRegenStat(RegenerativeStat.MANA, -1.0*manaCost)
                        sb.addRegenStat(RegenerativeStat.HEALTH, -1.0*healthCost)
                        action(sb)
                        if (manaCost != 0) sb.setCenterMessage(aqua("-$manaCost Mana (${gold(name)})"), 1)
                        else if (healthCost != 0) sb.setCenterMessage(red("-$healthCost Health (${gold(name)})"), 1)
                    } else sb.setRightMessage(red(bold("NOT ENOUGH HEALTH")), 1)
                } else sb.setRightMessage(red(bold("NOT ENOUGH MANA")), 1)
                sb.displayStats()
            }
        }
    }
}