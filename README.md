
## **Paso a Paso del Proyecto: Monitoreo de Frecuencia Cardíaca**

---

### **1. Lógica General del Proyecto**

El proyecto tiene como objetivo simular un **monitoreo de frecuencia cardíaca durante el ejercicio físico**. A través de este monitoreo, calculamos la **frecuencia cardíaca máxima** y el **rango de frecuencia esperado**, mostramos alertas cuando la frecuencia cardíaca está fuera del rango seguro, y generamos un historial de la simulación.

**Flujo general**:

1. El usuario ingresa sus **datos personales**: nombre y fecha de nacimiento.
2. A partir de la fecha de nacimiento, calculamos la **edad** y determinamos el **grupo de edad**.
3. Calculamos la **frecuencia cardíaca máxima** (FCM) y el **rango esperado** de acuerdo con la edad del usuario.
4. Se genera una **simulación del ejercicio** donde la frecuencia cardíaca se simula de manera aleatoria.
5. Durante la simulación, si la frecuencia cardíaca se acerca a los límites de seguridad, se generan **alertas**.
6. Al finalizar el ejercicio, se muestra un **historial completo** con las frecuencias y las alertas generadas.

---

### **2. Consideraciones de la American Heart Association (AHA)**

Para este proyecto, tomamos las siguientes **consideraciones de la AHA**:

- **Frecuencia Cardíaca Máxima (FCM)**:
  - **Fórmula estándar**: `FCM = 220 - Edad`, donde la **edad** es calculada a partir de la fecha de nacimiento del usuario.
  - Este valor es un **promedio estimado** de la cantidad máxima de pulsos por minuto que un corazón puede alcanzar bajo esfuerzo máximo.

- **Rango Esperado de Frecuencia Cardíaca**:
  - La **AHA** recomienda que la frecuencia cardíaca durante el ejercicio esté entre **50% y 85%** de la **FCM**.
  - Dependiendo de la **edad**, el rango de frecuencia cardíaca esperado puede cambiar.

- **Alertas**:
  - **Alerta moderada**: Si la frecuencia cardíaca supera el **80% de la FCM** pero no llega al 85%, se activa una alerta **moderada**.
  - **Alerta grave**: Si la frecuencia cardíaca supera el **85% de la FCM**, se activa una alerta **grave**.

- **Grupos de Edad**:
  - **Neonato**: 0-28 días
  - **Pediátrico**: 1 mes - 12 años
  - **Adolescente**: 13 - 17 años
  - **Adulto**: 18 - 65 años
  - **Adulto mayor**: 65 años en adelante

---

### **3. Clases y Variables**

- **Clase `Proyecto`**:
  - **Variables**:
    - `nombre`: Almacena el nombre del usuario.
    - `edad`: Almacena la edad calculada a partir de la fecha de nacimiento.
    - `grupoEdad`: Almacena el grupo de edad determinado según la edad del usuario.
    - `fcm`: La frecuencia cardíaca máxima calculada.
    - `rangoMin`: El límite inferior del rango de frecuencia cardíaca esperado.
    - `rangoMax`: El límite superior del rango de frecuencia cardíaca esperado.

- **Métodos**:
  - `calcularEdad()`: Calcula la edad del usuario a partir de su fecha de nacimiento.
  - `determinarGrupoEdad()`: Determina el grupo de edad basado en la edad del usuario.
  - `simulacionEjercicio()`: Simula el ejercicio generando valores aleatorios para la frecuencia cardíaca y mostrando alertas si es necesario.

---

### **4. Detalles del Código**

#### **Ingreso de Datos y Cálculo de Edad**:

