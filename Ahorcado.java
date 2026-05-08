import java.util.*;

public class Ahorcado {

    // ==========================================
    // 🎨 COLORES
    // ==========================================

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

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

            System.out.println("\n\n");

            System.out.println(CYAN +
            " █████╗ ██╗  ██╗ ██████╗ ██████╗  ██████╗ █████╗ ██████╗  ██████╗ ");

            System.out.println(
            "██╔══██╗██║  ██║██╔═══██╗██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔═══██╗");

            System.out.println(
            "███████║███████║██║   ██║██████╔╝██║     ███████║██║  ██║██║   ██║");

            System.out.println(
            "██╔══██║██╔══██║██║   ██║██╔══██╗██║     ██╔══██║██║  ██║██║   ██║");

            System.out.println(
            "██║  ██║██║  ██║╚██████╔╝██║  ██║╚██████╗██║  ██║██████╔╝╚██████╔╝");

            System.out.println(
            "╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚═════╝  ╚═════╝ "
            + RESET);

            System.out.println(PURPLE +
            "\n                  ☠️ AHORCADO ☠️\n"
            + RESET);

            System.out.println(BLUE +
            "               ╔══════════════╗");

            System.out.println(
            "            ╔══╝              ╚══╗");

            System.out.println(
            "         ╔══╝                      ╚══╗");

            System.out.println(
            "       ╔═╝                            ╚═╗");

            System.out.println(
            "       ║    " + GREEN + "🎮  [1] JUGAR" + BLUE + "            ║");

            System.out.println(
            "       ║                                ║");

            System.out.println(
            "       ║    " + CYAN + "📜  [2] INSTRUCCIONES" + BLUE + "   ║");

            System.out.println(
            "       ║                                ║");

            System.out.println(
            "       ║    " + RED + "🚪  [3] SALIR" + BLUE + "            ║");

            System.out.println(
            "       ╚═╗                            ╔═╝");

            System.out.println(
            "         ╚══╗                      ╔══╝");

            System.out.println(
            "            ╚══╗              ╔══╝");

            System.out.println(
            "               ╚══════════════╝"
            + RESET);

            opcion = leerInt(
            YELLOW + "\n➜ Selecciona una opción" + RESET,
            1,
            3
            );

