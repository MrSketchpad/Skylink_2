package sketchpad.skylink.gui.constants

import sketchpad.skylink.gui.GUI
import sketchpad.skylink.item.constants.Items

enum class GUIs(val gui: GUI) {
    UPGRADE_ITEM(UpgradeItemGUI()),
    ;
    companion object {
        fun registerGUIs() {
            for (gui in values()) {
                gui.gui
            }
        }
    }
}