/*
 * 프로그래머스 42883번. 큰 수 만들기
 * https://school.programmers.co.kr/learn/courses/30/lessons/42883
 */

import java.util.Stack;

class Solution {

    /**
     * 입력받은 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하는 메소드
     *
     * @param number 문자열 형식의 숫자
     * @param k      제거할 수의 개수
     * @return number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자 (문자열)
     */
    public String solution(String number, int k) {
        char[] result = new char[number.length() - k];
        Stack<Character> stack = new Stack<>();

        for (char c : number.toCharArray()) {
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                stack.pop();
            }

            stack.add(c);
        }

        for (int i = 0; i < result.length; i++) {
            result[i] = stack.get(i);
        }

        return new String(result);
    }
}
