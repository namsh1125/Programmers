/*
 * 프로그래머스 12953번. N개의 최소공배수
 * https://school.programmers.co.kr/learn/courses/30/lessons/12953
 */

class Solution {
    public int solution(int[] arr) {
        int answer = arr[0];
        for (int i = 1; i < arr.length; i++) {
            answer = answer * arr[i] / gcd(answer, arr[i]);
        }

        return answer;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
