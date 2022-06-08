package sketchpad.skylink.gui.items.constants.itemUpgrade

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import sketchpad.skylink.gui.items.GUIItem
import sketchpad.skylink.gui.items.GUIItemBuilder
import sketchpad.skylink.gui.items.GUIItemRegistry
import sketchpad.skylink.player.SkyblockPlayer
import sketchpad.skylink.utils.item.toSkyblockItem
import sketchpad.skylink.utils.text.green
import sketchpad.skylink.utils.text.yellow

class HPBUpgrade: GUIItem(
    GUIItemBuilder(Material.BOOK, "hpbUpgrade")
        .generate()) {

    init {
        GUIItemRegistry.registerItem(this, id)
    }

    override fun onClick(e: InventoryClickEvent) {
        val inventory = getCurrentInventory(SkyblockPlayer.of(e.whoClicked as Player))
        val currentItemStack = inventory.getItem(22)
        if (currentItemStack != null && currentItemStack.type != Material.AIR) {
            val item = currentItemStack.toSkyblockItem()
            if (e.isLeftClick) {
                item.info.hpb += 1
            } else if (e.isRightClick) {
                item.info.hpb -= 1
            }
            inventory.setItem(22, item.toItemStack())
        }
    }

    override fun getName(p: SkyblockPlayer): String {
        val inventory = getCurrentInventory(p)
        val currentItemStack = inventory.getItem(22)
        if (currentItemStack != null && currentItemStack.type != Material.AIR) {
            val item = currentItemStack.toSkyblockItem()
            return green("Hot Potato Books: ${yellow(item.info.hpb.toString())}")
        }
        return green("Hot Potato Books")
    }

    override fun getDescription(p: SkyblockPlayer): String {
        val inventory = getCurrentInventory(p)
        val currentItemStack = inventory.getItem(22)
        if (currentItemStack != null && currentItemStack.type != Material.AIR) {
            return "${yellow("Click")} to increase by ${green("+1")}" +
                    "\n${yellow("Right-Click")} to decrease by ${green("1")} %%ignoresize%%"
        }
        return "Insert an item into the empty slot to change its hot potato books!"
    }
}