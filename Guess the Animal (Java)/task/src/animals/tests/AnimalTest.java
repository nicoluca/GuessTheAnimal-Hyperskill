// // Tests are commented as Hyperskill doesn't support JUnit
// package animals.tests;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import animals.domain.animals.Animal;
//
//import static org.junit.Assert.*;
//
//public class AnimalTest {
//    private Animal cat, dog, fish;
//
//    @Before
//    public void setUp() {
//        cat = new Animal("the Cat");
//        dog = new Animal("A DOG");
//        fish = new Animal("AN fish");
//    }
//
//    @Test
//    public void getQuestion() {
//        assertEquals("Is it a cat?", cat.getQuestion());
//        assertEquals("Is it a dog?", dog.getQuestion());
//        assertEquals("Is it an fish?", fish.getQuestion());
//    }
//}