import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DataLoader {
    private static DataLoader Loader;
    public static DataLoader Loader()
    {
        if(Loader == null)
            Loader =new DataLoader();
        return Loader;
    }
    public static void LoadAdminTable(ArrayList<Admin> o_AdminList, String AdminPath)
    {
        FileInputStream f = null;
        Scanner s = null;
        try
        {
            f= new FileInputStream(AdminPath);
            s = new Scanner(f);
            int AdminID;
            String AdminName;
            String AdminPassword;
            while(s.hasNextLine())
            {
                AdminID = s.nextInt();
                AdminName = s.next().replace(",","").replace("_", " ");
                AdminPassword = s.next().replace(",","").trim();
                Admin.addAdmin(AdminID, AdminName, AdminPassword);
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            if (s!=null)
                s.close();
        }
    }
    public static void WriteAdminTable(ArrayList<Admin> o_AdminList, String AdminPath)
    {
        FileOutputStream f = null;
        try
        {
            f= new FileOutputStream(AdminPath);
            StringBuffer buffer= new StringBuffer();
            for(Admin Ad : o_AdminList)
            {
                buffer.append(Integer.toString(Ad.getAdminID())+", "+Ad.getAdminName().replace(" ", "_")+" ,"+Ad.getAdminPassword()+"\n");
            }
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    public static void LoadBookTable(ArrayList<Book> o_BookList, String BookPath)
    {
        FileInputStream f = null;
        Scanner s = null;
        try
        {
            f= new FileInputStream(BookPath);
            s = new Scanner(f);
            int BookID;
            String BookName;
            String AuthourName;
            int AvailableQuantity;
            int IssuedQuantity;
            while(s.hasNextLine())
            {
                BookID = s.nextInt();
                BookName = s.next().replace(",","").replace("_", " ");
                AuthourName = s.next().replace(",","").trim();
                AvailableQuantity =s.nextInt();
                IssuedQuantity = s.nextInt();
                Book.addBook(BookID, AvailableQuantity, IssuedQuantity, BookName, AuthourName);
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            if (s!=null)
                s.close();
        }
    }
    public static void WriteBookTable(ArrayList<Book> o_BookList, String BookPath)
    {
        FileOutputStream f = null;
        try
        {
            f= new FileOutputStream(BookPath);
            StringBuffer buffer= new StringBuffer();
            for(Book Bo : o_BookList)
            {
                buffer.append(Integer.toString(Bo.getBookID())+", "+Bo.getName().replace(" ", "_")+" ,"+Bo.getAuthor().replace(" ", "_")+" ,"+ Integer.toString(Bo.getAvail())+" ," + Integer.toString(Bo.getIssue())+"\n");
            }
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    public static void LoadLibrarianTable(HashMap<Integer,Librarian> o_LibrarianTable, String LibPath, String o_CallerID)
    {
        FileInputStream f = null;
        Scanner s = null;
        try
        {
            f= new FileInputStream(LibPath);
            s = new Scanner(f);
            int LibrarianID;
            String LibrarianName;
            String LibrarianPassword;
            while(s.hasNextLine())
            {
                LibrarianID = s.nextInt();
                LibrarianName = s.next().replace(",","").replace("_", " ");
                LibrarianPassword = s.next().replace(",","").trim();
                Librarian.addLibrarian(LibrarianID, LibrarianName, LibrarianPassword, o_CallerID);
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            if (s!=null)
                s.close();
        }
    }
    public static void WriteLibrarianTable(HashMap<Integer, Librarian> o_LibrarianTable, String LibPath)
    {
        FileOutputStream f = null;
        try
        {
            f= new FileOutputStream(LibPath);
            StringBuffer buffer= new StringBuffer();
            for(Librarian Li : o_LibrarianTable.values())
            {
                buffer.append(Integer.toString(Li.getID())+", "+Li.getName().replace(" ", "_")+" ,"+Li.getPassword()+"\n");
            }
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    public static void LoadIssueTable(ArrayList<Issue> o_IssuesTable, String IssuesPath)
    {
        FileInputStream f = null;
        Scanner s = null;
        try
        {
            f= new FileInputStream(IssuesPath);
            s = new Scanner(f);
            int ProcedureID;
            int BookID;
            int StudentID;
            boolean isReturned;
            while(s.hasNextLine())
            {
                ProcedureID = s.nextInt();
                BookID = s.nextInt();
                StudentID = s.nextInt();
                isReturned = s.next().replace(",","").trim().equals("R");
                IssueLogger.AddIssue(new Issue(ProcedureID,BookID,StudentID,isReturned));
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            if (s!=null)
                s.close();
        }
    }
    public static void WriteIssueTable(ArrayList<Issue> o_IssuesTable, String IssuesPath)
    {
        FileOutputStream f = null;
        try
        {
            f= new FileOutputStream(IssuesPath);
            StringBuffer buffer= new StringBuffer();
            for(Issue Is : o_IssuesTable)
            {
                buffer.append(Integer.toString(Is.getProcedure_id())+", "+Integer.toString(Is.getBook_ID())+" ,"+Integer.toString(Is.getStudent_ID())+" ,"+Boolean.toString(Is.isReturned())+"\n");
            }
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    public static void LoadStudentTable(HashMap<Integer,Student> o_StudentTable, String StudentPath,String o_CallerID)
    {
        FileInputStream f = null;
        Scanner s = null;
        try
        {
            f= new FileInputStream(StudentPath);
            s = new Scanner(f);
            int StudentID;
            String StudentName;
            String StudentBDay;
            String StudentEmail;
            String StudentPhoneNo;
            while(s.hasNextLine())
            {
                StudentID = s.nextInt();
                StudentName = s.next().replace(",","").replace("_", " ");
                StudentBDay = s.next().replace(",","");
                StudentEmail = s.next().replace(",","");
                StudentPhoneNo = s.next().replace(",","").trim();
                Student.addStudent(new Student(StudentID, StudentName, StudentBDay, StudentEmail, StudentPhoneNo),o_CallerID);
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            if (s!=null)
                s.close();
        }

    }
    public static void WriteStudentTable(HashMap<Integer,Student> o_StudentTable, String StudentPath)
    {
        FileOutputStream f = null;
        try
        {
            f= new FileOutputStream(StudentPath);
            StringBuffer buffer= new StringBuffer();
            for(Student St : o_StudentTable.values())
            {
                buffer.append(Integer.toString(St.getID())+", "+St.getName().replace(" ", "_")+" ,"+St.getBDay()+" ,"+St.getEmail()+" ,"+St.getPhoneNumber()+"\n");
            }
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    private DataLoader()
    {

    }
}
