import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GraphicalInterface extends JFrame implements ActionListener {

    // stuff to make the collection work with the interface
    private Collection bookCollection;
    private boolean found;
    private Book selected;
    String screen;

    // GUI elements needed
    private JPanel mainPanel;
    private JPanel searchPanel;
    private JPanel addBook;
    private JPanel bookListResults;
    private JScrollPane searchScroll;

    private JLabel bookInfo;
    private JLabel inputLabel;
    private JTextField inputBox;
    private JButton submit;
    private JButton cancel;
    private JButton deselectBook;
    private JButton searchAuthor;
    private JButton searchTitle;
    private JButton searchISBN;
    private JButton add;
    private JButton remove;

    public GraphicalInterface(String filename) {
        this.bookCollection = new Collection(filename);
        this.selected = null;
        this.found = false;
        this.screen = "main";

        // GUI setup
        this.setTitle("Book Collection System");
        this.setSize(800, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create main screen
        this.mainPanel = new JPanel();
        this.mainPanel.setPreferredSize(new Dimension(800, 600));
        this.mainPanel.setLayout(new GridLayout(7, 1));

        this.bookInfo = new JLabel("Selected Book: NONE");

        this.deselectBook = new JButton("Deselect Book");
        this.deselectBook.addActionListener(this);
        this.deselectBook.setActionCommand("deselect");

        this.add = new JButton("Add Book");
        this.add.addActionListener(this);
        this.add.setActionCommand("add");

        this.remove = new JButton("Remove Book");
        this.remove.addActionListener(this);
        this.remove.setActionCommand("remove");

        this.searchAuthor = new JButton("Search By Author");
        this.searchAuthor.addActionListener(this);
        this.searchAuthor.setActionCommand("byAuthor");

        this.searchTitle = new JButton("Search By Title");
        this.searchTitle.addActionListener(this);
        this.searchTitle.setActionCommand("byTitle");

        this.searchISBN = new JButton("Search By ISBN");
        this.searchISBN.addActionListener(this);
        this.searchISBN.setActionCommand("byISBN");

        this.mainPanel.add(this.bookInfo);
        this.mainPanel.add(this.deselectBook);
        this.mainPanel.add(this.add);
        this.mainPanel.add(this.remove);
        this.mainPanel.add(this.searchAuthor);
        this.mainPanel.add(this.searchTitle);
        this.mainPanel.add(this.searchISBN);

        // create the top panel for info input and for the TitleLabel
        this.inputBox = new JTextField();
        this.inputBox.setSize(800, 25);
        this.submit = new JButton("Submit");
        this.submit.addActionListener(this);
        this.submit.setActionCommand("submit");
        this.cancel = new JButton("Cancel");
        this.cancel.addActionListener(this);
        this.cancel.setActionCommand("cancel");
        this.inputLabel = new JLabel();
        this.inputLabel.setSize(800, 25);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.LINE_AXIS));
        inputPanel.add(this.inputBox);
        inputPanel.add(this.submit);
        inputPanel.add(this.cancel);

        this.searchPanel = new JPanel(new BorderLayout());
        this.searchPanel.setPreferredSize(new Dimension(800, 550));
        this.bookListResults = new JPanel();
        this.bookListResults.setPreferredSize(new Dimension(800, 550));
        this.searchScroll = new JScrollPane(this.bookListResults, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.searchPanel.add(inputLabel, BorderLayout.PAGE_START);
        this.searchPanel.add(inputPanel, BorderLayout.PAGE_END);
        this.searchPanel.add(searchScroll, BorderLayout.CENTER);

        this.add(this.mainPanel);
        this.pack();

    }

    private void createBookList(ArrayList<Book> books) {
        this.bookListResults.removeAll();
        this.bookListResults.setLayout(new BoxLayout(this.bookListResults, BoxLayout.PAGE_AXIS));

        this.bookListResults.setPreferredSize(new Dimension(800, books.size() * 40 + 40));
        JPanel line = new JPanel();
        line.setLayout(new BoxLayout(line, BoxLayout.LINE_AXIS));
        line.setPreferredSize(new Dimension(800, 40));
        JLabel title = new JLabel("TTILE");
        title.setMaximumSize(new Dimension(500, 40));
        JLabel author = new JLabel("AUTHOR");
        author.setMaximumSize(new Dimension(150, 40));
        JLabel select = new JLabel("SELECT");
        select.setMaximumSize(new Dimension(150, 40));
        line.add(title);
        line.add(author);
        line.add(select);
        this.bookListResults.add(line);

        for (Book b : books) {
            line = new JPanel();
            line.setLayout(new BoxLayout(line, BoxLayout.LINE_AXIS));
            line.setPreferredSize(new Dimension(800, 40));
            JLabel bookName = new JLabel(b.getTitle());
            JLabel bookAuthor = new JLabel(b.getAuthor());
            bookName.setMaximumSize(new Dimension(500, 40));
            bookAuthor.setMaximumSize(new Dimension(150, 40));
            JButton selectBook = new JButton("SELECT");
            selectBook.setMaximumSize(new Dimension(150, 40));
            selectBook.addActionListener(this);
            selectBook.setActionCommand("" + b.getISBN());
            line.add(bookName);
            line.add(bookAuthor);
            line.add(selectBook);
            this.bookListResults.add(line);
        }
        this.searchScroll.setViewportView(this.bookListResults);
    }

    private void createBookList(Book book) {
        ArrayList<Book> books = new ArrayList<>();
        books.add(book);
        this.createBookList(books);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("deselect")) {
            this.bookInfo.setText("Selected Book: NONE");
            this.selected = null;
        } else if (command.equals("add")) {

        } else if (command.equals("remove")) {
            if (this.selected != null) {
                this.bookCollection.remove(this.selected.getISBN());
                this.bookInfo.setText("Selected Book: NONE");
                this.selected = null;
            }

        } else if (command.equals("byAuthor")) {
            this.bookListResults.removeAll();
            this.getContentPane().removeAll();
            this.inputLabel.setText("Search by Author");
            this.add(this.searchPanel);
            screen = "byAuthor";
        } else if (command.equals("byTitle")) {
            this.bookListResults.removeAll();
            this.getContentPane().removeAll();
            this.inputLabel.setText("Search by Title");
            this.add(this.searchPanel);
            screen = "byTitle";
        } else if (command.equals("byISBN")) {
            this.bookListResults.removeAll();
            this.getContentPane().removeAll();
            this.inputLabel.setText("Search by ISBN");
            this.add(this.searchPanel);
            screen = "byISBN";

        } else if (command.equals("cancel")) {
            this.getContentPane().removeAll();
            this.add(this.mainPanel);
            screen = "main";
        } else if (command.equals("submit")) {
            if (screen.equals("byISBN")) {
                long isbn = Long.parseLong(this.inputBox.getText());
                Book found = this.bookCollection.searchByISBN(isbn);
                this.createBookList(found);
            } else if (screen.equals("byAuthor")) {
                String author = this.inputBox.getText();
                ArrayList<Book> found = this.bookCollection.searchByAuthor(author);
                this.createBookList(found);
            } else if (screen.equals("byTitle")) {
                String title = this.inputBox.getText();
                ArrayList<Book> found = this.bookCollection.searchByTitle(title);
                this.createBookList(found);
            }
            this.inputBox.setText("");
        } else {
            long isbn = Long.parseLong(command);
            this.selected = this.bookCollection.searchByISBN(isbn);
            this.bookInfo.setText("Selected Book: " + this.selected.getTitle() + " by " + this.selected.getAuthor());

            screen = "main";
            this.getContentPane().removeAll();
            this.add(this.mainPanel);
        }

        this.pack();
        this.repaint();
    }

    public static void main(String[] args) {
        new GraphicalInterface("books.txt");
    }

}
