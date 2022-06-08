package sketchpad.skylink.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import sketchpad.skylink.gui.constants.GUIs
import sketchpad.skylink.player.SkyblockPlayer

class UpgradeCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            sender.openInventory(GUIs.UPGRADE_ITEM.gui.asInventory(SkyblockPlayer.of(sender)))
            return true
        } else sender.sendMessage("Only players can execute this command!")
        return false
    }
}