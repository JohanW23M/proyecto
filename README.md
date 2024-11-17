# ¡Perfecto! Ahora que tenemos un buen diseño para la simulación, vamos a hacer un **paso a paso detallado** para que tengas una guía clara de lo que vamos a hacer, desde la lógica hasta el código y las salidas esperadas. Esto te permitirá tener un panorama claro de cómo se estructura todo el programa.

---

### **Paso a Paso del Proyecto**

### **1. Lógica General del Programa**

El programa se estructura en varias partes clave:

1. **Ingreso de Datos del Usuario:**
   - El usuario ingresa su nombre y fecha de nacimiento (día, mes, año).
   - Con esta información, calcularemos la **edad**, la **frecuencia cardiaca máxima** y el **rango esperado** de la frecuencia cardiaca, que son los parámetros clave para la simulación.

2. **Cálculos Iniciales:**
   - **Edad**: Se calcula restando el año de nacimiento del año actual.
   - **Frecuencia Cardiaca Máxima**: Usamos la fórmula de la Asociación Estadounidense del Corazón (AHA): `220 - edad`.
   - **Rango Esperado de Frecuencia Cardiaca**: Este es el 50% a 85% de la frecuencia cardiaca máxima, según las recomendaciones de salud.

3. **Simulación del Ejercicio:**
   - Inicia con el primer **Enter** del usuario.
   - Se simula el ejercicio durante cada minuto, mostrando la frecuencia cardiaca generada aleatoriamente dentro del rango esperado (50% - 85% de la frecuencia cardiaca máxima).
   - El ejercicio continúa hasta que el usuario presiona el segundo **Enter**, momento en el cual la simulación termina.

4. **Formato de Salida:**
   - El programa debe mostrar los datos iniciales del usuario (nombre, edad, frecuencia cardiaca máxima, rango esperado) en una tabla.
   - Luego, durante la simulación, cada minuto debe mostrarse en formato de tabla con las columnas: **Minuto**, **Frecuencia Cardiaca** y **Duración (minuto)**.

---

### **2. Diseño del Código**

#### **Clase `Persona`**
Esta clase representará a la persona, incluyendo su nombre, fecha de nacimiento, y métodos para calcular la edad, la frecuencia cardiaca máxima y el rango esperado.

```java
public class Persona {
    private String nombre;
    private int diaNacimiento, mesNacimiento, anoNacimiento;

    public Persona(String nombre, int dia, int mes, int ano) {
        this.nombre = nombre;
        this.diaNacimiento = dia;
        this.mesNacimiento = mes;
        this.anoNacimiento = ano;
    }

    // Calcular la edad de la persona
    public int calcularEdad() {
        int anoActual = 2024;  // Aquí puedes cambiar el año a lo que sea el año actual
        return anoActual - this.anoNacimiento;
    }

    // Calcular la frecuencia cardiaca máxima según la fórmula de AHA
    public int calcularFrecuenciaMaxima() {
        return 220 - calcularEdad();
    }

    // Calcular el rango esperado de frecuencia cardiaca (50%-85% de la frecuencia máxima)
    public String calcularRangoEsperado() {
        int max = calcularFrecuenciaMaxima();
        int min = (int) (max * 0.50);
        int maxRango = (int) (max * 0.85);
        return min + " - " + maxRango;
    }

    public String getNombre() {
        return nombre;
    }
}
```

#### **Clase `Ejercicio`**
Esta clase simula el ejercicio, realizando el seguimiento de la frecuencia cardiaca durante la simulación.

```java
import java.util.Random;

public class Ejercicio {
    private int duracion;  // Duración en minutos
    private int frecuenciaCardiaca;

    public Ejercicio(int duracion) {
        this.duracion = duracion;
    }

    // Simula la frecuencia cardiaca en cada minuto dentro del rango de 50%-85% de la máxima
    public void realizarEjercicio(int min, int max) {
        Random rand = new Random();
        this.frecuenciaCardiaca = rand.nextInt((max - min) + 1) + min;  // Genera un número aleatorio dentro del rango
    }

    // Obtener la frecuencia cardiaca actual del minuto
    public int getFrecuenciaCardiaca() {
        return this.frecuenciaCardiaca;
    }
}
```

