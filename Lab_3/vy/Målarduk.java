//Namn På Programmerare: Vincent McFaul och Gustav Andersson
package Lab_3.vy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JFrame;

import Lab_3.verktyg.Grafik;
import Lab_3.GlobalaKonstanter;
import Lab_3.modell.Gång;
import Lab_3.modell.Nivå;
import Lab_3.verktyg.Punkt;
import Lab_3.verktyg.Grafik;
import Lab_3.modell.Rum;
import Lab_3.modell.Väderstreck;

// TODO: Ändra nästa rad så att en Målarduk "är-en" JPanel. 
public class Målarduk extends JPanel {

	private final Nivå enNivå;

	public Målarduk(Nivå enNivå) {
		this.enNivå = enNivå;
		// TODO: Sätt bakgrundsfärgen på this till MARKFÄRG.
		this.setBackground(GlobalaKonstanter.MARKFÄRG);
		// TODO: Anropa metoden setFocusable på this och med argumentet true.
		// Detta behövs för att lyssnaren i programmet ska reagera.
		this.setFocusable(true);
	}

	// TODO: Lätt till @Override på metoden nedan.
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Lägg till ett anrop till paintComponent i omedelbara
		// överklassen (JPanel). Skicka med g som argument.
		super.paintComponent(g);
		
		// TODO: Lägg till kod som ritar ut en grafisk vy av enNivå.
		//
		// För att underlätta finns hjälpmetoder som ska skrivas klara. Studera
		// noga bilderna i labbinstruktionen för att få fram formlerna för
		// bas- och pivotpunkternas koordinater. Använd ritmetoderna i klassen
		// labb3.verktyg.Grafik. Anropa hjälpmetoderna från paintComponent.
		
		for (Rum R: enNivå.RumAlla()) {
            ritaRum(g, R);
            ritaGångarFrånRum(g,R);
        }

        ritaMarkörFörVarAnvändarenÄr(g);
	}

	private void ritaRum(Graphics g, Rum ettRum) {

	    g.setColor(GlobalaKonstanter.VÄGGFÄRG);
	    g.fillRect(ettRum.getÖvX(), ettRum.getÖvY(), ettRum.getBredd(), ettRum.getHöjd());
	    g.setColor(ettRum.getGolvfärg());
	    
	    g.fillRect(ettRum.getÖvX() + GlobalaKonstanter.VÄGGTJOCKLEK,
	    			ettRum.getÖvY() + GlobalaKonstanter.VÄGGTJOCKLEK,
	    			ettRum.getBredd() - GlobalaKonstanter.DUBBEL_VÄGGTJOCKLEK,
	    			ettRum.getHöjd() - GlobalaKonstanter.DUBBEL_VÄGGTJOCKLEK);
	}

	private void ritaGångarFrånRum(Graphics g, Rum ettRum) {
		for(Väderstreck Vs : Väderstreck.values()) {
			if(ettRum.finnsUtgångÅt(Vs)){
				System.out.println(ettRum.getGolvfärg() +" "+Vs + " kom igenom");
				Punkt bas = baspunkt(ettRum,Vs);
				Punkt piv = pivotpunkt(ettRum,Vs);
				System.out.println(piv);
				Punkt mitt = new Punkt((piv.x()+bas.x())/2,(piv.y()+bas.y())/2);
				Grafik.drawThickLine(g,bas,piv,8,GlobalaKonstanter.GÅNGFÄRG);
				Grafik.fillCircle(g, piv, 4, GlobalaKonstanter.GÅNGFÄRG);
				ritaGång(g, ettRum.gångenÅt(Vs));
			}
			System.out.println(Vs+ " Kom inte igenom");
		}

	}

	private Punkt baspunkt(Rum ettRum, Väderstreck enRiktning) {
		
		//ArrayList<Integer> pos = new ArrayList<>(2);

		int x = ettRum.getÖvX();
		int y = ettRum.getÖvY();
		int breddAvRum = ettRum.getBredd();
		int höjdAvRum = ettRum.getHöjd();
		int dir = enRiktning.index();
		
			switch(dir) {
			case(0):{
				x += breddAvRum/2;
				y += GlobalaKonstanter.VÄGGTJOCKLEK;
				break;
			}
			case(1):{
				y += höjdAvRum/2; 
				x += breddAvRum - GlobalaKonstanter.VÄGGTJOCKLEK;
				break;
			}
			case(2):{
				y += höjdAvRum - GlobalaKonstanter.VÄGGTJOCKLEK ;
				x += breddAvRum/2;
				break;
			}
			case(3):{
				y += höjdAvRum/2;
				x += GlobalaKonstanter.VÄGGTJOCKLEK;//2+GlobalaKonstanter.VÄGGTJOCKLEK; 
				break;
			}
		}
		
		return new Punkt(x,y); /* endast här för att Eclipse inte ska klaga */

	}

	private Punkt pivotpunkt(Rum ettRum, Väderstreck enRiktning) {

		int x = ettRum.getÖvX();
		int y = ettRum.getÖvY();
		int breddAvRum = ettRum.getBredd();
		int höjdAvRum = ettRum.getHöjd();
		int dir = enRiktning.index();
		
			switch(dir) {
			case(0):{
				x += breddAvRum/2;
				y -= GlobalaKonstanter.VÄGGTJOCKLEK/2;
				break;
			}
			case(1):{
				y += höjdAvRum/2; 
				x += breddAvRum + GlobalaKonstanter.VÄGGTJOCKLEK;
				break;
			}
			case(2):{
				y += höjdAvRum +GlobalaKonstanter.VÄGGTJOCKLEK;
				x += breddAvRum/2;
				break;
			}
			case(3):{
				y += höjdAvRum/2;
				x -= GlobalaKonstanter.VÄGGTJOCKLEK;
					//2+GlobalaKonstanter.VÄGGTJOCKLEK; 
				break;
			}
			}
		return new Punkt(x,y); /* endast här för att Eclipse inte ska klaga */

	}

	private void ritaGång(Graphics g, Gång enGång) {
		
		
		System.out.println(enGång.getFrånRum());
		System.out.println(enGång.getriktningsUtUrFrån());
		System.out.println(enGång.getTillRum());
		System.out.println(enGång.getriktningInTill());
		
		Grafik.drawThickLine(g, pivotpunkt(enGång.getFrånRum(), enGång.getriktningsUtUrFrån()), pivotpunkt(enGång.getTillRum(), enGång.getriktningInTill()), GlobalaKonstanter.VÄGGTJOCKLEK, GlobalaKonstanter.GÅNGFÄRG);
	}

	private void ritaMarkörFörVarAnvändarenÄr(Graphics g) {
		Rum nuRum = enNivå.getStartrum();
		Punkt spelpos = new Punkt(nuRum.getÖvX()+nuRum.getBredd()/2, nuRum.getÖvY()+nuRum.getHöjd()/2);
		Grafik.fillCircle(g, spelpos, 4, GlobalaKonstanter.GÅNGFÄRG);
	}
}
