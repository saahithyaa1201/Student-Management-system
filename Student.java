class Student {
    // Variable to store the student ID
    private String studentID;
    // Variable to store the student name
    private String studentName;
    // Variable to store the modules object for the student
    private Module modules;

    // Constructor that takes studentID and studentName as parameters
    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }

    // Constructor that takes studentID, studentName, and modules as parameters
    public Student(String studentID, String studentName, Module modules) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.modules = modules;
    }

    // Getter method for studentID
    public String getStudentID() {
        return studentID;
    }

    // Getter method for studentName
    public String getStudentName() {
        return studentName;
    }

    // Getter method for modules
    public Module getModules() {
        return modules;
    }

    // Override the toString method to provide a string representation of the student
    @Override
    public String toString() {
        return studentID + "," + studentName + (modules != null ? "," + modules : "");
    }
}
