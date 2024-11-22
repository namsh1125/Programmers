/*
 * 프로그래머스 340212번. [PCCP 기출문제] 2번 / 퍼즐 게임 챌린지
 * https://school.programmers.co.kr/learn/courses/30/lessons/340212
 */

class Solution {

    /**
     * @param diffs 퍼즐의 난이도를 순서대로 담은 1차원 정수 배열 (diffs[0] = 1, 1 ≤ diffs[i] ≤ 100,000)
     * @param times 퍼즐의 소요 시간을 순서대로 담은 1차원 정수 배열 (times[0] = 1, 1 ≤ times[i] ≤ 10,000)
     * @param limit 전체 제한 시간 (1 ≤ limit ≤ 10^15)
     * @return 제한 시간 내에 퍼즐을 모두 해결하기 위한 숙련도의 최솟값
     */
    public int solution(int[] diffs, int[] times, long limit) {
        int n = diffs.length; // 문제의 개수 (n ≤ 300,000)

        // 이진탐색 알고리즘을 사용하여 최적의 level값을 구한다.
        int start = 1; // level 최솟값
        int end = 100_000; // level 최댓값

        while (start <= end) {
            int mid = (start + end) / 2;

            long sum = 0L; // 전체 문제를 푸는데 걸린 시간
            for (int i = 0; i < n; i++) {
                int timePrev = i == 0 ? 0 : times[i - 1];
                sum += calcTime(diffs[i], mid, times[i], timePrev);
            }

            if (sum <= limit) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }

        return start;
    }


    /**
     * 문제를 푸는데 요구되는 시간을 계산하는 함수
     *
     * @param diff     문제의 난이도
     * @param level    나의 숙련도
     * @param timeCur  현재 문제를 시도하는데 요구되는 시간
     * @param timePrev 이전 문제를 푸는데 요구되는 시간
     * @return 문제를 푸는데 요구되는 시간
     */
    private int calcTime(int diff, int level, int timeCur, int timePrev) {
        if (diff <= level) {
            return timeCur;
        } else {
            return (diff - level) * (timeCur + timePrev) + timeCur;
        }
    }

}
