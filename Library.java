import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();

    // Load books from books.txt file
    public void loadBooks(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Example line: The Great Gatsby,F. Scott Fitzgerald,1925
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    int year = Integer.parseInt(parts[2].trim());
                    books.add(new Book(title, author, year));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // View all books
    public void viewAllBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Simple keyword search in title or author
    public Book searchBookByKeyword(String keyword) {
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                return book;
            }
        }
        return null; // Not found
    }

    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }
}
