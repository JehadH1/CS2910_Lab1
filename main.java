import java.util.*;
import java.io.*;

public class main {

    @SuppressWarnings({ "resource", "unchecked", "unlikely-arg-type" })
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        Scanner inputFStudent = new Scanner(new File("/home/jehad/Desktop/CS2910/Lab1/students.csv"));
        Scanner inputFCourses = new Scanner(new File("/home/jehad/Desktop/CS2910/Lab1/courses.csv"));
        Scanner inputFGrades = new Scanner(new File("/home/jehad/Desktop/CS2910/Lab1/grades.csv"));

        // List to store student data
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();

        while (inputFStudent.hasNextLine()) {
            String line = inputFStudent.nextLine(); // Read a line
            String[] parts = line.split(";"); // Split line by semicolon

            if (parts.length == 5) {
                String id = parts[0];
                String lastName = parts[1];
                String firstName = parts[2];
                String phoneNum = parts[3];
                String email = parts[4];
                students.add(new Student(id, lastName, firstName, phoneNum, email));
            } else {
                System.out.println("Invalid line format: " + line);
            }
        }

        while (inputFGrades.hasNextLine()) {
            String line = inputFGrades.nextLine(); // Read a line
            String[] parts = line.split(";"); // Split line by semicolon

            if (parts.length >= 4) {
                String id = parts[0];
                ArrayList<Integer> grades = new ArrayList<>();
                for (int i = 3; i < parts.length; i++) {
                    if (parts[i].equals("na")) {
                        grades.add(-1);
                    } else {
                        grades.add(Integer.parseInt(parts[i].trim()));
                    }
                }
                for (Student student : students) {
                    if (student.id.equals(id)) {
                        student.setGrades(grades);
                        break;
                    }
                }
            } else {
                System.out.println("Invalid line format: " + line);
            }
        }
        int codeTracker = 0;
        while (inputFCourses.hasNextLine()) {
            String line = inputFCourses.nextLine();
            String[] parts = line.split(";");

            if (parts.length == 3) {
                String courseName = parts[0];
                String semester = parts[1];
                String code = parts[2];
                if (Integer.parseInt(code) > codeTracker) {
                    codeTracker = Integer.parseInt(code);
                }
                courses.add(new Course(courseName, semester, code));
            } else {
                System.out.println("Invalid line format: " + line);
            }
        }

        boolean state = true;
        int choice;

