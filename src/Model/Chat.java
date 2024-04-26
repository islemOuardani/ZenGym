package Model;

import Controller.ChatRemote;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Chat extends Thread{
    JTextPane d;
    ChatRemote r;
    String login;

    public Chat(JTextPane d, ChatRemote r,String login) {
        this.d = d;
        this.r = r;
        this.login = login;
    }

    @Override
    public void run() {
        int size = 0;
        while(true){
            try {
                ArrayList<Message> disc = r.getMsg();
                for (int i = size ; i < disc.size() ; i++){
                    size= disc.size();
                    HTMLDocument doc = (HTMLDocument) d.getDocument();
                    HTMLEditorKit editorKit = (HTMLEditorKit) d.getEditorKit();
                    String text;
                    if(login.equals(disc.get(i).getPseudo())){
                        text = "<div>"
                                + "<div style=\"background-color: red; color: white ; padding: 5px; font-size: 12px;padding-left: 10px; padding-right: 10px;text-align: center;\">"+disc.get(i).getMessage()+"</div>"
                                + "</div>"
                                + "<br>"
                                + "<br>";
                    } else {
                        text = "<div>"
                                + "<p style=\"text-align: left; margin: 0px; padding-left: 10px; padding-right: 10px; font-size: 10px;\">"+disc.get(i).getPseudo()+"</p>"
                                + "<div style=\"background-color:#494846;color : white ; padding: 5px; font-size: 12px;padding-left: 10px; padding-right: 10px;width:150px;\">"+disc.get(i).getMessage()+"</div>"
                                + "</div>"
                                + "<br>"
                                + "<br>";
                    }
                    editorKit.insertHTML(doc, doc.getLength(), text, 0, 0, null);
                    d.setCaretPosition(d.getDocument().getLength());
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
