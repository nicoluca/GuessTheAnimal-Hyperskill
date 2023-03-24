//// Commenting this class as Hyperskill doesn't work with the import
//
//package animals.tests;
//
//import animals.util.StringUtil;
//import animals.domain.TypeOfFact;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class StringUtilTest {
//    // Testing in this package, as Hyperskill messes with the folder structure.
//
//    @Test
//    public void testGetWithoutArticle() {
//        assertEquals("cat", StringUtil.getWithoutArticle("a cat"));
//        assertEquals("cat", StringUtil.getWithoutArticle("an cat"));
//        assertEquals("cat", StringUtil.getWithoutArticle("the cat"));
//        assertEquals("nice cat", StringUtil.getWithoutArticle("nice cat"));
//    }
//
//    @Test
//    public void testGetArticeBasedOnFirstLetter() {
//        assertEquals("an", StringUtil.getArticleBasedOnFirstLetter("Ant"));
//        assertEquals("an", StringUtil.getArticleBasedOnFirstLetter("Elephant"));
//        assertEquals("a", StringUtil.getArticleBasedOnFirstLetter("Lion"));
//    }
//
//    @Test
//    public void testFormatAnomalInput() {
//        assertEquals("a cat", StringUtil.formatAnimalInput("the cat"));
//        assertEquals("an elephant", StringUtil.formatAnimalInput("an elephant"));
//        assertEquals("a elephant", StringUtil.formatAnimalInput("a elephant"));
//        assertEquals("an elephant", StringUtil.formatAnimalInput("the Elephant"));
//    }
//
//    @Test
//    public void isPositiveAnswer() {
//        assertTrue(StringUtil.isPositiveAnswer("Yes"));
//        assertTrue(StringUtil.isPositiveAnswer("Y"));
//        assertTrue(StringUtil.isPositiveAnswer("y"));
//        assertFalse(StringUtil.isPositiveAnswer("No"));
//        assertFalse(StringUtil.isPositiveAnswer("N"));
//        assertFalse(StringUtil.isPositiveAnswer("Banana"));
//    }
//
//    @Test
//    public void isNegativeAnswer() {
//        assertTrue(StringUtil.isNegativeAnswer("No"));
//        assertTrue(StringUtil.isNegativeAnswer("n"));
//        assertTrue(StringUtil.isNegativeAnswer("Nope"));
//        assertFalse(StringUtil.isNegativeAnswer("Y"));
//        assertFalse(StringUtil.isNegativeAnswer("Banana"));
//    }
//
//    @Test
//    public void testSentenceIsFact() {
//        assertTrue(StringUtil.sentenceIsFact("It can fly."));
//        assertTrue(StringUtil.sentenceIsFact("It has wings."));
//        assertTrue(StringUtil.sentenceIsFact("It is a bird."));
//        assertTrue(StringUtil.sentenceIsFact("IT IS A SHY ANIMAL"));
//        assertTrue(StringUtil.sentenceIsFact("It is a bird"));
//
//        assertFalse(StringUtil.sentenceIsFact("It knows English."));
//    }
//
//    @Test
//    public void testGetTypeOfFact() {
//        assertEquals(TypeOfFact.CAN, StringUtil.getTypeOfFact("It can fly."));
//        assertEquals(TypeOfFact.HAS, StringUtil.getTypeOfFact("It has wings."));
//        assertEquals(TypeOfFact.IS, StringUtil.getTypeOfFact("It is a bird."));
//    }
//
//    @Test
//    public void testFormatFact() {
//        assertEquals("fly", StringUtil.formatFact("It can fly"));
//        assertEquals("wings", StringUtil.formatFact("It has wings"));
//        assertEquals("a bird", StringUtil.formatFact("It is a bird"));
//        assertEquals("a shy animal", StringUtil.formatFact("It is a shy animal"));
//        assertEquals("a shy animal", StringUtil.formatFact("It is a shy animal."));
//        assertEquals("a shy animal", StringUtil.formatFact("It is a shy animal?"));
//    }
//}