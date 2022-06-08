package sketchpad.skylink.handlers

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerToggleSneakEvent
import sketchpad.skylink.item.abilities.AbilityTrigger
import sketchpad.skylink.player.SkyblockPlayer

class AbilityTriggerHandler: Listener {
    @EventHandler
    fun onInteract(e: PlayerInteractEvent) {
        val sb = SkyblockPlayer.of(e.player)
        for (ab in sb.getMainHand().base.abilities) {
            if (ab.sneak) {
                if (!e.player.isSneaking) {
                    continue
                }
            }
            if (ab.trigger == AbilityTrigger.RIGHT_CLICK) {
                if (e.action == Action.RIGHT_CLICK_BLOCK || e.action == Action.RIGHT_CLICK_AIR) {
                    ab.activate(sb)
                }
            } else if (ab.trigger == AbilityTrigger.LEFT_CLICK) {
                if (e.action == Action.LEFT_CLICK_BLOCK || e.action == Action.LEFT_CLICK_AIR) {
                    ab.activate(sb)
                }
            }
        }
    }

    @EventHandler
    fun onJump(e: PlayerJumpEvent) {
        val sb = SkyblockPlayer.of(e.player)
        for (ab in sb.getMainHand().base.abilities) {
            if (ab.sneak) {
                if (!e.player.isSneaking) {
                    continue
                }
            }
            if (ab.trigger == AbilityTrigger.JUMP) {
                ab.activate(sb)
            }
        }
    }

    @EventHandler
    fun onSneak(e: PlayerToggleSneakEvent) {
        val sb = SkyblockPlayer.of(e.player)
        for (ab in sb.getMainHand().base.abilities) {
            if (ab.trigger == AbilityTrigger.SNEAK) {
                if (e.isSneaking) {
                    ab.activate(sb)
                }
            }
        }
    }
}