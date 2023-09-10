package com.xmlCreator.controllers;

import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.yaml.snakeyaml.reader.StreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;

@RestController
@RequestMapping("api/v1/xml-parser")
public class XmlParserController {

    @PostMapping("parse")
    public String parse(@RequestBody String xmlData) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        try{
            StringReader stringReader = new StringReader(xmlData);
            InputSource source = new InputSource(stringReader);
            Document parsed = builder.parse(source);
            Element documentElement = parsed.getDocumentElement();
            documentElement.normalize();
            printElement(documentElement);

            return parsed.toString();
        }catch (Exception e){
            e.printStackTrace();
           return e.toString();
        }
    }

    private void printNode(Node node){

        StringBuilder sb = new StringBuilder();
        String nodeName = node.getNodeName();
        String nodeValue = node.getNodeValue();
        short nodeType = node.getNodeType();

        sb.append("Node Name : " +nodeName +". ");
        sb.append("Node value: " +nodeValue +". ");
        sb.append("Node type: " +nodeType +". ");
        System.out.println(sb);
        System.err.println("______________");


        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++)
            printNode(children.item(i));
    }
    private void printElement(Element node){
        String tagName = node.getTagName();
        System.out.println(tagName);
        String nodeValue = node.getNodeValue();
        System.out.println(nodeValue);
        String textContent = node.getTextContent();
        System.out.println(textContent);
        System.out.println();

        NodeList childNodes = node.getChildNodes();
        for(int i=0; i<childNodes.getLength();i++){
            Node item = childNodes.item(i);
            printNode(item);
        }
    }
}
