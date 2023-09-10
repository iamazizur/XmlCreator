package com.xmlCreator.controllers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.InputStream;
import java.io.StringReader;

@RestController
@RequestMapping("api/v1/json-parser")
public class JsonParserController {

    private void traverseJsonTree(JsonNode node, String prefix) {
        if(node == null){
            System.err.println("node is null");
            return;
        }
        if (node.isObject()) {
            System.out.println(prefix + "Object");
            node.fields().forEachRemaining(entry -> {
                System.out.println(prefix + "  Field: " + entry.getKey());
                traverseJsonTree(entry.getValue(), prefix + "    ");
            });
        } else if (node.isArray()) {
            System.out.println(prefix + "Array");
            for (int i = 0; i < node.size(); i++) {
                System.out.println(prefix + "  Element " + i);
                traverseJsonTree(node.get(i), prefix + "    ");
            }
        } else if (node.isValueNode()) {
            System.out.println(prefix + "Value: " + node.asText());
        }
    }
    @GetMapping("parse")
    public String parse(@RequestBody String jsonStr){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Create a JsonFactory instance
            JsonFactory jsonFactory = objectMapper.getFactory();

            StringReader stringReader = new StringReader(jsonStr);
            InputSource source = new InputSource(stringReader);
            int read = stringReader.read();
            // Create a JsonParser from the input stream
            JsonParser jsonParser = jsonFactory.createParser(stringReader);

            File file = new File("/Users/azizurrahman/Desktop/INodeManager/test.json");
            // Read the JSON data into a JsonNode
            TreeNode treeNode = jsonParser.readValueAsTree();
            JsonNode rootNode = objectMapper.readTree(file);
            traverseJsonTree((JsonNode) treeNode,"tree node");
            traverseJsonTree(rootNode,null);

            return rootNode.toPrettyString();
        }
        catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
}
