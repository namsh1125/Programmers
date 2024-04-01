/*
 * 프로그래머스 77485번. 행렬 테두리 회전하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/77485
 */

import java.util.ArrayList;

class Solution {

    /**
     * 회전 후 각 회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자들을 순서대로 담긴 배열을 반환하는 메소드
     *
     * @param rows    행렬의 세로 길이
     * @param columns 행렬의 가로 길이
     * @param queries 회전들의 목록
     * @return 회전 후 각 회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자들을 순서대로 담긴 배열
     */
    public int[] solution(int rows, int columns, int[][] queries) {
        // 회전을 수행할 행렬을 초기화한다.
        int[][] matrix = initMatrix(rows, columns);

        // 각 회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자들을 저장할 리스트
        ArrayList<Integer> answer = new ArrayList<>();

        // 각 회전에 대해 회전을 수행하고 회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자를 추가한다.
        for (int[] query : queries) {
            answer.add(rotateMatrix(matrix, query));
        }

        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    /**
     * 주어진 행렬의 테두리를 시계방향으로 회전시키고 회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자를 반환하는 메소드
     *
     * @param matrix 회전을 수행할 행렬
     * @param query  회전할 테두리의 정보
     * @return 회전 후 테두리의 숫자들 중 가장 작은 숫자
     */
    private int rotateMatrix(int[][] matrix, int[] query) {
        int x1 = query[0] - 1;
        int y1 = query[1] - 1;
        int x2 = query[2] - 1;
        int y2 = query[3] - 1;

        int temp = matrix[x1][y1];
        int min = temp;

        for (int i = x1; i < x2; i++) {
            matrix[i][y1] = matrix[i + 1][y1];
            min = Math.min(min, matrix[i][y1]);
        }

        for (int i = y1; i < y2; i++) {
            matrix[x2][i] = matrix[x2][i + 1];
            min = Math.min(min, matrix[x2][i]);
        }

        for (int i = x2; i > x1; i--) {
            matrix[i][y2] = matrix[i - 1][y2];
            min = Math.min(min, matrix[i][y2]);
        }

        for (int i = y2; i > y1; i--) {
            matrix[x1][i] = matrix[x1][i - 1];
            min = Math.min(min, matrix[x1][i]);
        }

        matrix[x1][y1 + 1] = temp;

        return min;
    }

    /**
     * 주어진 크기의 행렬을 초기화하는 메소드
     *
     * @param rows    행렬의 세로 길이
     * @param columns 행렬의 가로 길이
     * @return 초기화된 행렬
     */
    private int[][] initMatrix(int rows, int columns) {
        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = i * columns + j + 1;
            }
        }

        return matrix;
    }
}
