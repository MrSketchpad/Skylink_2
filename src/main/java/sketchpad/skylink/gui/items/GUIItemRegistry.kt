package sketchpad.skylink.gui.items

import org.bukkit.Material
import sketchpad.skylink.item.BaseSkyblockItem
import sketchpad.skylink.item.ItemBuilder

object GUIItemRegistry {
    private val registry = hashMapOf<String, GUIItem>()
    fun getItem(id: String): GUIItem {
        return if (registry.containsKey(id)) {
            registry[id]!!
        } else GUIItemBuilder(Material.STONE, "undefined").generate()
    }
    fun registerItem(item: GUIItem, id: String) {
        registry[id] = item
    }
    fun unregisterItem(id: String) {
        registry.remove(id)
    }
    fun hasItem(id: String): Boolean {
        return registry.containsKey(id)
    }
    fun asHashMap(): HashMap<String, GUIItem> {
        return registry
    }
}