package sketchpad.skylink.item.attributes

import org.bukkit.ChatColor

enum class Rarity(val color: ChatColor) {
    COMMON(ChatColor.WHITE),
    UNCOMMON(ChatColor.GREEN),
    RARE(ChatColor.BLUE),
    EPIC(ChatColor.DARK_PURPLE),
    LEGENDARY(ChatColor.GOLD),
    MYTHIC(ChatColor.LIGHT_PURPLE),
    DIVINE(ChatColor.AQUA),
    SPECIAL(ChatColor.RED),
    VERY_SPECIAL(ChatColor.RED),
    ;
}