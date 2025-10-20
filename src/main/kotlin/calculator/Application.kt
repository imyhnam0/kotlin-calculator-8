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

    val processedInput = input.replace("\\n", "\n")

    val numbers = if (processedInput.startsWith("//")) {
        val newlineIndex = processedInput.indexOf('\n')
        val customDelimiter = processedInput.substring(2, newlineIndex)
        val numbersPart = processedInput.substring(newlineIndex + 1)
        numbersPart.split(Regex("[,:$customDelimiter]"))
            .map { it.trim().toInt() }
    } else {
        processedInput.split(Regex("[,:]"))
            .map { it.trim().toInt() }
    }

    return numbers.sum()
}
