import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataLoader {
    private static DataLoader Loader;
    public static DataLoader Loader()
    {
        if(Loader == null)
            Loader =new DataLoader();
        return Loader;
    }
    public static void LoadAdminTable(String AdminPath)
    {
        FileInputStream f = null;
        Scanner s = null;
        try
        {
            f= new FileInputStream(AdminPath);
            s = new Scanner(f);
            String[] Line;
            int AdminID;
            String AdminName;
            String AdminPassword;
            if (f.available()==0)
                throw new FileNotFoundException(); 
            while(s.hasNextLine())
            {
                Line = s.nextLine().trim().split(",");
                AdminID = Integer.parseInt(Line[0]);
                AdminName = Line[1].replace("_", " ").trim();
                AdminPassword = Line[2].trim();
                Admin.addAdmin(AdminID, AdminName, AdminPassword);
            }
        }
        catch(FileNotFoundException e)
        {
            Admin.addAdmin(0, "admin", "admin");
            System.out.println("File not found added default admin\nPlease login as the default admin and add your admins and librarians");
            File a = new File(AdminPath);  
            try
            {
                a.createNewFile();
            }
            catch (IOException n)
            {
                System.out.println(n);
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
    public static void WriteAdminTable(String AdminPath)
    {
        FileWriter f = null;
        try
        {
            f= new FileWriter(AdminPath);
            StringBuffer buffer= new StringBuffer();
            for(Admin Ad : Admin.getAdminList())
            {
                buffer.append(Integer.toString(Ad.getAdminID())+", "+Ad.getAdminName().replace(" ", "_")+" ,"+Ad.getAdminPassword()+"\n");
            }
            f.write(buffer.toString());

            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        catch (NullPointerException e)
        {
            System.out.println("Admin Table Is null program crashed unexcepectedly");
        }
    }
    public static void LoadBookTable(String BookPath)
    {
        FileInputStream f = null;
        Scanner s = null;
        try
        {
            f= new FileInputStream(BookPath);
            s = new Scanner(f);
            String[] Line;
            int BookID;
            String BookName;
            String AuthourName;
            int AvailableQuantity;
            int IssuedQuantity;
            if (f.available()==0)
                throw new FileNotFoundException(); 
            while(s.hasNextLine())
            {
                Line = s.nextLine().trim().split(",");
                BookID = Integer.parseInt(Line[0]);
                BookName = Line[1].replace("_", " ").trim();
                AuthourName = Line[2].replace("_", " ").trim();
                AvailableQuantity =Integer.parseInt(Line[3]);
                IssuedQuantity = Integer.parseInt(Line[4]);
                Book.addBook(BookID, AvailableQuantity, IssuedQuantity, BookName, AuthourName);
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found, created new book database");
            File a = new File(BookPath);  
            try
            {
                a.createNewFile();
            }
            catch (IOException n)
            {
                System.out.println(n);
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
    public static void WriteBookTable(String BookPath)
    {
        FileWriter f = null;
        try
        {
            f= new FileWriter(BookPath);
            StringBuffer buffer= new StringBuffer();
            for(Book Bo : Book.getBookList())
            {
                buffer.append(Integer.toString(Bo.getBookID())+", "+Bo.getName().replace(" ", "_")+" ,"+Bo.getAuthor().replace(" ", "_")+" ,"+ Integer.toString(Bo.getAvail())+" ," + Integer.toString(Bo.getIssue())+"\n");
            }
            f.write(buffer.toString());
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        catch (NullPointerException e)
        {
        System.out.println("Book Table Is null program crashed unexcepectedly");
        }
    }
    public static void LoadLibrarianTable(String LibPath)
    {
        FileInputStream f = null;
        Scanner s = null;
        try
        {
            f= new FileInputStream(LibPath);
            s = new Scanner(f);
            String[] Line;
            int LibrarianID;
            String LibrarianName;
            String LibrarianPassword;
            if (f.available()==0)
                throw new FileNotFoundException(); 
            while(s.hasNextLine())
            {
                Line = s.nextLine().trim().split(",");
                LibrarianID = Integer.parseInt(Line[0]);
                LibrarianName = Line[1].replace("_", " ").trim();
                LibrarianPassword = Line[2].trim();
                Librarian.addLibrarian(LibrarianID, LibrarianName, LibrarianPassword);
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found, Please login as an admin and add your librarians");
            File a = new File(LibPath);  
            try
            {
                a.createNewFile();
            }
            catch (IOException n)
            {
                System.out.println(n);
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
    public static void WriteLibrarianTable(String LibPath)
    {
        FileWriter f = null;
        try
        {
            f= new FileWriter(LibPath);
            StringBuffer buffer= new StringBuffer();
            for(Librarian Li : Librarian.getLibraryHashMap().values())
            {
                buffer.append(Integer.toString(Li.getID())+", "+Li.getName().replace(" ", "_")+" ,"+Li.getPassword()+"\n");
            }
            f.write(buffer.toString());
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        catch (NullPointerException e)
        {
            System.out.println("Librarian Table Is null program crashed unexcepectedly");
        }
    }
    public static void LoadIssueTable(String IssuesPath)
    {
        FileInputStream f = null;
        Scanner s = null;
        try
        {
            f= new FileInputStream(IssuesPath);
            s = new Scanner(f);
            String[] Line;
            int ProcedureID;
            int BookID;
            int StudentID;
            boolean isReturned;
            if (f.available()==0)
                throw new FileNotFoundException(); 
            while(s.hasNextLine())
            {
                Line = s.nextLine().trim().split(",");
                ProcedureID = Integer.parseInt(Line[0]);
                BookID = Integer.parseInt(Line[1]);
                StudentID = Integer.parseInt(Line[2]);
                isReturned = Line[3].trim().equals("R");
                IssueLogger.AddIssue(new Issue(ProcedureID,BookID,StudentID,isReturned));
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found added empty issues database");
            File a = new File(IssuesPath);  
            try
            {
                a.createNewFile();
            }
            catch (IOException n)
            {
                System.out.println(n);
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
    public static void WriteIssueTable(String IssuesPath)
    {
        FileWriter f = null;
        try
        {
            f= new FileWriter(IssuesPath);
            StringBuffer buffer= new StringBuffer();
            for(Issue Is : IssueLogger.getIssueTable())
            {
                buffer.append(Integer.toString(Is.getProcedure_id())+", "+Integer.toString(Is.getBook_ID())+" ,"+Integer.toString(Is.getStudent_ID())+" ,"+Boolean.toString(Is.isReturned())+"\n");
            }
            f.write(buffer.toString());
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        catch (NullPointerException e)
        {
            System.out.println("Issues Table Is null program crashed unexcepectedly");
        }
    }
    public static void LoadStudentTable( String StudentPath)
    {
        FileInputStream f = null;
        Scanner s = null;
        try
        {
            f= new FileInputStream(StudentPath);
            s = new Scanner(f);
            String[] Lines;
            int StudentID;
            String StudentName;
            String StudentBDay;
            String StudentEmail;
            String StudentPhoneNo;
            if (f.available()==0)
                throw new FileNotFoundException(); 
            while(s.hasNextLine())
            {
                Lines = s.nextLine().trim().split(",");
                StudentID = Integer.parseInt(Lines[0]);
                StudentName = Lines[1].replace("_", " ").trim();
                StudentBDay = Lines[2].trim();
                StudentEmail = Lines[3].trim();
                StudentPhoneNo = Lines[4].trim();
                Student.addStudent(new Student(StudentID, StudentName, StudentBDay, StudentEmail, StudentPhoneNo));
            }
        }
        catch(FileNotFoundException e)
        {
            Admin.addAdmin(0, "admin", "admin");
            System.out.println("File not found added empty student database");
            File a = new File(StudentPath);  
            try
            {
                a.createNewFile();
            }
            catch (IOException n)
            {
                System.out.println(n);
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
    public static void WriteStudentTable( String StudentPath)
    {
        FileWriter f = null;
        try
        {
            f= new FileWriter(StudentPath);
            StringBuffer buffer= new StringBuffer();
            for(Student St : Student.getStudents().values())
            {
                buffer.append(Integer.toString(St.getID())+", "+St.getName().replace(" ", "_")+" ,"+St.getBDay()+" ,"+St.getEmail()+" ,"+St.getPhoneNumber()+"\n");
            }
            f.write(buffer.toString());
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        catch (NullPointerException e)
        {
            System.out.println("Students Table Is null program crashed unexcepectedly");
        }
    }
    private DataLoader()
    {

    }
}
