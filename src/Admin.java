import java.util.*;

public class Admin
{
    // Data Attributes
    private int adminID;
    private String adminName;
    private String adminPassword;
    private static int index = -1;  //The index variable is used to keep track of how many books are in the list. 
                                    //It helps keep track of the elements in the list to help search, or iterate over the list.

    private static ArrayList<Admin> adminList = new ArrayList<>();
    public static ArrayList<Admin> getAdminList()
    {
        if(adminList==null)
            return null;
        return adminList;
    }
    // No argument constructor
    public Admin()
    {
    }

    // Parameterized constructor
    public Admin(int ID, String name, String password)
    {
        adminID = ID;
        adminName = name;
        adminPassword = password;
    }

    public int getAdminID()
    {
        return adminID;   
    }

    public String getAdminName()
    {
        return adminName;
    }

    public String getAdminPassword()
    {
        return adminPassword;
    }

    /*
        The add admin method takes the admin's data when called, creates a new object of the book and calls the parameterized constructor
        the new admin object is then added to the arrayList that stores Admin objects.
        Before adding the admin, the name is checked to make sure it does not contain any numbers using the checkName method that is defined later in the code.
    */
    public static boolean addAdmin(int ID, String name, String password)
    {
        if(Admin.checkName(name))
        {
            Admin newAdmin = new Admin(ID, name, password);
            adminList.add(newAdmin);
            index++;
            return true;
            //System.out.println("\nAdmin added!");
        }
        else
        {
            System.out.println("Name Invalid, Cant have non alphabetical characters in name\nCouldn't Create Admin Instance");
        }
        return false;     
    }

    /*
        The remove admin method takes the admin's ID that should be removed. It then searches the admin list using the for look and 
        compares the IDs to the entered ID. If it is found, the admin object is deleted from the list using the remove() built in method.
    */
    public static void removeAdmin(int ID)
    {
        int found = 0;
        for (int i=0; i<=index; i++)
        {
            if (adminList.get(i).adminID == ID)
            {
                found = 1;
                adminList.remove(i);
                index--;
                break;
            }
            else
            {
                continue;
            }
        }

        if (found == 0)
        {
            System.out.println("The ID entered cannot be found    ->    Please make sure the ID is correct and try again.");
        }
    }

    //Prints the admins in the list
    public static void displayList()
    {
        for (int i=0; i<=index; i++)
        {
            System.out.println(adminList.get(i).toString());
        }
    }

    //Takes the data of the student to be added, then calls the static addStudent method that is in the Student class.
    public static void addStudent(int ID, String name, String bDay, String email, String phoneNumber)
    {
        Student.addStudent(ID, name, bDay, email, phoneNumber);
    }

    //Takes the ID of the student to be deleted, then calls the static deleteStudent method that is in the Student class.
    public static void removeStudent(int ID)
    {
        Student.deleteStudent(ID);
    }

    //Takes the data of the librarian to be added, then calls the static addLibrarian method that is in the Librarian class.
    public static void addLibrarian(int ID,String name, String password)
    {
        Librarian.addLibrarian(ID, name, password);
    }

    //Takes the ID of the student to be deleted, then calls the static deleteLibrarian method that is in the Librarian class.
    public static void deleteLibrarian(int ID)
    {
        Librarian.deleteLibrarian(ID);
    }

    /*
        The checkName method checks whether or not the admin's name entered contains any numbers (hence, invalid), 
        using the isAlphabetic built in method, which returns true if the char entered is an alphabet. toCharArray is used to convert 
        the string entered to an array of characters, which assists in making sure that each character in the name is an alphabetic letter.
    */
    public static boolean checkName(String name)
    {
        for(char l:name.toCharArray())
        {
            if(!Character.isAlphabetic(l))
            {
                return false;
            }
        }
        return true;
    }

    /*
        The checkPassword method is used in the main view. The admin enters the password, and the adminList are searched for the 
        admin with the same password. If it is found, then the admin proceeds.
    */
    public boolean checkPassword(String password)
    {
        for (int i=0; i<=index; i++)
        {
            if (adminList.get(i).adminPassword.equals(password))
            {
                
                return true;
            }
            else
            {
                continue;
            }
        }
        return false;
    }

    /*
        the searchAdmin is a method mainly used in the main view to allow us to get the data of the admin when the password is entered
    */
    public static String getAdmin(String password)
    {
        for (int i=0; i<=index; i++)
        {
            if (adminList.get(i).adminPassword.equals(password))
            {
                return (adminList.get(i).toString());
            }
            else
            {
                continue;
            }
        }
        return null;
    }

