import javax.swing.*;
import java.io.*;
import java.util.*;

public class BaseDeDatos {
    private static final String ARCHIVO_EJERCICIOS = "ejercicios.txt";
    private static final String ARCHIVO_CLIENTES = "clientes.txt";

    private static ArrayList<Ejercicio> ejercicios = new ArrayList<>();
    private static ArrayList<ClienteModel> clientes = new ArrayList<>();
    private static Set<String> usuariosRegistrados = new HashSet<>();

    static {
        cargarEjercicios();
        cargarClientes();
    }

    // -------------------- GESTIÓN DE EJERCICIOS --------------------

    public static void agregarEjercicio() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del ejercicio:");
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del ejercicio no puede estar vacío.");
            return;
        }

        String tipo = JOptionPane.showInputDialog("Ingrese el tipo de ejercicio (Pierna, Brazo, etc.):");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripción del ejercicio:");

        Ejercicio ejercicio = new Ejercicio(nombre, tipo, descripcion);
        ejercicios.add(ejercicio);
        guardarEjercicios();
        JOptionPane.showMessageDialog(null, "Ejercicio agregado exitosamente.");
    }

    public static void editarEjercicio() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del ejercicio a editar:");
        for (Ejercicio e : ejercicios) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:", e.getNombre());
                String nuevoTipo = JOptionPane.showInputDialog("Nuevo tipo:", e.getTipo());
                String nuevaDesc = JOptionPane.showInputDialog("Nueva descripción:", e.getDescripcion());

                ejercicios.remove(e);
                ejercicios.add(new Ejercicio(nuevoNombre, nuevoTipo, nuevaDesc));
                guardarEjercicios();
                JOptionPane.showMessageDialog(null, "Ejercicio actualizado.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Ejercicio no encontrado.");
    }

    public static void verEjerciciosAdmin() {
        if (ejercicios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay ejercicios registrados.");
            return;
        }

        StringBuilder listado = new StringBuilder("Lista de Ejercicios:\n\n");
        for (Ejercicio e : ejercicios) {
            listado.append(e.toString()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, listado.toString(), "Ejercicios Registrados", JOptionPane.INFORMATION_MESSAGE);
    }

   public static void consultarEjercicios() {
    if (ejercicios.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No hay ejercicios registrados.");
        return;
    }

    ArrayList<String> categorias = new ArrayList<>();
    for (Ejercicio e : ejercicios) {
        if (!categorias.contains(e.getTipo())) {
            categorias.add(e.getTipo());
        }
    }
    categorias.add("Todos");

    String seleccion = (String) JOptionPane.showInputDialog(
            null, "Seleccione la categoría de ejercicios:", "Consultar Ejercicios",
            JOptionPane.QUESTION_MESSAGE, null, categorias.toArray(), categorias.get(0));

    if (seleccion == null) return;

    StringBuilder listado = new StringBuilder("Ejercicios disponibles:\n\n");
    for (Ejercicio e : ejercicios) {
        if (seleccion.equals("Todos") || e.getTipo().equalsIgnoreCase(seleccion)) {
            listado.append(e.toString()).append("\n\n");
        }
    }

  
    JTextArea textArea = new JTextArea(listado.toString());
    textArea.setEditable(false);
    textArea.setRows(10); 
    textArea.setColumns(40);
    JScrollPane scrollPane = new JScrollPane(textArea);


    JOptionPane.showMessageDialog(null, scrollPane, "Ejercicios Registrados", JOptionPane.INFORMATION_MESSAGE);
}


    private static void guardarEjercicios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_EJERCICIOS))) {
            for (Ejercicio e : ejercicios) {
                writer.write(e.getNombre() + "," + e.getTipo() + "," + e.getDescripcion() + "\n");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar ejercicios.");
        }
    }

    private static void cargarEjercicios() {
    File archivo = new File(ARCHIVO_EJERCICIOS);
    ejercicios.clear();

    if (!archivo.exists()) {
        ejercicios.addAll(Ejercicio.obtenerEjerciciosPredeterminados());
        guardarEjercicios();
        return;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_EJERCICIOS))) {
        String linea;
        System.out.println("Ejercicios cargados desde el archivo:");
        while ((linea = reader.readLine()) != null) {
            System.out.println(linea); // 🔹 Verifica si está leyendo todo
            String[] datos = linea.split(",");
            if (datos.length == 3) {
                ejercicios.add(new Ejercicio(datos[0], datos[1], datos[2]));
            }
        }
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "Error al cargar los ejercicios.");
    }

    System.out.println("Ejercicios en memoria después de cargar:");
    for (Ejercicio e : ejercicios) {
        System.out.println(e);
    }
}

    

    // -------------------- GESTIÓN DE CLIENTES --------------------

   public static boolean iniciarSesionCliente(String usuario, String contraseña) {
    for (ClienteModel c : clientes) {
        if (c.getUsuario().equals(usuario) && c.getContraseña().equals(contraseña)) {
            return true;
        }
    }
    return false;
}

