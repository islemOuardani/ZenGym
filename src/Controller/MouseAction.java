package Controller;
import Config.ColorPalette;
import IHM.*;
import Model.AdherentCard;
import Model.Discussion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;

public class MouseAction extends MouseAdapter {
    IHMPrincipale p;
    IHMAdherents a;
    public MouseAction(IHMAdherents a) {
        this.a = a;
    }
    public MouseAction(IHMPrincipale p) {
        this.p = p;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (p != null) {
            if (e.getSource()==p.pHome) {
                if(p.pContenant.num!=1) {
                    p.pContenant.switchToView(new IHMHome(),1);
                    p.pHome.setBackground(ColorPalette.SELECTED.getColor());
                    p.pAdherents.setBackground(new Color(0,0,0,0));
                    //p.pPersonnels.setBackground(new Color(0,0,0,0));
                    p.pAbonnements.setBackground(new Color(0,0,0,0));
                    p.repaint();
                    p.revalidate();
                }
            } else if (e.getSource()==p.pAdherents) {
                if(p.pContenant.num!=2) {
                    p.pContenant.switchToView(new IHMAdherents(),2);
                    p.pAdherents.setBackground(ColorPalette.SELECTED.getColor());
                    p.pHome.setBackground(new Color(0,0,0,0));
                    //p.pPersonnels.setBackground(new Color(0,0,0,0));
                    p.pAbonnements.setBackground(new Color(0,0,0,0));
                    p.repaint();
                    p.revalidate();
                }
            } /*else if (e.getSource()==p.pPersonnels) {
                if(p.pContenant.num!=3) {
                    p.pContenant.switchToView(new IHMPersonnels(),3);
                    p.pPersonnels.setBackground(ColorPalette.SELECTED.getColor());
                    p.pHome.setBackground(new Color(0,0,0,0));
                    p.pAdherents.setBackground(new Color(0,0,0,0));
                    p.pAbonnements.setBackground(new Color(0,0,0,0));
                    p.repaint();
                    p.revalidate();
                }
            } */else if (e.getSource()==p.pAbonnements) {
                if(p.pContenant.num!=4) {
                    p.pContenant.switchToView(new IHMAbonnement(),4);
                    p.pAbonnements.setBackground(ColorPalette.SELECTED.getColor());
                    p.pHome.setBackground(new Color(0,0,0,0));
                    p.pAdherents.setBackground(new Color(0,0,0,0));
                    //p.pPersonnels.setBackground(new Color(0,0,0,0));
                    p.repaint();
                    p.revalidate();
                }
            } else if (e.getSource() == p.lbHelp) {
                JOptionPane.showMessageDialog( p, "<html>C'est une application conçue en Java Swing pour gérer les abonnements d'une salle de sport,<br> développée dans le cadre d'un projet de TP pour le module de Programmation Orientée Objet,<br> par Ouardani Mohamed Islem.</html>",
                        "Aide",JOptionPane.PLAIN_MESSAGE);
            } else if (e.getSource() == p.lbMsgIcon) {
                p.d = new Discussion(p.login);
                p.d.show(p.lbMsgIcon,-150,p.lbMsgIcon.getHeight());
            }else if (e.getSource() == p.lbLogout) {
                try {
                    System.out.println("Déconnection...");
                    String url = "rmi://127.0.0.1:9001/login";
                    Authentication auth = null;
                    auth = (Authentication) Naming.lookup(url);
                    auth.deconnecte(p.login);
                    p.dispose();
                    new IHMLogin();
                } catch (NotBoundException ex) {
                    throw new RuntimeException(ex);
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        if(a!=null){
            if (e.getSource()==a.jtAdhe){
                if (e.getClickCount()==2){
                    String num= a.model.data.get(a.jtAdhe.getSelectedRow())[2].toString();
                    String req2="select * from adherents where numero="+num+" ;";
                    String req3 = "SELECT * FROM abonnement a WHERE a.numero=" + num + " HAVING fin IN (SELECT MAX(fin) FROM abonnement b WHERE a.numero = b.numero ) ;";
                    ResultSet rs2 = a.dao.selection(req2);
                    IHMAbonnement ab = new IHMAbonnement();
                    ResultSet rs3 = ab.dao.selection(req3);
                    a.jpEast.removeAll();
                    a.jpEast.add(new AdherentCard(rs2,a.dao,a,rs3,ab.dao));
                    a.repaint();
                    a.revalidate();
                }
                if(e.getButton()==MouseEvent.BUTTON3){
                    JPopupMenu popup=new JPopupMenu();
                    JMenuItem itemSupp=new JMenuItem("Supprimer");
                    popup.add(itemSupp);
                    popup.show(a.jtAdhe, e.getX(),e.getY());

                    itemSupp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String nom=a.model.data.get(a.jtAdhe.getSelectedRow())[0].toString();
                            String prenom=a.model.data.get(a.jtAdhe.getSelectedRow())[1].toString();
                            int result=JOptionPane.showConfirmDialog(a,"Voullez vous vraiment supprimer "+nom+" "+prenom+" définitivement","Supprimer",JOptionPane.OK_CANCEL_OPTION);
                            if(result==0){
                                int index=a.jtAdhe.getSelectedRow();
                                int numero=Integer.parseInt(a.model.data.get(a.jtAdhe.getSelectedRow())[2].toString());
                                a.model.suppAdherent(numero,index);
                            }

                        }
                    });
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (p != null) {
            if (e.getSource()==p.pHome) {
                if(p.pContenant.num!=1) {
                    p.pHome.setBackground(ColorPalette.HOVER.getColor());
                    p.repaint();
                    p.revalidate();

                }
            } else if (e.getSource()==p.pAdherents) {
                if(p.pContenant.num!=2) {
                    p.pAdherents.setBackground(ColorPalette.HOVER.getColor());
                    p.repaint();
                    p.revalidate();
                }
            }/* else if (e.getSource()==p.pPersonnels) {
                if(p.pContenant.num!=3) {
                    p.pPersonnels.setBackground(ColorPalette.HOVER.getColor());
                    p.repaint();
                    p.revalidate();
                }
            }*/ else if (e.getSource()==p.pAbonnements) {
                if(p.pContenant.num!=4) {
                    p.pAbonnements.setBackground(ColorPalette.HOVER.getColor());
                    p.repaint();
                    p.revalidate();
                }
            }else if (e.getSource() == p.lbHelp) {
                p.lbHelp.setForeground(ColorPalette.HOVER.getColor());
            }else if (e.getSource() == p.lbLogout) {
                p.lbLogout.setForeground(new Color(127, 0, 0));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (p != null) {
            if (e.getSource()==p.pHome) {
                if(p.pContenant.num!=1) {
                    p.pHome.setBackground(new Color(0,0,0,0));
                    p.repaint();
                    p.revalidate();
                }
            } else if (e.getSource()==p.pAdherents) {
                if(p.pContenant.num!=2) {
                    p.pAdherents.setBackground(new Color(0,0,0,0));
                    p.repaint();
                    p.revalidate();
                }
            }/* else if (e.getSource()==p.pPersonnels) {
                if(p.pContenant.num!=3) {
                    p.pPersonnels.setBackground(new Color(0,0,0,0));
                    p.repaint();
                    p.revalidate();
                }
            }*/ else if (e.getSource()==p.pAbonnements) {
                if(p.pContenant.num!=4) {
                    p.pAbonnements.setBackground(new Color(0,0,0,0));
                    p.repaint();
                    p.revalidate();
                }
            } else if (e.getSource() == p.lbHelp) {
                p.lbHelp.setForeground(Color.WHITE);
            }else if (e.getSource() == p.lbLogout) {
                p.lbLogout.setForeground(Color.RED);
            }
        }
    }
}
