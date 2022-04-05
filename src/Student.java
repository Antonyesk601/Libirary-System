import java.util.HashMap;

public class Student {
    private static HashMap<Integer,Student> s_Students = new HashMap<Integer,Student>(); 
    public static HashMap<Integer,Student> getStudents()
    {
        if(s_Students==null)
            return null;
        return s_Students;
    }
    public static void addStudent(Student o_Student)
    {
        //Check Librarian class for similar suggesstions 
        // 

        if(s_Students==null)
            s_Students = new HashMap<Integer,Student>();
        if(s_Students.get(o_Student.getID())!=null)
        {
            System.out.println("Duplicate ID, Cant have Multiple students with the same ID\nCouldn't Create Student Instance");
            return;
        }
        for(char l:o_Student.getName().toCharArray())
        {
            if(!Character.isAlphabetic(l))
            {
                System.out.println("Name Invalid, Cant have non alphabetical characters in name\nCouldn't Create Student Instance");
                return;
            }
        }
        for(char  l  : o_Student.getPhoneNumber().toCharArray())
        {
            if(!Character.isDigit(l))
            {
                System.out.println("Phone Number Invalid, Cant have non digit characters in phone number\nCouldn't Create Student Instance");
                return;
            }
        }
        s_Students.put(o_Student.getID(),o_Student);   
    }
    public static void addStudent(int o_ID, String o_Name, String o_BDay, String o_Email, String o_PhoneNumber)
    {
        //Check Librarian class for similar suggesstions 
        // 

        if(s_Students==null)
            s_Students = new HashMap<Integer,Student>();
        if(s_Students.get(o_ID)!=null)
        {
            System.out.println("Duplicate ID, Cant have Multiple students with the same ID\nCouldn't Create Student Instance");
            return;
        }
        for(char l:o_Name.toCharArray())
        {
            if(!Character.isAlphabetic(l))
            {
                System.out.println("Name Invalid, Cant have non alphabetical characters in name\nCouldn't Create Student Instance");
                return;
            }
        }
        for(char  l  : o_PhoneNumber.toCharArray())
        {
            if(!Character.isDigit(l))
            {
                System.out.println("Phone Number Invalid, Cant have non digit characters in phone number\nCouldn't Create Student Instance");
                return;
            }
        }
        s_Students.put(o_ID,new Student(o_ID, o_Name, o_BDay, o_Email, o_PhoneNumber));   
    }
    public static void showStudents()
    {

    }
    public static Student getStudent(int o_ID)
    {
        if(s_Students==null)
            s_Students = new HashMap<Integer,Student>();
        return s_Students.get(o_ID);
    }
    public static void deleteStudent(int o_ID)
    {
        if(s_Students==null)
            s_Students = new HashMap<Integer,Student>();
        s_Students.remove(o_ID);
    }
    public int getID() {
        return m_ID;
    }
    public void setID(int o_ID) {
        this.m_ID = o_ID;
    }
    public String getName() {
        return m_Name;
    }
    public void setName(String o_Name) {
        this.m_Name = o_Name;
    }
    public String getBDay() {
        return m_BDay;
    }
    public void setBDay(String o_BDay) {
        this.m_BDay = o_BDay;
    }
    public String getEmail() {
        return m_Email;
    }
    public void setEmail(String o_Email) {
        this.m_Email = o_Email;
    }
    public String getPhoneNumber() {
        return m_PhoneNumber;
    }
    public void setPhoneNumber(String o_PhoneNumber) {
        this.m_PhoneNumber = o_PhoneNumber;
    }
    public Student(int o_ID, String o_Name, String o_BDay, String o_Email, String o_PhoneNumber) {
        this.m_ID = o_ID;
        this.m_Name = o_Name;
        this.m_BDay = o_BDay;
        this.m_Email = o_Email;
        this.m_PhoneNumber = o_PhoneNumber;
    }

    private int m_ID;
    private String m_Name;
    private String m_BDay;
    private String m_Email;
    private String m_PhoneNumber;

    @Override
    public String toString() {
        return "Student Name:" + m_Name + ", Email: " + m_Email + ", ID: " + m_ID + ", Birthday: " + m_BDay+ ", Phone Number: " + m_PhoneNumber ;
    }
}