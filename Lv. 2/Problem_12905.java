/*
 * 프로그래머스 12905번. 가장 큰 정사각형 찾기
 * https://school.programmers.co.kr/learn/courses/30/lessons/12905
 */

class Solution {
    public int solution(int[][] board) {
        int max = 0;
        int[][] dp = new int[board.length][board[0].length];

        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = board[i][j];
                    max = Math.max(max, dp[i][j]);
                    continue;
                }

                if (board[i][j] == 1) {
                    dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        return max * max;
    }

    /**
     * 입력받은 3개의 값 중 가장 작은 값을 반환하는 메소드
     */
    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

}
