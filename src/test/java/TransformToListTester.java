import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransformToListTester {
    TransformTolist transformTolist = new TransformTolist();

    @Test
    public void testParseXmlOnNull() {
        String argument = "staff.xml";
        List result = transformTolist.parseXML(argument);
        assertNotNull(result);
    }

    @Test
    public void testParseXmlOnEmtyness() {
        String argument = "staff.xml";
        List result = transformTolist.parseXML(argument);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testParseXmlOnContainingEmloeey() {
        String argument = "staff.xml";
        List list = transformTolist.parseXML(argument);
        assertTrue(list.get(0).getClass().equals(Employee.class));
    }

    @Test
    public void testParseXmlOnContainig() {
        String argument = "staff.xml";
        List result = transformTolist.parseXML(argument);
        assertThat(Arrays.asList(result.toArray()), hasSize(2));
    }

    @Test
    public void testListToJson() {
        List argument = transformTolist.parseXML("staff.xml");
        String result = transformTolist.listToJson(argument);
        assertThat(result, stringContainsInOrder("age\"", "id\"", "firstName\"", "country\""));
    }


}