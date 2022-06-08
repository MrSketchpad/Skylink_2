package sketchpad.skylink.item.abilities

import sketchpad.skylink.player.SkyblockPlayer
import kotlin.math.cos

class AbilityBuilder {
    /**
     * The actual ability
     */
    private var ability = Ability("", AbilityTrigger.RIGHT_CLICK, false, false, 0, 0, 0, "")

    /**
     * Returns the ability.
     */
    fun generate(): Ability {
        return ability
    }

    /**
     * Sets the ability's name to [name].
     */
    fun name(name: String): AbilityBuilder {
        ability.name = name
        return this
    }

    /**
     * Sets the ability's trigger to [trigger].
     */
    fun trigger(trigger: AbilityTrigger): AbilityBuilder {
        ability.trigger = trigger
        return this
    }

    /**
     * Sets the ability's sneak status to [sneak].
     */
    fun sneak(sneak: Boolean): AbilityBuilder {
        ability.sneak = sneak
        return this
    }

    /**
     * Sets the ability's toggle status to [toggle].
     */
    fun toggle(toggle: Boolean): AbilityBuilder {
        ability.toggle = toggle
        return this
    }

    /**
     * Sets the ability's mana cost to [cost].
     */
    fun manaCost(cost: Int): AbilityBuilder {
        ability.manaCost = cost
        return this
    }

    /**
     * Sets the ability's health cost to [cost].
     */
    fun healthCost(cost: Int): AbilityBuilder {
        ability.healthCost = cost
        return this
    }

    /**
     * Sets the ability's cooldown to [cooldown].
     */
    fun cooldown(cooldown: Int): AbilityBuilder {
        ability.cooldown = cooldown
        return this
    }

    /**
     * Sets the ability's description cost to [desc].
     */
    fun description(desc: String): AbilityBuilder {
        ability.description = desc
        return this
    }
}