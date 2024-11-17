/*
 * 프로그래머스 12952번. N-Queen
 * https://school.programmers.co.kr/learn/courses/30/lessons/12952
 */

class Solution {
    public int solution(int n) {
        int[] board = new int[n]; // board[i] = j: i행 위치한 퀸의 위치
        return dfs(board, 0, n);
    }

    /**
     * 퀸을 놓는 경우의 수를 구하는 함수
     *
     * @param board 퀸의 위치를 저장한 배열
     * @param row   현재 행
     * @param n     체스판의 크기
     * @return 퀸을 놓는 경우의 수
     */
    private int dfs(int[] board, int row, int n) {
        if (row == n) return 1;

        int answer = 0;
        for (int i = 0; i < n; i++) {
            board[row] = i;
            if (isPossible(board, row)) {
                answer += dfs(board, row + 1, n);
            }
        }

        return answer;
    }

    /**
     * 퀸을 놓을 수 있는지 확인하는 함수
     *
     * @param board 퀸의 위치를 저장한 배열
     * @param row   현재 행
     * @return 퀸을 놓을 수 있는지 여부
     */
    private boolean isPossible(int[] board, int row) {
        for (int i = 0; i < row; i++) {
            if (board[i] == board[row] || Math.abs(board[i] - board[row]) == row - i) { // 같은 열에 위치하거나 대각선에 위치하는 경우
                return false;
            }
        }
        return true;
    }

}
