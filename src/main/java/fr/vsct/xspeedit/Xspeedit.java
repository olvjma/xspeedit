package fr.vsct.xspeedit;

import java.util.Collection;
import java.util.Scanner;

import fr.vsct.xspeedit.article.Article;
import fr.vsct.xspeedit.article.ArticleParser;
import fr.vsct.xspeedit.carton.Carton;
import fr.vsct.xspeedit.robot.Robot;
import fr.vsct.xspeedit.robot.RobotOptmise;
import lombok.extern.slf4j.Slf4j;

/**
 * Lanceur de l'application.
 * <p>
 * __Information__ Ce lanceur demandera à l'utilisation de saisir la liste des
 * articles dans la console.
 * <p>
 * __Exemple__ Articles : 163841689525773 Robot actuel :
 * 163/8/41/6/8/9/52/5/7/73 => 10 cartons utilisés Robot optimisé:
 * 163/82/46/19/8/55/73/7 => 8 cartons utilisés
 */
@Slf4j
public class Xspeedit {

	/**
	 * Chaine d'articles:
	 * 
	 * @param args
	 *            aucun
	 */
	public static void main(final String[] args) {

		try (final Scanner listeArticles = new Scanner(System.in);) {
			System.out.print("Articles : ");
			final String articlesSerialises = listeArticles.nextLine();

			final Collection<Article> articlesAEmballer = ArticleParser.parse(articlesSerialises);
			final Robot robotEmballage = new RobotOptmise();
			final Collection<Carton> listeCartons = robotEmballage.emballer(articlesAEmballer);

			log.info("Répartition des articles de taille " + articlesSerialises + " dans " + listeCartons.size()
					+ " cartons:");
			for (Carton carton : listeCartons) {
				log.info("{}", carton);
			}
		} catch (final FormatException e) {
			log.error(
					"La chaîne de caractéres représentant les articles, doit être composée uniquement de chiffres allant de 1 à 9",
					e);

		}
	}

}
