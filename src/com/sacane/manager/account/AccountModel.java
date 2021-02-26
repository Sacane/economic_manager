package com.sacane.manager.account;

import com.sacane.manager.database.DbHandler;
import com.sacane.manager.database.QueryBuilder;
import com.sacane.manager.gui.TableInitializer;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccountModel extends AbstractTableModel {

    private ResultSet accountSet;
    private final DbHandler handler = new DbHandler();
    private AccountService service;
    private List<AccountManager> account;

    public AccountModel(){
        service = AccountService.getInstance();
        account = service.findLastAccount();
    }

    private TableInitializer getInitializer(){
        var titles = new ArrayList<String>();
        titles.add("name account");
        titles.add("amount");

        return new TableInitializer(titles);
    }



    public ResultSet getAccountSet(DbHandler handler) {
        handler.connection();
        try {
            accountSet = handler.getSetAccount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        handler.close();
        return accountSet;
    }

    private Object[] getHeader(){
        return getInitializer().buildTitles();
    }

    @Override
    public int getRowCount() {
        return account.size();
    }

    @Override
    public int getColumnCount() {
        return getHeader().length;
    }


    @Override
    public String getColumnName(int columnIndex) {
        return (String)getHeader()[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return account.get(rowIndex).getName();
            case 1:
                return account.get(rowIndex).getValue();
            default:
                throw new IllegalArgumentException("Index invalid");
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> String.class;
            case 1 -> Double.class;
            default -> Object.class;
        };
    }
}
