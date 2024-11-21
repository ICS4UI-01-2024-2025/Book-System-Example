public class Book {
    private long isbn;
    private String author;
    private String title;
    private double rating;

    public Book(long isbn, String author, 
            String title, double rating){
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.rating = rating;
    }

    public long getISBN(){
        return this.isbn;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getTitle(){
        return this.title;
    }

    public String toString(){
        String output = "";
        output += this.isbn + ",";
        output += this.author + ",";
        output += this.title + ",";
        output += this.rating;
        return output;
    }
}
