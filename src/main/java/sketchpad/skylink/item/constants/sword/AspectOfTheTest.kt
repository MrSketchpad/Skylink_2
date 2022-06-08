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

class AspectOfTheTest: BaseSkyblockItem(
    ItemBuilder(Material.DIAMOND_SWORD)
    .name("Aspect of the Test")
    .rarity(Rarity.EPIC)
    .type(ItemType.SWORD)
    .lore("This item is just for testing!")
        .health(1000.0)
        .damage(1000.0)
        .defense(1000.0)
        .intelligence(1000.0)
        .speed(1000.0)
    .addAbility(MoveTheEarthBackEightBlocks())
    .addAbility(MoveTheEarthBackEightBlocksExceptWithoutACooldown())
    .generate(true)) {

    class MoveTheEarthBackEightBlocks: Ability(
        AbilityBuilder()
            .name("mteb8b")
            .manaCost(50)
            .cooldown(1)
            .description("The name is self-explanatory.")
            .generate()) {
        override fun action(p: SkyblockPlayer) {
            p.teleport(8)
        }
    }

    class MoveTheEarthBackEightBlocksExceptWithoutACooldown: Ability(
        AbilityBuilder()
            .name("mteb8bewac")
            .manaCost(5)
            .cooldown(0)
            .description("mteb8b, ewac.")
            .trigger(AbilityTrigger.LEFT_CLICK)
            .generate()) {
        override fun action(p: SkyblockPlayer) {
            p.teleport(8)
        }
    }
}