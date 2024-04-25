package IHM;

import Config.ColorPalette;
import Config.DBConfig;
import Model.AbonnementTableModel;
import Model.AdherentTableModel;
import Model.DAO.AbonnementDAO;
import Model.DAO.AdherentsDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class IHMAbonnement extends JPanel {
    public JTable jtAbon;
    public AbonnementDAO dao;
    public AbonnementTableModel model;
    public IHMAbonnement() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1250,680));
        this.setBackground(ColorPalette.BACKGROUND.getColor());
        jtAbon=new JTable();
        JScrollPane jsp=new JScrollPane(jtAbon);
        jsp.getViewport().setOpaque(false);
        jsp.setOpaque(false);
        jtAbon.setBackground(Color.WHITE);
        this.add(jsp,BorderLayout.CENTER);

        dao=new AbonnementDAO(DBConfig.URL,DBConfig.USERNAME,DBConfig.PASSWORD);
        String req="select * from abonnement;";
        ResultSet rs = dao.selection(req);
        model=new AbonnementTableModel(rs,dao);
        jtAbon.setModel(model);
        jtAbon.getTableHeader().setReorderingAllowed(false);
        jtAbon.getTableHeader().setResizingAllowed(false);
        jtAbon.getTableHeader().setFont(new Font("SF Pro Text",Font.BOLD,18));
        jtAbon.getTableHeader().setBackground(ColorPalette.PRIMARY.getColor());
        jtAbon.getTableHeader().setForeground(Color.WHITE);
        jtAbon.setRowHeight(50);
        jtAbon.setFont(new Font("SF Pro Text",Font.PLAIN,14));
    }
}
