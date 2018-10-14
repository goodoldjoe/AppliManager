package sample;

import java.sql.*;

public class Login {

    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/EMP?serverTimezone=UTC";

    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "";

    //Database variables
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;

    public void connect() {
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connect Success");
        }catch(SQLException se){
        //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
        //Handle errors for Class.forName
            e.printStackTrace();
        }/*finally{
        //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }*/
    }

    public void query() throws SQLException {
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        String sql;
        sql = "SELECT id, first, last, age FROM Employees";
        rs = stmt.executeQuery(sql);
    }

    public void prepQuery() throws SQLException {
        System.out.println("Creating Prep statement..");
        String paraQuery = "SELECT id, first" +
                " FROM Employees " +
                "WHERE LAST = ?";

        prepStmt = conn.prepareStatement(paraQuery);

        prepStmt.setString(1, "Ali");

        rs = prepStmt.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String first = rs.getString("first");
            System.out.println("You've selected the following:");
            System.out.println("ID: " + id + " FirstName: " + first);
        }
        else {
            System.out.println("Failed to extract");
        }
    }

    public void extract() throws SQLException {
        //STEP 5: Extract data from result set
        while (rs.next()) {
            //Retrieve by column name
            int id = rs.getInt("id");
            int age = rs.getInt("age");
            String first = rs.getString("first");
            String last = rs.getString("last");

            //Display values
            System.out.print("ID: " + id);
            System.out.print(", Age: " + age);
            System.out.print(", First: " + first);
            System.out.println(", Last: " + last);
        }
    }

    public void cleanUp() throws SQLException {
        rs.close();
        //stmt.close();
        conn.close();
    }

}
