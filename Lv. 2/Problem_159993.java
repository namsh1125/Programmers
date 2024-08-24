/*
 * 프로그래머스 159993번. 미로 탈출
 * https://school.programmers.co.kr/learn/courses/30/lessons/159993
 */

import java.util.ArrayDeque;

class Solution {
    final char START = 'S';
    final char EXIT = 'E';
    final char LEVER = 'L';
    final char WALL = 'X';

    final int[] di = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    final int[] dj = {0, 0, -1, 1}; // 상, 하, 좌, 우

    public int solution(String[] maps) {
        // 입력받은 지도를 2차원 지도로 변환한다.
        char[][] map = new char[maps.length][maps[0].length()];
        Position start = null;
        Position lever = null;
        Position end = null;

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                map[i][j] = maps[i].charAt(j);

                if (map[i][j] == START) {
                    start = new Position(i, j);
                } else if (map[i][j] == LEVER) {
                    lever = new Position(i, j);
                } else if (map[i][j] == EXIT) {
                    end = new Position(i, j);
                }
            }
        }

        // 출발지에서 레버에 도달하는 경우와 레버에서 도착지에 도달하는 경우를 각각 찾는다.
        // 만약 도달할 수 없는 경우 (반환 값이 -1인 경우) -1을 반환한다.
        int pathStartToLever = findShortestPath(map, start, lever);
        int pathLeverToEnd = findShortestPath(map, lever, end);

        if (pathStartToLever == -1 || pathLeverToEnd == -1) {
            return -1;
        } else {
            return pathStartToLever + pathLeverToEnd;
        }
    }

    /**
     * 출발지에서 가장 빠르게 도착지에 도착하는 경우를 찾는 메소드
     *
     * @param map   2차원 지도 정보
     * @param start 출발지
     * @param end   도착지
     * @return 출발지에서 도착지까지 가는데 걸리는 시간
     */
    public int findShortestPath(char[][] map, Position start, Position end) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        ArrayDeque<Position> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Position cur = queue.removeFirst();

            if (cur.i == end.i && cur.j == end.j) { // 도착지에 도달한 경우
                return cur.time;
            }

            // 도착지에 도달하지 못한 경우
            for (int k = 0; k < 4; k++) {
                int ni = cur.i + di[k];
                int nj = cur.j + dj[k];

                if (!isInRange(map, ni, nj) || map[ni][nj] == WALL) { // 범위를 벗어나거나 벽인 경우
                    continue;
                }

                if (visited[ni][nj]) { // 이전에 방문한 경우
                    continue;
                }

                visited[ni][nj] = true;
                queue.add(new Position(ni, nj, cur.time + 1));

            }

        }

        return -1; // 도착지에 도달하지 못한 경우
    }

    public boolean isInRange(char[][] map, int i, int j) {
        return i >= 0 && i < map.length && j >= 0 && j < map[0].length;
    }

    class Position {
        int i;
        int j;
        int time; // 해당 위치까지 이동하는데 걸린 횟수

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
    }
}
