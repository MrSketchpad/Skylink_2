package sketchpad.skylink.utils.item

import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import sketchpad.skylink.gui.items.GUIItem
import sketchpad.skylink.gui.items.GUIItemBuilder
import sketchpad.skylink.gui.items.GUIItemRegistry
import sketchpad.skylink.gui.items.constants.GUIItems
import sketchpad.skylink.item.SkyblockItem

fun ItemStack.toSkyblockItem(): SkyblockItem {
    return SkyblockItem.fromItemStack(this)
}

fun ItemStack.toGUIItem(): GUIItem {
    if (this.type != Material.AIR) {
        val nbt = NBTItem(this)
        if (nbt.hasKey("id") && GUIItemRegistry.hasItem(nbt.getString("id"))) {
            return GUIItemRegistry.getItem(nbt.getString("id"))
        }
    }
    return GUIItemBuilder(Material.STONE, "undefined").movable(true).generate()
}