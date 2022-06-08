package sketchpad.skylink.handlers

import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import sketchpad.skylink.gui.GUIRegistry
import sketchpad.skylink.gui.items.GUIItemRegistry
import sketchpad.skylink.utils.item.toGUIItem

class GUIHandler: Listener {
    @EventHandler
    fun cancelClicks(e: InventoryClickEvent) {
        if (e.currentItem != null) {
            val item = e.currentItem!!.toGUIItem()
            if (!item.movable) e.isCancelled = true
        }
    }

    @EventHandler
    fun onClick(e: InventoryClickEvent) {
        if (e.currentItem != null) {
            e.currentItem!!.toGUIItem().onClick(e)
        }
        if (GUIRegistry.hasGUI(e.view.title)) {
            GUIRegistry.getGUI(e.view.title).onClick(e)
        }
    }
}