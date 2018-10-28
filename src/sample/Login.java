package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Login {
    //
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
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
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


    public int getBgc(String user) throws SQLException {
        int result = 1;
        connect();

        String query = "SELECT user.bgc FROM user WHERE USER.USER = ?";
        try {
            // going to do a search using "upper"
            //user = user.toUpperCase();

            // create the preparedstatement and add the criteria
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "" + user + "");

            // process the results
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("bgc");
            }
            rs.close();
            ps.close();
        } catch (SQLException se) {
            // log exception;
            throw se;
        }
        return result;

    }

    public int getTheme(String user)
            throws SQLException {
        connect();
        int result = 1;
        String query = "SELECT user.scene FROM user WHERE USER.USER = ?";
        try {
            // going to do a search using "upper"
            //user = user.toUpperCase();

            // create the preparedstatement and add the criteria
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "" + user + "");

            // process the results
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("scene");
            }
            rs.close();
            ps.close();

        } catch (SQLException se) {
            // log exception;
            throw se;
        }
        return result;
    }

    public Boolean register(String name, String password) {
        boolean registerSuccess = false;
        boolean correctEntry = false;
        try {
            connect();
            System.out.println("Registering");

            String registerQuery = "INSERT INTO user(user, pw, scene, bgc) " +
                    "VALUES (" +
                    "?, ?, ?, ?)";
            prepStmt = conn.prepareStatement(registerQuery);
            prepStmt.setString(1, name);
            prepStmt.setString(2, password);
            prepStmt.setInt(3, 1);
            prepStmt.setInt(4, 1);

            int erfolg = prepStmt.executeUpdate();

            if (erfolg > 0) {
                System.out.println("Insert erfolg!");
                registerSuccess = true;
                // main.closeRegister();
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

    // für theme1 ist der int 1 für theme 2 ist der int 2
    // für blau ist der int 1, Orange ist der int 2 für grün ist der int 3
    public void savePreference(String user, String preference, int integer)
    throws SQLException{
        connect();
        int result = 1;
        String query = "";
        switch (preference){
            case "theme":
                query = "update user set user.scene = ? WHERE USER.USER = ?";
                break;
            case "bgc":
                query = "update user set user.bgc = ? where user.user = ?";
                break;
        }

        try {
            // going to do a search using "upper"
           // user = user.toUpperCase();

            // create the preparedstatement and add the criteria
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "" + integer +"" );
            ps.setString(2, "" + user + "");

            // process the results
            ps.executeUpdate();

            //rs.close();
            ps.close();

        } catch (SQLException se) {
            // log exception;
            throw se;
        }

    }
}
