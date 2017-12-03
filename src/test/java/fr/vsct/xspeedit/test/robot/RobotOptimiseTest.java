package fr.vsct.xspeedit.test.robot;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.vsct.xspeedit.FormatException;
import fr.vsct.xspeedit.article.Article;
import fr.vsct.xspeedit.article.ArticleParser;
import fr.vsct.xspeedit.carton.Carton;
import fr.vsct.xspeedit.robot.RobotOptmise;

public class RobotOptimiseTest {

	private RobotOptmise robotOptmise;

	@Before
	public void init() {
		robotOptmise = new RobotOptmise();
	}

	@Test
	public void testRobot() throws FormatException {
		// Test exemple référérence
		List<Article> articles = ArticleParser.parse("163841689525773");
		Collection<Carton> cartons = robotOptmise.emballer(articles);
		assertEquals(8, cartons.size());
		// Test remplissage max sur les 8 cartons
		articles = ArticleParser.parse("16384168952577314");
		cartons = robotOptmise.emballer(articles);
		assertEquals(8, cartons.size());
		// 1 carton article supplémentaire => 1 carton supplémentaire
		articles = ArticleParser.parse("163841689525773141");
		cartons = robotOptmise.emballer(articles);
		assertEquals(9, cartons.size());
	}

}
