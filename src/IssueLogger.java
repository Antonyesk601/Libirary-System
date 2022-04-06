import java.util.ArrayList;
import java.util.HashSet;
public class IssueLogger {
    private IssueLogger()
    {

    }
    private static IssueLogger s_logger; 
    private static ArrayList<Issue> s_Issues =  new ArrayList<Issue>();
    public static ArrayList<Issue> getIssueTable()
    {
        if(s_Issues==null)
            return null;
        return s_Issues;
    }
    private static int s_Issue_Count=0;
    public static IssueLogger Logger()
    {
        if(s_logger != null)
        {
            return s_logger;
        }
        s_logger = new IssueLogger();
        return s_logger;
    }
    public static void CreateIssueRequest(Book o_Book, Student o_Student)
    {

        if(o_Book==null||o_Student==null)
            return;
        if((Book.getBook(o_Book.getBookID())!=null)&&(Student.getStudent(o_Student.getID())!=null))
        {
            if(o_Book.getAvail()<=0)
                return;
            Issue new_Issue = new Issue(++s_Issue_Count, o_Book.getBookID(), o_Student.getID(),false);
            s_Issues.add(new_Issue);
            Book.issueBook(o_Book.getBookID());
        }
    }
    public static void MarkIssueAsClosed(int o_Procedure_id)
    {
        for (Issue issue : s_Issues) {
            if(issue.getProcedure_id() == o_Procedure_id)
            {
                MarkIssueAsClosed(issue);
                return;
            }
        }
        return;
    }
    public static ArrayList<Issue> GetIssuesFromStudent(int o_StudentID)
    {
        ArrayList<Issue> StudentIssues = new ArrayList<Issue>();
        for(Issue I : s_Issues)
        {
            if(I.getStudent_ID() == o_StudentID)
                StudentIssues.add(I);
        }
        return StudentIssues;
    }
    public static ArrayList<Issue> GetIssuesForBook(int o_BookID)
    {
        ArrayList<Issue> BookIssues = new ArrayList<Issue>();
        for(Issue I : s_Issues)
        {
            if(I.getBook_ID() == o_BookID)
                BookIssues.add(I);
        }
        return BookIssues;
    }
    public static ArrayList<Issue> GetIssues(int o_BookID, int o_StudentID)
    {
        ArrayList<Issue> Issues = new ArrayList<Issue>();
        for(Issue I : s_Issues)
        {
            if(I.getBook_ID() == o_BookID&&I.getStudent_ID()==o_StudentID)
                Issues.add(I);
        }
        return Issues;
    }
    public static ArrayList<Issue> GetOpenIssuesForBook(int o_BookID)
    {
        ArrayList<Issue> openIssues =new ArrayList<Issue>();
        for(Issue I: GetIssuesForBook(o_BookID))
        {
            if (I.isReturned()==false)
                openIssues.add(I);
        }
        return openIssues;
    }
    public static ArrayList<Issue> GetOpenIssuesFromStudent( int o_StudentID)
    {
        ArrayList<Issue> openIssues =new ArrayList<Issue>();
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
        for(Issue I: GetIssues(o_BookID, o_StudentID))
        {
            if (I.isReturned()==false)
                openIssues.add(I);
        }
        return openIssues;
    }
    public static ArrayList<Issue> GetClosedIssuesForBook(int o_BookID)
    {
        ArrayList<Issue> openIssues =new ArrayList<Issue>();
        for(Issue I: GetIssuesForBook(o_BookID))
        {
            if (I.isReturned())
                openIssues.add(I);
        }
        return openIssues;
    }
    public static ArrayList<Issue> GetClosedIssuesFromStudent( int o_StudentID)
    {
        ArrayList<Issue> openIssues =new ArrayList<Issue>();
        for(Issue I:GetIssuesFromStudent(o_StudentID))
        {
            if (I.isReturned())
                openIssues.add(I);
        }
        return openIssues;
    }
    public static ArrayList<Issue> GetClosedIssues(int o_BookID, int o_StudentID)
    {
        ArrayList<Issue> openIssues =new ArrayList<Issue>();
        for(Issue I: GetIssues(o_BookID, o_StudentID))
        {
            if (I.isReturned())
                openIssues.add(I);
        }
        return openIssues;
    }
    public static void MarkIssueAsClosed(Student o_Student, Book o_Book)
    {
        if(o_Book==null||o_Student==null)
            return;
        for (Issue issue : s_Issues) {
            if(issue.getBook_ID()==o_Book.getBookID()&&issue.getStudent_ID()==o_Student.getID()&&!issue.isReturned())
            {
                MarkIssueAsClosed(issue);
                return;
            }
        }
        return;
    }
    public static void DisplayIssuedBooks()
    {
        HashSet<Integer> DisplayedBooks = new HashSet<Integer>();
        for(Issue I : s_Issues)
        {
            if(!DisplayedBooks.contains(I.getBook_ID()))
            {
                DisplayedBooks.add(I.getBook_ID());
                System.out.println(Book.getBook(I.getBook_ID()).toString());
            }
        }
    }
    public static void AddIssue(Issue o_Issue)
    {
        s_Issue_Count+=o_Issue.getProcedure_id();
        s_Issues.add(o_Issue);
    }
    private static void MarkIssueAsClosed(Issue o_Issue)
    {
        o_Issue.Return();
        Book.returnBook(o_Issue.getBook_ID());
    }

}
class Issue
{
    
    public Issue(int o_Procedure_id, int o_Book_ID, int o_Student_ID, boolean o_Is_Returned) {
        this.m_Procedure_id = o_Procedure_id;
        this.m_Book_ID = o_Book_ID;
        this.m_Student_ID = o_Student_ID;
        this.m_Is_Returned = o_Is_Returned;
    }
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