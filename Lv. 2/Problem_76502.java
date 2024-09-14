/*
 * 프로그래머스 76502번. 괄호 회전하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/76502
 */

import java.util.Stack;

class Solution {

    /**
     * 주어진 문자열 s를 왼쪽으로 회전시켜 올바른 괄호 문자열로 만들 수 있는 경우의 수를 반환하는 메소드
     *
     * @param s 대괄호, 중괄호, 그리고 소괄호로 이루어진 문자열
     * @return 왼쪽으로 x (0 ≤ x < (s의 길이)) 칸만큼 회전시켰을 때 s가 올바른 괄호 문자열이 되게 하는 x의 개수
     */
    public int solution(String s) {
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            String rotated = s.substring(i) + s.substring(0, i);
            if (isCorrectParenthesesString(rotated)) {
                answer++;
            }
        }

        return answer;
    }

    /**
     * 주어진 문자열 s가 올바른 괄호 문자열인지 판별하는 메소드
     *
     * @param s 대괄호, 중괄호, 그리고 소괄호로 이루어진 문자열
     * @return s가 올바른 괄호 문자열이라면 true, 그렇지 않다면 false
     */
    private boolean isCorrectParenthesesString(String s) {
        Stack<Character> stack = new Stack<>();

        for (char parentheses : s.toCharArray()) {
            if (parentheses == '(' || parentheses == '{' || parentheses == '[') {
                stack.push(parentheses);

            } else {
                if (stack.isEmpty()) return false;

                // 스택의 맨 위에 있는 괄호와 현재 괄호가 짝이 맞는지 확인
                if (stack.peek() != reverseParentheses(parentheses)) return false;
                else stack.pop();
            }
        }

        return stack.isEmpty();
    }

    /**
     * 주어진 괄호 문자를 뒤집는 메소드
     *
     * @param parentheses 괄호 문자
     * @return 뒤집힌 괄호 문자
     */
    private char reverseParentheses(char parentheses) {
        return switch (parentheses) {
            case '(' -> ')';
            case ')' -> '(';
            case '{' -> '}';
            case '}' -> '{';
            case '[' -> ']';
            case ']' -> '[';
            default -> ' ';
        };
    }

}
