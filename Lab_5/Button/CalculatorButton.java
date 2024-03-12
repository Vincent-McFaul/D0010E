// Axel Nordelöf & Vincent McFaul labb5 D0010E
package labb5.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import labb5.Situation;

/**
 * Abstrakt klass som representerar en knapp i miniräknaren.
 * Innehåller gemensam funktionalitet och metoder för alla typer av knappar.
 * @author Axel Nordelof and Vincent McFaul
 */
public abstract class CalculatorButton extends JButton implements ActionListener {
    private static final long serialVersionUID = 1L;

    protected Situation situation;

    /**
     * Skapar en ny CalculatorButton med angiven text och kopplad till given situation.
     *
     * @param text       Texten som ska visas på knappen.
     * @param situation  Situationen som knappen är kopplad till.
     */
    public CalculatorButton(String text, Situation situation) {
        super(text);
        this.situation = situation;

        addActionListener(this);
    }

    /**
     * Abstrakt metod som hanterar övergångar baserat på knappens funktion.
     */
    public abstract void transition();

    /**
     * Ändrar knappens bakgrundsfärg.
     *
     * @param color Färgen som ska sättas som bakgrund.
     */
    protected void setColor(Color color) {
        setBackground(color);
    }

    /**
     * Återställer displayens text till "0".
     */
    protected void resetDisplay() {
        situation.getDisplay().setText("0");
    }

    /**
     * Returnerar texten på knappen som en sträng.
     *
     * @return Texten på knappen.
     */
    @Override
    public String toString() {
        return getText();
    }

    /**
     * Abstrakt metod som hanterar händelsen när knappen klickas på.
     *
     * @param e ActionEvent-objektet som genereras vid knapptryckning.
     */
    public abstract void actionPerformed(ActionEvent e);

}
