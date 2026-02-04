# Лабораторная работа №13-14
Коллекции, обобщения и функциональный стиль в Kotlin
## Описание
Данная лабораторная работа посвящена изучению продвинутых возможностей языка Kotlin,
которые активно используются при разработке Android-приложений.
В рамках работы рассматриваются:
- обобщённые типы (Generics);
- коллекции Kotlin;
- функции высшего порядка;
- extension-функции;
Все примеры ориентированы на практическое применение и подготовку к разработке
мобильных приложений.
### Обобщённые классы (Generics)
**Generics** позволяют:
- описать класс один раз;
- подставлять нужный тип данных при создании объекта;
- избежать дублирования кода;
- повысить читаемость и безопасность типов.
```
class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)
```
### Enum class
**Enum class** применяется, когда:
- существует ограниченный набор возможных значений;
- использование любых других значений недопустимо;
- требуется строгий контроль на уровне компилятора.
```
enum class Difficulty {
    EASY,
    MEDIUM,
    HARD
}
```
### Data class
**Data class** — это класс, предназначенный для хранения данных.
Если класс объявлен как data class, компилятор Kotlin автоматически генерирует
для него следующие методы:
- equals()
- hashCode() (важен при работе с коллекциями)
- toString()
- componentN() (для деструктуризации)
- copy()
Метод toString() используется, например, при вызове println().
```
data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)
```
### Singleton-объект (object)
**Singleton** — это класс, у которого может существовать только один экземпляр.
В Kotlin для этого используется специальная конструкция object.
```
object StudentProgress {
    var total: Int = 10
    var answered: Int = 3
}
```
### Companion object
В Kotlin объекты можно объявлять внутри классов.
Если такой объект помечен как companion, его свойства доступны напрямую
через имя класса.
```
class Quiz {
    val question1 = Question<String>(
        "Речка спятила с ума — По домам пошла сама. ___",
        "водопровод",
        Difficulty.MEDIUM
    )
    val question2 = Question<Boolean>(
        "Небо зелёное. Правда или ложь",
        false,
        Difficulty.EASY
    )
    val question3 = Question<Int>(
        "Сколько дней между полнолуниями?",
        28,
        Difficulty.HARD
    )
    println(question1.answer)
    println(question2.answer)
    println(question3.answer)
    println(question1.toString())
    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 10
    }
}
```
### Extensions
**Extensions** позволяют:
- добавлять новые свойства и функции к уже существующим типам;
- использовать их через точечную нотацию (.), как будто они были частью класса;
- не изменять исходный код класса.
Важно:
- расширения не изменяют сам класс,
- они лишь добавляют удобный синтаксис поверх него.
#### Extension properties
```
val Quiz.StudentProgress.progressText: String
    get() = "$answered of $total answered."
```
#### Extension functions
```
fun Quiz.StudentProgress.printProgressBar() {
    repeat(Quiz.answered) {print("▓")}
    repeat(Quiz.total - Quiz.answered) {print("▒")}
    println()
    println(Quiz.progressText)
}
```
### Scope-functions
**Scope-функции** — это функции высшего порядка, которые:
- принимают лямбда-выражение;
- временно меняют контекст (scope) внутри этого блока;
- позволяют обращаться к объекту через it или this.
#### Устранение повторений с помощью let()
```
fun printQuiz() {
        question1.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        question2.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
}
```
#### Вызов методов без переменной с помощью apply()
```
Quiz().apply { printQuiz() }
```
### Arrays (массивы)
**Массив (Array)** — это простейший способ хранить несколько значений одного
типа данных.
- массив состоит из элементов (items);
- элементы упорядочены;
- доступ к элементам осуществляется по индексу.
```
val rockPlanets = arrayOf<String>("Mercury",
    "Venus", "Earth", "Mars")
```
### Lists (Списки)
**List** — это упорядоченная коллекция с изменяемым размером. Чаще всего она реализована поверх массива, который автоматически увеличивается при
необходимости.
#### List и MutableList
В Kotlin коллекции описываются через интерфейсы.
- List — интерфейс для только чтения упорядоченной коллекции.
- MutableList — расширяет List и добавляет методы для изменения списка (add,
remove и т.д.).
Как именно список хранит данные — решает конкретная реализация. В большинстве случаев используется массивная реализация.
**Функция listOf()** создаёт список только для чтения.
```
val solarSystem = mutableListOf(
        "Mercury", "Venus", "Earth", "Mars",
        "Jupiter", "Saturn", "Uranus", "Neptune"
    )
```
Чтобы изменять список, нужно использовать **mutableListOf()**:
```
val solarSystem = mutableListOf(
        "Mercury", "Venus", "Earth", "Mars",
        "Jupiter", "Saturn", "Uranus", "Neptune"
    )
```
### Sets (Множества)
**Set** — это коллекция без фиксированного порядка, которая не допускает
дубликатов.
- элементы уникальны;
- порядок элементов не гарантирован;
- нет индексов.
```
 val solarSystem = mutableSetOf(
        "Mercury", "Venus", "Earth", "Mars",
        "Jupiter", "Saturn", "Uranus", "Neptune"
    )
```
### Map collection (Коллекция Map)
**Map** — это коллекция, состоящая из ключей и значений.
Она называется «картой», потому что каждому уникальному ключу сопоставляется значение.
Пара ключ–значение обычно называется key-value pair.
- Ключи уникальны
- Значения могут повторяться
```
val solarSystem = mutableMapOf(
        "Mercury" to 0, "Venus" to 0, "Earth" to 1, "Mars" to 2,
        "Jupiter" to 79, "Saturn" to 82, "Uranus" to 27, "Neptune" to 14
    )
```
### Higher-order functions (Функции высокого порядка)
**Функции высокого порядка (high order function)** - это функции, которые либо принимают функцию в качестве параметра, либо возвращают функцию, либо и то, и другое.
```
fun main() {
    displayMessage(::morning)
    displayMessage(::evening)
}
fun displayMessage(mes: () -> Unit) {
    mes()
}
fun morning() {
    println("Good Morning")
}
fun evening() {
    println("Good Evening")
}
```
### Higher-order functions с коллекциями
Для коллекций функции высокого порядка особенно важны, потому что они позволяют выполнять типовые операции — перебор, преобразование, фильтрацию, группировку, сортировку — с минимальным количеством кода.
#### forEach()
Функция forEach() выполняет переданную в неё функцию для каждого элемента коллекции.
```
 cookies.forEach {
        println("Пункт меню: ${it.name}")
    }
 ```