        while (state) {
            System.out.println("What are you looking for ?");

            // Q1
            System.out.println("1) output original list of all students");

            // Q2
            System.out.println("2) output list of all students sorted by name (in alphabetic order and vice versa)");

            // Q3
            System.out.println("3) output original list of all courses");

            // Q4
            System.out.println("4) output list of all courses for the specific semester");

            // Q5
            System.out.println(
                    "5) output list of all courses for the specific semester sorted by course name (in alphabetic order and vice versa)");
            // Q6
            System.out.println("6) add new student, add new course, add a grade for the course");

            // Q7
            System.out.println("7) update student info (user enters student id)");

            // Q8
            System.out.println("8) search for course by name");

            // Q9
            System.out.println("9) search for course by code");

            // Q10
            System.out.println("10) search for student info by last name");

            // Q11
            System.out.println("11) search for student info by phone number");

            // Q12
            System.out.println(
                    "12) output list of all courses taken by student (user enters student last name) and grades");
            // Q13
            System.out.println(
                    "13) output list of all courses taken by student (user enters student last name) and grades");

            // Q14
            System.out.println("14) calculate average grade for a specific student (user enters student last name)");

            // Q15
            System.out.println(
                    "15) calculate the average grade for a specific student for a specific term (user enters student's last name and term)");

            // Q16
            System.out.println(
                    "16) calculate average grade for specific courses (user enters course name)");
            System.out.println("0) Exit");

            System.out.println();
            System.out.print("Please enter the number you want to access: ");
            choice = input.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    for (Student student : students) {
                        System.out.println(student);
                    }
                    System.out.println();
                    break;

                case 2:
                    Vector<String> first_last_name = new Vector<>();
                    for (Student student : students) {
                        first_last_name.add(student.firstName + " " + student.lastName);
                    }
                    Collections.sort(first_last_name);
                    System.out.println("alphabetical order");
                    for (String name : first_last_name) {
                        System.out.println(name);
                    }
                    System.out.println();
                    System.out.println("reverse alphabetical order");
                    for (int i = first_last_name.size() - 1; i >= 0; i--) {
                        System.out.println(first_last_name.get(i));
                    }
                    System.out.println();
                    break;

                case 3:
                    courses.sort((a, b) -> {
                        return (a.courseName).compareTo(b.courseName);
                    });
                    for (Course course : courses) {
                        System.out.println(course);
                    }
                    System.out.println();
                    break;

                case 4:
                    courses.sort((a, b) -> {
                        return (a.courseName).compareTo(b.courseName);
                    });
                    System.out.println("What semester do you wanna look for?");
                    System.out.print("You have these options fall, winter, spring or summer: ");
                    String semester = input.next();
                    System.out.println();
                    if (semester.equals("fall") || semester.equals("winter")
                            || semester.equals("spring") | semester.equals("summer")) {
                        for (Course course : courses) {
                            if (course.semester.equals(semester)) {
                                System.out.println(course);
                            }
                        }
                    } else {
                        System.out.println("sorry could not find the semester you want");
                    }
                    System.out.println();
                    break;

                case 5:
                    courses.sort((a, b) -> {
                        return (a.courseName).compareTo(b.courseName);
                    });
                    System.out.println("What semester do you wanna look for?");
                    System.out.print("You have these options fall, winter, spring or summer: ");
                    semester = input.next();
                    System.out.println();
                    if (semester.equals("fall") || semester.equals("winter") || semester.equals("spring")
                            || semester.equals("summer")) {

                        System.out.println("alphabetical order");
                        for (Course course : courses) {
                            if (course.semester.equals(semester)) {
                                System.out.println(course);
                            }
                        }
                        System.out.println();
                        System.out.println("reverse alphabetical order");
                        for (int i = courses.size() - 1; i >= 0; i--) {
                            if (courses.get(i).semester.equals(semester)) {
                                System.out.println(courses.get(i));
                            }
                        }
                        System.out.println();
                    } else {
                        System.out.println("sorry could not find the semester you want");
                    }
                    System.out.println();
                    break;
                case 6:
                    System.out.println("What do you want to add?");
                    System.out.println("1) New Student");
                    System.out.println("2) New Course");
                    System.out.println("3) New Grade");
                    System.out.println();
                    System.out.print("Please enter the number you want to access: ");
                    choice = input.nextInt();
                    System.out.println();

                    switch (choice) {
                        case 1:
                            String id = "0", lastName = "Doe", firstName = "John", phoneNum = "XXX-XXX-XXXX",
                                    email = " ";
                            boolean idState = false;
                            System.out.print("Enter a id: ");
                            while (!idState) {
                                id = input.next();
                                idState = true;

                                for (Student student : students) {
                                    if ((student.id.equals(id))) {
                                        idState = false;
                                        System.out.print("ID already exists. Please enter a different ID: ");
                                        break;
                                    }

                                }
                            }
                            System.out.print("Enter first name: ");
                            firstName = input.next();
                            System.out.print("Enter last name: ");
                            lastName = input.next();
                            System.out.print("Enter phone number: ");
                            phoneNum = input.next();
                            System.out.print("Enter email: ");
                            email = input.next();
                            students.add(new Student(id, lastName, firstName, phoneNum, email));
                            for (int i = 0; i < codeTracker; i++) {
                                students.getLast().addGrade(-1);
                            }
                            System.out.println("New student added successfully!");
                            System.out.println();
                            break;
                        case 2:
                            String courseName = "NA", code = "NA";
                            semester = "NA";
                            boolean isValidCourse = false;

                            while (!isValidCourse) {
                                System.out.print("Enter a Course Name: ");
                                courseName = input.next();
                                System.out.print("Enter Semester it's in: ");
                                semester = input.next();

                                // Check for duplicates
                                for (Course course : courses) {
                                    if (course.courseName.equals(courseName) && course.semester.equals(semester)) {
                                        isValidCourse = true;
                                        System.out.print(
                                                "Course name with the same semester already exists. \nPlease enter a different course name or semester: ");
                                        break;
                                    }

                                }

                                if (!isValidCourse) {
                                    isValidCourse = true;
                                }
                            }
                            codeTracker++;
                            code = "" + (codeTracker);

                            // Add the course once validation passes
                            courses.add(new Course(courseName, semester, code));
                            System.out.println("New Course added successfully!");
                            for (Student student : students) {
                                student.addGrade(-1);
                            }
                            System.out.println();
                            break;
                        case 3:
                            boolean IdFound = false;
                            System.out.print("Enter Student ID for Student you want to change grade for: ");
                            while (!IdFound) {
                                String studentId = input.next();
                                for (Student student : students) {
                                    if (student.id.equals(studentId)) {
                                        System.out.println("Student Found");
                                        System.out.println();

                                        System.out.println(
                                                "What class do you want to change the grade for (list provided)");
                                        for (Course course : courses) {
                                            System.out.println(course);
                                        }
                                        System.out.println();
                                        System.out.print("Enter code here: ");
                                        int index = input.nextInt();
                                        index--;
                                        System.out.println();
                                        System.out.print("What is the changed grade? (0-100) ");
                                        int newGrade = input.nextInt();
                                        System.out.println();

                                        student.changeGrades(index, newGrade);
                                        System.out.println();
                                        IdFound = true;
                                        break;
                                    }

                                }
                            }
                            if (!IdFound) {
                                System.out.println("ID not found. Please try again.");
                                System.out.println();
                                break;
                            }

                    }

                    break;
                case 7:
                    boolean found = false;
                    while (!found) {
                        System.out.print("Which student do you want to look for? enter Id: ");
                        String studentId = input.next();
                        for (Student student : students) {
                            if (student.id.equals(studentId)) {
                                System.out.println("Student Found");
                                System.out.println();

                                System.out.println("What do you want to change?");
                                System.out.print(
                                        "ID, Last_Name, First_Name, Phone_Number or Email (enter the first word as seen) ");
                                String changeOption = input.next();
                                System.out.println();
                                String Changed;
                                if (changeOption.equals("ID")) {
                                    System.out.print("Enter new change for ID: ");
                                    Changed = input.next();
                                    student.id = Changed;
                                } else if (changeOption.equals("Last_Name")) {
                                    System.out.print("Enter new change for Last Name: ");
                                    Changed = input.next();
                                    student.lastName = Changed;
                                } else if (changeOption.equals("First_Name")) {
                                    System.out.print("Enter new change for First Name: ");
                                    Changed = input.next();
                                    student.firstName = Changed;
                                } else if (changeOption.equals("Phone_Number")) {
                                    System.out.print("Enter new change for Phone number: ");
                                    Changed = input.next();
                                    student.phoneNum = Changed;
                                } else if (changeOption.equals("Email")) {
                                    System.out
                                            .print("Enter new change for email make sure to have the address aswell: ");
                                    Changed = input.next();
                                    student.email = Changed;
                                } else {
                                    System.out.println("You have messed up start from the start please");
                                    System.out.println();
                                }
                                System.out.println();
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("ID not found. Please try again.");
                            System.out.println();
                            break;
                        }
                    }
                    break;
                case 8:
                    boolean courseFound = false;
                    while (!courseFound) {
                        System.out.print("What Course are you looking for? (enter the name): ");
                        String courseCode = input.next();
                        for (Course course : courses) {
                            if (course.courseName.equals(courseCode)) {
                                System.out.println(course);
                                courseFound = true;
                            }
                        }

                        if (!courseFound) {
                            System.out.println("Course not found by name. Please try again.");
                            System.out.println();
                            break;
                        }
                        System.out.println();
                    }
                    break;
                case 9:
                    boolean codeFound = false;
                    while (!codeFound) {
                        System.out.print("What Course are you looking for? (enter the code): ");
                        String courseCode = input.next();
                        for (Course course : courses) {
                            if (course.code.equals(courseCode)) {
                                System.out.println(course);
                                codeFound = true;
                            }
                        }

                        if (!codeFound) {
                            System.out.println("Course not found by code. Please try again.");
                            System.out.println();
                            break;
                        }
                        System.out.println();
                    }
                    break;
                case 10:
                    System.out.print("Which students are you looking for? (enter last name) ");
                    String lastName = input.next();
                    System.out.println();
                    for (Student student : students) {
                        if (student.lastName.equals(lastName)) {
                            System.out.println(student.fullString());
                        }
                    }
                    System.out.println();
                    break;

                case 11:
                    System.out.print("Which students are you looking for? (enter phone number) ");
                    String phoneNum = input.next();
                    System.out.println();
                    for (Student student : students) {
                        if (student.phoneNum.equals(phoneNum)) {
                            System.out.println(student.fullString());
                        }
                    }
                    System.out.println();
                    break;
                case 12:
                case 13:
                    System.out.print("Which students are you looking for? (enter last name) ");
                    lastName = input.next();
                    System.out.println();
                    for (Student student : students) {
                        int classTracker = student.getGrades().size();
                        if (student.lastName.equals(lastName)) {
                            System.out.println(student);
                            System.out.println();
                            ArrayList<Integer> outputGrades = (ArrayList<Integer>) student.getGrades().clone();
                            for (int i = 0; i < outputGrades.size(); i++) {
                                if (!(outputGrades.get(i).equals(-1))) {
                                    System.out.print(courses.get(i).stringClass());
                                    System.out.println("Grade: " + outputGrades.get(i));
                                } else {
                                    classTracker--;
                                }
                            }
                            if (classTracker == 0) {
                                System.out.println("You have no classes");
                            }
                            System.out.println();
                        }
                    }
                    System.out.println();
                    break;
                case 14:
                    System.out.print("Which students are you looking for? (enter last name) ");
                    lastName = input.next();
                    System.out.println();
                    for (Student student : students) {
                        int ave = 0;
                        int numberOfClasses = 0;
                        if (student.lastName.equals(lastName)) {

                            System.out.print(student.lastName + " " + student.firstName);
                            ArrayList<Integer> outputGrades = (ArrayList<Integer>) student.getGrades().clone();
                            if (!(student.getGrades().equals(-1))) {
                                for (int i = 0; i < outputGrades.size(); i++) {
                                    if (!(outputGrades.get(i).equals(-1))) {
                                        ave += outputGrades.get(i);
                                        numberOfClasses++;
                                    }
                                }
                            }

                            if (numberOfClasses != 0) {
                                ave = ave / numberOfClasses;
                                System.out.println(" Overall Average = " + ave);
                                System.out.println();
                            } else {
                                System.out.println();
                                System.out.println("You have no classes to find an average for");
                            }
                        }
                    }
                    System.out.println();
                    break;
                case 15:
                    System.out.print("Which students are you looking for? (enter last name) ");
                    lastName = input.next();
                    System.out.println();
                    System.out.println("Which semester is this for?");
                    semester = input.next();
                    for(Student student:students){
                        if(student.lastName.equals(lastName)){
                            
                        }
                    }
                    break;
                case 16:
                    break;
                case 0:
                    state = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        }

    }
}

