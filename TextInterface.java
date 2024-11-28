import java.util.ArrayList;
import java.util.Scanner;

public class TextInterface {

    public static Scanner input;
    public static String screen;
    public static Collection bookCollection;
    private static boolean running;

    public static void main(String[] args) {
        input = new Scanner(System.in);
        screen = "main";
        running = true;

        bookCollection = new Collection("books.txt");

        while(running){
            if(screen.equals("main")){
                mainScreen();
            }else if(screen.equals("add")){
                addBook();
            }else if(screen.equals("remove")){
                removeBook();
            }else if(screen.equals("byAuthor")){
                searchByAuthor();
            }else if(screen.equals("byTitle")){
                searchByTitle();
            }else if(screen.equals("byISBN")){
                searchByISBN();
            }
            System.out.println();
        }
    }

    public static void mainScreen(){
        System.out.println("What would you like to do?");
        System.out.println("1 - Add a Book");
        System.out.println("2 - Remove a Book");
        System.out.println("3 - Search by ISBN");
        System.out.println("4 - Search by Author");
        System.out.println("5 - Search by Title");
        System.out.println("6 - Quit");
        int choice = input.nextInt();
        input.nextLine();

        if(choice == 1){
            screen = "add";
        }else if(choice == 2){
            screen = "remove";
        }else if(choice == 3){
            screen = "byISBN";
        }else if(choice == 4){
            screen = "byAuthor";
        }else if(choice == 5){
            screen = "byTitle";
        }else{
            running = false;
        }

    }

    public static void addBook(){
        System.out.println("Adding a new book");
        System.out.println("What is the ISBN?");
        long isbn = input.nextLong();
        input.nextLine();
        System.out.println("Who is the Author?");
        String author = input.nextLine();
        System.out.println("What is the title?");
        String title = input.nextLine();
        System.out.println("What is the Rating?");
        double rating = input.nextDouble();
        input.nextLine();

        bookCollection.add(isbn, author, title, rating);
        System.out.println("Book added to the collection");
        screen = "main";
    }

    public static void removeBook(){
        System.out.println("Removing a book");
        System.out.println("What is the ISBN?");
        long isbn = input.nextLong();
        input.nextLine();
        bookCollection.remove(isbn);
        System.out.println("Book removed");
        screen = "main";
    }

    public static void searchByISBN(){
        System.out.println("Searching by ISBN");
        System.out.println("What is the ISBN?");
        long isbn = input.nextLong();
        input.nextLine();
        Book b = bookCollection.searchByISBN(isbn);
        if(b != null){
            System.out.println(b);
        }else{
            System.out.println("Book not found!");
        }
        screen = "main";
    }

    public static void searchByAuthor(){
        System.out.println("Searching by Author");
        System.out.println("Who is the author?");
        String author = input.nextLine();
        ArrayList<Book> books = bookCollection.searchByAuthor(author);
        if(books != null){
            for(Book b: books){
                System.out.println(b);
            }
        }else{
            System.out.println("No books found!");
        }
        screen = "main";
    }

    public static void searchByTitle(){
        System.out.println("Searching by Title");
        System.out.println("What is part of the title?");
        String title = input.nextLine();
        ArrayList<Book> books = bookCollection.searchByTitle(title);
        if(books != null){
            for(Book b: books){
                System.out.println(b);
            }
        }else{
            System.out.println("No books found!");
        }
        screen = "main";
    }
}
