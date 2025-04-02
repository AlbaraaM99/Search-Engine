import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LibraryMenu {
    private Library library;
    private UserInteractionLogger logger = new UserInteractionLogger();
    private LibrarySerializer serializer = new LibrarySerializer();

    public LibraryMenu(Library library) {
        this.library = library;

        // Load the library data when the program starts
        List<Book> books = serializer.loadLibrary("src/resources/data/library.ser");
        if (books != null) {
            library.setBooks(books);
            System.out.println("Library loaded successfully.");
        } else {
            System.out.println("No saved library found. Loading default books.");
            library.loadBooks("src/resources/data/books.txt");
        }
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Digital Library Menu ===");
            System.out.println("1. View All Books");
            System.out.println("2. Search for a Book");
            System.out.println("3. Sort Books by Title");
            System.out.println("4. Sort Books by Author");
            System.out.println("5. Sort Books by Year");
            System.out.println("6. Save Library");
            System.out.println("7. Exit");
            System.out.print("Choose an option (1-7): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    library.viewAllBooks();
                    logger.log("Viewed all books.");
                    break;

                case "2":
                    System.out.print("Enter search term (title/author): ");
                    String query = scanner.nextLine();
                    Book result = library.searchBookByKeyword(query);
                    if (result == null) {
                        System.out.println("No books found.");
                    } else {
                        System.out.println(result);
                    }
                    logger.log("Searched for: " + query);
                    break;

                case "3":
                    SortUtil.bubbleSort(library.getBooks(), Comparator.comparing(Book::getTitle));
                    System.out.println("Books sorted by title.");
                    logger.log("Sorted books by title.");
                    break;

                case "4":
                    SortUtil.bubbleSort(library.getBooks(), Comparator.comparing(Book::getAuthor));
                    System.out.println("Books sorted by author.");
                    logger.log("Sorted books by author.");
                    break;

                case "5":
                    SortUtil.bubbleSort(library.getBooks(), Comparator.comparing(Book::getYear));
                    System.out.println("Books sorted by year.");
                    logger.log("Sorted books by year.");
                    break;

                case "6":
                    serializer.saveLibrary(library.getBooks(), "src/resources/data/library.ser");
                    System.out.println("Library saved.");
                    logger.log("Saved library.");
                    break;

                case "7":
                    System.out.println("Exiting... Goodbye!");
                    logger.log("Exited program.");
                    return;

                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 7.");
                    break;
            }
        }
    }
}
