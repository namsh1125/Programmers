/*
 * 프로그래머스 12949번. 행렬의 곱셈
 * https://school.programmers.co.kr/learn/courses/30/lessons/12949
 */

class Solution {

    /**
     * 주어진 두 행렬을 곱한 결과를 반환하는 메소드
     *
     * @param arr1 입력받은 첫 번째 행렬
     * @param arr2 입력받은 두 번째 행렬
     * @return 두 행렬을 곱한 결과
     */
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int row = arr1.length;
        int col = arr2[0].length;
        int[][] answer = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < arr1[0].length; k++) {
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        return answer;
    }
}
