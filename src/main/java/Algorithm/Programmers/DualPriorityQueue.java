package Algorithm.Programmers;

import java.util.*;

public class DualPriorityQueue {
    public static void main(String[] args) {
        Arrays.stream(solution(new String[]{"I 16", "D 1"})).forEach(System.out::println); // 0, 0
        Arrays.stream(solution(new String[]{"I 7", "I 5", "I -5", "D -1"})).forEach(System.out::println); // 7, 5
        Arrays.stream(solution(new String[]{"I 7"})).forEach(System.out::println);
        Arrays.stream(solution(new String[]{"I 4", "I 3", "I 2", "I 1", "D 1", "D 1", "D -1", "D -1", "I 5", "I 6"}))
                        .forEach(System.out::println); // 6 5
        // 1 2 3 4 5 6 7 8 9 10
        // 1 -1 1 -1 1 -1 1 -1 1
        // 6
    }

    public static int[] solution(String[] operations) {
        // I: 큐에 숫자  삽입
        // D 1 큐에서 최대값 삭제
        // D -1 큐에서 최솟값 삭제
        // 큐가 비어있으면 [0, 0] return
        // 큐가 비어있지 않으면 [max, min] return
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> pqr = new PriorityQueue<>(Collections.reverseOrder());
        int insertCount = 0;
        int extractCount = 0;

        for (String operator : operations) {
            String[] op = operator.split(" ");
            switch (op[0]) {
                case "I":
                    pq.add(Integer.valueOf(op[1]));
                    pqr.add(Integer.valueOf(op[1]));
                    insertCount++;
                    break;
                case "D":
                    if ("1".equals(op[1])) {
                        // 최대값 삭제
                        if (!pqr.isEmpty()) {
                            pqr.poll();
                            extractCount++;
                        }
                        break;
                    } else {
                        // 최소값 삭제
                        if (!pq.isEmpty()) {
                            pq.poll();
                            extractCount++;
                        }
                        break;
                    }
            }
        }
        if (insertCount <= extractCount) {
            return new int[]{0, 0};
        }

        TreeMap<Integer, Integer> duplicateKey = new TreeMap<>();
        Map<Integer, Integer> checkIsLeft = new HashMap<>();
        while (!pq.isEmpty()) {
            checkIsLeft.put(pq.poll(), 1);
        }
        while (!pqr.isEmpty()) {
            Integer num = pqr.poll();
            if (checkIsLeft.containsKey(num)) {
                duplicateKey.put(num, duplicateKey.getOrDefault(num, 0) + 1);
            }
        }

        if (duplicateKey.size() == 0) {
            return new int[]{pqr.poll(), pq.poll()};
        }
        if (duplicateKey.size() == 1) {
            return new int[]{duplicateKey.firstKey(), duplicateKey.firstKey()};
        } else {
            return new int[]{duplicateKey.lastKey(), duplicateKey.firstKey()};
        }
    }
}
