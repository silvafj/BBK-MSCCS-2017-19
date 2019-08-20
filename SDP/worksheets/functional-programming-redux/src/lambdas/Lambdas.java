package lambdas;

import java.math.BigDecimal;
import java.util.Random;
import java.util.function.*;

public class Lambdas {

  /**
   * Returns {@link Supplier} that always supply "Hello"
   *
   * @return a string supplier
   */
  public static Supplier<String> helloSupplier() {
    return () -> "Hello";
  }

  /**
   * Returns a {@link Predicate} of string that checks if string is empty
   *
   * @return a string predicate
   */
  public static Predicate<String> isEmptyPredicate() {
    return s -> s.isEmpty();
  }

  /**
   * Returns a {@link Function} that converts a {@link BigDecimal} number into a {@link String} that
   * start with a dollar sign and then gets a value
   *
   * @return function that converts adds dollar sign
   */
  public static Function<BigDecimal, String> toDollarStringFunction() {
    return d -> "$" + d.toString();
  }

  /**
   * Receives two parameter that represent a range and returns a {@link Predicate<String>} that
   * verifies if string length is in the specified range. E.g. min <= length < max
   *
   * @param min min length
   * @param max max length
   * @return a string predicate
   */
  public static Predicate<String> lengthInRangePredicate(int min, int max) {
    return s -> min <= s.length() && s.length() < max;
  }

  /**
   * Returns a {@link Supplier} of random integers
   *
   * @return int supplier
   */
  public static IntSupplier randomIntSupplier() {
    return () -> (new Random()).nextInt();
  }

  /**
   * Returns an {@link IntUnaryOperator} that receives an int as a bound parameter, and returns a
   * random int
   *
   * @return int operation
   */
  public static IntUnaryOperator boundedRandomIntSupplier() {
    return i -> (new Random()).nextInt(i);
  }

  /**
   * Returns {@link IntUnaryOperator} that calculates an integer square
   *
   * @return square operation
   */
  public static IntUnaryOperator intSquareOperation() {
    return i -> i * i;
  }

  /**
   * Returns a {@link LongBinaryOperator} sum operation.
   *
   * @return binary sum operation
   */
  public static LongBinaryOperator longSumOperation() {
    return (l, r) -> l + r;
  }

  /**
   * Returns a {@link ToIntFunction<String>} that converts string to integer.
   *
   * @return string to int converter
   */
  public static ToIntFunction<String> stringToIntConverter() {
    return s -> Integer.valueOf(s);
  }

  /**
   * Receives int parameter n, and returns a {@link Supplier} that supplies {@link IntUnaryOperator}
   * that is a function f(x) = n * x
   *
   * @param n a multiplier
   * @return a function supplier
   */
  public static Supplier<IntUnaryOperator> nMultiplyFunctionSupplier(int n) {
    return () -> (x -> n * x);
  }

  /**
   * Receives a {@link Runnable} parameter, and returns a {@link Supplier<Thread>}. The thread will
   * be started only when you call supplier method {@link Supplier#get()}
   *
   * @param runnable the code you want to tun in new thread
   * @return a thread supplier
   */
  public static Supplier<Thread> runningThreadSupplier(Runnable runnable) {
    return () -> {
      Thread t = new Thread(runnable);
      t.start();
      return t;
    };
  }

  /**
   * Returns a {@link Consumer} that accepts {@link Runnable} as a parameter and runs in in a new
   * thread.
   *
   * @return a runnable consumer
   */
  public static Consumer<Runnable> newThreadRunnableConsumer() {
    return r -> (new Thread(r)).start();
  }

  /**
   * Returns a {@link Function} that accepts an instance of {@link Runnable} and returns a {@link
   * Supplier} of a started {@link Thread} that is created from a given {@link Runnable}
   *
   * @return a function that transforms runnable into a thread supplier
   */
  public static Function<Runnable, Supplier<Thread>> runnableToThreadSupplierFunction() {
    return r ->
        (() -> {
          Thread t = new Thread(r);
          t.start();
          return t;
        });
  }

  /**
   * Returns a {@link BiFunction} that has two parameters. First is {@link IntUnaryOperator} which
   * is some integer function. Second is {@link IntPredicate} which is some integer condition. And
   * the third is {@link IntUnaryOperator} which is a new composed function that uses provided
   * predicate (second parameter of binary function) to verify its input parameter. If predicate
   * returns {@code true} it applies a provided integer function (first parameter of binary
   * function) and returns a result value, otherwise it returns an element itself.
   *
   * @return a binary function that receiver predicate and function and compose them to create a new
   *     function
   */
  public static BiFunction<IntUnaryOperator, IntPredicate, IntUnaryOperator>
      functionToConditionalFunction() {
    return (intFunc, intPred) -> (v -> intPred.test(v) ? intFunc.applyAsInt(v) : v);
  }

  /**
   * Returns {@link Supplier} of {@link Supplier} of {@link Supplier} of {@link String} "WELL DONE".
   *
   * @return a supplier instance
   */
  public static Supplier<Supplier<Supplier<String>>> trickyWellDoneSupplier() {
    return () -> () -> () -> "WELL DONE!";
  }
}
