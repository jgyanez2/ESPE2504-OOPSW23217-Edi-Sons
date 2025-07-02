
package ec.edu.espe.model;

import ConexionMySQL.conexionMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    conexionMysql conectar = new conexionMysql();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Método para listar usuarios desde la base de datos
    public List<Usuario> listar() {
        List<Usuario> datos = new ArrayList<>();
        String sql = "SELECT * FROM login";  

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            // Recorriendo el ResultSet y creando objetos Usuario
            while (rs.next()) {
                // Obtener el ID de la base de datos (asumimos que se llama "id")
                int id = rs.getInt("idLogin");
                String usuario = rs.getString("usuario");
                String password = rs.getString("password");
                String tipoUsuario = rs.getString("tipoUsuario");
                String nombreCompleto = rs.getString("nombreCompleto");
                String ciPasport = rs.getString("ciPasport");
                String fNacimiento = rs.getString("fNacimiento");
                String genero = rs.getString("genero");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");

                // Crear un objeto Usuario con el ID y los demás datos obtenidos de la base de datos
                Usuario u = new Usuario(id, usuario, password, tipoUsuario, nombreCompleto, ciPasport, fNacimiento, genero, direccion, telefono, email);

                // Agregar el objeto Usuario a la lista
                datos.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Imprimir cualquier error de SQL
        } finally {
            try {
                // Cerrar conexiones y liberar recursos
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return datos;  // Retornar la lista de usuarios
    }

    // Método para modificar un usuario en la base de datos
    public boolean modificarUsuario(Usuario usuario) {
        String sql = "UPDATE login SET usuario=?, password=?, tipoUsuario=?, nombreCompleto=?, ciPasport=?, fNacimiento=?, genero=?, direccion=?, telefono=?, email=? WHERE idLogin=?";

        try (Connection con = conectar.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getTipoUsuario());
            ps.setString(4, usuario.getNombreCompleto());
            ps.setString(5, usuario.getCiPasport());
            ps.setString(6, usuario.getfNacimiento());
            ps.setString(7, usuario.getGenero());
            ps.setString(8, usuario.getDireccion());
            ps.setString(9, usuario.getTelefono());
            ps.setString(10, usuario.getEmail());
            ps.setInt(11, usuario.getId());

            return ps.executeUpdate() > 0; // Si se actualizó, retorna true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para guardar un nuevo usuario en la base de datos
    public boolean guardarUsuario(Usuario usuario) {
        String sql = "INSERT INTO login (usuario, password, tipoUsuario, nombreCompleto, ciPasport, fNacimiento, genero, direccion, telefono, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conectar.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getTipoUsuario());
            ps.setString(4, usuario.getNombreCompleto());
            ps.setString(5, usuario.getCiPasport());
            ps.setString(6, usuario.getfNacimiento());
            ps.setString(7, usuario.getGenero());
            ps.setString(8, usuario.getDireccion());
            ps.setString(9, usuario.getTelefono());
            ps.setString(10, usuario.getEmail());

            return ps.executeUpdate() > 0;  // Si se insertó correctamente, retorna true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Retorna false en caso de error
        }
    }

// Método para eliminar un usuario de la base de datos
    public boolean eliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM login WHERE idLogin = ?";

        try (Connection con = conectar.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);  // Establecemos el ID del usuario que vamos a eliminar
            return ps.executeUpdate() > 0;  // Si la ejecución fue exitosa, retorna true
        } catch (SQLException e) {
            e.printStackTrace();  // Si ocurre un error, lo imprimimos en la consola
            return false;  // Si hubo un error, retorna false
        }
    }

    // Método para buscar un usuario por CI/Pasaporte
    public List<Usuario> buscarPorCiPasport(String ciPasport) {
        String sql = "SELECT idLogin, usuario, password, tipoUsuario, nombreCompleto, ciPasport, fNacimiento, genero, direccion, telefono, email FROM login WHERE ciPasport = ?";
        List<Usuario> usuariosEncontrados = new ArrayList<>();

        try (Connection con = conectar.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ciPasport);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {  // Usamos while para manejar varios resultados
                Usuario usuario = new Usuario(
                        rs.getInt("idLogin"),
                        rs.getString("usuario"),
                        rs.getString("password"),
                        rs.getString("tipoUsuario"),
                        rs.getString("nombreCompleto"),
                        rs.getString("ciPasport"),
                        rs.getString("fNacimiento"),
                        rs.getString("genero"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
                usuariosEncontrados.add(usuario);  // Agregamos el usuario a la lista
            }

            if (usuariosEncontrados.isEmpty()) {
                System.out.println("No se encontró un usuario con CI/Pasaporte: " + ciPasport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en la búsqueda por CI/Pasaporte: " + e.getMessage());
        }

        return usuariosEncontrados;  // Retorna la lista de usuarios encontrados
    }
}
