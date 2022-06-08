package sketchpad.skylink.item.constants

import org.bukkit.Material
import sketchpad.skylink.item.BaseSkyblockItem
import sketchpad.skylink.item.ItemBuilder
import sketchpad.skylink.item.attributes.ItemType
import sketchpad.skylink.item.attributes.Rarity
import sketchpad.skylink.item.constants.sword.AspectOfTheEnd
import sketchpad.skylink.item.constants.sword.AspectOfTheTest

enum class Items(val item: BaseSkyblockItem) {
    ASPECT_OF_THE_TEST(AspectOfTheTest()),
    ASPECT_OF_THE_END(AspectOfTheEnd()),
    ;
    companion object {
        fun registerItems() {
            for (item in values()) {
                item.item
            }
        }
    }
}