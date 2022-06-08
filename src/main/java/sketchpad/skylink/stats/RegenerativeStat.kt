package sketchpad.skylink.stats

import org.bukkit.ChatColor

enum class RegenerativeStat(val color: ChatColor, val symbol: String, val offensive: Boolean) {
    HEALTH(ChatColor.RED, "❤", false),
    MANA(ChatColor.AQUA, "✎", false),
    ;
}