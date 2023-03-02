import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        TransformTolist transformerToList=new TransformTolist();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("staff");
            document.appendChild(root);

            root.appendChild(getEmploee(document, "1", "John", "Smith", "USA", "25"));
            root.appendChild(getEmploee(document, "2", "Inav", "Petreov", "ru", "23"));


            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("staff.xml"));
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(domSource, streamResult);


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        List list = transformerToList.parseXML("staff.xml");
        String json = transformerToList.listToJson(list);
        transformerToList.writeString(json);

    }

    public static Node getEmploee(Document doc, String id, String firstName, String lastName, String country, String age) {
        Element employee = doc.createElement("Employee");
        employee.appendChild(getEmployeeElements(doc, "id", id));
        employee.appendChild(getEmployeeElements(doc, "firstName", firstName));
        employee.appendChild(getEmployeeElements(doc, "lastName", lastName));
        employee.appendChild(getEmployeeElements(doc, "country", country));
        employee.appendChild(getEmployeeElements(doc, "age", age));
        return employee;

    }

    public static Node getEmployeeElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

}
