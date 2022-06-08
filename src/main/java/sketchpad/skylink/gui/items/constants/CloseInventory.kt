package sketchpad.skylink.gui.items.constants

import org.bukkit.Material
import sketchpad.skylink.gui.items.GUIItem
import sketchpad.skylink.gui.items.GUIItemBuilder
import sketchpad.skylink.utils.text.red

class CloseInventory: GUIItem(
    GUIItemBuilder(Material.BARRIER, "closeMenu")
        .name(red("Close Menu"))
        .onClick { e ->
            e.inventory.close()
        }.generate(true))