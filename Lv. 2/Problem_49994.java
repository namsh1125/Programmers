/*
 * 프로그래머스 49994번. 방문 길이
 * https://school.programmers.co.kr/learn/courses/30/lessons/49994
 */

class Solution {

    private final int[] dy = {1, -1, 0, 0}; // 상하좌우
    private final int[] dx = {0, 0, -1, 1}; // 상하좌우

    /**
     * 주어진 방향으로 이동하며 처음으로 방문한 길의 수를 반환한다.
     *
     * @param dirs 명령어
     * @return 게임 캐릭터가 처음 걸어본 길의 길이
     */
    public int solution(String dirs) {
        Position position = new Position(0, 0); // 시작 위치
        boolean[][][][] visited = new boolean[11][11][11][11]; // 방문 여부 체크
        int answer = 0; // 방문한 길의 수

        for (int i = 0; i < dirs.length(); i++) {
            int direction = switch (dirs.charAt(i)) {
                case 'U' -> 0;
                case 'D' -> 1;
                case 'L' -> 2;
                case 'R' -> 3;
                default -> throw new IllegalStateException("Unexpected value: " + dirs.charAt(i));
            };

            int x = position.x;
            int y = position.y;
            int nx = position.x + dx[direction];
            int ny = position.y + dy[direction];

            if (!isInRange(nx, ny)) {
                continue;
            }

            if (!visited[x + 5][y + 5][nx + 5][ny + 5]) {
                visited[x + 5][y + 5][nx + 5][ny + 5] = true;
                visited[nx + 5][ny + 5][x + 5][y + 5] = true;
                answer++;
            }

            position.move(nx, ny);
        }

        return answer;
    }

    private boolean isInRange(int i, int j) {
        return i >= -5 && i <= 5 && j >= -5 && j <= 5;
    }

    public class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void move(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
