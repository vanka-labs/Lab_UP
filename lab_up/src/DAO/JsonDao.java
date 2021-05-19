package DAO;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;

import Events.Games;
import Events.Matches;
import Events.Races;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonDao<T> implements AbstractDao<T> {



    @Override
    public List<T> read() throws IOException {
        List<T> list=new ArrayList<T>();

        try {
           ObjectMapper mapper = new ObjectMapper();
           List<Object> l = Arrays.asList(mapper.readValue(Paths.get("Matches.json").toFile(), Matches[].class));
           list.addAll((Collection<? extends T>) l);
           l = Arrays.asList(mapper.readValue(Paths.get("Games.json").toFile(), Games[].class));
           list.addAll((Collection<? extends T>) l);
           l = Arrays.asList(mapper.readValue(Paths.get("Races.json").toFile(), Races[].class));
           list.addAll((Collection<? extends T>) l);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
      }


    @Override
    public void write(List<T> list) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString =new String();
        FileWriter writer = new FileWriter("result.json", false);
    for (int i=0;i<list.size();++i)
    {
        if(i!=list.size()-1) {
            if (list.toArray()[i] instanceof Games) {
                jsonString += mapper.writeValueAsString((Games) list.toArray()[i]);
                jsonString += "," + "\n";
            } else if (list.toArray()[i] instanceof Races) {
                jsonString += mapper.writeValueAsString((Races) list.toArray()[i]);
                jsonString += "," + "\n";
            } else {
                jsonString += mapper.writeValueAsString((Matches) list.toArray()[i]);
                jsonString += "," + "\n";
            }
        }
        else{
            if (list.toArray()[i] instanceof Games) {
                jsonString += mapper.writeValueAsString((Games) list.toArray()[i]);
                jsonString += "\n";
            } else if (list.toArray()[i] instanceof Races) {
                jsonString += mapper.writeValueAsString((Races) list.toArray()[i]);
                jsonString += "\n";
            } else {
                jsonString += mapper.writeValueAsString((Matches) list.toArray()[i]);
                jsonString += "\n";
            }
        }
    }
    writer.write("["+"\n");
    writer.write(jsonString);
        writer.write("]");
        writer.flush();
    }
}
