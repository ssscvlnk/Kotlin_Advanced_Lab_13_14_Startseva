class Library<T> {
    val items = mutableListOf<T>()
}
data class Book(
    val title: String,
    val author: String,
    val year: Int,
    val isbn: String
)
data class Magazine(
    val title: String,
    val issue: Int,
    val month: String
)
data class DVD(
    val title: String,
    val director: String,
    val duration: Int // в минутах
)
sealed class LibraryItem {
    data class BookItem(val book: Book) : LibraryItem()
    data class MagazineItem(val magazine: Magazine) : LibraryItem()
    data class DVDItem(val dvd: DVD) : LibraryItem()
}
fun filterByYear(library: Library<LibraryItem>, year: Int): List<Book> {
    return library.items
        .filter { it is LibraryItem.BookItem }
        .map { (it as LibraryItem.BookItem).book }
        .filter { it.year == year }
}
fun sortByTitle(library: Library<LibraryItem>): List<LibraryItem> {
    return library.items.sortedBy { item ->
        when (item) {
            is LibraryItem.BookItem -> item.book.title
            is LibraryItem.MagazineItem -> item.magazine.title
            is LibraryItem.DVDItem -> item.dvd.title
            else -> ""
        }
    }
}
fun groupByAuthor(library: Library<LibraryItem>): Map<String, List<Book>> {
    return library.items
        .filter { it is LibraryItem.BookItem }
        .map { (it as LibraryItem.BookItem).book }
        .groupBy { it.author }
}
fun calculateTotalDuration(library: Library<LibraryItem>): Int {
    return library.items
        .filter { it is LibraryItem.DVDItem }
        .map { (it as LibraryItem.DVDItem).dvd.duration }
        .sum()
}
fun main() {
    val library = Library<LibraryItem>()
    library.items.add(LibraryItem.BookItem(Book(
        "Война и мир", "Л. Н. Толстой", 1869, "978-5-17-032405-8"
    )))
    library.items.add(LibraryItem.MagazineItem(Magazine(
        "Наука и жизнь", 5, "May"
    )))
    library.items.add(LibraryItem.DVDItem(DVD(
        "Интерстеллар", "Кристофер Нолан", 169
    )))
    library.items.add(LibraryItem.BookItem(Book(
        "Преступление и наказание", "Ф. М. Достоевский", 1866, "978-5-17-032406-5"
    )))
    library.items.add(LibraryItem.DVDItem(DVD(
        "Начало", "Кристофер Нолан", 148
    )))
    println("Книги 1869 года: ${filterByYear(library, 1869)}")
    println("Сортировка по названию: ${sortByTitle(library)}")
    println("Книги по авторам: ${groupByAuthor(library)}")
    println("Общая длительность DVD: ${calculateTotalDuration(library)} мин")
}