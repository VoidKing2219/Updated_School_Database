import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDatabase {
    final static String url = "jdbc:mysql://localhost:3306/School";
    final static String user = "root";
    final static String password = "GoroV2219"; // enter password depending to your password
    static {
    try (Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement()) {
        String createTableStudent = """
            CREATE TABLE IF NOT EXISTS Student (
            ID INT AUTO_INCREMENT PRIMARY KEY,
            SID VARCHAR(255),
            Name VARCHAR(255) NOT NULL,
            Age INT NOT NULL,
            Program VARCHAR(255) NOT NULL,
            Year_Level VARCHAR(255) NOT NULL,
            Net_Balance DOUBLE NOT NULL
            );
        """;
        stmt.executeUpdate(createTableStudent);
    } catch (SQLException e) {
        System.out.println("Error initializing database: " + e.getMessage());
    }
    }

    public void RegisterStudent(String name, Student student){
        String sql = "INSERT INTO Student(Name, Age, Program, Year_Level, Net_Balance) VALUES(?, ?, ?, ?, ?);";
        String updateSID = """
        UPDATE Student
        SET SID = CONCAT('SID_00',ID)
        WHERE Name = ?;
        """;
        try (Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getProgram());
            ps.setString(4, student.getYearLevel());
            ps.setDouble(5, student.getNetBalance());
            ps.executeUpdate();

            PreparedStatement updatePID = conn.prepareStatement(updateSID);
            updatePID.setString(1, name);
            updatePID.executeUpdate();
            System.out.println("Student registerd successfully");
        } 
        catch (SQLException e) {
            System.out.println("Student register Error: " + e.getMessage());
        }
    }
    public void SeachStudent(String SID){
        String sql = """
                SELECT ID, Name, Age, Program, Year_Level, Net_Balance FROM Student
                WHERE SID = ?;
                """;
        try (Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, SID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                String program = rs.getString("Program");
                String yl = rs.getString("Year_Level");
                double nb = rs.getDouble("Net_Balance");

                System.out.println("   STI Student information");
                System.out.println("Name:         | " + name);
                System.out.println("Age:          | " + age);
                System.out.println("--------------------------------------");
                System.out.println("ID:           | " + id);
                System.out.println("Program:      | " + program);
                System.out.println("Year_Level:   | " + yl);
                System.out.println("--------------------------------------");
                System.out.println("Net_Balance:  | " + nb);
            }
        }
         catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(String name, int age, String program, String yearLevel, String sid){
        String update = """
        UPDATE Student
        SET Name = ?, Age = ?, Program = ?, Year_Level = ?
        WHERE SID = ?;
        """;
        try {Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = conn.prepareStatement(update);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, program);
            ps.setString(4, yearLevel);
            ps.setString(5, sid);
            ps.executeUpdate();
            System.out.println("Student updated successfully");
        } catch (SQLException e) {
            System.out.println("Student update Error: " + e.getMessage());
        }
    }
    
    public void displayBal(String sid){
        String dispayBalance = """
            SELECT Net_Balance FROM Student
            WHERE SID = ?;
            """;
        try {Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(dispayBalance);
            ps.setString(1, sid);
            ResultSet resultSet = ps.executeQuery();
            //this will display current balance
            if (resultSet.next()){
                double nb = resultSet.getDouble("Net_Balance");
                System.out.println("Net_Balance:  | " + nb);
            }
            
        } catch (Exception e) {
            System.out.println("Display balance Error: " + e.getStackTrace());
        }
    }

    public void updateStudentBalance(char operation, String sid, double balance ){
        String minus = """
                UPDATE Student
                SET Net_Balance = Net_Balance - ?
                WHERE SID = ?;
                """;
        String add = """
                UPDATE Student
                SET Net_Balance = Net_Balance + ?
                WHERE SID = ?;
                """;
        try {Connection conn = DriverManager.getConnection(url, user, password);
            if (operation == 'M'){
                PreparedStatement Minus = conn.prepareStatement(minus);
                Minus.setDouble(1, balance);
                Minus.setString(2, sid);
                Minus.executeUpdate();
                System.out.println("Balance has been Deducted");
            } else if (operation == 'A'){
                PreparedStatement Add = conn.prepareStatement(add);
                Add.setDouble(1, balance);
                Add.setString(2, sid);
                Add.executeUpdate();
                System.out.println("Balance has been Added");
            }    
        } catch (Exception e) {
            System.out.println("Balance error: " + e.getMessage());
        }
    }

    public void deleteStudent(String sid){
        String delete = """
                DELETE FROM Student
                WHERE SID = ?;
                """;
                try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = conn.prepareStatement(delete)){

                    ps.setString(1, sid);
                    ps.executeUpdate();
                    System.out.println("Student deleted successfully");
                } 
                catch (SQLException e) {
                    System.out.println("Student deletion Error: " + e.getMessage());
                }
    }
}
