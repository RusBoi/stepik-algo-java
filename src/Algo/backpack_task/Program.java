package Algo.backpack_task;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Program {

    public static void test() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w = sc.nextInt();
        double res = 0;
        Item[] items = new Item[n];


        for (int i = 0; i < n; i++) {
            items[i] = new Item(sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                double d1 = (double) o1.cost / o1.weight;
                double d2 = (double) o2.cost / o2.weight;
                return -(Double.compare(d1, d2));
            }
        });

        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            int weightToAdd = Math.min(w, item.weight);
            res += weightToAdd * (item.cost / (double)item.weight);
            w -= weightToAdd;
            if (w == 0) {
                break;
            }
        }

        System.out.println(String.format("%.3f", res));
    }
}

class Item {
    public int cost;
    public int weight;

    public Item(int cost, int weight) {
        this.cost = cost;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Item{" +
                "cost=" + cost +
                ", weight=" + weight +
                '}';
    }
}
