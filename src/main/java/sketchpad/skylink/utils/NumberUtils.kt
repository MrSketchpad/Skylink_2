package sketchpad.skylink.utils

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.regex.Pattern

fun formatWithCommas(num: String, int: Boolean): String {
    val pattern = Pattern.compile("-?\\d+(\\.\\d+)?")
    if (pattern.matcher(num).matches()) {
        val decimal = BigDecimal(num)
        val format = if (int) DecimalFormat("###,###") else DecimalFormat("#,##0.0")
        return format.format(decimal)
    } else {
        throw IllegalArgumentException("Parameter num must be an integer or float in string form!")
    }
}

fun Double.formatWithCommas(int: Boolean): String {
    return formatWithCommas(this.toString(), int)
}
fun Int.formatWithCommas(): String {
    return formatWithCommas(this.toString(), true)
}