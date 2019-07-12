package org.dimigo.gui.javaproject;

public class WayDirection {
    private String text;
    private String value;

    public WayDirection(String text, String value){
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return text;
    }
}