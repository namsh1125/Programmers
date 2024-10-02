/*
 * 프로그래머스 12951번. JadenCase 문자열 만들기
 * https://school.programmers.co.kr/learn/courses/30/lessons/12951
 */

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || s.charAt(i - 1) == ' ') {
                sb.append(Character.toUpperCase(s.charAt(i)));
            } else {
                sb.append(Character.toLowerCase(s.charAt(i)));
            }
        }

        return sb.toString();
    }
}
