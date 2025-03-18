import javax.swing.*;
import java.awt.*;

public class InterfazAdministrador extends JFrame {

    public InterfazAdministrador() {
        setTitle("Menú Administrador");
        setSize(420, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240));

        JLabel titulo = new JLabel("Menú Administrador", SwingConstants.CENTER);
        titulo.setFont(new Font("Sans-serif", Font.BOLD, 20));
        titulo.setForeground(new Color(0, 102, 204));

        JPanel botonesPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        botonesPanel.setBackground(new Color(240, 240, 240));

        JButton btnAgregarEjercicio = crearBoton("Agregar Ejercicio");
        JButton btnEditarEjercicio = crearBoton("Editar Ejercicio");
        JButton btnVerClientes = crearBoton("Ver Clientes");
        JButton btnEditarClientes = crearBoton("Editar Clientes");
        JButton btnEliminarCliente = crearBoton("Eliminar Cliente");
        JButton btnVerEjercicios = crearBoton("Ver Ejercicios");
        JButton btnVolver = crearBoton("Volver");

        btnVolver.setBackground(new Color(204, 0, 0));
        btnVolver.setForeground(Color.WHITE);

        btnAgregarEjercicio.addActionListener(e -> BaseDeDatos.agregarEjercicio());
        btnEditarEjercicio.addActionListener(e -> BaseDeDatos.editarEjercicio());
        btnVerClientes.addActionListener(e -> BaseDeDatos.verClientes());
        btnEditarClientes.addActionListener(e -> BaseDeDatos.editarCliente());
        btnEliminarCliente.addActionListener(e -> BaseDeDatos.eliminarCliente());
        btnVerEjercicios.addActionListener(e -> BaseDeDatos.verEjerciciosAdmin());

        btnVolver.addActionListener(e -> {
            new InterfazPrincipal();
            dispose();
        });

        botonesPanel.add(btnAgregarEjercicio);
        botonesPanel.add(btnEditarEjercicio);
        botonesPanel.add(btnVerClientes);
        botonesPanel.add(btnEditarClientes);
        botonesPanel.add(btnEliminarCliente);
        botonesPanel.add(btnVerEjercicios);
        botonesPanel.add(btnVolver);

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(botonesPanel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(panel);
        setVisible(true);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Sans-serif", Font.BOLD, 16));
        boton.setBackground(Color.WHITE);
        boton.setForeground(Color.DARK_GRAY);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        boton.setOpaque(true);
        boton.setBorderPainted(true);
        return boton;
    }
}
