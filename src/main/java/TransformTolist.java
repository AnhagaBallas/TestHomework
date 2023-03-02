import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TransformTolist {
    public void writeString(String json) {
        try (FileWriter fileWriter = new FileWriter("data.json")) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List parseXML(String file) {
        List<Employee> employeeList = new ArrayList<>();
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(file);
            Node root = doc.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Employee employee1 = new Employee();
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element employee = (Element) node;

                    employee1.id = (Long.parseLong(employee.getElementsByTagName("id").item(0).getTextContent()));
                    employee1.firstName = (employee.getElementsByTagName("firstName").item(0).getTextContent());
                    employee1.lastName = (employee.getElementsByTagName("lastName").item(0).getTextContent());
                    employee1.country = (employee.getElementsByTagName("country").item(0).getTextContent());
                    employee1.age = (Integer.parseInt(employee.getElementsByTagName("age").item(0).getTextContent()));

                    employeeList.add(employee1);


                }
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return employeeList;
    }

    public String listToJson(List list) {
        String json;
        Type listType = new TypeToken<List>() {
        }.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        json = gson.toJson(list, listType);
        return json;
    }
}
