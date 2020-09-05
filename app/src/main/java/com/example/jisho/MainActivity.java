/*
    Title:  Module 8 - Final Project
    File:   MainActivity.java
    Author: George Blombach
    Email:  gblombach@csumb.edu
    Date:   6/21/19
 */
package com.example.jisho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
//main controller class
public class MainActivity extends AppCompatActivity
{
    //define variables
    private EditText etSearch, etResults;
    private Button btnSubmit;
    private TextView tvKanji,tvKotoba, tvDetails,tvDetails2;
    private ImageButton btnKanjiLeft, btnKanjiRight, btnKotobaLeft,
          btnKotobaRight;
    private int kanjiCounter,kotobaCounter,kanjiArraySize, kotobaArraySize;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect controller to View layer
        etSearch = findViewById(R.id.etSearch);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnKanjiLeft = findViewById(R.id.btnKanjiLeft);
        btnKanjiRight= findViewById(R.id.btnKanjiRight);
        btnKotobaLeft = findViewById(R.id.btnKotobaLeft);
        btnKotobaRight = findViewById(R.id.btnKotobaRight);
        tvKanji = findViewById(R.id.tvKanji);
        tvKotoba = findViewById(R.id.tvKotoba);
        tvDetails = findViewById(R.id.tvDetails);
        tvDetails2 = findViewById(R.id.tvDetails2);
        
        //create data model object
        final DataModel dataModel = new DataModel();
        kanjiCounter = 0;
        kotobaCounter = 0;

        //hide controls
        btnKanjiRight.setVisibility(View.GONE);
        btnKanjiLeft.setVisibility(View.GONE);
        btnKotobaRight.setVisibility(View.GONE);
        btnKotobaLeft.setVisibility(View.GONE);

        //create button listener and main action
        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            //@Override
            public void onClick(View view)
            {
                if (etSearch.getText().toString().isEmpty())
                    Toast.makeText(MainActivity.this,
                          "Please enter a word to search",
                            Toast.LENGTH_SHORT).show();
                else
                {
                    //clear last search results
                    tvKanji.setText("");
                    tvDetails.setText("");
                    //search for word online
                    dataModel.search(etSearch.getText().toString());
                  
                    //get array sizes
                    kanjiArraySize = dataModel.getKanjiArray();
                    kotobaArraySize = dataModel.getKotobaArray();
                    
                    if(kanjiArraySize >=1)
                    {
                        //display results
                        tvKanji.setText(dataModel.kanjiArray[0].getImage());
                        //display results
                        tvDetails.setText(dataModel.kanjiArray[0]
                           .getDescription() + "\n" + dataModel.kanjiArray[0]
                           .getKun() + "\n" + dataModel.kanjiArray[0].getOn());
                    }
                    if(kanjiArraySize >1)
                    {
                        //show navigation buttons
                        btnKanjiRight.setVisibility(View.VISIBLE);
                        btnKanjiLeft.setVisibility(View.VISIBLE);
                    }
    
                    if(kotobaArraySize >=1)
                    {
                        //display results
                        String image=dataModel.kotobaArray[0].getImage();
                        SpannableString span =
                              new SpannableString(image + "\n" +
                              dataModel.kotobaArray[0].getFurigana());
                        span.setSpan(new AbsoluteSizeSpan(20,true),
                              image.length(),
                              span.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                              
                        tvKotoba.setText(span);
                        //display results
                        tvDetails2.setText(dataModel.kotobaArray[0]
                              .getDescription());
                    }
                    if(kotobaArraySize >1)
                    {
                        //show navigation buttons
                        btnKotobaRight.setVisibility(View.VISIBLE);
                        btnKotobaLeft.setVisibility(View.VISIBLE);
                    }
                    //clear input field
                    etSearch.setText("");
                }
    
                
            }
        });
    
        btnKanjiRight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(kanjiCounter < kanjiArraySize - 1)
                {
                    //move
                    kanjiCounter++;
                    //display results
                    tvKanji.setText(dataModel.kanjiArray[kanjiCounter]
                          .getImage());
                    //display results
                    tvDetails.setText(dataModel.kanjiArray[kanjiCounter]
                       .getDescription() + "\n" + dataModel
                       .kanjiArray[kanjiCounter].getKun() + "\n" + dataModel
                       .kanjiArray[kanjiCounter].getOn());
                    
                }
            }
        });
    
        btnKanjiLeft.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(kanjiCounter > 0)
                {
                    //move down in array
                    kanjiCounter--;
                    //display results
                    tvKanji.setText(dataModel.kanjiArray[kanjiCounter]
                          .getImage());
                    //display results
                    tvDetails.setText(dataModel.kanjiArray[kanjiCounter]
                       .getDescription() + "\n" + dataModel
                       .kanjiArray[kanjiCounter].getKun() + "\n" + dataModel
                       .kanjiArray[kanjiCounter].getOn());
                
                }
            }
        });
    
        btnKotobaRight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(kotobaCounter < kotobaArraySize - 1)
                {
                    //move up in array
                    kotobaCounter++;
                    //display results
                    String image=dataModel.kotobaArray[kotobaCounter]
                       .getImage();
                    SpannableString span =
                          new SpannableString(image + "\n" +
                                dataModel.kotobaArray[kotobaCounter]
                                   .getFurigana());
                    span.setSpan(new AbsoluteSizeSpan(16,true),
                          image.length(),
                          span.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    
                    tvKotoba.setText(span);
                    tvDetails2.setText(dataModel.kotobaArray[kotobaCounter]
                          .getDescription());
                }
            }
        });
    
        btnKotobaLeft.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(kotobaCounter > 0)
                {
                    //move down in array
                    kotobaCounter--;
                    //display results
                    String image=dataModel.kotobaArray[kotobaCounter]
                          .getImage();
                    SpannableString span =
                          new SpannableString(image + "\n" +
                                dataModel.kotobaArray[kotobaCounter]
                                      .getFurigana());
                    span.setSpan(new AbsoluteSizeSpan(16,true),
                          image.length(),
                          span.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    
                    tvKotoba.setText(span);
                    //display results
                    tvDetails2.setText(dataModel.kotobaArray[kotobaCounter]
                          .getDescription());
                
                }
            }
        });
   }
   
}
