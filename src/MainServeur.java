import Controller.AuthentificationImplementation;
import Controller.ChatImplementation;
import Controller.Discussion;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class MainServeur {
    public static void main(String[] args){
        try {
            System.out.println("Demmare serveur");
            String url1 = "rmi://localhost:9001/login";
            AuthentificationImplementation auth = new AuthentificationImplementation();

            String url = "rmi://localhost:9001/messages";
            ChatImplementation chat = new ChatImplementation();
            LocateRegistry.createRegistry(9001);

            Naming.rebind(url,chat);
            Naming.rebind(url1,auth);
        } catch (RemoteException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
        new Discussion().start();
    }
}
