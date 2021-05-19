package Events;

import com.opencsv.bean.CsvBindByName;

import java.util.HashMap;
import java.util.Map;

public class Matches implements Event {
    @CsvBindByName
    public String type;
    @CsvBindByName
    public String participant1;
    @CsvBindByName
    public String participant2;
    @CsvBindByName
    public String winner;
    @CsvBindByName
    public String loser;
    public Visitors visitors=new Visitors();

    public Matches() {
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getParticipant1() {
        return participant1;
    }
    public void setParticipant1(String participant1) {
        this.participant1 = participant1;
    }
    public String getParticipant2() {
        return participant2;
    }
    public void setParticipant2(String participant2) {
        this.participant2 = participant2;
    }
    public String getWinner() {
        return winner;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }
    public String getLoser() {
        return loser;
    }
    public void setLoser(String loser) {
        this.loser = loser;
    }
    public Visitors getVisitors() {
        return visitors;
    }
    public void setVisitors(Visitors visitors) {
        this.visitors = visitors;
    }
    public Matches(String type, String participant1, String participant2, String winner, String loser, Visitors visitors) {
        this.type = type;
        this.participant1 = participant1;
        this.participant2 = participant2;
        this.winner = winner;
        this.loser = loser;
        this.visitors = visitors;
    }
    public void setDay(String day){
        this.visitors.day=day;
    }
    public void setAge1(String age1){
        this.visitors.age1=age1;
    }
    public void setAge2(String age2){
        this.visitors.age1=age2;
    }
    public void setAge3(String age3){
        this.visitors.age1=age3;
    }
    public void setCount1(int count1){this.visitors.count1=count1;}
    public void setCount2(int count2){this.visitors.count1=count2;}
    public void setCount3(int count3){this.visitors.count1=count3;}


      @Override
    public Map sort(String age) {
        Map<Integer,String> map=new HashMap<Integer, String>() ;
        for (int i=0; i<3;++i)
        {
            if(this.visitors.getAge()[i].equals(age)){
                map.put(this.visitors.getCount()[i],this.type);
            }
        }
        return map;
    }
    @Override
    public int averagebyday(String day) {
        int average=0;
        if(this.visitors.getDay().equals(day)) {
            for (int i=0;i<3;++i)
            {
                average+=this.visitors.getCount()[i];

            }
        }
        return average;
    }
    @Override
    public String toString()
    {
        String s=new String();
        s+=this.type+" "+visitors.getDay()+" "+this.participant1+"-"+this.participant2+" Winner-"+winner+
                " Loser-"+loser+" Visitors: ";
        for (int i=0; i<3;++i )
        {
            s+=visitors.getAge()[i]+" : "+visitors.getCount()[i]+" ";
        }
        return s;
    }

}
