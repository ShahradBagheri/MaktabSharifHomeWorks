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
        assertEquals(new Rational(2,1) ,new Rational(1,2).add(new Rational(3,2)));
    }

    @Test
    public void halfOfTheeSubHalfOfOneShouldBeOne(){
        assertEquals(new Rational(1,1),new Rational(3,2).sub(new Rational(1,2)));
    }

    @Test
    public void halfOfOneMulHalfOfOneShouldBeQuarterOfThree(){
        assertEquals(new Rational(3,4),new Rational(3,2).mul(new Rational(1,2)));
    }



}