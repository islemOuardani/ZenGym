package IHM;

import Config.ColorPalette;
import Controller.Authentication;
import Controller.ChatRemote;
import Model.Chat;
import Model.ImagePanel;
import Model.SvgPanel;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class IHMLogin extends JFrame {

    public JLabel lbLogin, lbMdp;
    public JTextField tfLogin, tfMdp;
    public JButton bLogin;
    public JPanel lbPanel;
    public ImagePanel bgPanel;
    public JPanel pNorth;

    public IHMLogin() {
        super("ZenGym");
        initialiseComponents();
        createLayout();
        ajouterListeners();
    }
    private void initialiseComponents() {
        lbLogin = new JLabel("Login :");
        lbLogin.setFont(new Font("SF Pro Text",Font.BOLD,18));
        lbLogin.setBorder(new EmptyBorder(0, 30, 0, 0));
        lbLogin.setForeground(Color.WHITE);
        lbMdp = new JLabel("Mot de passe :");
        lbMdp.setFont(new Font("SF Pro Text",Font.BOLD,18));
        lbMdp.setBorder(new EmptyBorder(0, 30, 0, 0));
        lbMdp.setForeground(Color.WHITE);

        tfLogin = new JTextField();
        tfLogin.setPreferredSize(new Dimension(200,40));
        tfMdp = new JPasswordField();
        tfMdp.setPreferredSize(new Dimension(200,40));

        bLogin = new JButton("Connecter");
        bLogin.setForeground(Color.white);
        bLogin.setBackground(ColorPalette.SECONDARY.getColor());
        bLogin.setFont(new Font("SF Pro Text",Font.BOLD,18));
        bLogin.setPreferredSize(new Dimension(150,30));
    }
    private void createLayout() {
        lbPanel = new JPanel(new GridLayout(2,2));
        lbPanel.setPreferredSize(new Dimension(583,447));
        lbPanel.setBackground(new Color(0,0,0,0));
        lbPanel.add(lbLogin);

        JPanel pLogin = new JPanel(new FlowLayout());
        pLogin.setBackground(new Color(0,0,0,0));
        pLogin.setBorder(new EmptyBorder(50, 0, 0, 0));
        pLogin.add(tfLogin);
        lbPanel.add(pLogin);

        lbPanel.add(lbMdp);
        JPanel pMdp = new JPanel(new FlowLayout());
        pMdp.setBorder(new EmptyBorder(50, 0, 0, 0));
        pMdp.setBackground(new Color(0,0,0,0));
        pMdp.add(tfMdp);
        lbPanel.add(pMdp);

        JPanel btPanel = new JPanel(new FlowLayout());
        btPanel.setPreferredSize(new Dimension(500,60));
        btPanel.setBackground(new Color(0,0,0,0));
        btPanel.add(bLogin);
        bgPanel = new ImagePanel("C:\\Users\\islem\\Desktop\\ING A1\\Semestre 2\\Programmation OO\\ZenGym\\ressources\\bgLogin.png");
        JPanel north = new JPanel();
        north.setPreferredSize(new Dimension(800,61));
        north.setBackground(new Color(0,0,0,0));
        JPanel west = new JPanel();
        west.setPreferredSize(new Dimension(120,569));
        west.setBackground(new Color(0,0,0,0));
        JPanel south = new JPanel();
        south.setPreferredSize(new Dimension(800,61));
        south.setBackground(new Color(0,0,0,0));
        JPanel east = new JPanel();
        east.setPreferredSize(new Dimension(120,569));
        east.setBackground(new Color(0,0,0,0));
        bgPanel.add(north,BorderLayout.NORTH);
        bgPanel.add(west,BorderLayout.WEST);
        bgPanel.add(south,BorderLayout.SOUTH);
        bgPanel.add(east,BorderLayout.EAST);

        pNorth = new JPanel(new BorderLayout());
        pNorth.setBackground(new Color(0,0,0,0));
        JPanel pLogo = new JPanel(new FlowLayout());
        pLogo.setBackground(new Color(0,0,0,0));
        pLogo.setPreferredSize(new Dimension(80,93));
        JLabel lbIcon = new JLabel(new FlatSVGIcon("IHM/ressources/logoJaune.svg",61,58));
        lbIcon.setBorder(new EmptyBorder(20, 0, 0, 0));
        JLabel lbLogo = new JLabel("ZENGYM");
        lbLogo.setBorder(new EmptyBorder(35, 0, 0, 20));
        lbLogo.setFont(new Font("Franklin Gothic Demi Cond",Font.BOLD|Font.ITALIC,25));
        lbLogo.setForeground(Color.white);
        pLogo.setPreferredSize(new Dimension(80,93));
        pLogo.add(lbIcon);
        pLogo.add(lbLogo);
        pNorth.add(pLogo);

        SvgPanel bgForm = new SvgPanel("IHM/ressources/bgForm.svg");
        bgForm.setBackground(new Color(0,0,0,0));

        bgForm.add(lbPanel,BorderLayout.CENTER);
        bgForm.add(btPanel,BorderLayout.SOUTH);
        bgForm.add(pNorth,BorderLayout.NORTH);
        bgPanel.add(bgForm,BorderLayout.CENTER);

        this.add(bgPanel);
        setSize(800,569);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void ajouterListeners() {
        bLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
            }
        });
        tfMdp.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    connect();
                }
            }
        });
    }
    public void connect(){
        try {
            System.out.println("Authentification...");
            String url = "rmi://127.0.0.1:9001/login";
            Authentication auth = (Authentication) Naming.lookup(url);
            int authent = auth.authenticate(tfLogin.getText(),tfMdp.getText());
            if(authent == 0){
                JLabel msg = new JLabel("Mot de passe incorrecte");
                msg.setForeground(new Color(0xFF0000));
                msg.setFont(new Font("SF Pro Text",Font.PLAIN,15));
                msg.setBorder(new EmptyBorder(0,100,0,0));
                this.pNorth.add(msg,BorderLayout.SOUTH);
                this.revalidate();
                this.repaint();
            }else if (authent == 1){
                new IHMPrincipale(tfLogin.getText(),auth.getNom(tfLogin.getText()));
                this.dispose();
            }else{
                JLabel msg = new JLabel("Ce compte est déjà ouvert dans une autre application");
                msg.setForeground(new Color(0xFF0000));
                msg.setFont(new Font("SF Pro Text",Font.PLAIN,15));
                msg.setBorder(new EmptyBorder(0,100,0,0));
                this.pNorth.add(msg,BorderLayout.SOUTH);
                this.revalidate();
                this.repaint();
            }
        } catch (NotBoundException ex) {
            throw new RuntimeException(ex);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }
}
