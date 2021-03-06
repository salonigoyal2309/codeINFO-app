package com.example.codeinfo;

import com.example.codeinfo.HomeActivity.*;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

import java.io.IOException;
import java.util.Collections;
import java.util.Vector;
import androidx.core.app.ComponentActivity.ExtraData.*;

public class Codeforces extends AppCompatActivity {

    private LinearLayout parentLinearLayout;
    private LinearLayout l;
    private LinearLayout l2;
    private TextView t;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private LinearLayout.LayoutParams param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codeforces);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parentLayout);

        param = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
        );
        param.setMargins(20,10,20,10);
        new Content().execute();
    }

    private class Content extends AsyncTask<Void, Void, Void> {

        ArrayList<Contest> contest =new ArrayList<Contest>();
        Vector ColorList = new Vector();

        int color=0;
        @Override
        protected Void doInBackground(Void... voids) {

            ColorList.add("#E75555");
            ColorList.add("#6CDCDF");
            ColorList.add("#F47CBF");
            ColorList.add("#C39BD3");
            ColorList.add("#A9E185");
            ColorList.add("#F4DF62");


            try {

                Document doc = Jsoup.connect("https://codeforces.com/contests").get();
                Element table = doc.select("table").get(0);
                Elements rows = table.select("tr");

                for (int i = 1; i < rows.size(); i++) {

                    Element row = rows.get(i);
                    String name = row.select("td").get(0).text();
                    String date = row.select("td").get(2).select("span").text();
                    Contest c = new Contest(name,date,"https://codeforces.com/contests");
                    contest.add(c);

                }

                Comparator<Contest> cmp=new Comparator<Contest>() {

                    @Override
                    public int compare(Contest a, Contest b) {

                        if(a.dt.year==b.dt.year){
                            if(a.dt.month==b.dt.month){
                                if(a.dt.date==b.dt.date){
                                    if(a.dt.hour==b.dt.hour){
                                        if(a.dt.min<=b.dt.min) return -1;
                                        else return 1;
                                    }
                                    else if(a.dt.hour<b.dt.hour) return -1;
                                    else return 1;
                                }
                                else if(a.dt.date<b.dt.date) return -1;
                                else return 1;
                            }
                            else if(a.dt.month<b.dt.month) return -1;
                            else return 1;
                        }
                        else if(a.dt.year<b.dt.year) return -1;
                        else return 1;
                    }

                };

                Collections.sort(contest,cmp);


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            for(int i=0;i<contest.size();i++){

                l = new LinearLayout(com.example.codeinfo.Codeforces.this);
                l.setBackgroundResource(R.drawable.round_corner);
                l.setLayoutParams(param);
                l.setOrientation(LinearLayout.VERTICAL);

                t = new TextView(com.example.codeinfo.Codeforces.this);
                t.setText(contest.get(i).getName());
                t.setGravity(Gravity.CENTER);
                t.setTextSize(17);
                t.setTypeface(null, Typeface.BOLD);
                t.setPadding(10,10,10,30);
                //t.setTextColor(Color.WHITE);
                l.addView(t);


                View tr = new View(com.example.codeinfo.Codeforces.this);
                tr.setLayoutParams(new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, 5 ));
                tr.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l.addView(tr);

                l2 = new LinearLayout(com.example.codeinfo.Codeforces.this);
                l2.setOrientation(LinearLayout.HORIZONTAL);
                t1 = new TextView(com.example.codeinfo.Codeforces.this);
                t1.setText(contest.get(i).getDate());
                t1.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
                t1.setGravity(Gravity.CENTER);
                t1.setPadding(20,20,20,30);
                //t1.setTextColor(Color.WHITE);
                t1.setTextSize(15);

                View tr2 = new View(com.example.codeinfo.Codeforces.this);
                tr2.setLayoutParams(new LinearLayout.LayoutParams( 5, ViewGroup.LayoutParams.MATCH_PARENT));
                tr2.setBackgroundColor(Color.parseColor("#FFFFFF"));

                t2 = new TextView(com.example.codeinfo.Codeforces.this);
                t2.setText(contest.get(i).getTime());
                t2.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
                t2.setGravity(Gravity.CENTER);
                //t2.setTextColor(Color.WHITE);
                t2.setTextSize(15);
                t2.setPadding(20,20,20,30);
                l2.addView(t1);
                l2.addView(tr2);
                l2.addView(t2);

                l.addView(l2);

                View tr3 = new View(com.example.codeinfo.Codeforces.this);
                tr3.setLayoutParams(new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, 5 ));
                tr3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l.addView(tr3);

                t3 = new TextView(com.example.codeinfo.Codeforces.this);
                String dynamicUrl = contest.get(i).getUrl();
                String area = contest.get(i).getArea();
                String linkedText = String.format("<a href=\"%s\">\"%s\" link to page </a> ", dynamicUrl,area) ;
                t3.setText(Html.fromHtml(linkedText));
                //t3.setLinkTextColor(Color.WHITE);
                t3.setMovementMethod(LinkMovementMethod.getInstance());
                t3.setPadding(20,30,20,10);
                t3.setGravity(Gravity.CENTER);
                t3.setTextSize(15);

                l.addView(t3);
                l.setBackgroundColor(Color.parseColor(ColorList.get(color).toString()));
                color = (color+1)%ColorList.size();
                parentLinearLayout.addView(l);

            }

        }
    }



}
