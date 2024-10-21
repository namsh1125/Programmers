/*
 * 프로그래머스 12924번. 숫자의 표현
 * https://school.programmers.co.kr/learn/courses/30/lessons/12924
 */

class Solution {
    public int solution(int n) {
        int answer = 1;
        int sum = 1;

        for (int i = 2; i < n; i++) {
            sum += i;
            int x = n - sum;

            if (x < 0) break;
            if (x % i == 0) answer++;
        }

        return answer;
    }
}
