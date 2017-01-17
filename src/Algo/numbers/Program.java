package Algo.numbers;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    public static void test() {
        Scanner sc = new Scanner(System.in);
        BigInteger n = sc.nextBigInteger();
        ArrayList<BigInteger> res = getNumbers(n);
        System.out.println(res.size());
        for (BigInteger i : res) {
            System.out.print(i + " ");
        }
    }

    private static ArrayList<BigInteger> getNumbers(BigInteger n) {
        BigInteger sum = BigInteger.ZERO;
        ArrayList<BigInteger> res = new ArrayList<>();
        if (n.equals(BigInteger.valueOf(2))) {
            res.add(n);
            return res;
        }

        for (BigInteger i = BigInteger.ONE; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
            BigInteger val = n.subtract(sum.add(i));
            if (val.compareTo(BigInteger.ZERO) < 0)
                break;

            if (!res.contains(val) && !val.equals(i)) {
                res.add(i);
                sum = sum.add(i);
            }
        }
        return res;
    }
}