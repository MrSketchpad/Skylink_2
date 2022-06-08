package sketchpad.skylink.item.attributes

/**
 * Stores all upgrade info for SkyblockItem
 */
class ItemInfo {
    var hpb = 0
    var fpb = 0

    /**
     * Sets [hpb] to the value of [count]
     */
    fun hotPotatoBook(count: Int): ItemInfo {
        hpb = count
        return this
    }

    /**
     * Sets [fpb] to the value of [count]
     */
    fun fumingPotatoBook(count: Int): ItemInfo {
        fpb = count
        return this
    }
}