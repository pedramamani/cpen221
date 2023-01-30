CPEN 221

Problem Solving Practice
=========

## General Instructions

+ You have 75 minutes (1h 15m) to complete the assigned tasks.
+ Take your time to read the question.
+ Skeleton code can be obtained by cloning this repository. 

# Question: Strange Number System Hops

> The skeleton source code for this question is in the package `fibbase`. You may import the provided code as a Gradle project in Eclipse.

[Zeckendorf's Theorem](https://en.wikipedia.org/wiki/Zeckendorf%27s_theorem) says that it is possible to represent any positive integer as the sum of one or more distinct Fibonacci numbers.

Let `F(n)` be the `n`-th Fibonacci number. We have:

* `F(1)` = 1
* `F(2)` = 1
* `F(3)` = 2
* `F(4)` = 3
* `F(5)` = 5
* `F(6)` = 8
* and so on.

Using only `0` and `1` as digits, we can represent the decimal number `10` in base-Fib as `10010` where the leftmost position corresponds to the Fibonacci number 8 and the rightmost position corresponds to the Fibonacci number 1. The decimal number `9` in base-Fib would be `10001`. **Note that in this context, we will always pick the representation that uses the larger Fibonacci number rather than the next two smaller ones.**

Given a decimal number `a`, we can represent it in base-Fib and then we can interpret the base-Fib number as a binary number. In the case of the decimal number `10`, the base-Fib representation is `10010` and if we were to interpret this as a binary number then we would get `18` as the corresponding decimal.

Let `x(a)` be the function that takes a positive integer and produces its base-Fib representation. Let `y(a)` be the function that takes a positive integer, computes the base-Fib representation `x(a)` and then interprets that representation as a binary number and produces the equivalent decimal number.

With our example, `x(10)` is `10010` and `y(10)` is `18`.

A few other examples:

* `x(1)` = 1; `y(1)` = 1
* `x(2)` = 10; `y(2)` = 2
* `x(3)` = 100; `y(3)` = 4
* `x(4)` = 101; `y(4)` = 5
* `x(5)` = 1000; `y(5)` = 8
* `x(6)` = 1001; `y(6)` = 9

You have to implement the method `long ySum(long a, long b)` that returns `y(a)+y(a+1)+...+y(b-1)+y(b)` for integers `a` and `b` such that `b >= a`.

### Test Cases

```java
@Test
public void test1() {
	assertEquals(1, FibBase.ySum(1, 1));
}

@Test
public void test2() {
	assertEquals(3, FibBase.ySum(1, 2));
}

@Test
public void test3() {
	assertEquals(7, FibBase.ySum(1, 3));
}

@Test
public void test4() {
	assertEquals(12, FibBase.ySum(1, 4));
}

@Test
public void test5() {
	assertEquals(20, FibBase.ySum(1, 5));
}
```
