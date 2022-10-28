fun main() {
    println("Enter the number of rows:")
    val nbRows = try {
        val input = readln().toInt()
        if (input < 0 || input > 100) throw Exception() else input
    } catch (e: Exception) {
        println("\nInput error. Default to 15 rows.")
        15
    }

    println("Enter the number of seats in each row:")
    val nbSeats = try {
        val input = readln().toInt()
        if (input < 0 || input > 100) throw Exception() else input
    } catch (e: Exception) {
        println("\nInput error. Default to 15 seats.")
        15
    }

    val cinema = Cinema(nbRows, nbSeats)

    var isRunning = true
    while (isRunning) {
        try {
            cinema.printCinemaOptions()
            when (readln().toInt()) {
                1 -> cinema.printCinema()
                2 -> {
                    var isValidInput = false
                    do {
                        try {
                            println("\nEnter a row number:")
                            val row = readln().toInt()
                            println("Enter a seat number in that row:")
                            val seat = readln().toInt()
                            isValidInput = cinema.bookCinemaSeat(row, seat)
                        } catch (e: Exception) {
                            println("\nSomething is wrong with your choice input...")
                        }
                    } while (!isValidInput)
                }

                3 -> cinema.printCinemaCinemaStatistics()

                else -> isRunning = false
            }
        } catch (e: Exception) {
            println("\nSomething is wrong with your choice input...")
        }
    }
}