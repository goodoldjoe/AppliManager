package sample;

import java.sql.*;

public class Login {

    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/AppliManager?serverTimezone=UTC";

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

    public Boolean checkLogin(String name, String password) {
        boolean loggedIn = false;
        try {
            System.out.println("Checking details...");

            String loginQuery = "SELECT u.user, u.pw " +
                    "FROM user u " +
                    "WHERE u.user = ? AND u.pw = ?";
            prepStmt = conn.prepareStatement(loginQuery);

            prepStmt.setString(1, name);
            prepStmt.setString(2, password);

            rs = prepStmt.executeQuery();

            while (rs.next()) {
                System.out.println("Login for " + rs.getString("user") + " success");
                loggedIn = true;
            }
        } catch (SQLException e) {
            System.out.println("PrepStatment failed");
            loggedIn = false;
            e.printStackTrace();
        } catch (NullPointerException h) {
            h.printStackTrace();
        } finally {
            cleanConn();
            cleanResult();
        }

        return loggedIn;
    }

    public Boolean register(String name, String password) {
        boolean registerSuccess = false;
        boolean correctEntry = false;
        try {
            connect();
            System.out.println("Registering");

            String registerQuery = "INSERT INTO user(user, pw) " +
                    "VALUES (" +
                    "?, ?)";
            prepStmt = conn.prepareStatement(registerQuery);
            prepStmt.setString(1, name);
            prepStmt.setString(2, password);

            int erfolg = prepStmt.executeUpdate();

            if (erfolg > 0) {
                System.out.println("Insert erfolg!");
                registerSuccess = true;
            } else {
                System.out.println("Insert failed | No ROW Affected");
            }
            correctEntry = true;
        } catch (SQLException e) {
            System.out.println("Insert failed");
        } finally {
            cleanConn();
        }
        return registerSuccess;
    }

    public void cleanConn() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cleanResult() {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("Close failed..!");
        }

    }

}
