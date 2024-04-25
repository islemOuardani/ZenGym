package Model;

import Model.DAO.AdherentsDAO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdherentTableModel extends AbstractTableModel {
    public ArrayList<Object[]> data = new ArrayList<Object[]>();
    ResultSetMetaData rsmd;
    AdherentsDAO dao;
    public AdherentTableModel(ResultSet rs, AdherentsDAO dao){
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
            case 0 -> "Nom";
            case 1 -> "Prénom";
            case 2 -> "Téléphone";
            default -> null; // Or some default title
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(getColumnName(columnIndex).equalsIgnoreCase("Téléphone"))
            return false;
        else
            return true;
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
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String nom=getValueAt(rowIndex,columNameToIndex("Nom")).toString();
        String prenom=getValueAt(rowIndex,columNameToIndex("Prénom")).toString();
        int numero=Integer.parseInt(getValueAt(rowIndex,columNameToIndex("Téléphone")).toString());

        if(getColumnName(columnIndex).equalsIgnoreCase("nom")) {
            nom= aValue.toString();
        }else if (getColumnName(columnIndex).equalsIgnoreCase("prenom")){
            prenom=aValue.toString();
        }
        int a=dao.modifierAdherent(nom,prenom,numero);
        //mise a jour de la base de donnée
        data.get(rowIndex)[columnIndex]=aValue;
    }
    public void insertAdherent(String nom, String prenom, int numero, LocalDate naissance,String adresse){
        int a=dao.insertAdherents(nom,prenom,numero,naissance,adresse);
        if(a>0){
            data.add(new Object[]{nom,prenom,numero,naissance});
            fireTableDataChanged();
            JOptionPane.showMessageDialog(null,"Ajout success");
        }else{
            JOptionPane.showMessageDialog(null,"Une erreur est survenue");
        }
    }

    public int suppAdherent(int numero,int index){
        int a=dao.supprimerAdherent(numero);
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
    public int modiffAdherent(String nom, String prenom, String adresse, int numero,int index){

        int a=dao.modifierAdherents(nom,prenom,numero,adresse);
        if(a>0){
            data.get(index)[0]=nom;
            data.get(index)[1]=prenom;
            fireTableDataChanged();
            return 0;
        }else {
            JOptionPane.showMessageDialog(null,"Une erreur est survenue");
            return -1;
        }

    }
}
