package Controller;

import IHM.IHMPrincipale;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FocusAction implements FocusListener {
    IHMPrincipale p;

    public FocusAction(IHMPrincipale p) {
        this.p = p;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(p!=null){
            if(e.getSource()==p.tfSearch){
                if (p.tfSearch.getText().equals("Cherchez un adhérent par numéro de téléphone")){
                    p.tfSearch.setText("");
                }
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(p!=null){
            if(e.getSource()==p.tfSearch){
                if (p.tfSearch.getText().isEmpty()){
                    p.tfSearch.setText("Cherchez un adhérent par numéro de téléphone");
                }
            }
        }
    }
}
