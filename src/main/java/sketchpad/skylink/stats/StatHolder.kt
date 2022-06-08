package sketchpad.skylink.stats

/**
 * An object that contains all [Stat]s and a corresponding value to those stats.
 */
open class StatHolder(open val values: HashMap<Stat, Double> = hashMapOf()) {
    /**
     * The HashMap containing the stats and their values
     */
    init {
        if (values.isEmpty()) {
            for (stat in Stat.values()) {
                values[stat] = 0.0
            }
        }
    }

    open fun hasStat(stat: Stat): Boolean {
        if (values.containsKey(stat)) {
            if ((values[stat]!!)!=0.0) return true
        }
        return false
    }
    /**
     * Adds [delta] to the current value of [stat]
     */
    open fun addStat(stat: Stat, delta: Double) {
        setStat(stat, getStat(stat)+delta)
    }

    /**
     * Sets [stat] to the value of [value]
     */
    open fun setStat(stat: Stat, value: Double) {
        values[stat] = value
    }

    /**
     * Returns the value of [stat]
     */
    open fun getStat(stat: Stat): Double {
        return values[stat]!!
    }

    /**
     * Returns the private [values] HashMap with all stats and their values.
     */
    open fun asHashMap(): HashMap<Stat, Double> {
        return values
    }

    /**
     * Resets all stats to 0
     */
    open fun resetStats() {
        for (stat in Stat.values()) {
            values[stat] = 0.0
        }
    }

    /**
     * Combines [other] and [this] into a new [StatHolder]
     */
    fun combine(other: StatHolder): StatHolder {
        val newMap = hashMapOf<Stat, Double>()
        for (stat in other.asHashMap()) {
            newMap[stat.key] = stat.value+getStat(stat.key)
        }
        return StatHolder(newMap)
    }

    fun replaceWithOther(other: StatHolder) {
        for (stat in other.asHashMap()) {
            setStat(stat.key, stat.value)
        }
    }
}