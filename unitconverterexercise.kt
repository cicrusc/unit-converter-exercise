enum class MeasurementType {
    LENGTH,
    WEIGHT,
    TEMPERATURE
}

enum class MeasurementUnit(
    val toBaseFactor: Double,
    val singularName: String,
    val pluralName: String,
    val aliases: Set<String>,
    val type: MeasurementType
) {
    //Length units
    METERS(1.0, "meter", "meters", setOf("m", "meter", "meters"), MeasurementType.LENGTH),
    MILLIMETER(0.001, "millimeter", "millimeters", setOf("mm", "millimeter", "millimeters"), MeasurementType.LENGTH),
    CENTIMETERS(0.01, "centimeter", "centimeters", setOf("cm", "centimeter", "centimeters"), MeasurementType.LENGTH),
    KILOMETERS(1000.0, "kilometer", "kilometers", setOf("km", "kilometer", "kilometers"), MeasurementType.LENGTH),
    INCHES(0.0254, "inch", "inches", setOf("in", "inch", "inches"), MeasurementType.LENGTH),
    FOOT(0.3048, "foot", "feet", setOf("ft", "foot", "feet"), MeasurementType.LENGTH),
    YARD(0.9144, "yard", "yards", setOf("yd", "yard", "yards"), MeasurementType.LENGTH),
    MILE(1609.344, "mile", "miles", setOf("mi", "mile", "miles"), MeasurementType.LENGTH),

    //Weight units
    KILOGRAMS(1.0, "kilogram", "kilograms", setOf("kg", "kilogram", "kilograms"), MeasurementType.WEIGHT),
    GRAMS(0.001, "gram", "grams", setOf("gr", "gram", "grams"), MeasurementType.WEIGHT),
    MILLIGRAM(0.000001, "milligram", "milligrams", setOf("mg", "milligram", "milligrams"), MeasurementType.WEIGHT),
    POUNDS(0.453592, "pound", "pounds", setOf("lb", "pound", "pounds"), MeasurementType.WEIGHT),
    OUNCE(0.028349523125, "ounce", "ounces", setOf("oz", "ounce", "ounces"), MeasurementType.WEIGHT),

    //Temperature Units
    CELSIUS(1.0, "degree Celsius", "degrees Celsius", setOf("c", "celsius", "dc", "degree celsius", "degrees celsius"), MeasurementType.TEMPERATURE),
    FAHRENHEIT(1.0, "degree Fahrenheit", "degrees Fahrenheit", setOf("f", "fahrenheit", "df", "degree fahrenheit", "degrees fahrenheit"), MeasurementType.TEMPERATURE),
    KELVIN(1.0, "Kelvin", "Kelvins", setOf("k", "kelvin", "kelvins"), MeasurementType.TEMPERATURE);
}

fun convertValue(value: Double, fromUnit: MeasurementUnit, toUnit: MeasurementUnit): Double {
    if (fromUnit.type != toUnit.type) {
        throw IllegalArgumentException("Conversion from ${fromUnit.name.lowercase()} to ${toUnit.name.lowercase()} is impossible")
    }

    if (fromUnit.type == MeasurementType.TEMPERATURE && toUnit.type == MeasurementType.TEMPERATURE) {
        return when {
            fromUnit == MeasurementUnit.CELSIUS && toUnit == MeasurementUnit.FAHRENHEIT -> value * 9 / 5 + 32
            fromUnit == MeasurementUnit.FAHRENHEIT && toUnit == MeasurementUnit.CELSIUS -> (value - 32) * 5 / 9
            fromUnit == MeasurementUnit.CELSIUS && toUnit == MeasurementUnit.KELVIN -> value + 273.15
            fromUnit == MeasurementUnit.KELVIN && toUnit == MeasurementUnit.CELSIUS -> value - 273.15
            fromUnit == MeasurementUnit.FAHRENHEIT && toUnit == MeasurementUnit.KELVIN -> (value - 32) * 5 / 9 + 273.15
            fromUnit == MeasurementUnit.KELVIN && toUnit == MeasurementUnit.FAHRENHEIT -> (value - 273.15) * 9 / 5 + 32
            else -> value
        }
    }

    val valueInBaseUnit = value * fromUnit.toBaseFactor
    return valueInBaseUnit / toUnit.toBaseFactor
}

fun main() {
    while (true) {
        println("Enter what you want to convert (or exit):")
        val input = readln()
        if (input.equals("exit", ignoreCase = true)) break

        val parts = input.split(" ")

        try {

            val value = parts[0].toDoubleOrNull() ?: throw IllegalArgumentException()
            var unitOriginString = parts[1].lowercase()
            if (unitOriginString == "degree" || unitOriginString == "degrees") {
                unitOriginString += " " + parts[2].lowercase()
            }

            var unitDestinationString = parts[3].lowercase()
            if (unitDestinationString == "in" || unitDestinationString == "to") {
                unitDestinationString = parts[4].lowercase()
            } else if (unitDestinationString == "degree" || unitDestinationString == "degrees") {
                unitDestinationString += " " + parts[5].lowercase()
            }

            val unitOrigin = MeasurementUnit.entries.find { unitOriginString in it.aliases } ?: throw IllegalArgumentException()

            val unitDestination = MeasurementUnit.entries.find { unitDestinationString in it.aliases } ?: throw IllegalArgumentException()

            val convertedValue = convertValue(value, unitOrigin, unitDestination)
            val originUnitName = if (value == 1.0) unitOrigin.singularName else unitOrigin.pluralName
            val destinationUnitName = if (convertedValue == 1.0) unitDestination.singularName else unitDestination.pluralName
            println("$value $originUnitName is $convertedValue $destinationUnitName")

        } catch (e: IllegalArgumentException) {
            if (e.message?.startsWith("Conversion from") == true) {
                println(e.message)
            } else {
                println("Parse Error")
            }
        }
    }
}
