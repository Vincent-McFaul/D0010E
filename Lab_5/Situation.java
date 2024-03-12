// Axel Nordelöf & Vincent McFaul labb5 D0010E
package labb5;

import labb5.Button.BinOpButton;
import javax.swing.JLabel;

/**
 * Representerar den aktuella situationen i miniräknaren.
 * Håller koll på det aktuella tillståndet, displayen, den binära operatorn och vänster operand.
 * @author Axel Nordelof and Vincent McFaul
 */
public class Situation {
    private State state = State.Input1;
    private JLabel display;
    private BinOpButton binaryOperator;
    private int leftOperand;

    /**
     * Skapar en ny Situation med angiven JLabel som display.
     *
     * @param display JLabel som används som display i miniräknaren.
     */
    public Situation(JLabel display) {
        this.display = display;
    }

    /**
     * Hämtar det aktuella tillståndet.
     *
     * @return Det aktuella tillståndet.
     */
    public State getState() {
        return state;
    }

    /**
     * Sätter det nya tillståndet.
     *
     * @param newState Det nya tillståndet.
     */
    public void setState(State newState) {
        this.state = newState;
    }

    /**
     * Hämtar JLabel-displayen.
     *
     * @return JLabel-displayen.
     */
    public JLabel getDisplay() {
        return display;
    }

    /**
     * Hämtar den binära operatorn.
     *
     * @return Den binära operatorn.
     */
    public BinOpButton getBinaryOperator() {
        return binaryOperator;
    }

    /**
     * Sätter den binära operatorn.
     *
     * @param binaryOperator Den nya binära operatorn.
     */
    public void setBinaryOperator(BinOpButton binaryOperator) {
        this.binaryOperator = binaryOperator;
    }

    /**
     * Hämtar vänster operand.
     *
     * @return Vänster operand.
     */
    public int getLeftOperand() {
        return leftOperand;
    }

    /**
     * Sätter den nya vänstra operanden.
     *
     * @param leftOperand Den nya vänstra operanden.
     */
    public void setLeftOperand(int leftOperand) {
        this.leftOperand = leftOperand;
    }

    /**
     * Hämtar det aktuella tillståndet.
     *
     * @return Det aktuella tillståndet.
     */
    public State currentState() {
        return this.state;
    }

    /**
     * Sätter det nya tillståndet.
     *
     * @param nextState Det nya tillståndet.
     */
    public void newState(State nextState) {
        this.state = nextState;
    }

    /**
     * Ersätter den binära operatorn och returnerar den gamla.
     *
     * @return Den gamla binära operatorn.
     */
    public BinOpButton replaceBinaryOperator() {
        return this.binaryOperator;
    }

    /**
     * Ersätter den binära operatorn med en ny.
     *
     * @param newOperator Den nya binära operatorn.
     */
    public void replaceBinaryOperator(BinOpButton newOperator) {
        this.binaryOperator = newOperator;
    }

    /**
     * Ersätter den vänstra operanden och returnerar den gamla.
     *
     * @return Den gamla vänstra operanden.
     */
    public int replaceLeftOperand() {
        return this.leftOperand;
    }

    /**
     * Ersätter den vänstra operanden med en ny.
     *
     * @param newLeftOperand Den nya vänstra operanden.
     */
    public void replaceLeftOperand(int newLeftOperand) {
        this.leftOperand = newLeftOperand;
    }

    /**
     * Hämtar värdet från displayen som en heltalsrepresentation.
     *
     * @return Heltalsvärdet från displayen.
     */
    public int getDisplayValue() {
        return Integer.parseInt(display.getText());
    }

    /**
     * Ersätter värdet på displayen med det angivna heltalsvärdet.
     *
     * @param valueOf Det nya heltalsvärdet för displayen.
     */
    public void replaceDisplayValue(int valueOf) {
        String stringValueOf = Integer.toString(valueOf);
        this.display.setText(stringValueOf);
    }
}
