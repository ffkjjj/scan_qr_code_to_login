package cf.zhul.scanqrcodetologin;

import cf.zhul.scanqrcodetologin.common.util.JsonUtils;
import cf.zhul.scanqrcodetologin.exception.TokenNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JacksonTests {

    @Test
    public void testObjectToJsonString() {
        ObjectMapper mapper = new ObjectMapper();

        String json = null;
        try {
            // Convert object to JSON string
//            TokenAuthentication tokenAuthentication = new TokenAuthentication("adfs");
            TokenNotFoundException tokenNotFoundException = new TokenNotFoundException();
            json = mapper.writeValueAsString(tokenNotFoundException);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
    }

    @Test
    public void testGeneratorJsonString() {
        ObjectNode objectNode = JsonUtils.getObjectNode();
        objectNode.put("code", 1);
        objectNode.put("msg", "hello");
        System.out.println(objectNode.toString());
    }
}
