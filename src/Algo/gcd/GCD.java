package Algo.gcd;


import java.math.BigInteger;

public class GCD {
    public static long getGCD(long a, long b) {
//        System.out.println(a + " " + b);
        if (a == 0) return b;
        if (b == 0) return a;
//      тут идет хвостовая рекурсия, ее всегда можно убрать
        if (a >= b)
            return getGCD(a % b, b);
        else
            return getGCD(a, b % a);
    }

    public static long getGCD2(long a, long b) {
        while (true) {
            if (a == 0) return b;
            if (b == 0) return a;
            if (a >= b)
                a %=  b;
            else
                b %=  a;
        }
    }

    public static void test() {
//        long startTime = System.currentTimeMillis();
//        long res = getGCD(1231231231231231212L, 8128381299889981231L);
//        long endTime = System.currentTimeMillis();
//        System.out.println(res);
//        System.out.println(endTime - startTime + "ms");

        System.out.println(new BigInteger("123123129120391203012831231290371827381").gcd(new BigInteger("8912381923891293912032193213123")));
    }
}
