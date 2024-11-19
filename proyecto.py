import tkinter as tk
from tkinter import messagebox
from datetime import datetime
import random

# Función para calcular la edad a partir de la fecha de nacimiento
def calcular_edad(fecha_nacimiento):
    try:
        dia, mes, anio = map(int, fecha_nacimiento.split('/'))
        fecha_nacimiento = datetime(anio, mes, dia)
    except ValueError:
        messagebox.showerror("Error", "Fecha de nacimiento no válida. Por favor ingrese en formato dd/mm/aaaa.")
        return None

    fecha_actual = datetime.today()
    edad = fecha_actual.year - fecha_nacimiento.year
    if (fecha_actual.month < mes) or (fecha_actual.month == mes and fecha_actual.day < dia):
        edad -= 1
    return edad

# Función para determinar el grupo de edad
def determinar_grupo_edad(edad):
    if edad <= 1:
        return "Neonato"
    elif edad <= 12:
        return "Pediátrico"
    elif edad <= 17:
        return "Adolescente"
    elif edad <= 64:
        return "Adulto"
    else:
        return "Adulto Mayor"

# Función para iniciar la simulación
def iniciar_simulacion():
    nombre = entry_nombre.get()
    fecha_nacimiento = entry_fecha.get()

    # Calcular edad
    edad = calcular_edad(fecha_nacimiento)
    if edad is None:
        return

    # Determinar el grupo de edad
    grupo_edad = determinar_grupo_edad(edad)

    # Calcular FCM y rango esperado
    fcm = 220 - edad
    rango_min = fcm * 0.50
    rango_max = fcm * 0.85

    # Mostrar resultados iniciales
    result_var.set(f"Nombre: {nombre}\nEdad: {edad} años\nGrupo de Edad: {grupo_edad}\n"
                   f"Frecuencia Cardíaca Máxima: {fcm} BPM\nRango Esperado: {int(rango_min)} - {int(rango_max)} BPM")

    # Iniciar la simulación
    historial = []
    minuto = 1
    taquicardia_detectada = False
    alerta_leve = int(rango_max)
    alerta_grave = int(fcm * 0.90)
    frecuencia_cardiaca = random.randint(int(rango_min), int(rango_max))
    complicaciones = "Ninguna"

    while not taquicardia_detectada:
        frecuencia_cardiaca += random.randint(1, 2)

        if frecuencia_cardiaca >= alerta_grave:
            complicaciones = "ALERTA GRAVE: Frecuencia cardiaca cerca del límite máximo."
        elif frecuencia_cardiaca >= alerta_leve:
            complicaciones = "ALERTA LEVE: Frecuencia cardiaca fuera del rango esperado."
        else:
            complicaciones = "Ninguna"

        if frecuencia_cardiaca > fcm:
            taquicardia_detectada = True
            complicaciones = "PACIENTE SUFRIÓ UNA TAQUICARDIA"

        # Guardar el minuto en el historial
        historial.append([minuto, frecuencia_cardiaca, complicaciones])
        minuto += 1

    # Mostrar historial de la simulación en el área de texto
    historial_text.delete(1.0, tk.END)
    historial_text.insert(tk.END, f"Simulación de Ejercicio\n")
    historial_text.insert(tk.END, f"{'Minuto':<8} {'Frecuencia Cardiaca':<20} {'Complicaciones'}\n")
    historial_text.insert(tk.END, "-"*50 + "\n")
    
    for minuto, frecuencia, complicacion in historial:
        historial_text.insert(tk.END, f"{minuto:<8} {frecuencia} BPM   {complicacion}\n")

    historial_text.insert(tk.END, "\nSimulación Finalizada. ¡Gracias!")

# Configuración de la ventana principal
ventana = tk.Tk()
ventana.title("Simulación de Ejercicio")

# Etiquetas y entradas para los datos del paciente
tk.Label(ventana, text="Nombre del paciente:").grid(row=0, column=0, padx=10, pady=10, sticky="w")
entry_nombre = tk.Entry(ventana, width=30)
entry_nombre.grid(row=0, column=1, padx=10, pady=10)

tk.Label(ventana, text="Fecha de nacimiento (dd/mm/aaaa):").grid(row=1, column=0, padx=10, pady=10, sticky="w")
entry_fecha = tk.Entry(ventana, width=15)
entry_fecha.grid(row=1, column=1, padx=10, pady=10)

# Botón para iniciar la simulación
boton_simulacion = tk.Button(ventana, text="Iniciar Simulación de Ejercicio", command=iniciar_simulacion)
boton_simulacion.grid(row=2, column=0, columnspan=4, padx=10, pady=20)

# Etiqueta para mostrar los resultados
result_var = tk.StringVar()
tk.Label(ventana, textvariable=result_var, justify=tk.LEFT, width=60, anchor="w", relief="solid", padx=10, pady=10).grid(row=3, column=0, columnspan=4, padx=10, pady=10)

# Área de texto para mostrar el historial de la simulación
historial_text = tk.Text(ventana, width=70, height=15, wrap=tk.WORD)
historial_text.grid(row=4, column=0, columnspan=4, padx=10, pady=10)

# Ejecutar la ventana
ventana.mainloop()
