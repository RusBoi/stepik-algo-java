package Algo.points_task;


public class Segment {
    public int start;
    public int end;

    public Segment(int a, int b) {
        start = a;
        end = b;
    }

    public String toString() {
        return start + "-" + end;
    }
}
