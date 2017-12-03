package fr.vsct.xspeedit.carton;

import java.util.ArrayList;
import java.util.List;

import fr.vsct.xspeedit.article.Article;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Représentation d'un carton destiné à contenir des articles.
 */
@Getter
@Setter
@Slf4j
@ToString(exclude = "capacite")
public class Carton {

	/**
	 * Identifiant du carton
	 */
	private int id;

	/**
	 * Capacite du carton.
	 */
	private int capacite;

	/**
	 * Espace encore disponible dans le carton.
	 */
	private int espaceLibre;

	/**
	 * Constructeur d'un carton précisant sa capacité.
	 * 
	 * @param id
	 *            L'identifiant du carton.
	 * @param capacite
	 *            La capacité du carton.
	 */
	protected Carton(final int identifiant, final int capacite) {
		this.id = identifiant;
		this.capacite = capacite;
		this.espaceLibre = capacite;
		articles = new ArrayList<>();
	}

	/**
	 * Liste des articles présent dans le carton.
	 */
	private List<Article> articles;

	/**
	 * Ajout d'un article dans la liste et décrémentation de l'espace disponible
	 * selon la taille de l'article.
	 * 
	 * @param article
	 */
	public boolean addArticle(final Article article) {
		if (article != null && article.getTaille() > 0 && this.articles.size() < this.capacite
				&& this.espaceLibre > 0) {
			espaceLibre -= article.getTaille();
			return this.articles.add(article);
		} else {
			log.error("Impossible d'ajouter l'article {} dans le carton {}", article, this);
			return false;
		}
	}

}
