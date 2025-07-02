
package ec.edu.espe.controller;

import ec.edu.espe.model.Mensaje;
import ec.edu.espe.model.MensajeDAO;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ControladorTablaMensajes {

    private MensajeDAO mDAO = new MensajeDAO();
    private JTable tablaMensajes;

    // Constructor que recibe el JTable donde se van a mostrar los datos
    public ControladorTablaMensajes(JTable tablaMensajes) {
        this.tablaMensajes = tablaMensajes;
    }

    // Método para cargar los datos en la tabla
    public void cargarDatos() {
        // Obtener los datos desde el DAO
        List<Mensaje> listaMensajes = mDAO.listar();

        // Definir el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Remitente");
        modelo.addColumn("Destinatario");
        modelo.addColumn("Asunto");
        modelo.addColumn("Cuerpo");
        modelo.addColumn("Fecha Envio");
        modelo.addColumn("Tipo Mensaje");
        modelo.addColumn("Observación");

        // Agregar los datos al modelo de la tabla
        for (Mensaje mensaje : listaMensajes) {
            Object[] fila = new Object[8];
            fila[0] = mensaje.getIdMensaje();  // ID
            fila[1] = mensaje.getRemitente();  // Remitente
            fila[2] = mensaje.getDestinatario(); // Destinatario
            fila[3] = mensaje.getAsunto();  // Asunto
            fila[4] = mensaje.getCuerpo();  // Cuerpo
            fila[5] = mensaje.getFechaEnvio();  // Fecha de Envio
            fila[6] = mensaje.getTipoMensaje();  // Tipo de Mensaje
            fila[7] = mensaje.getObservacion();  // Observación
            modelo.addRow(fila);
        }

        // Asignar el modelo a la tabla
        tablaMensajes.setModel(modelo);
    }
}
