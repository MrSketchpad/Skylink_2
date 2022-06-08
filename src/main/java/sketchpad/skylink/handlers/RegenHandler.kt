package sketchpad.skylink.handlers

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityRegainHealthEvent
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason


class RegenHandler: Listener {
    @EventHandler
    fun onRegen(event: EntityRegainHealthEvent) {
        if (event.regainReason == RegainReason.SATIATED || event.regainReason == RegainReason.REGEN) event.isCancelled =
            true
    }
}