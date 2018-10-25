package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


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
    public Boolean checkPreverence(String todo, String user) throws Exception{
        boolean bool = true;

        if (todo == "theme"){
            int theme = getTheme(user);
            if (theme == 1){
                bool = true;
            }else {
                 bool = false;
            }
        }else {
            int bgc = getBgc(user);
            if (bgc == 1) {
                bool = true;
            }else {
                bool = false;
            }
        }
    return bool;
    }
    public int getBgc(String user)   throws SQLException
    {
        int result = 1;
        connect();

            String query = "SELECT user.bgc FROM user WHERE USER.USER = ?";
            try
            {
                // going to do a search using "upper"
                user = user.toUpperCase();

                // create the preparedstatement and add the criteria
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, "%" + user + "%");

                // process the results
                ResultSet rs = ps.executeQuery();
                while ( rs.next() )
                {
                    result = rs.getInt("scene");
                }
                rs.close();
                ps.close();
            }
            catch (SQLException se)
            {
                // log exception;
                throw se;
            }
        return result;

        }

    public int getTheme(String user)
      throws SQLException
        {
            connect();
            int result = 1;
            String query = "SELECT user.scene FROM user WHERE USER.USER = ?";
            try
            {
                // going to do a search using "upper"
                user = user.toUpperCase();

                // create the preparedstatement and add the criteria
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, "%" + user + "%");

                // process the results
                ResultSet rs = ps.executeQuery();
                while ( rs.next() )
                {
                    result = rs.getInt("scene");
                }
                rs.close();
                ps.close();
            }
            catch (SQLException se)
            {
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
