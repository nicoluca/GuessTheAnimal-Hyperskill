package animals.tests;

import animals.StringUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {
    // Testing in this package, as Hyperskill messes with the folder structure.

    @Test
    public void testGetArticeBasedOnFirstLetter() {
        assertEquals("an", StringUtil.getArticleBasedOnFirstLetter("Ant"));
        assertEquals("an", StringUtil.getArticleBasedOnFirstLetter("Elephant"));
        assertEquals("a", StringUtil.getArticleBasedOnFirstLetter("Lion"));
    }

    @Test
    public void testFormatAnomalInput() {
        assertEquals("a cat", StringUtil.formatAnimalInput("the cat"));
        assertEquals("an elephant", StringUtil.formatAnimalInput("an elephant"));
        assertEquals("a elephant", StringUtil.formatAnimalInput("a elephant"));
        assertEquals("an elephant", StringUtil.formatAnimalInput("the Elephant"));
    }

    @Test
    public void isPositiveAnswer() {
        assertTrue(StringUtil.isPositiveAnswer("Yes"));
        assertTrue(StringUtil.isPositiveAnswer("Y"));
        assertTrue(StringUtil.isPositiveAnswer("y"));
        assertFalse(StringUtil.isPositiveAnswer("No"));
        assertFalse(StringUtil.isPositiveAnswer("N"));
        assertFalse(StringUtil.isPositiveAnswer("Banana"));
    }

    @Test
    public void isNegativeAnswer() {
        assertTrue(StringUtil.isNegativeAnswer("No"));
        assertTrue(StringUtil.isNegativeAnswer("n"));
        assertTrue(StringUtil.isNegativeAnswer("Nope"));
        assertFalse(StringUtil.isNegativeAnswer("Y"));
        assertFalse(StringUtil.isNegativeAnswer("Banana"));
    }

    @Test
    public void testSentenceIsFact() {
        assertTrue(StringUtil.sentenceIsFact("It can fly."));
        assertTrue(StringUtil.sentenceIsFact("It has wings."));
        assertTrue(StringUtil.sentenceIsFact("It is a bird."));

        assertFalse(StringUtil.sentenceIsFact("It is a bird"));
        assertFalse(StringUtil.sentenceIsFact("It knows English."));
    }
}