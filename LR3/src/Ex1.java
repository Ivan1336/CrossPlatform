import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
public class Ex1 {
    static private Set<String> tags = new TreeSet<>();
    static private ArrayList<String> people=new ArrayList<>();

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
                    tags.add(qName);
                    if (qName.equalsIgnoreCase("brth_yr") && people.size()<=20)
                        people.add("");
                }
                public void characters(char ch[], int start, int length) throws SAXException {
                    if(people.size()<=20)
                        people.set(people.size()-1,people.get(people.size()-1)+new String(ch, start, length)+"\t");
                }
            };
            System.out.println("Birth\tGender\tEthnicity\tName\tCount\tRating");
            saxParser.parse("Popular_Baby_Names_NY.xml", handler);
            for (String ppl : people) {
                System.out.println(ppl);
            }

            System.out.println();
            System.out.println("List of all tags in the document:");
            for (String tag : tags) {
                System.out.println(tag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
