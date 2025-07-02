package ec.edu.espe.model;

import javax.swing.JOptionPane;

public class RegistroMensaje {

    // Método para validar los campos del formulario de mensaje
    public static boolean validarFormulario(String remitente, String destinatario, String asunto, 
                                             String cuerpo, String tipoMensaje, String observacion) {
        // Validación de campos vacíos
        if (remitente.isEmpty() || destinatario.isEmpty() || asunto.isEmpty()
                || cuerpo.isEmpty() || tipoMensaje.isEmpty() || observacion.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            return false;
        }

        return true; // Si todos los campos están completos
    }

// Método para guardar un mensaje
public static void guardarMensaje(String remitente, String destinatario, String asunto, String cuerpo, 
                                  String tipoMensaje, String observacion, String fechaEnvio) {
    // Validar los campos antes de guardar
    if (validarFormulario(remitente, destinatario, asunto, cuerpo, tipoMensaje, observacion)) {
        
        // Crear una nueva instancia de Mensaje con la fecha que ya recibes como String
        Mensaje mensaje = new Mensaje(0, remitente, destinatario, asunto, cuerpo, 
                                      fechaEnvio, tipoMensaje, observacion);

        // Guardar el mensaje utilizando la clase MensajeDAO
        MensajeDAO mensajeDAO = new MensajeDAO();
        boolean exito = mensajeDAO.guardarMensaje(mensaje);
        
        // Mostrar un mensaje de éxito o error
        if (exito) {
            JOptionPane.showMessageDialog(null, "Mensaje enviado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Hubo un error al enviar el mensaje.");
        }
    }
}


    public void setFields(Mensaje mensaje) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

