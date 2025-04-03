class Module {
    //Array to hold marks for the modules
    private final int[] marks;
    //Array to hold the grade
    private String grade;

    //Constructor that takes marks and grade as parameters
    public Module(int[] marks, String grade) {
        this.marks = marks;
        this.grade = grade;
    }


    //Constructor that takes only marks as a parametere and calculator
    public Module(int[] marks) {
        this.marks = marks;
        calculateGrade();
    }
    //Getter method for the grade
    public String getGrade() {
        return grade;
    }


    //Method to print marks for each Module
    public void getMarks() {
        System.out.println("Module 1 marks: " + marks[0]);
        System.out.println("Module 2 marks: " + marks[1]);
        System.out.println("Module 3 marks: " + marks[2]);
    }

    //Private method to calculate the grade based on marks
    private void calculateGrade() {
        //Validate marks are between 0 to 100
        for (int mark : marks) {
            if (mark < 0 || mark > 100) {
                System.out.println("Marks should be between 0 and 100.");
                return;
            }
        }

        //Calculate total and average marks
        int total = marks[0] + marks[1] + marks[2];
        int average = total / 3;

        if (average >= 80) {
            this.grade = "Distinction";
        } else if (average >= 70) {
            this.grade = "Merit";
        } else if (average >= 40) {
            this.grade = "Pass";
        } else {
            this.grade = "Fail";
        }
    }

    //  Method to get the total marks for the modules
    public int getTotalMarks() {
        return marks[0] + marks[1] + marks[2];
    }

    //Method to get the average marks for the modules
    public int getAverageMarks() {
        return getTotalMarks() / 3;
    }

    //Method to check if the student has passed all modules
    public boolean hasPassedAllModules() {
        for (int mark : marks) {
            if (mark <= 40) {
                return false;
            }
        }
        return true;
    }
//Override the tostring method to provide a string represenatation of the modules
    @Override
    public String toString() {
        return marks[0] + "\t" + marks[1] + "\t" + marks[2] + "\t" + getTotalMarks() + "\t" + getAverageMarks() + "\t" + grade;
    }
}