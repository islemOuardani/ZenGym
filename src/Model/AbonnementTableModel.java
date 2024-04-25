package Model;

import Model.DAO.AbonnementDAO;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AbonnementTableModel extends AbstractTableModel {
    public ArrayList<Object[]> data = new ArrayList<Object[]>();
    ResultSetMetaData rsmd;
    AbonnementDAO dao;

    public AbonnementTableModel(ResultSet rs, AbonnementDAO dao) {
        this.dao=dao;
        try {
            rsmd=rs.getMetaData();
            while(rs.next()){
                Object[] ligne=new Object[rsmd.getColumnCount()];
                for(int i=0;i< ligne.length;i++){
                    ligne[i]=rs.getObject(i+1);
                }
                data.add(ligne);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        try {
            return rsmd.getColumnCount();
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Numero de l'adhérent";
            case 1 -> "Type";
            case 2 -> "Date de début";
            case 3 -> "Date de fin";
            default -> null; // Or some default title
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }
    public int columNameToIndex(String columName){
        for(int i=0;i<getColumnCount();i++)
        {
            if(getColumnName(i).equalsIgnoreCase(columName)){
                return i;
            }
        }
        return -1;
    }

    public void insertAbonnement(int numero, int type, LocalDate dateDebut, LocalDate dateFin){
        int a=dao.insertAbonnement(numero,type,dateDebut,dateFin);
        if(a>0){
            data.add(new Object[]{numero,type,dateDebut,dateFin});
            fireTableDataChanged();
            JOptionPane.showMessageDialog(null,"Ajout success");
        }else{
            JOptionPane.showMessageDialog(null,"Une erreur est survenue");
        }
    }

    public int suppAbonnement(int numero,int type,int index){
        int a=dao.supprimerAbonnement(numero,type);
        if(a>0){
            data.remove(index);
            fireTableDataChanged();
            JOptionPane.showMessageDialog(null,"Supprimé avec succès");
            return 0;
        }else{
            JOptionPane.showMessageDialog(null,"Une erreur est survenue");
            return -1;
        }
    }
}


