package sketchpad.skylink.gui.items.constants

import sketchpad.skylink.gui.items.GUIItem
import sketchpad.skylink.gui.items.constants.itemUpgrade.FPBUpgrade
import sketchpad.skylink.gui.items.constants.itemUpgrade.HPBUpgrade

enum class GUIItems(val item: GUIItem) {
    CLOSE_INVENTORY(CloseInventory()),
    MENU_GLASS(MenuGlass()),
    EMPTY_SPACE(EmptySpace()),
    HPB_UPGRADE(HPBUpgrade()),
    FPB_UPGRADE(FPBUpgrade()),
    ;
    companion object {
        fun registerItems() {
            for (item in values()) {
                item.item
            }
        }
    }
}