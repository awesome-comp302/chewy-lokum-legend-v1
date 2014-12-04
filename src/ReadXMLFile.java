import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
 
public class ReadXMLFile {
	private static String name;
	private static String id;
 
  public static void main(String argv[]) {
 
    try {
 
	File fXmlFile = new File("file.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
 
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();
 
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
 
	NodeList player = doc.getElementsByTagName("Player");
	NodeList board = doc.getElementsByTagName("Board");
	
	Node passingScore = doc.getElementsByTagName("passingScore").item(0);
	Node currentScore = doc.getElementsByTagName("currentScore").item(0);
	Node remainingMoves = doc.getElementsByTagName("remainingMoves").item(0);
	Node level = doc.getElementsByTagName("level").item(0);
 
	System.out.println("----------------------------");
	
	System.out.println("passingScore : " + passingScore.getTextContent());
	System.out.println("currentScore : " + currentScore.getTextContent());
	System.out.println("remainingMoves : " + remainingMoves.getTextContent());
	System.out.println("level : " + level.getTextContent());
	

	
	Element el = (Element) player.item(0);
	

	//System.out.println(devam.item(0).getTextContent());
	System.out.println("----------------------------");
	
	String name = getTextValue(el,"name");
	int id = getIntValue(el,"id");


	System.out.println("name : " + name);
	System.out.println("id : " + id);
	System.out.println("----------------------------");
	
	NodeList lokums = (NodeList) board.item(0);
	Element lokum = (Element) lokums.item(0);
	
	String type = getTextValue(lokum,"type");
	String sType = getTextValue(lokum,"sType");

	String x = getTextValue(lokum,"xPos");
	String y = getTextValue(lokum,"yPos");
	System.out.println("***");

	System.out.println("type : " + type);
	System.out.println("sType : " + sType);
	
	System.out.println("xPos : " + x );
	System.out.println("yPos : " + y );

	
	
	

    } catch (Exception e) {
	e.printStackTrace();
    }
  }
  
  private static String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
  
  private static int getIntValue(Element ele, String tagName) {
		//in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele,tagName));
	}
 
}