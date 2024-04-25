package IHM;

import Config.ColorPalette;
import Controller.Action;
import Controller.KeyAction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class IHMProlongement extends JFrame {
    public IHMAdherents a;
    public JLabel lbNom, lbPrenom;
    public JComboBox<String> jourComboBox, moisComboBox, anneeComboBox,typeComboBox;
    public JButton bAjouter, bAnnuler;
    public JPanel datePanel, nomPanel;
    public ArrayList<String> joursListe, moisListe, anneesListe;
    public int numero;
    public IHMProlongement(int numero, IHMAdherents a,String nom, String prenom,ArrayList<String> types) {
        super("Prolonger un abonnement");
        this.a=a;
        this.numero=numero;
        initialiseComponents(nom,prenom,types);
        createLayout();
        ajouterListeners();
    }
    private void initialiseComponents(String nom,String prenom, ArrayList<String> types) {

        lbNom = new JLabel(nom);
        lbNom.setFont(new Font("SF Pro Text",Font.BOLD,24));
        lbNom.setBorder(new EmptyBorder(0, 30, 30, 0));
        lbNom.setForeground(Color.white);
        lbPrenom = new JLabel(prenom);
        lbPrenom.setFont(new Font("SF Pro Text",Font.BOLD,24));
        lbPrenom.setBorder(new EmptyBorder(0, 30, 30, 0));
        lbPrenom.setForeground(Color.white);

        joursListe = new ArrayList<>();
        for(int i=LocalDate.now().getDayOfMonth();i<=31;i++)
            joursListe.add(String.valueOf(i));
        jourComboBox = new JComboBox<>(joursListe.toArray(new String[0]));
        jourComboBox.setPreferredSize(new Dimension(70,40));

        moisListe = new ArrayList<>();
        for(int i=LocalDate.now().getMonthValue();i<=12;i++)
            moisListe.add(String.valueOf(i));
        moisComboBox = new JComboBox<>(moisListe.toArray(new String[0]));
        moisComboBox.setPreferredSize(new Dimension(70,40));

        anneesListe = new ArrayList<>();
        for(int i=0;i<3;i++)
            anneesListe.add(String.valueOf(LocalDate.now().getYear()+i));
        anneeComboBox = new JComboBox<>(anneesListe.toArray(new String[0]));
        anneeComboBox.setPreferredSize(new Dimension(140,40));

        typeComboBox = new JComboBox<>(types.toArray(new String[0]));

        bAjouter = new JButton("Valider");
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

        nomPanel = new JPanel(new FlowLayout());
        nomPanel.setBackground(new Color(0,0,0,0));
        nomPanel.add(lbNom);
        nomPanel.add(lbPrenom);
        lbPanel.add(nomPanel);

        lbPanel.add(typeComboBox);

        datePanel = new JPanel();
        datePanel.setBackground(new Color(0,0,0,0));
        datePanel.add(new JLabel("Date de fin"));
        datePanel.add(jourComboBox);
        datePanel.add(moisComboBox);
        datePanel.add(anneeComboBox);
        JPanel pDate = new JPanel(new FlowLayout());
        pDate.setBackground(new Color(0,0,0,0));
        pDate.add(datePanel);
        lbPanel.add(pDate);


        JPanel btPanel = new JPanel(new FlowLayout());
        btPanel.setBackground(ColorPalette.PRIMARY.getColor());
        btPanel.setPreferredSize(new Dimension(800,60));
        btPanel.add(bAjouter);
        btPanel.add(bAnnuler);


        this.setLayout(new BorderLayout());
        this.add(lbPanel,BorderLayout.CENTER);
        this.add(btPanel,BorderLayout.SOUTH);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    private void ajouterListeners() {
        bAjouter.addActionListener(new Action(this));
        bAnnuler.addActionListener(new Action(this));
        jourComboBox.addActionListener(new Action(this));
        moisComboBox.addActionListener(new Action(this));
        anneeComboBox.addActionListener(new Action(this));
    }
}
