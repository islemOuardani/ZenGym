package Controller;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Authentication extends Remote {
    int authenticate(String login, String mdp) throws RemoteException;
    String getNom(String login) throws RemoteException;
    boolean checkConn(String login) throws RemoteException;
    void deconnecte(String login) throws RemoteException;
}
