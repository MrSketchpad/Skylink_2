package sketchpad.skylink.gui.items

import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent

class GUIItemBuilder(material: Material, id: String) {
    private val item = GUIItem(material, id, "", "", false, false) {}

    fun generate(addToRegistry: Boolean = false): GUIItem {
        if (addToRegistry) {
            GUIItemRegistry.registerItem(item, item.id)
        }
        return item
    }

    /**
     * Sets the item's enchant glint status to [glint]
     */
    fun enchantGlint(glint: Boolean): GUIItemBuilder {
        item.enchantGlint = glint
        return this
    }

    /**
     * Sets the item's id to [id]
     */
    fun id(id: String): GUIItemBuilder {
        item.id = id
        return this
    }

    /**
     * Sets the item's material to [material]
     */
    fun material(material: Material): GUIItemBuilder {
        item.m = material
        return this
    }

    /**
     * Sets the item's default name to [name]
     */
    fun name(name: String): GUIItemBuilder {
        item.defaultItemName = name
        return this
    }

    /**
     * Sets the item's default description to [desc]
     */
    fun description(desc: String): GUIItemBuilder {
        item.defaultItemDesc = desc
        return this
    }

    /**
     * Sets the item's movable status to [movable]
     */
    fun movable(movable: Boolean): GUIItemBuilder {
        item.movable = movable
        return this
    }

    /**
     * Sets the action when the item is clicked to [action]
     */
    fun onClick(action: (InventoryClickEvent) -> Unit): GUIItemBuilder {
        item.onClickAction = action
        return this
    }
}