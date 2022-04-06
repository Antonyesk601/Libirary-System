import java.util.*;


public class LibrarySystem
{
    public static void mainView()
    {
        DataLoader.LoadAdminTable("C:\\Users\\donia\\Desktop\\Libirary-System\\NormalTables\\admins.csv");
        DataLoader.LoadBookTable("C:\\Users\\donia\\Desktop\\Libirary-System\\NormalTables\\books.csv");
        DataLoader.LoadIssueTable("C:\\Users\\donia\\Desktop\\Libirary-System\\NormalTables\\issues.csv");
        DataLoader.LoadLibrarianTable("C:\\Users\\donia\\Desktop\\Libirary-System\\NormalTables\\librarians.csv");
        DataLoader.LoadStudentTable("C:\\Users\\donia\\Desktop\\Libirary-System\\NormalTables\\students.csv");
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
        DataLoader.WriteAdminTable("C:\\Users\\donia\\Desktop\\Libirary-System\\NormalTables\\admins.csv");
        DataLoader.WriteBookTable("C:\\Users\\donia\\Desktop\\Libirary-System\\NormalTables\\books.csv");
        DataLoader.WriteIssueTable("C:\\Users\\donia\\Desktop\\Libirary-System\\NormalTables\\issues.csv");
        DataLoader.WriteLibrarianTable("C:\\Users\\donia\\Desktop\\Libirary-System\\NormalTables\\librarians.csv");
        DataLoader.WriteStudentTable("C:\\Users\\donia\\Desktop\\Libirary-System\\NormalTables\\students.csv");
    }
    public static void main(String[] args)
    {
        LibrarySystem.mainView();
    }
}