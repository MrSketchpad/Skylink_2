package sketchpad.skylink.gui.items.constants

import org.bukkit.Material
import sketchpad.skylink.gui.GUIBuilder
import sketchpad.skylink.gui.items.GUIItem
import sketchpad.skylink.gui.items.GUIItemBuilder

class MenuGlass: GUIItem(
    GUIItemBuilder(Material.GRAY_STAINED_GLASS_PANE, "menuGlass")
        .name(" ")
        .generate(true))