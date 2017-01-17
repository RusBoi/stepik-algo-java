package Algo.segment_task2;


import Algo.points_task.Segment;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Program {
    /*
    По данным n отрезкам необходимо найти множество точек минимального размера, для которого каждый из отрезков
    содержит хотя бы одну из точек.
     */
    public static ArrayList<Integer> getPoints(List<Segment> segments) {
        segments.sort(new Comparator<Segment>() {
            @Override
            public int compare(Segment o1, Segment o2) {
                return o1.end - o2.end;
            }
        });
        int i = 0;
        ArrayList<Integer> result = new ArrayList<Integer>();

        while (i < segments.size()) {

            Segment currentSegment = segments.get(i);
            result.add(currentSegment.end);
            i += 1;
            while (i < segments.size() && segments.get(i).start <= currentSegment.end) {
                i += 1;
            }
        }

        return result;
    }

    public static void test() {
        Scanner scanner = new Scanner(System.in);
        Integer n = scanner.nextInt();
        ArrayList<Segment> segments = new ArrayList<Segment>();
        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            segments.add(new Segment(a, b));
        }
        ArrayList<Integer> res = getPoints(segments);
        System.out.println(res.size());
        for(Integer p : res) {
            System.out.print(p + " ");
        }
    }
}
