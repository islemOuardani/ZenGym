package Model;

import Config.ColorPalette;
import Controller.Action;
import IHM.IHMAdherents;
import Model.DAO.AbonnementDAO;
import Model.DAO.AdherentsDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdherentCard extends JPanel {
    public IHMAdherents a;
    AdherentsDAO dao;
    AbonnementDAO dao2;
    ResultSet rs,rs2;
    public JPanel pNom;
    public JPanel pPrenom;
    JPanel pNaissance;
    JPanel pNumero;
    public JPanel pAdresse;
    JPanel pDateAjout;
    public JLabel nom;
    public JLabel lbNom;
    public JLabel prenom;
    public JLabel lbPrenom;
    JLabel naissance;
    JLabel lbNaissance;
    public JLabel numero;
    JLabel lbNumero;
    public JLabel adresse;
    public JLabel lbAdresse;
    JLabel dateAjout;
    JLabel lbDateAjout;
    public JPanel jpButtons,jpButtonsAb;
    public JButton bModifier,bSupprimer,bProlonger,bNouveau;
    String type, debut,fin;
    JLabel lbAbonnement;

    public AdherentCard(ResultSet rs, AdherentsDAO dao, IHMAdherents a, ResultSet rs2, AbonnementDAO dao2){
        this.a = a;
        this.dao = dao;
        this.rs = rs;
        this.dao2 = dao2;
        this.rs2 = rs2;
        this.setLayout(new GridLayout(9,1));
        this.setBackground(ColorPalette.BACKGROUND.getColor());
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        pNom = new JPanel(new BorderLayout());
        pNom.setBackground(new Color(0,0,0,0));
        lbNom = new JLabel("Nom: ");
        lbNom.setFont(new Font("Times New Roman",Font.BOLD,20));
        pNom.add(lbNom,BorderLayout.WEST);
        pPrenom = new JPanel(new BorderLayout());
        pPrenom.setBackground(ColorPalette.BACKGROUND.getColor());
        lbPrenom = new JLabel("Prénom : ");
        lbPrenom.setFont(new Font("Times New Roman",Font.BOLD,20));
        pPrenom.add(lbPrenom,BorderLayout.WEST);
        pNaissance = new JPanel(new BorderLayout());
        pNaissance.setBackground(ColorPalette.BACKGROUND.getColor());
        lbNaissance = new JLabel("Date de naissance : ");
        lbNaissance.setFont(new Font("Times New Roman",Font.BOLD,20));
        pNaissance.add(lbNaissance,BorderLayout.WEST);
        pNumero = new JPanel(new BorderLayout());
        pNumero.setBackground(ColorPalette.BACKGROUND.getColor());
        lbNumero = new JLabel("Numéro de téléphone : ");
        lbNumero.setFont(new Font("Times New Roman",Font.BOLD,20));
        pNumero.add(lbNumero,BorderLayout.WEST);
        pAdresse = new JPanel(new BorderLayout());
        pAdresse.setBackground(ColorPalette.BACKGROUND.getColor());
        lbAdresse = new JLabel("Adresse : ");
        lbAdresse.setFont(new Font("Times New Roman",Font.BOLD,20));
        pAdresse.add(lbAdresse,BorderLayout.WEST);
        pDateAjout = new JPanel(new BorderLayout());
        pDateAjout.setBackground(ColorPalette.BACKGROUND.getColor());
        lbDateAjout = new JLabel("Inscrit depuis : ");
        lbDateAjout.setFont(new Font("Times New Roman",Font.BOLD,20));
        pDateAjout.add(lbDateAjout,BorderLayout.WEST);
        try {
            while(rs.next()){

                nom = new JLabel(String.valueOf(rs.getObject(1)));
                nom.setFont(new Font("Times New Roman",Font.PLAIN,20));
                prenom = new JLabel(String.valueOf(rs.getObject(2)));
                prenom.setFont(new Font("Times New Roman",Font.PLAIN,20));
                numero = new JLabel(String.valueOf(rs.getObject(3)));
                numero.setFont(new Font("Times New Roman",Font.PLAIN,20));
                naissance = new JLabel(String.valueOf(rs.getObject(4)));
                naissance.setFont(new Font("Times New Roman",Font.PLAIN,20));
                adresse = new JLabel(String.valueOf(rs.getObject(5)));
                adresse.setFont(new Font("Times New Roman",Font.PLAIN,20));
                dateAjout = new JLabel(String.valueOf(rs.getObject(6)));
                dateAjout.setFont(new Font("Times New Roman",Font.PLAIN,20));
                pNom.add(nom);
                pPrenom.add(prenom);
                pNaissance.add(naissance);
                pNumero.add(numero);
                pAdresse.add(adresse);
                pDateAjout.add(dateAjout);
            }
            while(rs2.next()){
                type = String.valueOf(rs2.getObject(2));
                debut = String.valueOf(rs2.getObject(3));
                fin = String.valueOf(rs2.getObject(4));
            }
            this.add(pNom);
            this.add(pPrenom);
            this.add(pNaissance);
            this.add(pNumero);
            this.add(pAdresse);
            this.add(pDateAjout);

            if (type == null){
                lbAbonnement = new JLabel("Cet adhérent n'a aucun abonnement ");
            }else{
                if (LocalDate.parse(fin,DateTimeFormatter.ofPattern("yyyy-MM-dd")).isBefore(LocalDate.now())){
                    lbAbonnement = new JLabel("Le dernier abonnement était du type "+type+" du "+debut+" au "+ fin);
                }else{
                    lbAbonnement = new JLabel("L'abonnement actuel est du type "+type+" depuis "+debut+" jusqu'au "+ fin);
                }
            }
            lbAbonnement.setFont(new Font("Times New Roman",Font.PLAIN,15));
            this.add(lbAbonnement);

            jpButtonsAb = new JPanel(new BorderLayout());
            bNouveau = new JButton("Nouveau abonnement");
            bNouveau.setBackground(new Color(0x4A6A75));
            bNouveau.setForeground(Color.WHITE);
            if (type == null){
                bNouveau.setPreferredSize(new Dimension(460,75));
            }else {
                bNouveau.setPreferredSize(new Dimension(230,75));
                bProlonger = new JButton("Prolonger l'abonnement");
                bProlonger.setBackground(new Color(0xAF690E));
                bProlonger.setForeground(Color.WHITE);
                bProlonger.setPreferredSize(new Dimension(230,75));
                jpButtonsAb.add(bProlonger,BorderLayout.EAST);
            }
            jpButtonsAb.add(bNouveau,BorderLayout.WEST);
            this.add(jpButtonsAb);

            jpButtons = new JPanel(new BorderLayout());
            bModifier = new JButton("Modifier");
            bModifier.setBackground(ColorPalette.TABLE.getColor());
            bModifier.setForeground(Color.WHITE);
            bModifier.setPreferredSize(new Dimension(230,75));
            jpButtons.add(bModifier,BorderLayout.WEST);
            bSupprimer = new JButton("Supprimer");
            bSupprimer.setBackground(ColorPalette.SELECTED.getColor());
            bSupprimer.setForeground(Color.WHITE);
            bSupprimer.setPreferredSize(new Dimension(230,75));
            jpButtons.add(bSupprimer,BorderLayout.EAST);
            this.add(jpButtons);

            bSupprimer.addActionListener(new Action(this));
            bModifier.addActionListener(new Action(this));
            if(bProlonger != null){
                bProlonger.addActionListener(new Action(this));
            }
            bNouveau.addActionListener(new Action(this));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