public static void registrarCliente() {
    String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
    if (nombre == null || nombre.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
        return;
    }

    String contraseña = JOptionPane.showInputDialog("Ingrese su contraseña:");
    if (contraseña == null || contraseña.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía.");
        return;
    }

    // Generar un usuario único
    String usuario = nombre.replaceAll("\\s+", "") + new Random().nextInt(1000);

    // Verificar si el usuario ya existe
    for (ClienteModel c : clientes) {
        if (c.getUsuario().equals(usuario)) {
            JOptionPane.showMessageDialog(null, "El usuario ya existe. Intente con otro nombre.");
            return;
        }
    }

    // Crear y almacenar el cliente
    clientes.add(new ClienteModel(nombre, usuario, contraseña));
    usuariosRegistrados.add(usuario);
    guardarClientes();

    JOptionPane.showMessageDialog(null, "Registro exitoso.\n\n"
            + "Usuario: " + usuario + "\n"
            + "Contraseña: " + contraseña,
            "Usuario Registrado", JOptionPane.INFORMATION_MESSAGE);
}

    
    public static void verClientes() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados.");
            return;
        }

        StringBuilder listado = new StringBuilder("Clientes registrados:\n");
        for (ClienteModel c : clientes) {
            listado.append(c.toString()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, listado.toString());
    }

    public static void editarCliente() {
        String usuario = JOptionPane.showInputDialog("Ingrese el usuario del cliente a editar:");
        for (ClienteModel c : clientes) {
            if (c.getUsuario().equalsIgnoreCase(usuario)) {
                String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:", c.getNombre());
                String nuevaContraseña = JOptionPane.showInputDialog("Nueva contraseña:", c.getContraseña());

                clientes.remove(c);
                clientes.add(new ClienteModel(nuevoNombre, usuario, nuevaContraseña));
                guardarClientes();
                JOptionPane.showMessageDialog(null, "Cliente actualizado.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
    }

    public static void eliminarCliente() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados.");
            return;
        }

        String usuario = JOptionPane.showInputDialog("Ingrese el usuario del cliente a eliminar:");
        clientes.removeIf(c -> c.getUsuario().equalsIgnoreCase(usuario));
        guardarClientes();
        JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente.");
    }

    public static void crearRutina() {
    if (ejercicios.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No hay ejercicios disponibles.");
        return;
    }

    String[] listaEjercicios = new String[ejercicios.size()];
    for (int i = 0; i < ejercicios.size(); i++) {
        listaEjercicios[i] = ejercicios.get(i).getNombre();
    }

    JList<String> jList = new JList<>(listaEjercicios);
    jList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    JOptionPane.showMessageDialog(null, new JScrollPane(jList), "Seleccione ejercicios para su rutina", JOptionPane.PLAIN_MESSAGE);

    int[] seleccion = jList.getSelectedIndices();
    if (seleccion.length == 0) {
        JOptionPane.showMessageDialog(null, "No se seleccionaron ejercicios.");
        return;
    }

    StringBuilder rutina = new StringBuilder("Rutina del Cliente:\n\n");
    for (int index : seleccion) {
        rutina.append(ejercicios.get(index).toString()).append("\n\n");
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("rutina.txt"))) {
        writer.write(rutina.toString());
        JOptionPane.showMessageDialog(null, "Rutina guardada en 'rutina.txt'.");
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "Error al guardar la rutina.");
    }
}


// -------------------- GESTIÓN DE ADMINISTRADOR --------------------
public static boolean validarContraseñaAdministrador(String contraseña) {
    final String ADMIN_PASSWORD = "Fidness";
    return contraseña.equals(ADMIN_PASSWORD);
}



    private static void guardarClientes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_CLIENTES))) {
            for (ClienteModel c : clientes) {
                writer.write(c.getNombre() + "," + c.getUsuario() + "," + c.getContraseña() + "\n");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar clientes.");
        }
    }

    private static void cargarClientes() {
        File archivo = new File(ARCHIVO_CLIENTES);
        clientes.clear();
        usuariosRegistrados.clear();

        if (!archivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_CLIENTES))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    clientes.add(new ClienteModel(datos[0], datos[1], datos[2]));
                    usuariosRegistrados.add(datos[1]);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar los clientes.");
        }
    }
    
    
}
