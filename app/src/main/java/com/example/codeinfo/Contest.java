package com.example.codeinfo;

public class Contest {

    private String name,date,time,area,url;
    private int star;
    public DateAndTime dt;


    public  Contest(String name,String date,String time,String area,String url){
        this.name = name;
        this.date = date;
        this.time = time;
        this.area = area;
        this.url = url;
        this.star=0;
        dt = new DateAndTime();

        if(area=="Codechef") fillDTCodechef();
        if(area=="Atcoder") AdjustTime2(date);

    }

    public void AdjustTime2(String date){

        int hour=0,minutes=0;
        boolean flag=false;
        String time = date.substring(11,16);
        for(int j=0;j<time.length();j++){
            if(time.charAt(j)==':'){flag=true;}
            else if(!flag) {hour = hour*10+(time.charAt(j)-'0');}
            else{minutes = minutes*10+(time.charAt(j)-'0');}
        }
        hour=(hour-4)%24;
        minutes=(minutes-30)%60;
        if(hour<0) hour+=24;
        if(minutes<0) minutes+=60;
        String h = String.valueOf(hour);
        String m = String.valueOf(minutes);
        if(h.length()==1) h = "0"+h;
        if(m.length()==1) m = "0"+m;
        time = h+":"+m;
        this.date = date.substring(0,11)+time;

    }

    public  Contest(String name,String date,String url){
        this.name = name;
        BreakDateIntoDateAndTime(date);
        AdjustTime(this.time);
        this.area = "Codeforces";
        this.url = url;
        this.star=0;

        dt = new DateAndTime();

        if(area=="Codeforces") fillDTCodeforces();

    }

    private void findMonth(String month){
        switch (month){
            case "Jan": this.dt.month=1;break;
            case "Feb": this.dt.month=2;break;
            case "Mar": this.dt.month=3;break;
            case "Apr": this.dt.month=4;break;
            case "May": this.dt.month=5;break;
            case "Jun": this.dt.month=6;break;
            case "Jul": this.dt.month=7;break;
            case "Aug": this.dt.month=8;break;
            case "Sep": this.dt.month=9;break;
            case "Oct": this.dt.month=10;break;
            case "Nov": this.dt.month=11;break;
            case "Dec": this.dt.month=12;break;
        }
    }

    private void findDate(int s,int e,String date){
        this.dt.date = 0;
        for(int i=s;i<e;i++){
            this.dt.date = this.dt.date*10+(date.charAt(i)-'0');
        }
    }

    private void findYear(int s,int e,String date){

        this.dt.year = 0;
        for(int i=s;i<e;i++){
            this.dt.year = this.dt.year*10+(date.charAt(i)-'0');
        }

    }

    private void findHour(int s,int e,String date){
        this.dt.hour = 0;
        for(int i=s;i<e;i++){
            this.dt.hour = this.dt.hour*10+(date.charAt(i)-'0');
        }
    }

    private void findMin(int s,int e,String date){
        this.dt.min = 0;
        for(int i=s;i<e;i++){
            this.dt.min = this.dt.min*10+(date.charAt(i)-'0');
        }
    }

    private  void fillDTCodeforces(){

        String month = this.date.substring(0,3);
        findMonth(month);
        findDate(4,6,this.date);
        findYear(7,11,this.date);
        findHour(0,2,this.time);
        findMin(3,5,this.time);

    }

    private void fillDTCodechef(){

        findDate(0,2,this.date);
        String month = this.date.substring(3,6);
        findMonth(month);
        findYear(7,11,this.date);
        findHour(12,14,this.date);
        findMin(15,17,this.date);

    }

    private void BreakDateIntoDateAndTime(String date){

        int n = date.length();
        for(int j=0;j<n;j++){
            if(date.charAt(j)==' '){
                time = date.substring(j+1,n);
                date = date.substring(0,j);
                break;
            }
        }
        this.date = date;
        this.time = time;

    }

    private String AdjustTime(String time){

        int hour=0,minutes=0;
        boolean flag=false;
        for(int j=0;j<time.length();j++){
            if(time.charAt(j)==':'){flag=true;}
            else if(!flag) {hour = hour*10+(time.charAt(j)-'0');}
            else{minutes = minutes*10+(time.charAt(j)-'0');}
        }
        hour=(hour+2)%24;
        minutes=(minutes+30)%60;
        String h = String.valueOf(hour);
        String m = String.valueOf(minutes);
        if(h.length()==1) h = "0"+h;
        if(m.length()==1) m = "0"+m;
        time = h+":"+m;
        return time;

    }

    public String getName(){
        return this.name;
    }

    public String getDate(){
        return this.date;
    }

    public String getTime(){
        return this.time;
    }

    public String getArea(){
        return this.area;
    }

    public String getUrl(){
        return this.url;
    }




}
