/*
 * 프로그래머스 142085번. 디펜스 게임
 * https://school.programmers.co.kr/learn/courses/30/lessons/142085
 */

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    /**
     * 준호가 무족권을 사용하여 최대로 막을 수 있는 라운드 수를 구하는 함수
     *
     * @param n     준호가 처음 가지고 있는 병사의 수 (1 ≤ n ≤ 1,000,000,000)
     * @param k     사용 가능한 무적권의 횟수 (1 ≤ k ≤ 500,000)
     * @param enemy 매 라운드마다 공격해오는 적의 수가 순서대로 담긴 정수 배열 (1 ≤ enemy의 길이 ≤ 1,000,000)
     * @return 막을 수 있는 라운드 수 (1 ≤ enemy[i] ≤ 1,000,000)
     */
    public int solution(int n, int k, int[] enemy) {
        // 최대로 진행할 수 있는 라운드의 경우는 준호가 진행할 수 있는 라운드에서 적이 가장 많이 공격해올 때 무족권을 사용하는 경우이다.
        // 따라서 준호가 무족권을 사용하는 경우를 제외하고 최대로 진행할 수 있는 라운드의 수를 구하고, 이후 무족권을 사용하는 경우를 고려하여 최대로 막을 수 있는 라운드의 수를 구한다.
        int maxRound = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder()); // 적의 수를 저장할 우선순위 큐 (적의 수가 많은 순서대로 정렬)

        for (int i = 1; i <= enemy.length; i++) {
            if (k == 0 && n < enemy[i - 1]) { // 무적권을 사용할 수 없고 현재 라운드를 진행하기 어려운 경우
                break; // 현재 라운드를 진행할 수 없으므로 반복문을 종료한다.
            }

            // 현재 라운드를 진행할 수 있는 경우
            queue.add(enemy[i - 1]); // i번째 적의 수를 우선순위 큐에 추가하고,

            if (k > 0 && n < enemy[i - 1]) { // 무적권을 보유한 상태에서, 무적권 없이 현재 라운드를 진행하기 어려운 경우
                n += queue.remove(); // 무적권을 사용하여 가장 많은 적을 제거하는 병사 수를 추가하고,
                k--; // 무적권을 사용했으므로 무적권의 횟수를 감소시킨다.
            }

            n -= enemy[i - 1]; // 현재 라운드를 진행하고 남은 병사의 수를 계산한다.
            maxRound++; // 현재 라운드를 진행할 수 있으므로 최대 라운드 수를 증가시킨다.
        }

        return maxRound;
    }
}
