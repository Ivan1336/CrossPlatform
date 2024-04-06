import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Ex4 {
    private String ethnicity, gender, name;
    private int year, count, rating;
    static private int flag;
    private static Scanner in = new Scanner(System.in);

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public static void main(String[] args) {
        ArrayList<Ex4> allKids = new ArrayList<>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if(qName.equalsIgnoreCase("brth_yr"))
                        allKids.add(new Ex4());
                    switch (qName) {
                        case "brth_yr":
                            flag = 1;
                            break;
                        case "gndr":
                            flag = 2;
                            break;
                        case "ethcty":
                            flag = 3;
                            break;
                        case "nm":
                            flag = 4;
                            break;
                        case "cnt":
                            flag = 5;
                            break;
                        case "rnk":
                            flag = 6;
                            break;
                    }
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    if (!allKids.isEmpty()) {
                        String data = new String(ch, start, length).trim();
                        switch (flag) {
                            case 1:
                                allKids.get(allKids.size() - 1).setYear(Integer.parseInt(data));
                                break;
                            case 2:
                                allKids.get(allKids.size() - 1).setGender(data);
                                break;
                            case 3:
                                allKids.get(allKids.size() - 1).setEthnicity(data);
                                break;
                            case 4:
                                allKids.get(allKids.size() - 1).setName(data);
                                break;
                            case 5:
                                allKids.get(allKids.size() - 1).setCount(Integer.parseInt(data));
                                break;
                            case 6:
                                allKids.get(allKids.size() - 1).setRating(Integer.parseInt(data));
                                break;
                        }
                    }
                }
            };
            saxParser.parse("Popular_Baby_Names_NY.xml", handler);

            Collections.sort(allKids, Comparator.comparingInt(Ex4::getRating));

            int num = 0;
            String selectEthnicity = "";
            System.out.print("Enter number of names: ");
            num = in.nextInt();
            System.out.print("Enter ethnicity: ");
            selectEthnicity = in.next();

            if (!checkEthnicity(selectEthnicity, allKids)) {
                System.out.println("There aren't such ethnicity");
                System.exit(0);
            }

            ArrayList<Ex4> selectedKids = fillSelected(allKids, selectEthnicity, num);

            System.out.println("Birth year\tGender\tEthnicity\tName\tCount\tRating");
            for (Ex4 val : selectedKids) {
                System.out.println(val.getYear() + "\t"
                        + val.getGender() + "\t"
                        + val.getEthnicity() + "\t"
                        + val.getName() + "\t"
                        + val.getCount() + "\t"
                        + val.getRating());
            }

            DocumentBuilderFactory factoryDOM = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factoryDOM.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("Kids");
            doc.appendChild(root);

            createDOM(selectedKids, doc, root);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Popular_Baby_Names_NY_DOM.xml"));
            transformer.transform(source, result);
            System.out.println("Data recording in xml DOM completed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkEthnicity(String check, ArrayList<Ex4> vals) {
        for (Ex4 val : vals) {
            if (val.getEthnicity().equalsIgnoreCase(check))
                return true;
        }
        return false;
    }

    public static ArrayList<Ex4> fillSelected(ArrayList<Ex4> source, String str, int count) {
        ArrayList<Ex4> out = new ArrayList<>();
        int i = 0;
        while (count > 0 && i < source.size()) {
            if (source.get(i).getEthnicity().equals(str)) {
                out.add(source.get(i));
                count--;
            }
            i++;
        }
        return out;
    }

    public static void createDOM(ArrayList<Ex4> arr, Document doc, Element root) {
        for (Ex4 p : arr) {
            Element kidElement = doc.createElement("kid");
            Element yearElement = doc.createElement("brth_yr");
            yearElement.setTextContent(String.valueOf(p.getYear()));
            Element genderElement = doc.createElement("gndr");
            genderElement.setTextContent(String.valueOf(p.getGender()));
            Element EthnicityElement = doc.createElement("ethcty");
            EthnicityElement.setTextContent(String.valueOf(p.getEthnicity()));
            Element nameElement = doc.createElement("nm");
            nameElement.setTextContent(String.valueOf(p.getName()));
            Element countElement = doc.createElement("cnt");
            countElement.setTextContent(String.valueOf(p.getCount()));
            Element ratingElement = doc.createElement("rnk");
            ratingElement.setTextContent(String.valueOf(p.getRating()));
            kidElement.appendChild(yearElement);
            kidElement.appendChild(genderElement);
            kidElement.appendChild(EthnicityElement);
            kidElement.appendChild(nameElement);
            kidElement.appendChild(countElement);
            kidElement.appendChild(ratingElement);
            root.appendChild(kidElement);
        }
    } 
}
