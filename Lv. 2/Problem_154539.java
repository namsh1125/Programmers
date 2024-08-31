/*
 * 프로그래머스 154539번. 뒤에 있는 큰 수 찾기
 * https://school.programmers.co.kr/learn/courses/30/lessons/154539
 */

import java.util.*;

class Solution {

    /**
     * 뒤에 있는 큰 수 찾기
     *
     * @param numbers 주어진 숫자 배열
     * @return 모든 원소에 대한 뒷 큰수들을 차례로 담은 배열
     */
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1); // -1로 배열 초기화

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numbers.length; i++) {
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) { // 스택이 비어있지 않고, 스택의 top이 현재 값보다 작을 때
                answer[stack.pop()] = numbers[i]; // 스택의 top에 해당하는 인덱스에 현재 값을 넣어줌
            }
            stack.push(i);
        }

        return answer;
    }
}
