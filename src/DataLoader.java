import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataLoader {
    // Singleton Object that could only be read and not set and as such only a getter is provided 
    private static DataLoader Loader;
    public static DataLoader Loader()
    {
        if(Loader == null)
            Loader =new DataLoader();
        return Loader;
    }
    /**  Opens the file denoted to by the admin path string and reads the 
    * file contents and constructrs the static admin list from the contents 
    */
     public static void LoadAdminTable(String AdminPath)
    {
        FileInputStream f = null;
        Scanner s = null;
        try
        {
            f= new FileInputStream(AdminPath);
            s = new Scanner(f);
            // Buffer object to act as the current line pointer
            String[] Line;
            // the next three objects are buffer objects of the data members of the admin class
            int AdminID;
            String AdminName;
            String AdminPassword;
            // Checks if the file is empty (which invokes the same behaviour of a missing file)
            if (f.available()==0)
                throw new FileNotFoundException(); 
            // Check if the file is done yet 
            while(s.hasNextLine())
            {
                // Read a row at a time from the csv
                // each cell contains a member of the Admin class where the cells are always ordered
                Line = s.nextLine().trim().split(",");
                AdminID = Integer.parseInt(Line[0].trim());
                AdminName = Line[1].trim().replace("_", " ").trim();
                AdminPassword = Line[2].trim();
                // Invoke the public method that creates a new admin object that wraps the constructor
                Admin.addAdmin(AdminID, AdminName, AdminPassword);
            }
        }
        // Exception handling if file doesn't exist or is empty
        catch(FileNotFoundException e)
        {
            // Add a default admin to enable using the program for 
            // the first time which should be manually deleted by the admins
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
        // Catches permission errors
        catch (IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            // Close the file if it was opened
            if (s!=null)
                s.close();
        }
    }
    /**
     * Writes the contente of the static admin list to the path
     * denoted to by AdminPath.
     * This method must be called after calling the LoadAdminTable(String AdminPath) at least once
     * in the program runtime to insure working without errors
     */
    public static void WriteAdminTable(String AdminPath)
    {
        FileWriter f = null;
        try
        {
            // Create a new file writer object which expects the file to always be there
            // This method cannot be called before the LoadAdminTable method has been called at least once 
            f= new FileWriter(AdminPath);
            // Create a mutable object to act as a buffer for data storage
            StringBuffer buffer= new StringBuffer();
            // For each loop that writes the admin list contents to the buffer
            for(Admin Ad : Admin.getAdminList())
            {
                buffer.append(Integer.toString(Ad.getAdminID())+","+Ad.getAdminName().replace(" ", "_")+","+Ad.getAdminPassword()+"\n");
            }
            // Convert the String buffer to a String and write it on the file
            f.write(buffer.toString());
            f.close();
        }
        // Checks for permission errors
        catch (IOException e)
        {
            System.out.println(e);
        }
        // Gets invoked if there was an error reading from Admin Table
        catch (NullPointerException e)
        {
            System.out.println("Admin Table Is null program crashed unexcepectedly");
        }
    }
    /**Opens the file denoted to by the BookPath string and reads the 
      *file contents and constructrs the static Book list from the contents 
      */
      public static void LoadBookTable(String BookPath)
    {

        FileInputStream f = null;
        Scanner s = null;
        try
        {
            f= new FileInputStream(BookPath);
            s = new Scanner(f);
            // Buffer object to act as the current line pointer
            String[] Line;
            // the next five objects are buffer objects of the data members of the Book class
            int BookID;
            String BookName;
            String AuthourName;
            int AvailableQuantity;
            int IssuedQuantity;
            // Checks if the file is empty (which invokes the same behaviour of a missing file)
            if (f.available()==0)
                throw new FileNotFoundException(); 
            // Check if the file is done yet
            while(s.hasNextLine())
            {
                // Read a row at a time from the csv
                // each cell contains a member of the Admin class where the cells are always ordered
                Line = s.nextLine().trim().split(",");
                BookID = Integer.parseInt(Line[0].trim());
                BookName = Line[1].replace("_", " ").trim();
                AuthourName = Line[2].replace("_", " ").trim();
                AvailableQuantity =Integer.parseInt(Line[3].trim());
                IssuedQuantity = Integer.parseInt(Line[4].trim());
                // Invoke the public method that creates a new admin object that wraps the constructor
                Book.addBook(BookID, AvailableQuantity, IssuedQuantity, BookName, AuthourName);
            }
        }
        // Exception handling if file doesn't exist or is empty
        catch(FileNotFoundException e)
        {
            // Create a new empty file that could be written to 
            // after the librarian adds books 
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
        // Catches Permission Errors
        catch (IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            // Close the file if it was opened
            if (s!=null)
                s.close();
        }
    }
    public static void WriteBookTable(String BookPath)
    {
        FileWriter f = null;
        try
        {
            // Create a new file writer object which expects the file to always be there
            // This method cannot be called before the LoadBookTable method has been called at least once 
            f= new FileWriter(BookPath);
            // Create a mutable object to act as a buffer for data storage
            StringBuffer buffer= new StringBuffer();
            // For each loop that writes the admin list contents to the buffer
            for(Book Bo : Book.getBookList())
            {
                buffer.append(Integer.toString(Bo.getBookID())+", "+Bo.getName().replace(" ", "_")+" ,"+Bo.getAuthor().replace(" ", "_")+" ,"+ Integer.toString(Bo.getAvail())+" ," + Integer.toString(Bo.getIssue())+"\n");
            }
            // Convert the String buffer to a String and write it on the file
            f.write(buffer.toString());
            f.close();
        }
        // Checks for permission errors
        catch (IOException e)
        {
            System.out.println(e);
        }
        // Gets invoked if there was an error reading from Book Table
        catch (NullPointerException e)
        {
        System.out.println("Book Table Is null program crashed unexcepectedly");
        }
    }
    /**  Opens the file denoted to by the LibPath string and reads the 
    * file contents and constructrs the static Librarian HashMap from the contents 
    */
    public static void LoadLibrarianTable(String LibPath)
    {
        FileInputStream f = null;
        Scanner s = null;
        try
        {
            f= new FileInputStream(LibPath);
            s = new Scanner(f);
            String[] Line;
            // Buffer object to act as the current line pointer
            // the next three objects are buffer objects of the data members of the Librarian class
            int LibrarianID;
            String LibrarianName;
            String LibrarianPassword;
            // Checks if the file is empty (which invokes the same behaviour of a missing file)
            if (f.available()==0)
                throw new FileNotFoundException(); 
            // Check if the file is done yet 
            while(s.hasNextLine())
            {
                // Read a row at a time from the csv
                // each cell contains a member of the Librarian class where the cells are always ordered
                Line = s.nextLine().trim().split(",");
                LibrarianID = Integer.parseInt(Line[0].trim());
                LibrarianName = Line[1].trim().replace("_", " ").trim();
                LibrarianPassword = Line[2].trim();
                Librarian.addLibrarian(LibrarianID, LibrarianName, LibrarianPassword);
            }
        
        }
        // Exception handling if file doesn't exist or is empty
        catch(FileNotFoundException e)
        {
            // Creates an Empty file that should be filled by the admin class
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
        // Catch permission errors
        catch (IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            // Close the file it was opened
            if (s!=null)
                s.close();
        }
    }
    /**
     * Writes the contente of the static Librarian HashMap Values to the path
     * denoted to by LibPath.
     * This method must be called after calling the LoadLibrarianTable(String LibPath) at least once
     * in the program runtime to insure working without errors
     */
    public static void WriteLibrarianTable(String LibPath)
    {
        FileWriter f = null;
        try
        {
            // Create a new file writer object which expects the file to always be there
            // This method cannot be called before the LoadLibrarianTable method has been called at least once 
            f= new FileWriter(LibPath);
            // Create a mutable object to act as a buffer for data storage
            StringBuffer buffer= new StringBuffer();
            // For each loop that writes the Librarian list contents to the buffer
            for(Librarian Li : Librarian.getLibraryHashMap().values())
            {
                buffer.append(Integer.toString(Li.getID())+", "+Li.getName().replace(" ", "_")+" ,"+Li.getPassword()+"\n");
            }
            // Convert the String buffer to a String and write it on the file
            f.write(buffer.toString());
            f.close();
        }
        // Checks for permission errors
        catch (IOException e)
        {
            System.out.println(e);
        }
        // Gets invoked if there was an error reading from Admin Table
        catch (NullPointerException e)
        {
            System.out.println("Librarian Table Is null program crashed unexcepectedly"+e);
        }
    }
    /**  Opens the file denoted to by the IssuesPath string and reads the 
    * file contents and constructrs the static Issues List from the contents 
    */
    public static void LoadIssueTable(String IssuesPath)
    {
        FileInputStream f = null;
        Scanner s = null;
        try
        {
            f= new FileInputStream(IssuesPath);
            s = new Scanner(f);
            // Buffer object to act as the current line pointer
            String[] Line;
            // the next four objects are buffer objects of the data members of the Issues class
            int ProcedureID;
            int BookID;
            int StudentID;
            boolean isReturned;
            // Checks if the file is empty (which invokes the same behaviour of a missing file)
            if (f.available()==0)
                throw new FileNotFoundException(); 
            // Check if the file is done yet 
            while(s.hasNextLine())
            {
                // Read a row at a time from the csv
                // each cell contains a member of the Admin class where the cells are always ordered
                Line = s.nextLine().trim().split(",");
                ProcedureID = Integer.parseInt(Line[0].trim());
                BookID = Integer.parseInt(Line[1].trim());
                StudentID = Integer.parseInt(Line[2].trim());
                isReturned = Line[3].trim().equals("true");
                IssueLogger.AddIssue(new Issue(ProcedureID,BookID,StudentID,isReturned));
            }
        }
        // Exception handling if file doesn't exist or is empty
        catch(FileNotFoundException e)
        {
            // Create an empty file that should be fill by the librarian 
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
        // Catches Permession errors
        catch (IOException e)
        {
            System.out.println(e);
        }
        // Close the file if opened
        finally
        {
            if (s!=null)
                s.close();
        }
    }
    /**
     * Writes the contente of the static Issues List to the path
     * denoted to by IssuesPath.
     * This method must be called after calling the LoadIssueTable(String IssuesPath) at least once
     * in the program runtime to insure working without errors
     */
    public static void WriteIssueTable(String IssuesPath)
    {
        FileWriter f = null;
        try
        {
            // Create a new file writer object which expects the file to always be there
            // This method cannot be called before the LoadIssuesTable method has been called at least once 
            f= new FileWriter(IssuesPath);
            // Create a mutable object to act as a buffer for data storage
            StringBuffer buffer= new StringBuffer();
            // For each loop that writes the Issues list contents to the buffer
            for(Issue Is : IssueLogger.getIssueTable())
            {
                buffer.append(Integer.toString(Is.getProcedure_id())+", "+Integer.toString(Is.getBook_ID())+" ,"+Integer.toString(Is.getStudent_ID())+" ,"+Boolean.toString(Is.isReturned())+"\n");
            }
            // Convert the String buffer to a String and write it on the file
            f.write(buffer.toString());
            f.close();
        }
        // Checks for permission errors
        catch (IOException e)
        {
            System.out.println(e);
        }
        // Gets invoked if there was an error reading from Issues Table
        catch (NullPointerException e)
        {
            System.out.println("Issues Table Is null program crashed unexcepectedly");
        }
    }
    /**  Opens the file denoted to by the StudentPath string and reads the 
    * file contents and constructrs the static Student HashMap from the contents 
    */
    public static void LoadStudentTable( String StudentPath)
    {
        FileInputStream f = null;
        Scanner s = null;
        try
        {
            f= new FileInputStream(StudentPath);
            s = new Scanner(f);
            // Buffer object to act as the current line pointer
            String[] Lines;
            // the next five objects are buffer objects of the data members of the Student class
            int StudentID;
            String StudentName;
            String StudentBDay;
            String StudentEmail;
            String StudentPhoneNo;
            // Checks if the file is empty (which invokes the same behaviour of a missing file)
            if (f.available()==0)
                throw new FileNotFoundException(); 
            // Check if the file is done yet 
            while(s.hasNextLine())
            {
                // Read a row at a time from the csv
                // each cell contains a member of the Book class where the cells are always ordered
                Lines = s.nextLine().trim().split(",");
                StudentID = Integer.parseInt(Lines[0]);
                StudentName = Lines[1].replace("_", " ").trim();
                StudentBDay = Lines[2].trim();
                StudentEmail = Lines[3].trim();
                StudentPhoneNo = Lines[4].trim();
                // Invoke the public method that creates a new Student object 
                Student.addStudent(new Student(StudentID, StudentName, StudentBDay, StudentEmail, StudentPhoneNo));
            }
        }
        // Exception handling if file doesn't exist or is empty

        catch(FileNotFoundException e)
        {
            // Creates an empty file that should be filled by the admin clas
            // during program runtime
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
        // Catches permission errors
        catch (IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            // Close the file if it was opened
            if (s!=null)
                s.close();
        }

    }
    /**
     * Writes the contente of the static Student HashMap to the path
     * denoted to by StudentPath.
     * This method must be called after calling the LoadStudentTable(String StudentPath) at least once
     * in the program runtime to insure working without errors
     */
    public static void WriteStudentTable( String StudentPath)
    {
        FileWriter f = null;
        try
        {
            // Create a new file writer object which expects the file to always be there
            // This method cannot be called before the LoadStudentTable method has been called at least once 
            f= new FileWriter(StudentPath);
            // Create a mutable object to act as a buffer for data storage
            StringBuffer buffer= new StringBuffer();
            // For each loop that writes the Student list contents to the buffer
            for(Student St : Student.getStudents().values())
            {
                buffer.append(Integer.toString(St.getID())+", "+St.getName().replace(" ", "_")+" ,"+St.getBDay()+" ,"+St.getEmail()+" ,"+St.getPhoneNumber()+"\n");
            }
            // Convert the String buffer to a String and write it on the file
            f.write(buffer.toString());
            f.close();
        }
        // Checks for permission errors
        catch (IOException e)
        {
            System.out.println(e);
        }
        // Gets invoked if there was an error reading from Admin Table
        catch (NullPointerException e)
        {
            System.out.println("Students Table Is null program crashed unexcepectedly");
        }
    }
    // Created a private default constructor to 
    // prevent creating more DataLoader objects
    private DataLoader()
    {

    }
}
