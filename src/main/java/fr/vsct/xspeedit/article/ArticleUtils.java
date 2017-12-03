package fr.vsct.xspeedit.article;

import java.util.Comparator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Classe utilitaire pour les articles.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleUtils {

	/**
	 * Comparateur d'articles sur leur taille afin de réaliser un classement du
	 * plus grand au plus petit.
	 */
	public static final Comparator<Article> COMPARATOR_TAILLE_DESC = new Comparator<Article>() {
		@Override
		public int compare(final Article a1, final Article a2) {
			return a2.getTaille() - a1.getTaille();
		}
	};
}
