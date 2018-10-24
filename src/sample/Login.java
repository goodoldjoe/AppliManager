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

    public Boolean connect() {
        Boolean connected = false;
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connect Success");
            connected = true;
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
        return connected;
    }

    public void query() throws SQLException {
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        String sql;
        sql = "SELECT id, first, last, age FROM Employees";
        rs = stmt.executeQuery(sql);
    }

    public void prepQuery() {
        try {
            System.out.println("Creating Prep statement..");
            String paraQuery = "SELECT id, first" +
                    " FROM Employees " +
                    "WHERE LAST = ?";

            prepStmt = conn.prepareStatement(paraQuery);

            prepStmt.setString(1, "Ali");

            rs = prepStmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String first = rs.getString("first");
                System.out.println("You've selected the following:");
                System.out.println("ID: " + id + " FirstName: " + first);
            }
            prepStmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Failed to extract");
            e.printStackTrace();
            try {
                prepStmt.close();
                conn.close();
            } catch (SQLException b) {
                System.out.println("Failed to close Statement.");
            }
        } catch (NullPointerException h) {
            h.printStackTrace();
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

    public void cleanUp() {
        try {
            if (rs != null) {
                rs.close();
            }
            //stmt.close();
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Close failed..!");
        }

    }

    public Boolean checkLogin(String name, String password) {
        boolean loggedIn = false;
        try {
            System.out.println("Checking details...");

            String loginQuery = "SELECT first, last " +
                    "FROM Employees " +
                    "WHERE first = ? AND last = ?";
            prepStmt = conn.prepareStatement(loginQuery);

            prepStmt.setString(1, name);
            prepStmt.setString(2, password);

            rs = prepStmt.executeQuery();

            while (rs.next()) {
                System.out.println("Login for " + rs.getString("first") + " success");
                loggedIn = true;
            }
        } catch (SQLException e) {
            System.out.println("PrepStatment failed");
            loggedIn = false;
            e.printStackTrace();
        } catch (NullPointerException h) {
            h.printStackTrace();
        }

        return loggedIn;
    }

    public Boolean register(String name, String password) {
        boolean registerSuccess = false;
        try {
            System.out.println("Registering");

            String registerQuery = "INSERT INTO Employees(age, first, last) " +
                    "VALUES (" +
                    "?, ?, ?)";
            prepStmt = conn.prepareStatement(registerQuery);
            prepStmt.setInt(1, 20);
            prepStmt.setString(2, name);
            prepStmt.setString(3, password);

            int erfolg = prepStmt.executeUpdate();

            if ( erfolg > 0) {
                System.out.println("Insert erfolg!");
                registerSuccess = true;
            } else {
                System.out.println("Insert failed | No ROW Affected");
            }
        } catch (SQLException e) {
            System.out.println("Insert failed");
            e.printStackTrace();
        } catch (NullPointerException h) {
            h.printStackTrace();
        }
        return registerSuccess;
    }

    public void closeConn() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
