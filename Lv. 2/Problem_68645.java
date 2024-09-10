/*
 * 프로그래머스 68645번. 삼각 달팽이
 * https://school.programmers.co.kr/learn/courses/30/lessons/68645
 */

import java.util.ArrayList;

class Solution {

    private final int[] di = {1, 0, -1}; // 좌하단, 우, 좌상단 순서 (아래, 우측, 우상단)
    private final int[] dj = {0, 1, -1}; // 좌하단, 우, 좌상단 순서 (아래, 우측, 우상단)

    /**
     * 삼각 달팽이를 만들어 첫 행부터 마지막 행까지 모두 순서대로 합친 새로운 배열을 반환하는 메소드
     *
     * @param n 밑변의 길이와 높이 (1 <= n <= 1,000)
     * @return 맨 위 꼭짓점부터 반시계 방향으로 달팽이 채우기를 진행한 후, 첫 행부터 마지막 행까지 모두 순서대로 합친 새로운 배열
     */
    public int[] solution(int n) {
        int[][] triangle = new int[n][n]; // 삼각 달팽이

        // 삼각 달팽이를 채운다
        int i = 0, j = 0, num = 1; // 시작 위치, 채울 숫자
        int dir = 0; // 방향 (0: 아래, 1: 우, 2: 상)
        while (num <= n * (n + 1) / 2) { // 삼각 달팽이를 다 채운 경우, 반복문 탈출
            // 삼각 달팽이를 채운다
            triangle[i][j] = num++;

            // 다음 위치로 이동한다
            int ni = i + di[dir];
            int nj = j + dj[dir];

            if (!isInRange(ni, nj, n) || triangle[ni][nj] != 0) { // 다음 위치가 범위를 벗어나거나 이미 채워진 경우
                dir = (dir + 1) % 3; // 방향을 변경한다
                ni = i + di[dir];
                nj = j + dj[dir];
            }

            i = ni;
            j = nj;
        }

        // 삼각 달팽이를 1차원 배열로 변환하여 반환한다.
        ArrayList<Integer> answer = new ArrayList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c <= r; c++) {
                answer.add(triangle[r][c]);
            }
        }

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private boolean isInRange(int i, int j, int n) {
        return 0 <= i && i < n && 0 <= j && j <= i;
    }
}
