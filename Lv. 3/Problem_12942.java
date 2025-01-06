/*
 * 프로그래머스 12942번. 최적의 행렬 곱셈
 * https://school.programmers.co.kr/learn/courses/30/lessons/12942
 */

import java.util.Arrays;

class Solution {

    private final int INF = 100_000_000;
    private int[][] dp; // dp[i][j] : i번째 행렬부터 j번째 행렬까지 곱했을 때, 최소 곱셈 연산의 횟수

    public int solution(int[][] matrixSizes) {
        // A * B 행렬과 B * C 행렬을 곱할 때, 곱셈 연산의 횟수는 A * B * C
        dp = new int[matrixSizes.length + 1][matrixSizes.length + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, INF);
        }

        // 행렬의 개수가 1개일 때
        for (int i = 0; i < matrixSizes.length; i++) {
            dp[i][i] = 0;
        }

        // 행렬의 곱셈을 수행할 때, 최소 곱셈 연산의 횟수 계산
        for (int count = 2; count <= matrixSizes.length; count++) { // count: 곱셈을 수행할 행렬의 개수
            for (int i = 0; i + count - 1 < matrixSizes.length; i++) { // i: 곱생을 수행할 행렬의 시작 인덱스
                int j = i + count - 1; // j: 곱셈을 수행할 행렬의 끝 인덱스

                // i부터 j까지의 행렬을 곱했을 때의 최소 곱셈 횟수 계산
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + matrixSizes[i][0] * matrixSizes[k][1] * matrixSizes[j][1]);
                }
            }
        }

        return dp[0][matrixSizes.length - 1];
    }

}
