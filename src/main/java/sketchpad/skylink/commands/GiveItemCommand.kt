package sketchpad.skylink.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import sketchpad.skylink.item.ItemRegistry
import sketchpad.skylink.item.attributes.ItemInfo
import sketchpad.skylink.utils.text.green
import sketchpad.skylink.utils.text.red

class GiveItemCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (args.size==1) {
                if (ItemRegistry.hasItem(args[0])) {
                    val item = ItemRegistry.getItem(args[0]).getCleanItem()
                    sender.inventory.addItem(item.toItemStack())
                    sender.sendMessage(green("Gave you a ${item.base.name}."))
                    return true
                } else sender.sendMessage(red("The item ${args[0]} does not exist!"))
            } else sender.sendMessage(red("You must specify what item to give!"))
        } else sender.sendMessage(red("Only players can execute this command!"))
        return false
    }
}