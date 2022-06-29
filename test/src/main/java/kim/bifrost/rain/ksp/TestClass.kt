package kim.bifrost.rain.ksp

@IntSummable
data class TestClass(
    val num1: Int,
    val num2: Int,
    val num3: Int
)

fun main() {
    val testClass = TestClass(1, 2, 3)
    testClass.sumInts()
}