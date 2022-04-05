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
                    break;
                case 'l':
                    Librarian.LibrarianView(input);
                    break;
                case 'q':
                    break;
                default:
                    System.out.println("Please enter a valid choice.");
                    break;
            }
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