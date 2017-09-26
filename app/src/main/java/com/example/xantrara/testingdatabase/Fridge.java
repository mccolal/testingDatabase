package com.example.xantrara.testingdatabase;

import java.io.Serializable;

/**
 * Created by Xantrara on 25-Sep-17.
 */

public class Fridge implements Serializable{
    private String name;
    private int ID;
    private String description;

    public Fridge(String name, int ID){
        this.name = name;
        this.ID = ID;
    }

    public String GetName(){
        return this.name;
    }
    
    public int GetID () { return this.ID; }

}
