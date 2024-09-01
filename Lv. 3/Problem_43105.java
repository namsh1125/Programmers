/*
 * 프로그래머스 43105번. 정수 삼각형
 * https://school.programmers.co.kr/learn/courses/30/lessons/43105
 */

import java.util.Arrays;

class Solution {

    /**
     * 삼각형의 꼭대기에서 바닥까지 내려가며 합이 최대가 되는 경로의 합을 구하는 메소드
     *
     * @param triangle 삼각형의 정보가 담긴 배열
     * @return 삼각형의 꼭대기에서 바닥까지 내려가며 합이 최대가 되는 경로의 합
     */
    public int solution(int[][] triangle) {
        int[][] dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];

        for (int i = 1; i < triangle.length; i++) { // 삼각형 i번째 줄 (0번째 줄은 1개니까)
            // 한 줄의 양 끝은 이전 줄의 양 끝에서만 올 수 있음
            dp[i][0] = dp[i - 1][0] + triangle[i][0];
            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];

            // 한 줄의 나머지는 이전 줄의 양 옆에서 올 수 있음
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }
        }

        return Arrays.stream(dp[triangle.length - 1]).max().getAsInt();
    }
}
