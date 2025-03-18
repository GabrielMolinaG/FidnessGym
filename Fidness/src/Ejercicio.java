import java.io.*;
import java.util.List;

public class Ejercicio {
    private String nombre;
    private String tipo;
    private String descripcion;

    private static final String ARCHIVO_EJERCICIOS = "ejercicios.txt";

    public Ejercicio(String nombre, String tipo, String descripcion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "Ejercicio: " + nombre + "\nTipo: " + tipo + "\nDescripción: " + descripcion;
    }

    public static List<Ejercicio> obtenerEjerciciosPredeterminados() {
    return List.of(
        // Pecho
        new Ejercicio("Press de Banca", "Pecho", "Acostado, bajar y subir la barra con peso."),
        new Ejercicio("Aperturas con Mancuernas", "Pecho", "Extender los brazos y juntarlos."),
        new Ejercicio("Fondos en Paralelas", "Pecho", "Bajar y subir el cuerpo en barras paralelas."),
        new Ejercicio("Press Inclinado", "Pecho", "Similar al press de banca, pero en inclinación."),
        new Ejercicio("Flexiones de Pecho", "Pecho", "Bajar y subir el cuerpo apoyado en las manos."),

        // Brazos
        new Ejercicio("Curl de Bíceps", "Brazo", "Levantar mancuernas con flexión del codo."),
        new Ejercicio("Martillo con Mancuernas", "Brazo", "Sujetar mancuernas verticalmente y subir."),
        new Ejercicio("Press Francés", "Brazo", "Levantar barra o mancuerna desde detrás de la cabeza."),
        new Ejercicio("Fondos en Banco", "Brazo", "Apoyarse en un banco y bajar el cuerpo."),
        new Ejercicio("Extensión de Tríceps", "Brazo", "Extender el codo con mancuerna o polea."),

        // Piernas
        new Ejercicio("Sentadilla con Barra", "Pierna", "Bajar y subir con una barra sobre los hombros."),
        new Ejercicio("Peso Muerto", "Pierna", "Levantar una barra desde el suelo."),
        new Ejercicio("Prensa de Pierna", "Pierna", "Empujar un peso con las piernas en la prensa."),
        new Ejercicio("Zancadas con Mancuernas", "Pierna", "Avanzar con una pierna flexionada mientras se sostiene peso."),
        new Ejercicio("Elevación de Talones", "Pierna", "Subir y bajar los talones para trabajar los gemelos."),

        // Espalda
        new Ejercicio("Dominadas", "Espalda", "Subir el cuerpo colgado de una barra."),
        new Ejercicio("Remo con Barra", "Espalda", "Remar con barra inclinando el torso."),
        new Ejercicio("Pullover con Mancuerna", "Espalda", "Llevar una mancuerna desde el pecho hacia atrás."),
        new Ejercicio("Jalón al Pecho", "Espalda", "Tirar de la barra hacia el pecho en polea alta."),
        new Ejercicio("Hiperextensiones", "Espalda", "Levantar el torso en banco de hiperextensiones."),

        // Hombros
        new Ejercicio("Press Militar", "Hombros", "Subir y bajar una barra o mancuernas sobre la cabeza."),
        new Ejercicio("Elevaciones Laterales", "Hombros", "Levantar mancuernas con los brazos rectos hacia los lados."),
        new Ejercicio("Face Pull", "Hombros", "Tirar de la cuerda hacia la cara en polea."),
        new Ejercicio("Pájaros", "Hombros", "Inclinarse hacia adelante y levantar pesas lateralmente."),
        new Ejercicio("Encogimientos con Mancuernas", "Hombros", "Subir y bajar los hombros con mancuernas."),

        // Abdominales
        new Ejercicio("Crunches", "Abdominales", "Flexionar el tronco llevando los hombros hacia las rodillas."),
        new Ejercicio("Plancha", "Abdominales", "Mantener el cuerpo recto apoyado en antebrazos y puntas de los pies."),
        new Ejercicio("Elevación de Piernas", "Abdominales", "Levantar las piernas estando acostado."),
        new Ejercicio("Bicicleta Abdominal", "Abdominales", "Girar el torso y tocar el codo con la rodilla opuesta."),
        new Ejercicio("Toques al Tobillo", "Abdominales", "Acostado, tocar los tobillos con las manos."),

        // Estiramientos
        new Ejercicio("Estiramiento de Cuello", "Estiramientos", "Girar la cabeza de lado a lado."),
        new Ejercicio("Estiramiento de Espalda", "Estiramientos", "Flexionar el torso hacia adelante y estirar la espalda."),
        new Ejercicio("Estiramiento de Isquiotibiales", "Estiramientos", "Tocar los pies con las manos manteniendo las piernas rectas."),
        new Ejercicio("Estiramiento de Brazos", "Estiramientos", "Estirar los brazos cruzándolos por el pecho."),
        new Ejercicio("Postura del Niño", "Estiramientos", "Posición de yoga para relajar la espalda.")
    );
}

    public static void crearArchivoEjercicios() {
        File archivo = new File(ARCHIVO_EJERCICIOS);

        if (!archivo.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
                List<Ejercicio> ejercicios = obtenerEjerciciosPredeterminados();
                for (Ejercicio e : ejercicios) {
                    writer.write(e.getNombre() + "," + e.getTipo() + "," + e.getDescripcion() + "\n");
                }
                System.out.println("Archivo 'ejercicios.txt' creado con ejercicios predefinidos.");
            } catch (IOException ex) {
                System.err.println("Error al crear el archivo de ejercicios: " + ex.getMessage());
            }
        }
    }
}