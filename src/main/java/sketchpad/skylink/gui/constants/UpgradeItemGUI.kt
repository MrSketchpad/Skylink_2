package sketchpad.skylink.gui.constants

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import sketchpad.skylink.Skylink
import sketchpad.skylink.gui.GUI
import sketchpad.skylink.gui.GUIBuilder
import sketchpad.skylink.gui.items.constants.CloseInventory
import sketchpad.skylink.gui.items.constants.EmptySpace
import sketchpad.skylink.gui.items.constants.itemUpgrade.HPBUpgrade
import sketchpad.skylink.gui.items.constants.MenuGlass
import sketchpad.skylink.gui.items.constants.itemUpgrade.FPBUpgrade
import sketchpad.skylink.player.SkyblockPlayer

class UpgradeItemGUI: GUI(
    GUIBuilder(54)
        .name("Upgrade Item")
        .keys(
            "E" to MenuGlass(),
            "X" to CloseInventory(),
            "N" to EmptySpace(),
            "HPB" to HPBUpgrade(),
            "FPB" to FPBUpgrade(),
        ).layout(
            "E", "E", "E", "E", "E", "E", "E", "E", "E",
            "E", "HPB", "E", "E", "E", "E", "E", "E", "E",
            "E", "FPB", "E", "E", "N", "E", "E", "E", "E",
            "E", "E", "E", "E", "E", "E", "E", "E", "E",
            "E", "E", "E", "E", "E", "E", "E", "E", "E",
            "E", "E", "E", "E", "X", "E", "E", "E", "E")
        // Reopens the inventory to make sure all GUI items are updated to the new item
        .onClick {
            // Delayed so that the inventory is the latest one
            Bukkit.getScheduler().scheduleSyncDelayedTask(Skylink.INSTANCE) {
                val item = it.inventory.getItem(22)
                val inventory = GUIs.UPGRADE_ITEM.gui.asInventory(SkyblockPlayer.of(it.whoClicked as Player))
                if (item!=null) {
                    inventory.setItem(22, item)
                }
                // The slots of all items that change when the center item (at slot 22) is changed
                for (i in listOf(10, 19, 28, 37, 11, 20, 29, 38, 16, 25, 34, 41, 15, 24, 33, 40)) {
                    it.inventory.setItem(i, inventory.getItem(i))
                }
            }
        }
        .generate(true)) {
}