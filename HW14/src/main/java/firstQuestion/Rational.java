package firstQuestion;

import java.util.Objects;

public class Rational {
    private final int numerator;
    private final int denominator;
    public  Rational (){
        this.numerator = 0;
        this.denominator = 1;
    }
    public Rational(int numerator,int denominator){
        int gcd = Math.abs(gcd(numerator,denominator));

        if(denominator == 0)
            throw new ArithmeticException("Division by zero");

        if (numerator < 0 && denominator < 0){
            numerator = Math.abs(numerator);
            denominator = Math.abs(denominator);
        }else if (numerator > 0 && denominator < 0){
            numerator = numerator * (-1);
            denominator = Math.abs(denominator);
        }

        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }
    private int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b,a%b);
    }

    public Rational add (Rational num){
        return new Rational(this.numerator * num.denominator + num.numerator * this.denominator, this.denominator * num.denominator);
    }

    public Rational sub (Rational num){
        return new Rational(this.numerator * num.denominator - num.numerator* this.denominator , this.denominator * num.denominator);
    }

    public Rational mul (Rational num) {
        return new Rational(this.numerator * num.numerator, this.denominator * num.denominator);
    }

    public Rational div (Rational num){
        Rational newNum = new Rational(num.denominator,num.numerator);
        return this.mul(newNum);
    }

    public double toFloatingPoint (){
        return  ((double) this.numerator / (double) this.denominator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rational rational = (Rational) o;
        return numerator == rational.numerator && denominator == rational.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        int realNum = this.numerator / this.denominator;
        if(realNum == 0)
            return this.numerator + "/" + this.denominator;
        else if(this.numerator - this.denominator*realNum == 0)
            return Integer.toString(realNum);
        else if(this.numerator < 0)
            return realNum + " - " + Math.abs((this.numerator - this.denominator*realNum)) + "/" + this.denominator;
        else
            return realNum + " + " + (this.numerator - this.denominator*realNum) + "/" + this.denominator;
    }
}
