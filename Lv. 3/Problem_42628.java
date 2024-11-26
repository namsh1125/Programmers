/*
 * 프로그래머스 42628번. 이중우선순위큐
 * https://school.programmers.co.kr/learn/courses/30/lessons/42628
 */

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());

        for (String operation : operations) {
            String[] str = operation.split(" ");
            String command = str[0];
            int value = Integer.parseInt(str[1]);

            if (Objects.equals(command, "I")) { // 삽입 연산인 경우
                minPQ.add(value);
                maxPQ.add(value);

            } else { // 삭제 연산인 경우
                if (value == 1) { // 최댓값 삭제 연산인 경우
                    if (!maxPQ.isEmpty()) {
                        minPQ.remove(maxPQ.remove());
                    }

                } else { // 최솟값 삭제 연산인 경우
                    if (!minPQ.isEmpty()) {
                        maxPQ.remove(minPQ.remove());
                    }
                }
            }
        }

        int min = minPQ.isEmpty() ? 0 : minPQ.peek();
        int max = maxPQ.isEmpty() ? 0 : maxPQ.peek();

        return new int[]{max, min};
    }
}
