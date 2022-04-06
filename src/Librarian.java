import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
public class Librarian 
{
    /**
     * HashMap of the Librarians Which is Indexed By their IDs
     */
    private static HashMap<Integer, Librarian> s_Librarians;
    /**
     * HashSet of the LoginInfo of the librarians
     */
    private static HashSet<String> s_LoginInfo = new HashSet<String>();
    /**
     * Static Instance to track which librarian is currently Logged in
     */
    private static Librarian currentlyLoggedIn;
    /**
     * Main View of the librarian class which serves as a command line interface to manage the library
     * @param inputHandler Scanner Object to handle where the input is passed from
     */
    public static void LibrarianView(Scanner inputHandler)
    {
        String loginName,loginPassword,retry;
        System.out.print("Enter Your name:\n");
        loginName = inputHandler.nextLine();
        System.out.print("Enter your Password:\n");
        loginPassword = inputHandler.nextLine();
        // Keeps trying to login as long as the Login info is incorrect or terminates if the user asks to 
        while(!Librarian.Login(loginName+loginPassword))
        {
            System.out.println("Incorrect login info, do you want to re enter you data\n enter Y to retry anything else to exit");
            retry= inputHandler.nextLine();
            if(retry.toCharArray()[0]=='y'||retry.toCharArray()[0]=='Y')
            {
                System.out.print("Enter Your name:\n");
                loginName = inputHandler.nextLine();
                System.out.print("Enter your Password:\n");
                loginPassword = inputHandler.nextLine();
            }
            else
                return;
        }
        int choice = 0;
        System.out.println("Welcome, " + loginName+ "\n please choose an action by entering the number next to the chosen action");
        while(choice!=9)
        {
            System.out.println("1. Add books\n2. Delete books\n3. Issue Books\n4. Return Issued Books\n5. View All Books\n6. View Currently Issued Books\n7. Find a book by name\n8. Find a book by ID\n9. Log out");
            choice = Integer.parseInt(inputHandler.nextLine());
            switch(choice)
            {
                case 1:
                    {
                        // Takes input from the user about the book details and then tries to add it
                        // if the process is successful the BookClass should have added it to the static list and should return a
                        // non null instance when the getBook Method is called
                        System.out.print("Please enter the Book ID, Book Name, Author name, Available Quantity, Issued Quantity separated by lines\n");
                        int o_ID = Integer.parseInt(inputHandler.nextLine());
                        String o_BookName = inputHandler.nextLine();
                        String o_BookAuthor = inputHandler.nextLine();
                        int o_AvailQuan = Integer.parseInt(inputHandler.nextLine());
                        int o_IssuedQuan = Integer.parseInt(inputHandler.nextLine());
                        Book o_Book = new Book(o_ID,o_AvailQuan,o_IssuedQuan,o_BookName,o_BookAuthor);
                        currentlyLoggedIn.AddBook(o_Book);
                        if(Book.getBook(o_ID)!=null)
                            System.out.print("Book Added Successfully\n");
                        else
                            System.out.print("Book couldn't be added\n");
                    }
                    break;
                case 2:
                    {
                        // Takes input from the user that consists of the ID of the book to be deleted and checks 
                        // if it still exists in the BookClass static List by calling getBook method and expecting null in case of successful deletion 
                        System.out.print("Please enter the ID of the book to be deleted\n");
                        int o_ID = Integer.parseInt(inputHandler.nextLine());
                        if(Book.getBook(o_ID)==null)
                        {
                            System.out.println("Book not Found");
                            break;
                        }
                        currentlyLoggedIn.DeleteBook(o_ID);
                        if(Book.getBook(o_ID)==null)
                            System.out.print("Book Deleted Successfully\n");
                        else
                            System.out.print("Book couldn't be deleted\n");
                    }
                    break;
                case 3:
                    {
                        // Takes input from the user that consists of the book id and student id and tries to issue the book
                        // if the book is issued successfully the issue count should be increased by 1
                        System.out.print("Please enter the ID of the book to be issued and the Student ID\n");
                        int o_Book_ID = Integer.parseInt(inputHandler.nextLine());
                        int o_Student_ID = Integer.parseInt(inputHandler.nextLine());
                        int issueCount = IssueLogger.GetIssues(o_Book_ID, o_Student_ID).size();
                        currentlyLoggedIn.IssueBook(o_Book_ID, o_Student_ID);
                        if(IssueLogger.GetIssues(o_Book_ID, o_Student_ID).size()==issueCount+1)
                            System.out.print("Book Issued Successfully\n");
                        else
                            System.out.print("Book Couldn't be Issued Successfully\n");                    
                    }
                    break;
                case 4:
                    {
                        // Takes input from the user that consists of the book id and student id and tries to return the book
                        // if the book is returned successfully the closed issues count should be increased by 1
                        System.out.print("Please enter the ID of the book to be issued and the Student ID\n");
                        int o_Book_ID = Integer.parseInt(inputHandler.nextLine());
                        int o_Student_ID = Integer.parseInt(inputHandler.nextLine());
                        int closedIssuesCount = IssueLogger.GetClosedIssues(o_Book_ID, o_Student_ID).size();
                        currentlyLoggedIn.ReturnBook(o_Book_ID, o_Student_ID);
                        if(closedIssuesCount+1==IssueLogger.GetClosedIssues(o_Book_ID, o_Student_ID).size())
                            System.out.print("Book Returned Successfully\n");                    
                        else
                            System.out.print("Book Couldn't be Returned Successfully\n");                    
                    }
                    break;
                case 5:
                    {
                        Book.displayList();
                    }
                    break;
                case 6:
                    {
                        
                        Librarian.ShowIssuedBooks();
                    }
                    break;
                case 7:
                    {
                        // Finds all books that contain the substring entered by the user
                        System.out.print("Please Enter the name of the book\n");
                        String o_BookName = inputHandler.nextLine();
                        Book.searchBook(o_BookName);

                    }
                    break;
                case 8:
                    {    
                        // Finds the book instance that has the ID entered by the user
                        System.out.print("Enter the ID of the book you want to find\n");
                        int o_Book_ID = Integer.parseInt(inputHandler.nextLine());
                        if(Book.getBook(o_Book_ID)!=null)    
                            System.out.println(Book.getBook(o_Book_ID).toString());
                        else
                            System.out.println("Book Couldn't be found");    
                    }
                    break;
                case 9:
                    {
                        // removes the currently logged in Librarian and exits the view
                        currentlyLoggedIn = null;
                        System.out.print("Logged Out\nExiting Librarian View\n");                    
                    }
                    break;
                default:
                    {
                        System.out.print("Invalid Choice\n");                    
                    }
                    break;    
            
            }
        }

    }
    /**
     * 
     * @return the static hashmap of the librarians
     */
    public static HashMap<Integer,Librarian> getLibraryHashMap()
    {
        return s_Librarians;
    }
    /**
     * 
     * @param o_LoginInfo the login token that is checked for in the LoginInfo HashSet
     * @return if the login process was successful or not
     */
    public static Boolean Login(String o_LoginInfo)
    {
        if(s_LoginInfo==null)
        {
        s_LoginInfo = new HashSet<String>();
        }
        if(s_LoginInfo.contains(o_LoginInfo))
        {
            // Assigns the librarian that has the login token as the currently logged in librarian 
            currentlyLoggedIn = Librarian.getLibrarian(o_LoginInfo);
            return true;
        }
        return false;
    } 
    /**
     * Creates a new instance of the librarian class and adds it to the HashMap
     * @param o_ID ID of the new Librarian
     * @param o_Name Name of the new Librarian
     * @param o_Password Password of the new Librarian
     */
    public static void addLibrarian(int o_ID,String o_Name, String o_Password)
    {
        if(s_Librarians==null)
            s_Librarians = new HashMap<Integer,Librarian>();
        // Checks for duplicate IDs
        if(s_Librarians.get(o_ID)!=null)
        {
            System.out.println("Duplicate ID, Cant have Multiple Librarians with the same ID\nCouldn't Create Librarian Instance");
            return;
        }
        // Checks if the name contains non latin Alphabet
        for(char l:o_Name.toCharArray())
        {
            if(!Character.isAlphabetic(l)&&!Character.isWhitespace(l))
            {
                System.out.println("Name Invalid, Cant have non alphabetical characters in name\nCouldn't Create Librarian Instance");
                return;
            }
        }
        s_Librarians.put(o_ID,new Librarian(o_ID, o_Name,o_Password));   
        s_LoginInfo.add(o_Name+o_Password);
    }
    /**
     * Adds a librarian object to the Hash Map
     * @param o_Librarian Librarian Object
     */
    public static void addLibrarian(Librarian o_Librarian)
    {
        if(s_Librarians==null)
            s_Librarians = new HashMap<Integer,Librarian>();
        if(s_Librarians.get(o_Librarian.getID())!=null)
        {
            System.out.println("Duplicate ID, Cant have Multiple students with the same ID\nCouldn't Create Student Instance");
            return;
        }
        // Checks if the name is in Latin Alphabet
        for(char l:o_Librarian.getName().toCharArray())
        {
            if(!Character.isAlphabetic(l)&&!Character.isWhitespace(l))
            {
                System.out.println("Name Invalid, Cant have non alphabetical characters in name\nCouldn't Create Student Instance");
                return;
            }
        }
        s_Librarians.put(o_Librarian.getID(),o_Librarian);   
        s_LoginInfo.add(o_Librarian.getName()+o_Librarian.getPassword());
    }
    /**
     * 
     * @param o_LoginInfo LoginToken (Usually Name+Password)
     * @return Librarian Instance that has the same LoginToken
     */
    public static Librarian getLibrarian(String o_LoginInfo)
    {
        for(Librarian l:s_Librarians.values())
        {
            if((l.getName()+l.getPassword()).equals(o_LoginInfo))
                return l;
        }
        return null;
    }
    /**
     * 
     * @param o_ID ID of the Librarian
     * @return Librarian Instance that has the same ID
     */
    public static Librarian getLibrarian(int o_ID)
    {
        if(s_Librarians==null)
            s_Librarians = new HashMap<Integer,Librarian>();
        return s_Librarians.get(o_ID);
    }
    /**
     * Removes the Librarian of matching ID
     * @param o_ID ID of the Librarian to be deleted
     */
    public static void deleteLibrarian(int o_ID)
    {
        if(s_Librarians==null)
            s_Librarians = new HashMap<Integer,Librarian>();
        
        Librarian toBeRemoved = Librarian.getLibrarian(o_ID);
        s_LoginInfo.remove(toBeRemoved.getName()+toBeRemoved.getPassword());
        s_Librarians.remove(o_ID);
    }
    public Librarian(int o_ID, String o_Name, String o_Password) {
        this.m_ID = o_ID;
        this.m_Name = o_Name;
        this.m_Password = o_Password;
    }
    /**
     * Wrapper for the add book method from the BookClass
     * @param o_Book Book to be added
     */
    public void AddBook(Book o_Book)
    {
        // Could you please add a get book method that checks if a book is in the static arraylist
        if(Book.getBook(o_Book.getBookID())!=null)
        {
            // There should be another one that should add Issued quantites so that we increase the 
            // total books by all the amount found in the new book class 
            Book.increaseQuan(o_Book.getBookID(), o_Book.getAvail());
            return;
        }
        Book.addBook(o_Book.getBookID(),o_Book.getAvail(),o_Book.getIssue(),o_Book.getName(), o_Book.getAuthor());        
    }
    /**
     * Wrapper for the remove book method
     * @param o_ID ID of the book to be deleted
     */
    public void DeleteBook(int o_ID)
    {
        Book.removeBook(o_ID);
    }
    /**
     * Wrapper for CreateIssueRequest from IssueLogger Class 
     * @param o_Book_ID ID of the book to be Issued
     * @param o_Student_ID ID of the Student to whom the book will be Issued
     */
    public void IssueBook(int o_Book_ID, int o_Student_ID)
    {
        IssueLogger.CreateIssueRequest(Book.getBook(o_Book_ID),Student.getStudent(o_Student_ID));

    }
    /**
     * Wrapper for MarkIssueAsClosed from IssueLogger Class
     * @param o_Book_ID ID of the book that was issued
     * @param o_Student_ID ID of the student that was issued
     */
    public void ReturnBook(int o_Book_ID, int o_Student_ID)
    {
        IssueLogger.MarkIssueAsClosed(Student.getStudent(o_Student_ID),Book.getBook(o_Book_ID));
    } 
    /**
     * Wrapper for the displayList from the Book Class
     */
    public static void ShowBooks()
    {
        Book.displayList();
    }
    /**
     * Wrapper for DisplayIssuedBooks from IssueLogger Class 
     */
    public static void ShowIssuedBooks()
    {
        IssueLogger.DisplayIssuedBooks();
    }
    /**
     * Prints Out the Librarians
     */
    public static void ShowLibrarians()
    {
        for(Librarian L  : s_Librarians.values())
            System.out.println(L.toString());
    }
    public String toString()
    {
        return "Librarian ID: "+ m_ID +"Librarian Name: "+ m_Name;
    }
    //Setters and Getters
    public int getID() {
        return m_ID;
    }
    public void setID(int o_ID) {
        this.m_ID = o_ID;
    }
    public String getName() {
        return m_Name;
    }
    public void setName(String o_Name) {
        this.m_Name = o_Name;
    }
    public String getPassword() {
        return m_Password;
    }
    public void setPassword(String o_Password) {
        this.m_Password = o_Password;
    }
    private int m_ID;
    private String m_Name;
    private String m_Password;
}