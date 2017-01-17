package PriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class PriorityQueue<E> {
    private ArrayList<E> heap;
    private Comparator<E> comparator;

    public PriorityQueue(Comparator<E> comparator) {
        this.comparator = comparator;
        heap = new ArrayList<E>();
        heap.add(null);
    }

    public void insert(E item) {
        heap.add(item);
        siftUp(heap.size() - 1);
    }

    public E extract() {
        E res = heap.get(1);
        E element = heap.remove(heap.size() - 1);
        if (heap.size() != 1) {
            heap.set(1, element);
            siftDown();
        }
        return res;
    }

    private void siftUp(int index) {
        int currentIndex = index;
        while (true) {
            int parentIndex = currentIndex / 2;
            if (parentIndex == 0 || comparator.compare(heap.get(parentIndex), heap.get(currentIndex)) <= 0)
                break;
            swap(parentIndex, currentIndex);
            currentIndex = parentIndex;
        }
    }

    private void siftDown() {
//      сделай как нибудь покрасивее
        int currentIndex = 1;
        while (true) {
            int childIndex1 = currentIndex * 2;
            int childIndex2 = childIndex1 + 1;

            if (childIndex1 < heap.size()) {
                if (childIndex2 < heap.size()) {
//                  есть оба ребенка
                    int indexToSwap = comparator.compare(heap.get(childIndex1), heap.get(childIndex2)) <= 0 ? childIndex1 : childIndex2;
                    if (comparator.compare(heap.get(indexToSwap), heap.get(currentIndex)) <= 0) {
                        swap(currentIndex, indexToSwap);
                        currentIndex = indexToSwap;
                    }
                    else
                        break;
                }
                else {
//                    есть только один ребенок
                    if (comparator.compare(heap.get(childIndex1), heap.get(currentIndex)) <= 0)
                    {
                        swap(currentIndex, childIndex1);
                        currentIndex = childIndex1;
                    }
                    else
                        break;
                }
            }
            else
                break;
        }
    }

    private void swap(int i1, int i2) {
        E c = heap.get(i1);
        heap.set(i1, heap.get(i2));
        heap.set(i2, c);
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1, o2);
            }
        });

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            if (sc.next().equals("Insert"))
                q.insert(sc.nextInt());
            else
                System.out.println(q.extract());
        }
    }
}