#### **Clase `Main`**
En esta clase se coordina la entrada de datos, la simulación del ejercicio y la salida en formato de tabla.

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear un escáner para leer entradas del usuario
        Scanner scanner = new Scanner(System.in);

        // Ingreso de los datos de la persona
        System.out.println("Ingrese su nombre:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese su fecha de nacimiento (día mes año):");
        int dia = scanner.nextInt();
        int mes = scanner.nextInt();
        int ano = scanner.nextInt();

        // Crear el objeto persona
        Persona persona = new Persona(nombre, dia, mes, ano);

        // Calcular la edad, la frecuencia cardiaca máxima y el rango esperado
        int edad = persona.calcularEdad();
        int frecuenciaMaxima = persona.calcularFrecuenciaMaxima();
        String rangoEsperado = persona.calcularRangoEsperado();

        // Mostrar la información de la persona en formato de tabla
        System.out.printf("\n%-20s: %s\n", "Nombre", persona.getNombre());
        System.out.printf("%-20s: %d años\n", "Edad", edad);
        System.out.printf("%-20s: %d BPM\n", "Frecuencia cardiaca máxima", frecuenciaMaxima);
        System.out.printf("%-20s: %s BPM\n", "Rango esperado", rangoEsperado);

        // Pedir al usuario presionar Enter para comenzar el ejercicio
        System.out.println("\nPresione Enter para comenzar el ejercicio...");
        scanner.nextLine();  // Limpiar el buffer
        scanner.nextLine();  // Esperar que el usuario presione Enter

        // Simulación del ejercicio
        System.out.println("\nSimulación del ejercicio iniciada...");

        // Encabezado de la tabla
        System.out.printf("\n%-10s%-25s%-25s\n", "Minuto", "Frecuencia cardiaca", "Duración (min)");
        System.out.println("----------------------------------------------------");

        Ejercicio ejercicio = new Ejercicio(0);  // 0 minutos porque ahora es indefinido

        int minuto = 1;
        boolean ejercicioEnCurso = true;

        // Mientras el usuario no presione Enter para terminar
        while (ejercicioEnCurso) {
            // Simulamos la frecuencia cardiaca en cada minuto del ejercicio
            ejercicio.realizarEjercicio((int)(frecuenciaMaxima * 0.50), (int)(frecuenciaMaxima * 0.85));

            // Mostrar el estado de la persona cada minuto
            System.out.printf("%-10d%-25d%-25d\n", minuto, ejercicio.getFrecuenciaCardiaca(), minuto);

            // Pausar un segundo para simular el paso del tiempo
            try {
                Thread.sleep(1000); // Pausa de 1 segundo (simula el paso de tiempo real)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Incrementar el minuto
            minuto++;

            // Verificar si el usuario presiona Enter para detener el ejercicio
            if (System.in.available() > 0) { // Si hay una entrada disponible
                String input = scanner.nextLine();
                if (input.equals("")) {
                    ejercicioEnCurso = false; // Si presionan Enter, terminamos la simulación
                }
            }
        }

        // Finalizar ejercicio
        System.out.println("\nEjercicio finalizado. ¡Gracias!");
    }
}
```

---

### **3. Salidas Esperadas**

#### **Entrada de Datos (Interacción del Usuario):**

1. El programa solicita al usuario ingresar su **nombre**:
    ```
    Ingrese su nombre:
    Juan Pérez
    ```

2. Luego, solicita la **fecha de nacimiento**:
    ```
    Ingrese su fecha de nacimiento (día mes año):
    15 8 1993
    ```

#### **Cálculos Iniciales (Mostrados en Formato de Tabla):**

El programa muestra la información calculada:

```
Nombre               : Juan Pérez
Edad                 : 31 años
Frecuencia cardiaca máxima: 189 BPM
Rango esperado       : 94 - 160 BPM
```

#### **Simulación del Ejercicio:**

Al presionar **Enter** para comenzar, la simulación comienza. El programa muestra la tabla en cada minuto del ejercicio:

```
Simulación del ejercicio iniciada...

Minuto    Frecuencia cardiaca      Duración (min)
----------------------------------------------------
1         125                      1
2         135                      2
3         142                      3
4         148                      4
5         137                      5
...
```

#### **Finalización del Ejercicio:**

Cuando el usuario presiona **Enter** nuevamente, la simulación termina y muestra el siguiente mensaje:

```
Ejercicio finalizado. ¡Gracias!
```

---

### **Conclusión**

Con este paso a paso, hemos cubierto toda la lógica y el flujo del programa. El código está estructurado de manera que sea dinámico y flexible, permitiendo que el ejercicio se simule sin una duración fija.
