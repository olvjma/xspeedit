package fr.vsct.xspeedit.robot;

import java.util.Collection;

import fr.vsct.xspeedit.article.Article;
import fr.vsct.xspeedit.carton.Carton;

/**
 * Contrat d'interface pour la fonction d'emballage de carton.
 */
public interface Robot {

	/**
	 * Emballer un ensemble d'articles dans un minimum de carton.
	 *
	 * @param articles
	 *            ensemble d'articles à emballer.
	 * @return liste de cartons utilisés.
	 */
	Collection<Carton> emballer(final Collection<Article> articles);
}
