import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
public class Librarian 
{
    private static HashMap<Integer, Librarian> s_Librarians;
    private static HashSet<String> s_LoginInfo;
    private static Librarian currentlyLoggedIn;
    public static void LibrarianView()
    {
        Scanner inputHandler = new Scanner (System.in);
        String loginName,loginPassword,retry;
        System.out.print("Enter Your name:\n");
        loginName = inputHandler.nextLine();
        System.out.print("Enter your Password");
        loginPassword = inputHandler.nextLine();
        while(!Librarian.Login(loginName+loginPassword))
        {
            System.out.println("Incorrect login info, do you want to re enter you data\n enter Y to retry anything else to exit");
            retry= inputHandler.nextLine();
            if(retry.toCharArray()[0]=='y'||retry.toCharArray()[0]=='Y')
            {
                inputHandler.close();
                return;
            }
            System.out.print("Enter Your name:\n");
            loginName = inputHandler.nextLine();
            System.out.print("Enter your Password");
            loginPassword = inputHandler.nextLine();
        }
        int choice = 0;
        System.out.println("Welcome, " + loginName+ "\n please choose an action by entering the number next to the chosen action");
        System.out.println("1. Add books\n2. Delete books\n 3. Issue Books\n4. Return Issued Books\n5. View All Books\n6. Find a book by name\n 7. Find a book by ID\n8. Log out");
        choice = inputHandler.nextInt();
        while(choice!=8)
        {
            switch(choice)
            {
                case 1:
                    {
                        System.out.print("Please enter the Book ID, Book Name, Author name, Available Quantity, Issued Quantity\n");
                        int o_ID = inputHandler.nextInt();
                        String o_BookName = inputHandler.nextLine();
                        String o_BookAuthor = inputHandler.nextLine();
                        int o_AvailQuan = inputHandler.nextInt();
                        int o_IssuedQuan = inputHandler.nextInt();
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
                        System.out.print("Please enter the ID of the book to be deleted\n");
                        int o_ID = inputHandler.nextInt();
                        currentlyLoggedIn.DeleteBook(o_ID);
                        if(Book.getBook(o_ID)==null)
                            System.out.print("Book Deleted Successfully\n");
                        else
                            System.out.print("Book couldn't be deleted\n");
                    }
                    break;
                case 3:
                    {
                        System.out.print("Please enter the ID of the book to be issued and the Student ID\n");
                        int o_Book_ID = inputHandler.nextInt();
                        int o_Student_ID = inputHandler.nextInt();
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
                        System.out.print("Please enter the ID of the book to be issued and the Student ID\n");
                        int o_Book_ID = inputHandler.nextInt();
                        int o_Student_ID = inputHandler.nextInt();
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
                        System.out.print("Please Enter the name of the book\n");
                        String o_BookName = inputHandler.nextLine();                    
                    }
                    break;
                case 7:
                    {    
                        System.out.print("Enter the ID of the book you want to find\n");
                        int o_Book_ID = inputHandler.nextInt();
                        if(Book.getBook(o_Book_ID)!=null)    
                            System.out.println(Book.getBook(o_Book_ID).toString());
                        else
                            System.out.println("Book Couldn't be found");    
                    }
                    break;
                case 8:
                    {
                        currentlyLoggedIn = null;
                        s_Librarians =null;
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
        inputHandler.close();
    }
    public static Boolean Login(String o_LoginInfo)
    {
        if(s_LoginInfo.contains(o_LoginInfo))
        {
            currentlyLoggedIn = Librarian.getLibrarian(o_LoginInfo);
            return true;
        }
        return false;
    } 
    public static void addLibrarian(int o_ID,String o_Name, String o_Password,String o_CallerID)
    {
        //We could add a check if the caller object has admin priveligies  
        //by comparing the caller ID (Name+Password) with the HashSet<String>
        //LoginInfo in the admin class (I am suggesting you add it)
        //  
        if(s_Librarians==null)
            s_Librarians = new HashMap<Integer,Librarian>();
        if(s_Librarians.get(o_ID)!=null)
        {
            System.out.println("Duplicate ID, Cant have Multiple Librarians with the same ID\nCouldn't Create Student Instance");
            return;
        }
        for(char l:o_Name.toCharArray())
        {
            if(!Character.isAlphabetic(l))
            {
                System.out.println("Name Invalid, Cant have non alphabetical characters in name\nCouldn't Create Student Instance");
                return;
            }
        }
        s_Librarians.put(o_ID,new Librarian(o_ID, o_Name,o_Password));   
        s_LoginInfo.add(o_Name+o_Password);
    }
    public static void addLibrarian(Librarian o_Librarian)
    {
        if(s_Librarians==null)
            s_Librarians = new HashMap<Integer,Librarian>();
        if(s_Librarians.get(o_Librarian.getID())!=null)
        {
            System.out.println("Duplicate ID, Cant have Multiple students with the same ID\nCouldn't Create Student Instance");
            return;
        }
        for(char l:o_Librarian.getName().toCharArray())
        {
            if(!Character.isAlphabetic(l))
            {
                System.out.println("Name Invalid, Cant have non alphabetical characters in name\nCouldn't Create Student Instance");
                return;
            }
        }
        s_Librarians.put(o_Librarian.getID(),o_Librarian);   
        s_LoginInfo.add(o_Librarian.getName()+o_Librarian.getPassword());
    }
    public static Librarian getLibrarian(String o_LoginInfo)
    {
        for(Librarian l:s_Librarians.values())
        {
            if((l.getName()+l.getPassword()).equals(o_LoginInfo))
                return l;
        }
        return null;
    }
    public static Librarian getLibrarian(int o_ID)
    {
        if(s_Librarians==null)
            s_Librarians = new HashMap<Integer,Librarian>();
        return s_Librarians.get(o_ID);
    }
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
    public void DeleteBook(int o_ID)
    {
        Book.removeBook(o_ID);
    }
    public void IssueBook(int o_Book_ID, int o_Student_ID)
    {
        IssueLogger.CreateIssueRequest(Book.getBook(o_Book_ID),Student.getStudent(o_Student_ID));

    }
    public void ReturnBook(int o_Book_ID, int o_Student_ID)
    {
        IssueLogger.MarkIssueAsClosed(Student.getStudent(o_Student_ID),Book.getBook(o_Book_ID));
    } 
    public static void ShowBooks()
    {
        Book.displayList();
    }
    public static void ShowIssuedBooks()
    {
        IssueLogger.DisplayIssuedBooks();
    }
    public static void ShowLibrarians()
    {
        for(Librarian L  : s_Librarians.values())
            System.out.println(L.toString());
    }
    public String toString()
    {
        return "Librarian ID: "+ m_ID +"Librarian Name: "+ m_Name;
    }
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