            switch (opcion) {

                case 1:
                    jugar();
                    break;

                case 2:
                    instrucciones();
                    break;

                case 3:
                    System.out.println(RED +
                    "\n☠️ Cerrando juego..."
                    + RESET);
                    break;
            }

        } while (opcion != 3);
    }

    static void jugar() {

        List<String> categorias = obtenerCategorias();

        System.out.println(CYAN +
        "\n══════════ 📂 CATEGORÍAS 📂 ══════════\n"
        + RESET);

        for (int i = 0; i < categorias.size(); i++) {

            System.out.println(
            YELLOW + (i + 1) + ". "
            + WHITE + categorias.get(i));
        }

        int opc = leerInt(
        "\nSelecciona el número",
        1,
        categorias.size()
        );

        String categoria = categorias.get(opc - 1);

        List<String[]> lista = new ArrayList<String[]>();

        for (String[] p : palabras) {

            if (p[0].equals(categoria)) {

                lista.add(p);
            }
        }

        String[] seleccion =
        lista.get(random.nextInt(lista.size()));

        String palabra = seleccion[1];
        String pista = seleccion[2];

        char[] estado = new char[palabra.length()];

        Arrays.fill(estado, '_');

        int errores = 0;
        int MAX = 6;

        boolean usoPista = false;

        while (
        errores < MAX &&
        !String.valueOf(estado).equals(palabra)
        ) {

            System.out.println(PURPLE +
            "\n═══════════════════════════════════");

            System.out.println(
            "📂 Categoría: "
            + YELLOW + categoria);

            System.out.println(PURPLE +
            "═══════════════════════════════════"
            + RESET);

            mostrarAhorcado(errores);

            System.out.println(
            CYAN +
            "\n🔤 PALABRA:\n");

            System.out.println(
            WHITE +
            "        " + mostrar(estado)
            + RESET);

            System.out.println(
            RED +
            "\n❤️ Errores: "
            + errores + "/" + MAX
            + RESET);

            char letra =
            leerChar("\n✍️ Introduce una letra");

            boolean acierto = false;

            for (int i = 0; i < palabra.length(); i++) {

                if (palabra.charAt(i) == letra) {

                    estado[i] = letra;
                    acierto = true;
                }
            }

            if (!acierto) {

                errores++;

                System.out.println(
                RED +
                "\n❌ LETRA INCORRECTA"
                + RESET);

            } else {

                System.out.println(
                GREEN +
                "\n✅ LETRA CORRECTA"
                + RESET);
            }

            if (
            !usoPista &&
            errores < MAX - 1 &&
            !String.valueOf(estado).equals(palabra)
            ) {

                System.out.print(
                PURPLE +
                "\n💡 ¿Quieres una pista? (S/N): "
                + RESET);

                String op =
                scanner.nextLine().toUpperCase();

                if (op.equals("S")) {

                    System.out.println(
                    YELLOW +
                    "\n🧠 PISTA: "
                    + pista
                    + RESET);

                    errores++;
                    usoPista = true;
                }
            }
        }

        if (String.valueOf(estado).equals(palabra)) {

            System.out.println(GREEN);

            System.out.println(
            "\n██╗   ██╗██╗ ██████╗████████╗ ██████╗ ██████╗ ██╗   ██╗");

            System.out.println(
            "██║   ██║██║██╔════╝╚══██╔══╝██╔═══██╗██╔══██╗╚██╗ ██╔╝");

            System.out.println(
            "██║   ██║██║██║        ██║   ██║   ██║██████╔╝ ╚████╔╝ ");

            System.out.println(
            "╚██╗ ██╔╝██║██║        ██║   ██║   ██║██╔══██╗  ╚██╔╝  ");

            System.out.println(
            " ╚████╔╝ ██║╚██████╗   ██║   ╚██████╔╝██║  ██║   ██║   ");

            System.out.println(
            "  ╚═══╝  ╚═╝ ╚═════╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝   ╚═╝   ");

            System.out.println(
            YELLOW +
            "\n🎉 ¡GANASTE! LA PALABRA ERA: "
            + palabra
            + RESET);

        } else {

            mostrarAhorcado(errores);

            System.out.println(RED);

            System.out.println(
            "\n ██████╗  █████╗ ███╗   ███╗███████╗");

            System.out.println(
            "██╔════╝ ██╔══██╗████╗ ████║██╔════╝");

            System.out.println(
            "██║  ███╗███████║██╔████╔██║█████╗  ");

            System.out.println(
            "██║   ██║██╔══██║██║╚██╔╝██║██╔══╝  ");

            System.out.println(
            "╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗");

            System.out.println(
            " ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝");

            System.out.println(
            WHITE +
            "\n☠️ LA PALABRA ERA: "
            + palabra
            + RESET);
        }

        leerString("\nPresiona ENTER para volver");
    }

    static void mostrarAhorcado(int e) {

        String[] estados = {

        " \n\n\n\n\n",

        "      ╭────╮\n" +
        "      │    │\n" +
        "      O    │\n" +
        "           │\n" +
        "           │\n" +
        "         ═════",

        "      ╭────╮\n" +
        "      │    │\n" +
        "      O    │\n" +
        "      │    │\n" +
        "           │\n" +
        "         ═════",

        "      ╭────╮\n" +
        "      │    │\n" +
        "      O    │\n" +
        "     ╱│    │\n" +
        "           │\n" +
        "         ═════",

        "      ╭────╮\n" +
        "      │    │\n" +
        "      O    │\n" +
        "     ╱│╲   │\n" +
        "           │\n" +
        "         ═════",

        "      ╭────╮\n" +
        "      │    │\n" +
        "      O    │\n" +
        "     ╱│╲   │\n" +
        "     ╱     │\n" +
        "         ═════",

        "      ╭────╮\n" +
        "      │    │\n" +
        "      O    │\n" +
        "     ╱│╲   │\n" +
        "     ╱ ╲   │\n" +
        "         ═════"
        };

        System.out.println(YELLOW + estados[e] + RESET);
    }

    static List<String> obtenerCategorias() {

        Set<String> set =
        new LinkedHashSet<String>();

        for (String[] p : palabras) {

            set.add(p[0]);
        }

        return new ArrayList<String>(set);
    }

    static String mostrar(char[] estado) {

        String res = "";

        for (char c : estado) {

            res += c + " ";
        }

        return res;
    }

    static void instrucciones() {

        System.out.println(CYAN +
        "\n══════════ 📜 INSTRUCCIONES 📜 ══════════\n"
        + RESET);

        System.out.println(YELLOW +
        "➤ Debes adivinar la palabra");

        System.out.println(YELLOW +
        "➤ Tienes máximo 6 errores");

        System.out.println(YELLOW +
        "➤ Las pistas cuestan 1 error");

        System.out.println(YELLOW +
        "➤ Si completas el muñeco pierdes ☠️"
        + RESET);

        leerString("\nPresiona ENTER");
    }

    static String leerString(String msg) {

        System.out.print(
        GREEN + msg + ": "
        + RESET);

        return scanner.nextLine();
    }

    static char leerChar(String msg) {

        while (true) {

            String s =
            leerString(msg).toUpperCase();

            if (
            s.length() == 1 &&
            Character.isLetter(s.charAt(0))
            ) {

                return s.charAt(0);
            }

            System.out.println(
            RED +
            "⚠️ Solo una letra"
            + RESET);
        }
    }

    static int leerInt(
    String msg,
    int min,
    int max
    ) {

        while (true) {

            try {

                int n = Integer.parseInt(
                leerString(msg)
                );

                if (n >= min && n <= max) {

                    return n;
                }

            } catch (Exception e) {}

            System.out.println(
            RED +
            "⚠️ Opción inválida"
            + RESET);
        }
    }

    public static void main(String[] args) {

        menu();
    }
}
