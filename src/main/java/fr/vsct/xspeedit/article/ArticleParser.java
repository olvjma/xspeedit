package fr.vsct.xspeedit.article;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.CharUtils;

import fr.vsct.xspeedit.FormatException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe en charge de parser une chaine de caractéres en une liste d'article.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleParser {

	/**
	 * Pattern de validation. n caractères de 1 à 9.
	 */
	private static final String PATTERN_VALIDATOR = "[1-9]+";

	/**
	 * Permet de transformer une chaine de caractéres représentant des articles
	 * en une liste d'{@link Article}.
	 *
	 * @param articlesSerialises
	 *            une chaine de caractéres représentant les tailles articles
	 * @return Une liste d'article représentés par la chaine de caractéres
	 * @throws FormatException
	 *             la chaine de caractére passée en entrée n'est pas valide.
	 */
	public static List<Article> parse(final String articlesSerialises) throws FormatException {
		log.debug("Parsing de {}", articlesSerialises);
		validationFormat(articlesSerialises);
		final ArrayList<Article> articles = new ArrayList<>(articlesSerialises.length());
		for (final char tailleChar : articlesSerialises.toCharArray()) {
			final int taille = CharUtils.toIntValue(tailleChar);
			final Article article = new Article(taille);
			articles.add(article);
			log.trace("Création et ajout de l'article {}", article);
		}
		return articles;
	}

	/**
	 * Application du pattern.
	 * 
	 * @param articlesSerialises
	 * @throws FormatException
	 */
	private static void validationFormat(final String articlesSerialises) throws FormatException {
		if (articlesSerialises == null || !articlesSerialises.matches(PATTERN_VALIDATOR)) {
			log.trace("Serialisation d'article invalide : {}", articlesSerialises);
			throw new FormatException();
		}
	}

}
