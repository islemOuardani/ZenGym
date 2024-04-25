package IHM;

import Config.ColorPalette;
import Controller.Action;
import Controller.KeyAction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class IHMAjoutAdherent extends JFrame {
    public IHMPrincipale ihmPrincipale;
    public IHMAdherents a;
    public int numValide;
    public JLabel lbNom, lbPrenom, lbNumero, lbNaissance,lbAdresse;
    public JTextField tfNom, tfPrenom, tfNumero,tfAdresse;
    public JComboBox<String> jourComboBox, moisComboBox, anneeComboBox;
    public JButton bAjouter, bAnnuler;
    public JPanel naissancePanel;
    public String[] joursListe, moisListe, anneesListe;
    public IHMAjoutAdherent(IHMAdherents a, IHMPrincipale ihmPrincipale) {
        super("Ajout d'un adhérent");
        this.a=a;
        this.ihmPrincipale = ihmPrincipale;
        initialiseComponents();
        createLayout();
        ajouterListeners();
    }

    private void initialiseComponents() {

        lbNom = new JLabel("Nom :");
        lbNom.setFont(new Font("SF Pro Text",Font.BOLD,24));
        lbNom.setBorder(new EmptyBorder(0, 30, 30, 0));
        lbNom.setForeground(Color.white);
        lbPrenom = new JLabel("Prénom :");
        lbPrenom.setFont(new Font("SF Pro Text",Font.BOLD,24));
        lbPrenom.setBorder(new EmptyBorder(0, 30, 30, 0));
        lbPrenom.setForeground(Color.white);
        lbNumero = new JLabel("Numéro de téléphone :");
        lbNumero.setFont(new Font("SF Pro Text",Font.BOLD,24));
        lbNumero.setBorder(new EmptyBorder(0, 30, 30, 0));
        lbNumero.setForeground(Color.white);
        lbNaissance = new JLabel("Date de naissance :");
        lbNaissance.setFont(new Font("SF Pro Text",Font.BOLD,24));
        lbNaissance.setBorder(new EmptyBorder(0, 30, 30, 0));
        lbNaissance.setForeground(Color.white);
        lbAdresse = new JLabel("Adresse :");
        lbAdresse.setFont(new Font("SF Pro Text",Font.BOLD,24));
        lbAdresse.setBorder(new EmptyBorder(0, 30, 30, 0));
        lbAdresse.setForeground(Color.white);

        tfNom = new JTextField();
        tfNom.setPreferredSize(new Dimension(280,40));
        tfPrenom = new JTextField();
        tfPrenom.setPreferredSize(new Dimension(280,40));
        tfNumero = new JTextField();
        tfNumero.setPreferredSize(new Dimension(280,40));
        tfAdresse = new JTextField();
        tfAdresse.setPreferredSize(new Dimension(280,40));

        joursListe = new String[31];
        for(int i=1;i<=31;i++)
            joursListe[i-1]=String.valueOf(i);
        jourComboBox = new JComboBox<>(joursListe);
        jourComboBox.setPreferredSize(new Dimension(70,40));
        moisListe = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        moisComboBox = new JComboBox<>(moisListe);
        moisComboBox.setPreferredSize(new Dimension(70,40));
        anneesListe = new String[100];
        for(int i=0;i<100;i++)
            anneesListe[i]=String.valueOf(1925+i);
        anneeComboBox = new JComboBox<>(anneesListe);
        anneeComboBox.setPreferredSize(new Dimension(140,40));

        bAjouter = new JButton("Ajouter");
        bAjouter.setForeground(Color.white);
        bAjouter.setBackground(new Color(0x038C0E));
        bAjouter.setFont(new Font("SF Pro Text",Font.BOLD,18));
        bAjouter.setPreferredSize(new Dimension(150,30));
        bAnnuler = new JButton("Annuler");
        bAnnuler.setForeground(Color.white);
        bAnnuler.setBackground(ColorPalette.SECONDARY.getColor());
        bAnnuler.setFont(new Font("SF Pro Text",Font.BOLD,18));
        bAnnuler.setPreferredSize(new Dimension(150,30));

    }


    private void createLayout() {
        JPanel lbPanel = new JPanel(new GridLayout(7,1));
        lbPanel.setBackground(ColorPalette.SECONDARY.getColor());
        lbPanel.setPreferredSize(new Dimension(300,600));
        lbPanel.add(new JLabel());
        lbPanel.add(lbNom);
        lbPanel.add(lbPrenom);
        lbPanel.add(lbNumero);
        lbPanel.add(lbNaissance);
        lbPanel.add(lbAdresse);

        JPanel tfPanel = new JPanel(new GridLayout(7,1));
        tfPanel.setBackground(ColorPalette.BACKGROUND.getColor());
        JPanel pNom = new JPanel(new FlowLayout());
        pNom.setBackground(new Color(0,0,0,0));
        pNom.add(tfNom);
        JPanel pPrenom = new JPanel(new FlowLayout());
        pPrenom.setBackground(new Color(0,0,0,0));
        pPrenom.add(tfPrenom);
        JPanel pNumero = new JPanel(new FlowLayout());
        pNumero.setBackground(new Color(0,0,0,0));
        pNumero.add(tfNumero);
        JPanel pAdresse = new JPanel(new FlowLayout());
        pAdresse.setBackground(new Color(0,0,0,0));
        pAdresse.add(tfAdresse);
        naissancePanel = new JPanel();
        naissancePanel.setBackground(new Color(0,0,0,0));
        naissancePanel.add(jourComboBox);
        naissancePanel.add(moisComboBox);
        naissancePanel.add(anneeComboBox);
        JPanel pDate = new JPanel(new FlowLayout());
        pDate.setBackground(new Color(0,0,0,0));
        pDate.add(naissancePanel);

        tfPanel.add(new JLabel());
        tfPanel.add(pNom);
        tfPanel.add(pPrenom);
        tfPanel.add(pNumero);
        tfPanel.add(pDate);
        tfPanel.add(pAdresse);

        JPanel btPanel = new JPanel(new FlowLayout());
        btPanel.setBackground(ColorPalette.PRIMARY.getColor());
        btPanel.setPreferredSize(new Dimension(800,60));
        btPanel.add(bAjouter);
        btPanel.add(bAnnuler);


        this.setLayout(new BorderLayout());
        this.add(lbPanel,BorderLayout.WEST);
        this.add(tfPanel,BorderLayout.CENTER);
        this.add(btPanel,BorderLayout.SOUTH);
        setSize(800,700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        numValide=0;
    }
    private void ajouterListeners() {
        bAjouter.addActionListener(new Action(this));
        bAnnuler.addActionListener(new Action(this));
        jourComboBox.addActionListener(new Action(this));
        moisComboBox.addActionListener(new Action(this));
        anneeComboBox.addActionListener(new Action(this));
        tfNumero.addKeyListener(new KeyAction(this));
        tfNom.addKeyListener(new KeyAction(this));
        tfPrenom.addKeyListener(new KeyAction(this));
    }
}
