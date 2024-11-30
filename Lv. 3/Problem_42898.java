/*
 * 프로그래머스 42898번. 등굣길
 * https://school.programmers.co.kr/learn/courses/30/lessons/42898
 */

class Solution {

    private static final int MOD = 1_000_000_007; // 나눌 수
    private static final int WATER = -1; // 물 웅덩이

    /**
     * 집에서 학교까지 갈 수 있는 최단경로의 개수를 구하는 함수
     *
     * @param m       가로 길이
     * @param n       세로 길이
     * @param puddles 물이 잠긴 지역의 좌표를 담은 2차원 배열
     * @return 오른쪽과 아래쪽으로만 움직여 집에서 학교까지 갈 수 있는 최단경로의 개수를 1,000,000,007로 나눈 나머지
     */
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];

        // 물 웅덩이 위치 표기
        for (int[] puddle : puddles) {
            dp[puddle[1] - 1][puddle[0] - 1] = WATER;
        }

        // 가로 첫 줄 초기화
        for (int j = 0; j < m; j++) {
            if (dp[0][j] == WATER) {
                break;
            }
            dp[0][j] = 1;
        }

        // 세로 첫 줄 초기화
        for (int i = 0; i < n; i++) {
            if (dp[i][0] == WATER) {
                break;
            }
            dp[i][0] = 1;
        }

        // 최단 경로 개수 구하기
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                // 현재 위치가 물 웅덩이인 경우
                if (dp[i][j] == WATER) {
                    continue;
                }

                // 현재 위치가 물 웅덩이가 아닌 경우
                if (dp[i][j - 1] == WATER) { // 왼쪽이 물 웅덩이인 경우
                    dp[i][j] = dp[i - 1][j] % MOD; // 위에서 오는 경우밖에 없음

                } else if (dp[i - 1][j] == WATER) { // 위쪽이 물 웅덩이인 경우
                    dp[i][j] = dp[i][j - 1] % MOD; // 왼쪽에서 오는 경우밖에 없음

                } else { // 위쪽, 왼쪽 모두 물 웅덩이가 아닌 경우
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD; // 왼쪽에서 오는 경우 + 위에서 오는 경우
                }
            }
        }

        // 최단 경로 반환
        return dp[n - 1][m - 1];
    }
}
