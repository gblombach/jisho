/*
    Title:  Module 8 - Final Project
    File:   Kanji.java
    Author: George Blombach
    Email:  gblombach@csumb.edu
    Date:   6/21/19
 */
package com.example.jisho;

public class Kanji extends ImageBase
{
    private String on, kun;

    //default constructor
    protected Kanji()
    {
        this.image = "";
        this.description = "";
        this.on = "";
        this.kun ="";
    }
    //overloaded constructor
    protected Kanji(String image,  String description, String on, String kun)
    {
        this.image = image;
        this.description = description;
        this.on = on;
        this.kun = kun;
    }
    protected boolean setOn(String on)
    {
        if(this.on != null) {
            this.on = on;
            return true;
        }
        else
            return false;
    }

    protected String getOn()
    {
        if(this.on != null)
            return this.on;
        else
            return "No ON Available";
    }
    protected boolean setKun(String kun)
    {
        if(this.image != null) {
            this.kun= kun;
            return true;
        }
        else
            return false;
    }

    protected String getKun()
    {
        if(this.kun != null)
            return this.kun;
        else
            return "No Kun Available";
    }
    
    
}
