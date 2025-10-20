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

    val numbers = input.split(Regex("[,:]"))
        .map { it.trim().toInt() }

    return numbers.sum()
}
