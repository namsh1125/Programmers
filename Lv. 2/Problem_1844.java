/*
 * 프로그래머스 1844번. 게임 맵 최단거리
 * https://school.programmers.co.kr/learn/courses/30/lessons/1844
 */

import java.util.PriorityQueue;

class Solution {

    private final int WALL = 0;
    private final int[] di = {-1, 1, 0, 0}; // 상하좌우
    private final int[] dj = {0, 0, -1, 1}; // 상하좌우

    public int solution(int[][] maps) {
        // 방문 여부를 저장하는 배열
        boolean[][] visited = new boolean[maps.length][maps[0].length];

        // 우선순위 큐를 이용하여 BFS 탐색
        PriorityQueue<Position> queue = new PriorityQueue<>();
        queue.add(new Position(0, 0));

        while (!queue.isEmpty()) {
            Position current = queue.remove();

            // 방문 처리
            if (visited[current.i][current.j]) {
                continue;
            } else {
                visited[current.i][current.j] = true;
            }

            // 도착지에 확인했는지 확인
            if (isArrived(current.i, current.j, maps)) {
                return current.moved;
            }

            // 도착지에 도착하지 않았으면 상하좌우로 이동
            for (int d = 0; d < 4; d++) {
                int ni = current.i + di[d];
                int nj = current.j + dj[d];

                // 이동 가능한 위치인지 확인
                if (isInRange(ni, nj, maps) && maps[ni][nj] != WALL) {
                    queue.add(new Position(ni, nj, current.moved + 1));
                }
            }
        }

        return -1;
    }

    /**
     * 현재 위치가 게임 맵에 있는지 확인하는 메소드
     *
     * @param i    현재 위치의 행
     * @param j    현재 위치의 열
     * @param maps 게임 맵
     * @return 현재 위치가 게임 맵에 있으면 true, 아니면 false
     */
    private boolean isInRange(int i, int j, int[][] maps) {
        return i >= 0 && i < maps.length && j >= 0 && j < maps[0].length;
    }

    /**
     * 도착지에 도착했는지 확인하는 메소드
     *
     * @param i    현재 위치의 행
     * @param j    현재 위치의 열
     * @param maps 게임 맵
     * @return 도착지에 도착했으면 true, 아니면 false
     */
    private boolean isArrived(int i, int j, int[][] maps) {
        return i == maps.length - 1 && j == maps[0].length - 1;
    }

    public class Position implements Comparable<Position> {
        int i;
        int j;
        int moved; // 이동한 칸의 수

        public Position(int i, int j) {
            this.i = i;
            this.j = j;
            this.moved = 1;
        }

        public Position(int i, int j, int moved) {
            this.i = i;
            this.j = j;
            this.moved = moved;
        }

        @Override
        public int compareTo(Position o) {
            return this.moved - o.moved; // 오름차순
        }
    }
}
