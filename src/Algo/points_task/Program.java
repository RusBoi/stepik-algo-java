package Algo.points_task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Program {
    /*
    Вход: множество n точек на прямой
    Выход: минимаьное кол-во отрезков заданной длины, покрывающие все заданные точки
     */


    public static final int SEGMENT_SIZE = 2;
    public static List<Segment> getSegments(List<Integer> points) {
        if (points.size() == 0)
            throw new IllegalArgumentException("Дан пустой массив точек");

        List<Segment> res = new ArrayList<Segment>();
        points.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int i = 0;

        while (i < points.size()) {
            int start = points.get(i);
            Segment seg = new Segment(start, start + SEGMENT_SIZE);
            res.add(seg);
            i += 1;
            while (i < points.size() && points.get(i) <= start + SEGMENT_SIZE) {
                i += 1;
            }
        }
        return res;
    }

    public static void test() {
        List<Integer> points = new ArrayList<Integer>() {{
            add(1);
            add(3);
            add(4);
            add(5);
            add(7);
            add(9);
            add(10);
        }};

        for (Segment seg : getSegments(points)) {
            System.out.println(seg);
        }
    }
}
