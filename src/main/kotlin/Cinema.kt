const val SEATS_MAX_LIMIT = 60
const val NORMAL_PRICE = 10
const val LOW_PRICE = 8
const val EMPTY_SEAT = "S"
const val BOOKED_SEAT = "B"

class Cinema(private val nbRows: Int, private val nbSeats: Int) {
    private val cinemaRoom =
        MutableList(nbRows) { MutableList(nbSeats) { EMPTY_SEAT } }
    private var purchasedSeats = 0
    private var currentIncome = 0

    fun printCinemaOptions() {
        println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit")
    }

    fun printCinemaCinemaStatistics() {
        val percentageOccupied =
            (purchasedSeats * 100.0) / (this.nbRows * this.nbSeats)

        var statisticsString = "\n"
        statisticsString += "Number of purchased tickets: ${this.purchasedSeats}"
        statisticsString += "\nPercentage: ${"%.2f".format(percentageOccupied)}%"
        statisticsString += "\nCurrent income: $${this.currentIncome}"
        statisticsString += "\nTotal income: $${getTotalIncomePossible()}"
        println(statisticsString)
    }

    fun bookCinemaSeat(chosenRow: Int, chosenSeat: Int): Boolean {
        if (chosenRow > this.nbRows || chosenSeat > this.nbSeats) {
            println("\nWrong input!")
            return false
        }

        if (isBooked(chosenRow, chosenSeat)) {
            println("\nThat ticket has already been purchased!")
            return false
        }

        val seatPrice = getCinemaSeatPrice(chosenRow)
        purchasedSeats++
        currentIncome += seatPrice
        this.cinemaRoom[chosenRow - 1][chosenSeat - 1] = BOOKED_SEAT

        println("\nTicket price: $$seatPrice")
        return true
    }

    private fun isBooked(chosenRow: Int, chosenSeat: Int): Boolean {
        return this.cinemaRoom[chosenRow - 1][chosenSeat - 1] == BOOKED_SEAT
    }

    private fun getTotalIncomePossible(): Int {
        var totalIncome = 0
        for (row in 1..this.nbRows) {
            totalIncome += getCinemaSeatPrice(row) * this.nbSeats
        }
        return totalIncome
    }

    private fun getCinemaSeatPrice(chosenRow: Int): Int {
        if (this.nbRows * this.nbSeats < SEATS_MAX_LIMIT) return NORMAL_PRICE
        return if (chosenRow <= this.nbRows / 2) NORMAL_PRICE else LOW_PRICE
    }

    fun printCinema() {
        var cinemaString = "\nCinema:\n "

        for (i in 1..this.nbSeats) {
            cinemaString += " $i"
        }
        for (i in 1..this.nbRows) {
            cinemaString += "\n$i ${this.cinemaRoom[i - 1].joinToString(" ")}"
        }

        println(cinemaString)
    }
}