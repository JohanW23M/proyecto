
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class proyecto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ingreso de datos personales
        System.out.print("Ingrese el nombre del paciente: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese su fecha de nacimiento (dd mm aaaa): ");
        int dia = sc.nextInt();
        int mes = sc.nextInt();
        int anio = sc.nextInt();

        // Calcular edad
        int edad = calcularEdad(anio, mes, dia);
        
        // Determinar grupo de edad
        String grupoEdad = determinarGrupoEdad(edad);

        // Calcular FCM y Rango Esperado
        int fcm = 220 - edad;
        double rangoMin = fcm * 0.50;
        double rangoMax = fcm * 0.85;

        // Mostrar datos iniciales
        System.out.printf("\nNombre              : %s\n", nombre);
        System.out.printf("Edad                : %d years\n", edad);
        System.out.printf("Grupo de Edad       : %s\n", grupoEdad);
        System.out.printf("Rango esperado      : %d - %d BPM\n", (int)rangoMin, (int)rangoMax);
        System.out.printf("Frecuencia maxima   : %d BPM\n\n", fcm);

        // Esperar que el usuario presione Enter para comenzar la simulación
        System.out.println("Presione Enter para comenzar la simulacion del ejercicio...");
        sc.nextLine(); // Pausar hasta que el usuario presione Enter
        sc.nextLine(); // Otra vez para iniciar

        // Simulación del ejercicio
        simulacionEjercicio(fcm, rangoMin, rangoMax);
    }

    // Metodo para calcular la edad
    public static int calcularEdad(int anio, int mes, int dia) {
        int anioActual = 2024; 
        int mesActual = 11;    
        int diaActual = 18;    

        int edad = anioActual - anio;
        if (mesActual < mes || (mesActual == mes && diaActual < dia)) {
            edad--;
        }
        return edad;
    }

    // Metodo para determinar el grupo de edad
    public static String determinarGrupoEdad(int edad) {
        if (edad <= 1) {
            return "Neonato";
        } else if (edad <= 12) {
            return "Pediatrico";
        } else if (edad <= 17) {
            return "Adolescente";
        } else if (edad <= 64) {
            return "Adulto (18-64 years)";
        } else {
            return "Adulto mayor";
        }
    }

    // Metodo para simular el ejercicio
    public static void simulacionEjercicio(int fcm, double rangoMin, double rangoMax) {
        Scanner sc = new Scanner(System.in);
        int minuto = 1;
        boolean continuar = true;

        // Crear lista para almacenar los resultados
        List<String> historial = new ArrayList<>();

        // Umbrales para las alertas
        int alertaLeve = (int)rangoMax; // Alerta leve cuando se alcanza el rango maximo
        int alertaGrave = (int)(fcm * 0.90); // Alerta grave cuando se alcanza el 90% de la frecuencia maxima

        // Iniciar con un valor dentro del rango esperado
        int frecuenciaCardiaca = (int)(rangoMin + Math.random() * (rangoMax - rangoMin));
        String complicaciones = "Ninguna";

        // Encabezado de la tabla
        System.out.printf("\n%-8s %-25s %-12s %-50s %-25s\n", "Minuto", "Frecuencia cardiaca", "Duracion (min)", "Complicaciones", "Taquicardia");
        System.out.println("----------------------------------------------------------------------------------");

        // Bucle de simulacion de ejercicio
        boolean taquicardiaDetectada = false; // Variable para detectar si hubo taquicardia
        while (continuar) {
            // Incrementar la frecuencia de manera mas gradual (1 a 2 BPM por minuto)
            frecuenciaCardiaca += (int)(Math.random() * 2 + 1);

            // Detectar alertas segun los rangos
            if (frecuenciaCardiaca >= alertaGrave) {
                complicaciones = "ALERTA GRAVE: Frecuencia cardiaca cerca del limite maximo.";
            } else if (frecuenciaCardiaca >= alertaLeve) {
                complicaciones = "ALERTA LEVE: Frecuencia cardiaca fuera del rango esperado.";
            } else {
                complicaciones = "Ninguna";
            }

            // Si la frecuencia supera el rango maximo, detener la simulacion y dar mensaje de taquicardia
            if (frecuenciaCardiaca > fcm) {
                System.out.printf("\nEl paciente ha sufrido una taquicardia. Simulacion terminada.\n");
                complicaciones = "PACIENTE SUFRIO UNA TAQUICARDIA";
                taquicardiaDetectada = true;
                break;
            }

            // Almacenar los resultados del minuto
            historial.add(String.format("%-8d %-25s %-12d %-50s %-25s", minuto, frecuenciaCardiaca + " BPM", minuto, complicaciones, taquicardiaDetectada ? "Si" : "No"));
            // Mostrar los resultados del minuto
            System.out.printf("%-8d %-25s %-12d %-50s %-25s\n", minuto, frecuenciaCardiaca + " BPM", minuto, complicaciones, taquicardiaDetectada ? "Si" : "No");

            // Preguntar si continuar
            System.out.println("\nPresione Enter para continuar o cualquier otra tecla para finalizar...");
            String input = sc.nextLine();
            if (!input.equals("")) {
                continuar = false; // Finalizar simulacion si no se presiona Enter
            }
            
            minuto++;
        }

        // Mostrar la tabla completa con todos los resultados
        System.out.println("\nTabla de resultados finales:");
        System.out.println("Minuto    Frecuencia cardiaca      Duracion (min)        Complicaciones                 Taquicardia");
        System.out.println("----------------------------------------------------------------------------------------------");

        // Imprimir todas las filas almacenadas en el historial con el formato de tabla
        for (String linea : historial) {
            System.out.println(linea);
        }

        // Mostrar mensaje final
        System.out.println("\nPaciente entro en Taquicardia. Ejercicio finalizado. ¡Gracias!");
    }
}
