package com.yoj.collections.level3.part10.comment_inside_xml;

import com.yoj.collections.level3.part10.classes.Shop;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommentInsideXmlTest {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException, IOException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        marshaller.marshal(obj,writer);

        Document doc = convertStringToXml(writer.toString());

        AddCdataSection(doc);

        addCommentToNodesWithTagName(tagName, comment, doc);

        return convertXmlToString(doc);
    }

    public static String toXml(Object obj) throws JAXBException  {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(obj,writer);

        Document doc = convertStringToXml(writer.toString());

        AddCdataSection(doc);

        return convertXmlToString(doc);
    }

    private static Document convertStringToXml(String xmlString) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder builder = dbf.newDocumentBuilder();

            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));

            return doc;

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

    }

    private static void addCommentToNodesWithTagName(String tagName, String comment, Document doc) {
        NodeList childNodes = doc.getElementsByTagName(tagName);


        for (int i=0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeName().equals(tagName)){
                Comment commentNode = doc.createComment(comment);
                item.getParentNode().insertBefore(commentNode,item);
            }
        }
    }

    private static String convertXmlToString(Document doc) {
        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
            transformer.transform(domSource, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }

    private static void AddCdataSection(Document doc) {
        String cdataContent = "\n <secretData> \n some another </secretData> \n";
        NodeList shop  = doc.getElementsByTagName("shop");


        Element item = doc.createElement("secretData");
        CDATASection cdataSection = doc.createCDATASection("");
        cdataSection.setTextContent("secretText");

        item.appendChild(cdataSection);
        shop.item(0).appendChild(item);
    }




    public static void main(String[] args) throws JAXBException, IOException, ParserConfigurationException, SAXException {

        Shop shop = new Shop();
        shop.setCount(14);
        shop.setProfit(1.2d);
        shop.setSecretData(new String[]{"know", "your", "meme", "motherfucker"});
        List<String> items = new ArrayList<>();
        items.add("Bow");
        items.add("Sword");
        items.add("Spear");
        shop.setGoods(new Shop.Goods(items));

        System.out.println(toXml(shop));
        System.out.println(" ********************************* ");
        System.out.println(toXmlWithComment(shop, "secretData", "it's a comment"));

    }
}
