package DAO;
import Events.Games;
import Events.Matches;
import Events.Races;
import Events.Visitors;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileWriter;
import java.lang.String;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class XMLDao<T> implements AbstractDao<T> {
    List<T> list = new ArrayList<T>();
    class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("Game")) {
                String type = attributes.getValue("type");
                String participant1 = attributes.getValue("participant1");
                String participant2 = attributes.getValue("participant2");
                String winner = attributes.getValue("winner");
                String loser = attributes.getValue("loser");
                String score = attributes.getValue("score");
                String day = attributes.getValue("day");
                String age1 = attributes.getValue("age1");
                int count1 = Integer.parseInt(attributes.getValue("count1"));
                String age2 = attributes.getValue("age2");
                int count2 = Integer.parseInt(attributes.getValue("count2"));
                String age3 = attributes.getValue("age3");
                int count3 = Integer.parseInt(attributes.getValue("count3"));
                list.add((T) new Games(type, participant1, participant2, winner, loser, score, new Visitors(day, age1,age2,age3, count1,count2,count3)));
            }
            else if (qName.equals("Match")) {
                String type = attributes.getValue("type");
                String participant1 = attributes.getValue("participant1");
                String participant2 = attributes.getValue("participant2");
                String winner = attributes.getValue("winner");
                String loser = attributes.getValue("loser");
                String day = attributes.getValue("day");
                String age1 = attributes.getValue("age1");
                int count1 = Integer.parseInt(attributes.getValue("count1"));
                String age2 = attributes.getValue("age2");
                int count2 = Integer.parseInt(attributes.getValue("count2"));
                String age3 = attributes.getValue("age3");
                int count3 = Integer.parseInt(attributes.getValue("count3"));
                list.add((T) new Matches(type, participant1, participant2, winner, loser, new Visitors(day, age1,age2,age3, count1,count2,count3)));
            }
            else if (qName.equals("Races")) {
                String type = attributes.getValue("type");
                String winner = attributes.getValue("winner");
                String part = attributes.getValue("participant");
                String day = attributes.getValue("day");
                String age1 = attributes.getValue("age1");
                int count1 = Integer.parseInt(attributes.getValue("count1"));
                String age2 = attributes.getValue("age2");
                int count2 = Integer.parseInt(attributes.getValue("count2"));
                String age3 = attributes.getValue("age3");
                int count3 = Integer.parseInt(attributes.getValue("count3"));


                list.add((T) new Races(type, winner, part, new Visitors(day, age1,age2,age3, count1,count2,count3)));
            }
        }
    }

    @Override
    public List<T> read() throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File("Events.xml"), handler);
        return list;
    }
    @Override
    public void write(List<T> list) {
    try {
        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = output.createXMLStreamWriter(new FileWriter("result.xml"));
        writer.writeStartDocument("1.0");
        writer.writeStartElement("Events");
        for (int i=0;i<list.size();++i){
            if(list.toArray()[i] instanceof Games) {
                writer.writeStartElement("Game");
                writer.writeCharacters(((Games) list.toArray()[i]).toString());
                writer.writeEndElement();
            }
            else if(list.toArray()[i] instanceof Races){
                writer.writeStartElement("Race");
                writer.writeCharacters(((Races) list.toArray()[i]).toString());
                writer.writeEndElement();
            }
            else{
                writer.writeStartElement("Match");
                writer.writeCharacters(((Matches) list.toArray()[i]).toString());
                writer.writeEndElement();
            }


        }
        writer.writeEndElement();
        writer.writeEndDocument();
        writer.flush();
    }catch (XMLStreamException | IOException ex) {
        System.out.println(ex.toString());
    }



    }

}
