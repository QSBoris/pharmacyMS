package hospital.management.system;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.Statement;

public class Connect1 {


    Connection connection;
    Statement statement;

    public Connect1() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:127/hospitalms", "root", "pass123");
            statement = connection.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
