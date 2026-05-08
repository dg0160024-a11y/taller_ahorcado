import java.util.*;

public class Ahorcado {

    // Colores para la terminal
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    static String[][] palabras = {
        {"ANIMALES", "ELEFANTE", "Mamífero grande con trompa"},
        {"ANIMALES", "LEON", "Rey de la selva"},
        {"PAISES", "COLOMBIA", "El país más acogedor del mundo"},
        {"PAISES", "MEXICO", "Cuna de los Aztecas y el Mariachi"},
        {"TECNOLOGIA", "ANDROID", "Sistema operativo de Google"},
        {"PROGRAMACION", "JAVA", "Lenguaje de la taza de café"},
        {"COLOMBIA", "MEDELLIN", "La ciudad de la eterna primavera"}
    };

    static void menu() {
        int opcion;
        do {
            System.out.println(PURPLE + "\n╔════════════════════════════════╗" + RESET);
            System.out.println(PURPLE + "║       🔥 AHORCADO PRO 🔥       ║" + RESET);
            System.out.println(PURPLE + "╠════════════════════════════════╣" + RESET);
            System.out.println(PURPLE + "║  " + YELLOW + "1. Jugar" + PURPLE + "                      ║");
            System.out.println(PURPLE + "║  " + YELLOW + "2. Instrucciones" + PURPLE + "              ║");
            System.out.println(PURPLE + "║  " + RED + "3. Salir" + PURPLE + "                      ║");
            System.out.println(PURPLE + "╚════════════════════════════════╝" + RESET);

            opcion = leerInt("Elige una opción", 1, 3);

            switch (opcion) {
                case 1:
                    jugar();
                    break;
                case 2:
                    instrucciones();
                    break;
                case 3:
                    System.out.println(RED + "¡Gracias por jugar! Adiós. 👋" + RESET);
                    break;
            }
        } while (opcion != 3);
    }

    static void jugar() {
        List<String> categorias = obtenerCategorias();
        System.out.println(CYAN + "\n--- SELECCIONA TU CATEGORÍA ---" + RESET);
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println(YELLOW + (i + 1) + ". " + RESET + categorias.get(i));
        }

        int opc = leerInt("Selecciona el número", 1, categorias.size());
        String categoria = categorias.get(opc - 1);

        List<String[]> lista = new ArrayList<String[]>();
        for (String[] p : palabras) {
            if (p[0].equals(categoria)) lista.add(p);
        }

        String[] seleccion = lista.get(random.nextInt(lista.size()));
        String palabra = seleccion[1];
        String pista = seleccion[2];

        char[] estado = new char[palabra.length()];
        Arrays.fill(estado, '_');

        int errores = 0;
        int MAX = 6;
        boolean usoPista = false;

        while (errores < MAX && !String.valueOf(estado).equals(palabra)) {
            System.out.println(CYAN + "\nCategoría: " + RESET + YELLOW + categoria + RESET);
            mostrarAhorcado(errores);
            System.out.println("\nPalabra: " + CYAN + mostrar(estado) + RESET);
            System.out.println(RED + "Errores: " + errores + "/" + MAX + RESET);

            char letra = leerChar("Introduce una letra");

            boolean acierto = false;
            for (int i = 0; i < palabra.length(); i++) {
                if (palabra.charAt(i) == letra) {
                    estado[i] = letra;
                    acierto = true;
                }
            }

            if (!acierto) {
                errores++;
                System.out.println(RED + "❌ ¡Fallaste!" + RESET);
            } else {
                System.out.println(GREEN + "✅ ¡Bien hecho!" + RESET);
            }

            if (!usoPista && errores < MAX - 1 && !String.valueOf(estado).equals(palabra)) {
                System.out.print(PURPLE + "¿Quieres una pista? (costo 1 error) S/N: " + RESET);
                String op = scanner.nextLine().toUpperCase();
                if (op.equals("S")) {
                    System.out.println(YELLOW + "💡 PISTA: " + pista + RESET);
                    usoPista = true;
                    errores++;
                }
            }
        }

        if (String.valueOf(estado).equals(palabra)) {
            System.out.println(GREEN + "\n🎊 ¡GANASTE SOCIO! 🔥 La palabra era: " + palabra + RESET);
        } else {
            mostrarAhorcado(errores);
            System.out.println(RED + "\n☠️ ¡PERDISTE! La palabra era: " + palabra + RESET);
        }
        leerString("Presiona ENTER para volver");
    }

    static void mostrarAhorcado(int e) {
        String[] estados = {
            "  +---+\n      |\n      |\n      |\n      |\n      |\n=======",
            "  +---+\n  O   |\n      |\n      |\n      |\n      |\n=======",
            "  +---+\n  O   |\n  |   |\n      |\n      |\n      |\n=======",
            "  +---+\n  O   |\n /|   |\n      |\n      |\n      |\n=======",
            "  +---+\n  O   |\n /|\\  |\n      |\n      |\n      |\n=======",
            "  +---+\n  O   |\n /|\\  |\n /    |\n      |\n      |\n=======",
            "  +---+\n  O   |\n /|\\  |\n / \\  |\n      |\n      |\n======="
        };
        System.out.println(YELLOW + estados[e] + RESET);
    }

    static List<String> obtenerCategorias() {
        Set<String> set = new LinkedHashSet<String>();
        for (String[] p : palabras) set.add(p[0]);
        return new ArrayList<String>(set);
    }

    static String mostrar(char[] estado) {
        String res = "";
        for (char c : estado) res += c + " ";
        return res;
    }

    static void instrucciones() {
        System.out.println(CYAN + "\n--- INSTRUCCIONES ---" + RESET);
        System.out.println("Adivina la palabra. Tienes 6 vidas. Las pistas te quitan 1 vida.");
        leerString("Presiona ENTER");
    }

    static String leerString(String msg) {
        System.out.print(GREEN + msg + ": " + RESET);
        return scanner.nextLine();
    }

    static char leerChar(String msg) {
        while (true) {
            String s = leerString(msg).toUpperCase();
            if (s.length() == 1 && Character.isLetter(s.charAt(0))) return s.charAt(0);
            System.out.println(RED + "Solo una letra" + RESET);
        }
    }

    static int leerInt(String msg, int min, int max) {
        while (true) {
            try {
                int n = Integer.parseInt(leerString(msg));
                if (n >= min && n <= max) return n;
            } catch (Exception e) {}
            System.out.println(RED + "Opción inválida" + RESET);
        }
    }

    public static void main(String[] args) {
        menu();
    }
}