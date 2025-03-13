/*
 * 백준 389480번. 완전범죄
 * https://school.programmers.co.kr/learn/courses/30/lessons/389480
 */

class Solution {

    private static final int INF = 1000000000;

    /**
     * @param info 각 물건을 훔칠 때 생기는 흔적에 대한 정보를 담은 2차원 정수 배열
     * @param n    A도둑이 경찰에 붙잡히는 최소 흔적 개수
     * @param m    B도둑이 경찰에 붙잡히는 최소 흔적 개수
     * @return 두 도둑 모두 경찰에 붙잡히지 않도록 모든 물건을 훔쳤을 때, A도둑이 남긴 흔적의 누적 개수의 최솟값. 만약 어떠한 방법으로도 두 도둑 모두 경찰에 붙잡히지 않게 할 수 없다면 -1
     */
    public int solution(int[][] info, int n, int m) {
        int size = info.length;
        int[][] dp = new int[size + 1][m]; // dp[i][j]: i번째 물건까지 훔쳤을 때, B도둑의 흔적 개수가 j개일 때 A도둑의 흔적 개수의 최솟값

        // DP 배열 초기화
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = INF;
            }
        }

        dp[0][0] = 0; // 초기값 설정

        // DP 배열 채우기
        for (int i = 1; i <= size; i++) { // i번째 물건을 훔칠때
            int a = info[i - 1][0]; // A도둑이 남긴 흔적 개수
            int b = info[i - 1][1]; // B도둑이 남긴 흔적 개수

            // DP 배열 갱신 - A도둑이 훔친 경우
            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a);
            }

            // DP 배열 갱신 - B도둑이 훔친 경우
            for (int j = b; j < m; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - b]);
            }
        }

        // 정답 찾기
        int min = INF;
        for (int i = 0; i < m; i++) {
            if (dp[size][i] < n) { // A도둑, B도둑 모두 경찰에 붙잡히지 않는 경우
                min = Math.min(min, dp[size][i]);
            }
        }

        return min == INF ? -1 : min;
    }
}