#### map()
Функция map() позволяет трансформировать элементы коллекции в новую коллекцию того же размера.
```
val fullMenu = cookies.map {
        "${it.name} - $${it.price}"
   }
```
#### filter()
Функция filter() создаёт подмножество коллекции, оставляя только элементы, которые соответствуют условию.
В отличие от map(), результат может быть меньше исходного размера. Тип элементов остаётся тем же, что и в оригинальной коллекции.
```
val softBakedMenu = cookies.filter {
        it.softBaked
    }
```
#### groupBy()
Функция groupBy() позволяет превратить список в Map, где:
- ключ — результат функции, переданной в groupBy
- значение — список элементов, для которых функция вернула этот ключ
```
val groupedMenu = cookies.groupBy { it.softBaked }
val softBakedMenu = groupedMenu[true] ?: emptyList()
val crunchyMenu = groupedMenu[false] ?: emptyList()
```
#### fold()
Функция fold() аккумулирует значения коллекции в одно значение. Полезно для суммирования, подсчёта общего количества и т.п.
```
 val totalPrice = cookies.fold(0.0) { total, cookie ->
        total + cookie.price
    }
 ```
#### sortedBy()
Функция sortedBy() сортирует коллекцию по значению свойства, указанного в лямбде.
```
val alphabeticalMenu = cookies.sortedBy { it.name }
```
## Как запустить проект
1. Клонируйте репозиторий:
```bash
git clone <URL_репозитория>
```
## Автор
Старцева Полина Сергеевна
## Лицензия
Проект создан в учебных целях.