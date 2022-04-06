import java.util.ArrayList;
import java.util.HashSet;
public class IssueLogger {
    /**
     * Private constructor to prevent creation of new instances
     */
    private IssueLogger()
    {

    }
    // Static Singleton Object of the IssueLogger 
    private static IssueLogger s_logger;
    // Static List of all issues to be tracked by the logger  
    private static ArrayList<Issue> s_Issues =  new ArrayList<Issue>();
    /**
     * @return Static Issues List
     */
    public static ArrayList<Issue> getIssueTable()
    {
        if(s_Issues==null)
            return null;
        return s_Issues;
    }
    /**
     * Manages Issues Count to make tracking procedure IDs easier 
    */
    private static int s_Issue_Count=0;
    /**
     * @return the Issue Logger object
     */
    public static IssueLogger Logger()
    {
        if(s_logger != null)
        {
            return s_logger;
        }
        s_logger = new IssueLogger();
        return s_logger;
    }
    /**
     * Tries to Create a new issue 
     * @param o_Book instance of book that is to be issued
     * @param o_Student intance of student to whom the book is issued
     */
    public static void CreateIssueRequest(Book o_Book, Student o_Student)
    {
        // Null checks to prevent errors
        if(o_Book==null||o_Student==null)
            return;
        // Check if the book and student passed exist in the static list
        if((Book.getBook(o_Book.getBookID())!=null)&&(Student.getStudent(o_Student.getID())!=null))
        {
            // Check if there are available books to be issued
            if(o_Book.getAvail()<=0)
                return;
            Issue new_Issue = new Issue(++s_Issue_Count, o_Book.getBookID(), o_Student.getID(),false);
            s_Issues.add(new_Issue);
            Book.issueBook(o_Book.getBookID());
        }
    }
    /**
     * Closes an open issue using the procedure ID
     * @param o_Procedure_id
     */
    public static void MarkIssueAsClosed(int o_Procedure_id)
    {
        // Search for the issue that has that Procedure ID
        for (Issue issue : s_Issues) {
            if(issue.getProcedure_id() == o_Procedure_id)
            {
                // Call the helper function that takes an IssueObject
                MarkIssueAsClosed(issue);
                return;
            }
        }
        return;
    }
    /**
     * @param o_StudentID
     * ID of the student
     * @return A list of all issues the student has ever opened
     * either closed or not
     */
    public static ArrayList<Issue> GetIssuesFromStudent(int o_StudentID)
    {
        ArrayList<Issue> StudentIssues = new ArrayList<Issue>();
        // Search the issue list for all issues that contain the student ID 
        // and add them to the studentissues list
        for(Issue I : s_Issues)
        {
            if(I.getStudent_ID() == o_StudentID)
                StudentIssues.add(I);
        }
        return StudentIssues;
    }
    /**
     * 
     * @param o_BookID ID of the book
     * @return All issues that has the book either closed or not
     */
    public static ArrayList<Issue> GetIssuesForBook(int o_BookID)
    {
        ArrayList<Issue> BookIssues = new ArrayList<Issue>();
        // Search the issues list for all issues that contain the book id
        // and add these issues to BookIssues List
        for(Issue I : s_Issues)
        {
            if(I.getBook_ID() == o_BookID)
                BookIssues.add(I);
        }
        return BookIssues;
    }
    /**
     * 
     * @param o_BookID ID of the book
     * @param o_StudentID ID of the student
     * @return All issues that this particular student has created for this particular book
     */
    public static ArrayList<Issue> GetIssues(int o_BookID, int o_StudentID)
    {
        ArrayList<Issue> Issues = new ArrayList<Issue>();
        // search the issues List for all issues that contain the student and book 
        // and add them to the issues list   
        for(Issue I : s_Issues)
        {
            if(I.getBook_ID() == o_BookID&&I.getStudent_ID()==o_StudentID)
                Issues.add(I);
        }
        return Issues;
    }
    /**
     * 
     * @param o_BookID ID of the book
     * @return All Open Issues for this book
     */
    public static ArrayList<Issue> GetOpenIssuesForBook(int o_BookID)
    {
        ArrayList<Issue> openIssues =new ArrayList<Issue>();
        // Search the Issues List of the book for the issues that have not been returned
        //  and add them to OpenBookIssuesList
        for(Issue I: GetIssuesForBook(o_BookID))
        {
            if (I.isReturned()==false)
                openIssues.add(I);
        }
        return openIssues;
    }
    /**
     * 
     * @param o_StudentID ID of the Student
     * @return All open Issues of this particular student 
     */
    public static ArrayList<Issue> GetOpenIssuesFromStudent( int o_StudentID)
    {
        ArrayList<Issue> openIssues =new ArrayList<Issue>();
        // Search the Issues List of the Student and check for the issues that have not been returned
        // and add them to the OpenStudentIssuesList
        for(Issue I:GetIssuesFromStudent(o_StudentID))
        {
            if (I.isReturned()==false)
                openIssues.add(I);
        }
        return openIssues;
    }
    public static ArrayList<Issue> GetOpenIssues(int o_BookID, int o_StudentID)
    {
        ArrayList<Issue> openIssues =new ArrayList<Issue>();
        // Search the Issues List of student and book combination that have not been returned
        // and add them to the OpenIssuesList 
        for(Issue I: GetIssues(o_BookID, o_StudentID))
        {
            if (I.isReturned()==false)
                openIssues.add(I);
        }
        return openIssues;
    }
    /**
     * 
     * @param o_BookID ID of the book
     * @return List of all issues that contain the book and has been returned
     */
    public static ArrayList<Issue> GetClosedIssuesForBook(int o_BookID)
    {
        ArrayList<Issue> Issues =new ArrayList<Issue>();
        // Search the Book Issues List  that have been returned
        // and add them to the IssuesList 
        for(Issue I: GetIssuesForBook(o_BookID))
        {
            if (I.isReturned())
                Issues.add(I);
        }
        return Issues;
    }
    /**
     * 
     * @param o_StudentID ID of the Student
     * @return Issues of the student that has been closed
     */
    public static ArrayList<Issue> GetClosedIssuesFromStudent( int o_StudentID)
    {
        ArrayList<Issue> Issues =new ArrayList<Issue>();
        // Search the Student IssuesList for Issues that have been returned
        // and add them to the IssuesList
        for(Issue I:GetIssuesFromStudent(o_StudentID))
        {
            if (I.isReturned())
                Issues.add(I);
        }
        return Issues;
    }
    /**
     * 
     * @param o_BookID ID of the Book
     * @param o_StudentID ID of the Student
     * @return List of Closed Issues that contain both the student and book  
     */
    public static ArrayList<Issue> GetClosedIssues(int o_BookID, int o_StudentID)
    {
        ArrayList<Issue> Issues =new ArrayList<Issue>();
        // Search the Issues List of student and book combination that have been returned
        // and add them to the IssuesList 
        for(Issue I: GetIssues(o_BookID, o_StudentID))
        {
            if (I.isReturned())
                Issues.add(I);
        }
        return Issues;
    }
    /**
     * Closes the Issue and reduces the Issued count and increases the Available count
     * @param o_Student Object of the student class to which the book was issued 
     * @param o_Book Object of the book class which was assigned to the student
     */
    public static void MarkIssueAsClosed(Student o_Student, Book o_Book)
    {
        if(o_Book==null||o_Student==null)
            return;
        // Search for the issue that contains the student and book combination
        for (Issue issue : s_Issues) {
            if(issue.getBook_ID()==o_Book.getBookID()&&issue.getStudent_ID()==o_Student.getID()&&!issue.isReturned())
            {
                // Calls the helper function that takes the issue object
                MarkIssueAsClosed(issue);
                return;
            }
        }
        return;
    }
    /**
     * Prints All books that are currently issued
     */
    public static void DisplayIssuedBooks()
    {
        HashSet<Integer> DisplayedBooks = new HashSet<Integer>();
        for(Issue I : s_Issues)
        {
            // Check if the book has been displayed before and if the issue is not returned 
            if(!DisplayedBooks.contains(I.getBook_ID())&&I.isReturned()==false)
            {
                DisplayedBooks.add(I.getBook_ID());
                System.out.println(Book.getBook(I.getBook_ID()).toString());
            }
        }
    }
    /**
     * Adds an issue object to the Issues List
     * @param o_Issue
     */
    public static void AddIssue(Issue o_Issue)
    {
        s_Issue_Count+=o_Issue.getProcedure_id();
        s_Issues.add(o_Issue);
    }
    // Mark the issue as returned 
    // and call the return Book method to handle returns by the book class
    private static void MarkIssueAsClosed(Issue o_Issue)
    {
        o_Issue.Return();
        Book.returnBook(o_Issue.getBook_ID());
    }

}
class Issue
{

    // parameterized constructor
    public Issue(int o_Procedure_id, int o_Book_ID, int o_Student_ID, boolean o_Is_Returned) {
        this.m_Procedure_id = o_Procedure_id;
        this.m_Book_ID = o_Book_ID;
        this.m_Student_ID = o_Student_ID;
        this.m_Is_Returned = o_Is_Returned;
    }
    // Setters and Getters
    public int getProcedure_id() {
        return m_Procedure_id;
    }
    public boolean isReturned() {
        return m_Is_Returned;
    }
    public void Return() {
        this.m_Is_Returned =true;
    }
    public int getStudent_ID() {
        return m_Student_ID;
    }
    public void setStudent_ID(int m_Student_ID) {
        this.m_Student_ID = m_Student_ID;
    }
    public int getBook_ID() {
        return m_Book_ID;
    }
    public void setBook_ID(int m_Book_ID) {
        this.m_Book_ID = m_Book_ID;
    }
    public void setProcedure_id(int m_Procedure_id) {
        this.m_Procedure_id = m_Procedure_id;
    } 
    private int m_Procedure_id;
    private int m_Book_ID;
    private int m_Student_ID;
    private boolean m_Is_Returned;
}