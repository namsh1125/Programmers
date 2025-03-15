/*
 * 백준 388353번. 지게차와 크레인
 * https://school.programmers.co.kr/learn/courses/30/lessons/388353
 */

import java.util.ArrayDeque;
import java.util.ArrayList;

class Solution {

    private final int[] di = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    private final int[] dj = {0, 0, -1, 1}; // 상, 하, 좌, 우

    /**
     * 컨테이너를 출고하고 남은 컨테이너의 수를 구하는 메서드
     *
     * @param storage  처음 물류창고에 놓인 컨테이너의 정보를 담은 1차원 문자열 배열
     * @param requests 출고할 컨테이너의 종류와 출고방법을 요청 순서대로 담은 1차원 문자열 배열
     * @return 모든 요청을 순서대로 완료한 후 남은 컨테이너의 수
     */
    public int solution(String[] storage, String[] requests) {
        // 입력받은 storage를 저장할 그래프
        // +2를 한 이유는, 그래프 외각을 추가하기 위함
        Character[][] graph = new Character[storage.length + 2][storage[0].length() + 2];

        // 입력받은 storage를 이차원 그래프로 전환
        for (int i = 0; i < storage.length; i++) {
            for (int j = 0; j < storage[i].length(); j++) {
                graph[i + 1][j + 1] = storage[i].charAt(j);
            }
        }

        // request를 돌며 컨테이너 제거
        for (String request : requests) {
            removeContainer(graph, request);
        }

        // 남은 컨테이너 수 계산
        int answer = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] != null) {
                    answer++;
                }
            }
        }

        return answer;
    }

    /**
     * 컨테이너를 제거하는 메서드
     *
     * @param graph
     * @param request
     */
    private void removeContainer(Character[][] graph, String request) {
        if (request.length() == 1) { // request가 알파벳 하나인 경우
            removeContainerByForklift(graph, request.charAt(0));
        } else { // request가 같은 알파벳이 두 번 반복된 경우
            removeContainerByCrane(graph, request.charAt(0));
        }
    }

    /**
     * 지개차로 컨테이너를 제거하는 메서드
     *
     * @param graph
     * @param request
     */
    private void removeContainerByForklift(Character[][] graph, char request) {
        // 그래프 외각을 돌며, 현재 접근 가능한 컨테이너를 구한다.
        ArrayList<Point> accessibleContainers = new ArrayList<>();

        // BFS로 접근 가능한 컨테이너를 구한다.
        boolean[][] visited = new boolean[graph.length][graph[0].length];
        ArrayDeque<Point> queue = new ArrayDeque<>();

        queue.add(new Point(0, 0));

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int i = point.i;
            int j = point.j;

            if (graph[point.i][point.j] != null || visited[i][j]) {
                continue;
            } else {
                visited[i][j] = true;
            }

            // 상하좌우를 탐색
            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];

                // 그래프의 범위를 벗어나지 않고, 방문하지 않은 곳인 경우
                if (isInRange(ni, nj, graph) && !visited[ni][nj]) {
                    if (graph[ni][nj] != null) { // 접근 가능한 컨테이너인 경우
                        accessibleContainers.add(new Point(ni, nj));
                    }

                    queue.add(new Point(ni, nj));
                }
            }
        }

        // 접근 가능한 컨테이너 중, 출고 요청이 들어온 컨테이너인 경우 제거한다.
        for (Point point : accessibleContainers) {
            if (graph[point.i][point.j] != null && graph[point.i][point.j] == request) {
                graph[point.i][point.j] = null;
            }
        }

    }

    /**
     * 그래프의 범위를 벗어나지 않는지 확인하는 메서드
     *
     * @param i
     * @param j
     * @param graph
     * @return
     */
    private boolean isInRange(int i, int j, Character[][] graph) {
        return i >= 0 && i < graph.length && j >= 0 && j < graph[0].length;
    }

    /**
     * 크레인으로 컨테이너를 제거하는 메서드
     *
     * @param graph
     * @param request
     */
    private void removeContainerByCrane(Character[][] graph, char request) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] != null && graph[i][j] == request) {
                    graph[i][j] = null;
                }
            }
        }
    }

}

class Point {
    int i;
    int j;

    public Point(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
