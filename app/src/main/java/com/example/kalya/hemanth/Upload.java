package com.example.kalya.hemanth;

/**
 * Created by kalya on 6/5/2018.
 */

public class Upload {
    private String Name;
    private String Url;
    public Upload(){

    }
    public Upload(String name, String url){
        if (name.trim().equals("")){
            name = "no name";
        }

        Name = name;
        Url = url;
    }
    public String getName()
    {
        return Name;
    }
    public void setmName(String name){
        Name = name;

    }




    public String getUrl(){
        return Url;
    }
    public void setUrl(String url){
        Url = url;
    }
}
