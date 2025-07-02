
package ec.edu.espe.model;

public class Usuario {

    private int id;  // Asumimos que el id es un entero
    private String usuario;
    private String password;
    private String tipoUsuario;
    private String nombreCompleto;
    private String ciPasport;
    private String fNacimiento;
    private String genero;
    private String direccion;
    private String telefono;
    private String email;

    // Constructor
    public Usuario(int id, String usuario, String password, String tipoUsuario, String nombreCompleto, String ciPasport,
            String fNacimiento, String genero, String direccion, String telefono, String email) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.nombreCompleto = nombreCompleto;
        this.ciPasport = ciPasport;
        this.fNacimiento = fNacimiento;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    // Método para mostrar información del usuario
    @Override
    public String toString() {
        return "Usuario: " + getUsuario() + "\n"
                + "Tipo: " + getTipoUsuario() + "\n"
                + "Nombre: " + getNombreCompleto() + "\n"
                + "Cédula/Pasaporte: " + getCiPasport() + "\n"
                + "Fecha Nacimiento: " + getfNacimiento() + "\n"
                + "Genero: " + getGenero() + "\n"
                + "Teléfono: " + getTelefono() + "\n"
                + "Email: " + getEmail();
    }

    //Vallidacion de cedula
    public static boolean validarCedula(String cedula) {
        
        // Si la cédula es "0", la consideramos válida
        if (cedula.equals("0")) {
            return true;
        }
        // Verifica que la cédula tenga exactamente 10 dígitos y que solo contenga números
        if (cedula.length() != 10 || !cedula.matches("\\d+")) {
            return false;  // Retorna falso si la longitud no es 10 o si contiene caracteres no numéricos
        }

        // Extrae los dos primeros dígitos que representan la provincia
        int provincia = Integer.parseInt(cedula.substring(0, 2));

        // Verifica que el código de provincia esté en el rango válido (1 a 24)
        if (provincia < 1 || provincia > 24) {
            return false;  // Si la provincia no es válida, la cédula es incorrecta
        }

        // Extrae el tercer dígito de la cédula
        int tercerDigito = Character.getNumericValue(cedula.charAt(2));

        // Verifica que el tercer dígito esté en el rango válido (0 a 6)
        // En Ecuador, los números del 6 al 9 están reservados para otras entidades
        if (tercerDigito < 0 || tercerDigito > 6) {
            return false;  // Si no está en el rango válido, la cédula no es correcta
        }

        int suma = 0;  // Variable para almacenar la suma del algoritmo de validación

        // Recorremos los primeros 9 dígitos para aplicar el algoritmo de validación
        for (int i = 0; i < 9; i++) {
            int num = Character.getNumericValue(cedula.charAt(i));  // Convertimos el carácter a número

            // Los dígitos en posiciones impares (0,2,4,6,8) se multiplican por 2
            if (i % 2 == 0) {
                num *= 2;

                // Si el resultado es mayor a 9, se resta 9
                if (num > 9) {
                    num -= 9;
                }
            }

            // Se acumula la suma
            suma += num;
        }

        // Se obtiene el dígito verificador (último dígito de la cédula)
        int digitoVerificador = (10 - (suma % 10)) % 10;

        // Compara el dígito verificador calculado con el último dígito de la cédula
        return digitoVerificador == Character.getNumericValue(cedula.charAt(9));
    }

    // Getters y setters 
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the tipoUsuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * @return the ciPasport
     */
    public String getCiPasport() {
        return ciPasport;
    }

    /**
     * @param ciPasport the ciPasport to set
     */
    public void setCiPasport(String ciPasport) {
        this.ciPasport = ciPasport;
    }

    /**
     * @return the fNacimiento
     */
    public String getfNacimiento() {
        return fNacimiento;
    }

    /**
     * @param fNacimiento the fNacimiento to set
     */
    public void setfNacimiento(String fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
