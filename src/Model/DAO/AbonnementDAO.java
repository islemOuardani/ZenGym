package Model.DAO;

import Model.DataBase.MyConnection;

import java.sql.*;
import java.time.LocalDate;

public class AbonnementDAO {
    Connection con = null;
    public AbonnementDAO(String url, String username, String password){
        con= MyConnection.getConnection(url,username,password);
    }
    public int insertAbonnement(int numero, int type, LocalDate debut, LocalDate fin) {
        String req1 = "insert into abonnement values (?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(req1);
            ps.setInt(1,numero);
            ps.setInt(2,type);
            ps.setDate(3,java.sql.Date.valueOf(debut));
            ps.setDate(4, java.sql.Date.valueOf(fin));
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
    public int modifierAbonnement(int numero, int type, LocalDate dateFin ) {
        String req = "UPDATE abonnement SET fin = ? WHERE numero = ? and type = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setDate(1, java.sql.Date.valueOf(dateFin));
            ps.setInt(2,numero);
            ps.setInt(3,type);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int supprimerAbonnement(int numero, int type){
        String req="Delete from abonnement where numero = ? and type = ?";
        try{
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1,numero);
            ps.setInt(2,type);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
}
