
package labb5.Button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.function.IntBinaryOperator;
import labb5.State;
import labb5.Situation;

/**
 * En knapp som representerar en binär operator (+, -, *, /) i miniräknaren.
 * Används för att välja och utföra binära operationer på numeriska värden.
 * @author Axel Nordelof and Vincent McFaul
 */
@SuppressWarnings("serial")
public class BinOpButton extends CalculatorButton {

    private IntBinaryOperator binaryOp;

    /**
     * Skapar en ny BinOpButton med angiven text, kopplad till given situation, och
     * associerad med en given binär operator.
     *
     * @param text         Texten som ska visas på knappen.
     * @param situation    Situationen som knappen är kopplad till.
     * @param intOperator  Binär operator som knappen representerar.
     */
    public BinOpButton(String text, Situation situation, IntBinaryOperator intOperator) {
        super(text, situation);
        this.setText(text);
        this.binaryOp = intOperator;
        //this.situation = situation;
    }

    /**
     * Utför den binära operationen på två givna heltal.
     *
     * @param firstNum   Det första heltalet.
     * @param secondNum  Det andra heltalet.
     * @return Resultatet av den binära operationen.
     */
    public int calculate(int firstNum, int secondNum) {
        return binaryOp.applyAsInt(firstNum, secondNum);
    }

    /**
     * Hanterar övergångar baserat på nuvarande tillstånd vid knapptryckning.
     */
    @Override
    public void transition() {
        switch (situation.currentState()) {
            case Input1:
                String calctext = String.valueOf(situation.getDisplayValue());
                if (!calctext.isEmpty()) {
                    situation.replaceLeftOperand(situation.getDisplayValue());
                    situation.newState(State.OpReady);
                    super.setColor(Color.RED);
                    situation.replaceBinaryOperator(this);
                }
                break;
            case OpReady:
                if (situation.getBinaryOperator() != null) {
                    situation.getBinaryOperator().setColor(Color.GRAY);
                }
                situation.replaceBinaryOperator(this);
                situation.newState(State.OpReady);
                situation.replaceLeftOperand(situation.getDisplayValue());
                super.setColor(Color.RED);
                break;
            case HasResult:
                String hrcalctext = String.valueOf(situation.getDisplayValue());
                if (!hrcalctext.isEmpty()) {
                    situation.replaceLeftOperand(situation.getDisplayValue());
                    situation.newState(State.OpReady);
                    situation.replaceBinaryOperator(this);
                }
                break;
            case Input2:
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
