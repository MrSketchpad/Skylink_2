package sketchpad.skylink.stats

import org.bukkit.Bukkit
import sketchpad.skylink.Skylink
import kotlin.properties.Delegates

/**
 * An object that contains all [RegenerativeStat]s and a corresponding value to those stats.
 * Also, naturally regenerates these stats by a specified multiplier at a certain time interval.
 */
open class RegenStatHolder: StatHolder() {
    /**
     * The HashMap containing the stats and their values
     */
    private val regenValues = hashMapOf<RegenerativeStat, Double>()

    var healthMultiplier = 0.01
    var healthInterval = 2 // in seconds
    var healthProcessID by Delegates.notNull<Int>()

    var manaMultiplier = 0.02
    var manaInterval = 1 // in seconds
    var manaProcessID by Delegates.notNull<Int>()

    init {
        for (stat in RegenerativeStat.values()) {
            regenValues[stat] = 0.0
        }
    }

    /**
     * Stops the clock started by [startClock]
     */
    fun stopClock() {
        Bukkit.getScheduler().cancelTask(healthProcessID)
        Bukkit.getScheduler().cancelTask(manaProcessID)
    }
    /**
     * Begins the clock that regenerates Health and Mana by their corresponding multipliers in their corresponding intervals.
     */
    open fun startClock() {
        healthProcessID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Skylink.INSTANCE, {
            val newHealthValue = getRegenStat(RegenerativeStat.HEALTH)+1.5+getStat(Stat.HEALTH)*healthMultiplier
            setRegenStat(RegenerativeStat.HEALTH, Math.min(newHealthValue, getStat(Stat.HEALTH)))
        }, 0, healthInterval.toLong()*20)

        manaProcessID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Skylink.INSTANCE, {
            val newManaValue = getRegenStat(RegenerativeStat.MANA)+(getStat(Stat.INTELLIGENCE)*manaMultiplier)
            setRegenStat(RegenerativeStat.MANA, Math.min(newManaValue, getStat(Stat.INTELLIGENCE)))
        }, 0, manaInterval.toLong()*20)
    }

    /**
     * Adds [delta] to the current value of [stat]
     */
    fun addRegenStat(stat: RegenerativeStat, delta: Double) {
        setRegenStat(stat, getRegenStat(stat)+delta)
    }

    /**
     * Sets [stat] to the value of [value]
     */
    fun setRegenStat(stat: RegenerativeStat, value: Double) {
        regenValues[stat] = value
    }

    /**
     * Returns the value of [stat]
     */
    fun getRegenStat(stat: RegenerativeStat): Double {
        return regenValues[stat]!!
    }

    /**
     * Returns the private [values] HashMap with all stats and their values.
     */
    fun regenAsHashmap(): HashMap<RegenerativeStat, Double> {
        return regenValues
    }
}