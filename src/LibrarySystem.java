import java.util.*;


public class LibrarySystem
{
    public static void mainView()
    {
        DataLoader.LoadAdminTable("Tables/admins.csv");
        DataLoader.LoadBookTable("Tables/books.csv");
        DataLoader.LoadIssueTable("Tables/issues.csv");
        DataLoader.LoadLibrarianTable("Tables/librarians.csv");
        DataLoader.LoadStudentTable("Tables/students.csv");
        System.out.println("Welcome to the Library System. \nIf you are an admin, please click \"a\", if you are a librarian, please click \"l\" to exit and save click \"q\"");
        Scanner input = new Scanner(System.in);
        char choice = input.next().charAt(0);
        switch(choice)
        {
            case 'a':
                Admin.main(null);
                break;

            case 'l':
                Librarian.LibrarianView();
                break;
            case 'q':
                break;
            default:
                System.out.println("Please enter a valid choice.");
                mainView();
        }
        input.close();
        DataLoader.WriteAdminTable("Tables/admins.csv");
        DataLoader.WriteBookTable("Tables/books.csv");
        DataLoader.WriteIssueTable("Tables/issues.csv");
        DataLoader.WriteLibrarianTable("Tables/librarians.csv");
        DataLoader.WriteStudentTable("Tables/students.csv");
    }

    public static void main(String[] args)
    {
        LibrarySystem.mainView();
    }
}