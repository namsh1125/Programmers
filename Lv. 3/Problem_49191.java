/*
 * 프로그래머스 49191번. 순위
 * https://school.programmers.co.kr/learn/courses/30/lessons/49191
 */

import java.util.*;

class Solution {

    /**
     * 정확히 순위를 매길 수 있는 선수의 수를 반환하는 함수
     *
     * @param n       선수의 수
     * @param results 경기 결과
     * @return 정확히 순위를 매길 수 있는 선수의 수
     */
    public int solution(int n, int[][] results) {
        int[] indegree = new int[n + 1]; // 진입 차수
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        HashSet<Integer>[] loseSet = new HashSet[n + 1]; // i번째 선수가 진 선수들의 집합
        HashSet<Integer>[] winSet = new HashSet[n + 1]; // i번째 선수가 이긴 선수들의 집합

        // 그래프 초기화
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            loseSet[i] = new HashSet<>();
            winSet[i] = new HashSet<>();
        }

        // results 배열을 돌면서 그래프를 채움
        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];
            indegree[loser]++;
            graph[winner].add(loser);
        }

        // 위상이 0인 정점들 큐에 추가
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // 위상 정렬 알고리즘
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                indegree[next]--;

                // 진 선수 갱신
                loseSet[next].addAll(loseSet[cur]); // next 선수는 cur 선수가 진 선수들에게 짐 (실력에 따라 경기 결과가 달라질 수 없기 때문)
                loseSet[next].add(cur); // next 선수는 cur 선수에게 짐

                // 만약 위상이 0이면 큐에 추가
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        // 이긴 선수들 갱신
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (loseSet[j].contains(i)) { // j 선수가 i 선수에게 졌다면
                    winSet[i].add(j); // i 선수는 j 선수에게 이김
                }
            }
        }

        // i가 이긴 사람들 + i가 진 사람들 == n - 1 이면 순위를 매길 수 있음
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (loseSet[i].size() + winSet[i].size() == n - 1) {
                answer++;
            }
        }

        return answer;
    }
}
