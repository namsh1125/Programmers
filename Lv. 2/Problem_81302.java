/*
 * 프로그래머스 81302번. 거리두기 확인하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/81302
 */


import java.util.ArrayDeque;
import java.util.ArrayList;

class Solution {

    private static final int[] di = {0, 0, 1, -1};
    private static final int[] dj = {1, -1, 0, 0};

    private static final char PERSON = 'P'; // 응시자
    private static final char EMPTY = 'O'; // 빈 테이블
    private static final char PARTITION = 'X'; // 파티션

    /**
     * @param places 자리에 앉아있는 응시자들의 정보와 대기실 구조를 대기실별로 담은 2차원 문자열 배열
     * @return 각 대기실별로 거리두기를 지키고 있는지 여부를 담은 1차원 정수 배열
     */
    public int[] solution(String[][] places) {
        ArrayList<Integer> answer = new ArrayList<>();

        for (String[] place : places) {
            answer.add(isSafe(place));
        }

        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    /**
     * 대기실의 응시자들이 거리두기를 지키고 있는지 확인하는 메소드
     *
     * @param place 대기실의 응시자들의 정보와 대기실 구조를 담은 2차원 문자열 배열
     * @return 거리두기를 지키고 있다면 1, 지키지 않았다면 0
     */
    private int isSafe(String[] place) {
        char[][] graph = toCharArray(place);

        // 방문 처리 여부를 저장하는 배열
        boolean[][] visited = new boolean[graph.length][graph[0].length];

        // 반복문을 돌며 응시자들의 위치를 찾아내어 거리두기를 지키고 있는지 확인
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] == PERSON) {
                    if (!isSafe(graph, visited, new Position(i, j))) {
                        return 0;
                    }
                }
            }
        }

        return 1;
    }

    /**
     * 응시자의 위치를 기준으로 거리두기를 지키고 있는지 확인하는 메소드
     *
     * @param graph   대기실 정보
     * @param visited 방문 처리 여부를 저장하는 배열
     * @param person  응시자의 위치
     * @return 거리두기를 지키고 있다면 true, 지키지 않았다면 false
     */
    private boolean isSafe(char[][] graph, boolean[][] visited, Position person) {
        // 현재 위치와 거리를 저장하는 Queue
        ArrayDeque<Pair<Position, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(person, 0));

        // Queue가 빌 때까지
        while (!queue.isEmpty()) {
            // 현재 위치와 거리를 가져온다.
            Pair<Position, Integer> current = queue.poll();
            Position currentPos = current.getFirst();
            int distance = current.getSecond();

            // 방문처리를 한다.
            visited[currentPos.i][currentPos.j] = true;

            // 상하좌우를 돌며
            for (int k = 0; k < 4; k++) {
                // 다음 위치를 계산하고
                int ni = currentPos.i + di[k];
                int nj = currentPos.j + dj[k];

                // 다음 위치가 대기실을 벗어나거나 방문한 적이 있으면 넘어간다
                if (ni < 0 || nj < 0 || ni >= graph.length || nj >= graph[0].length || visited[ni][nj]) {
                    continue;
                }

                // 다음 위치가 사람인 경우, 맨해튼 거리를 확인하고 2 이하이면 거리두기를 지키지 않은 것이므로 false를 반환한다.
                if (graph[ni][nj] == PERSON && distance + 1 <= 2) {
                    return false;
                }

                // 다음 위치가 빈 테이블인 경우, Queue에 추가한다.
                if (graph[ni][nj] == EMPTY) {
                    queue.add(new Pair<>(new Position(ni, nj), distance + 1));
                }
            }
        }

        return true;
    }

    /**
     * String 배열을 2차원 char 배열로 변환하는 메소드
     *
     * @param place String 배열로 구성된 대기실 정보
     * @return 2차원 char 배열로 변환된 대기실 정보
     */
    private char[][] toCharArray(String[] place) {
        char[][] room = new char[place.length][place[0].length()];

        for (int i = 0; i < place.length; i++) {
            room[i] = place[i].toCharArray();
        }

        return room;
    }

    /**
     * 두 개의 값을 저장하는 클래스
     */
    class Pair<T, U> {
        T first;
        U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public U getSecond() {
            return second;
        }
    }

    /**
     * 위치 정보를 담고 있는 클래스
     */
    class Position {
        int i, j;

        public Position(int i, int j) {
            this.i = i;
            this.j = j;
        }

        private int getDistance(Position other) {
            return Math.abs(this.i - other.i) + Math.abs(this.j - other.j);
        }
    }

}
