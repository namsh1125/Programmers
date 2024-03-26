/*
 * 프로그래머스 64061번. 크레인 인형뽑기 게임
 * https://school.programmers.co.kr/learn/courses/30/lessons/64061
 */

import java.util.ArrayDeque;

class Solution {

    /**
     * 크레인 인형뽑기 게임
     *
     * @param board 게임 화면의 격자의 상태가 담긴 2차원 배열
     * @param moves 인형을 집기 위해 크레인을 작동시킨 위치가 담긴 배열
     * @return 크레인을 모두 작동시킨 후 터트려져 사라진 인형의 개수
     */
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        // 각 위치별 크레인이 잡을 인형이 담긴 배열
        int[] top = new int[board.length];

        // 각 위치별 크레인이 잡을 인형의 위치를 저장
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] != 0 && top[i] == 0) {
                    top[i] = j;
                    break;
                }
            }
        }

        // 크레인을 작동시키며 뽑은 인형을 담긴 스택
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        // 크레인을 작동시키며 인형을 뽑기
        for (int move : moves) {

            // 인덱스는 0부터 시작하지만 게임은 1부터 시작하므로 -1을 해준다.
            int idx = move - 1;
            if (top[idx] < board.length) {

                // 크레인이 뽑은 인형
                int doll = board[top[idx]][idx];

                // 크레인이 뽑은 인형의 위치를 한 칸 아래로 이동
                top[idx]++;

                // 스택이 비어있지 않고, 스택의 가장 위에 있는 인형과 같은 인형을 뽑았다면
                if (!stack.isEmpty() && stack.peek() == doll) {
                    stack.pop();
                    answer += 2;

                } else {
                    stack.push(doll);
                }
            }
        }

        return answer;
    }
}
