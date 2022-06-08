package sketchpad.skylink.item

import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import sketchpad.skylink.item.attributes.ItemInfo
import sketchpad.skylink.stats.Stat
import sketchpad.skylink.stats.StatBuilder
import sketchpad.skylink.stats.StatHolder
import sketchpad.skylink.utils.formatWithCommas
import sketchpad.skylink.utils.text.*

/**
 * The actual SkyblockItem that is given to players. This item has a base [BaseSkyblockItem] and all the upgrades on top of it.
 */
class SkyblockItem(var base: BaseSkyblockItem) {
    companion object {
        fun fromItemStack(stack: ItemStack): SkyblockItem {
            val nbtItem = NBTItem(stack)
            if (nbtItem.hasKey("id")) {
                val id = nbtItem.getString("id")
                val baseItem = ItemRegistry.getItem(id)
                val item = SkyblockItem(baseItem)
                item.info = ItemInfo()
                if (nbtItem.hasKey("info")) {
                    val info = nbtItem.getObject("info", ItemInfo::class.java)
                    item.info = info
                }
                return item
            } else return ItemBuilder(Material.STONE).generate().getCleanItem()
        }
    }
    var info: ItemInfo

    init {
        info = ItemInfo()
    }

    /**
     * Returns the combined stats of all upgrades on this item.
     */
    fun upgradeStats(): StatHolder {
        return StatBuilder()
            .damage((info.hpb+info.fpb)*2.0)
            .strength((info.hpb+info.fpb)*2.0)
            .generate()
    }

    /**
     * Sets this item's ItemInfo to [info]
     */
    fun info(info: ItemInfo): SkyblockItem {
        this.info = info
        return this
    }

    /**
     * Returns a [StatHolder] holding this item's stats.
     */
    fun getStats(): StatHolder {
        return base.combine(upgradeStats())
    }

    /**
     * Creates a Minecraft item out of this Skyblock item
     */
    fun toItemStack(): ItemStack {
        val stats = getStats()
        var item = ItemStack(base.material)
        val meta = item.itemMeta
        val lore = mutableListOf<String>()

        meta.setDisplayName("${base.rarity.color}${base.name}")
        val offensiveStats = mutableListOf<String>()
        val defensiveStats = mutableListOf<String>()
        for (stat in Stat.values()) {
            if (stats.hasStat(stat)) {
                var extraText = ""
                if (stat.offensive) {
                    val totalBooks = (info.hpb+info.fpb)
                    if ((stat == Stat.DAMAGE || stat == Stat.STRENGTH) && totalBooks!=0) {
                        extraText = yellow(" (${if (totalBooks>0) "+" else ""}${(info.hpb+info.fpb)*2})")
                    }
                    offensiveStats.add(gray("${screamingSnakeToRegular(stat.name)}: ${red("+${stats.getStat(stat).formatWithCommas(true)}")}"+extraText))
                } else defensiveStats.add(gray("${screamingSnakeToRegular(stat.name)}: ${green("+${stats.getStat(stat).formatWithCommas(true)}")}"+extraText))
            }
        }
        lore.addAll(offensiveStats)
        lore.addAll(defensiveStats)
        lore.add("")
        for (ab in base.abilities) {
            lore.addAll(ab.getLore())
            lore.add("")
        }
        if (base.lore.isNotEmpty()) {
            lore.addAll(formatForDescription(base.lore))
            lore.add("")
        }
        lore.add("${base.rarity.color}${bold(base.rarity.name)} ${base.rarity.color}${if (base.dungeon) bold("DUNGEON ") else ""}${bold(base.type.name.replace("_", " "))}")

        meta.addItemFlags(
            ItemFlag.HIDE_ATTRIBUTES,
            ItemFlag.HIDE_DESTROYS,
            ItemFlag.HIDE_DYE,
            ItemFlag.HIDE_ENCHANTS,
            ItemFlag.HIDE_PLACED_ON,
            ItemFlag.HIDE_POTION_EFFECTS,
            ItemFlag.HIDE_UNBREAKABLE
        )
        if (base.enchantGlint) {
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true)
        }
        meta.lore = lore
        item.itemMeta = meta

        val nbtItem = NBTItem(item)
        nbtItem.setString("id", base.name.toUpperCase().replace(" ", "_"))
        nbtItem.setObject("info", info)
        item = nbtItem.item
        return item
    }
}