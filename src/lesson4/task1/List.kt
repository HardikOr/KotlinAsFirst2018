@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.log
import kotlin.math.sqrt
import kotlin.math.pow

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>) = sqrt(v.fold(0.0) { sum, el -> sum + el * el })

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>) = if (list.isEmpty()) 0.0 else list.average()

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val average = mean(list)

    for (i in 0..(list.size - 1)) list[i] -= average

//    By using iterator:
//    val iterate = list.listIterator()
//    while (iterate.hasNext()) iterate.set(iterate.next() - average)

    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>) = a.foldIndexed(0.0) { i, sum, el -> sum + el * b[i] }

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double) = p.foldIndexed(0.0) { i, sum, el -> sum + el * Math.pow(x, i.toDouble()) }

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    var sum = 0.0

    if (list.isNotEmpty()) {
        sum += list[0]
        for (i in 1..(list.size - 1)) {
            sum += list[i]
            list[i] = sum
        }
    }

    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var newN = n
    val list = mutableListOf<Int>()

    for (i in 2..(sqrt(n.toDouble()).toInt()))
        if (newN % i == 0)
            while (newN % i == 0) {
                newN /= i
                list.add(i)
            }
    if (newN != 1) list.add(newN)

    return list.toList()
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int) = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val len = if (n == 0) 0 else log(n.toDouble(), base.toDouble()).toInt()
    var power = (base.toDouble()).pow(len).toInt()

    val list = MutableList(len + 1) { 0 }
    var newN = n

    for (i in 0..len) {
        if (power <= newN) while (power <= newN) {
            newN -= power
            list[i]++
        }
        power /= base

        if (newN == 0)
            break
    }

    return list
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToSymbol(s: Int): Char {
    if ('0' + s in '0'..'9')
        return '0' + s
    return 'a' + s - 10
}

fun convertToString(n: Int, base: Int) = convert(n, base).fold("") { sum, el -> sum + convertToSymbol(el) }

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var sum = 0
    var power = 1

    for (i in (digits.size - 1) downTo 0) {
        sum += power * digits[i]
        power *= base
    }

    return sum
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun convertFromSymbol(s: Char): Int {
    if (s in 'a'..'z')
        return s + 10 - 'a'
    return s - '0'
}

fun decimalFromString(str: String, base: Int): Int {
    val list = mutableListOf<Int>()

    for (i in 0..(str.length - 1)) list.add(convertFromSymbol(str[i]))

    return decimal(list, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun convertToRoman(n: Int, s1: String, s2: String, s3: String) = when (n) {
    1 -> s1
    2 -> s1 + s1
    3 -> s1 + s1 + s1
    4 -> s1 + s2
    5 -> s2
    6 -> s2 + s1
    7 -> s2 + s1 + s1
    8 -> s2 + s1 + s1 + s1
    9 -> s1 + s3
    else -> ""
}

fun roman(n: Int): String {
    val str = convertToRoman((n % 1000) / 100, "C", "D", "M") +
            convertToRoman((n % 100) / 10, "X", "L", "C") +
            convertToRoman(n % 10, "I", "V", "X")

    return when (n / 1000) {
        1 -> "M$str"
        2 -> "MM$str"
        3 -> "MMM$str"
        else -> str
    }
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun ctr(n: Int, mode: Int): String {
    if (n >= 100) {
        val str = ctr(n % 100, mode)
        return when ((n % 1000) / 100) {
            1 -> "сто $str"
            2 -> "двести $str"
            3 -> "триста $str"
            4 -> "четыреста $str"
            5 -> "пятьсот $str"
            6 -> "шестьсот $str"
            7 -> "семьсот $str"
            8 -> "восемьсот $str"
            9 -> "девятьсот $str"
            else -> ""
        }
    }

    if (n >= 20) {
        val str = ctr(n % 10, mode)
        return when ((n % 100) / 10) {
            2 -> "двадцать $str"
            3 -> "тридцать $str"
            4 -> "сорок $str"
            5 -> "пятьдесят $str"
            6 -> "шестьдесят $str"
            7 -> "семьдесят $str"
            8 -> "восемьдесят $str"
            9 -> "девяносто $str"
            else -> ""
        }
    }

    when (n) {
        1 -> return if (mode == 1) "один" else "одна тысяча "
        2 -> return if (mode == 1) "два" else "две тысячи "
        3 -> return if (mode == 1) "три" else "три тысячи "
        4 -> return if (mode == 1) "четыре" else "четыре тысячи "
    }

    val str = when (n % 100) {
        5 -> "пять"
        6 -> "шесть"
        7 -> "семь"
        8 -> "восемь"
        9 -> "девять"
        10 -> "десять"
        11 -> "одиннадцать"
        12 -> "двенадцать"
        13 -> "тринадцать"
        14 -> "четырнадцать"
        15 -> "пятнадцать"
        16 -> "шестнадцать"
        17 -> "семнадцать"
        18 -> "восемнадцать"
        19 -> "девятнадцать"
        else -> ""
    }

    return if (mode == 1) str else "$str тысяч "
}

fun russian(n: Int): String =
        ((if (n > 999) ctr(n / 1000, 2) else "") + ctr(n % 1000, 1))
                .replace("  ", " ").trim()