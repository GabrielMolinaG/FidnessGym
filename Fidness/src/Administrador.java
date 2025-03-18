import javax.swing.JOptionPane;

class Administrador extends Usuario {
    private static final String ADMIN_PASSWORD = "Fidness";

    public Administrador() {
        super("Admin", "admin", ADMIN_PASSWORD);
    }

    @Override
    public boolean iniciarSesion(String usuario, String contraseña) {
        return this.usuario.equals(usuario) && this.contraseña.equals(contraseña);
    }

    public static void menuAdministrador() {
        Administrador admin = new Administrador();
        String pass = JOptionPane.showInputDialog("Ingrese la contraseña de administrador:");
        
        if (pass == null || !admin.iniciarSesion("admin", pass)) {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
            return;
        }

        while (true) {
            String[] opciones = {"Agregar Ejercicio", "Editar Ejercicio", "Ver Clientes", "Editar Clientes", "Eliminar Cliente", "Ver Ejercicios", "Salir"};
            int eleccion = JOptionPane.showOptionDialog(
                    null, "Seleccione una opción:", "Administrador",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);

            switch (eleccion) {
                case 0:
                    BaseDeDatos.agregarEjercicio();
                    break;
                case 1:
                    BaseDeDatos.editarEjercicio();
                    break;
                case 2:
                    BaseDeDatos.verClientes();
                    break;
                case 3:
                    BaseDeDatos.editarCliente();
                    break;
                case 4:
                    BaseDeDatos.eliminarCliente();
                    break;
                case 5:
                    BaseDeDatos.verEjerciciosAdmin();
                    break;
                case 6:
                    return;
            }
        }
    }
}