// Define a Student class to represent each student's data
class Student {
    public String id;
    public String lastName;
    public String firstName;
    public String phoneNum;
    public String email;
    private ArrayList<Integer> grades;

    public Student(String id, String lastName, String firstName, String phoneNum, String email) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.grades = new ArrayList<>();
    }

    public void setGrades(ArrayList<Integer> grades) {
        this.grades = grades;
    }

    public ArrayList<Integer> getGrades() {
        return this.grades;
    }

    public void addGrade(int num) {
        this.grades.add(num);
    }

    public Integer changeGrades(int index, int grade) {
        return this.grades.set(index, grade);
    }

    public String fullString() {
        String gradesStr = grades.toString();
        return String.format("ID: %-10s | Name: %-15s %-15s | Phone: %-15s | Email: %-30s | Grades: %s",
                id, lastName, firstName, phoneNum, email, gradesStr);
    }

    @Override
    public String toString() {
        return String.format("ID: %-10s | Name: %-15s %-15s | Phone: %-15s | Email: %-30s",
                id, lastName, firstName, phoneNum, email);
    }
}

class Course {
    public String courseName;
    public String code;
    public String semester;

    public Course(String courseName, String semester, String code) {
        this.courseName = courseName;
        this.code = code;
        this.semester = semester;
    }

    public String stringClass() {
        return String.format("Course Name: %-10s | | Semester: %-15s",
                courseName, semester);
    }

    @Override
    public String toString() {
        return String.format("Course Name: %-10s | | Semester: %-15s | Code: %-15s",
                courseName, semester, code);
    }

}