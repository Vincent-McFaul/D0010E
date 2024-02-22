//Namn På Programmerare: Vincent McFaul och Gustav Andersson
package Lab_3;

import static Lab_3.modell.Väderstreck.*;

import java.awt.Color;
import java.util.ArrayList;

import Lab_3.modell.*;

public class Huvudprogram {

	public static void main(String[] args) {

		ArrayList<Rum> rum = new ArrayList<Rum>();

		// Dessa rum och gångar morsvarar de i laborationsinstruktionen.

		// TODO Skapa även andra uppsättningar rum/gångar för att kunna testköra
		// ordentligt. Lägg varje uppsättning (även den givna nedan) i separata
		// metoder här i klassen. Såna bör vara deklarerade static för att kunna
		// anropas från main (som ju också är static).
		
		rum.add(new Rum(Color.RED, 75, 75, 25, 25));
		rum.add(new Rum(Color.BLUE, 75, 50, 50, 150));
		rum.add(new Rum(Color.MAGENTA, 100, 50, 175, 100));
		rum.add(new Rum(Color.YELLOW, 100, 75, 300, 200));
		rum.add(new Rum(Color.CYAN, 100, 75, 500, 50));
		rum.add(new Rum(Color.ORANGE, 75, 75, 450, 400));
		rum.add(new Rum(Color.PINK, 100, 50, 275, 325));
		rum.add(new Rum(Color.GREEN, 75, 100, 75, 275));
		rum.add(new Rum(Color.BLACK,50,50,600,400));
		
		Rum.kopplaIhop(rum.get(0), SÖDER, rum.get(1), NORR);
		Rum.kopplaIhop(rum.get(0), ÖSTER, rum.get(2), NORR);
		Rum.kopplaIhop(rum.get(1), SÖDER, rum.get(3), VÄSTER);
		Rum.kopplaIhop(rum.get(2), SÖDER, rum.get(3), NORR);
		Rum.kopplaIhop(rum.get(2), ÖSTER, rum.get(4), VÄSTER);
		Rum.kopplaIhop(rum.get(4), ÖSTER, rum.get(5), NORR);
		Rum.kopplaIhop(rum.get(5), SÖDER, rum.get(6), ÖSTER);
		Rum.kopplaIhop(rum.get(3), ÖSTER, rum.get(5), VÄSTER);
		Rum.kopplaIhop(rum.get(3), SÖDER, rum.get(6), NORR);
		Rum.kopplaIhop(rum.get(7), ÖSTER, rum.get(6), VÄSTER);
		
		//Rum.kopplaIhop(rum.get(5),)
		/*
		rum.add(new Rum(Color.RED, 50, 50, 25, 25));
		rum.add(new Rum(Color.BLUE, 50, 50, 50, 100));
		rum.add(new Rum(Color.MAGENTA, 50, 50, 130, 50));
		rum.add(new Rum(Color.YELLOW, 50, 50, 100, 200));
		rum.add(new Rum(Color.CYAN, 50, 50, 100, 50));
		rum.add(new Rum(Color.ORANGE, 50, 50, 450, 125));
		rum.add(new Rum(Color.PINK, 50, 50, 275, 325));
		rum.add(new Rum(Color.GREEN, 50, 50, 75, 275));

		Rum.kopplaIhop(rum.get(0), SÖDER, rum.get(1), NORR);
		Rum.kopplaIhop(rum.get(0), ÖSTER, rum.get(2), NORR);
		Rum.kopplaIhop(rum.get(1), SÖDER, rum.get(3), VÄSTER);
		Rum.kopplaIhop(rum.get(2), SÖDER, rum.get(3), NORR);
		Rum.kopplaIhop(rum.get(2), ÖSTER, rum.get(4), VÄSTER);
		Rum.kopplaIhop(rum.get(4), ÖSTER, rum.get(5), NORR);
		Rum.kopplaIhop(rum.get(5), SÖDER, rum.get(6), ÖSTER);
		Rum.kopplaIhop(rum.get(3), ÖSTER, rum.get(5), VÄSTER);
		Rum.kopplaIhop(rum.get(3), SÖDER, rum.get(6), NORR);
		Rum.kopplaIhop(rum.get(7), ÖSTER, rum.get(6), VÄSTER);
		*/
		// TODO: Skapa en nivå med argumenten rum.get(3) och rum.
		Nivå Nivå = new Nivå(rum.get(3), rum);
		// TODO: Skapa en instans av klassen GUI och skicka med nivån ovan som
		// argument. Man kan ha en referensvariabel som refererar till
		// GUI-instansen men det är är inte nödvändigt.
		new GUI(Nivå);
		
	}

}
