package sketchpad.skylink.item.constants.sword

import org.bukkit.Material
import sketchpad.skylink.item.BaseSkyblockItem
import sketchpad.skylink.item.ItemBuilder
import sketchpad.skylink.item.abilities.Ability
import sketchpad.skylink.item.abilities.AbilityBuilder
import sketchpad.skylink.item.abilities.AbilityTrigger
import sketchpad.skylink.item.attributes.ItemType
import sketchpad.skylink.item.attributes.Rarity
import sketchpad.skylink.player.SkyblockPlayer
import sketchpad.skylink.stats.Stat
import sketchpad.skylink.utils.text.green
import sketchpad.skylink.utils.text.white

class AspectOfTheEnd: BaseSkyblockItem(
    ItemBuilder(Material.DIAMOND_SWORD)
        .name("Aspect of the End")
        .rarity(Rarity.RARE)
        .type(ItemType.SWORD)
        .damage(100.0)
        .strength(100.0)
        .addAbility(InstantTransmission())
        .generate(true)) {

    class InstantTransmission: Ability(
        AbilityBuilder()
            .name("Instant Transmission")
            .manaCost(50)
            .description("Teleport ${green(("8 blocks"))} ahead of you and gain ${green("+50")} ${white(Stat.SPEED.symbol)+white(" Speed")} for ${green("3 seconds")}.")
            .generate()) {
        override fun action(p: SkyblockPlayer) {
            p.teleport(8)
        }
    }
}