
/* JDBC_Connection_Demo.java */
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

 
public class jdbc1
{
  /* static block is executed when a class is loaded into memory 
   * this block loads MySQL's JDBC driver
   */
  static
  {
    try
    {
      // loads com.mysql.jdbc.Driver into memory
      Class.forName(" com.mysql.jdbc.Driver");
    } 
    catch (ClassNotFoundException cnf) 
    {
      System.out.println("Driver could not be loaded: " + cnf);
    }
  }
 
  public static void main(String[] args)
  {
    String connectionUrl = "jdbc:mysql://localhost:3306/college";
    String dbUser = "root";
    String dbPwd = "";
    Connection conn;
    ResultSet rs;
    String queryString = "SELECT name,class,department FROM student";
 
    try
    {
      conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
      Statement stmt = conn.createStatement();
 
      // INSERT A RECORD
      stmt.executeUpdate("INSERT INTO student VALUES ('abcd','te','entc')");
      stmt.executeUpdate("INSERT INTO student VALUES ('efgh','se','comp')");
 
      // SELECT ALL RECORDS FROM EXPTABLE
      rs = stmt.executeQuery(queryString);
 
      System.out.println("name \tclass\tdepartment");
      System.out.println("============");
      while(rs.next())
      {
        System.out.print(rs.getInt("name") + ".\t" + rs.getString("class")+"\t"+rs.getString("department"));
        System.out.println();
      }
      if (conn != null)
      {
        conn.close();
        conn = null;
      }
    }
    catch (SQLException sqle) 
    {
      System.out.println("SQL Exception thrown: " + sqle);
    }
  }
} //JDBC_Connection_Demo ends here
 
