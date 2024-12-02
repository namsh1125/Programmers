/*
 * 프로그래머스 12904번. 가장 긴 팰린드롬
 * https://school.programmers.co.kr/learn/courses/30/lessons/12904
 */

class Solution {

    /**
     * s의 부분문자열(Substring)중 가장 긴 팰린드롬의 길이를 구하는 함수
     *
     * @param s 문자열 (1 <= s.length() <= 2,500, s는 소문자로만 구성)
     * @return s의 부분문자열(Substring)중 가장 긴 팰린드롬의 길이
     */
    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            // 홀수 길이의 팰린드롬
            int left = i;
            int right = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                answer = Math.max(answer, right - left + 1);
                left--;
                right++;
            }

            // 짝수 길이의 팰린드롬
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                answer = Math.max(answer, right - left + 1);
                left--;
                right++;
            }
        }

        return answer;
    }

}
