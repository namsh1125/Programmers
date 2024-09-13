/*
 * 프로그래머스 12902번. 3 x n 타일링
 * https://school.programmers.co.kr/learn/courses/30/lessons/12902
 */

class Solution {

    /**
     * 3 x n 크기의 직사각형을 채우는 방법의 수를 구하는 메소드
     *
     * @param n 직사각형의 가로의 길이
     * @return 3 x n 크기의 직사각형을 채우는 방법의 수를 1,000,000,007로 나눈 나머지를 리턴
     */
    public long solution(int n) {
        if (n % 2 == 1) { // n이 홀수인 경우
            return 0; // 홀수의 공간에, 짝수의 타일을 사용하여 바닥을 꽉 채울 수 없다.
        }

        long[] dp = new long[n + 1];
        dp[2] = 3;
        dp[4] = 11;

        // f(n) = 3 * f(n - 2) + 2 * f(n - 4) + 2 * f(n - 6) + ... + 2 * f(0)
        // f(n - 2) = 3 * f(n - 4) + 2 * f(n - 6) + ... + 2 * f(0) -> f(n - 2) - f(n - 4) = 2 * f(n - 4) + 2 * f(n - 6) + ... + 2 * f(0)
        // f(n) = 3 * f(n - 2) + f(n - 2) - f(n - 4) = 4 * f(n - 2) - f(n - 4)

        for (int i = 6; i <= n; i += 2) {
            dp[i] = (4 * dp[i - 2] - dp[i - 4]) % 1000000007;
            if (dp[i] < 0) {
                dp[i] += 1000000007;
            }
        }

        return dp[n];
    }
}
