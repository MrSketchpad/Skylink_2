package sketchpad.skylink.gui

object GUIRegistry {
    private val registry = hashMapOf<String, GUI>()
    fun getGUI(id: String): GUI {
        return if (registry.containsKey(id)) {
            registry[id]!!
        } else GUIBuilder(54).generate()
    }
    fun registerGUI(gui: GUI, id: String) {
        registry[id] = gui
    }
    fun unregisterGUI(id: String) {
        registry.remove(id)
    }
    fun hasGUI(id: String): Boolean {
        return registry.containsKey(id)
    }
    fun asHashMap(): HashMap<String, GUI> {
        return registry
    }
}