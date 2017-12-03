package fr.vsct.xspeedit.carton;

import lombok.extern.slf4j.Slf4j;

/**
 * Fabrique de carton. La logique de création précise la capacité maximale du
 * carton et pourrait être couplée à un système gestion (stockage, tailles
 * disponibles, approvisionnement,...).
 */
@Slf4j
public class CartonFactory {

	/**
	 * Capacité d'un carton.
	 */
	public static final int CAPACITE = 10;

	/**
	 * Identifiants des cartons créés.
	 */
	private static int sequence = 0;

	/**
	 * Créér un nouveau carton d'une capacite de {@link CartonFactory#CAPACITE}.
	 * 
	 * @return
	 */
	public static Carton creerCarton() {
		log.info("Création d'un nouveau carton.");
		return new Carton(sequence++, CAPACITE);
	}
}