    public static void adminView(Scanner input)
    {
        System.out.println("\t\t\t ----- Welcome to the Admin's view -----");

        System.out.print("Please enter your password to proceed: ");
        String passwordCheck = input.nextLine();

        Admin admin = new Admin();
        int choice =-1;
        if(admin.checkPassword(passwordCheck))
        {
            System.out.println("Welcome!    ->     "  +  Admin.getAdmin(passwordCheck));
            while(choice!=8)
            {
                System.out.println("Please choose on of the following options: \n 1- Add a new admin \n 2- Delete an admin \n 3- Add a new student \n 4- Delete a student \n 5- Add a new librarian \n 6- Delete a librarian \n 7- Display admin list \n 8- Exit");
                System.out.print("Please enter your choice ->  ");
                choice = Integer.parseInt(input.nextLine());
                switch(choice)
                {

                    case 1: 
                        System.out.println("\t\t*** Adding an admin ***");

                        System.out.print("Enter the new admin's name: ");
                        String name = input.nextLine();

                        System.out.print("\nEnter the new admin's ID: ");
                        int id = Integer.parseInt(input.nextLine());

                        System.out.print("\nEnter the new admin's Password: ");
                        String password = input.nextLine();

                        if (Admin.addAdmin(id, name, password))
                        {
                            System.out.println("Admin added!");
                        }
                        else
                        {
                            System.out.println("Could not add admin due to invalid data. Please check your data and try again later.");
                        }

                        
                        break;

                    case 2:
                        System.out.println("\t\t*** Deleting an admin ***");

                        System.out.print("Enter the admin's ID: ");
                        int idtoDelete = Integer.parseInt(input.nextLine());
                        
                        Admin.removeAdmin(idtoDelete);

                        System.out.println("\nAdmin deleted!");
                        break;

                    case 3:
                        System.out.println("\t\t*** Adding a student ***");

                        System.out.print("Enter the new student's name: ");
                        String nameST = input.nextLine();

                        System.out.print("Enter the new student's ID: ");
                        int idST = Integer.parseInt(input.nextLine());

                        System.out.print("Enter the new student's date of birth: ");
                        String birthDate = input.nextLine();

                        System.out.print("Enter the new student's email: ");
                        String email = input.nextLine();

                        System.out.print("Enter the new student's phone number: ");
                        String phone = input.nextLine();

                        addStudent(idST, nameST, birthDate, email, phone);

                        System.out.println("\nStudent added!");
                        break;

                    case 4:
                        System.out.println("\t\t*** Deleting a student ***");
                        System.out.print("Enter the student's ID: ");
                        int idSTtoDelete =Integer.parseInt(input.nextLine());
                        
                        Admin.removeStudent(idSTtoDelete);

                        System.out.println("\nStudent deleted!");
                        break;

                    case 5:
                        System.out.println("\t\t*** Adding a librarian ***");

                        System.out.print("Enter the new librarians's name: ");
                        String nameL = input.nextLine();

                        System.out.print("Enter the new librarian's ID: ");
                        int idL = Integer.parseInt(input.nextLine());

                        System.out.print("Enter the new librarian's password: ");
                        String passwordL = input.nextLine();

                        addLibrarian(idL, nameL, passwordL);

                        System.out.println("\nLibrarian added!");
                        break;

                    case 6:
                        System.out.println("\t\t*** Deleting a librarian ***");
                        System.out.print("Enter the librarian's ID: ");
                        int idLtoDelete = Integer.parseInt(input.nextLine());
                    
                        Admin.deleteLibrarian(idLtoDelete);

                        System.out.println("\nlibrarian deleted!");
                        adminView(input);
                        break;

                    case 7:
                        Admin.displayList();
                        break;

                    case 8:
                        System.out.println("Exiting Admin View....");
                        break;

                    default:
                        System.out.println("Please enter a valid choice");
                        break;
                } 
            }
        }
        else
        {
            System.out.println("\nThe password entered in incorrect, please check your password and try again.");
        }
    }

    public String toString()
    {
        return ("Admin ID: " + adminID + "\tAdmin name: " + adminName);
    }

    public static void main(String[] args)
    {
        Admin.addAdmin(2345, "Julia", "Lola@123452");
        Admin.addAdmin(564, "Eurydice", "Orpheus4Life");
        Admin.addAdmin(1212, "Jasper", "jasper@001");
        Admin.addAdmin(3656, "Rayan", "kiteBYE");
        Admin.addAdmin(1543, "Zayn", "doungher");
        Scanner input = new Scanner(System.in);
        Admin.adminView(input);

    }
}
