import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class GraphIO {

	public static void readFile(Graph g, String filename) throws IOException {
		try {
			File file = new File(filename); 
			Scanner scanfile = new Scanner(file);
			int TotalNodes = scanfile.nextInt(); //talet på 1a raden av filen som ger antal noder
			
			for (int i = 0; i < TotalNodes; i++) { //läser in noder (definerat av TotalNodes)
				
				//(identitet) (x-kord) (y-kord)
				int identity = scanfile.nextInt(); 		
				int x = scanfile.nextInt();
				int y = scanfile.nextInt();
				
				g.addNode(identity, x, y); //placerar punkten
			}
				
			while (scanfile.hasNext()) { //läser in kanter från filen
				
				//(identitet-1) (identitet-2) (vikt?)
				int node1 = scanfile.nextInt();			
				int node2 = scanfile.nextInt();			
				int weight = scanfile.nextInt(); //? inge skillnad, fråga handledaren
				
				g.addEdge(node1, node2, weight); //drar kanterna
			}
			scanfile.close(); //stänger scanner
		} 
		catch (Exception e) {
			throw new IOException();
		}
	}
}