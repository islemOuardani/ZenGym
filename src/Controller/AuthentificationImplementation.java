package Controller;

import Model.LoginDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AuthentificationImplementation extends UnicastRemoteObject implements Authentication {
    public Set<String> logins;
    public AuthentificationImplementation() throws RemoteException {
        this.logins = new HashSet<>();
    }

    @Override
    public int authenticate(String login, String mdp) throws RemoteException {
        int result = 0;
        LoginDAO dao = new LoginDAO(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
        String req = "select count(*) from personnel where login = '"+login+"' and mdp = '"+mdp+"';";
        ResultSet rs = dao.selection(req);
        try {
            while (rs.next()) {
                result = Integer.parseInt(rs.getObject(1).toString());
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        if (result == 1){
            if(checkConn(login)){
                result = -1;
            }else {
                this.logins.add(login);
            }
        }
        return result;
    }

    @Override
    public String getNom(String login) throws RemoteException {
        String l = null;
        LoginDAO dao = new LoginDAO(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
        String req = "select nom from personnel where login = '"+login+"';";
        ResultSet rs = dao.selection(req);
        try {
            while (rs.next()) {
                l = rs.getObject(1).toString();
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return l;
    }

    @Override
    public boolean checkConn(String login) throws RemoteException {
        return this.logins.contains(login);
    }

    @Override
    public void deconnecte(String login) throws RemoteException {
        this.logins.remove(login);
    }

}
