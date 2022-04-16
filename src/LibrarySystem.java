import java.util.*;


public class LibrarySystem
{
    public static void mainView()
    {
        DataLoader.LoadAdminTable("NormalTables/admins.csv");
        DataLoader.LoadBookTable("NormalTables/books.csv");
        DataLoader.LoadIssueTable("NormalTables/issues.csv");
        DataLoader.LoadLibrarianTable("NormalTables/librarians.csv");
        DataLoader.LoadStudentTable("NormalTables/students.csv");
        Scanner input = new Scanner(System.in);
        char choice ='\0';
        while(choice!='q')
        {
            System.out.println("Welcome to the Library System. \nIf you are an admin, please click \"a\", if you are a librarian, please click \"l\" to exit and save click \"q\"");
            choice = input.nextLine().charAt(0);
            switch(choice)
            {
                case 'a':
                    Admin.adminView(input);
                    saveChanges();
                    break;
                case 'l':
                    Librarian.LibrarianView(input);
                    saveChanges();
                    break;
                case 'q':
                    saveChanges();
                    break;
                default:
                    System.out.println("Please enter a valid choice.");
                    break;
            }
        }
        input.close();

    }
    /**
     * Calls all the WriteTables Methods from the DataLoader Class 
     * 
     */
    public static void saveChanges()
    {
        DataLoader.WriteAdminTable("NormalTables/admins.csv");
        DataLoader.WriteBookTable("NormalTables/books.csv");
        DataLoader.WriteIssueTable("NormalTables/issues.csv");
        DataLoader.WriteLibrarianTable("NormalTables/librarians.csv");
        DataLoader.WriteStudentTable("NormalTables/students.csv");
    }
    public static void main(String[] args)
    {
        LibrarySystem.mainView();
    }
}