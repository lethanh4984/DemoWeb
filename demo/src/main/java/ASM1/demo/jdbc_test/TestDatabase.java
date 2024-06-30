package ASM1.demo.jdbc_test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDatabase {
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=UTC";
        String user = "springstudent";
        String pass = "springstudent";

        try {
            System.out.println("Connecting to database: " + jdbcUrl);

            Connection myConn =
                    DriverManager.getConnection(jdbcUrl, user, pass);

            System.out.println("Connection successful!!!");

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }
}
