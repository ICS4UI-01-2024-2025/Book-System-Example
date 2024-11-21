import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Collection books = new Collection("books.txt");
        //System.out.println(books);

        Scanner input = new Scanner(System.in);
        System.out.println("Name an author");
        String name = input.nextLine();
        ArrayList<Book> found = books.searchByAuthor(name);
        int i = 0;
        for(Book b: found){
            System.out.println(i + " : " + b);
            i++;
        }
 
        System.out.println("What number to remove?");
        int index = input.nextInt();
        books.remove(found.get(index).getISBN());
        
    }
}