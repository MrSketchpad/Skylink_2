package sketchpad.skylink.item

import org.bukkit.Material

object ItemRegistry {
    private val registry = hashMapOf<String, BaseSkyblockItem>()
    fun getItem(id: String): BaseSkyblockItem {
        return if (registry.containsKey(id)) {
            registry[id]!!
        } else ItemBuilder(Material.STONE).generate()
    }
    fun registerItem(item: BaseSkyblockItem, id: String) {
        registry[id] = item
    }
    fun unregisterItem(id: String) {
        registry.remove(id)
    }
    fun hasItem(id: String): Boolean {
        return registry.containsKey(id)
    }
    fun asHashMap(): HashMap<String, BaseSkyblockItem> {
        return registry
    }
}