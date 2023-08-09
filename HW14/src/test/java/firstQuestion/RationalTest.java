package firstQuestion;

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

    @Test
    public void halfOfOneDivHalfOfOneShouldBeThree(){
        assertEquals(new Rational(3,1),new Rational(3,2).div(new Rational(1,2)));
    }

    @Test
    public void halfOfOnePlusHalfOfThreeShouldBeTwoInFloatingPoint(){
        assertEquals(2 ,new Rational(1,2).add(new Rational(3,2)).toFloatingPoint());
    }

    @Test
    public void noArgRationalShouldBeZeroAndOne(){
        assertEquals(new Rational(0,1),new Rational());
    }

    @Test
    public void twoNegativeArgsShouldCancelEachOut(){
        assertEquals(new Rational(-1,-2), new Rational(1,2));
    }
}