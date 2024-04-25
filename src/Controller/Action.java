package Controller;

import Config.ColorPalette;
import Config.DBConfig;
import IHM.*;
import Model.AdherentCard;
import Model.DAO.AbonnementDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Locale;


public class Action implements ActionListener {

    IHMAdherents a;
    IHMAjoutAdherent aAdherent;
    AdherentCard ac;
    IHMProlongement p;
    IHMAjoutAbonnement ajoutAbonnement;
    public Action(IHMAjoutAdherent aAdherent){
        this.aAdherent=aAdherent;
        this.a=aAdherent.a;
    }
    public Action(AdherentCard ac) {
        this.ac = ac;
    }

    public Action(IHMProlongement p) {
        this.p = p;
    }

    public Action(IHMAjoutAbonnement ajoutAbonnement) {
        this.ajoutAbonnement = ajoutAbonnement;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (aAdherent != null) {
            if (e.getSource() == aAdherent.anneeComboBox) {
                aAdherent.anneeComboBox.setBorder(new LineBorder(null));
                aAdherent.moisComboBox.setBorder(new LineBorder(null));
                aAdherent.jourComboBox.setBorder(new LineBorder(null));
                aAdherent.naissancePanel.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
                aAdherent.repaint();
                aAdherent.revalidate();
            } else if (e.getSource() == aAdherent.jourComboBox) {
                aAdherent.anneeComboBox.setBorder(new LineBorder(null));
                aAdherent.moisComboBox.setBorder(new LineBorder(null));
                aAdherent.jourComboBox.setBorder(new LineBorder(null));
                aAdherent.naissancePanel.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
                aAdherent.repaint();
                aAdherent.revalidate();
            } else if (e.getSource() == aAdherent.moisComboBox) {
                aAdherent.anneeComboBox.setBorder(new LineBorder(null));
                aAdherent.moisComboBox.setBorder(new LineBorder(null));
                aAdherent.jourComboBox.setBorder(new LineBorder(null));
                aAdherent.naissancePanel.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
                aAdherent.repaint();
                aAdherent.revalidate();

                String annee = (String) aAdherent.anneeComboBox.getSelectedItem();
                String mois = (String) aAdherent.moisComboBox.getSelectedItem();
                if (mois.equals("1") || mois.equals("3") || mois.equals("5") || mois.equals("7") || mois.equals("8") || mois.equals("10") || mois.equals("12")) {
                    aAdherent.joursListe = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
                } else if (mois.equals("4") || mois.equals("6") || mois.equals("9") || mois.equals("11")) {
                    aAdherent.joursListe = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
                } else {
                    if ((Integer.parseInt(annee) % 4) == 0) {
                        aAdherent.joursListe = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"};
                    } else {
                        aAdherent.joursListe = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"};
                    }
                }
                aAdherent.jourComboBox.setModel(new DefaultComboBoxModel<>(aAdherent.joursListe));
            } else if (e.getSource() == aAdherent.bAnnuler) {
                aAdherent.dispose();
            } else if (e.getSource() == aAdherent.bAjouter) {
                String nom = aAdherent.tfNom.getText();
                String prenom = aAdherent.tfPrenom.getText();
                String adresse = aAdherent.tfAdresse.getText();
                LocalDate naissance = LocalDate.of(Integer.parseInt(aAdherent.anneeComboBox.getSelectedItem().toString()), Integer.parseInt(aAdherent.moisComboBox.getSelectedItem().toString()), Integer.parseInt(aAdherent.jourComboBox.getSelectedItem().toString()));
                if (nom.isEmpty() || prenom.isEmpty() || aAdherent.tfNumero.getText().length() > 8 || aAdherent.tfNumero.getText().length() < 8 || naissance.equals(LocalDate.of(1925, 1, 1)) || adresse.isEmpty()) {
                    if (nom.isEmpty()) {
                        aAdherent.tfNom.setBorder(new TitledBorder(new LineBorder(Color.RED), "Ce champ ne pouvait pas être vide",
                                TitledBorder.LEFT, TitledBorder.BOTTOM, new Font("SF Pro Text", Font.ITALIC, 10), Color.RED));
                    }
                    if (prenom.isEmpty()) {
                        aAdherent.tfPrenom.setBorder(new TitledBorder(new LineBorder(Color.RED), "ce champ ne pouvait pas être vide",
                                TitledBorder.LEFT, TitledBorder.BOTTOM, new Font("SF Pro Text", Font.ITALIC, 10), Color.RED));
                    }
                    if (adresse.isEmpty()) {
                        aAdherent.tfAdresse.setBorder(new TitledBorder(new LineBorder(Color.RED), "ce champ ne pouvait pas être vide",
                                TitledBorder.LEFT, TitledBorder.BOTTOM, new Font("SF Pro Text", Font.ITALIC, 10), Color.RED));
                    }
                    if (aAdherent.tfNumero.getText().isEmpty()) {
                        aAdherent.tfNumero.setBorder(new TitledBorder(new LineBorder(Color.RED), "ce champ ne pouvait pas être vide",
                                TitledBorder.LEFT, TitledBorder.BOTTOM, new Font("SF Pro Text", Font.ITALIC, 10), Color.RED));
                    }
                    if (naissance.equals(LocalDate.of(1925, 1, 1))) {
                        aAdherent.naissancePanel.setBorder(new TitledBorder(new LineBorder(Color.RED), "Choisissez une date valide",
                                TitledBorder.LEFT, TitledBorder.BOTTOM, new Font("SF Pro Text", Font.ITALIC, 10), Color.RED));
                        aAdherent.jourComboBox.setBorder(new LineBorder(Color.RED));
                        aAdherent.moisComboBox.setBorder(new LineBorder(Color.RED));
                        aAdherent.anneeComboBox.setBorder(new LineBorder(Color.RED));
                        aAdherent.repaint();
                        aAdherent.revalidate();
                    }
                } else {
                    int retour = JOptionPane.showConfirmDialog(aAdherent,
                            "Êtes-vous sûr d'ajouter " + nom + " " + prenom + "\n" +
                                    "Adresse : " + aAdherent.tfAdresse.getText() + "\n" +
                                    "Numéro de téléphone : " + aAdherent.tfNumero.getText() + "\n" +
                                    "Anniversaire : " + naissance + "\n" +
                                    "NB: le numéro de téléphone et la date de naissance n'ont pas pu être modifiés ultérieurement",
                            "Ajouter",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (retour == 0) {
                        int numero = Integer.parseInt(aAdherent.tfNumero.getText());
                        a.model.insertAdherent(nom, prenom, numero, naissance, adresse);
                        if (aAdherent.ihmPrincipale.pAdherents.getBackground() == ColorPalette.SELECTED.getColor()) {
                            aAdherent.ihmPrincipale.pContenant.switchToView(new IHMAdherents(), 2);
                            aAdherent.ihmPrincipale.repaint();
                            aAdherent.ihmPrincipale.revalidate();
                        }
                        new IHMAjoutAbonnement(numero,aAdherent.a,nom,prenom,new IHMAbonnement());
                    }
                    aAdherent.dispose();
                }
            }
        }
        if (ac != null) {
            if (e.getSource() == ac.bSupprimer) {
                String nom = ac.nom.getText();
                String prenom = ac.prenom.getText();
                int result = JOptionPane.showConfirmDialog(null, "Voullez vous vraiment supprimer " + nom + " " + prenom + " définitivement", "Supprimer", JOptionPane.OK_CANCEL_OPTION);
                if (result == 0) {
                    int index = ac.a.jtAdhe.getSelectedRow();
                    int numero = Integer.parseInt(ac.numero.getText());
                    if (ac.a.model.suppAdherent(numero, index) == 0) {
                        ac.removeAll();
                        ac.setBackground(null);
                        ac.repaint();
                        ac.revalidate();
                    }
                }
            }
            if (e.getSource() == ac.bModifier) {
                int numero = Integer.parseInt(ac.numero.getText());

                String nom = ac.nom.getText();
                JTextField tNom = new JTextField(nom, 10);
                tNom.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                tNom.setBackground(Color.white);
                ac.pNom.add(tNom, BorderLayout.CENTER);

                String prenom = ac.prenom.getText();
                JTextField tPrenom = new JTextField(prenom, 10);
                tPrenom.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                tPrenom.setBackground(Color.WHITE);
                ac.pPrenom.add(tPrenom, BorderLayout.CENTER);

                String adresse = ac.adresse.getText();
                JTextField tAdresse = new JTextField(adresse, 10);
                tAdresse.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                tAdresse.setBackground(Color.WHITE);
                ac.pAdresse.add(tAdresse, BorderLayout.CENTER);

                JButton bConfirmer = new JButton("Confirmer");
                bConfirmer.setBackground(new Color(0x005B20));
                bConfirmer.setForeground(Color.WHITE);
                bConfirmer.setPreferredSize(new Dimension(230, 75));
                ac.jpButtons.remove(ac.bModifier);
                ac.jpButtons.add(bConfirmer, BorderLayout.WEST);

                JButton bAnnuler = new JButton("Annuler");
                bAnnuler.setBackground(ColorPalette.SELECTED.getColor());
                bAnnuler.setForeground(Color.WHITE);
                bAnnuler.setPreferredSize(new Dimension(230, 75));
                ac.jpButtons.remove(ac.bSupprimer);
                ac.jpButtons.add(bAnnuler, BorderLayout.EAST);

                ac.repaint();
                ac.revalidate();

                bConfirmer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int result = JOptionPane.showConfirmDialog(null, "Confirmer la modification ? ", "Modifier", JOptionPane.OK_CANCEL_OPTION);
                        if (result == 0) {
                            int index = ac.a.jtAdhe.getSelectedRow();
                            if (ac.a.model.modiffAdherent(tNom.getText(), tPrenom.getText(), tAdresse.getText(), numero, index) == 0) {
                                ac.pNom.remove(tNom);
                                ac.pPrenom.remove(tPrenom);
                                ac.pAdresse.remove(tAdresse);
                                ac.nom.setText(tNom.getText());
                                ac.prenom.setText(tPrenom.getText());
                                ac.adresse.setText(tAdresse.getText());
                                ac.jpButtons.remove(bConfirmer);
                                ac.jpButtons.add(ac.bModifier, BorderLayout.WEST);
                                ac.jpButtons.remove(bAnnuler);
                                ac.jpButtons.add(ac.bSupprimer, BorderLayout.EAST);
                                ac.repaint();
                                ac.revalidate();
                            }
                        }
                    }
                });
                bAnnuler.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ac.pNom.remove(tNom);
                        ac.pPrenom.remove(tPrenom);
                        ac.pAdresse.remove(tAdresse);
                        ac.jpButtons.remove(bConfirmer);
                        ac.jpButtons.add(ac.bModifier, BorderLayout.WEST);
                        ac.jpButtons.remove(bAnnuler);
                        ac.jpButtons.add(ac.bSupprimer, BorderLayout.EAST);
                        ac.repaint();
                        ac.revalidate();
                    }
                });
            }
            if (e.getSource() == ac.bProlonger) {
                ArrayList<String> types = new ArrayList<>();
                String req = "select type from abonnement where numero=" + ac.numero.getText() + " ;";
                IHMAbonnement ab = new IHMAbonnement();
                ResultSet rs = ab.dao.selection(req);
                try {
                    while (rs.next()) {
                        types.add(String.valueOf(rs.getObject(1)));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                IHMProlongement p = new IHMProlongement(Integer.parseInt(ac.numero.getText()), ac.a, ac.nom.getText(), ac.prenom.getText(), types);
            }
            if (e.getSource() == ac.bNouveau) {
                new IHMAjoutAbonnement(Integer.parseInt(ac.numero.getText()), ac.a, ac.nom.getText(), ac.prenom.getText(),new IHMAbonnement());
            }
        }
        if (p != null) {
            if (e.getSource() == p.anneeComboBox) {
                int annee = Integer.parseInt((String) p.anneeComboBox.getSelectedItem());
                int mois;
                int jour;
                if (annee == LocalDate.now().getYear()) {
                    mois = LocalDate.now().getMonthValue();
                    jour = LocalDate.now().getDayOfMonth();
                } else {
                    mois = 1;
                    jour = 1;
                }
                p.moisListe.clear();
                for (int i = mois; i <= 12; i++)
                    p.moisListe.add(String.valueOf(i));
                p.moisComboBox.setModel(new DefaultComboBoxModel<>(p.moisListe.toArray(new String[0])));
                p.joursListe.clear();
                for (int i = jour; i <= Month.of(mois).length(annee % 4 == 0); i++)
                    p.joursListe.add(String.valueOf(i));
                p.jourComboBox.setModel(new DefaultComboBoxModel<>(p.joursListe.toArray(new String[0])));
            } else if (e.getSource() == p.moisComboBox) {
                int annee = Integer.parseInt((String) p.anneeComboBox.getSelectedItem());
                int mois = Integer.parseInt((String) p.moisComboBox.getSelectedItem());
                int jour;
                if (mois == LocalDate.now().getMonthValue() && annee == LocalDate.now().getYear()) {
                    jour = LocalDate.now().getDayOfMonth();
                } else {
                    jour = 1;
                }
                p.joursListe.clear();
                for (int i = jour; i <= Month.of(mois).length(annee % 4 == 0); i++)
                    p.joursListe.add(String.valueOf(i));
                p.jourComboBox.setModel(new DefaultComboBoxModel<>(p.joursListe.toArray(new String[0])));
            } else if (e.getSource() == p.bAnnuler) {
                p.dispose();
            } else if (e.getSource() == p.bAjouter) {
                LocalDate d = LocalDate.of(Integer.parseInt((String) p.anneeComboBox.getSelectedItem()), Integer.parseInt((String) p.moisComboBox.getSelectedItem()), Integer.parseInt((String) p.jourComboBox.getSelectedItem()));
                int retour = JOptionPane.showConfirmDialog(aAdherent,
                        "Êtes-vous sûr de prolenger l'abonnement.",
                        "Valider",
                        JOptionPane.OK_CANCEL_OPTION);
                if (retour == 0) {
                    AbonnementDAO abonnementDAO = new AbonnementDAO(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
                    abonnementDAO.modifierAbonnement(p.numero, Integer.parseInt((String) p.typeComboBox.getSelectedItem()), d);

                    String req2 = "select * from adherents where numero=" + p.numero + " ;";
                    String req3 = "SELECT * FROM abonnement a WHERE a.numero=" + p.numero + " HAVING fin IN (SELECT MAX(fin) FROM abonnement b WHERE a.numero = b.numero ) ;";
                    IHMAbonnement ab = new IHMAbonnement();
                    ResultSet rs2 = p.a.dao.selection(req2);
                    ResultSet rs3 = ab.dao.selection(req3);

                    p.a.jpEast.removeAll();
                    p.a.jpEast.add(new AdherentCard(rs2, p.a.dao, p.a, rs3, ab.dao));
                    p.a.jpEast.repaint();
                    p.a.jpEast.revalidate();
                }
                p.dispose();
            }
        }
        if (ajoutAbonnement != null) {
            if (e.getSource() == ajoutAbonnement.anneeComboBox) {
                int annee = Integer.parseInt((String) ajoutAbonnement.anneeComboBox.getSelectedItem());
                int mois;
                int jour;
                if (annee == LocalDate.now().getYear()) {
                    mois = LocalDate.now().getMonthValue();
                    jour = LocalDate.now().getDayOfMonth();
                } else {
                    mois = 1;
                    jour = 1;
                }
                ajoutAbonnement.moisListe.clear();
                for (int i = mois; i <= 12; i++)
                    ajoutAbonnement.moisListe.add(String.valueOf(i));
                ajoutAbonnement.moisComboBox.setModel(new DefaultComboBoxModel<>(ajoutAbonnement.moisListe.toArray(new String[0])));
                ajoutAbonnement.joursListe.clear();
                for (int i = jour; i <= Month.of(mois).length(annee % 4 == 0); i++)
                    ajoutAbonnement.joursListe.add(String.valueOf(i));
                ajoutAbonnement.jourComboBox.setModel(new DefaultComboBoxModel<>(ajoutAbonnement.joursListe.toArray(new String[0])));
            } else if (e.getSource() == ajoutAbonnement.moisComboBox) {
                int annee = Integer.parseInt((String) ajoutAbonnement.anneeComboBox.getSelectedItem());
                int mois = Integer.parseInt((String) ajoutAbonnement.moisComboBox.getSelectedItem());
                int jour;
                if (mois == LocalDate.now().getMonthValue() && annee == LocalDate.now().getYear()) {
                    jour = LocalDate.now().getDayOfMonth();
                } else {
                    jour = 1;
                }
                ajoutAbonnement.joursListe.clear();
                for (int i = jour; i <= Month.of(mois).length(annee % 4 == 0); i++)
                    ajoutAbonnement.joursListe.add(String.valueOf(i));
                ajoutAbonnement.jourComboBox.setModel(new DefaultComboBoxModel<>(ajoutAbonnement.joursListe.toArray(new String[0])));
            }
            if (e.getSource() == ajoutAbonnement.anneeDebutComboBox) {
                int jour;
                int annee = Integer.parseInt((String) ajoutAbonnement.anneeDebutComboBox.getSelectedItem());
                int mois;
                if (annee == LocalDate.now().getYear()) {
                    mois = LocalDate.now().getMonthValue();
                    jour = LocalDate.now().getDayOfMonth();
                } else {
                    mois = 1;
                    jour = 1;
                }
                ajoutAbonnement.moisDebutListe.clear();
                for (int i = mois; i <= 12; i++)
                    ajoutAbonnement.moisDebutListe.add(String.valueOf(i));
                ajoutAbonnement.moisDebutComboBox.setModel(new DefaultComboBoxModel<>(ajoutAbonnement.moisDebutListe.toArray(new String[0])));
                ajoutAbonnement.joursDebutListe.clear();
                for (int i = jour; i <= Month.of(mois).length(annee % 4 == 0); i++)
                    ajoutAbonnement.joursDebutListe.add(String.valueOf(i));
                ajoutAbonnement.jourDebutComboBox.setModel(new DefaultComboBoxModel<>(ajoutAbonnement.joursDebutListe.toArray(new String[0])));

            } else if (e.getSource() == ajoutAbonnement.moisDebutComboBox) {
                int annee = Integer.parseInt((String) ajoutAbonnement.anneeDebutComboBox.getSelectedItem());
                int mois = Integer.parseInt((String) ajoutAbonnement.moisDebutComboBox.getSelectedItem());
                int jour;
                if (mois == LocalDate.now().getMonthValue() && annee == LocalDate.now().getYear()) {
                    jour = LocalDate.now().getDayOfMonth();
                } else {
                    jour = 1;
                }
                ajoutAbonnement.joursDebutListe.clear();
                for (int i = jour; i <= Month.of(mois).length(annee % 4 == 0); i++)
                    ajoutAbonnement.joursDebutListe.add(String.valueOf(i));
                ajoutAbonnement.jourDebutComboBox.setModel(new DefaultComboBoxModel<>(ajoutAbonnement.joursDebutListe.toArray(new String[0])));
            } else if (e.getSource() == ajoutAbonnement.bAnnuler){
                ajoutAbonnement.dispose();
            } else if (e.getSource() == ajoutAbonnement.bAjouter) {
                int retour = JOptionPane.showConfirmDialog(ajoutAbonnement,  "Êtes-vous sûr d'ajouter un nouveau abonnement de type "+ajoutAbonnement.typeComboBox.getSelectedItem().toString()+".","Valider",
                        JOptionPane.OK_CANCEL_OPTION);
                if (retour == 0) {
                    int type = Integer.parseInt(ajoutAbonnement.typeComboBox.getSelectedItem().toString());
                    LocalDate debut = LocalDate.of(Integer.parseInt(ajoutAbonnement.anneeDebutComboBox.getSelectedItem().toString()),Integer.parseInt(ajoutAbonnement.moisDebutComboBox.getSelectedItem().toString()),Integer.parseInt(ajoutAbonnement.jourDebutComboBox.getSelectedItem().toString()));
                    LocalDate fin = LocalDate.of(Integer.parseInt(ajoutAbonnement.anneeComboBox.getSelectedItem().toString()),Integer.parseInt(ajoutAbonnement.moisComboBox.getSelectedItem().toString()),Integer.parseInt(ajoutAbonnement.jourComboBox.getSelectedItem().toString()));
                    ajoutAbonnement.abonnement.model.insertAbonnement(ajoutAbonnement.numero,type,debut,fin);

                    String req2 = "select * from adherents where numero=" + ajoutAbonnement.numero + " ;";
                    String req3 = "SELECT * FROM abonnement a WHERE a.numero=" + ajoutAbonnement.numero + " HAVING fin IN (SELECT MAX(fin) FROM abonnement b WHERE a.numero = b.numero ) ;";
                    IHMAbonnement ab = new IHMAbonnement();
                    ResultSet rs2 = ajoutAbonnement.a.dao.selection(req2);
                    ResultSet rs3 = ab.dao.selection(req3);

                    ajoutAbonnement.a.jpEast.removeAll();
                    ajoutAbonnement.a.jpEast.add(new AdherentCard(rs2, ajoutAbonnement.a.dao, ajoutAbonnement.a, rs3, ab.dao));
                    ajoutAbonnement.a.jpEast.repaint();
                    ajoutAbonnement.a.jpEast.revalidate();
                    System.out.println("weselna l houni 4");

                }
                ajoutAbonnement.dispose();
            }
        }
    }
}
