package com.sacane.manager.gui;


import com.sacane.calc.main.Run;
import com.sacane.manager.Month;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Calendar implements ActionListener {

    private final JMenuBar menu = new JMenuBar();
    private final ArrayList<JButton> yearButton = new ArrayList<>();
    private final ArrayList<JButton> monthButton = new ArrayList<>();
    private final JPanel calendar = new JPanel();
    private final JPanel yearPanel = new JPanel();
    private final JPanel monthPanel = new JPanel();
    private final JButton addYear = new JButton("add");
    private int currentYear;
    private int maxYear;
    private Month currentMonth;
    private final JButton calculator = new JButton("Calc");
    private final JPanel centerPanel = new JPanel();
    private final JPanel leftPanel = new JPanel();
    private final JPanel rightPanel = new JPanel();
    private final JPanel northPanel = new JPanel();

    public Calendar(){
        //Set all the panel in the main panel
        centerPanel.setLayout(new FlowLayout());
        leftPanel.setLayout(new FlowLayout());
        rightPanel.setLayout(new BorderLayout());
        northPanel.setLayout(new BorderLayout());


        calendar.setLayout(new BorderLayout());
        yearPanel.setLayout(new GridLayout(15, 3));
        monthPanel.setLayout(new GridLayout(3, 4));
        buildPanel();
        maxYear = 2021;
        currentYear = maxYear;
    }



    JPanel getCalendarPanel(){
        return calendar;
    }

    public JMenuBar getMenu() {
        return menu;
    }

    /**
     * build the mainCalendar panel
     */
    private void buildPanel(){

        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu View = new JMenu("View");

        menu.add(file);
        menu.add(edit);
        menu.add(View);
        setButton();
        leftPanel.add(yearPanel, FlowLayout.LEFT);
        rightPanel.add(calculator, BorderLayout.SOUTH);
        calendar.add(rightPanel, BorderLayout.EAST);
        calendar.add(centerPanel, BorderLayout.CENTER);
        calendar.add(menu, BorderLayout.NORTH);
//        calendar.add(yearPanel, BorderLayout.WEST);
        calendar.add(leftPanel, BorderLayout.WEST);

    }

    /**
     * add a new button designed by a year in the JPanel man.
     * @param year to add in the JPanel.
     */
    void createButton(int year){
        var newButton = new JButton(String.valueOf(year));
        newButton.addActionListener(this);
        yearButton.add(newButton);
    }

    void updateButton(){
        for(var button : yearButton){
            yearPanel.add(button);
        }
    }

    /**
     * set all the buttons in the main panel of the calendar
     */
    void setButton(){

        for(var month : Month.values()){
            var buttonMonth = new JButton(month.getTypeText());
            buttonMonth.addActionListener(this);
            monthButton.add(buttonMonth);
            monthPanel.add(buttonMonth);
        }
        centerPanel.add(monthPanel, BorderLayout.CENTER);
        addYear.addActionListener(this);
        calculator.addActionListener(this);
        yearPanel.add(addYear);
        createButton(2020);
        createButton(2021);
        updateButton();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var o = e.getSource();
        var button = (JButton)o;
        if(o == addYear){
            maxYear +=1;
            createButton(maxYear);
            updateButton();
            yearPanel.validate();
            yearPanel.repaint();
        }

        if(yearButton.contains(button)){
            currentYear = Integer.parseInt(button.getText());
            System.out.println(currentYear);
        }
        if(monthButton.contains(button)){
            currentMonth = Month.get(button.getText());
            System.out.println(currentMonth);
            System.out.println(currentYear);
        }
        if(o == calculator){
            Run.runCalculator();
        }
    }
}
