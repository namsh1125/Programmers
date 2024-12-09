/*
 * 프로그래머스 12906번. 같은 숫자는 싫어
 * https://school.programmers.co.kr/learn/courses/30/lessons/12906
 */

import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        for (int num : arr) {
            if (stack.isEmpty()) {
                stack.push(num);
            } else if (stack.peek() != num) {
                stack.push(num);
            }
        }

        return stack.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
