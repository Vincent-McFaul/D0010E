// Axel Nordelöf & Vincent McFaul labb5 D0010E
package labb5.Button;

import java.awt.event.ActionEvent;
import labb5.State;
import labb5.Situation;

/**
 * En knapp som representerar en siffra i miniräknaren.
 * Används för att ange numeriska värden till displayen.
 * @author Axel Nordelof and Vincent McFaul
 */
@SuppressWarnings("serial")
public class DigitButton extends CalculatorButton {
    private String digit;

    /**
     * Skapar en ny DigitButton med angiven siffra och kopplad till given situation.
     *
     * @param digit        Siffran som knappen representerar.
     * @param situation    Situationen som knappen är kopplad till.
     */
    public DigitButton(String digit, Situation situation) {
        super(digit, situation);
        this.digit = digit;
        this.setText(digit);
    }

    /**
     * Hanterar övergångar baserat på nuvarande tillstånd vid knapptryckning.
     */
    @Override
    public void transition() {
        String calcText = String.valueOf(situation.getDisplayValue());

        switch (situation.currentState()) {
            case Input1:
            case Input2:
                if (calcText.equals("0")) {
                    situation.replaceDisplayValue(Integer.parseInt(digit));
                } else {
                    situation.replaceDisplayValue(Integer.parseInt(calcText + digit));
                }
                break;
            case HasResult:
                situation.replaceDisplayValue(0);
                situation.replaceDisplayValue(Integer.parseInt(digit));
                situation.newState(State.Input1);
                break;
            case OpReady:
                situation.replaceDisplayValue(Integer.parseInt(digit));
                if (situation.currentState() == State.OpReady) {
                    situation.newState(State.Input2);
                } else {
                    situation.newState(State.Input1);
                }
                break;
            default:
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
