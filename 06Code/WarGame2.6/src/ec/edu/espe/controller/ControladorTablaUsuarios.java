
package ec.edu.espe.controller;

import ec.edu.espe.model.Usuario;
import ec.edu.espe.model.UsuarioDAO;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ControladorTablaUsuarios {

    private UsuarioDAO uDAO = new UsuarioDAO();
    private JTable tablaUsuarios;

    // Constructor que recibe el JTable donde se van a mostrar los datos
    public ControladorTablaUsuarios(JTable tablaUsuarios) {
        this.tablaUsuarios = tablaUsuarios;
    }

    // Método para cargar los datos en la tabla
    public void cargarDatos() {
        // Obtener los datos desde el DAO
        List<Usuario> listaUsuarios = uDAO.listar();

        // Definir el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");  
        modelo.addColumn("Usuario");
        modelo.addColumn("Contraseña");
        modelo.addColumn("Tipo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cédula/Pasaporte");
        modelo.addColumn("Fecha Nacimiento");
        modelo.addColumn("Genero");
        modelo.addColumn("Dirección");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Email");

        // Agregar los datos al modelo de la tabla
        for (Usuario usuario : listaUsuarios) {
            Object[] fila = new Object[11];
            fila[0] = usuario.getId();  // Asumiendo que tienes un método getId() en tu clase Usuario
            fila[1] = usuario.getUsuario();
            fila[2]= usuario.getPassword();
            fila[3] = usuario.getTipoUsuario();
            fila[4] = usuario.getNombreCompleto();
            fila[5] = usuario.getCiPasport();
            fila[6] = usuario.getfNacimiento();
            fila[7] = usuario.getGenero();
            fila[8] = usuario.getDireccion();
            fila[9] = usuario.getTelefono();
            fila[10] = usuario.getEmail();
            modelo.addRow(fila);
        }

        // Asignar el modelo a la tabla
        tablaUsuarios.setModel(modelo);
    }
}
