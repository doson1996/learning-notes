package com.ds.dp.interpreter.unused;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;

/**
 * @Author ds
 * @Date 2021/4/7 9:35
 * @Description
 */
public class ReadAppXml {

    public void read(InputStream is) {
        try {
            Document doc = null;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(is);
          //  doc.normalize();

            NodeList jdbc = doc.getElementsByTagName("jdbc");
            NodeList dcList = ((Element) jdbc.item(0)).getElementsByTagName("driver-class");
            String dc = dcList.item(0).getFirstChild().getNodeValue();
            System.out.println("dc = " + dc);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
