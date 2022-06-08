package sketchpad.skylink.commands.autocomplete

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import sketchpad.skylink.item.ItemRegistry

class GeneralTabCompleter: TabCompleter {
    override fun onTabComplete(sender: CommandSender, cmd: Command, str: String, args: Array<String>): List<String>? {
        if (cmd.name == "getitem") {
            return ItemRegistry.asHashMap().keys.toList()
        }
        return null
    }
}