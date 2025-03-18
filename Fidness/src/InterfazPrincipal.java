import javax.swing.*;
import java.awt.*;

public class InterfazPrincipal extends JFrame {

    public InterfazPrincipal() {
        setTitle("Fidness Gym - Inicio");
        setSize(420, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240));

        JLabel titulo = new JLabel("Fidness Gym", SwingConstants.CENTER);
        titulo.setFont(new Font("Sans-serif", Font.BOLD, 28));
        titulo.setForeground(new Color(0, 102, 204));

        JPanel botonesPanel = new JPanel(new GridLayout(3, 1, 15, 15));
        botonesPanel.setBackground(new Color(240, 240, 240));

        JButton btnAdmin = crearBoton("Administrador");
        JButton btnCliente = crearBoton("Cliente");
        JButton btnSalir = crearBoton("Salir");

        btnSalir.setBackground(new Color(204, 0, 0));
        btnSalir.setForeground(Color.WHITE);

    
        btnAdmin.addActionListener(e -> {
            String password = JOptionPane.showInputDialog("Ingrese la contraseña de Administrador:");
            if (BaseDeDatos.validarContraseñaAdministrador(password)) {
                new InterfazAdministrador();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
            }
        });

        btnCliente.addActionListener(e -> {
            new InterfazClienteLogin();
            dispose();
        });

        btnSalir.addActionListener(e -> System.exit(0));

        botonesPanel.add(btnAdmin);
        botonesPanel.add(btnCliente);
        botonesPanel.add(btnSalir);

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
