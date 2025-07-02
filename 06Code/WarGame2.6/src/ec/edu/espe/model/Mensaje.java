package ec.edu.espe.model;

import java.time.LocalDateTime;

public class Mensaje {

    private int idMensaje;
    private String remitente;
    private String destinatario;
    private String asunto;
    private String cuerpo;
    private String fechaEnvio;
    private String tipoMensaje;
    private String observacion;

    // Constructor
    public Mensaje(int idMensaje, String remitente, String destinatario, String asunto, String cuerpo, String fechaEnvio, String tipoMensaje, String observacion1) {
        this.idMensaje = idMensaje;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.fechaEnvio = fechaEnvio;
        this.tipoMensaje = tipoMensaje;
        this.observacion = observacion;
    }

    public Mensaje(String remitente, String destinatario, String asunto, String cuerpo, String tipoMensaje, String observacion, LocalDateTime fechaEnvio) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.tipoMensaje = tipoMensaje;
        this.observacion = observacion;
        this.fechaEnvio = fechaEnvio.toString();
    }

    public Mensaje(int idMensaje, String remitente, String destinatario, String asunto, String cuerpo, LocalDateTime fechaEnvio, String tipoMensaje, String observacion) {
        this.idMensaje = idMensaje;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.fechaEnvio = fechaEnvio.toString();
        this.tipoMensaje = tipoMensaje;
        this.observacion = observacion;
    }

    // Getters y Setters
    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
