import java.util.*;

public class LibrarySystem
{
    public static void mainView()
    {
        System.out.println("Welcome to the Library System. \nIf you are an admin, please click \"a\", if you are a librarian, please click \"l\"");
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

            default:
            System.out.println("Please enter a valid choice.");
            mainView();
        }
        input.close();
    }
    public static void main(String[] args)
    {
        LibrarySystem.mainView();
    }
}