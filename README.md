
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

