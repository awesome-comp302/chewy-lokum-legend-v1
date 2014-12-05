import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
 
public class ReadXMLFile {
	
	private static ReadXMLFile instance;
	private static GamePlay gp;
	
	private ReadXMLFile(){
		
	}
	
	public static ReadXMLFile getInstance(){
		if (instance == null) {
			instance = new ReadXMLFile();
		}
		return instance;
	}
	
	public GamePlay loadGame(){
		return gp;
	}
 
  public void read() {
 
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
	
	int passingScoreState = Integer.parseInt(passingScore.getTextContent());
	int currentScoreState = Integer.parseInt(currentScore.getTextContent());
	int remainingMovesState = Integer.parseInt(remainingMoves.getTextContent());
	int levelState = Integer.parseInt(level.getTextContent());
	
 
	/*System.out.println("----------------------------");
	
	System.out.println("passingScore : " + passingScoreState);
	System.out.println("currentScore : " + currentScoreState);
	System.out.println("remainingMoves : " + remainingMovesState);
	System.out.println("level : " + levelState);
	*/

	
	Element el = (Element) player.item(0);
	

	//System.out.println(devam.item(0).getTextContent());
	//System.out.println("----------------------------");
	
	String name = getTextValue(el,"name");
	int id = getIntValue(el,"id");


	/*System.out.println("name : " + name);
	System.out.println("id : " + id);
	System.out.println("----------------------------");
	*/
	NodeList lokums = (NodeList) board.item(0);
	NodeList lokum = (NodeList) lokums.item(0);
	
	String width = ((Node) lokums).getAttributes().getNamedItem("width").getNodeValue();
	String height = ((Node) lokums).getAttributes().getNamedItem("height").getNodeValue();
	
	Board boardState = new Board(Integer.parseInt(width), Integer.parseInt(height));
	Level levelTest = new Level(passingScoreState, remainingMovesState, boardState, levelState);
	Player playerTest = new Player(name);
	playerTest.setID(id);
	gp = new GamePlay(levelTest, playerTest);
	
	for (int i = 0; i < lokum.getLength(); i++) {
		String type = getTextValue((Element) lokum.item(i), "type");
		String sType = getTextValue((Element) lokum.item(i), "sType");
		int x = Integer.parseInt(getTextValue((Element) lokum.item(i), "xPos"));
		int y = Integer.parseInt(getTextValue((Element) lokum.item(i), "yPos"));
		/*System.out.println("***");
		System.out.println("type : " + type);
		System.out.println("sType : " + sType);
		System.out.println("xPos : " + x);
		System.out.println("yPos : " + y);
		*/
		
		boardState.fillCellAt(x, y, new Lokum(type, sType));
	}
	
	
	/*
	System.out.println(playerTest);
	System.out.println(levelTest);
	System.out.println(gp);
	System.out.println(boardState);
	
	*/
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