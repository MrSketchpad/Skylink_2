package sketchpad.skylink

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin
import sketchpad.skylink.commands.GiveItemCommand
import sketchpad.skylink.commands.UpgradeCommand
import sketchpad.skylink.commands.autocomplete.GeneralTabCompleter
import sketchpad.skylink.gui.constants.GUIs
import sketchpad.skylink.gui.constants.UpgradeItemGUI
import sketchpad.skylink.gui.items.constants.GUIItems
import sketchpad.skylink.handlers.AbilityTriggerHandler
import sketchpad.skylink.handlers.GUIHandler
import sketchpad.skylink.handlers.InfoManagementHandler
import sketchpad.skylink.handlers.RegenHandler
import sketchpad.skylink.info.JsonManager
import sketchpad.skylink.item.ItemRegistry
import sketchpad.skylink.item.SkyblockItem
import sketchpad.skylink.item.constants.Items
import sketchpad.skylink.player.PlayerInfo
import sketchpad.skylink.player.SkyblockEntity
import sketchpad.skylink.player.SkyblockPlayer
import sketchpad.skylink.stats.RegenerativeStat
import sketchpad.skylink.stats.Stat
import java.io.File
import java.util.*

class Skylink : JavaPlugin() {
    companion object {
        lateinit var INSTANCE: Skylink

        lateinit var INFO_FOLDER: File
        lateinit var PLAYER_INFO_FOLDER: File
        val players = hashMapOf<UUID, SkyblockPlayer>()
        val mobs = hashMapOf<UUID, SkyblockEntity>()
    }

    override fun onEnable() {
        INSTANCE = this
        PLAYER_INFO_FOLDER = File(INSTANCE.dataFolder.toString() + "\\Players")
        INFO_FOLDER = File(INSTANCE.dataFolder.toString())
        INFO_FOLDER.mkdirs()
        PLAYER_INFO_FOLDER.mkdirs()

        Items.registerItems()
        GUIItems.registerItems()
        GUIs.registerGUIs()

        getCommand("upgradeitem")!!.setExecutor(UpgradeCommand())
        getCommand("getitem")!!.setExecutor(GiveItemCommand())
        getCommand("getitem")!!.tabCompleter = GeneralTabCompleter()

        server.pluginManager.registerEvents(InfoManagementHandler(), INSTANCE)
        server.pluginManager.registerEvents(AbilityTriggerHandler(), INSTANCE)
        server.pluginManager.registerEvents(RegenHandler(), INSTANCE)
        server.pluginManager.registerEvents(GUIHandler(), INSTANCE)

        for (player in Bukkit.getOnlinePlayers()) {
            if (!JsonManager.infoWritten(player)) {
                JsonManager.writePlayerInfo(player, PlayerInfo(hashMapOf()))
            }
            for (item in JsonManager.getPlayerInfo(player).inventory) {
                item.value.base = ItemRegistry.getItem(item.value.base.getID())
                player.inventory.setItem(item.key, item.value.toItemStack())
            }
            val skyblockPlayer = SkyblockPlayer(player)
            skyblockPlayer.startClock()
            players[player.uniqueId] = skyblockPlayer
            skyblockPlayer.updateStats()
            skyblockPlayer.setRegenStat(RegenerativeStat.HEALTH, skyblockPlayer.getStat(Stat.HEALTH))
            skyblockPlayer.setRegenStat(RegenerativeStat.MANA, skyblockPlayer.getStat(Stat.INTELLIGENCE))
        }

        server.consoleSender.sendMessage("${ChatColor.GREEN}[Skylink] Plugin has been successfully enabled.")
        println(ItemRegistry.asHashMap().keys.toList())
    }

    override fun onDisable() {
        for (player in Bukkit.getOnlinePlayers()) {
            val inventory = hashMapOf<Int, SkyblockItem>()
            for (i in 0..54) {
                val item =  player.inventory.getItem(i)
                if (item!=null) {
                    inventory[i] = SkyblockItem.fromItemStack(item)
                }
            }
            val info = JsonManager.getPlayerInfo(player)
            info.inventory = inventory
            JsonManager.writePlayerInfo(player, info)
        }
    }
}