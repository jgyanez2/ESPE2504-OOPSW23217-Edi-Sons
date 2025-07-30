package ec.edu.espe.wargame.controller;

import java.awt.Component;
import javax.swing.*;

public class FrmController {

    /**
     * Clears the content of multiple JTextFields or JPasswordFields
     * @param components
     */
    public static void clearFields(JComponent... components) {
        for (JComponent comp : components) {
            if (comp instanceof JTextField textField) {
                textField.setText("");
            } else if (comp instanceof JPasswordField passwordField) {
                passwordField.setText("");
            }
        }
    }

    /**
     * Simple message popup
     * @param message
     * @param parent
     */
    public static void showMessage(String message, Component parent) {
        JOptionPane.showMessageDialog(parent, message);
    }

    /**
     * Confirmation dialog
     * @param message
     * @param parent
     * @return 
     */
    public static boolean confirm(String message, Component parent) {
        int result = JOptionPane.showConfirmDialog(parent, message, "Confirm", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }
    public static void switchFrames(JFrame from, JFrame to) {
    from.dispose();
    to.setVisible(true);
}
    
}
