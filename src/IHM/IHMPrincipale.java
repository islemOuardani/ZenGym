package IHM;

import Config.ColorPalette;
import Config.DBConfig;
import Controller.*;
import Controller.Action;
import Model.DAO.AdherentsDAO;
import Model.Discussion;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;


public class IHMPrincipale extends JFrame {
    public String login;
    public AdherentsDAO dao=new AdherentsDAO(DBConfig.URL,DBConfig.USERNAME,DBConfig.PASSWORD);
    public JPanel pHeader;
    public JPanel pLogo;
    public JLabel lbIcon,lbLogo,espace1,espace2;
    public JPanel pSearch;
    public JTextField tfSearch;
    public JLabel lbSearch;
    public JPanel pHEast;
    public JLabel lbNom;
    public JPanel pSideMenu;
    public JPanel pHome, pAdherents, pPersonnels, pAbonnements;
    public JLabel lbHomeIcon, lbAdherentsIcon, lbPersonnelsIcon, lbAbonnementsIcon, lbMsgIcon;
    public JLabel lbHome, lbAdherents, lbPersonnels, lbAbonnements, lbHelp, lbLogout;
    public JPanelController pContenant;
    JMenuBar menuBar;
    JMenu menuAjouter;
    JMenuItem menuAdherent;
    public Discussion d;
    public IHMPrincipale(String login,String nom)  {
        this.setTitle("ZenGym");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.login = login;

        menuBar = new JMenuBar();
        menuAjouter = new JMenu("Ajouter");
        menuAjouter.setFont(new Font("Times New Roman",Font.BOLD,16));
        menuAdherent = new JMenuItem("Nouvel Adhérent");
        menuAdherent.setFont(new Font("Times New Roman",Font.PLAIN,14));
        menuAjouter.add(menuAdherent);
        menuBar.add(menuAjouter);
        this.setJMenuBar(menuBar);
        //header
        pHeader = new JPanel(new FlowLayout());
        pLogo = new JPanel(new FlowLayout());
        pLogo.setBackground(new Color(0,0,0,0));
        lbIcon = new JLabel(new FlatSVGIcon("IHM/ressources/logo.svg",60,50));
        lbIcon.setBorder(new EmptyBorder(20, 0, 0, 0));
        lbLogo = new JLabel("ZENGYM");
        lbLogo.setBorder(new EmptyBorder(35, 0, 0, 20));
        lbLogo.setFont(new Font("Franklin Gothic Demi Cond",Font.BOLD|Font.ITALIC,40));
        lbLogo.setForeground(Color.white);
        pLogo.setPreferredSize(new Dimension(250,100));
        pLogo.add(lbIcon);
        pLogo.add(lbLogo);
        pHeader.add(pLogo);

        espace1 = new JLabel();
        espace1.setPreferredSize(new Dimension(110,100));
        pHeader.add(espace1);

        pSearch = new JPanel(new FlowLayout());
        pSearch.setBackground(new Color(0,0,0,0));
        tfSearch= new JTextField("Cherchez un adhérent par numéro de téléphone",50);
        tfSearch.setPreferredSize(new Dimension(50,40));
        tfSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
        tfSearch.setFocusable(false);
        pSearch.add(tfSearch);
        lbSearch = new JLabel(new FlatSVGIcon("IHM/ressources/search2.svg",28,28));
        pSearch.setBackground(Color.white);
        pSearch.add(lbSearch);
        pHeader.add(pSearch);

        espace2 = new JLabel();
        espace2.setPreferredSize(new Dimension(250,100));
        pHeader.add(espace2);

        pHEast= new JPanel(new FlowLayout());
        pHEast.setBackground(new Color(255,255,255,0));
        lbNom=new JLabel(nom);
        lbNom.setFont(new Font("SF Pro Text",Font.BOLD,18));
        lbNom.setForeground(Color.white);
        lbNom.setBorder(new EmptyBorder(0, 20, 0, 10));
        pHEast.add(lbNom);
        lbMsgIcon = new JLabel(new FlatSVGIcon("IHM/ressources/message.svg",40,40));
        pHeader.add(lbMsgIcon);
        pHeader.add(pHEast);
        pHeader.setPreferredSize(new Dimension(500,100));
        pHeader.setBackground(ColorPalette.PRIMARY.getColor());
        this.add(pHeader,BorderLayout.NORTH);

        //side Bar
        pSideMenu=new JPanel(new GridLayout(10,1));
        pSideMenu.setBackground(ColorPalette.SECONDARY.getColor());
        pSideMenu.setPreferredSize(new Dimension(250,0));
        pSideMenu.setBorder(new EmptyBorder(60, 0, 20, 0));

        pHome=new JPanel(new FlowLayout());
        pHome.setBackground(ColorPalette.SELECTED.getColor());
        lbHome=new JLabel("Tableau de bord");
        lbHome.setFont(new Font("SF Pro Text",Font.BOLD,18));
        lbHome.setForeground(Color.white);
        lbHome.setBorder(new EmptyBorder(15, 0, 0, 0));
        lbHomeIcon= new JLabel(new FlatSVGIcon("IHM/ressources/home.svg",50,50));
        pHome.add(lbHomeIcon);
        pHome.add(lbHome);

        pAdherents=new JPanel(new FlowLayout());
        pAdherents.setBackground(new Color(0,0,0,0));
        lbAdherents=new JLabel("Adhérents");
        lbAdherents.setFont(new Font("SF Pro Text",Font.BOLD,18));
        lbAdherents.setForeground(Color.white);
        lbAdherents.setBorder(new EmptyBorder(15, 30, 0, 0));
        lbAdherentsIcon= new JLabel(new FlatSVGIcon("IHM/ressources/adherents.svg",45,45));
        lbAdherentsIcon.setBorder(new EmptyBorder(0, 0, 0, 20));
        pAdherents.add(lbAdherentsIcon);
        pAdherents.add(lbAdherents);

       /* pPersonnels=new JPanel(new FlowLayout());
        pPersonnels.setBackground(new Color(0,0,0,0));;
        lbPersonnels=new JLabel("Personnels");
        lbPersonnels.setFont(new Font("SF Pro Text",Font.BOLD,18));
        lbPersonnels.setForeground(Color.white);
        lbPersonnels.setBorder(new EmptyBorder(15, 20, 0, 0));
        lbPersonnelsIcon= new JLabel(new FlatSVGIcon("IHM/ressources/personnels.svg",50,50));
        lbPersonnelsIcon.setBorder(new EmptyBorder(0, 0, 0, 20));
        pPersonnels.add(lbPersonnelsIcon);
        pPersonnels.add(lbPersonnels);*/

        pAbonnements=new JPanel(new FlowLayout());
        pAbonnements.setBackground(new Color(0,0,0,0));
        lbAbonnements=new JLabel("Abonnements");
        lbAbonnements.setFont(new Font("SF Pro Text",Font.BOLD,18));
        lbAbonnements.setForeground(Color.white);
        lbAbonnements.setBorder(new EmptyBorder(15, 0, 0, 0));
        lbAbonnementsIcon= new JLabel(new FlatSVGIcon("IHM/ressources/abonnements.svg",40,40));
        lbAbonnementsIcon.setBorder(new EmptyBorder(0, 0, 0, 20));
        pAbonnements.add(lbAbonnementsIcon);
        pAbonnements.add(lbAbonnements);

        lbHelp = new JLabel("Aide");
        lbHelp.setFont(new Font("SF Pro Text",Font.BOLD,18));
        lbHelp.setForeground(Color.white);
        lbHelp.setHorizontalAlignment(JLabel.RIGHT);
        lbHelp.setBorder(new EmptyBorder(0, 0, 0, 30));

        lbLogout = new JLabel("Déconnecté");
        lbLogout.setFont(new Font("SF Pro Text",Font.BOLD,18));
        lbLogout.setForeground(Color.RED);
        lbLogout.setHorizontalAlignment(JLabel.RIGHT);
        lbLogout.setBorder(new EmptyBorder(0, 0, 0, 30));

        pSideMenu.add(pHome);
        pSideMenu.add(new JLabel());
        pSideMenu.add(pAdherents);
        pSideMenu.add(new JLabel());
        //pSideMenu.add(pPersonnels);
        //pSideMenu.add(new JLabel());
        pSideMenu.add(pAbonnements);
        pSideMenu.add(new JLabel());
        pSideMenu.add(new JLabel());
        pSideMenu.add(new JLabel());
        pSideMenu.add(lbHelp);
        pSideMenu.add(lbLogout);
        this.add(pSideMenu,BorderLayout.WEST);

        pContenant = new JPanelController();
        pContenant.setBackground(ColorPalette.BACKGROUND.getColor());
        pContenant.switchToView(new IHMHome(),1);
        this.add(pContenant);

        lbHelp.addMouseListener(new MouseAction(this));
        pHome.addMouseListener(new MouseAction(this));
        pAdherents.addMouseListener(new MouseAction(this));
        //pPersonnels.addMouseListener(new MouseAction(this));
        pAbonnements.addMouseListener(new MouseAction(this));
        tfSearch.addFocusListener(new FocusAction(this));
        tfSearch.addKeyListener(new KeyAction(this));
        lbMsgIcon.addMouseListener(new MouseAction(this));
        menuAdherent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IHMAjoutAdherent a=new IHMAjoutAdherent(new IHMAdherents(),IHMPrincipale.this);
            }
        });
        this.setVisible(true);
        lbLogout.addMouseListener(new MouseAction(this));
    }
}
