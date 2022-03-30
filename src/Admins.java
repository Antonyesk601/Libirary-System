import java.util.*;

public class Admins
{
    // Data Attributes
    private int adminID;
    private String adminName;
    private String adminPassword;
    private int index = -1;

    private static ArrayList<Admins> adminList = new ArrayList<>();

    public Admins()
    {
    }

    public Admins(int ID, String name, String password)
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

    public void addAdmin(int ID, String name, String password)
    {
        if(this.checkName(name))
        {
            Admins newAdmin = new Admins(ID, name, password);
            adminList.add(newAdmin);
            index++;
        }
        else
        {
            System.out.println("Name Invalid, Cant have non alphabetical characters in name\nCouldn't Create Admin Instance");
        }
                
    }

    public void removeAdmin(int ID)
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

    public void displayList()
    {
        for (int i=0; i<=index; i++)
        {
            System.out.println(adminList.get(i).toString());
        }
    }

    public void addStudent(int ID, String name, String bDay, String email, String phoneNumber, String callerID)
    {
        Student.addStudent(ID, name, bDay, email, phoneNumber, callerID);
    }

    public void removeStudent(int ID)
    {
        Student.deleteStudent(ID);
    }

    public void addLibrarian(int ID,String name, String password,String callerID)
    {
        Librarian.addLibrarian(ID, name, password, callerID);
    }

    public void deleteLibrarian(int ID)
    {
        Librarian.deleteLibrarian(ID);
    }

    public boolean checkName(String name)
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
        Admins admin = new Admins();

        admin.addAdmin(2345, "Juli54a", "Lola@123452");
        admin.addAdmin(564, "Huda", "jasper@001");

        admin.displayList();

        admin.addStudent(234, "Daniel", "01/07/2007", "Daniel@hotmail.com", "1092834211", "Daniel NU");
    }
}
