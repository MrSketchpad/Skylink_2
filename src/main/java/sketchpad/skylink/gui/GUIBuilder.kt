package sketchpad.skylink.gui

import org.bukkit.event.inventory.InventoryClickEvent
import sketchpad.skylink.gui.items.GUIItem

class GUIBuilder(size: Int) {
    val gui = GUI(size, "", null, null, hashMapOf(), listOf()) {}

    /**
     * Returns the final GUI.
     */
    fun generate(addToRegistry: Boolean = false): GUI{
        if (addToRegistry) {
            GUIRegistry.registerGUI(gui, gui.name)
        }
        return gui
    }

    /**
     * Determines what will happen when a player clicks inside this inventory.
     */
    fun onClick(action: (InventoryClickEvent) -> Unit): GUIBuilder {
        gui.onClick = action
        return this
    }

    /**
     * Sets the GUI's size in slots to [size]
     */
    fun size(size: Int): GUIBuilder {
        gui.size = size
        return this
    }

    /**
     * Sets the GUI's title to [name]
     */
    fun name(name: String): GUIBuilder {
        gui.name = name
        return this
    }

    /**
     * Sets the GUI that will be opened when a back arrow is pressed in this GUI to [gui]
     */
    fun backGUI(gui: GUI): GUIBuilder {
        gui.backGUI = gui
        return this
    }

    /**
     * Sets the GUI that will be opened when a next arrow is pressed in this GUI to [gui]
     */
    fun nextGUI(gui: GUI): GUIBuilder {
        gui.nextGUI = gui
        return this
    }

    /**
     * Sets the values for the keys in layout to [pairs]
     */
    fun keys(vararg pairs: Pair<String, GUIItem>): GUIBuilder {
        val map = hashMapOf<String, GUIItem>()
        for (pair in pairs) {
            map[pair.first] = pair.second
        }
        gui.keys = map
        return this
    }

    /**
     * Sets the layout of the GUI to [keys], where each key corresponds to an item set in the keys() function.
     */
    fun layout(vararg keys: String): GUIBuilder {
        val layout = mutableListOf<String>()
        for (key in keys) {
            layout.add(key)
        }
        gui.layout = layout
        return this
    }
}