// Axel Nordelöf & Vincent McFaul labb5 D0010E
package labb5;

import labb5.Button.BinOpButton;

/**
 * Representerar de olika tillstånden i miniräknaren.
 * Varje tillstånd håller också information om den vänstra operanden och den binära operatorn.
 * @author Axel Nordelof and Vincent McFaul
 */
public enum State {
    Input1,
    OpReady,
    Input2,
    HasResult;

    private int leftOperand;
    private BinOpButton binaryOperator;

    /**
     * Sätter den vänstra operanden för det aktuella tillståndet.
     *
     * @param operand Den vänstra operanden.
     */
    public void setLeftOperand(int operand) {
        this.leftOperand = operand;
    }

    /**
     * Hämtar den vänstra operanden för det aktuella tillståndet.
     *
     * @return Den vänstra operanden.
     */
    public int getLeftOperand() {
        return leftOperand;
    }

    /**
     * Sätter den binära operatorn för det aktuella tillståndet.
     *
     * @param operator Den binära operatorn.
     */
    public void setBinaryOperator(BinOpButton operator) {
        this.binaryOperator = operator;
    }

    /**
     * Hämtar den binära operatorn för det aktuella tillståndet.
     *
     * @return Den binära operatorn.
     */
    public BinOpButton getBinaryOperator() {
        return binaryOperator;
    }

    /**
     * Hanterar övergången mellan olika tillstånd.
     *
     * @return Det nya tillståndet efter övergången.
     */
    public State handleTransition() {
        switch (this) {
            case Input1:
            case HasResult:
                return State.OpReady;

            case OpReady:
                return State.Input2;

            default:
                return this;
        }
    }
}
