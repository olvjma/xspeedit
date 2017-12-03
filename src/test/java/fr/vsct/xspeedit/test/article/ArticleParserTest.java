package fr.vsct.xspeedit.test.article;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import fr.vsct.xspeedit.FormatException;
import fr.vsct.xspeedit.article.Article;
import fr.vsct.xspeedit.article.ArticleParser;

public class ArticleParserTest {

	/**
	 * Tests ds cas en erreur.
	 *
	 * @param articleSerialise
	 * @throws MauvaisFormatageException
	 */

	@Test(expected = FormatException.class)
	public void testErreur() throws FormatException {
		ArticleParser.parse(null);
		ArticleParser.parse("ABD");
		ArticleParser.parse("12A");
		ArticleParser.parse("");
		ArticleParser.parse("0");
	}

	/**
	 * Tests des cas passant.
	 *
	 * @param articleSerialise
	 * @param sortie
	 * @throws MauvaisFormatageException
	 */
	@Test
	public void testFormatage() throws FormatException {
		List<Article> articles = ArticleParser.parse("1");
		assertEquals(1, articles.size());
		assertEquals(1, articles.get(0).getTaille());
		articles = ArticleParser.parse("19");
		assertEquals(2, articles.size());
		assertEquals(1, articles.get(0).getTaille());
		assertEquals(9, articles.get(1).getTaille());
	}

}
