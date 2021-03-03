package com.sacane.manager.income;

import com.google.common.collect.Table;
import com.sacane.manager.gui.ModelWrapper;
import com.sacane.manager.gui.TableInitializer;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class IncomeModel extends AbstractTableModel {

    private List<IncomeManager> income;

    private ModelWrapper wrapper;
    private IncomeService service;
    private TableInitializer initializer;

    public IncomeModel(ModelWrapper wrapper){
        this.wrapper = wrapper;
        this.service = IncomeService.getInstance(wrapper);
        this.initializer = getInitializer();
        income = service.findLastIncome();

    }



    private TableInitializer getInitializer(){
        var titles = new ArrayList<String>();
        titles.add("Label");
        titles.add("cost");
        titles.add("date");

        return new TableInitializer(titles);
    }

    private Object[] getHeader(){
        return initializer.buildTitles();
    }

    double getTotal(){
        return service.getTotal();
    }

    @Override
    public int getRowCount() {
        return income.size();
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
        return switch (columnIndex) {
            case 0 -> income.get(rowIndex).getNameLabel();
            case 1 -> income.get(rowIndex).getValue();
            case 2 -> income.get(rowIndex).getDate();
            default -> throw new IllegalArgumentException("Index invalid");
        };
    }
}
