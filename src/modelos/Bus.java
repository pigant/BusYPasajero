/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author ignacio
 */
public class Bus {

	private int valorPasaje;
	private Pasajero pasajeros[];
	private int ganancia;

	public Bus(int valorPasaje) {
		this.valorPasaje = valorPasaje;
		pasajeros = new Pasajero[40];
	}

	public boolean agregarPasajeros(int numeroAsiento, Pasajero pasajero) {
		boolean disponibilidad;
		System.out.println("El numero que entro es de : " + numeroAsiento);
		if (numeroAsiento > 40 || numeroAsiento < 0) {
			System.out.println("El asiento no existe en el bus.");
			disponibilidad = false;
		} else if (pasajeros[numeroAsiento - 1] == null) {
			pasajeros[numeroAsiento - 1] = pasajero;
			//Agrega el pago del pasajero a la ganancia total
			if (pasajero.isDescuento()) {
				ganancia += valorPasaje * 0.9;
			} else {
				ganancia += valorPasaje;
			}
			System.out.println(
				"El pasajero " + pasajero.getNombre()
				+ " se sento en el asiento " + numeroAsiento
				+ " y su estado de descuento es: " + pasajero.isDescuento());
			disponibilidad = true;
		} else {
			System.out.println("Asiento ya ocupado por "
				+ pasajeros[numeroAsiento - 1].getNombre()
				+ ", intente con otro número!");
			disponibilidad = false;
		}
		return disponibilidad;

	}

	public int verGanancia() {
		return ganancia;
	}

	public int getValorPasaje() {
		return valorPasaje;
	}

	public void setValorPasaje(int valorPasaje) {
		this.valorPasaje = valorPasaje;
	}

}
