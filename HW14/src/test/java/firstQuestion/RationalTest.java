package firstQuestion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RationalTest {

    public Rational rational;

    @Test
    public void denominatorZeroShouldThrowException(){
        assertThrows(ArithmeticException.class, () -> rational = new Rational(1,0));
    }

    @Test
    public void halfOfOnePlusHalfOfThreeShouldBeTwo(){
        assertEquals(2,new Rational(1,2).add(new Rational(3,2)).toFloatingPoint());
    }



}