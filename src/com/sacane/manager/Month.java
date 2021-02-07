package com.sacane.manager;

public enum Month {

    JANUARY("January", 1),
    FEBRUARY("February", 2),
    MARCH("March", 3),
    APRIL("April", 4),
    MAY("May", 5),
    JUNE("June", 6),
    JULY("July", 7),
    AUGUST("August", 8),
    SEPTEMBER("September", 9),
    OCTOBER("October", 10),
    NOVEMBER("November", 11),
    DECEMBER("December", 12);

    private final String typeText;
    private final int representation;
    Month(String typeText, int representation){
        this.typeText = typeText;
        this.representation = representation;
    }

    String getTypeText(){
        return typeText;
    }
    int getRepresentation(){
        return representation;
    }
}