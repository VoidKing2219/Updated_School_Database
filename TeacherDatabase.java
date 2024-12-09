import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TeacherDatabase {
    final static String url = "jdbc:mysql://localhost:3306/School";
    final static String user = "root";
    final static String password = "GoroV2219"; // enter password depending to your password
    static {
    try (Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement()) {
        String createTableTeacher = """
            CREATE TABLE IF NOT EXISTS Teacher (
            ID INT AUTO_INCREMENT PRIMARY KEY,
            TID VARCHAR(255),
            Name VARCHAR(255) NOT NULL,
            Age INT NOT NULL,
            Subject_Area VARCHAR(255) NOT NULL,
            Year_Expirience INT NOT NULL
            );
        """;
        stmt.executeUpdate(createTableTeacher);
    } catch (SQLException e) {
        System.out.println("Error initializing database: " + e.getMessage());
    }
    }

    public void RegisterTeacher(String name, Teacher teacher){
        String sql = "INSERT INTO Teacher(Name, Age, Subject_Area, Year_Expirience) VALUES(?, ?, ?, ?);";
        String updateTID = """
        UPDATE Teacher
        SET TID = CONCAT('TID_00',ID)
        WHERE Name = ?;
        """;
        try (Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, teacher.getName());
            ps.setInt(2, teacher.getAge());
            ps.setString(3, teacher.getSubjectArea());
            ps.setInt(4, teacher.getYearExp());
            ps.executeUpdate();

            PreparedStatement updatePID = conn.prepareStatement(updateTID);
            updatePID.setString(1, name);
            updatePID.executeUpdate();
            System.out.println("Teacher registerd successfully");
        } 
        catch (SQLException e) {
            System.out.println("Teacher register Error" + e.getMessage());
        }
    }

    public void SeachTeacher (String TID) {
        String sql = """
                SELECT ID, Name, Age, Subject_Area, Year_Expirience FROM Teacher
                WHERE TID = ?;
                """;
        try (Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, TID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int tid = rs.getInt("ID");
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                String sa = rs.getString("Subject_Area");
                int ye = rs.getInt("Year_Expirience");
               
                System.out.println("Name:            | " + name);
                System.out.println("Age:             | " + age);
                System.out.println("--------------------------------------");
                System.out.println("ID:              | " + tid);
                System.out.println("Subject Area:    | " + sa);
                System.out.println("Year Expirience: | " + ye);
            }
        }
         catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTeacher(String name, int age, String subjectArea, String yearExp, String tid){
        String update = """
        UPDATE Teacher
        SET Name = ?, Age = ?, Subject_Area = ?, Year_Level = ?
        WHERE TID = ?;
        """;
        try {Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = conn.prepareStatement(update);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, subjectArea);
            ps.setString(4, yearExp);
            ps.setString(5, tid);
            ps.executeUpdate();
            System.out.println("Teacher updated successfully");
        } catch (SQLException e) {
            System.out.println("Teacher update Error: " + e.getMessage());
        }
    }

    public void deleteTeacher(String tid){
        String delete = """
                DELETE FROM Teacher
                WHERE TID = ?;
                """;
                try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = conn.prepareStatement(delete)){
                    ps.setString(1, tid);
                    ps.executeUpdate();
                    System.out.println("Teacher deletion successfully");
                } 
                catch (SQLException e) {
                    System.out.println("Teacher deletion Error: " + e.getMessage());
                }
    }
}
