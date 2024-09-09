/*
 * 프로그래머스 68936번. 쿼드압축 후 개수 세기
 * https://school.programmers.co.kr/learn/courses/30/lessons/68936
 */

class Solution {

    public int zeroCount = 0; // 0의 개수
    public int oneCount = 0; // 1의 개수;

    /**
     * 2차원 배열을 쿼드압축 후 배열에 최종적으로 남는 0의 개수와 1의 개수를 구하는 메소드
     *
     * @param arr 압축하고자 하는 2차원 배열
     * @return 압축 후 배열에 최종적으로 남는 0의 개수와 1의 개수
     */
    public int[] solution(int[][] arr) {
        compress(arr, 0, 0, arr.length);
        return new int[]{zeroCount, oneCount};
    }

    /**
     * 2차원 배열을 쿼드압축하는 메소드
     *
     * @param arr    압축하고자 하는 2차원 배열
     * @param startI 압축하고자 하는 범위의 시작 행
     * @param startJ 압축하고자 하는 범위의 시작 열
     * @param size   압축하고자 하는 범위의 크기
     */
    public void compress(int[][] arr, int startI, int startJ, int size) {
        if (isSameNumber(arr, startI, startJ, size)) {
            if (arr[startI][startJ] == 0) {
                zeroCount++;
            } else {
                oneCount++;
            }

        } else {
            int newSize = size / 2;
            compress(arr, startI, startJ, newSize);
            compress(arr, startI, startJ + newSize, newSize);
            compress(arr, startI + newSize, startJ, newSize);
            compress(arr, startI + newSize, startJ + newSize, newSize);
        }
    }

    /**
     * 배열의 범위 내에 있는 값들이 모두 같은지 확인하는 메소드
     *
     * @param arr    압축하고자 하는 2차원 배열
     * @param startI 확인하는 범위의 시작 행
     * @param startJ 확인하는 범위의 시작 열
     * @param size   확인하는 범위의 크기
     * @return 배열의 범위 내에 있는 값들이 모두 같으면 true, 아니면 false
     */
    public boolean isSameNumber(int[][] arr, int startI, int startJ, int size) {
        for (int i = startI; i < startI + size; i++) {
            for (int j = startJ; j < startJ + size; j++) {
                if (arr[startI][startJ] != arr[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
