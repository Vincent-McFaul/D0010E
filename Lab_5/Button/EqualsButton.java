// Axel Nordelöf & Vincent McFaul labb5 D0010E
package labb5.Button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import labb5.State;
import labb5.Situation;

/**
 * En knapp som representerar lika med (=) i miniräknaren.
 * Används för att slutföra en beräkning och visa resultatet.
 * @author Axel Nordelof and Vincent McFaul
 */
@SuppressWarnings("serial")
public class EqualsButton extends CalculatorButton {

    /**
     * Skapar en ny EqualsButton med angivet text och kopplar den till given situation.
     *
     * @param text      Texten som ska visas på knappen.
     * @param situation Situationen som knappen är kopplad till.
     */
    public EqualsButton(String text, Situation situation) {
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
            case OpReady:
                break;
            case Input2:
                calculateResult();
                situation.newState(State.HasResult);
                situation.getBinaryOperator().setColor(Color.GRAY);
                break;
            case HasResult:
                break;
            default:
                break;
        }
    }

    /**
     * Beräknar resultatet av den pågående beräkningen och uppdaterar displayen.
     */
    private void calculateResult() {
        int result = 0;
        int input1 = situation.getLeftOperand();
        int input2 = situation.getDisplayValue();
        BinOpButton binaryOperator = situation.getBinaryOperator();

        if (binaryOperator != null) {
            result = binaryOperator.calculate(input1, input2);
            situation.replaceDisplayValue(result);
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
