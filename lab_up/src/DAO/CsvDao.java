package DAO;

import Events.Matches;
import Events.Races;
import Events.Visitors;
import Events.Games;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class CsvDao<T> implements AbstractDao<T> {


    @Override
    public List<T> read() throws IOException, CsvException, URISyntaxException {
        List<T> list=new ArrayList<T>();

        ICsvBeanReader csvBeanReaderG = new CsvBeanReader(new FileReader("Games.csv"), CsvPreference.STANDARD_PREFERENCE);
        ICsvBeanReader csvBeanReaderM = new CsvBeanReader(new FileReader("Matches.csv"), CsvPreference.STANDARD_PREFERENCE);
        ICsvBeanReader csvBeanReaderR = new CsvBeanReader(new FileReader("Races.csv"), CsvPreference.STANDARD_PREFERENCE);

        String[] mappingG = new String[]{"type", "participant1", "participant2","winner", "loser", "score"};
        String[] mappingM = new String[]{"type", "participant1", "participant2","winner", "loser"};
        String[] mappingR = new String[]{"type", "participants", "winner"};
        String[] mappingV=new String[]{"day" ,"age1","age2","age3","count1","count2","count3"};

        CellProcessor[] processorsV=new CellProcessor[]{new Optional(),new Optional(), new Optional(),new Optional(),new Optional(),
                new Optional(),new Optional()};
        CellProcessor[] processorsM=new CellProcessor[]{new Optional(),new Optional(), new Optional(),new Optional(),new Optional()};
        CellProcessor[] processorsG=new CellProcessor[]{new Optional(),new Optional(), new Optional(),new Optional(),new Optional(), new Optional()};
        CellProcessor[] processorsR=new CellProcessor[]{new Optional(),new Optional(), new Optional()};

        Visitors visitors=new Visitors();
        Games game;
        while ((visitors = csvBeanReaderG.read(Visitors.class, mappingV, processorsV)) != null &&(game = csvBeanReaderG.read(Games.class, mappingG, processorsG)) != null) {
            visitors=csvBeanReaderG.read(Visitors.class, mappingV, processorsV);
            game.setVisitors(visitors);
            list.add((T)game);
        }
        Races race;
        while ((visitors = csvBeanReaderR.read(Visitors.class, mappingV, processorsV)) != null &&(race = csvBeanReaderR.read(Races.class, mappingR, processorsR)) != null) {
            race.setVisitors(visitors);
            list.add((T)race);
        }
        Matches match;
        while ((visitors = csvBeanReaderM.read(Visitors.class, mappingV, processorsV)) != null && (match = csvBeanReaderM.read(Matches.class, mappingM, processorsM)) != null ) {
            match.setVisitors(visitors);
            list.add((T)match);
        }

        csvBeanReaderG.close();
        csvBeanReaderM.close();
        csvBeanReaderR.close();










        return list;
    }

    @Override
    public void write(List<T> list) throws IOException {
        CSVWriter csvWriter=new CSVWriter(new FileWriter("result.csv"));
        String s=new String();
        String[] wr=new String[list.size()];
        for (int i=0; i<list.size();++i)
        {
            if(list.toArray()[i] instanceof Games){
                s=((Games) list.toArray()[i]).getType()+", "+((Games) list.toArray()[i]).getVisitors().getDay()+", "+((Games) list.toArray()[i]).participant1+"-"+((Games) list.toArray()[i]).participant2+", Winner-"+(((Games) list.toArray()[i]).winner)+
                        ", Loser-"+((Games) list.toArray()[i]).loser+", "+((Games) list.toArray()[i]).score+", Visitors: ";
                for (int j=0; j<2;++j)
                {
                    s+=((Games) list.toArray()[i]).visitors.getAge()[j]+":"+((Games) list.toArray()[i]).visitors.getCount()[j]+", ";
                }
                s+=((Games) list.toArray()[i]).visitors.getAge()[2]+":"+((Games) list.toArray()[i]).visitors.getCount()[2]+"\n";
            wr[i]=s;
            }
            else if(list.toArray()[i] instanceof Matches){
                s=((Matches) list.toArray()[i]).type+", "+((Matches) list.toArray()[i]).visitors.getDay()+", "+((Matches) list.toArray()[i]).participant1+"-"+((Matches) list.toArray()[i]).participant2+", Winner-"+((Matches) list.toArray()[i])+
                        ", Loser-"+((Matches) list.toArray()[i]).loser+", Visitors: ";
                for (int j=0; j<2;++j)
                {
                    s+=((Matches) list.toArray()[i]).visitors.getAge()[j]+":"+((Matches) list.toArray()[i]).visitors.getCount()[j]+", ";
                }
                s+=((Matches) list.toArray()[i]).visitors.getAge()[2]+":"+((Matches) list.toArray()[i]).visitors.getCount()[2]+"\n";
                wr[i]=s;
            }
        }
        csvWriter.writeNext(wr);
        csvWriter.close();
    }


    }
