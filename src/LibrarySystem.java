import java.util.*;


public class LibrarySystem
{
    public static void mainView()
    {
        DataLoader.LoadAdminTable("RandomTables/admins.csv");
        DataLoader.LoadBookTable("RandomTables/books.csv");
        DataLoader.LoadIssueTable("RandomTables/issues.csv");
        DataLoader.LoadLibrarianTable("RandomTables/librarians.csv");
        DataLoader.LoadStudentTable("RandomTables/students.csv");
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
    public static void saveChanges()
    {
        DataLoader.WriteAdminTable("RandomTables/admins.csv");
        DataLoader.WriteBookTable("RandomTables/books.csv");
        DataLoader.WriteIssueTable("RandomTables/issues.csv");
        DataLoader.WriteLibrarianTable("RandomTables/librarians.csv");
        DataLoader.WriteStudentTable("RandomTables/students.csv");
    }
    public static void main(String[] args)
    {
        LibrarySystem.mainView();
    }
}