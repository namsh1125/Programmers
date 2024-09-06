/*
 * 프로그래머스 12900번. 2 x n 타일링
 * https://school.programmers.co.kr/learn/courses/30/lessons/12900
 */

class Solution {

    /**
     * 직사각형을 채우는 방법의 수를 리턴하는 메소드
     *
     * @param n 직사각형의 가로의 길이
     * @return 직사각형을 채우는 방법의 수를 1,000,000,007로 나눈 나머지를 리턴
     */
    public int solution(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1; // 타일을 세로로 배치하는 경우
        dp[2] = 2; // 타일을 가로로 배치 & 세로로 배치하는 경우

        if (n > 2) {
            for (int i = 3; i <= n; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
            }
        }

        return dp[n];
    }
}
