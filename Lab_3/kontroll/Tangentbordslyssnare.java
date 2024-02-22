//Namn På Programmerare: Vincent McFaul och Gustav Andersson
package Lab_3.kontroll;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Lab_3.modell.Nivå;
import Lab_3.modell.Väderstreck;

public class Tangentbordslyssnare implements KeyListener {
	private Nivå enNivå;

	public Tangentbordslyssnare(Nivå enNivå) {
		this.enNivå = enNivå;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO: Skriv klar denna metod som automatiskt anropas så snart
		// användaren tryckt på en tangent på tangentbordet. Metoden ska
		// översätta tangenten till ett väderstreck och sen anropa hoppaÅt i
		// enNivå med detta väderstreck. Här ska
		int Tagent = e.getKeyCode();
		
		switch(Tagent) {
		case KeyEvent.VK_W:
			enNivå.hoppaÅt(Väderstreck.NORR);
			break;
			
		case KeyEvent.VK_A:
			enNivå.hoppaÅt(Väderstreck.VÄSTER);
			break;
			
		case KeyEvent.VK_S:
			enNivå.hoppaÅt(Väderstreck.SÖDER);
			break;
			
		case KeyEvent.VK_D:
			enNivå.hoppaÅt(Väderstreck.ÖSTER);
			break;
			
		default:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Används inte men måste implementeras eftersom keyTyped finns i
		// gränssnittet KeyListener.
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Används inte men måste implementeras eftersom keyReleased finns is
		// gränssnittet KeyListener.
	}
}
