package Controller;

import Model.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ChatRemote extends Remote {
    ArrayList<Message> getMsg() throws RemoteException;
    void addMsg(Message msg) throws RemoteException;
    int getSize() throws RemoteException;
    void setSize(int size) throws RemoteException;
    boolean nouveauMsg() throws RemoteException;
}