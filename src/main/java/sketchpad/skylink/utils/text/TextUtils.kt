package sketchpad.skylink.utils.text

/**
 * Description codes:
 * %%ignoresize%% will not auto-indent the line that it's written in
 */
fun formatForDescription(text: String): List<String> {
    val characterLimit = 19
    val lines = text.lines()
    val formatted = mutableListOf<String>()
    for (line in lines) {
        val words = line.split(" ")
        var currentLength = 0
        var currentLine = ""
        for (w in words) {
            val word = w.replace("%%ignoresize%%", "")
            if (currentLength <= characterLimit || line.contains("%%ignoresize%%")) {
                currentLine += "${if (currentLength == 0) "" else " "}$word"
                currentLength += word.length
            } else {
                formatted.add(gray("$currentLine $word"))
                currentLine = ""
                currentLength = 0
            }
        }
        if (currentLine.isNotEmpty()) {
            formatted.add(gray(currentLine))
        }
    }
    return formatted
}

fun screamingSnakeToRegular(s: String): String {
    val newString = s.toLowerCase().replace("_", "")
    val words = newString.split(" ")
    var finalString = ""
    for (word in words) {
        val firstChar = word.substring(0, 1)
        val restOfWord = word.substring(1)
        finalString += firstChar.uppercase()+restOfWord+" "
    }
    // To remove final space
    finalString = finalString.substring(0, finalString.length-1)
    return finalString
}