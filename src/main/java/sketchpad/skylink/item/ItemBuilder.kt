package sketchpad.skylink.item

import org.bukkit.Material
import sketchpad.skylink.item.abilities.Ability
import sketchpad.skylink.item.attributes.ItemType
import sketchpad.skylink.item.attributes.Rarity
import sketchpad.skylink.stats.Stat

/**
 * A utility object to make creating items easier
 */
class ItemBuilder(material: Material) {
    /**
     * The actual item
     */
    private val item = BaseSkyblockItem("Undefined", material, ItemType.MATERIAL, Rarity.COMMON, false, "", mutableListOf(), false)

    /**
     * Returns the item
     */
    fun generate(addToRegistry: Boolean = false, id: String = item.getID()): BaseSkyblockItem {
        if (addToRegistry) {
            ItemRegistry.registerItem(item, id)
        }
        return item
    }

    /**
     * Sets the item's enchant glint status to [glint]
     */
    fun enchantGlint(glint: Boolean): ItemBuilder {
        item.enchantGlint = glint
        return this
    }

    /**
     * Adds [ab] to the item's list of abilities.
     */
    fun addAbility(ab: Ability): ItemBuilder {
        item.abilities.add(ab)
        return this
    }

    /**
     * Sets the item's id to [id]
     */
    fun id(id: String) {
        item.setID(id)
    }

    /**
     * Sets the item's dungeon status to [isDungeon]
     */
    fun dungeon(isDungeon: Boolean): ItemBuilder {
        item.dungeon = isDungeon
        return this
    }

    /**
     * Sets the item's type to [type]
     */
    fun type(type: ItemType): ItemBuilder {
        item.type = type
        return this
    }

    /**
     * Sets the item name to [name]
     */
    fun name(name: String): ItemBuilder {
        item.name = name
        return this
    }

    /**
     * Sets the item material to [m]
     */
    fun material(m: Material): ItemBuilder {
        item.material = m
        return this
    }

    /**
     * Sets the item rarity to [rarity]
     */
    fun rarity(rarity: Rarity): ItemBuilder {
        item.rarity = rarity
        return this
    }

    /**
     * Sets the item lore to [lore]
     */
    fun lore(lore: String): ItemBuilder {
        item.lore = lore
        return this
    }

    /**
     * The following functions set their corresponding stat to [value].
     * I made a function for each stat because then using it is more fun >:)
     */

    fun health(value: Double): ItemBuilder {
        item.setStat(Stat.HEALTH, value)
        return this
    }
    fun defense(value: Double): ItemBuilder {
        item.setStat(Stat.DEFENSE, value)
        return this
    }
    fun strength(value: Double): ItemBuilder {
        item.setStat(Stat.STRENGTH, value)
        return this
    }
    fun intelligence(value: Double): ItemBuilder {
        item.setStat(Stat.INTELLIGENCE, value)
        return this
    }
    fun speed(value: Double): ItemBuilder {
        item.setStat(Stat.SPEED, value)
        return this
    }
    fun critChance(value: Double): ItemBuilder {
        item.setStat(Stat.CRIT_CHANCE, value)
        return this
    }
    fun critDamage(value: Double): ItemBuilder {
        item.setStat(Stat.CRIT_DAMAGE, value)
        return this
    }
    fun attackSpeed(value: Double): ItemBuilder {
        item.setStat(Stat.BONUS_ATTACK_SPEED, value)
        return this
    }
    fun abilityDamage(value: Double): ItemBuilder {
        item.setStat(Stat.ABILITY_DAMAGE, value)
        return this
    }
    fun trueDefense(value: Double): ItemBuilder {
        item.setStat(Stat.TRUE_DEFENSE, value)
        return this
    }
    fun ferocity(value: Double): ItemBuilder {
        item.setStat(Stat.FEROCITY, value)
        return this
    }
    fun magicFind(value: Double): ItemBuilder {
        item.setStat(Stat.MAGIC_FIND, value)
        return this
    }
    fun petLuck(value: Double): ItemBuilder {
        item.setStat(Stat.PET_LUCK, value)
        return this
    }
    fun seaCreatureChance(value: Double): ItemBuilder {
        item.setStat(Stat.SEA_CREATURE_CHANCE, value)
        return this
    }
    fun miningSpeed(value: Double): ItemBuilder {
        item.setStat(Stat.MINING_SPEED, value)
        return this
    }
    fun miningFortune(value: Double): ItemBuilder {
        item.setStat(Stat.MINING_FORTUNE, value)
        return this
    }
    fun farmingFortune(value: Double): ItemBuilder {
        item.setStat(Stat.FARMING_FORTUNE, value)
        return this
    }
    fun foragingFortune(value: Double): ItemBuilder {
        item.setStat(Stat.FORAGING_FORTUNE, value)
        return this
    }
    fun pristine(value: Double): ItemBuilder {
        item.setStat(Stat.PRISTINE, value)
        return this
    }
    fun damage(value: Double): ItemBuilder {
        item.setStat(Stat.DAMAGE, value)
        return this
    }
    fun trueDamage(value: Double): ItemBuilder {
        item.setStat(Stat.TRUE_DAMAGE, value)
        return this
    }
    fun absorption(value: Double): ItemBuilder {
        item.setStat(Stat.ABSORPTION, value)
        return this
    }
    fun breakingPower(value: Double): ItemBuilder {
        item.setStat(Stat.BREAKING_POWER, value)
        return this
    }
    fun overflowMana(value: Double): ItemBuilder {
        item.setStat(Stat.OVERFLOW, value)
        return this
    }
    fun heat(value: Double): ItemBuilder {
        item.setStat(Stat.HEAT, value)
        return this
    }
}