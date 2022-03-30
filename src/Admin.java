import java.util.*;

public class Admin
{
    // Data Attributes
    private int adminID;
    private String adminName;
    private String adminPassword;
    private static int index = -1;

    private static ArrayList<Admin> adminList = new ArrayList<>();

    public Admin()
    {
    }

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

    public static void addAdmin(int ID, String name, String password)
    {
        if(Admin.checkName(name))
        {
            Admin newAdmin = new Admin(ID, name, password);
            adminList.add(newAdmin);
            index++;
        }
        else
        {
            System.out.println("Name Invalid, Cant have non alphabetical characters in name\nCouldn't Create Admin Instance");
        }
                
    }

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

    public static void displayList()
    {
        for (int i=0; i<=index; i++)
        {
            System.out.println(adminList.get(i).toString());
        }
    }

    public static void addStudent(int ID, String name, String bDay, String email, String phoneNumber, String callerID)
    {
        Student.addStudent(ID, name, bDay, email, phoneNumber, callerID);
    }

    public static void removeStudent(int ID)
    {
        Student.deleteStudent(ID);
    }

    public static void addLibrarian(int ID,String name, String password,String callerID)
    {
        Librarian.addLibrarian(ID, name, password, callerID);
    }

    public static void deleteLibrarian(int ID)
    {
        Librarian.deleteLibrarian(ID);
    }

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

    public String toString()
    {
        return ("Admin ID: " + adminID + "\tAdmin name: " + adminName);
    }

    public static void main(String[] args)
    {
        Admin admin = new Admin();

        Admin.addAdmin(2345, "Juli54a", "Lola@123452");
        Admin.addAdmin(564, "Huda", "jasper@001");

        Admin.displayList();

        Admin.addStudent(234, "Daniel", "01/07/2007", "Daniel@hotmail.com", "1092834211", "Daniel NU");
    }
}
