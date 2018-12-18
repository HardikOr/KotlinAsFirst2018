@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

import kotlin.math.max

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
        shoppingList: List<String>,
        costs: Map<String, Double>): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
        phoneBook: MutableMap<String, String>,
        countryCode: String) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
        text: List<String>,
        vararg fillerWords: String): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val res = mapA.toMutableMap()

    for ((key, value) in mapB)
        if (key in mapA && res[key] != value)
            res[key] = res[key] + ", " + value
        else
            res[key] = value

    return res
}

/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val res = mutableMapOf<Int, MutableList<String>>()

    for ((key, value) in grades) {
        val a = res.getOrPut(value) { mutableListOf() }
        a.add(key)
        res[value] = a
    }

    return res
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    val copyA = a.toMutableMap()

    for ((key, value) in a)
        if (b[key] == value)
            copyA.remove(key, value)

    return copyA.isEmpty()
}

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val ans = mutableMapOf<String, MutableList<Double>>()

    for ((key, value) in stockPrices)
        if (key in ans)
            ans[key]!!.add(value)
        else
            ans[key] = mutableListOf(value)

    val res = mutableMapOf<String, Double>()

    for ((key, value) in ans) res[key] = value.average()

    return res
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    val stuff2 = mutableListOf<Pair<String, Double>>() // Name, Cost

    for ((key, value) in stuff)
        if (value.first == kind)
            stuff2.add(Pair(key, value.second))

    return if (stuff2.isEmpty()) null else stuff2.minWith(compareBy { it.second })!!.first
}

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */
fun checkAndAddHandshakes(friends: Map<String, Set<String>>,
                          map: Map<String, MutableSet<String>>,
                          name: String,
                          fset: Set<String>) {
    if (fset.isEmpty())
        return

    for (i in fset)
        if (i != name && i !in map[name]!!) {
            map[name]!!.add(i)
        if (i in friends) checkAndAddHandshakes(friends, map, name, friends[i]!!)
    }
}

fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    val res = mutableMapOf<String, MutableSet<String>>()

    for ((key, value) in friends) {
        if (key !in res) res[key] = mutableSetOf()
        for (i in value) if (i !in res) res[i] = mutableSetOf()
    }

    for ((key, value) in friends) checkAndAddHandshakes(friends, res, key, value)

    return res
}

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>) {
    for ((key, value) in b) a.remove(key, value)
}

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> = a.intersect(b).toList()

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean =
        chars.map { it.toLowerCase() }.toSet() == (word + chars.joinToString("")).toLowerCase().toSet()

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun symbolsMap(list: List<String>) = list.groupingBy { it }.eachCount()

fun extractRepeats(list: List<String>): Map<String, Int> =
        symbolsMap(list).filter { it.value > 1 }

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
//fun symbolsMap2(str: String) =
//        symbolsMap(str.map { it.toString() })
//
//fun extractMapRepeats(map: List<Map<String, Int>>) =
//        map.groupingBy { it }.eachCount()
//
//fun hasAnagrams(words: List<String>): Boolean =
//        extractMapRepeats(words.map { symbolsMap2(it) })
//                .filter { it.value > 1 }.isNotEmpty()
fun hasAnagrams(words: List<String>): Boolean =
        words.map { it2 -> symbolsMap(it2.map { it2 }) }
                .groupingBy { it }.eachCount()
                .filter { it.value > 1 }.isNotEmpty()

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    val map = list.mapIndexed { index, i -> Pair(i, index) }.groupBy({ it.first }, { it.second })

    for ((key, value) in map) {
        val second = map[number - key]
        if (second != null)
            return if (key == number - key)
                if (value.size > 1)
                    Pair(value[0], value[1])
                else
                    continue
            else
                Pair(value[0], second[0])
    }

    return Pair(-1, -1)
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun calc(treas: List<Pair<String, Pair<Int, Int>>>,
         cost: MutableMap<Pair<Int, Int>, Int>,
         res: MutableSet<String>,
         k: Int, s: Int) {
    if (cost[Pair(k, s)] == 0)
        return
    if (cost[Pair(k, s)] == cost[Pair(k - 1, s)])
        calc(treas, cost, res, k - 1, s)
    else {
        calc(treas, cost, res, k - 1, s - treas[k - 1].second.first)
        res.add(treas[k - 1].first)
    }
}

fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    val treas = treasures.toList()
    val amount = treas.size

    val cost = mutableMapOf<Pair<Int, Int>, Int>() // (Weight, first N backpacks), maximum cost
    val res = mutableSetOf<String>()

    for (i in 0..amount) cost[Pair(i, 0)] = 0
    for (i in 0..capacity) cost[Pair(0, i)] = 0

    for (k in 1..amount)
        for (s in 1..capacity)
            if (s >= treas[k - 1].second.first)
                cost[Pair(k, s)] =
                        max(cost[Pair(k - 1, s)]!!, cost[Pair(k - 1, s - treas[k - 1].second.first)]!! + treas[k - 1].second.second)
            else
                cost[Pair(k, s)] = cost[Pair(k - 1, s)]!!

    calc(treas, cost, res, amount, capacity)

    return res
}



