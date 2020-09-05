/*
    Title:  Module 8 - Final Project
    File:   DataModel.java
    Author: George Blombach
    Email:  gblombach@csumb.edu
    Date:   6/21/19
 */
package com.example.jisho;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DataModel
{
   private String dictionaryWord;
   private Document page = null;
   protected Kanji[] kanjiArray;
   protected Kotoba[] kotobaArray;
   
   //default constructor
   protected DataModel()
   {
      //create object arrays
   }
   //main function to receive search word from controller and return
   // results to controller
   protected void search(String searchWord)
   {
      if (searchWord != null)
      {
         this.dictionaryWord = searchWord;
         String url = "https://jisho.org/search/" + searchWord;
         Document page = null;
         
         try
         {
            //get the webpage data
            Object results = new getdata().execute().get();
            //Log.d("DATA",results.toString());
            
            
         }
         catch(InterruptedException e)
         {
            Log.d("ERROR",e.toString());
         }
         catch(ExecutionException e)
         {
            Log.d("ERROR",e.toString());
         }
      }
   }
   
   //function to query jisho website
   protected class getdata extends AsyncTask<String, Void, Void>
   {
      @Override
      protected void onPreExecute()
      {
         super.onPreExecute();
      }
      
      @Override
      protected Void doInBackground(String... voids)
      {
         try
         {
            String url = "https://jisho.org/search/" + dictionaryWord;
            page =
                  Jsoup.connect("" + url).userAgent("Mozilla").data("name",
                        "jsoup").get();
   
            //get kanjis
            Elements kanjis = page.select("div.kanji_light_content");
   
            //create kanji array
            kanjiArray = new Kanji[kanjis.size()];
            int counter = 0;
   
            for (Element kanji : kanjis)
            {
               String strKanji = kanji.getElementsByClass
                     ("literal_block").text();
               String strMeaning = kanji.getElementsByClass
                     ("meanings english sense").text();
               String strKun = kanji.getElementsByClass
                     ("kun readings").text();
               String strOn = kanji.getElementsByClass
                     ("on readings").text();
               Log.d("DATA", "Kanji: " + strKanji + "\n" +
                     "Meaning: " + strMeaning +
                     "\n" + strKun + "\n" + strOn);
      
               //create kanji object
               kanjiArray[counter] = new Kanji();
               kanjiArray[counter].setImage(strKanji);
               kanjiArray[counter].setDescription(strMeaning);
               kanjiArray[counter].setKun(strKun);
               kanjiArray[counter].setOn(strOn);
      
               //increment counter
               counter ++;
            }
   
            //get words NOTE: use a DOT for blanks in class names
            Elements kotobas = page.select
                  ("div.concept_light.clearfix");
            //count inner classes for accurate word count
            Elements objs = page.select
                  ( "div.concept_light-representation");
            //create word array
            kotobaArray = new Kotoba[objs.size()];
            Log.d("DATA", "Kotobas: " + kotobaArray.length);
            counter = 0;
            
   
            for (Element kotoba : kotobas)
            {
               if(kotoba.getElementsByClass
                  ("concept_light-representation").isEmpty() == false)
               {
                  String strSound = " ", strWord = " ";
                  String strTemp = kotoba.getElementsByClass
                        ("concept_light-representation").text();
                  if(strTemp.contains(" "))
                  {
                     String[] strTemp2 = strTemp.split(" ");
                     strSound = strTemp2[0];
                     strWord = strTemp2[1];
                  }
                  else
                  {
                     strSound = " ";
                     if (strTemp != null)
                        strWord = strTemp;
                     else
                        strWord = " ";
                  }
                  Elements items = kotoba.select
                        ("div.meaning-definition.zero-padding");
                  String strWordMeaning ="";
                  for(Element item:items)
                     strWordMeaning += " " + item.getElementsByClass
                        ("meaning-definition-section_divider").text()
                        + " " + item.getElementsByClass
                        ("meaning-meaning").text();
                  Log.d("DATA", "Word:" + strWord + "\nSound: " +
                        strSound + "\nMeaning:" + strWordMeaning);
         
                  //create word objects
                  kotobaArray[counter] = new Kotoba();
                  kotobaArray[counter].setImage(strWord);
                  kotobaArray[counter].setFurigana(strSound);
                  kotobaArray[counter].setDescription(strWordMeaning);
         
                  //increment counter
                  counter ++;
               }
            }
            Log.d("DATA", Integer.toString(kanjiArray.length));
            
            if (page !=null)
               Log.d("DATA", "Connect OK");
         }
         catch (IOException e)
         {
            Log.d("ERROR", "" + e);
         }
         return null;
      }
      
      protected void onPostExecute(Void aVoid)
      {
         super.onPostExecute(aVoid);
      }
   }
   //helper function to help navigation of array
   protected Integer getKanjiArray()
   {
      return kanjiArray.length;
   }
   
   //helper function to return array size for navigation
   protected Integer getKotobaArray()
   {
      return kotobaArray.length;
   }
}