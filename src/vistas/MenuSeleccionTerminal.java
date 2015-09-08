/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.util.InputMismatchException;
import java.util.Scanner;
import modelos.Bus;
import modelos.Pasajero;

/**
 *
 * @author ignacio
 */
public class MenuSeleccionTerminal {

	private final static String decorador
		= "============================================";

	public static void menu() {
		int opcion = 0;
		boolean continuar = true;
		boolean formatoInadecuado;
		Scanner sc = new Scanner(System.in);
		//Crea un nuevo bus con un valor de pasaje inicial de $500
		Bus bus = new Bus(500);
		while (continuar) {
			System.out.println("");
			System.out.println(decorador);
			System.out.println("Elija una opción:");
			System.out.println("1.- Establecer valor del pasaje.");
			System.out.println("2.- Agregar pasajero.");
			System.out.println("3.- Ver ganancia.");
			System.out.println("4.- Salir.");
			System.out.print("Inserte una opción: ");
			opcion = validarNumero(sc);
			switch (opcion) {
				case 1:
					System.out.println(decorador);
					System.out.print("Inserte el valor del pasaje: ");
					bus.setValorPasaje(validarNumero(sc));
					System.out.println(decorador);
					System.out.println("El valor del pasaje se dejó en " 
						+ bus.getValorPasaje());
					break;
				case 2:
					System.out.println(decorador);
					String nombre;
					boolean descuento,
					 disponible = false;
					int asiento;
					System.out.println("¿Cómo se llama nuestro pasajero?");
					nombre = sc.next();
					System.out.println("¿Tiene alguna clase de descuento? s/n: ");
					descuento = sc.next().equals("s");
					do {
						System.out.println("¿En donde se sentará?");
						asiento = validarNumero(sc);
						disponible = bus.agregarPasajeros(
							asiento, new Pasajero(nombre, descuento));
						if (!disponible) {
							System.out.print("Asiento ya ocupado... "
								+ "¿Desea intentarlo otra vez? s/n: ");
							if (!sc.next().equals("s")) {
								break;
							}
						}
					} while (!disponible);
					System.out.println(decorador);
					break;
				case 3:
					System.out.println(decorador);
					System.out.println("La ganancia hasta el momento es de: "
						+ bus.verGanancia());
					System.out.println(decorador);
					break;
				case 4:
					System.out.println(decorador);
					continuar = false;
					break;

			}
		}
	}

	private static int validarNumero(Scanner sc) {
		boolean formatoInadecuado = true;
		int opcion = 0;
		while (formatoInadecuado) {
			try {
				opcion = sc.nextInt();
				formatoInadecuado = false;
			} catch (InputMismatchException ex) {
				System.out.println("");
				System.out.print("Esta mal escrito el número, escríbalo nuevamente: ");
			}
		}
		return opcion;
	}
}
