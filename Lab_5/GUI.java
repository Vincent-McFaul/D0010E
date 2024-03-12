package labb5;

import java.awt.*;
import javax.swing.*;

import labb5.Button.BinOpButton;
import labb5.Button.CancelButton;
import labb5.Button.DigitButton;
import labb5.Button.EqualsButton;
/**
 * GUI-klassen representerar det grafiska användargränssnittet för den digitala miniräknaren.
 * Den innehåller huvudpanelen, en display för siffror och resultat, samt knappar för användarinteraktion.
 * @author Axel Nordelof and Vincent McFaul
 */
@SuppressWarnings("serial")
public class GUI extends JFrame {
    public JPanel mainPanel;
    public JPanel keys;
    public JLabel display;
    
    public Situation situation;
    /**
     * Konstruktorn för GUI-klassen. Här skapas och konfigureras det grafiska användargränssnittet.
     */
    public GUI() {
 
    	mainPanel();
    	display();
    	visible();
    	keysPanel();

        addButton(new DigitButton("7", situation));
        addButton(new DigitButton("8", situation));
        addButton(new DigitButton("9", situation));
        addButton(new BinOpButton("/", situation, (Input1, Input2) -> Input1 / Input2));
        addButton(new DigitButton("4", situation));
        addButton(new DigitButton("5", situation));
        addButton(new DigitButton("6", situation));
        addButton(new BinOpButton("*", situation, (Input1, Input2) -> Input1 * Input2));
        addButton(new DigitButton("1", situation));
        addButton(new DigitButton("2", situation));
        addButton(new DigitButton("3", situation));
        addButton(new BinOpButton("-", situation, (Input1, Input2) -> Input1 - Input2));
        addButton(new DigitButton("0", situation));
        addButton(new EqualsButton("=", situation));
        addButton(new CancelButton("C", situation));
        addButton(new BinOpButton("+", situation, (Input1, Input2) -> Input1 + Input2));

        setSize(300, 400);
        setVisible(true);
        this.setDefaultCloseOperation(GUI.EXIT_ON_CLOSE);
    }
    
    public void mainPanel() {
    	mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(300, 400));
        mainPanel.setLayout(new GridBagLayout());
        setContentPane(mainPanel);
        System.out.println("Hello");
    } 
    

    public void display() {
    	display = new JLabel();
        display.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        display.setText("0");
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font(Font.SERIF, Font.PLAIN, 40));
    }
    

    public void visible() {
    	situation = new Situation(display);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridy = 0;
        constraints.gridx = 0;
        mainPanel.add(display, constraints);
    }
    

    public void keysPanel() {
    	keys = new JPanel();
        keys.setLayout(new GridLayout(4, 4));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 9;
        constraints.weightx = 1;
        constraints.gridy = 1;
        constraints.gridx = 0;
        mainPanel.add(keys, constraints);
    }
    
    
    /**
     * Lägger till en knapp till användargränssnittet.
     *
     * @param button Knappen som ska läggas till.
     */
    
    private void addButton(JButton button) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.insets = new Insets(10, 10, 10, 10);

        button.setBackground(Color.GRAY);
        button.setOpaque(true);
        button.setBorderPainted(true);
        button.setForeground(Color.BLACK);
        button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        button.setFocusPainted(false);

        keys.add(button, constraints);
    }


}
