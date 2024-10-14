/*
 * 프로그래머스 12913번. 땅따먹기
 * https://school.programmers.co.kr/learn/courses/30/lessons/12913
 */

import java.util.Arrays;

class Solution {
    int solution(int[][] land) {
        int n = land.length;
        int[][] dp = new int[n][4];

        for (int i = 0; i < 4; i++) {
            dp[0][i] = land[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (j == k) {
                        continue;
                    }

                    dp[i][j] = Math.max(dp[i][j], land[i][j] + dp[i-1][k]);
                }
            }
        }

        return Arrays.stream(dp[n-1]).max().getAsInt();
    }
}
