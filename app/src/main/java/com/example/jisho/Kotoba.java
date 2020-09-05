/*
    Title:  Module 8 - Final Project
    File:   Kotoba.java
    Author: George Blombach
    Email:  gblombach@csumb.edu
    Date:   6/21/19
 */
package com.example.jisho;

public class Kotoba extends ImageBase
{
    private String furigana, audio;

    //default constructor
    protected Kotoba()
    {
        //initialize variables
        this.image = "";
        this.description = "";
        this.furigana = "";
        this.audio = ""; //future use
    }

    //overloaded constructor
    protected Kotoba(String image, String description, String furigana,
                 String audio)
    {
        this.image = image;
        this.description = description;
        this.furigana = furigana;
        this.audio = audio;
    }

    protected boolean setFurigana(String furigana)
    {
        if(this.furigana != null)
        {
            this.furigana = furigana;
            return true;
        }
        else
            return false;
    }
    protected String getFurigana()
    {
        if(this.furigana != null)
            return this.furigana;
        else
            return "";
    }


}
