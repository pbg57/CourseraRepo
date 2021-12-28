package com.griffin.testdome;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Folders {
    public static Collection<String> folderNames(String xml, char startingLetter) throws Exception {
        DocumentBuilderFactory dbf =  DocumentBuilderFactory.newInstance();
        Collection<String> retColl = new ArrayList<>();

        try {
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new InputSource(new StringReader(xml)));
                doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            Node firstNode = doc.getDocumentElement().getAttributeNode("name");
            System.out.println(" Element Attr :" + firstNode);

                NodeList nodeList = doc.getElementsByTagName("folder");
            System.out.println("Node List Size : " + nodeList.getLength());

                for (int idx = 0; idx < nodeList.getLength(); idx++) {
                    Node node = nodeList.item(idx);
                    System.out.println("NOde Name : " + node.getNodeName());

                    if (node.getNodeName().equals("folder")) {
                        System.out.println("Folder Element : " + node.getNodeName());
                        Element element = (Element)node;
                        String attrName = element.getAttribute("name");
                        System.out.println("Folder Attr : " + attrName);
                        if (attrName.startsWith(String.valueOf(startingLetter))) {
                            retColl.add(attrName);
                        }
                    }
                }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return retColl;

    }

    public static void main(String[] args) throws Exception {
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                        "<folder name=\"c\">" +
                        "<folder name=\"program files\">" +
                        "<folder name=\"uninstall information\" />" +
                        "</folder>" +
                        "<folder name=\"users\" />" +
                        "</folder>";

        Collection<String> names = folderNames(xml, 'u');
        for(String name: names)
            System.out.println(name);
    }
}