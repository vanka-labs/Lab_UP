package com.company;

import DAO.CsvDao;
import DAO.JsonDao;
import DAO.XMLDao;
import Events.Event;
import Events.Games;
import Events.Matches;
import Events.Races;
import com.opencsv.exceptions.CsvException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    static public int average(String day,List list)
    {
        int aver=0;
        int t=0;
        for (int i=0; i<list.size();++i)
        {
            if(list.toArray()[i] instanceof Games) {
                aver +=((Games) list.toArray()[i]).averagebyday(day);
               if(((Games) list.toArray()[i]).visitors.getDay().equals(day))
               {
                   ++t;
               }
            }
            else if(list.toArray()[i] instanceof Races){
                aver +=((Races) list.toArray()[i]).averagebyday(day);
                if(((Races) list.toArray()[i]).visitors.getDay().equals(day))
                {
                    ++t;
                }
            }
            else {
                aver += ((Matches) list.toArray()[i]).averagebyday(day);
                if(((Matches) list.toArray()[i]).visitors.getDay().equals(day))
                {
                    ++t;
                }
            }
        }
        if(t==0)
        {
            return 0;
        }
        else
        return aver/t;
    }
    static public void printAverage(List list)
    {
        System.out.println("\n"+"Average by everyday:");
        System.out.println("Monday: "+average("Monday",list));
        System.out.println("Tuesday: "+average("Tuesday",list));
        System.out.println("Wednesday: "+average("Wednesday",list));
        System.out.println("Thursday: "+average("Thursday",list));
        System.out.println("Friday: "+average("Friday",list));
        System.out.println("Saturday: "+average("Saturday",list));
        System.out.println("Sunday: "+average("Sunday",list));
    }
    static public void print(List list)
    {
        for (int i=0; i<list.size();++i)
        {
            System.out.println(list.toArray()[i].toString());
        }
    }
    static public Map<Integer,Event> sort(List list, String age)
    {
        Map<Integer, Event> map=new TreeMap<Integer,Event>();
        for (int i=0; i<list.size();++i)
        {
            if(list.toArray()[i] instanceof Games) {
              map.putAll(((Games) list.toArray()[i]).sort(age));
            }
            else if(list.toArray()[i] instanceof Races){
                map.putAll(((Races) list.toArray()[i]).sort(age));
            }
            else {
                map.putAll(((Matches) list.toArray()[i]).sort(age));
            }
        }
      return map;
    }
    static public void printSorted(List list){
        System.out.println("\n"+"Sorted by children: "+sort(list,"children"));
        System.out.println("Sorted by young: "+sort(list,"young"));
        System.out.println("Sorted by old: "+sort(list,"old"));
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, CsvException, URISyntaxException {
      XMLDao<Event> xmlDao=new XMLDao<Event>();
      CsvDao<Matches> csvDao=new CsvDao<Matches>();
      JsonDao<Event> jsonDao=new JsonDao<Event>();
      List<Event>list=new ArrayList<Event>();

      list.addAll(csvDao.read());
      list.addAll(jsonDao.read());
      list.addAll(xmlDao.read());
      print(list);
      System.out.println();
      printAverage(list);
      System.out.println();
      printSorted(list);
    }
}

