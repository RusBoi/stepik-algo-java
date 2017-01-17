package Huffman;

import java.util.*;

class Huffman {
    public static final String TEST_STRING = "a";

    private static HashMap<Character, Integer> getFrequencies(String str) {
        HashMap<Character, Integer> fr = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);
            fr.putIfAbsent(letter, 0);
            fr.put(letter, fr.get(letter) + 1);
        }
        return fr;
    }

    private static TreeItem buildTree(String input) {
        HashMap<Character, Integer> frequencies = getFrequencies(input);
        PriorityQueue<TreeItem> queue = new PriorityQueue<>(new QueueComparator());
        for (Map.Entry<Character, Integer> e : frequencies.entrySet()) {
            queue.add(new TreeItem(e.getKey(), e.getValue()));
        }

        while (queue.size() > 1) {
            TreeItem t1 = queue.remove();
            TreeItem t2 = queue.remove();
            TreeItem newTreeItem = new TreeItem(t1.frequency + t2.frequency, t1, t2);
            queue.add(newTreeItem);
        }
        return queue.remove();
    }

    private static void getCodes(TreeItem currentItem, String currentCode, HashMap<Character, String> table) {
        if (currentItem.child1 != null)
            getCodes(currentItem.child1, currentCode + "0", table);
        if (currentItem.child2 != null)
            getCodes(currentItem.child2, currentCode + "1", table);
        if (currentItem.child2 == null && currentItem.child1 == null)
            table.put(currentItem.letter, currentCode);
    }

    public static HashMap<Character, String> getCodeTable(String input) {
        TreeItem root = buildTree(input);
        HashMap<Character, String> table = new HashMap<>();
        if (root.child1 == null)
            table.put(root.letter, "0");
        else
            getCodes(root, "", table);
        return table;
    }

    public static String encode(String input, Map<Character, String> codeTable) {
        String result = "";
        for (int i = 0; i < input.length(); i++) {
            result += codeTable.get(input.charAt(i));
        }
        return result;
    }

    public static String decode(String input, Map<String, Character> codeTable) {
        String subString = "";
        String result = "";
        for (int i = 0; i < input.length(); i++) {
            subString += input.charAt(i);
            if (codeTable.containsKey(subString))
            {
                result += codeTable.get(subString);
                subString = "";
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String input = sc.next();
//        HashMap<Character, String> table = getCodeTable(input);
//        String output = encode(input, table);
//
//        System.out.println(table.size() + " " + output.length());
//        for (Map.Entry<Character, String> e : table.entrySet()) {
//            System.out.println(e.getKey() + ": " + e.getValue());
//        }
//        System.out.println(output);

        HashMap<String, Character> table = new HashMap<>();
        int n = sc.nextInt();
        sc.nextInt();
        for (int i = 0; i < n; i++) {
            char ch = sc.next().charAt(0);
            table.put(sc.next(), ch);
        }
        String input = sc.next();
        System.out.println(decode(input, table));
    }
}

class TreeItem {
    public char letter;
    public int frequency;
    public TreeItem child1;
    public TreeItem child2;

    public TreeItem(char l, int f) {
//      если это лист
        letter = l;
        frequency = f;
    }

    public TreeItem(int f, TreeItem c1, TreeItem c2) {
//      если это не лист
        frequency = f;
        child1 = c1;
        child2 = c2;
    }
}

class QueueComparator implements Comparator<TreeItem>{
    @Override
    public int compare(TreeItem t1, TreeItem t2) {
        return Integer.compare(t1.frequency, t2.frequency);
    }
}
