/*
 * 프로그래머스 169199번. 리코쳇 로봇
 * https://school.programmers.co.kr/learn/courses/30/lessons/169199
 */

import java.util.*;

class Solution {

    final char ROBOT = 'R'; // 로봇의 처음 위치
    final char OBSTACLE = 'D'; // 장애물의 위치
    final char GOAL = 'G'; // 목표지점

    Position start;
    Position end;

    /**
     * 주어진 문자열 배열 board에 대해 로봇이 목표지점에 도달하는데 걸리는 최소 시간을 반환하는 메소드
     *
     * @param board 게임판의 상태를 나타내는 문자열 배열
     * @return 로봇이 목표지점에 도달하는데 걸리는 최소 시간
     */
    public int solution(String[] board) {
        // 입력받은 문자열 배열을 2차원 배열로 변환
        char[][] boardMap = new char[board.length][board[0].length()];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                boardMap[i][j] = board[i].charAt(j);

                if (boardMap[i][j] == ROBOT) {
                    start = new Position(i, j);
                } else if (boardMap[i][j] == GOAL) {
                    end = new Position(i, j);
                }
            }
        }

        // 시작점을 기준으로 BFS 탐색
        boolean[][] visited = new boolean[board.length][board[0].length()];
        PriorityQueue<Position> queue = new PriorityQueue<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Position cur = queue.remove();

            // 방문한 적이 있다면
            if (visited[cur.i][cur.j]) {
                continue;
            } else {
                visited[cur.i][cur.j] = true; // 방문처리
            }

            // 현재 위치가 목표지점이라면
            if (cur.arrive(end)) {
                return cur.time;
            }

            // 상하좌우로 이동
            queue.add(goUp(boardMap, cur));
            queue.add(goDown(boardMap, cur));
            queue.add(goLeft(boardMap, cur));
            queue.add(goRight(boardMap, cur));
        }

        return -1;
    }

    /**
     * 해당 위치에서 위로 쭉 올라가는 메소드
     *
     * @param boardMap 게임판의 상태를 나타내는 2차원 배열
     * @param current  현재 위치
     * @return 위로 쭉 올라갔을 때 도달하는 위치
     */
    private Position goUp(char[][] boardMap, Position current) {
        int i = current.i;
        int j = current.j;

        while (true) {
            i--; // 위로 이동

            if (!isInRange(boardMap, new Position(i, j)) || boardMap[i][j] == OBSTACLE) { // 위로 이동할 수 없는 경우
                return new Position(i + 1, j, current.time + 1); // 이전 위치로 돌아가기 위해
            }
        }
    }

    /**
     * 해당 위치에서 아래로 쭉 내려가는 메소드
     *
     * @param boardMap 게임판의 상태를 나타내는 2차원 배열
     * @param current  현재 위치
     * @return 아래로 쭉 내려갔을 때 도달하는 위치
     */
    private Position goDown(char[][] boardMap, Position current) {
        int i = current.i;
        int j = current.j;

        while (true) {
            i++; // 아래로 이동

            if (!isInRange(boardMap, new Position(i, j)) || boardMap[i][j] == OBSTACLE) { // 아래로 이동할 수 없는 경우
                return new Position(i - 1, j, current.time + 1); // 이전 위치로 돌아가기 위해
            }
        }
    }

    /**
     * 해당 위치에서 왼쪽으로 쭉 이동하는 메소드
     *
     * @param boardMap 게임판의 상태를 나타내는 2차원 배열
     * @param current  현재 위치
     * @return 왼쪽으로 쭉 이동했을 때 도달하는 위치
     */
    private Position goLeft(char[][] boardMap, Position current) {
        int i = current.i;
        int j = current.j;

        while (true) {
            j--; // 왼쪽으로 이동

            if (!isInRange(boardMap, new Position(i, j)) || boardMap[i][j] == OBSTACLE) { // 왼쪽으로 이동할 수 없는 경우
                return new Position(i, j + 1, current.time + 1); // 이전 위치로 돌아가기 위해
            }
        }
    }

    /**
     * 해당 위치에서 오른쪽으로 쭉 이동하는 메소드
     *
     * @param boardMap 게임판의 상태를 나타내는 2차원 배열
     * @param current  현재 위치
     * @return 오른쪽으로 쭉 이동했을 때 도달하는 위치
     */
    private Position goRight(char[][] boardMap, Position current) {
        int i = current.i;
        int j = current.j;

        while (true) {
            j++; // 오른쪽으로 이동

            if (!isInRange(boardMap, new Position(i, j)) || boardMap[i][j] == OBSTACLE) { // 오른쪽으로 이동할 수 없는 경우
                return new Position(i, j - 1, current.time + 1); // 이전 위치로 돌아가기 위해
            }
        }
    }

    private boolean isInRange(char[][] board, Position current) {
        return current.i >= 0 && current.i < board.length && current.j >= 0 && current.j < board[0].length;
    }

    class Position implements Comparable<Position> {
        int i;
        int j;
        int time; // (i, j) 위치에 도달하는데 걸린 시간

        public Position(int i, int j) {
            this.i = i;
            this.j = j;
            this.time = 0;
        }

        public Position(int i, int j, int time) {
            this.i = i;
            this.j = j;
            this.time = time;
        }

        public boolean arrive(Position target) {
            return this.i == target.i && this.j == target.j;
        }

        @Override
        public int compareTo(Position o) {
            return this.time - o.time;
        }
    }
}
