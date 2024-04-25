package Model;

import Controller.ChatRemote;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Chat extends Thread{
    JTextPane d;
    JButton e;
    JTextField m;
    ChatRemote r;

    public Chat(JTextPane d, JButton e, JTextField m, ChatRemote r) {
        this.d = d;
        this.e = e;
        this.m = m;
        this.r = r;
    }

    @Override
    public void run() {
        System.out.println();
        while(true){
            try {
                ArrayList<Message> disc = r.getMsg();
                if(d.getDocument().getLength()==0)
                {
                    for (int i = 0 ; i < disc.size() ; i++){
                        r.setSize(disc.size());
                        HTMLDocument doc = (HTMLDocument) d.getDocument();
                        HTMLEditorKit editorKit = (HTMLEditorKit) d.getEditorKit();
                        String text = "<html><font face='Times New Roman' size='5' color='red' bgcolor='blue'>"+disc.get(i).getPseudo()+" : "+disc.get(i).getMessage()+"</font><br></html>";
                        editorKit.insertHTML(doc, doc.getLength(), text, 0, 0, null);
                        d.setCaretPosition(d.getDocument().getLength());
                    }
                }else {
                    for (int i = r.getSize() ; i < disc.size() ; i++){
                        r.setSize(disc.size());
                        HTMLDocument doc = (HTMLDocument) d.getDocument();
                        HTMLEditorKit editorKit = (HTMLEditorKit) d.getEditorKit();
                        String text = "<html><font face='Times New Roman' size='5' color='red' bgcolor='blue'>"+disc.get(i).getPseudo()+" : "+disc.get(i).getMessage()+"</font><br></html>";
                        editorKit.insertHTML(doc, doc.getLength(), text, 0, 0, null);
                        d.setCaretPosition(d.getDocument().getLength());
                    }
                }
                Thread.sleep(100);
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
