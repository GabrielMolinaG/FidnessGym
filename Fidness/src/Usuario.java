import javax.swing.JOptionPane;

// Nueva clase base para usuarios
public abstract class Usuario {
    protected String nombre;
    protected String usuario;
    protected String contraseña;

    public Usuario(String nombre, String usuario, String contraseña) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public abstract boolean iniciarSesion(String usuario, String contraseña);
}