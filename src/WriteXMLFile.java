import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 

import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
public class WriteXMLFile {
	private static String pID;
	private static String pName;
	private static Board board;
	private static Level level;
	private static GamePlay gp;
	private static Element lokum[][];
	
	
 
	public static void main(String argv[]) {
		pID = "1";
		pName = "Berk";
		board = new Board(5, 5);
		level = new Level(5, 10,board, 1);
		gp = new GamePlay(level);
		
		gp.updateBoardGC();
		
		lokum = new Element [board.getWidth()][board.getHeight()];
 
	  try {
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("Game");
		doc.appendChild(rootElement);
 
		// staff elements
		Element player = doc.createElement("Player");
		rootElement.appendChild(player);
		
		Element Board = doc.createElement("Board");
		rootElement.appendChild(Board);
 
		// set attribute to staff element
		//Attr attr = doc.createAttribute("id");
		//attr.setValue(ID);
		//player.setAttributeNode(attr);
 
		// shorten way
		//player.setAttribute("id", ID);
		
		// lastname elements
		Element id = doc.createElement("id");
		id.appendChild(doc.createTextNode(pID));
		player.appendChild(id);
 
		// firstname elements
		Element name = doc.createElement("name");
		name.appendChild(doc.createTextNode(pName));
		player.appendChild(name);
 
		// lastname elements
		//Element lastname = doc.createElement("lastname");
		//lastname.appendChild(doc.createTextNode("mook kim"));
		//staff.appendChild(lastname);
 
		// nickname elements
		//Element nickname = doc.createElement("nickname");
		//nickname.appendChild(doc.createTextNode("mkyong"));
		//staff.appendChild(nickname);
 
		// salary elements
		//Element salary = doc.createElement("salary");
		//salary.appendChild(doc.createTextNode("100000"));
		//staff.appendChild(salary);
		
		Element lokums = doc.createElement("lokums");
		Board.appendChild(lokums);
		
		for(int i=0; i<board.getWidth(); i++){
			for(int j=0; j<board.getHeight(); j++){
				lokum[i][j] = doc.createElement("lokum");
				lokums.appendChild(lokum[i][j]);
				
				Element type = doc.createElement("type");
				type.appendChild(doc.createTextNode(board.cellAt(i, j).getCurrentObject().getType()));
				lokum[i][j].appendChild(type);
				
				Element sType = doc.createElement("sType");
				sType.appendChild(doc.createTextNode(((Lokum) board.cellAt(i, j).getCurrentObject()).getSpecialType()));
				lokum[i][j].appendChild(sType);
				
				
				Element xPos = doc.createElement("xPos");
				xPos.appendChild(doc.createTextNode(""+i));
				lokum[i][j].appendChild(xPos);
				
				Element yPos = doc.createElement("yPos");
				yPos.appendChild(doc.createTextNode(""+j));
				lokum[i][j].appendChild(yPos);
				
				
			}
			
		}
		
		/*lokum[0][0] = doc.createElement("lokum");
		lokums.appendChild(lokum[0][0]);
		
		Element type = doc.createElement("type");
		type.appendChild(doc.createTextNode(board.cellAt(0, 0).getCurrentObject().getType()));
		lokum[0][0].appendChild(type);
		
		Element sType = doc.createElement("sType");
		sType.appendChild(doc.createTextNode(((Lokum) board.cellAt(0, 0).getCurrentObject()).getSpecialType()));
		lokum[0][0].appendChild(sType);
		
		Element position = doc.createElement("position");
		lokum[0][0].appendChild(position);
		
		position.setAttribute("X", "0");
		position.setAttribute("Y", "0");*/
		
		Element passingScore = doc.createElement("passingScore");
		passingScore.appendChild(doc.createTextNode(""+level.getPassingScore()));
		rootElement.appendChild(passingScore);
		
		Element currentScore = doc.createElement("currentScore");
		currentScore.appendChild(doc.createTextNode(""+gp.getScore()));
		rootElement.appendChild(currentScore);
		
		Element remainingMoves = doc.createElement("remainingMoves");
		remainingMoves.appendChild(doc.createTextNode(""+gp.getMovementsLeft()));
		rootElement.appendChild(remainingMoves);
		
		Element levelID = doc.createElement("level");
		levelID.appendChild(doc.createTextNode(""+level.getLevelId()));
		rootElement.appendChild(levelID);
 
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("file.xml"));
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
 
		transformer.transform(source, result);
 
		System.out.println("File saved!");
 
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
}