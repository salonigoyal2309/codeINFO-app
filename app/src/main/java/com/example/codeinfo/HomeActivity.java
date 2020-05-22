package com.example.codeinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void callCodeforcesIndent(View view){

        Intent i = new Intent(HomeActivity.this,Codeforces.class);
        startActivity(i);
    }

    public void callCodechefIndent(View view){

        Intent i = new Intent(HomeActivity.this,Codechef.class);
        startActivity(i);
    }

    public void callHackerearthIndent(View view){

        Intent i = new Intent(HomeActivity.this,Hackerearth.class);
        startActivity(i);
    }

    public void callAtcoderIndent(View view){

        Intent i = new Intent(HomeActivity.this,Atcoder.class);
        startActivity(i);
    }


}