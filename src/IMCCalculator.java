import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IMCCalculator extends JFrame implements ActionListener {

    private JTextField weightField, heightField;
    private JButton calculateButton;
    private JLabel resultLabel, bmiLabel;
    private JPanel imagePanel;

    public IMCCalculator() {
        setTitle("Calculadora de IMC");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1));

        weightField = new JTextField(10);
        heightField = new JTextField(10);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Peso (kg):"));
        inputPanel.add(weightField);
        inputPanel.add(new JLabel("Altura (m):"));
        inputPanel.add(heightField);

        calculateButton = new JButton("Calcular");
        calculateButton.addActionListener(this);

        resultLabel = new JLabel("IMC: ");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        bmiLabel = new JLabel();
        bmiLabel.setHorizontalAlignment(SwingConstants.CENTER);

        imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout());
        imagePanel.add(new JLabel(new ImageIcon("abaixodopeso.png")));

        mainPanel.add(inputPanel);
        mainPanel.add(calculateButton);
        mainPanel.add(resultLabel);
        mainPanel.add(bmiLabel);
        mainPanel.add(imagePanel);

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            double bmi = weight / (height * height);

            resultLabel.setText("IMC: " + String.format("%.2f", bmi));

            if (bmi < 18.5) {
                bmiLabel.setText("Abaixo do peso");
                imagePanel.removeAll();
                imagePanel.add(new JLabel(new ImageIcon("C:\\Users\\hkspe\\OneDrive\\Área de Trabalho\\IMC\\out\\production\\IMC\\abaixodopeso.png")));
            } else if (bmi >= 18.5 && bmi < 25) {
                bmiLabel.setText("Peso normal");
                imagePanel.removeAll();
                imagePanel.add(new JLabel(new ImageIcon("C:\\Users\\hkspe\\OneDrive\\Área de Trabalho\\IMC\\out\\production\\IMC\\pesonormal.png")));
            } else if (bmi >= 25 && bmi < 30) {
                bmiLabel.setText("Sobrepeso");
                imagePanel.removeAll();
                imagePanel.add(new JLabel(new ImageIcon("C:\\Users\\hkspe\\OneDrive\\Área de Trabalho\\IMC\\out\\production\\IMC\\excessodepeso.png")));
            } else {
                bmiLabel.setText("Obesidade");
                imagePanel.removeAll();
                imagePanel.add(new JLabel(new ImageIcon("C:\\Users\\hkspe\\OneDrive\\Área de Trabalho\\IMC\\out\\production\\IMC\\obeso.png")));
            }

            imagePanel.revalidate();
            imagePanel.repaint();
        } catch (NumberFormatException ex) {
            resultLabel.setText("Por favor, insira valores numéricos válidos.");
            bmiLabel.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IMCCalculator calculator = new IMCCalculator();
            calculator.setVisible(true);
        });
    }
}
