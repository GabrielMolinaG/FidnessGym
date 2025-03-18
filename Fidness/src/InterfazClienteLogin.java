import javax.swing.*;
import java.awt.*;

public class InterfazClienteLogin extends JFrame {

    public InterfazClienteLogin() {
        setTitle("Acceso Cliente");
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240));

        JLabel titulo = new JLabel("Acceso Cliente", SwingConstants.CENTER);
        titulo.setFont(new Font("Sans-serif", Font.BOLD, 20));
        titulo.setForeground(new Color(0, 102, 204));

        JPanel botonesPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        botonesPanel.setBackground(new Color(240, 240, 240));

        JButton btnIniciarSesion = crearBoton("Iniciar Sesión");
        JButton btnRegistrarse = crearBoton("Registrarse");
        JButton btnVolver = crearBoton("Volver");

        btnVolver.setBackground(new Color(204, 0, 0));
        btnVolver.setForeground(Color.WHITE);

        btnIniciarSesion.addActionListener(e -> iniciarSesionCliente());
        btnRegistrarse.addActionListener(e -> {
            BaseDeDatos.registrarCliente();
            new InterfazClienteLogin();
            dispose();
        });

        btnVolver.addActionListener(e -> {
            new InterfazPrincipal();
            dispose();
        });

        botonesPanel.add(btnIniciarSesion);
        botonesPanel.add(btnRegistrarse);
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

    private void iniciarSesionCliente() {
        String usuario = JOptionPane.showInputDialog("Ingrese su usuario:");
        String contraseña = JOptionPane.showInputDialog("Ingrese su contraseña:");

        if (BaseDeDatos.iniciarSesionCliente(usuario, contraseña)) {
            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
            new InterfazCliente();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
        }
    }
}
