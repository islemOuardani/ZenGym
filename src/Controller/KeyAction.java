package Controller;
import Config.ColorPalette;
import IHM.IHMAbonnement;
import IHM.IHMAdherents;
import IHM.IHMAjoutAdherent;
import IHM.IHMPrincipale;
import Model.AdherentCard;
import Model.ResultSearch;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyAction extends KeyAdapter {
    IHMAjoutAdherent aAdherent;
    IHMPrincipale p;
    public KeyAction(IHMAjoutAdherent aAdherent) {
        this.aAdherent = aAdherent;
    }
    public KeyAction(IHMPrincipale p) {
        this.p = p;
    }


    @Override
    public void keyReleased(KeyEvent e) {
        if(aAdherent!=null){
            if(e.getSource()==aAdherent.tfNumero)
            {
                if (!(aAdherent.tfNumero.getText().matches("\\d+")||aAdherent.tfNumero.getText().matches(".*\\u0008.*"))){
                    aAdherent.numValide=0;
                    aAdherent.tfNumero.setBorder(new TitledBorder(new LineBorder(Color.RED), "Chiffres seulement!",
                            TitledBorder.LEFT,
                            TitledBorder.BOTTOM,
                            new Font("SF Pro Text",Font.ITALIC,10), Color.RED));
                }else{
                    aAdherent.numValide=1;
                    aAdherent.tfNumero.setBorder(new LineBorder(null));
                    if(aAdherent.tfNumero.getText().length()>8){
                        aAdherent.tfNumero.setBorder(new TitledBorder(new LineBorder(Color.RED), "Le numéro doit être composé de 8 chiffres",
                                TitledBorder.LEFT,
                                TitledBorder.BOTTOM,
                                new Font("SF Pro Text",Font.ITALIC,10), Color.RED));
                        aAdherent.numValide=0;

                    } else if (aAdherent.tfNumero.getText().length()<8) {
                        aAdherent.numValide=0;
                    } else{
                        aAdherent.numValide=1;
                        aAdherent.tfNumero.setBorder(new LineBorder(null));
                    }
                }
            } else if (e.getSource()==aAdherent.tfNom){
                aAdherent.tfNom.setBorder(new LineBorder(null));
            } else if (e.getSource()==aAdherent.tfPrenom) {
                aAdherent.tfPrenom.setBorder(new LineBorder(null));
            }else if (e.getSource()==aAdherent.tfAdresse) {
                aAdherent.tfAdresse.setBorder(new LineBorder(null));
            }
        }
        if (p!=null){
            if(e.getSource()==p.tfSearch){
                JPopupMenu popupSerach = new JPopupMenu();
                popupSerach.setVisible(true);
                String num=p.tfSearch.getText();
                String req="select nom, prenom, numero from adherents where CAST(numero AS CHAR) LIKE '%"+num+"%' ;";
                ResultSet rs = p.dao.selection(req);
                ResultSearch rSearch = new ResultSearch(rs);
                popupSerach.add(rSearch.resultList);
                popupSerach.show(p.tfSearch, -5,p.tfSearch.getHeight());
                p.tfSearch.requestFocus();
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    rSearch.resultList.setSelectedIndex(0);
                    popupSerach.requestFocus();
                }
                popupSerach.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        int selectedIndex = rSearch.resultList.getSelectedIndex();
                        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            if (selectedIndex < rSearch.resultList.getModel().getSize() - 1) {
                                rSearch.resultList.setSelectedIndex(selectedIndex + 1);
                            }
                        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                            if (selectedIndex > 0) {
                                rSearch.resultList.setSelectedIndex(selectedIndex - 1);
                            }
                            else{
                                rSearch.resultList.clearSelection();
                                p.tfSearch.requestFocus();
                            }
                        } else if (e.getKeyCode()==KeyEvent.VK_ENTER){
                            IHMAdherents a=new IHMAdherents();
                            a.jpEast.removeAll();
                            String num = rSearch.resultList.getSelectedValue();
                            Pattern pattern = Pattern.compile("\\d+");
                            Matcher matcher = pattern.matcher(num);
                            int numero=0;
                            while (matcher.find()) {
                                String numberString = matcher.group();
                                numero = Integer.parseInt(numberString);
                            }
                            p.pContenant.switchToView(a,2);
                            p.pAdherents.setBackground(ColorPalette.SELECTED.getColor());
                            p.pHome.setBackground(new Color(0,0,0,0));
                            //p.pPersonnels.setBackground(new Color(0,0,0,0));
                            p.pAbonnements.setBackground(new Color(0,0,0,0));
                            p.repaint();
                            p.revalidate();
                            String req2="select * from adherents where numero="+numero+" ;";
                            String req3 = "SELECT * FROM abonnement a WHERE a.numero=" + numero + " HAVING fin IN (SELECT MAX(fin) FROM abonnement b WHERE a.numero = b.numero ) ;";
                            IHMAbonnement ab = new IHMAbonnement();
                            ResultSet rs2 = a.dao.selection(req2);
                            ResultSet rs3 = ab.dao.selection(req3);
                            a.jpEast.add(new AdherentCard(rs2,a.dao,a,rs3,ab.dao));

                        }
                    }
                });
            }
        }
    }
}
