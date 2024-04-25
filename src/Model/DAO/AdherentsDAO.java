package Model.DAO;

import Model.DataBase.MyConnection;

import java.sql.*;
import java.time.LocalDate;

public class AdherentsDAO {
    Connection con = null;
    public AdherentsDAO(String url, String username, String password){
        con= MyConnection.getConnection(url,username,password);
    }
    public int insertAdherents(String nom, String prenom, int num, LocalDate naissance,String adresse) {
        String req1 = "insert into adherents values (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(req1);
            ps.setString(1,nom);
            ps.setString(2,prenom);
            ps.setInt(3,num);
            ps.setDate(4, java.sql.Date.valueOf(naissance));
            ps.setString(5,adresse);
            ps.setDate(6,java.sql.Date.valueOf(LocalDate.now()));
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
    public ResultSet selection(String req) {
        try {
            Statement st = con.createStatement();
            return st.executeQuery(req);
        } catch (SQLException e) {
            return null;
        }
    }
    public int modifierAdherent(String nom, String prenom, int numero) {
        String req = "UPDATE adherents SET nom = ?, prenom = ? WHERE numero = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1,nom);
            ps.setString(2,prenom);
            ps.setInt(3,numero);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int modifierAdherents(String nom, String prenom, int numero,String adresse) {
        String req = "UPDATE adherents SET nom = ?, prenom = ?, adresse = ? WHERE numero = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1,nom);
            ps.setString(2,prenom);
            ps.setString(3,adresse);
            ps.setInt(4,numero);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int supprimerAdherent(int numero){
        String req="Delete from adherents where numero = ?";
        try{
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1,numero);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
}
