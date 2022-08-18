var currentBill: Int = 0
var totalIncome: Int = 0
var bookedSeatCount: Int = 0

fun printSeats(seating: MutableList<MutableList<Char>>) {
    val columns: Int = seating[1].size
    println("\nCinema:")
    print("  ")
    for (i in 1..columns) { print("$i ") }
    println()
    var str: String
    for (i in 0 until seating.size) {
        str = seating[i].joinToString(" ")
        val c = i + 1
        print("$c ")
        println(str)
    }
    println()
}
fun buyTicket(seating: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
    val rows: Int = seating.size
    val columns: Int = seating[1].size
    var brow: Int = 0
    var bcol: Int = 0
    var booked: Boolean = false

    // take input of desired seat
    do {
        println("Enter a row number:")
        brow = readln().toInt()
        println("Enter a seat number in that row:")
        bcol = readln().toInt()
        try {
            if (seating[brow-1][bcol-1] == 'B') {
                println("That ticket has already been purchased!")
            } else {
                // calculate seat price for the current booking
                val bill: Int = if (rows * columns <= 60) 10
                else if (rows * columns > 60 && brow <= rows / 2) 10
                else 8
                currentBill += bill    // update current income
                println("\nTicket price: $$bill")
                seating[brow - 1][bcol - 1] = 'B'
                bookedSeatCount++
                booked = true
            }
        } catch (e: Exception) {
            println("Wrong input!")
        }
    } while (brow > rows || bcol > columns || !booked)

    println("You want seat $brow, $bcol")
    println("Seating[$brow-1][$bcol-1] will be updated")
    println("Status before update is " + seating[brow-1][bcol-1])

    return seating
}

fun getMenuResponse(): Int {
    println("\n1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
    return readln().toInt()
}
fun statistics (seating: MutableList<MutableList<Char>>) {
    val rows: Int = seating.size
    val columns: Int = seating[0].size
    val totSeats: Int = rows * columns

    // print No of  booked seats
    println ("\nNumber of purchased tickets: $bookedSeatCount")

    // print % of booked seats
    var percent = bookedSeatCount.toDouble() / totSeats.toDouble() * 100
    val format_percent = "%.2f".format(percent)
    println ("Percentage: $format_percent%")

    // print other details
    println("Current income: $$currentBill")
    println("Total income: $$totalIncome")
}
fun main() {
    // get No of rows and columns
    println("Enter the number of rows:")
    val rows: Int = readln().toInt()
    println("Enter the number of seats in each row:")
    val columns: Int = readln().toInt()

    // Calculate total income
    //val bill: Int
    totalIncome =
        if ((rows * columns) <= 60) { 10 * rows * columns }
        else if ((rows * columns) > 60 && rows % 2 == 0) { 9 * rows * columns } 
        else { (10 * (rows/2) * columns) + (8 * (rows - rows/2) * columns) }

    //Populate seating array
    val seating: MutableList<MutableList<Char>>  = MutableList(rows) { MutableList(columns) { 'S' } }
    var userRequest: Int

    // getMenuResponse
        do {
            userRequest = getMenuResponse()
            when (userRequest) {
                    1 -> printSeats(seating)
                    2 -> buyTicket(seating)
                    3 -> statistics(seating)
                    else -> {}
            }
        } while (userRequest != 0)
}
