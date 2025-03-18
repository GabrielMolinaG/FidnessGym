import javax.swing.JOptionPane;

public class Cliente {
    public static void menuCliente() {
        while (true) {
            String[] opciones = {"Iniciar Sesión", "Registrarse", "Salir"};
            int eleccion = JOptionPane.showOptionDialog(
                    null, "Seleccione una opción:", "Clientes",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);

            switch (eleccion) {
                case 0:
                    if (BaseDeDatos.iniciarSesionCliente()) { 
                        menuOpcionesCliente();
                    }
                    break;
                case 1:
                    BaseDeDatos.registrarCliente();
                    break;
                case 2:
                    return;
            }
        }
    }

    private static void menuOpcionesCliente() {
        while (true) {
            String[] opciones = {"Consultar Ejercicios", "Crear Rutina", "Salir"};
            int eleccion = JOptionPane.showOptionDialog(
                    null, "Seleccione una opción:", "Cliente",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);

            switch (eleccion) {
                case 0:
                    BaseDeDatos.consultarEjercicios();
                    break;
                case 1:
                    BaseDeDatos.crearRutina();
                    break;
                case 2:
                    return;
            }
        }
    }
}
