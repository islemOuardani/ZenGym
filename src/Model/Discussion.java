package Model;

import Config.ColorPalette;
import Controller.ChatRemote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

public class Discussion extends JPopupMenu {
    public Discussion(String login) {
        this.setPreferredSize(new Dimension(400,400));
        this.setLayout(new BorderLayout());
        JTextPane conversation = new JTextPane();
        conversation.setEditable(false);
        conversation.setContentType("text/html");
        JScrollPane scrollPane = new JScrollPane(conversation);
        conversation.setCaretPosition(conversation.getDocument().getLength());

        this.add(scrollPane,BorderLayout.CENTER);
        JPanel south = new JPanel(new FlowLayout());
        south.setBackground(ColorPalette.PRIMARY.getColor());
        south.setPreferredSize(new Dimension(400,50));
        JTextField message = new JTextField();
        message.setPreferredSize(new Dimension(200,30));
        JButton envoyer = new JButton("Envoyer");
        envoyer.setPreferredSize(new Dimension(100,30));
        envoyer.setFont(new Font("Times New Roman",Font.BOLD,10));
        envoyer.setBackground(ColorPalette.SECONDARY.getColor());
        envoyer.setForeground(Color.WHITE);
        south.add(message);
        south.add(envoyer);
        this.add(south,BorderLayout.SOUTH);

        this.setVisible(true);

        try {
            System.out.println("Lancement client...");
            String url = "rmi://127.0.0.1:9001/messages";
            ChatRemote r = (ChatRemote) Naming.lookup(url);
            new Chat(conversation,envoyer,message,r).start();
            envoyer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        r.addMsg(new Message(login,message.getText(), LocalDateTime.now()));
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
