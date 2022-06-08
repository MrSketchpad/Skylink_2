package sketchpad.skylink.handlers

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import sketchpad.skylink.Skylink
import sketchpad.skylink.info.JsonManager
import sketchpad.skylink.item.ItemRegistry
import sketchpad.skylink.item.SkyblockItem
import sketchpad.skylink.player.PlayerInfo
import sketchpad.skylink.player.SkyblockPlayer
import sketchpad.skylink.stats.RegenerativeStat
import sketchpad.skylink.stats.Stat

class InfoManagementHandler: Listener {
    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        if (!JsonManager.infoWritten(e.player)) {
            JsonManager.writePlayerInfo(e.player, PlayerInfo(hashMapOf()))
        }
        for (item in JsonManager.getPlayerInfo(e.player).inventory) {
            item.value.base = ItemRegistry.getItem(item.value.base.getID())
            e.player.inventory.setItem(item.key, item.value.toItemStack())
        }
        val skyblockPlayer = SkyblockPlayer(e.player)
        skyblockPlayer.startClock()
        Skylink.players[e.player.uniqueId] = skyblockPlayer
        skyblockPlayer.updateStats()
        skyblockPlayer.setRegenStat(RegenerativeStat.HEALTH, skyblockPlayer.getStat(Stat.HEALTH))
        skyblockPlayer.setRegenStat(RegenerativeStat.MANA, skyblockPlayer.getStat(Stat.INTELLIGENCE))
    }

    @EventHandler
    fun onLeave(e: PlayerQuitEvent) {
        val inventory = hashMapOf<Int, SkyblockItem>()
        for (i in 0..54) {
            val item =  e.player.inventory.getItem(i)
            if (item!=null) {
                inventory[i] = SkyblockItem.fromItemStack(item)
            }
        }
        val info = JsonManager.getPlayerInfo(e.player)
        info.inventory = inventory
        JsonManager.writePlayerInfo(e.player, info)
        Skylink.players[e.player.uniqueId]!!.startClock()
        Skylink.players.remove(e.player.uniqueId)
    }
}