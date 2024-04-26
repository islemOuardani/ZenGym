package Controller;

import Model.Messages;

import java.util.Scanner;

public class Discussion extends Thread{
    @Override
    public void run() {
        while (true){
            Scanner sc = new Scanner(System.in);
            String commande = sc.nextLine();
            if(commande.equalsIgnoreCase("Messages")){
                new Messages("admin");
            }
        }
    }
}
