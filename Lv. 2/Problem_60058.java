/*
 * 프로그래머스 60058번. 괄호 변환
 * https://school.programmers.co.kr/learn/courses/30/lessons/60058
 */

import java.util.Stack;

class Solution {

    /**
     * 주어진 알고리즘을 수행해 "올바른 괄호 문자열"로 변환하는 메소드
     *
     * @param p 균형잡힌 괄호 문자열
     * @return 주어진 알고리즘을 수행해 "올바른 괄호 문자열"로 변환한 결과
     */
    public String solution(String p) {
        return dfs(p);
    }

    /**
     * 주어진 알고리즘을 수행하는 메소드
     *
     * @param str 변환할 문자열
     * @return 변환한 문자열
     */
    private String dfs(String str) {
        // 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환한다.
        if (str.isEmpty()) return "";

        // 2. 문자열을 두 "균형잡힌 괄호 문자열" u, v로 분리한다.
        Pair<String, String> pair = divide(str);
        String u = pair.getFirst();
        String v = pair.getSecond();

        // 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행한다.
        //  3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환한다.
        if (isCorrectParenthesisString(u)) {
            return u + dfs(v);
        }

        // 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행한다.
        //  4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
        //  4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
        //  4-3. ')'를 다시 붙입니다.
        //  4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
        //  4-5. 생성된 문자열을 반환합니다.
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(dfs(v));
        sb.append(')');
        for (int i = 1; i < u.length() - 1; i++) {
            sb.append(reverse(u.charAt(i)));
        }

        return sb.toString();
    }

    /**
     * 주어진 문자열의 괄호 방향을 뒤집는 메소드
     *
     * @param c 뒤집을 문자열
     * @return 뒤집은 문자열
     */
    private Character reverse(char c) {
        return c == '(' ? ')' : '(';
    }

    /**
     * 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리하는 메소드 (u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있다.)
     *
     * @param w 분리할 문자열
     * @return 분리한 문자열 u, v
     */
    private Pair<String, String> divide(String w) {
        int open = 0;
        int close = 0;

        for (int i = 0; i < w.length(); i++) {
            open += w.charAt(i) == '(' ? 1 : 0;
            close += w.charAt(i) == ')' ? 1 : 0;

            if (open == close) {
                return new Pair<>(w.substring(0, i + 1), w.substring(i + 1));
            }
        }

        return new Pair<>(w, "");
    }


    /**
     * 주어진 문자열이 "올바른 괄호 문자열"인지 확인하는 메소드
     *
     * @param str 확인할 문자열
     * @return "올바른 괄호 문자열"이면 true, 아니면 false
     */
    private boolean isCorrectParenthesisString(String str) {
        if (!isBalancedParenthesisString(str)) return false; // 균형잡힌 괄호 문자열이 아니면 false

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    /**
     * 주어진 문자열이 "균형잡힌 괄호 문자열"인지 확인하는 메소드
     *
     * @param str 확인할 문자열
     * @return "균형잡힌 괄호 문자열"이면 true, 아니면 false
     */
    private boolean isBalancedParenthesisString(String str) {
        int open = 0;
        int close = 0;

        for (int i = 0; i < str.length(); i++) {
            open += str.charAt(i) == '(' ? 1 : 0;
            close += str.charAt(i) == ')' ? 1 : 0;
        }

        return open == close;
    }

    public class Pair<T, U> {
        private T first;
        private U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public U getSecond() {
            return second;
        }
    }
}
