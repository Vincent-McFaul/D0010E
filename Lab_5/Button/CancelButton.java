// Axel Nordelöf & Vincent McFaul labb5 D0010E
package labb5.Button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import labb5.State;
import labb5.Situation;

/**
 * En knapp som representerar "Cancel" - knappen i miniräknaren.
 * Används för att återställa tillståndet och nollställa värdena.
 * @author Axel Nordelof and Vincent McFaul
 */
public class CancelButton extends CalculatorButton {

    private static final long serialVersionUID = 1L;

    /**
     * Skapar en ny CancelButton med angiven text och kopplad till given situation.
     *
     * @param text         Texten som ska visas på knappen.
     * @param situation    Situationen som knappen är kopplad till.
     */
    public CancelButton(String text, Situation situation) {
        super(text, situation);
        this.setText(text);
    }

    /**
     * Hanterar övergångar baserat på nuvarande tillstånd vid knapptryckning.
     */
    @Override
    public void transition() {
        switch (situation.currentState()) {
            case Input1:
                situation.replaceDisplayValue(0);
                situation.replaceLeftOperand(0);
                situation.replaceBinaryOperator(null);
                break;
            case OpReady:
                situation.replaceDisplayValue(0);
                situation.replaceLeftOperand(0);
                situation.getBinaryOperator().setColor(Color.GRAY);
                situation.replaceBinaryOperator(null);
                situation.newState(State.Input1);
                break;
            case Input2:
                situation.replaceDisplayValue(0);
                situation.replaceLeftOperand(0);
                situation.getBinaryOperator().setColor(Color.GRAY);
                situation.replaceBinaryOperator(null);
                situation.newState(State.Input1);
                break;
            case HasResult:
                situation.replaceDisplayValue(0);
                situation.replaceLeftOperand(0);
                situation.getBinaryOperator().setColor(Color.GRAY);
                situation.replaceBinaryOperator(null);
                situation.newState(State.Input1);
                break;
        }
    }

    /**
     * Hanterar händelsen när knappen klickas på.
     *
     * @param e ActionEvent-objektet som genereras vid knapptryckning.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        transition();
    }
}
