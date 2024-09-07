/*
 * 프로그래머스 87377번. 교점에 별 만들기
 * https://school.programmers.co.kr/learn/courses/30/lessons/87377
 */

import java.util.ArrayList;
import java.util.Arrays;

class Solution {

    /**
     * 별의 좌표를 나타내는 문자열 배열을 반환하는 메소드
     *
     * @param line 직선 A, B, C에 대한 정보가 담긴 배열 (Ax + By + C = 0)
     * @return 모든 별을 포함하는 최소 사각형
     */
    public String[] solution(int[][] line) {
        // 모든 직선에 대한 교점을 구한다.
        ArrayList<Position> positions = new ArrayList<>(); // 교점을 저장할 리스트

        // 교점 중 정수 좌표를 구한다.
        // Ax + By + E = 0, Cx + Dy + F = 0 일 때 교점 (x, y)는 (BF - ED) / (AD - BC), (EC - AF) / (AD - BC)
        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                long A = line[i][0];
                long B = line[i][1];
                long E = line[i][2];
                long C = line[j][0];
                long D = line[j][1];
                long F = line[j][2];

                if (A * D - B * C == 0) { // 두 직선이 평행하면 교점이 없다.
                    continue;
                }

                if ((B * F - E * D) % (A * D - B * C) == 0 && (E * C - A * F) % (A * D - B * C) == 0) { // 정수 좌표인지 확인
                    long x = (B * F - E * D) / (A * D - B * C);
                    long y = (E * C - A * F) / (A * D - B * C);

                    positions.add(new Position((int) x, (int) y));
                }
            }
        }

        // 모든 별을 포함하는 최소 사각형을 구한다. (빈 공간: ., 별: *)
        int minX = positions.stream().mapToInt(p -> p.x).min().getAsInt();
        int maxX = positions.stream().mapToInt(p -> p.x).max().getAsInt();
        int minY = positions.stream().mapToInt(p -> p.y).min().getAsInt();
        int maxY = positions.stream().mapToInt(p -> p.y).max().getAsInt();

        // 최소 사각형을 구성하는 문자열 배열을 반환한다.
        char[][] map = new char[maxY - minY + 1][maxX - minX + 1];
        for (char[] row : map) {
            Arrays.fill(row, '.');
        }

        // 별의 좌표를 찍는다.
        // 좌표를 표현하기 쉽게 하기 위해, 좌표를 평행이동시킨다. -> 좌상단이 (0, 0)이 되도록
        for (Position position : positions) {
            int x = position.x - minX;
            int y = maxY - position.y;

            map[y][x] = '*';
        }

        return Arrays.stream(map)
                .map(String::new)
                .toArray(String[]::new);
    }

    public class Position {
        int x; // x 좌표
        int y; // y 좌표

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
