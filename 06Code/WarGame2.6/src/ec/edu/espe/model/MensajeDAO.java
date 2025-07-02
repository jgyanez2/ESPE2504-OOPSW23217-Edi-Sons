package ec.edu.espe.model;

import java.util.ArrayList;
import java.util.List;

public class MensajeDAO {
    private final ConexionMySQL.conexionMysql conectar = new ConexionMySQL.conexionMysql();
    private String ultimoError = "";

    // Método para listar los mensajes desde la base de datos
    public List<Mensaje> listar() {
        List<Mensaje> datos = new ArrayList<>();
        String sql = "SELECT * FROM mensajes";

        try (Connection con = conectar.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Mensaje m = new Mensaje(
                        rs.getInt("idmensaje"),  
                        rs.getString("remitente"),
                        rs.getString("destinatario"),
                        rs.getString("asunto"),
                        rs.getString("cuerpo"),
                        rs.getString("fechaEnvio"), // Cambié getObject a getString
                        rs.getString("tipoMensaje"),
                        rs.getString("observacion")
                );
                datos.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ultimoError = e.getMessage();
        }
        return datos;
    }

    // Método para modificar un mensaje en la base de datos
    public boolean modificarMensaje(Mensaje mensaje) {
        String sql = "UPDATE mensajes SET remitente=?, destinatario=?, asunto=?, cuerpo=?, fechaEnvio=?, tipoMensaje=?, observacion=? WHERE idmensaje=?";

        try (Connection con = conectar.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mensaje.getRemitente());
            ps.setString(2, mensaje.getDestinatario());
            ps.setString(3, mensaje.getAsunto());
            ps.setString(4, mensaje.getCuerpo());
            ps.setString(5, mensaje.getFechaEnvio());  // Cambié a setString
            ps.setString(6, mensaje.getTipoMensaje());
            ps.setString(7, mensaje.getObservacion());
            ps.setInt(8, mensaje.getIdMensaje());  

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            ultimoError = e.getMessage();
            return false;
        }
    }

    // Método para guardar un nuevo mensaje
    public boolean guardarMensaje(Mensaje mensaje) {
        String sql = "INSERT INTO mensajes (remitente, destinatario, asunto, cuerpo, fechaEnvio, tipoMensaje, observacion) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conectar.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mensaje.getRemitente());
            ps.setString(2, mensaje.getDestinatario());
            ps.setString(3, mensaje.getAsunto());
            ps.setString(4, mensaje.getCuerpo());
            ps.setString(5, mensaje.getFechaEnvio());  // setString para fecha
            ps.setString(6, mensaje.getTipoMensaje());
            ps.setString(7, mensaje.getObservacion());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            ultimoError = e.getMessage();
            return false;
        }
    }

    // Método para eliminar un mensaje
    public boolean eliminarMensaje(int idMensaje) {
        String sql = "DELETE FROM mensajes WHERE idmensaje = ?";

        try (Connection con = conectar.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idMensaje);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            ultimoError = e.getMessage();
            return false;
        }
    }

    // Método para buscar un mensaje por idMensaje
    public Mensaje buscarPorIdMensaje(int idMensaje) {
        String sql = "SELECT * FROM mensajes WHERE idmensaje = ?";
        Mensaje mensajeEncontrado = null;

        try (Connection con = conectar.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idMensaje);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mensajeEncontrado = new Mensaje(
                            rs.getInt("idmensaje"),
                            rs.getString("remitente"),
                            rs.getString("destinatario"),
                            rs.getString("asunto"),
                            rs.getString("cuerpo"),
                            rs.getString("fechaEnvio"), // Cambié a getString
                            rs.getString("tipoMensaje"),
                            rs.getString("observacion")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ultimoError = e.getMessage();
        }
        return mensajeEncontrado;
    }

    // Método para obtener el último error
    public String obtenerUltimoError() {
        return ultimoError;
    }
}

