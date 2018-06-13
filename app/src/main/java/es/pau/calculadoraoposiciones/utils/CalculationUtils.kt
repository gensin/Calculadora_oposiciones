package es.pau.calculadoraoposiciones.utils

/**
* Created on 23/04/2016.
*/

/**
 * This function returns the probability of occurrence of a studied topic in a exam where
 * you have @total topics to studied you had studied @studied topics and you can choose from a
 * group of @taken different topics.
 * @param total Number of total topics that have the exam
 * @param taken Number of topics which you can choose from
 * @param studied Number of topics you study for the exam
 * @return probability of occurrence of a studied topic (by percentage) as string
 */
fun probabilityPercentage(total: Int, taken: Int, studied: Int): String {
    val dividend = factorial(((total-studied).toDouble())) * factorial((total-taken).toDouble())
    val divisor = factorial(total.toDouble()) * factorial((total-studied-taken).toDouble())
    val p = 1-(dividend/divisor)
    val percentage = p * 100
   // return String.format(Locale.getDefault(), "%.2f", percentage)
    if (percentage.isNaN()){
        return "%.2f".format(0.0)
    }
    return "%.2f".format(percentage)
}

fun factorial(num: Double): Double {
        if (num >= 1)
            return num * factorial(num - 1)
        else
            return 1.0
}

fun takeRandomTopics(taken: Int, max : Int): ArrayList<Int> {
    val takenList = ArrayList<Int>()
    while (takenList.size == taken){
        val random = (1..max).random()
        if (!takenList.contains(random)) {
            takenList.add(random)
        }
    }

    return takenList
}