import java.util.Scanner;

public class School {
    public static void main(String[] args) throws Exception {
        StudentDatabase sd = new StudentDatabase();
        TeacherDatabase td = new TeacherDatabase();
        Scanner input = new Scanner(System.in);

        while (true){
            System.out.printf("Welcome to STI ADMIN%n"
            + "Press S to search for a person%n"
            + "Press R to register a person%n"
            + "Press U to update a person%n"
            + "Press D to delete a person%n"
            + "Enter your option: ");
            char option = input.next().toUpperCase().charAt(0);
            switch(option){
                case 'S': 
                    System.out.printf("Who would you like to Search%n"
                        + "Press S for Student, T for Teacher: ");
                        input.nextLine();
                        char search = input.next().toUpperCase().charAt(0);
                        if (search == 'S') {
                            System.out.print("Enter Student ID: ");
                            input.nextLine();
                            String SID = input.nextLine();
                            sd.SeachStudent(SID);
                            System.out.println("");
                            continue;
                        } else if (search == 'T') {
                            System.out.print("Enter Teacher ID: ");
                            input.nextLine();
                            String TID = input.nextLine();
                            td.SeachTeacher(TID);
                            System.out.println("");
                            continue;
                        }
                case 'R':
                    System.out.printf("Who would you like to Register%n"
                        + "Press S for Student, T for Teacher: ");
                        input.nextLine();
                        char register = input.next().toUpperCase().charAt(0);
                        if (register == 'S'){
                            System.out.print("Enter name: ");
                            input.nextLine();
                            String name = input.nextLine();
                            System.out.print("Enter age: ");
                            int age = input.nextInt();
                            System.out.print("Enter program: ");
                            input.nextLine();
                            String program = input.nextLine();
                            System.out.print("Enter year level: ");
                            String yearLevel = input.nextLine();
                            System.out.print("Set net balance: ");
                            double netbalance = input.nextDouble();
                            sd.RegisterStudent(name, new Student(name, age, program, yearLevel, netbalance));
                            System.out.println("");
                            continue;
                        } else if (register == 'T') {
                            System.out.print("Enter name: ");
                            input.nextLine();
                            String name = input.nextLine();
                            System.out.print("Enter age: ");
                            int age = input.nextInt();
                            System.out.print("Enter subject area: ");
                            input.nextLine();
                            String subjectArea = input.nextLine();
                            System.out.print("Enter year of expirience: ");
                            int ye = input.nextInt();
                            td.RegisterTeacher(name, new Teacher(name, age, subjectArea, ye));
                            System.out.println("");
                            continue;
                        } else {
                        System.out.println("Input error");
                        System.exit(0);
                        }
                case 'U':
                    System.out.printf("Who would you like to Update%n"
                    + "Press S for Student, T for Teacher: ");
                    input.nextLine();
                    char update = input.next().toUpperCase().charAt(0);
                    if (update == 'S'){
                        System.out.print("Press I for student information, B for student balance: ");
                        input.nextLine();
                        char select = input.next().toUpperCase().charAt(0);
                        if (select == 'I'){
                            System.out.print("Enter Student ID to update: ");
                            input.nextLine();
                            String sid = input.nextLine();
                            System.out.print("Enter new name: ");
                            String name = input.nextLine();
                            System.out.print("Enter new age: ");
                            int age = input.nextInt();
                            System.out.print("Enter new program: ");
                            input.nextLine();
                            String program = input.nextLine();
                            System.out.print("Enter new year level: ");
                            String yearLevel = input.nextLine();
                            sd.updateStudent(name, age, program, yearLevel, sid);
                            System.out.println("");
                            continue;
                        } else if (select == 'B'){
                            System.out.print("Enter Student ID to update: ");
                            input.nextLine();
                            String sid = input.nextLine();
                            sd.displayBal(sid);
                            System.out.print("Press M to minus balance, A to add balance: ");
                            char operation = input.next().toUpperCase().charAt(0);
                            System.out.print("Enter Balance to Add/Minus: ");
                            double balance = input.nextDouble();
                            sd.updateStudentBalance(operation, sid, balance);
                            System.out.println("Student current status");
                            sd.SeachStudent(sid);
                            System.out.println("");
                            continue;
                        }
                        
                    } else if (update == 'T'){
                        System.out.print("Enter Teacher ID to update: ");
                        input.nextLine();
                        String sid = input.nextLine();
                        System.out.print("Enter new name: ");
                        String name = input.nextLine();
                        System.out.print("Enter new age: ");
                        int age = input.nextInt();
                        System.out.print("Enter new subject area: ");
                        input.nextLine();
                        String subjectArea = input.nextLine();
                        System.out.print("Enter new year of expirience: ");
                        int ye = input.nextInt();
                        td.updateTeacher(name, age, subjectArea, subjectArea, sid);
                        System.out.println("");
                        continue;
                    }
                case 'D':
                    System.out.printf("Who would you like to Delete%n"
                    + "Press S for Student, T for Teacher: ");
                    input.nextLine();
                    char delete = input.next().toUpperCase().charAt(0);
                    if (delete == 'S'){
                        System.out.print("Enter student ID to delete: ");
                        input.nextLine();
                        String sid = input.nextLine();
                        sd.deleteStudent(sid);
                        System.out.println("");
                        continue;
                    } else if (delete == 'T'){
                        System.out.print("Enter teacher ID to delete: ");
                        String tid = input.nextLine();
                        input.nextLine();
                        td.deleteTeacher(tid);
                        System.out.println("");
                        continue;
                    }
            }
        }
        
    }
}

abstract class Person{
    public abstract String getName();
    public abstract int getAge();
    public abstract int getId();
}

class Student extends Person {
    private String name;
    private int age;
    private int id;
    private String program;
    private String yearLvel;
    private double netBalance;

    Student( int id, String name, int age, String program, String yearLvel, double netBalance){
        this.name = name;
        this.age = age;
        this.id = id;
        this.program = program;
        this.yearLvel = yearLvel;
        this.netBalance = netBalance;
    }
    Student(String name, int age, String program, String yearLvel, double netBalance){
        this(0, name, age, program, yearLvel, netBalance);
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public int getId(){
        return id;
    }
    public String getProgram(){
        return program;
    }
    public String getYearLevel(){
        return yearLvel;
    }
    public double getNetBalance(){
        return netBalance;
    }
}

class Teacher extends Person {
    private String name;
    private int age;
    private int id;
    private String subjectArea; //example: specialize in math
    private int yearExp; // year experience : 4 years

    Teacher( int id, String name, int age, String subjectArea, int yearExp){
        this.name = name;
        this.age = age;
        this.id = id;
        this.subjectArea = subjectArea;
        this.yearExp = yearExp;

    }
    Teacher(String name, int age, String subjectArea, int yearExp){
        this(0, name, age, subjectArea, yearExp);
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public int getId(){
        return id;
    }
    public String getSubjectArea(){
        return subjectArea;
    }
    public int getYearExp(){
        return yearExp;
    }
}