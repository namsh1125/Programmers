/*
 * 프로그래머스 42626번. 더 맵게
 * https://school.programmers.co.kr/learn/courses/30/lessons/42626
 */

import java.util.PriorityQueue;

class Solution {

    /**
     * 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 구하는 메소드
     *
     * @param scoville 각 음식의 스코빌 지수를 담은 배열
     * @param K        모든 음식의 목표 스코빌 지수
     * @return 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수
     */
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int s : scoville) {
            queue.add(s);
        }

        int answer = 0;
        while (queue.peek() < K) {
            if (queue.size() == 1) {
                return -1;
            }

            int a = queue.poll();
            int b = queue.poll();
            queue.add(a + b * 2);
            answer++;
        }

        return answer;
    }
}
