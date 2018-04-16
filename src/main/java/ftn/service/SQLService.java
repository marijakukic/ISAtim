package ftn.service;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;

@Service
public class SQLService {

    public static void runInitializationSQLScript() {

        String script = "C:/Users/milica/Desktop/upiti.sql";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            new ScriptRunner(DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/isa2017", "milica", "mkdir123"))
                    .runScript(new BufferedReader(new FileReader(script)));
        } catch (Exception e) {
            System.err.println(e);
        }

        System.out.println("\nApplication started!\n");

    }

}
