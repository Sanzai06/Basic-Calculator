import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModernCalculator implements ActionListener {
    JFrame frame;
    JTextField textField;
    JPanel buttonPanel;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[8]; // +, -, *, /, =, C, ., ←
    String[] functions = {"+", "-", "*", "/", "=", "C", ".", "←"};
    double num1, num2, result;
    char operator;

    public ModernCalculator() {
        frame = new JFrame("Modern Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 550);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 32));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.GREEN);
        frame.add(textField, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 8, 8));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(Color.DARK_GRAY);

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 24));
            numberButtons[i].setBackground(Color.LIGHT_GRAY);
            numberButtons[i].addActionListener(this);
        }

        for (int i = 0; i < 8; i++) {
            functionButtons[i] = new JButton(functions[i]);
            functionButtons[i].setFont(new Font("Arial", Font.BOLD, 24));
            functionButtons[i].setBackground(Color.ORANGE);
            functionButtons[i].addActionListener(this);
        }

        // Add buttons in order
        int[] order = {7,8,9,3,4,5,6,2,1,2,3,1}; // custom order
        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(functionButtons[3]); // /

        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        buttonPanel.add(functionButtons[2]); // *

        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(functionButtons[1]); // -

        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(functionButtons[6]); // .
        buttonPanel.add(functionButtons[4]); // =
        buttonPanel.add(functionButtons[0]); // +

        buttonPanel.add(functionButtons[5]); // C
        buttonPanel.add(functionButtons[7]); // ←
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++)
            if (e.getSource() == numberButtons[i])
                textField.setText(textField.getText() + i);

        for (int i = 0; i < 4; i++)
            if (e.getSource() == functionButtons[i] && !textField.getText().isEmpty()) {
                num1 = Double.parseDouble(textField.getText());
                operator = functions[i].charAt(0);
                textField.setText("");
            }

        if (e.getSource() == functionButtons[4] && !textField.getText().isEmpty()) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': result = num1 / num2; break;
            }
            textField.setText(String.valueOf(result));
            num1 = result;
        }

        if (e.getSource() == functionButtons[5]) textField.setText(""); // C
        if (e.getSource() == functionButtons[6] && !textField.getText().contains(".")) textField.setText(textField.getText() + ".");
        if (e.getSource() == functionButtons[7] && !textField.getText().isEmpty()) textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
    }

    public static void main(String[] args) {
        new ModernCalculator();
    }
}
