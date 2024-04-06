import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class Ex2 {
    public static void main(String[] args) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Validator validator = factory.newSchema(new File("Popular_Baby_Names_NY.xsd")).newValidator();
            validator.validate(new StreamSource(new File("Popular_Baby_Names_NY.xml")));
            System.out.println("Validation successful");
        } catch (Exception e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }
}
