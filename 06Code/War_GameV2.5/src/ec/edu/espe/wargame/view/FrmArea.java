package ec.edu.espe.wargame.view;

import ec.espe.edu.wargame.model.Area;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FrmArea extends javax.swing.JFrame {

    // Variables declaration
    private JComboBox<Area.areaUnit> cbFromUnit;
    private JComboBox<Area.areaUnit> cbToUnit;
    private JTextField txtValue;
    private JLabel lblResult;
    private JButton btnConvert;
    private JButton btnClear;
    // End of variables declaration

    public FrmArea() {
        initComponents();
        setTitle("Area Unit Converter");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Layout setup
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Components
        JLabel lblTitle = new JLabel("Area Unit Converter");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel lblValue = new JLabel("Value:");
        txtValue = new JTextField(10);
        
        JLabel lblFrom = new JLabel("From Unit:");
        cbFromUnit = new JComboBox<>(Area.areaUnit.values());
        
        JLabel lblTo = new JLabel("To Unit:");
        cbToUnit = new JComboBox<>(Area.areaUnit.values());
        
        btnConvert = new JButton("Convert");
        btnClear = new JButton("Clear");
        
        lblResult = new JLabel("Result will appear here");
        lblResult.setBorder(BorderFactory.createEtchedBorder());

        // Button actions
        btnConvert.addActionListener(this::convertAction);
        btnClear.addActionListener(e -> {
            txtValue.setText("");
            lblResult.setText("Result will appear here");
        });

        // Horizontal layout
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblTitle)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblValue)
                        .addComponent(lblFrom)
                        .addComponent(lblTo))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtValue)
                        .addComponent(cbFromUnit)
                        .addComponent(cbToUnit)))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnConvert)
                    .addComponent(btnClear))
                .addComponent(lblResult, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
        );

        // Vertical layout
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(lblTitle)
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValue)
                    .addComponent(txtValue))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFrom)
                    .addComponent(cbFromUnit))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTo)
                    .addComponent(cbToUnit))
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConvert)
                    .addComponent(btnClear))
                .addGap(20)
                .addComponent(lblResult, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        );

        // Add main panel to frame
        this.add(mainPanel);
    }

    private void convertAction(ActionEvent evt) {
        try {
            double value = Double.parseDouble(txtValue.getText());
            Area.areaUnit fromUnit = (Area.areaUnit) cbFromUnit.getSelectedItem();
            Area.areaUnit toUnit = (Area.areaUnit) cbToUnit.getSelectedItem();

            double result = Area.convert(value, fromUnit, toUnit);
            lblResult.setText(String.format("%.4f %s = %.4f %s", 
                value, fromUnit, result, toUnit));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid number", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmArea frame = new FrmArea();
            frame.setVisible(true);
        });
    }
}