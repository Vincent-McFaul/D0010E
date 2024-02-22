//Namn På Programmerare: Vincent McFaul och Gustav Andersson
package Lab_3.modell;

import java.util.ArrayList;

// TODO: Gör så att klassen Nivå ärver Observable i paketet java.util. 
public class Nivå extends java.util.Observable {
	
	private Rum startrum;
	private ArrayList<Rum> rum;
	
	// TODO: Lägg till tillståndsvariabler för att hålla reda på nivåns rum och
	// i vilket rum som användaren "är".

	public Nivå(Rum startrum, ArrayList<Rum> rum) {
		this.startrum = startrum;
		this.rum = rum;

		// TODO: Kopiera in startrum och rum in i tillståndsvariablerna.

		// TODO: Kontrollera att startrum finns med i rum. Om inte, kasta
		// undantag med lämpligt felmeddelande.
		if(rum.indexOf(startrum) == -1) {
			throw new IllegalArgumentException("startrum finns inte i arraylist rum");
		}

		// TODO: Kontrollera att inga rum överlappar varandra. Om det ändå är
		// fallet, kasta undantag med lämpligt felmeddelande.
		if (finns_det_inte_overlap() == false){
			throw new IllegalArgumentException("rummen överlappar");
		}
		
					}
		
	
	public boolean finns_det_inte_overlap() {

		for(int i = 0; i < rum.size(); i++) {

			Rum rum1 = rum.get(i);
			int vän1 = rum1.getÖvX();
			int upp1 = rum1.getÖvY();
			int ner1 = rum1.getÖvY()+rum1.getHöjd();
			int hö1 = rum1.getÖvX()+rum1.getBredd();
			
			for(int j = i+1; j < rum.size(); j++) {
				//System.out.println("BRABRA");

				Rum rum2 = rum.get(j);
				int vän2 = rum2.getÖvX();
				int upp2 = rum2.getÖvY();
				int ner2 = rum2.getÖvY()+rum2.getHöjd();
				int hö2 = rum2.getÖvX()+rum2.getBredd();
				/*
				int topvän1 = rum1.getÖvX();
				int tophög1 = rum1.getÖvX+rum1.getBredd();
				int botvän1
				int bothög1
				
				int topvän2
				int tophög2
				int botvän2
				int bothög2
				
				
				if(())
				*/
				
				if((hö1 > vän2 && vän1 < hö2) || (hö2 > vän1 && vän2 < hö1)) {
					
					if ((upp1 <= ner2 && upp2 <= ner1) || (upp2 <= ner1 && upp1 <= ner2)) {
						throw new IllegalArgumentException("overlappar");
					}
				
				}
				
				}
			
		}
		
		return true;
		
	
	}
	
	public Rum getStartrum() {
        return this.startrum;
    }
	
	public void setStartrum(Rum startrum) {
        this.startrum = startrum;
    }
	
	public ArrayList<Rum> getRum() {
        return this.rum;
    }
	
	public void setRum(ArrayList<Rum> rum) {
        this.rum = rum;
    }
	// TODO: Skriv en instansmetod som returnerar alla rummen. Denna behöver
	// Målarduk för att veta vilka rum som finns på nivån och som ska ritas ut.
	public ArrayList<Rum> RumAlla(){
        return rum;
    }

	// TODO Skriv en instansmetod som returnerar en referens till det rum som
	// användaren "är i".

	// TODO: Skriv klar instansmetoden hoppaÅt nedan så att den ändrar det rum
	// som användaren "är i" om det är möjligt genom att följa en gång från
	// rummet och i riktning väderstreck.
	//
	// Om väderstreck inte är en riktning i vilken det finns en gång, så ändras
	// inte rummet användaren "är i" (och inte heller kastas undantag). (Denna
	// metod använder kontrolldelen av programmet för att begära ett hopp till
	// angränsande rum efter att användaren tryckt på en tangent.)

	public void hoppaÅt(Väderstreck väderstreck) {
		if(getStartrum().finnsUtgångÅt(väderstreck)) {
			if(startrum == startrum.gångenÅt(väderstreck).getFrånRum()) {
				startrum = startrum.gångenÅt(väderstreck).getTillRum();
			}
			else {
				startrum = startrum.gångenÅt(väderstreck).getFrånRum();
			}
		this.setChanged();
		this.notifyObservers();
		}
	}
}
