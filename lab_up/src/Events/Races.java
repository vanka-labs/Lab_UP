package Events;

import java.util.HashMap;
import java.util.Map;

public class Races implements Event {
    public String type;
    public String winner;
    public String participants;
    public Visitors visitors=new Visitors();

    public Races(){

    }
    public String getType() {
        return type;
    }
    public String getWinner() {
        return winner;
    }
    public String getParticipants() {
        return participants;
    }
    public Visitors getVisitors() {
        return visitors;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }
    public void setParticipants(String participants) {
        this.participants = participants;
    }
    public void setVisitors(Visitors visitors) {
        this.visitors = visitors;
    }
    public Races(String type, String winner, String participants, Visitors visitors) {
        this.type = type;
        this.winner = winner;
        this.participants = participants;
        this.visitors = visitors;
    }
    public void setDay(String day){
        this.visitors.day=day;
    }
    public void setAge1(String age1) {
        this.visitors.age1 = age1;
    }
    public void setAge2(String age2) {
        this.visitors.age2 = age2;
    }
    public void setAge3(String age3) {
        this.visitors.age3 = age3;
    }
    public void setCount1(int count1) {
        this.visitors.count1 = count1;
    }
    public void setCount2(int count2) {
        this.visitors.count2 = count2;
    }
    public void setCount3(int count3) {
        this.visitors.count3 = count3;
    }

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
        s+=this.type;
        s+=" "+this.visitors.getDay();
        s+=" "+this.participants;
        s+=" Winner-"+winner+" Visitors: ";
        for (int i=0; i<3;++i )
        {
            s+=visitors.getAge()[i]+":"+visitors.getCount()[i]+" ";
        }
        return s;
    }
}
