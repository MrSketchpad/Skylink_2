package sketchpad.skylink.gui

import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import sketchpad.skylink.gui.items.GUIItem
import sketchpad.skylink.gui.items.constants.EmptySpace
import sketchpad.skylink.gui.items.constants.GUIItems
import sketchpad.skylink.player.SkyblockPlayer

/**
 * An inventory-based GUI
 * For a default, empty layout with E representing menu glass:
 *
 * "E", "E", "E", "E", "E", "E", "E", "E", "E",
 * "E", "E", "E", "E", "E", "E", "E", "E", "E",
 * "E", "E", "E", "E", "E", "E", "E", "E", "E",
 * "E", "E", "E", "E", "E", "E", "E", "E", "E",
 * "E", "E", "E", "E", "E", "E", "E", "E", "E",
 * "E", "E", "E", "E", "E", "E", "E", "E", "E"
 */
open class GUI(var size: Int,
               var name: String,
               var backGUI: GUI?,
               var nextGUI: GUI?,
               var keys: HashMap<String, GUIItem>,
               var layout: List<String>,
               var onClick: (InventoryClickEvent) -> Unit) {

    constructor(gui: GUI): this(gui.size,gui.name, gui.backGUI, gui.nextGUI, gui.keys, gui.layout, gui.onClick)
    fun asInventory(p: SkyblockPlayer): Inventory {
        val inventory = Bukkit.createInventory(null, size, name)
        for ((index, item) in layout.withIndex()) {
            if (keys.containsKey(item)) {
                if (keys[item]!!.id != "emptySpace") {
                    inventory.setItem(index, keys[item]!!.toItemStack(p))
                }
            }
        }
        return inventory
    }
}