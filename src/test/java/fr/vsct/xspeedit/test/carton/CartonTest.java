package fr.vsct.xspeedit.test.carton;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.vsct.xspeedit.article.Article;
import fr.vsct.xspeedit.carton.Carton;
import fr.vsct.xspeedit.carton.CartonFactory;

public class CartonTest {

	@Test
	public void testFactory() {
		Carton carton = CartonFactory.creerCarton();
		assertNotNull(carton);
		// Le carton a la bonne capacite
		assertEquals(CartonFactory.CAPACITE, carton.getCapacite());
		// A la création, l'espace disponible est égal à la capacité
		assertEquals(CartonFactory.CAPACITE, carton.getEspaceLibre());
	}

	@Test
	public void testAjoutArticles() {
		Carton carton = CartonFactory.creerCarton();
		assertNotNull(carton);
		Article article = new Article(0);// Testé dans le parser, mais pourrait
											// provenir d'une autre source.
		assertEquals(0, article.getTaille());
		assertFalse(carton.addArticle(article));
		article = new Article(1);
		assertEquals(1, article.getTaille());
		assertTrue(carton.addArticle(article));
		// La capacité n'a pas bougé
		assertEquals(CartonFactory.CAPACITE, carton.getCapacite());
		// L'espace libre a diminué de 1
		assertEquals(9, carton.getEspaceLibre());
		article = new Article(9);
		assertEquals(9, article.getTaille());
		assertTrue(carton.addArticle(article));
		// La capacité n'a pas bougé
		assertEquals(CartonFactory.CAPACITE, carton.getCapacite());
		// L'espace libre a diminué de 9. plus d'espace
		assertEquals(0, carton.getEspaceLibre());
		// Impossible d'ajouter un article dans ce carton
		article = new Article(1);
		assertFalse(carton.addArticle(article));
	}
}
