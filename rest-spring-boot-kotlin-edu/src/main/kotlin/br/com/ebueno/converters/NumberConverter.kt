package br.com.ebueno.converters

object NumberConverter {
    fun convertToDouble(number: String?): Double {
        if(number.isNullOrBlank()) return 0.0
        val num = number.replace(",".toRegex(), replacement = ".")
        return if(isNumeric(num)) num.toDouble() else 0.0
    }

    fun isNumeric(number: String?): Boolean {
        if(number.isNullOrBlank()) return false
        val num = number.replace(",".toRegex(), replacement = ".")
        return num.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
    }
}