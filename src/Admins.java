import java.util.*;

public class Admins
{
    // Data Attributes
    private int adminID;
    private String adminName;
    private String adminPassword;

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

    
}
