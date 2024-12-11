/*
 * 프로그래머스 12910번. 나누어 떨어지는 숫자 배열
 * https://school.programmers.co.kr/learn/courses/30/lessons/12910
 */

import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        ArrayList<Integer> answer = new ArrayList<>();

        for (int num : arr) {
            if (num % divisor == 0) {
                answer.add(num);
            }
        }

        if (answer.isEmpty()) {
            answer.add(-1);
        }

        return answer.stream()
                .mapToInt(i -> i)
                .sorted()
                .toArray();
    }
}
