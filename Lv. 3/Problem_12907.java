/*
 * 프로그래머스 12907번. 거스름돈
 * https://school.programmers.co.kr/learn/courses/30/lessons/12907
 */

class Solution {

    private static final int MOD = 1_000_000_007;

    /**
     * 거스름돈을 거슬러 줄 방법의 수를 구하는 함수
     *
     * @param n     거슬러 줘야 하는 금액
     * @param money 현재 보유하고 있는 돈의 종류
     * @return n 원을 거슬러 줄 방법의 수
     */
    public int solution(int n, int[] money) {
        long[] dp = new long[n + 1]; // dp[i] : i원을 거슬러 줄 방법의 수
        dp[0] = 1; // 0원을 거슬러 줄 방법은 1가지

        for (int m : money) {
            for (int i = m; i <= n; i++) {
                dp[i] += dp[i - m];
                dp[i] %= MOD;
            }
        }

        return (int) dp[n];
    }
}
