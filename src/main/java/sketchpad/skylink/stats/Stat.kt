package sketchpad.skylink.stats

import org.bukkit.ChatColor

enum class Stat(val color: ChatColor, val symbol: String, val offensive: Boolean) {
    HEALTH(ChatColor.RED, "❤", false),
    DEFENSE(ChatColor.GREEN, "❈", false),
    STRENGTH(ChatColor.RED, "❁", true),
    INTELLIGENCE(ChatColor.AQUA, "✎", false),
    SPEED(ChatColor.WHITE, "✦", false),
    CRIT_CHANCE(ChatColor.BLUE, "☣", true),
    CRIT_DAMAGE(ChatColor.BLUE, "☠", true),
    BONUS_ATTACK_SPEED(ChatColor.YELLOW, "⚔", true),
    ABILITY_DAMAGE(ChatColor.RED, "๑", true),
    TRUE_DEFENSE(ChatColor.WHITE, "❂", false),
    FEROCITY(ChatColor.RED, "⫽", true),
    MAGIC_FIND(ChatColor.AQUA, "✯", false),
    PET_LUCK(ChatColor.LIGHT_PURPLE, "♣", false),
    SEA_CREATURE_CHANCE(ChatColor.DARK_AQUA, "α", false),
    MINING_SPEED(ChatColor.GOLD, "⸕", false),
    MINING_FORTUNE(ChatColor.GOLD, "☘", false),
    FARMING_FORTUNE(ChatColor.GOLD, "☘", false),
    FORAGING_FORTUNE(ChatColor.GOLD, "☘", false),
    PRISTINE(ChatColor.DARK_PURPLE, "✧", false),
    DAMAGE(ChatColor.RED, "❁", true),
    TRUE_DAMAGE(ChatColor.WHITE, "❂", true),
    ABSORPTION(ChatColor.GOLD, "❤", false),
    BREAKING_POWER(ChatColor.DARK_GREEN, "Ⓟ", false),
    OVERFLOW(ChatColor.DARK_AQUA, "ʬ", false),
    HEAT(ChatColor.DARK_RED, "♨", false),
    ;
}