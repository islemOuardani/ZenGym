package Model;

import Controller.ChatRemote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Messages extends JFrame {
    public Messages(String login) {
        this.setSize(new Dimension(400,400));
        this.setLayout(new BorderLayout());
        JTextPane conversation = new JTextPane();
        conversation.setEditable(false);
        conversation.setContentType("text/html");
        JScrollPane scrollPane = new JScrollPane(conversation);
        conversation.setCaretPosition(conversation.getDocument().getLength());

        this.add(scrollPane,BorderLayout.CENTER);
        JPanel south = new JPanel(new FlowLayout());
        south.setBackground(new Color(0x0C0A0B));
        south.setPreferredSize(new Dimension(400,50));
        JTextField message = new JTextField();
        message.setPreferredSize(new Dimension(200,30));
        JButton envoyer = new JButton("Envoyer");
        envoyer.setPreferredSize(new Dimension(100,30));
        envoyer.setFont(new Font("Times New Roman",Font.BOLD,10));
        envoyer.setBackground(new Color(0xC19D08));
        envoyer.setForeground(Color.WHITE);
        south.add(message);
        south.add(envoyer);
        this.add(south,BorderLayout.SOUTH);

        this.setVisible(true);

        try {
            System.out.println("Connecte to messagerie....");
            String url = "rmi://127.0.0.1:9001/messages";
            ChatRemote r = (ChatRemote) Naming.lookup(url);
            new Chat(conversation,r,login).start();
            envoyer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if(!message.getText().isEmpty()){
                            r.addMsg(new Message(login,message.getText(), LocalTime.now()));
                            message.setText("");
                        }
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            message.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if(e.getKeyCode()== KeyEvent.VK_ENTER){
                        try {
                            if(!message.getText().isEmpty()){
                                r.addMsg(new Message(login,message.getText(), LocalTime.now()));
                                message.setText("");
                            }
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
