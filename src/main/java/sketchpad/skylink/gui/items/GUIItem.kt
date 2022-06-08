package sketchpad.skylink.gui.items

import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import sketchpad.skylink.player.SkyblockPlayer
import sketchpad.skylink.utils.text.formatForDescription

open class GUIItem(var m: Material,
                   var id: String,
                   var defaultItemName: String,
                   var defaultItemDesc: String,
                   var movable: Boolean,
                   var enchantGlint: Boolean,
                   open var onClickAction: (InventoryClickEvent) -> Unit) {
    constructor(item: GUIItem): this(item.m, item.id, item.defaultItemName, item.defaultItemDesc, item.movable, item.enchantGlint, item.onClickAction)

    /**
     * Called when the item is clicked in an inventory.
     */
    open fun onClick(e: InventoryClickEvent) {
        this.onClickAction(e)
    }

    /**
     * Returns the inventory that [p] is currently viewing.
     */
    fun getCurrentInventory(p: SkyblockPlayer): Inventory {
        return p.p.openInventory.topInventory
    }

    /**
     * Creates an ItemStack with the name and description of this [GUIItem]
     */
    fun toItemStack(p: SkyblockPlayer): ItemStack {
        var item = ItemStack(m)
        val meta = item.itemMeta
        val lore = mutableListOf<String>()

        meta.setDisplayName(getName(p))
        lore.addAll(formatForDescription(getDescription(p)))

        meta.addItemFlags(
            ItemFlag.HIDE_ATTRIBUTES,
            ItemFlag.HIDE_DESTROYS,
            ItemFlag.HIDE_DYE,
            ItemFlag.HIDE_ENCHANTS,
            ItemFlag.HIDE_PLACED_ON,
            ItemFlag.HIDE_POTION_EFFECTS,
            ItemFlag.HIDE_UNBREAKABLE
        )
        if (enchantGlint) {
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true)
        }

        meta.lore = lore
        item.itemMeta = meta
        val nbt = NBTItem(item)
        nbt.setString("id", id)
        item = nbt.item
        return item
    }

    /**
     * Returns the item's description.
     * Can be overridden if the player viewing the item is required in order to create the description
     */
    open fun getDescription(p: SkyblockPlayer): String {
        return defaultItemDesc
    }

    /**
     * Returns the item's name
     * Can be overridden if the player viewing the item is required in order to create the name
     */
    open fun getName(p: SkyblockPlayer): String {
        return defaultItemName
    }
}