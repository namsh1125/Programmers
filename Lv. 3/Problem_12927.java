/*
 * 프로그래머스 12927번. 야근 지수
 * https://school.programmers.co.kr/learn/courses/30/lessons/12927
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    /**
     * 야근 피로도를 최소화한 값을 반환하는 함수
     *
     * @param n     퇴근까지 남은 시간
     * @param works 각 일에 대한 작업량
     * @return 야근 피로도를 최소화한 값
     */
    public long solution(int n, int[] works) {
        int sum = Arrays.stream(works).sum();
        if (sum <= n) { // 퇴근까지 남은 작업을 모두 처리할 수 있는 경우
            return 0;
        }

        // 퇴근까지 남은 작업을 처리할 수 없는 경우
        // 남은 작업량을 작업량이 큰 순서대로 처리하면서 작업량을 줄여나간다.
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) {
            pq.add(work);
        }

        int time = n;
        while (time > 0) {
            int work = pq.poll();
            pq.add(work - 1);
            time--;
        }

        // 야근 피로도를 계산한다.
        long answer = 0;
        while (!pq.isEmpty()) {
            int work = pq.poll();
            answer += work * work;
        }

        return answer;
    }
}
