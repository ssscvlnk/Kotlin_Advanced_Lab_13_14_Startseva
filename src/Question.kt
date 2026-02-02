//class FillInTheBlankQuestion(
//    val questionText: String,
//    val answer: String,
//    val difficulty: String
//)
//class TrueOrFalseQuestion(
//    val questionText: String,
//    val answer: Boolean,
//    val difficulty: String
//)
//class NumericQuestion(
//    val questionText: String,
//    val answer: Int,
//    val difficulty: String
//)
class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: String
)

fun main() {
    val question1 = Question<String>(
        "Речка спятила с ума — По домам пошла сама. ___",
        "водопровод",
        "medium"
    )
    val question2 = Question<Boolean>(
        "Небо зелёное. Правда или ложь",
        false,
        "easy"
    )
    val question3 = Question<Int>(
        "Сколько дней между полнолуниями?",
        28,
        "hard"
    )
    println(question1.answer)
    println(question2.answer)
    println(question3.answer)
}
