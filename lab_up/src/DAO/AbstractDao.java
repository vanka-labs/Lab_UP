package DAO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.exceptions.CsvException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface AbstractDao<T> {
    List<T> read() throws ParserConfigurationException, SAXException, IOException, CsvException, URISyntaxException;
    void write(List<T> list) throws IOException;

}
