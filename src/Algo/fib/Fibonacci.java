package Algo.fib;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fibonacci {
    private Map<Integer, BigInteger> cache = new HashMap<>();
    private List<BigInteger> cache2 = new ArrayList() {{
        add(BigInteger.ZERO);
        add(BigInteger.ONE);
    }};

    public BigInteger getFibonacciSlow(int n) {
//      изначальная простенькая реализация с кэшом. Возможно переполнение стека вызовов
        if (n < 2)
            return BigInteger.valueOf(n);
        if (cache.containsKey(n))
            return cache.get(n);
        else {
            BigInteger res = getFibonacciSlow(n - 2).add(getFibonacciSlow(n - 1));
            cache.put(n, res);
            return res;
        }
    }

    public BigInteger getFibonacci(int n) {
        if (n < 2)
            return BigInteger.valueOf(n);
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        else {
//          немного оптимизируем тут код. Мы специально посчитаем все числа Фибоначчи до запрашиваемого числа для того,
//          чтобы снизить кол-во рекурсивных вызовов функции
            for (int i = 2; i <= n; i++) {
                BigInteger res = getFibonacci(i - 1).add(getFibonacci(i - 2));
                cache.put(i, res);
            }
            return cache.get(n);
        }
    }

    public BigInteger getFibonacci2(int n) {
//      заменим ассоциативный массив на список
        for (int i = 2; i <= n; i++) {
            BigInteger res = cache2.get(i - 1).add(cache2.get(i - 2));
            cache2.add(res);
        }
        return cache2.get(n);
    }

    public BigInteger getFibonacciGood(int n) {
        if (n < 2)
            return BigInteger.valueOf(n);

        BigInteger prev2 = BigInteger.ZERO;
        BigInteger prev = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            BigInteger fib = prev.add(prev2);
            prev2 = prev;
            prev = fib;
        }
        return prev;
    }

    public void test() {
        long startTime = System.currentTimeMillis();
        BigInteger res = getFibonacciGood(20);
        long endTime = System.currentTimeMillis();
        System.out.println(res);
        System.out.println(endTime - startTime + "ms");
    }
}
