package sketchpad.skylink.stats


class StatBuilder {
    private val stats = StatHolder()

    fun generate(): StatHolder {
        return stats
    }

    fun health(value: Double): StatBuilder {
        stats.setStat(Stat.HEALTH, value)
        return this
    }
    fun defense(value: Double): StatBuilder {
        stats.setStat(Stat.DEFENSE, value)
        return this
    }
    fun strength(value: Double): StatBuilder {
        stats.setStat(Stat.STRENGTH, value)
        return this
    }
    fun intelligence(value: Double): StatBuilder {
        stats.setStat(Stat.INTELLIGENCE, value)
        return this
    }
    fun speed(value: Double): StatBuilder {
        stats.setStat(Stat.SPEED, value)
        return this
    }
    fun critChance(value: Double): StatBuilder {
        stats.setStat(Stat.CRIT_CHANCE, value)
        return this
    }
    fun critDamage(value: Double): StatBuilder {
        stats.setStat(Stat.CRIT_DAMAGE, value)
        return this
    }
    fun attackSpeed(value: Double): StatBuilder {
        stats.setStat(Stat.BONUS_ATTACK_SPEED, value)
        return this
    }
    fun abilityDamage(value: Double): StatBuilder {
        stats.setStat(Stat.ABILITY_DAMAGE, value)
        return this
    }
    fun trueDefense(value: Double): StatBuilder {
        stats.setStat(Stat.TRUE_DEFENSE, value)
        return this
    }
    fun ferocity(value: Double): StatBuilder {
        stats.setStat(Stat.FEROCITY, value)
        return this
    }
    fun magicFind(value: Double): StatBuilder {
        stats.setStat(Stat.MAGIC_FIND, value)
        return this
    }
    fun petLuck(value: Double): StatBuilder {
        stats.setStat(Stat.PET_LUCK, value)
        return this
    }
    fun seaCreatureChance(value: Double): StatBuilder {
        stats.setStat(Stat.SEA_CREATURE_CHANCE, value)
        return this
    }
    fun miningSpeed(value: Double): StatBuilder {
        stats.setStat(Stat.MINING_SPEED, value)
        return this
    }
    fun miningFortune(value: Double): StatBuilder {
        stats.setStat(Stat.MINING_FORTUNE, value)
        return this
    }
    fun farmingFortune(value: Double): StatBuilder {
        stats.setStat(Stat.FARMING_FORTUNE, value)
        return this
    }
    fun foragingFortune(value: Double): StatBuilder {
        stats.setStat(Stat.FORAGING_FORTUNE, value)
        return this
    }
    fun pristine(value: Double): StatBuilder {
        stats.setStat(Stat.PRISTINE, value)
        return this
    }
    fun damage(value: Double): StatBuilder {
        stats.setStat(Stat.DAMAGE, value)
        return this
    }
    fun trueDamage(value: Double): StatBuilder {
        stats.setStat(Stat.TRUE_DAMAGE, value)
        return this
    }
    fun absorption(value: Double): StatBuilder {
        stats.setStat(Stat.ABSORPTION, value)
        return this
    }
    fun breakingPower(value: Double): StatBuilder {
        stats.setStat(Stat.BREAKING_POWER, value)
        return this
    }
    fun overflowMana(value: Double): StatBuilder {
        stats.setStat(Stat.OVERFLOW, value)
        return this
    }
    fun heat(value: Double): StatBuilder {
        stats.setStat(Stat.HEAT, value)
        return this
    }
}