package Controller;

import Model.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatImplementation extends UnicastRemoteObject implements ChatRemote {
    ArrayList<Message> disc;
    int size;
    public ChatImplementation() throws RemoteException {
        this.disc = new ArrayList<>();
        this.size = 0;
    }
    @Override
    public ArrayList<Message> getMsg() throws RemoteException {
        return disc;
    }

    @Override
    public void addMsg(Message msg) throws RemoteException {
        disc.add(msg);
    }

    @Override
    public int getSize() throws RemoteException {
        return size;
    }

    @Override
    public void setSize(int size) throws RemoteException {
        this.size = size;
    }

    @Override
    public boolean nouveauMsg() throws RemoteException {
        return (!(getMsg().size() == getSize()));
    }
}
