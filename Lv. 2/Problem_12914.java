/*
 * 프로그래머스 12914번. 멀리 뛰기
 * https://school.programmers.co.kr/learn/courses/30/lessons/12914
 */

class Solution {

    /**
     * @param n 멀리뛰기에 사용될 칸의 수
     * @return 끝에 도달하는 방법을 1234567를 나눈 나머지
     */
    public long solution(int n) {
        if (n <= 2) return n; // 1칸, 2칸일 때는 그대로 반환 (1칸일 때는 1가지 방법, 2칸일 때는 2가지 방법)

        int[] count = new int[n + 1];

        // 1칸일 때는 1가지 방법, 2칸일 때는 2가지 방법
        count[1] = 1;
        count[2] = 2; // 1칸 + 1칸, 2칸

        // n칸일 때는 n-1칸 + 1칸, n-2칸 + 2칸
        for (int i = 3; i <= n; i++) {
            count[i] = (count[i - 1] + count[i - 2]) % 1234567;
        }

        return count[n];
    }
}
