//Namn På Programmerare: Vincent McFaul och Gustav Andersson
package Lab_3.modell;

import java.awt.Color;

import Lab_3.verktyg.Punkt;

public class Rum {
	private Color golvfärg;
    private int bredd;
    private int höjd;
    private int övX;
    private int övY;
	Gång[] GångArray = new Gång[4];
	private Punkt punkt;
	

	// TODO: Lägg till tillståndsvariabler.

	public Rum(Color golvfärg, int bredd, int höjd, int övX, int övY) {
		this.golvfärg = golvfärg;
        this.bredd = bredd;
        this.höjd = höjd;
        this.övX = övX;
        this.övY = övY;
		// TODO: Kopiera parametrarna in i tillståndsvariablerna. (övX,övY) är
		// koordinaten för rummets övre vänstra hörn och lagras lämpligen som en
		// instans av klassen Punkt i paketet verktyg.
	}
	
	
	public Color getGolvfärg() {
        return golvfärg;
    }

	
	public void setGolvfärg(Color golvfärg) {
        this.golvfärg = golvfärg;
    }
	
	public int getBredd() {
	    return bredd;
	}

	public void setBredd(int bredd) {
	    this.bredd = bredd;
	}

	public int getHöjd() {
	    return höjd;
	}

	public void setHöjd(int höjd) {
	    this.höjd = höjd;
	}

	public int getÖvX() {
	    return övX;
	}

	public void setÖvX(int övX) {
	    this.övX = övX;
	}

	public int getÖvY() {
	    return övY;
	}

	public void setÖvY(int övY) {
	    this.övY = övY;
	}
	
	public Punkt getPunkt(){
		return this.punkt;
	}
	
	
	// TODO: Skriv instansmetoden
	//
	// finnsUtgångÅt(Väderstreck väderstreck)
	//
	// som ska kontrollera om det från ett rum finns en utgång åt visst
	// väderstreck.

	public boolean finnsUtgångÅt(Väderstreck väderstreck) {
		switch(väderstreck) {
		case NORR: {
			if (GångArray[0] == null) {
				return false; }
			else return true;
		}
		case ÖSTER: {
			if (GångArray[1] == null) {
				return false; }
			else return true;
		}
		case SÖDER: {
			if (GångArray[2] == null) {
				return false; }
			else return true;
		}
		case VÄSTER: {
			if (GångArray[3] == null) {
				return false; }
			else return true;
		}
		//return GångArray[väderstreck.index()] != null; //lmao tog 3 timmar att fixa den här (!=)
	}
		return false; }
	
	// TODO: Skriv instansmetoden
	//
	// Gång gångenÅt(Väderstreck väderstreck) som
	//
	// returnerar den gång som leder från ett rum i riktning väderstreck. Om
	// sådan gång saknas ska ett undantag kastas med lämpligt felmeddelande.

	public Gång gångenÅt(Väderstreck väderstreck){
		if(GångArray[väderstreck.index()] != null){
			return GångArray[väderstreck.index()];
		}
		throw new IllegalArgumentException();
	}
	
	// TODO: Skrivklar metoden nedan som kopplar ihop två rum med en gång.

	public static void kopplaIhop(Rum från, Väderstreck riktningUtUrFrån,
			
			Rum till, Väderstreck riktningInITill) {
			//if (finnsUtgångÅt(riktningUtUrFrån) == true){
			if(från.finnsUtgångÅt(riktningUtUrFrån) || till.finnsUtgångÅt(riktningInITill))
				throw new IllegalArgumentException(); 
	
			
			Gång gång = new Gång(från,riktningUtUrFrån,till,riktningInITill);
			//Gång gång = new Gång(till,riktningInITill,från,riktningUtUrFrån);
			
			från.GångArray[riktningUtUrFrån.index()] = gång;
			till.GångArray[riktningInITill.index()] = gång;
			//}
		}
	}