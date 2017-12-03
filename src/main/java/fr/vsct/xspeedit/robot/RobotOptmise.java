package fr.vsct.xspeedit.robot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import fr.vsct.xspeedit.article.Article;
import fr.vsct.xspeedit.article.ArticleUtils;
import fr.vsct.xspeedit.carton.Carton;
import fr.vsct.xspeedit.carton.CartonFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * Robot d'emballage optimisé.
 * <p>
 * L'optimoisation réside dans le tri des articles avant emballage. Ils sont
 * triés dans l'ordre décoissant de taille.
 * <p>
 * Son fonctionnement est simple : - tri des articles - parcours des article un
 * a un et pour chaque article - ajout dans l'article dans le Carton qui le
 * permet, qui dispose d'assez d'espace libre - si pas de Carton alors on en cré
 * un nouveau et on y ajoute l'article
 */
@Slf4j
public class RobotOptmise implements Robot {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Carton> emballer(final Collection<Article> articles) {
		log.debug("Emballage des articles suivants : {}", articles);
		final List<Article> articlesPrepares = trierArticles(articles);
		final Collection<Carton> lotCarton = new ArrayList<>();
		for (final Article article : articlesPrepares) {
			remplirCartonExistantOuCreerUnNouveau(lotCarton, article);
		}
		log.debug("Emballage terminé");
		return lotCarton;
	}

	/**
	 * Ordonner les article par taille du plus grand vers le plus petit.
	 * <p>
	 * C'est via ce traitement que ce robot est optimisé.
	 *
	 * @param articles
	 * @return
	 */
	private List<Article> trierArticles(final Collection<Article> articles) {
		final ArrayList<Article> articlesPrepares = new ArrayList<>(articles);
		Collections.sort(articlesPrepares, ArticleUtils.COMPARATOR_TAILLE_DESC);
		log.trace("Articles réordonnés pour optimisation de l'emballage : {}", articles);
		return articlesPrepares;
	}

	/**
	 * Ajouter l'article à un Carton existant ou en créer un nouveau Carton.
	 * <p>
	 * Le nouveau Carton est ajouté au lot.
	 *
	 * @param cartons
	 * @param article
	 */
	private void remplirCartonExistantOuCreerUnNouveau(final Collection<Carton> cartons, final Article article) {
		Carton carton = getCartonSuffisamentGrand(cartons, article.getTaille());
		if (carton == null) {
			// aucun Carton disponible, on en crée un nouveau
			carton = CartonFactory.creerCarton();
			cartons.add(carton);
		}
		carton.addArticle(article);
		log.debug("Ajout de l'article {} dans le Carton {}", article, carton);
	}

	/**
	 * Récupérer un Carton avec assez d'espace libre pour contenir l'article.
	 *
	 * @param cartons
	 * @param tailleArticle
	 * @return
	 */
	private Carton getCartonSuffisamentGrand(final Collection<Carton> cartons, final int tailleArticle) {
		Iterator<Carton> itCartons = cartons.iterator();
		Carton carton = null;
		while (itCartons.hasNext() && carton == null) {
			final Carton cartonExistant = itCartons.next();
			if (cartonExistant.getEspaceLibre() >= tailleArticle) {
				carton = cartonExistant;
			}
		}
		return carton;
	}

}
