/*
 * 프로그래머스 154540번. 무인도 여행
 * https://school.programmers.co.kr/learn/courses/30/lessons/154540
 */

import java.util.ArrayDeque;
import java.util.ArrayList;

class Solution {

    final char SEA = 'X';

    final int[] di = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    final int[] dj = {0, 0, -1, 1}; // 상, 하, 좌, 우

    public int[] solution(String[] maps) {
        char[][] map = new char[maps.length][maps[0].length()]; // 지도를 저장할 2차원 배열
        boolean[][] visited = new boolean[maps.length][maps[0].length()]; // 방문 여부를 저장할 배열

        // 2차원 배열로 변환
        for (int i = 0; i < maps.length; i++) {
            map[i] = maps[i].toCharArray();
        }

        ArrayList<Integer> answer = new ArrayList<>(); // 각 섬에서 최대 며칠씩 머무를 수 있는 시간을 저장할 리스트

        // 각 섬을 찾아서 bfs 탐색
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                if (maps[i].charAt(j) != SEA && !visited[i][j]) { // 바다가 아니며, 방문하지 않은 경우
                    answer.add(bfs(map, visited, i, j));
                }
            }
        }

        // answer이 비어있는 경우, -1을 반환하며, 그렇지 않은 경우, 오름차순으로 정렬된 배열을 반환
        if (answer.isEmpty()) {
            return new int[]{-1};
        } else {
            return answer.stream().mapToInt(i -> i).sorted().toArray();
        }
    }

    /**
     * bfs 탐색을 통해 섬에서 버틸 수 있는 최대 일수를 구하는 메소드
     *
     * @param map     지도
     * @param visited 방문 여부를 저장하는 배열
     * @param i       현재 위치의 행
     * @param j       현재 위치의 열
     * @return 섬에서 버틸 수 있는 최대 일수
     */
    public int bfs(char[][] map, boolean[][] visited, int i, int j) {
        int max = 0; // 섬에서 버틸 수 있는 최대 일수
        ArrayDeque<Position> queue = new ArrayDeque<>(); // bfs 탐색을 위한 큐
        queue.add(new Position(i, j)); // 시작 위치를 큐에 추가

        while (!queue.isEmpty()) {
            Position cur = queue.removeFirst(); // 큐에서 하나 꺼내기

            if (visited[cur.i][cur.j]) { // 이미 방문한 경우
                continue;
            }

            max = max + map[cur.i][cur.j] - '0'; // 섬에서 버틸 수 있는 최대 일수 갱신
            visited[cur.i][cur.j] = true; // 방문 표시

            // 상하좌우 탐색
            for (int d = 0; d < 4; d++) {
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if (ni < 0 || ni >= map.length || nj < 0 || nj >= map[ni].length) { // 범위를 벗어난 경우
                    continue;
                }

                if (map[ni][nj] == SEA || visited[ni][nj]) { // 바다이거나 이미 방문한 경우
                    continue;
                }

                queue.add(new Position(ni, nj)); // 큐에 추가
            }
        }

        return max;
    }

    public class Position {
        int i;
        int j;

        public Position(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
