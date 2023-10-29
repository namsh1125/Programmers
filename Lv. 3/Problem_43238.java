/*
 * 프로그래머스 43238번. 입국심사
 * https://school.programmers.co.kr/learn/courses/30/lessons/43238
 */

import java.util.Arrays;

class Solution {
    /**
     * 모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 return하는 solution 함수
     *
     * @param n     입국심사를 기다리는 사람 수 (1 <= n <= 1_000_000_000)
     * @param times 각 심사관이 한 명을 심사하는데 걸리는 시간이 들어있는 배열 (1 <= time <= 1_000_000_000, 최대 100_000명의 심사관)
     * @return
     */
    public long solution(int n, int[] times) {
        long answer = 0;

        long start = 0;
        long end = (long) Arrays.stream(times).max().getAsInt() * n; // 최대 심사 시간. 최대 입국 심사 시간 * 기다리는 사람 수

        while (start <= end) {
            long mid = (start + end) / 2;

            long passed = 0; // mid 시간동안 심사한 사람 수
            for (int time : times) {
                passed += mid / time; // i번째 심사관이 심사한 사람 수 추가
                if (passed > n) break; // 모든 사람을 다 심사한 경우 반복문 종료
            }

            if (passed >= n) end = mid - 1; // 모든 사람을 다 심사하고도 남은 경우
            else start = mid + 1; // 모든 사람을 다 심사하지 못 한 경우
        }

        return start;
    }
}