- **Código**:

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class proyecto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ingreso de datos personales
        System.out.print("Ingrese su nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese su fecha de nacimiento (día mes año): ");
        int dia = sc.nextInt();
        int mes = sc.nextInt();
        int anio = sc.nextInt();

        // Calcular edad
        int edad = calcularEdad(anio, mes, dia);
        
        // Determinar grupo de edad
        String grupoEdad = determinarGrupoEdad(edad);

        // Mostrar datos iniciales
        System.out.println("\nNombre              : " + nombre);
        System.out.println("Edad                : " + edad + " años");
        System.out.println("Grupo de Edad       : " + grupoEdad);

        // Calcular y mostrar FCM y Rango Esperado
        int fcm = 220 - edad;
        double rangoMin = fcm * 0.50;
        double rangoMax = fcm * 0.85;

        System.out.println("Rango esperado      : " + (int)rangoMin + " - " + (int)rangoMax + " BPM");
        System.out.println("Frecuencia máxima   : " + fcm + " BPM");

        // Iniciar simulación
        System.out.println("\nPresione Enter para comenzar la simulación del ejercicio...");
        sc.nextLine(); // Captura Enter para continuar
        sc.nextLine(); // Otra vez para iniciar

        // Simulación del ejercicio
        simulacionEjercicio(fcm, rangoMin, rangoMax);
    }

    // Método para calcular la edad
    public static int calcularEdad(int anio, int mes, int dia) {
        int anioActual = 2024; 
        int mesActual = 11;    
        int diaActual = 17;    

        int edad = anioActual - anio;
        if (mesActual < mes || (mesActual == mes && diaActual < dia)) {
            edad--;
        }
        return edad;
    }

    // Método para determinar el grupo de edad
    public static String determinarGrupoEdad(int edad) {
        if (edad <= 1) {
            return "Neonato";
        } else if (edad <= 12) {
            return "Pediátrico";
        } else if (edad <= 17) {
            return "Adolescente";
        } else if (edad <= 65) {
            return "Adulto";
        } else {
            return "Adulto mayor";
        }
    }

    // Método para simular el ejercicio
    public static void simulacionEjercicio(int fcm, double rangoMin, double rangoMax) {
        Scanner sc = new Scanner(System.in);
        int minuto = 1;
        boolean continuar = true;

        // Crear lista para almacenar los resultados
        List<String> historial = new ArrayList<>();

        // Mostrar encabezado de la tabla
        System.out.println("\nMinuto    Frecuencia cardiaca      Duración (min)        Complicaciones");
        System.out.println("--------------------------------------------------------------");

        // Bucle de simulación de ejercicio
        while (continuar) {
            int frecuencia = (int) (Math.random() * (fcm - (int)rangoMin)) + (int)rangoMin;
            String complicaciones = "Ninguna";

            // Alertas
            if (frecuencia > fcm * 0.80 && frecuencia <= fcm * 0.85) {
                complicaciones = "ALERTA: La frecuencia cardiaca está a punto de alcanzar su máximo.";
            } else if (frecuencia > fcm * 0.85) {
                complicaciones = "ALERTA GRAVE: Frecuencia cardiaca fuera del rango esperado.";
            }

            // Almacenar los resultados del minuto
            historial.add(String.format("%-10d%-25s%-20d%-25s", minuto, frecuencia + " BPM", minuto, complicaciones));
            // Mostrar los resultados del minuto
            System.out.println(historial.get(historial.size() - 1));

            // Preguntar si continuar
            System.out.println("\nPresione Enter para continuar o cualquier otra tecla para finalizar...");
            String input = sc.nextLine();
            if (!input.equals("")) {
                continuar = false; // Finalizar simulación si no se presiona Enter
            }
            minuto++;
        }

        // Mostrar la tabla completa con todos los resultados
        System.out.println("\nTabla de resultados finales:");
        System.out.println("--------------------------------------------------------------");
        for (String linea : historial) {
            System.out.println(linea);
        }

        // Mostrar mensaje final
        System.out.println("\nEjercicio finalizado. ¡Gracias!");
    }
}

```

---

### **5. Salidas Esperadas:**

#### **Ejemplo de salida al ingreso de datos**:

```
Ingrese su nombre: Johan Wang
Ingrese su fecha de nacimiento (día mes año): 23 06 2006

Nombre              : Johan Wang
Edad                : 18 años
Grupo de Edad       : Adulto
Rango esperado      : 101 - 171 BPM
Frecuencia máxima   : 202 BPM

Presione Enter para comenzar la simulación del ejercicio...
```

#### **Ejemplo de salida durante la simulación**:

```
Minuto    Frecuencia cardiaca      Duración (min)        Complicaciones
----------------------------------------------------
1         111 BPM                  1                       Ninguna
2         119 BPM                  2                       Ninguna
3         123 BPM                  3                       Ninguna
4         150 BPM                  4                       ALERTA: La frecuencia cardiaca está a punto de alcanzar su máximo.
5         160 BPM                  5                       ALERTA GRAVE: Frecuencia cardiaca fuera del rango esperado.
6         170 BPM                  6                       ALERTA GRAVE: Frecuencia cardiaca fuera del rango esperado.
7         155 BPM                  7                       Ninguna

Presione Enter para continuar o cualquier otra tecla para finalizar...
```

#### **Ejemplo de salida al finalizar la

 simulación**:

```
Ejercicio finalizado. ¡Gracias!
```

---

