import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Set;
import java.util.TreeSet;

public class Ex3 {
    static private Set<String> ethnoses=new TreeSet<>();
    static private boolean flag=false;

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("ethcty"))
                        flag = true;
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    flag = false;
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    if (flag)
                        ethnoses.add(new String(ch, start, length));
                }
            };
            saxParser.parse("Popular_Baby_Names_NY.xml", handler);
            System.out.println("All ethnoses in file: ");
            for (String ethnos : ethnoses) {
                System.out.println(ethnos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
