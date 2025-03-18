public class ClienteModel extends Usuario {
    public ClienteModel(String nombre, String usuario, String contraseña) {
        super(nombre, usuario, contraseña);
    }

    @Override
    public boolean iniciarSesion(String usuario, String contraseña) {
        return this.usuario.equals(usuario) && this.contraseña.equals(contraseña);
    }

    @Override
    public String toString() {
        return "Cliente: " + nombre + "\nUsuario: " + usuario;
    }
}