package Controller;

import Model.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatImplementation extends UnicastRemoteObject implements ChatRemote {
    ArrayList<Message> disc;
    public ChatImplementation() throws RemoteException {
        this.disc = new ArrayList<>();
    }
    @Override
    public ArrayList<Message> getMsg() throws RemoteException {
        return disc;
    }

    @Override
    public void addMsg(Message msg) throws RemoteException {
        disc.add(msg);
    }
}
