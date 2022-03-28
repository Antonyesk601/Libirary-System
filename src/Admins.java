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
        Admins newAdmin = new Admins(ID, name, password);
        adminList.add(newAdmin);
        index++;        
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

    public String toString()
    {
        return ("Admin ID: " + adminID + "\tAdmin name: " + adminName);
    }

    public static void main(String[] args)
    {
        Admins admin = new Admins();

        admin.addAdmin(2345, "Julia", "Lola@123452");
        admin.addAdmin(564, "Huda", "jasper@001");

        admin.displayList();
    }
}
