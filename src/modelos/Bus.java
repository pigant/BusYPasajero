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

	/**
	 * Agrega pasajeros al bus, en caso de que el asiento esta ocupado o no
	 * existe, retorna falso, en caso de colocar exitosamente al pasajero en
	 * el bus, retorna verdadero.
	 * @param numeroAsiento
	 * @param pasajero
	 * @return Verdadero, al integrar al pasajero. Falso si no pudo
	 */
	public boolean agregarPasajeros(int numeroAsiento, Pasajero pasajero) {
		//Variable de retorno que avisa si el pasajero pudo o no sentarse
		boolean disponibilidad;
		//Revisa si el asiento especificado existe en el bus
		if (numeroAsiento > 40 || numeroAsiento < 0) {
			System.out.println("El asiento no existe en el bus los asientos "
				+ "disponibles estan ente el 1 y el 40");
			disponibilidad = false;
		} //Revisa que el asiento especificado este vacio
		//viendo que en el arreglo de pasajeros no exista alguien
		else if (pasajeros[numeroAsiento - 1] == null) {
			//Agrega el pasajero al arreglo de pasajeros
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
		} //En caso de que el asiento se encuentre ocupado
		else {
			System.out.println("Asiento ya ocupado por "
				+ pasajeros[numeroAsiento - 1].getNombre()
				+ ", intente con otro número!");
			disponibilidad = false;
		}
		//Retorna si el asiento fue o no ocupado.
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
