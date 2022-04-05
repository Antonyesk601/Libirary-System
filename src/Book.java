import java.util.*;

public class Book
{
	// Data Attributes
	
	private int bookID;
	private int availQuan;
	private int issueQuan;
	private String bookName;
	private String bookAuthor;
    public static int index = -1; //The index variable is used to keep track of how many books are in the list. 
                                  //It helps keep track of the elements in the list to help search, or iterate over the list.
	
	private static ArrayList<Book> bookList = new ArrayList<>(); // ArrayList that contains Book objects
	public static ArrayList<Book> getBookList()
    {
        if(bookList==null)
            return null;
        return bookList; 
    }
	// Methods

    public Book()
    {
    }

    public Book(int ID, int avail, int issue, String name, String author)
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

    /*
        Add method takes the book's information, creates an object using the parameterized constructor. 
        The Book object was then added to the Books array.
    */
    public static void addBook(int ID, int avail, int issue, String name, String author)
    {
        Book newBook = new Book(ID, avail, issue, name, author);
        bookList.add(newBook);
        index++;
    }

    /*
        Remove method receives the book ID, searches the bookList for the book with the same ID, then removes it using the built in remove method
    */
    public static void removeBook(int ID)
    {
        int found = 0;

        for (int i = 0; i<=index; i++)
        {
            if (bookList.get(i).bookID == ID)
            {
                bookList.remove(i);
                index--;
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

    /*
        Issuing a book means one book has been borrowed, so the issueBook method takes the ID of the book to be issued, 
        searches the bookList for the required book, then increases the issued quantity by one, and reduces the available quantity by one.
    */ 
    public static void issueBook(int ID)
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

    /*
        Return a book means an issued book has been returned, so the returnBook method takes the ID of the book to be returned, 
        searches the bookList for the required book, then increases the available quantity by one, and reduces the issued quantity by one.
    */
    public static void returnBook(int ID)
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

    /*
        In the increaseQuan method, it increases the number of the available quantity by the given extraQuan.
        This method can be called if a new shipment of books arrived, for example.
    */
    public static void  increaseQuan(int ID, int extraQuan)
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

    /*
        This method takes the ID of the desired book, searches for it in the bookList using the for loop. 
        If the book is found, the details of the book is printed, if not, the user is informed that the book has not been found.
    */
    public static void getDetails(int ID)
    {
        int found = 0;  // flag to know whether the book was found or not, if not, it is used to print the string in line 213.

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

    /* 
        This method takes a string and prints any book name that contains the string entered as well as adds these books to a list.
    */
    public static void searchBook(String bookN)
    {
        ArrayList<Book> commonName = new ArrayList<>();
        int found = 0;

        for (int i = 0; i<=index; i++)
        {
            if (bookList.get(i).bookName.contains(bookN))
            {
                found = 1;
                commonName.add(bookList.get(i));
                System.out.println(bookList.get(i).bookName);
            }
            else
            {
                continue;
            }
        }

        if(found == 0)
        {
            System.out.println("No book contains that name, please check the title and try again later.");
        }
    }

    public static Book getBook(int IDbook)
    {
        for (int i = 0; i<=index; i++)
        {
            if (bookList.get(i).bookID == IDbook)
            {
                return bookList.get(i);
            }

            else
            {
                continue;
            }
        }

        return null;
    }

    /* 
        Prints the books in the list
    */
    public static void displayList()
    {
        for (int i=0; i<=index; i++)
        {
            System.out.println(bookList.get(i).toString());
        }
    }

    public static void main(String[] args)
    {
        Book.addBook(2987, 23, 7, "The Cruel Prince", "Unknown bardo");
        Book.addBook(546, 23, 7, "The Wicked King", "To be Found.Mclain");

        Book.issueBook(546);

        Book.returnBook(546);

        Book.increaseQuan(546, 34);

        Book.getDetails(546);

        Book.displayList();

        Book.searchBook("The");
    }
}
