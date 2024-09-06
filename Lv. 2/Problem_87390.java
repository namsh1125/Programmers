/*
 * 프로그래머스 87390번. n^2 배열 자르기
 * https://school.programmers.co.kr/learn/courses/30/lessons/87390
 */

class Solution {

    /**
     * 주어진 과정대로 만들어진 1차원 배열을 반환하는 메소드
     * - n행 n열 크기의 비어있는 2차원 배열을 만든다.
     * - i = 1, 2, 3, ..., n에 대해서, 1행 1열부터 i행 i열까지의 영역 내의 모든 빈 칸을 숫자 i로 채운다.
     * - 1행, 2행, ..., n행을 잘라내어 모두 이어붙인 새로운 1차원 배열을 만든다.
     * - 새로운 1차원 배열을 arr이라 할 때, arr[left], arr[left+1], ..., arr[right]만 남기고 나머지는 지운다.
     *
     * @param n     2차원 배열의 크기 (1 ≤ n ≤ 10^7)
     * @param left  1차원 배열에서 남기고 싶은 숫자들의 시작 인덱스 (0 ≤ left ≤ right < n2, right - left < 10^5)
     * @param right 1차원 배열에서 남기고 싶은 숫자들의 끝 인덱스 (0 ≤ left ≤ right < n2, right - left < 10^5)
     * @return 1차원 배열에 남긴 숫자들
     */
    public int[] solution(int n, long left, long right) {
        // n의 범위가 크기 때문에 조건대로 배열을 만들어서 풀기에는 시간이 오래 걸릴 수 있음

        // 1행 1열 - 1
        // 2행 1열 / 2행 2열 / 1행 2열 - 2
        // 3행 1열 / 3행 2열 / 3행 3열 / 2행 3열 / 1행 3열 - 3
        // 4행 1열 / 4행 2열 / 4행 3열 / 4행 4열 / 3행 4열 / 2행 4열 / 1행 4열 - 4

        // i행 j열의 인덱스 -> n * (i - 1) + j - 1
        // 1행 1열 - 0번째 인덱스 (4 * 0)
        // 1행 2열 - 1번째 인덱스 (4 * 0 + 1)
        // 1행 3열 - 2번째 인덱스 (4 * 0 + 2)
        // 1행 4열 - 3번째 인덱스 (4 * 0 + 3)
        // 2행 1열 - 4번째 인덱스 (4 * 1)

        int[] answer = new int[(int) (right - left + 1)];

        for (long i = left; i <= right; i++) {
            // i번째 인덱스의 행과 열을 구함
            // row행 col열 -> i = n * (row - 1) + col - 1
            long col = (i + 1) % n == 0 ? n : (i + 1) % n; // 열
            long row = (i + 1 - col) / n + 1; // 행

            // row행 col열의 숫자를 구함
            answer[(int) (i - left)] = Math.max((int) row, (int) col);
        }

        return answer;
    }
}
