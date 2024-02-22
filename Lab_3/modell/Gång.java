//Namn På Programmerare: Vincent McFaul och Gustav Andersson
package Lab_3.modell;

public class Gång {

	// TODO: Lägg till tillståndsvariabler för att hålla parametrarna till
	// konstruktorn. 
	private Rum från;
	private Rum till;
	private Väderstreck riktningsUtUrFrån;
	private Väderstreck riktningInTill;
	
	public Gång(Rum från, Väderstreck riktningUtUrFrån, Rum till,
			Väderstreck riktningInITill) {
		this.från = från;
		this.till = till;
		this.riktningsUtUrFrån = riktningUtUrFrån;
		this.riktningInTill = riktningInITill;
		
		// TODO: Tilldela tillståndsvariablerna parametervärdena.
	}
	public Rum getFrånRum(){
		return this.från;
	}
	
	public Rum getTillRum(){
		return this.till;
	}
	
	public Väderstreck getriktningsUtUrFrån(){
		return this.riktningsUtUrFrån;
	}
	public Väderstreck getriktningInTill(){
		return this.riktningInTill;
	}
	
	// TODO: Lägg till instansmetoder som returnerar tillståndsvariablernas
	// värden.
}
