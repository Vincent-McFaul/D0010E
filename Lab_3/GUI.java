//Namn På Programmerare: Vincent McFaul och Gustav Andersson
package Lab_3;

import java.awt.Dimension;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

import Lab_3.kontroll.Tangentbordslyssnare;
import Lab_3.modell.Nivå;
import Lab_3.modell.Rum;
import Lab_3.vy.*;

// TODO: Klassen ska ärva klassen JFrame i standardbibliotekets paket 
// javax.swing och implementera gränssnittet Observer i paketet java.util. 
public class GUI extends JFrame implements java.util.Observer{

	private Målarduk målarduk;
	private int breddAvAllaRum;
	private int höjdAvAllaRum;
	private Dimension totalStorlekAvAllaRum;
		
	public GUI(Nivå enNivå) {
	 
		
		// TODO: Anropa metoden setDefaultCloseOperation med konstanten
		// JFrame.EXIT_ON_CLOSE.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// TODO: Gör så att enNivå observerar this (instansen av GUI som denna
		// kod håller på att skapa.
		enNivå.addObserver(this);
		
		// TODO: Tilldela tillståndsvariabeln målarduk en instans av klassen
		// Målarduk.
		målarduk = new Målarduk(enNivå);

		// TODO: Använd setPreferredSize på målarduk och sätt dess dimensioner
		// så alla rum faktiskt syns. Ett tips är att loopa igenom nivåns alla
		// rum och räkna ut vilken bredd och höjd målarduken måste ha(!)
		// Annars går det också att dra till med en storlek och sen bara
		// använda rum som säkert kan visas på målarduken.
		
		
		for(Rum Rum : enNivå.getRum()) {
			breddAvAllaRum += Rum.getBredd();
			höjdAvAllaRum += Rum.getHöjd();
			
		}
		Dimension totalStorlekAvAllaRum = new Dimension(breddAvAllaRum,höjdAvAllaRum);
		
		setPreferredSize(totalStorlekAvAllaRum);
		
		// TODO: Lägg till en KeyListener på målardiken, dvs en instans av
		// typen Tangentbordslyssnare. Notera att lyssnaren vill ha enNivå
		// som argument till konstruktorn för att kunna påverka just den
		// nivån.
		målarduk.addKeyListener(new Tangentbordslyssnare(enNivå)); 
		
		// TODO: Anropa setContentPane med målarduk, så att målarduk är
		// den yta som vår JFrame kommer att ha. Sen återstår bara att
		// göra setVisible(true) och pack().
		setContentPane(målarduk);
		setVisible(true);
		pack();
	}

	// TODO: Metoden update ska vara den i gränssnittet Observer. Lägg därför
	// till @Override på raden innan metodhuvudet. (Klassen måste sen importera
	// java.util.Observer.)
	@Override
	public void update(Observable o, Object arg) {
		// Detta anrop triggar ett anrop till paintComponent i Målarduk.
		// Avkommentera raden (när Målarduk "är-en" JPanel). 
		this.målarduk.repaint();
	}
}
