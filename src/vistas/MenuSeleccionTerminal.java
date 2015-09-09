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

	//Constantes usadas en el menu
	private final static int ESTABLECER_PASAJE = 1;
	private final static int AGREGAR_PASAJERO = 2;
	private final static int VER_GANANCIA = 3;
	private final static int SALIR = 4;

	public static void menu() {
		//La opcion elegida en el menu
		int opcion = 0;
		//Para mantenerse en el menu
		boolean continuar = true;
		Scanner sc = new Scanner(System.in);
		//Crea un nuevo bus con un valor de pasaje inicial de $500
		Bus bus = new Bus(500);
		//Entra al menu
		while (continuar) {
			System.out.println("");
			decorar();
			System.out.println("Elija una opción:\n"
				+ "1.- Establecer valor del pasaje.\n"
				+ "2.- Agregar pasajero.\n"
				+ "3.- Ver ganancia.\n"
				+ "4.- Salir.\n"
				+ "Inserte una opción: ");
			opcion = validarNumero(sc);
			switch (opcion) {
				case ESTABLECER_PASAJE:
					decorar();
					System.out.print("Inserte el valor del pasaje: ");
					bus.setValorPasaje(validarNumero(sc));
					decorar();
					System.out.println("El valor del pasaje se dejó en "
						+ bus.getValorPasaje());
					break;
				case AGREGAR_PASAJERO:
					String nombre;
					boolean descuento,
					 disponible;
					int asiento;
					decorar();
					System.out.println("¿Cómo se llama nuestro pasajero?");
					nombre = sc.next();
					//Revisa que se escriba s o si, en ese caso, el pasajero
					//tiene descuento
					System.out.println("¿Tiene alguna clase de descuento? s/n: ");
					String confirmacion = sc.next();
					descuento = confirmacion.equals("s") || confirmacion.equals("si");
					//Consulta en donde se sentara el pasajero, en caso de
					//no existir el asiento, o este este ocupado, consultara
					//si desea escribir nuevamente otro asiento
					do {
						System.out.println("¿En donde se sentará?");
						asiento = validarNumero(sc);
						disponible = bus.agregarPasajeros(
							asiento, new Pasajero(nombre, descuento));
						//Si no esta disponible, avisa y consulta el curso de accion
						if (!disponible) {
							System.out.print("Asiento ya ocupado... "
								+ "¿Desea intentarlo otra vez? s/n: ");
							if (!sc.next().equals("s")) {
								break;
							}
						}
					} while (!disponible);
					decorar();
					break;
				case VER_GANANCIA:
					decorar();
					System.out.println("La ganancia hasta el momento es de: "
						+ bus.verGanancia());
					decorar();
					break;
				case SALIR:
					continuar = false;
					break;

			}
		}
		decorar();
		System.out.println("Cerrando el programa...");
		decorar();
	}

	private static void decorar() {
		String decorador = "============================================";
		System.out.println(decorador);
	}

	private static int validarNumero(Scanner sc) {
		boolean formatoInadecuado = true;
		int opcion = 0;
		while (formatoInadecuado) {
			try {
				opcion = sc.nextInt();
				//En caso de que sea algun numero negativo, vuelve a preguntar
				if (opcion < 0) {
					System.out.println("\nNo se aceptan numero negativos.\n"
						+ "escribalo nuevamente");
					continue;
				}
				//Si paso la validacion, permite salir del while
				formatoInadecuado = false;
			} //Captura la excepcion cuando se escribe un mal formato, por ejemplo
			//letras, al pedir numeros
			catch (InputMismatchException ex) {
				System.out.print("\nEsta mal escrito el número, escríbalo nuevamente: ");
			}
		}
		return opcion;
	}
}
