package IHM;
import Controller.Action;
import Controller.MouseAction;
import Model.DAO.*;
import Model.*;
import Config.*;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.ResultSet;

public class IHMAdherents extends JPanel {
    public JTable jtAdhe;
    public AdherentTableModel model;
    public JPanel jpEast;
    public AdherentsDAO dao;
    public IHMAdherents() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1250,680));
        this.setBackground(ColorPalette.BACKGROUND.getColor());
        jtAdhe=new JTable();
        JScrollPane jsp=new JScrollPane(jtAdhe);
        jsp.getViewport().setOpaque(false);
        jsp.setOpaque(false);
        jtAdhe.setBackground(Color.WHITE);
        this.add(jsp,BorderLayout.CENTER);

        dao=new AdherentsDAO(DBConfig.URL,DBConfig.USERNAME,DBConfig.PASSWORD);
        String req="select nom, prenom , numero from adherents;";
        ResultSet rs = dao.selection(req);
        model=new AdherentTableModel(rs,dao);
        jtAdhe.setModel(model);
        jtAdhe.getTableHeader().setReorderingAllowed(false);
        jtAdhe.getTableHeader().setResizingAllowed(false);
        jtAdhe.getTableHeader().setFont(new Font("SF Pro Text",Font.BOLD,18));
        jtAdhe.getTableHeader().setBackground(ColorPalette.PRIMARY.getColor());
        jtAdhe.getTableHeader().setForeground(Color.WHITE);
        jtAdhe.setRowHeight(50);
        jtAdhe.setFont(new Font("SF Pro Text",Font.PLAIN,14));


        jpEast=new JPanel(new BorderLayout());
        jpEast.setPreferredSize(new Dimension(500,800));
        jpEast.setBackground(ColorPalette.BACKGROUND.getColor());
        this.add(jpEast,BorderLayout.EAST);
        jtAdhe.addMouseListener(new MouseAction(this));
    }
}
