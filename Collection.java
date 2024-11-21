import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class Collection {
    private String filename;
    private ArrayList<Book> books;

    public Collection(String filename){
        // initialize the array list
        this.books = new ArrayList<>();

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

    public String toString(){
        String output = "";
        // go through each book
        for(Book b : this.books){
            // can use the toString on Book
            output += b + "\n";
        }
        return output;
    }

    public Book searchByISBN(long isbn){
        for(Book b : this.books){
            // if the isbn matches
            if(b.getISBN() == isbn){
                // found the book
                return b;
            }
        }
        // didn't find it after all the books
        return null;
    }

    public ArrayList<Book> searchByAuthor(String name){
        // initialize an arraylist to store results
        ArrayList<Book> found = new ArrayList<>();
        // go through each book
        for(Book b : this.books){
            // see if the name matches
            if(b.getAuthor().equals(name)){
                // add it to the list of found items
                found.add(b);
            }
        }
        // match the other search, if nothing found -> null
        if(found.isEmpty()){
            return null;
        }else{
            return found;
        }
    }

    public void add(long isbn, String author, String title, double rating){
        Book b = new Book(isbn, author, title, rating);
        this.books.add(b);
        updateFile();
    }

    public void remove(long isbn){
        // find the book using the method
        Book b = this.searchByISBN(isbn);
        // remove the book from the list
        this.books.remove(b);
        // update our file
        this.updateFile();
    }

    private void updateFile(){
        // create a PrintWriter from the file
        PrintWriter output = null;
        try{
            output =  new PrintWriter(this.filename);
        }catch(Exception e){
            e.printStackTrace();
        }
        // rewrite the entire collection file
        // start with the header
        output.println("isbn13,authors,title,average_rating");
        // print out all the books
        for(Book b:this.books){
            output.println(b);
        }
        // close the PrintWriter to finish changes
        output.close();
    }
}
