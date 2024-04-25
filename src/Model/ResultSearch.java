package Model;

import Model.DAO.AdherentsDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultSearch {
    public JList<String> resultList;
    ResultSet rs;

    public ResultSearch( ResultSet rs) {
        this.rs=rs;
        DefaultListModel<String> listModel = new DefaultListModel<>();
        try {
            while(rs.next()){
                String ligne = String.valueOf(rs.getObject(1))+" "+String.valueOf(rs.getObject(2))+" "+Integer.parseInt(rs.getObject(3).toString());
                listModel.addElement(ligne);
            }
            resultList = new JList<>(listModel);
            resultList.setBackground(new Color(0,0,0,0));
            resultList.setBackground(Color.white);
            resultList.setFont(new Font("SF Pro Text",Font.PLAIN,15));
            resultList.setFixedCellWidth(542);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

