import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


//Declares a public class named StudentManagement
public class StudentManagement {
    //Define a constant Max_student with value of 100
    private static final int Max_student = 100;
    //Create an array students that holds up to Student object
    private static final Student[] students = new Student[Max_student];
    //Initialize student_count to keep track of the number of registered student
    private static int student_count = 0;



    // Main method to start the program
    public static void main(String[] args) {
        //Creates a scanner object to read input from the console
        Scanner input = new Scanner(System.in);
        while (true) {
            //Calls the options method to display the menu option
            options();
            //calls the menu method to get users choice
            int choice = menu();
            System.out.println("You chose option " + choice);

            // Using switch statement to handle each choice
            switch (choice) {
                case 1:
                    //Calls the checkAvailable method to dislay available seats
                    checkAvailable_seats();
                    break;
                case 2:
                    //Calls the registerStudent method to register a new student
                    registerStudent();
                    break;
                case 3:
                    //Calls the deletestudent method to delete a student
                    deleteStudent();
                    break;
                case 4:
                    //Calls the findstudent method to find a student
                    findStudent();
                    break;
                case 5:
                    //Calls the storestudent details into file to store student details into file
                    storeStudentDetailsIntoFile();
                    break;
                case 6:
                    //call method to loaf student details
                    loadStudentDetails();
                    break;
                case 7:
                    //call method to view student
                    viewStudents();
                    break;
                case 8:
                    //initialize a boolean variable to control the loop for submenu selection
                    boolean continueSelection = true;
                    //Loop to handle submenu options
                    while (continueSelection) {
                        //Using error handling
                        try {
                            System.out.print("Select a sub option: ");
                            String subMenu = input.next();
                            //Handle the submenu options using a switch statement
                            switch (subMenu) {
                                case "a" -> studentResultsMenu();
                                case "b" -> System.out.println("Module marks are: " + Arrays.toString(module()));
                                case "c" -> total();
                                case "d" -> displayCompleteReport();
                                default -> System.out.println("Invalid option. Please select 'a,b,c,d'.");
                            }
                        } catch (Exception e) {
                            //Catch any exceptions that occure and print error message
                            System.out.println("An error occurred: " + e.getMessage());
                        }

                        System.out.print("Do you want to select another sub-option? (yes/no): ".toLowerCase());
                        //Read the user's response
                        String response = input.next();
                        //If the user say yes continue  set continue selection to false exit the loop
                        if (!response.equalsIgnoreCase("yes")) {
                            continueSelection = false;
                        }
                    }
                    break;


                case 9:
                    //Exit the program
                    System.out.println("Thank you for choosing Student Management System (SMS)...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    /*
    @Student class parameters accessed:
        - studentID
        - studentName
        - modules

    @Module class parameters accessed:
        - marks
        - grade
*/



    // Method to display options
    public static void options() {
        System.out.println("""
                *** STUDENT MANAGEMENT SYSYTEM ***
                
                \t 1) Check available seats\040\040\040
                \t 2) Register student (with ID)\040
                \t 3) Delete student\040
                \t 4) Find student (with student ID)\040
                \t 5) Store student details into a file\040
                \t 6) Load student details from the file to the system\040
                \t 7) View the list of students based on their names\040
                \t 8) More options
                            a) Add Student with module marks
                            b)Module marks 1,2 and 3
                            c)Total students registrations
                            d)Complete report
                \t 9)Exit
                """);
    }


    // Method to get user's menu choice
    public static int menu() {
        Scanner scanner = new Scanner(System.in);
        //// initializing with an invalid value, loop will continue until a valid input.
        int menu_selection = -1;
        //Loop to ensure a valid choice
        while (menu_selection < 1 || menu_selection > 9) {
            //block to read and validate user's input
            try {
                System.out.print("\nSelect your option: ");
                menu_selection = scanner.nextInt();
                if (menu_selection < 1 || menu_selection > 9) {
                    System.out.println("Invalid option. Please enter a number between 1-8 according to your choice.");
                }
                //Handles invalid input
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // clear the invalid input
            }
        }
        return menu_selection;
    }


    // Method to check available seats
    public static void checkAvailable_seats() {
        int availableSeats = Max_student - student_count;
        System.out.println("Available seats: " + availableSeats);
    }


    // Method to register a student
    private static void registerStudent() {
        try {
            //check if seats are Available
            if (student_count >= Max_student) {
                System.out.println("No available seats.");
                return;
            }

            Scanner scan = new Scanner(System.in);
            System.out.print("Enter Student ID (8 characters starting with 'w'): ");
            String id = scan.nextLine();

            // Validate student ID format
            if (id.length() != 8 || !id.startsWith("w")) {
                System.out.println("Invalid input. Please enter a valid student ID.");
                return;
            }

            System.out.print("Enter Student Name: ");
            String name = scan.nextLine();
            // Validate that the name contains only letters and spaces
            if (!name.matches("[a-zA-Z\\s]+")) {
                System.out.println("Invalid input. Please enter a valid name with only letters.");
                return;
            }
            // Check if student ID already exists
            for (int i = 0; i < student_count; i++) {
                if (students[i].getStudentID().equals(id)) {
                    System.out.println("Student ID " + id + " already exists.");
                    return;
                }
            }
            // Create and add the new student
            // Assuming Student class has a constructor
            Student newStudent = new Student(id, name);
            students[student_count] = newStudent;
            student_count++;
            System.out.println("Student registered successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



    // Method to delete a student by ID
    private static void deleteStudent() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Student ID to delete: ");
        String id = scan.nextLine();
        //Block for exception handling
        try {
            boolean found = false;
            for (int i = 0; i < student_count; i++) {
                if (students[i].getStudentID().equals(id)) {
                    found = true;
                    // Shift elements to the left to delete the student
                    for (int j = i; j < student_count - 1; j++) {
                        students[j] = students[j + 1];
                    }
                    //Clear the last element
                    students[student_count - 1] = null;
                    System.out.println("Student deleted successfully.");
                    student_count--;
                    //Exit loop once student is deleted
                    break;
                }
            }
            //If student is not registered or when cannot find it
            if (!found) {
                throw new Exception("Student ID not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // Modify the findStudent() method
    private static void findStudent() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Student ID to find: ");
        String id = scan.nextLine();
        //Check if the student registered and can find
        try {
            boolean found = false;
            for (int i = 0; i < student_count; i++) {
                if (students[i].getStudentID().equals(id)) {
                    found = true;
                    System.out.println("Student Found ");
                    System.out.println("Student ID: " + students[i].getStudentID());
                    System.out.println("Student Name: " + students[i].getStudentName());
                    break;
                }
            }
            //If not found display error
            if (!found) {
                System.out.println("Error: Please enter valid student ID");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    // Method to store student details into a file
    private static void storeStudentDetailsIntoFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Records.txt"))) {
            for (int i = 0; i < student_count; i++) {
                writer.write(students[i].toString());
                writer.newLine();
            }
            System.out.println("Student details stored into file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }



    // Method to load student details from a file
    private static void loadStudentDetails() {
        //path to your file
        String filePath = "Records.txt"; // Path to your file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            student_count = 0; // Reset the student count
            while ((line = br.readLine()) != null) {
                String[] details = line.split(","); // Assuming comma is the delimiter
                if (details.length == 2) { // Ensure correct format
                    String id = details[0].trim();
                    String name = details[1].trim();
                    // Create Student object using ID and name constructor
                    students[student_count] = new Student(id, name);
                    student_count++;
                } else {
                    System.out.println("Invalid record: " + line);
                }
            }
            System.out.println("Student details loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }



    //Using bubble sorting for arranging student name in A-Z
    private static void bubbleSortStudentsByName() {
        //Declares a variable to track if any swapping occurs during the pass
        boolean swapped;
        //Outer loop to control the of passes over the array
        for (int i = 0; i < student_count - 1; i++) {
            swapped = false;
            //Each pass through array compares adjacent elements
            for (int j = 0; j < student_count - 1 - i; j++) {
                if (students[j].getStudentName().compareTo(students[j + 1].getStudentName()) > 0) {
                    //Swap students[j] and students[j+1]
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                    //Sets swapped to true
                    swapped = true;
                }
            }
            //Stop if no swap occurred,meaning the array is sorted
            if (!swapped) break;
        }
    }


    //Method to view the list of students based on their names
    public static void viewStudents() {
        //calling bubble sore student by name method to arrange the order
        bubbleSortStudentsByName();
        System.out.println("List of students sorted by name:");
        for (int i = 0; i < student_count; i++) {
            System.out.println(students[i].getStudentName());
        }
    }


    // Modify the module() method
    public static int[] module() {
        Scanner input = new Scanner(System.in);
        int[] moduleMarks = new int[3];
        // Loop to enter module marks
        for (int i = 0; i < 3; i++) {
            while (true) {
                try {
                    System.out.print("Enter module " + (i + 1) + " marks (0-100): ");
                    int marks = input.nextInt();
                    // Validate marks input
                    if (marks >= 0 && marks <= 100) {
                        moduleMarks[i] = marks;
                        //exit the loop for this module
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter marks between 0 and 100.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    input.next(); // clear the invalid input
                }
            }
        }
        //Return the array of module marks
        return moduleMarks;
    }


    // Separate the studentResultsMenu method definition
    private static void studentResultsMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter student ID:");
            String studentID = scanner.nextLine();

            // Validate the student ID
            if (studentID.length() != 8 || !studentID.startsWith("w")) {
                System.out.println("Invalid input. Please enter a valid student ID.");
                return;
            }
            System.out.println("Enter student name:");
            String studentName = scanner.nextLine();

            // Get module marks
            int[] moduleMarks = module();

            // Create a Module object
            Module module = new Module(moduleMarks);

            // Create a new Student object
            Student newStudent = new Student(studentID, studentName, module);

            // Add the new student to the students array
            if (student_count < Max_student) {
                students[student_count++] = newStudent;
                System.out.println("Student registered successfully.");
            } else {
                System.out.println("No available seats.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    // Fix the method call type in total()
    private static void total() {
        int studentsPassedAllModules = 0;
        for (int i = 0; i < student_count; i++) {
            if (students[i].getModules() != null && students[i].getModules().hasPassedAllModules()) {
                studentsPassedAllModules++;
            }
        }

        System.out.println("Total student registrations: " + student_count);
        System.out.println("Score more than 40 in all modules: " + studentsPassedAllModules);
    }


    //Method to display the complete report of student
    public static void displayCompleteReport() {
        // Get the sorted students array
        Student[] sortedStudents = getSortedStudentsByAverageMarksDescending();

        System.out.println("\n~~~ Complete Report Sorted by Average Marks (Descending) ~~~");
        //Iterate through each student in sorted array
        for (Student student : sortedStudents) {
            //Check if student has module data
            if (student.getModules() != null) {
                System.out.println("Student ID: " + student.getStudentID());
                System.out.println("Student Name: " + student.getStudentName());
                //get the students module data
                Module modules = student.getModules();
                modules.getMarks();

                //Print the total,Average,Grade
                System.out.println("Total: " + modules.getTotalMarks());
                System.out.println("Average: " + modules.getAverageMarks());
                System.out.println("Grade: " + modules.getGrade());
            }
        }
        }



        private static Student[] getSortedStudentsByAverageMarksDescending() {
            // Create a copy of the students array
            Student[] sortedStudents = Arrays.copyOf(students, student_count);

            // Bubble sort on the copied array
            boolean swapped;
            for (int i = 0; i < student_count - 1; i++) {
                swapped = false;
                for (int j = 0; j < student_count - 1 - i; j++) {
                    if (sortedStudents[j].getModules() != null && sortedStudents[j + 1].getModules() != null) {
                        if (sortedStudents[j].getModules().getAverageMarks() < sortedStudents[j + 1].getModules().getAverageMarks()) {
                            // Swap sortedStudents[j] and sortedStudents[j+1]
                            Student temp = sortedStudents[j];
                            sortedStudents[j] = sortedStudents[j + 1];
                            sortedStudents[j + 1] = temp;
                            swapped = true;
                        }
                    }
                }
                // Stop if no swap occurred, meaning the array is sorted
                if (!swapped) break;
            }
            return sortedStudents;
        }

    }





