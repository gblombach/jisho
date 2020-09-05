/*
    Title:  Module 8 - Final Project
    File:   ImageBase.java
    Author: George Blombach
    Email:  gblombach@csumb.edu
    Date:   6/21/19
 */
package com.example.jisho;

public class ImageBase implements Cloneable
{
    protected String image;
    protected String description;


    protected boolean setImage(String image)
    {
        if(this.image != null) {
            this.image = image;
            return true;
        }
        else
            return false;
    }

    protected String getImage()
    {
        if(this.image != null)
            return this.image;
        else
            return "No Image Available";
    }

    protected boolean setDescription(String description)
    {
        if(this.image != null) {
            this.description = description;
            return true;
        }
        else
            return false;

    }
    protected String getDescription()
    {
        if(this.description != null)
            return this.description;
        else
            return "No Description Available";
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
    
    
}
