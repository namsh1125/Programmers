/*
 * 프로그래머스 64062번. 징검다리 건너기
 * https://school.programmers.co.kr/learn/courses/30/lessons/64062
 */

class Solution {
    /**
     * @param stones 디딤돌에 적힌 숫자가 순서대로 담긴 배열 (1 <= stone <= 200_000_000, 1 <= length <= 200_000)
     * @param k      한 번에 건너뛸 수 있는 디딤돌의 최대 칸수
     * @return 최대 몇 명까지 징검다리를 건널 수 있는지
     */
    public int solution(int[] stones, int k) {
        int answer = 0;

        int start = 0;
        int end = 200_000_000; // stone의 최댓값

        while (start <= end) {
            int mid = (start + end) / 2;

            if (isAvailable(stones, k, mid)) { // 지나갈 수 있다면
                start = mid + 1;
                answer = Math.max(answer, mid);
            } else end = mid - 1; // 건널 수 없다면
        }

        return answer;
    }

    public boolean isAvailable(int[] stones, int k, int friends) {
        int skip = 0; // 못 건너는 징검다리의 개수를 저장

        for (int stone : stones) {
            if (stone < friends) // 건널 수 없다면
                skip++;
            else // 건널 수 있음
                skip = 0;

            if (skip == k) return false; // 못 건너는 징검다리의 수가 최대칸 k를 넘는 경우
        }

        return true;
    }
}
