import javax.swing.*;
import java.awt.*;

public class InterfazCliente extends JFrame {

    public InterfazCliente() {
        setTitle("Menú Cliente");
        setSize(420, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240));

 
        JLabel titulo = new JLabel("Menú Cliente", SwingConstants.CENTER);
        titulo.setFont(new Font("Sans-serif", Font.BOLD, 20));
        titulo.setForeground(new Color(0, 102, 204));


        JPanel botonesPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        botonesPanel.setBackground(new Color(240, 240, 240));

        JButton btnConsultarEjercicios = crearBoton("Consultar Ejercicios");
        JButton btnCrearRutina = crearBoton("Crear Rutina");
        JButton btnVolver = crearBoton("Volver");

        
        btnVolver.setBackground(new Color(204, 0, 0));
        btnVolver.setForeground(Color.WHITE);

        
        btnConsultarEjercicios.addActionListener(e -> BaseDeDatos.consultarEjercicios());
        btnCrearRutina.addActionListener(e -> BaseDeDatos.crearRutina());

        btnVolver.addActionListener(e -> {
            new InterfazPrincipal();
            dispose();
        });

        botonesPanel.add(btnConsultarEjercicios);
        botonesPanel.add(btnCrearRutina);
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
