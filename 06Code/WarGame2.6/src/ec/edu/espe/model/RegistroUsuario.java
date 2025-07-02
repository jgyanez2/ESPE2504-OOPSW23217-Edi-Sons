
package ec.edu.espe.model;

import javax.swing.JOptionPane;

public class RegistroUsuario {

    public static boolean validarFormulario(String usuario, String password, String passwordConfirm,
            String tipoUsuario, String nombreCompleto, String ciPasport,
            String fNacimiento, String genero, String direccion,
            String telefono, String email) {
        // Validación de campos vacíos
        if (usuario.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty() || tipoUsuario == null
                || nombreCompleto.isEmpty() || ciPasport.isEmpty() || fNacimiento.isEmpty() || genero == null
                || direccion.isEmpty() || telefono.isEmpty() || email.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            return false;
        }

        // Validación: Contraseñas coincidentes
        if (!password.equals(passwordConfirm)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden. Por favor, verifica e ingresa nuevamente.");
            return false;
        }

        return true;
    }

    public static void guardarUsuario(String usuario, String password, String tipoUsuario, String nombreCompleto,
            String ciPasport, String fNacimiento, String genero, String direccion,
            String telefono, String email) {
        consultasUsuario con = new consultasUsuario();
        con.guardarUsuario(usuario, password, tipoUsuario, nombreCompleto, ciPasport, fNacimiento, genero, direccion, telefono, email);
    }

    public static void limpiarCampos(javax.swing.JTextField jTextField8, javax.swing.JPasswordField jPasswordField1,
            javax.swing.JPasswordField jPasswordField2, javax.swing.JComboBox<String> jComboBox1,
            javax.swing.JTextField jTextField1, javax.swing.JTextField jTextField2,
            com.toedter.calendar.JDateChooser jDateChooser1, javax.swing.JComboBox<String> jComboBox2,
            javax.swing.JTextField jTextField5, javax.swing.JTextField jTextField6,
            javax.swing.JTextField jTextField7) {
        jTextField8.setText("");
        jPasswordField1.setText("");
        jPasswordField2.setText("");
        jComboBox1.setSelectedIndex(0);
        jTextField1.setText("");
        jTextField2.setText("");
        jDateChooser1.setDate(null);
        jComboBox2.setSelectedIndex(0);
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
    }
}
