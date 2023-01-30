package ca.ubc.ece.cpen221.mp3.operator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OperatorTest {

    private static double EPSILON = 0.00000001;

    @Test
    public void test1Abs() {
        AbsoluteValOperator abs = new AbsoluteValOperator();
        assertEquals(1.0, abs.apply(-1.0), EPSILON);
        assertEquals(1.0, abs.apply(1.0), EPSILON);
        assertEquals("abs", abs.toString());
    }

    @Test
    public void test2Cos() {
        CosineOperator cos = new CosineOperator();
        assertEquals(1.0, cos.apply(0), EPSILON);
        assertEquals("cos", cos.toString());
    }

    @Test
    public void test3Sin() {
        SineOperator sin = new SineOperator();
        assertEquals(0.0, sin.apply(0), EPSILON);
        assertEquals("sin", sin.toString());
    }

    @Test
    public void test4Exp() {
        ExponentiationOperator exp = new ExponentiationOperator();
        assertEquals(1.0, exp.apply(100, 0), EPSILON);
        assertEquals(0.0, exp.apply(0, 100), EPSILON);
        assertEquals(8.0, exp.apply(2, 3), EPSILON);
        assertEquals("^", exp.toString());
    }

    @Test
    public void test5NatLog() {
        NaturalLogOperator log = new NaturalLogOperator();
        assertEquals(0.0, log.apply(1.0), EPSILON);
        assertEquals("log", log.toString());
    }

    @Test
    public void test6Sqrt() {
        SquareRootOperator sqrt = new SquareRootOperator();
        assertEquals(2.0, sqrt.apply(4.0), EPSILON);
        assertEquals(0.0, sqrt.apply(0.0), EPSILON);
        assertEquals("sqrt", sqrt.toString());
    }

    @Test
    public void test7Mult() {
        MultiplicationOperator mult = new MultiplicationOperator();
        assertEquals(2.0, mult.apply(1, 2.0), EPSILON);
        assertEquals("*", mult.toString());
    }

    @Test
    public void test8Sub() {
        SubtractionOperator sub = new SubtractionOperator();
        assertEquals(2.0, sub.apply(4.0, 2.0), EPSILON);
        assertEquals("-", sub.toString());
    }

    @Test
    public void test9Add() {
        AdditionOperator add = new AdditionOperator();
        assertEquals(2.0, add.apply(0.0, 2.0), EPSILON);
        assertEquals("+", add.toString());
    }
}
