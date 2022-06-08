package sketchpad.skylink.item

import org.bukkit.Material
import sketchpad.skylink.global.SkyblockObject
import sketchpad.skylink.item.abilities.Ability
import sketchpad.skylink.item.attributes.ItemType
import sketchpad.skylink.item.attributes.Rarity
import sketchpad.skylink.utils.text.magenta

open class BaseSkyblockItem(var name: String,
                            var material: Material,
                            var type: ItemType,
                            var rarity: Rarity,
                            var dungeon: Boolean,
                            var lore: String,
                            var abilities: MutableList<Ability>,
                            var enchantGlint: Boolean,
                            private var id: String = ""): SkyblockObject() {
    constructor(item: BaseSkyblockItem) : this(item.name, item.material, item.type, item.rarity, item.dungeon, item.lore, item.abilities, item.enchantGlint, item.id)
    fun getCleanItem(): SkyblockItem {
        return SkyblockItem(this)
    }
    fun getID(): String {
        if (id != "") return id
        return name.toUpperCase().replace(" ", "_")
    }
    fun setID(id: String) {
        this.id = id
    }
}