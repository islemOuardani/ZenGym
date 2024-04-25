package Model;

import Controller.MyConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {
    Connection con = null;
    public LoginDAO(String url, String username, String password) {
        this.con = MyConnection.getConnection(url,username,password);;
    }
    public ResultSet selection(String req) {
        try {
            Statement st = con.createStatement();
            return st.executeQuery(req);
        } catch (SQLException e) {
            return null;
        }
    }
}
