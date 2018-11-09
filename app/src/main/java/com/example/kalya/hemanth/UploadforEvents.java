package com.example.kalya.hemanth;


public class UploadforEvents {
    private String Names;
    private String Values;
    public UploadforEvents(){

    }
    public UploadforEvents(String names, String values){
        if (names.trim().equals("")){
            names = "no name";
        }

        Names = names;
        Values = values;
    }
    public String getNames()
    {
        return Names;
    }
    public void setmNames(String name){
        Names = name;

    }




    public String getValues(){
        return Values;
    }
    public void setValues(String values){
        Values = values;
    }
}
