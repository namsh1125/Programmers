/*
 * 프로그래머스 160585번. 혼자서 하는 틱택토
 * https://school.programmers.co.kr/learn/courses/30/lessons/160585
 */

class Solution {

    public int solution(String[] board) {
        int countO = 0;
        int countX = 0;

        // O와 X의 개수를 파악한다.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'O') countO++;
                else if (board[i].charAt(j) == 'X') countX++;
            }
        }

        // O와 X의 개수를 확인한다.
        // O와 X의 개수의 차이는 0또는 1이어야 한다.
        if (!(countO - countX == 0 || countO - countX == 1)) {
            return 0;
        }

        // 빙고 여부를 확인한다.
        // O와 X 둘 다 빙고일 수는 없으며,
        // O가 빙고인 경우는 X보다 1개 많고,
        // X가 빙고인 경우는 O와 개수가 같다.
        if (isBingo(board, 'O') && countO - countX != 1) {
            return 0;
        }

        if (isBingo(board, 'X') && countO - countX != 0) {
            return 0;
        }

        return 1;

    }

    public boolean isBingo(String[] board, char c) {
        // 가로로 빙고인지 확인한다.
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == c && board[i].charAt(1) == c && board[i].charAt(2) == c) {
                return true;
            }
        }

        // 세로로 빙고인지 확인한다.
        for (int i = 0; i < 3; i++) {
            if (board[0].charAt(i) == c && board[1].charAt(i) == c && board[2].charAt(i) == c) {
                return true;
            }
        }

        // 대각선으로 빙고인지 확인한다.
        if (board[0].charAt(0) == c && board[1].charAt(1) == c && board[2].charAt(2) == c) {
            return true;
        }

        if (board[2].charAt(0) == c && board[1].charAt(1) == c && board[0].charAt(2) == c) {
            return true;
        }

        // 그 외의 경우는 빙고가 아니다.
        return false;
    }


}
