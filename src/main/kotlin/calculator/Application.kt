package calculator

fun main() {
    val input = readln()
    val result = add(input)
    println("결과 : $result")
}

fun add(input: String?): Int {
    if (input.isNullOrEmpty()) {
        return 0
    }
    val numbers = parseNumbers(input)
    return numbers.sum()
}

fun parseNumbers(input: String): List<Int> {
    val processedInput = input.replace("\\n", "\n")

    val (delimiter, numbersPart) = if (processedInput.startsWith("//")) {
        val newlineIndex = processedInput.indexOf('\n')
        val (customDelimiter, numbers) = if (newlineIndex != -1) {
            val d = processedInput.substring(2, newlineIndex).trim()
            val n = processedInput
                .substring(newlineIndex + 1)
                .replace("\r", "")
                .trim()
            d to n
        } else {
            val d = processedInput.substring(2, 3)
            val n = processedInput.substring(3)
                .replace("\r", "")
                .trim()
            d to n
        }

        Regex.escape(customDelimiter) to numbers
    } else {
        "[,:]" to processedInput.replace("\r", "").trim()
    }

    return numbersPart
        .split(Regex(delimiter))
        .map {
            val number = it.trim().toIntOrNull()
                ?: throw IllegalArgumentException("❌ 숫자가 아닙니다: '$it'")
            if (number < 0) {
                throw IllegalArgumentException("❌ 음수는 허용되지 않습니다: $number")
            }
            number
        }
}


