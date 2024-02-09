package hotel.management.system;
// connecting database via JDBC
// 5 steps involved - 1.register the driver, 2.creating the connection string, 3.create a statement, 
//4.executing SQL queries(in login cls), 5.closing connection(optinal). 

import java.sql.*; //has connection cls.

public class Conn {
    
    Connection c;
    Statement s; //we can xecute queries thru this statement s.
    Conn(){ //cnstrctr
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///hotelmanagementsystem", "root", "12345"); //connection string
            s = c.createStatement();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
