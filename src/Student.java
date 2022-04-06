import java.util.HashMap;

public class Student {
    /**
     * Static HashMap of all students contained managed by the admins
     * The students are hashed by their IDs
     */
    private static HashMap<Integer,Student> s_Students = new HashMap<Integer,Student>(); 
    /**
     * 
     * @return the HashMap of students
     */
    public static HashMap<Integer,Student> getStudents()
    {
        if(s_Students==null)
            return null;
        return s_Students;
    }
    /**
     * Adds a student object to the static student List
     * @param o_Student Object of type student
     */
    public static void addStudent(Student o_Student)
    {
        // Check if the hashmap is not initialized yet
        if(s_Students==null)
            s_Students = new HashMap<Integer,Student>();
        if(s_Students.get(o_Student.getID())!=null)
        {
            System.out.println("Duplicate ID, Cant have Multiple students with the same ID\nCouldn't Create Student Instance");
            return;
        }
        //checks if the Student Name is only in Latin letters 
        for(char l:o_Student.getName().toCharArray())
        {
            if(!Character.isAlphabetic(l)&&!Character.isWhitespace(l))
            {
                System.out.println("Name Invalid, Cant have non alphabetical characters in name\nCouldn't Create Student Instance");
                return;
            }
        }
        // Check if the Phone Number is only Digits
        for(char  l  : o_Student.getPhoneNumber().toCharArray())
        {
            if(!Character.isDigit(l))
            {
                System.out.println("Phone Number Invalid, Cant have non digit characters in phone number\nCouldn't Create Student Instance");
                return;
            }
        }
        // Adds the student to the HashMap
        s_Students.put(o_Student.getID(),o_Student);   
    }
    /**
     * Create a new object of student and adds it to the HashMap
     * @param o_ID ID of the New Student
     * @param o_Name Name of the New Student
     * @param o_BDay Birthday of the new Student
     * @param o_Email Email of the new Student
     * @param o_PhoneNumber Phoen number of the new Student
     */
    public static void addStudent(int o_ID, String o_Name, String o_BDay, String o_Email, String o_PhoneNumber)
    {
        // Null Check for the HashMap
        if(s_Students==null)
            s_Students = new HashMap<Integer,Student>();
    
        if(s_Students.get(o_ID)!=null)
        {
            System.out.println("Duplicate ID, Cant have Multiple students with the same ID\nCouldn't Create Student Instance");
            return;
        }
        // Check if the name is in Latin Alphabet 
        for(char l:o_Name.toCharArray())
        {
            if(!Character.isAlphabetic(l)&&!Character.isWhitespace(l))
            {
                System.out.println("Name Invalid, Cant have non alphabetical characters in name\nCouldn't Create Student Instance");
                return;
            }
        }
        // Check if the phone number is only Digits
        for(char  l  : o_PhoneNumber.toCharArray())
        {
            if(!Character.isDigit(l))
            {
                System.out.println("Phone Number Invalid, Cant have non digit characters in phone number\nCouldn't Create Student Instance");
                return;
            }
        }
        // Add the new student to the hashmap
        s_Students.put(o_ID,new Student(o_ID, o_Name, o_BDay, o_Email, o_PhoneNumber));   
    }
    /**
     *  Print All registered Students
    */
    public static void showStudents()
    {
        for(Student s :s_Students.values())
            System.out.println(s.toString());
    }
    /**
     * Returns the student object if it is in the HashMap
     * @param o_ID ID of the student
     * @return Student Object
     */
    public static Student getStudent(int o_ID)
    {
        if(s_Students==null)
            s_Students = new HashMap<Integer,Student>();
        return s_Students.get(o_ID);
    }
    /**
     * Deletes Student Instance from the HashMap
     * @param o_ID ID of the student to be removed
     */
    public static void deleteStudent(int o_ID)
    {
        if(s_Students==null)
            s_Students = new HashMap<Integer,Student>();
        s_Students.remove(o_ID);
    }
    //Setters and Getters
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
    // Public Constructor
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