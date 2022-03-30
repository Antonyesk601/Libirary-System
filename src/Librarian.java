import java.util.HashMap;
import java.util.HashSet;

public class Librarian 
{
    private static HashMap<Integer, Librarian> s_Librarians;
    private static HashSet<String> s_LoginInfo;
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
            System.out.println("Duplicate ID, Cant have Multiple students with the same ID\nCouldn't Create Student Instance");
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