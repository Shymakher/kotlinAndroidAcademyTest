import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.Month

class OrdersAnalyzer {
    data class Order(
        val orderId: Int,
        val creationDate: LocalDateTime,
        val orderLines: List<OrderLine>
    )

    data class OrderLine(
        val productId: Int,
        val name: String,
        val quantity: Int,
        val unitPrice: BigDecimal
    )

    fun totalDailySales(orders: List<Order>): Map<DayOfWeek, Int> {
        val listDailySales = mutableMapOf(
            DayOfWeek.MONDAY to 0,
            DayOfWeek.TUESDAY to 0,
            DayOfWeek.WEDNESDAY to 0,
            DayOfWeek.THURSDAY to 0,
            DayOfWeek.FRIDAY to 0,
            DayOfWeek.SATURDAY to 0,
            DayOfWeek.SUNDAY to 0
        )

        for (order in orders) {
            val day = LocalDateTime.parse(order.creationDate.toString()).dayOfWeek
            var quantity = 0
            for (lines in order.orderLines) {
                quantity += lines.quantity
            }
            listDailySales[day] = listDailySales.getOrDefault(day, 0) + quantity
        }
        return listDailySales
    }
}

fun main() {
    val ordersAnalyzer = OrdersAnalyzer()
    val orders = ordersGenerator()

    val result = ordersAnalyzer.totalDailySales(orders)
    println(result)
}

fun ordersGenerator(): List<OrdersAnalyzer.Order> {

    val orderLines1 = listOf(
        OrdersAnalyzer.OrderLine(9872, "Pencil", 3, BigDecimal(3.00))
    )
    val order1 = OrdersAnalyzer.Order(
        554, LocalDateTime.of(2017, Month.MARCH, 25, 10, 35, 20),
        orderLines1
    )

    val orderLines2 = listOf(
        OrdersAnalyzer.OrderLine(9872, "Pencil", 2, BigDecimal(3.00)),
        OrdersAnalyzer.OrderLine(1746, "Eraser", 1, BigDecimal(1.00))
    )
    val order2 = OrdersAnalyzer.Order(
        555, LocalDateTime.of(2017, Month.MARCH, 25, 11, 24, 20),
        orderLines2
    )

    val orderLines3 = listOf(
        OrdersAnalyzer.OrderLine(5723, "Pen", 4, BigDecimal(4.22)),
        OrdersAnalyzer.OrderLine(9872, "Pencil", 3, BigDecimal(3.12)),
        OrdersAnalyzer.OrderLine(3433, "Erasers Set", 1, BigDecimal(6.15))
    )
    val order3 = OrdersAnalyzer.Order(
        453, LocalDateTime.of(2017, Month.MARCH, 27, 14, 53, 12),
        orderLines3
    )

    val orderLines4 = listOf(
        OrdersAnalyzer.OrderLine(5723, "Pen", 7, BigDecimal(4.22)),
        OrdersAnalyzer.OrderLine(3433, "Erasers Set", 2, BigDecimal(6.15))
    )
    val order4 = OrdersAnalyzer.Order(
        431, LocalDateTime.of(2017, Month.MARCH, 20, 12, 15, 2),
        orderLines4
    )

    val orderLines5 = listOf(
        OrdersAnalyzer.OrderLine(9872, "Pencil", 4, BigDecimal(3.12)),
        OrdersAnalyzer.OrderLine(4098, "Marker", 5, BigDecimal(4.50))
    )
    val order5 = OrdersAnalyzer.Order(
        4690, LocalDateTime.of(2017, Month.MARCH, 26, 11, 14, 0),
        orderLines5
    )

    return listOf(order1, order2, order3, order4, order5)
}