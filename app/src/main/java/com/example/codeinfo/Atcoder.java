package com.example.codeinfo;

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
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class Atcoder extends AppCompatActivity {

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
        setContentView(R.layout.activity_atcoder);

        parentLinearLayout = (LinearLayout) findViewById(R.id.parentLayout);

        param = new LinearLayout.LayoutParams(
                GridLayout.LayoutParams.MATCH_PARENT,
                GridLayout.LayoutParams.MATCH_PARENT
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

                Document doc = Jsoup.connect("https://atcoder.jp/contests/").get();
                Element table = doc.select("table").get(0);
                Elements rows = table.select("tr");

                for (int i = 1; i < rows.size(); i++) {

                    Element row = rows.get(i);
                    String name = row.select("td").get(0).select("a").text();
                    String url = row.select("td").get(0).select("a").get(0).absUrl("href");
                    String date = "Permanent Contest";
                    String time ="Permanent Contest";
                    Contest c = new Contest(name,date,time,"AtCoder",url);
                    contest.add(c);

                }

                table = doc.select("table").get(1);
                rows = table.select("tr");

                for(int i=1;i<rows.size();i++){

                    Element row = rows.get(i);
                    String name = row.select("td").get(1).select("a").text();
                    String url = row.select("td").get(1).select("a").get(0).absUrl("href");
                    String date = row.select("td").get(0).select("a").get(0).select("time").get(0).text().substring(0,19);
                    String time = row.select("td").get(2).text();
                    Contest c = new Contest(name,date,time,"Atcoder",url);
                    contest.add(c);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            for(int i=0;i<contest.size();i++){

                l = new LinearLayout(com.example.codeinfo.Atcoder.this);
                l.setBackgroundResource(R.drawable.round_corner);
                l.setLayoutParams(param);
                l.setOrientation(LinearLayout.VERTICAL);

                t = new TextView(com.example.codeinfo.Atcoder.this);
                t.setText(contest.get(i).getName());
                t.setGravity(Gravity.CENTER);
                t.setTextSize(17);
                t.setTypeface(null, Typeface.BOLD);
                t.setPadding(10,10,10,30);
                //t.setTextColor(Color.WHITE);
                l.addView(t);


                View tr = new View(com.example.codeinfo.Atcoder.this);
                tr.setLayoutParams(new LinearLayout.LayoutParams( GridLayout.LayoutParams.MATCH_PARENT, 5 ));
                tr.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l.addView(tr);

                l2 = new LinearLayout(com.example.codeinfo.Atcoder.this);
                l2.setOrientation(LinearLayout.HORIZONTAL);
                t1 = new TextView(com.example.codeinfo.Atcoder.this);
                t1.setText(contest.get(i).getDate());
                t1.setLayoutParams(new TableLayout.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT, 1f));
                t1.setGravity(Gravity.CENTER);
                t1.setPadding(20,20,20,30);
                //t1.setTextColor(Color.WHITE);
                t1.setTextSize(15);

                View tr2 = new View(com.example.codeinfo.Atcoder.this);
                tr2.setLayoutParams(new LinearLayout.LayoutParams( 5, ViewGroup.LayoutParams.MATCH_PARENT));
                tr2.setBackgroundColor(Color.parseColor("#FFFFFF"));

                t2 = new TextView(com.example.codeinfo.Atcoder.this);
                t2.setText(contest.get(i).getTime());
                t2.setLayoutParams(new TableLayout.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT, 1f));
                t2.setGravity(Gravity.CENTER);
                //t2.setTextColor(Color.WHITE);
                t2.setTextSize(15);
                t2.setPadding(20,20,20,30);
                l2.addView(t1);
                l2.addView(tr2);
                l2.addView(t2);

                l.addView(l2);

                View tr3 = new View(com.example.codeinfo.Atcoder.this);
                tr3.setLayoutParams(new LinearLayout.LayoutParams( GridLayout.LayoutParams.MATCH_PARENT, 5 ));
                tr3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l.addView(tr3);

                t3 = new TextView(com.example.codeinfo.Atcoder.this);
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
