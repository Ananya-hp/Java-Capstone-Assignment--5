import java.util.*;

// 1. Book Class
class Book {
    String title;
    String author;
    boolean isAvailable;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true; // book is available initially
    }

    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Borrowed";
        return title + " by " + author + ". Status: " + status;
    }
}

// 2. Library Class
class Library {
    private Book[] books;
    private int count;

    Library(int size) {
        books = new Book[size];
        count = 0;
    }

    // Add a new book
    public void addBook(Book book) {
        if (count < books.length) {
            books[count++] = book;
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Library is full! Cannot add more books.");
        }
    }

    // Overloaded searchBook by title
    public Book searchBookByTitle(String title) {
        for (int i = 0; i < count; i++) {
            if (books[i].title.equalsIgnoreCase(title)) {
                return books[i];
            }
        }
        return null; // not found
    }

    // Overloaded searchBook by author
    public void searchBookByAuthor(String author) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (books[i].author.equalsIgnoreCase(author)) {
                System.out.println(books[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found by author: " + author);
        }
    }

    // Borrow a book
    public void borrowBook(String title) {
        Book book = searchBookByTitle(title);
        if (book != null) {
            if (book.isAvailable) {
                book.isAvailable = false;
                System.out.println("You have successfully borrowed: " + book.title);
            } else {
                System.out.println("Sorry, the book is already borrowed.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    // Return a book
    public void returnBook(String title) {
        Book book = searchBookByTitle(title);
        if (book != null) {
            if (!book.isAvailable) {
                book.isAvailable = true;
                System.out.println("You have successfully returned: " + book.title);
            } else {
                System.out.println("This book was not borrowed.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    // Display all books
    public void displayAllBooks() {
        if (count == 0) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("All books in the library:");
        for (int i = 0; i < count; i++) {
            System.out.println(books[i]);
        }
    }
}

// 3. Main Class - Menu-driven LibraryApp
public class LibraryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library(100); // Library can hold 100 books

        while (true) {
            System.out.println("\nWelcome to the Library Management System!");
            System.out.println("1. Add a new book");
            System.out.println("2. Search for a book by title");
            System.out.println("3. Search for books by author");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Display all books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author name: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(title, author));
                    break;
                case 2:
                    System.out.print("Enter book title: ");
                    String searchTitle = sc.nextLine();
                    Book foundBook = library.searchBookByTitle(searchTitle);
                    if (foundBook != null) {
                        System.out.println("Book found: " + foundBook);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter author name: ");
                    String searchAuthor = sc.nextLine();
                    library.searchBookByAuthor(searchAuthor);
                    break;
                case 4:
                    System.out.print("Enter book title to borrow: ");
                    String borrowTitle = sc.nextLine();
                    library.borrowBook(borrowTitle);
                    break;
                case 5:
                    System.out.print("Enter book title to return: ");
                    String returnTitle = sc.nextLine();
                    library.returnBook(returnTitle);
                    break;
                case 6:
                    library.displayAllBooks();
                    break;
                case 7:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
