import java.util.*;

public class Books
{
	// Data Attributes
	
	private int bookID;
	private int availQuan;
	private int issueQuan;
	private String bookName;
	private String bookAuthor;
    public int index = -1;
	
	private static ArrayList<Books> bookList = new ArrayList<>();
	
	// Methods

    public Books()
    {
    }

    public Books(int ID, int avail, int issue, String name, String author)
    {
        bookID = ID;
        availQuan = avail;
        issueQuan = issue;
        bookName = name;
        bookAuthor = author;
    }

    public int getBookID()
    {
        return bookID;
    }

    public int getAvail()
    {
        return availQuan;
    }

    public int getIssue()
    {
        return issueQuan;
    }

    public String getName()
    {
        return bookName;
    }

    public String getAuthor()
    {
        return bookAuthor;
    }

    public String toString()
    {
        return ("Book ID: " + bookID + "\tBook name: " + bookName + "\tBook Author: " + bookAuthor + "\tAvailable Quantity: " + availQuan + "\tIssued Quantity: " + issueQuan);
    }

    public void addBook(int ID, int avail, int issue, String name, String author)
    {
        Books newBook = new Books(ID, avail, issue, name, author);
        bookList.add(newBook);
        index++;
    }

    public void removeBook(int ID)
    {
        int found = 0;

        for (int i = 0; i<=index; i++)
        {
            if (bookList.get(i).bookID == ID)
            {
                bookList.remove(i);
                index--;
                found = 1;
            }

            else
            {
                continue;
            }
        }

        if (found == 0)
        {
            System.out.println("The book with the entered ID cannot be found     ->     Please make sure the ID is correct and try again.");
        }
    }

    public void issueBook(int ID)
    {
        int found = 0;

        for (int i = 0; i<=index; i++)
        {
            if (bookList.get(i).bookID == ID)
            {
                found = 1;
                bookList.get(i).issueQuan = (bookList.get(i).issueQuan) + 1;
                bookList.get(i).availQuan = (bookList.get(i).availQuan) - 1;
                break;
            }

            else
            {
                continue;
            }
        }

        if (found == 0)
        {
            System.out.println("The book with the entered ID cannot be found     ->     Please make sure the ID is correct and try again.");
        }
    }

    public void returnBook(int ID)
    {
        int found = 0;

        for (int i = 0; i<=index; i++)
        {
            if (bookList.get(i).bookID == ID)
            {
                found = 1;
                bookList.get(i).issueQuan = (bookList.get(i).issueQuan) - 1;
                bookList.get(i).availQuan = (bookList.get(i).availQuan) + 1;
                break;
            }

            else
            {
                continue;
            }
        }

        if (found == 0)
        {
            System.out.println("The book with the entered ID cannot be found     ->     Please make sure the ID is correct and try again.");
        }
    }

    public void increaseQuan(int ID, int extraQuan)
    {
        int found = 0;

        for (int i = 0; i<=index; i++)
        {
            if (bookList.get(i).bookID == ID)
            {
                found = 1;
                bookList.get(i).availQuan = (bookList.get(i).availQuan) + extraQuan;
                break;
            }
            else
            {
                continue;
            }
        }

        if (found == 0)
        {
            System.out.println("The book with the entered ID cannot be found     ->     Please make sure the ID is correct and try again.");
        }
    }

    public void getDetails(int ID)
    {
        int found = 0;

        for (int i = 0; i<=index; i++)
        {
            if (bookList.get(i).bookID == ID)
            {
                System.out.println(bookList.get(i).toString());
                found = 1;
                break;
            }
            else
            {
                continue;
            }
        }

        if (found == 0)
        {
            System.out.println("The book with the entered ID cannot be found     ->     Please make sure the ID is correct and try again.");
        }
    }

    public static void main(String[] args)
    {
         Books books = new Books();

        books.addBook(2987, 23, 7, "The Cruel Prince", "Unknown bardo");
        books.addBook(546, 23, 7, "The Wicked King", "To be Found.Mclain");

        System.out.println(Books.bookList.get(0).toString());
        System.out.println(Books.bookList.get(1).toString());

        books.removeBook(2987);

        books.issueBook(546);
        System.out.println(Books.bookList.get(0).toString());

        books.returnBook(546);
        System.out.println(Books.bookList.get(0).toString());

        books.increaseQuan(546, 34);
        System.out.println(Books.bookList.get(0).toString());

        books.getDetails(546);
    }
}
