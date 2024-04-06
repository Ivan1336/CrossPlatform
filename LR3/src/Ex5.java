import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Ex5 {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory documentBuilderFactory= DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse("Popular_Baby_Names_NY_DOM.xml");

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("kid");

            System.out.println("Birth year\tGender\tEthnicity\tName\tCount\tRating");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String year = element.getElementsByTagName("brth_yr").item(0).getTextContent();
                    String gender = element.getElementsByTagName("gndr").item(0).getTextContent();
                    String ethnos = element.getElementsByTagName("ethcty").item(0).getTextContent();
                    String name = element.getElementsByTagName("nm").item(0).getTextContent();
                    String count = element.getElementsByTagName("cnt").item(0).getTextContent();
                    String rating = element.getElementsByTagName("rnk").item(0).getTextContent();
                    System.out.println(year+"\t"+gender+"\t"+ethnos+"\t"+name+"\t"+count+"\t"+rating);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
