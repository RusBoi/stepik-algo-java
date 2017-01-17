package Algo.segment_task;
import Algo.points_task.Segment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Program {
    /*
    Дано: массив отрезков на прямой
    Найти: наибольшое мно-во попарное непересекающихся отрезков
     */
    public static ArrayList<Segment> getNonCrossingSegments(List<Segment> segments) {
        segments.sort(new Comparator<Segment>() {
            @Override
            public int compare(Segment o1, Segment o2) {
                return o1.end - o2.end;
            }
        });
        ArrayList<Segment>  res = new ArrayList<Segment>();
        int i = 0;
        while (i < segments.size()) {
            Segment currentSegment = segments.get(i);
            res.add(currentSegment);
            i += 1;
            while (i < segments.size() && segments.get(i).start <= currentSegment.end) {
                i += 1;
            }
        }
        return res;
    }

    public static void test() {
        ArrayList<Segment> testArray = new ArrayList<Segment>() {{
            add(new Segment(0, 6));
            add(new Segment(0, 3));
            add(new Segment(1, 2));
            add(new Segment(0, 10));
            add(new Segment(0, 9));
            add(new Segment(4, 5));
        }};
        List<Segment> res = getNonCrossingSegments(testArray);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }
}
