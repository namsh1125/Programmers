/*
 * 프로그래머스 147354번. 테이블 해시 함수
 * https://school.programmers.co.kr/learn/courses/30/lessons/147354
 */

import java.util.Arrays;

class Solution {

    /**
     * 테이블의 해시 값을 반환하는 함수
     *
     * @param data     2차원 배열
     * @param col      컬럼
     * @param rowBegin 시작 행
     * @param rowEnd   끝 행
     * @return 해시 값
     */
    public int solution(int[][] data, int col, int rowBegin, int rowEnd) {
        // 1. data를 col번째 컬럼(세로)을 기준으로 정렬
        // col번째 컬럼(세로)을 기준으로 오름차순 정렬
        // 값이 동일한 경우, 첫 번재 컬럼(세로)를 기준으로 내림차순 정렬
        Arrays.sort(data, (a, b) -> {
            if (a[col - 1] == b[col - 1]) {
                return b[0] - a[0];
            }
            return a[col - 1] - b[col - 1];
        });

        // 2. 정렬된 데이터에서 rowBegin <= i <= row_end까지의 S_i의 합을 구함
        // S_i는 1번에서 정렬된 데이터에서 i 번째 행의 튜플에 대해 각 컬럼의 값을 i 로 나눈 나머지들의 합
        // 3. bitwise XOR 연산을 수행하여 해시 값을 반환
        int answer = 0;

        for (int row = rowBegin; row <= rowEnd; row++) {
            // S_i를 구함
            int finalRow = row;
            int sum = Arrays.stream(data[row - 1])
                    .map(i -> i % (finalRow))
                    .sum();

            // bitwise XOR 연산을 수행
            answer ^= sum;
        }

        return answer;
    }
}
