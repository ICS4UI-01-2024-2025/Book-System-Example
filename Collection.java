import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Collection {
    private String filename;
    private ArrayList<Book> books;

    public Collection(String filename){
        this.filename = filename;
        // scanner to read the file
        Scanner input = null;
        try{
            input = new 
                  Scanner(new File(filename));
        }catch(Exception e){
            e.printStackTrace();
        }

        // throw away the header
        input.nextLine();
        // start scanning books
        // keep going as long as something to scan
        while(input.hasNext()){
            // scan in the data line
            String dataline = input.nextLine();
            // break up data into each chunk
            String[] data = dataline.split(",");
            long isbn = Long.parseLong(data[0]);
            String author = data[1];
            String title = data[2];
            double rating = Double.parseDouble(data[3]);

            // make a book
            Book b = new Book(isbn, author, title, rating);
            // add it to the collection
            books.add(b);
        }
    }
}
