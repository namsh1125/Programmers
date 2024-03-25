/*
 * 프로그래머스 138476번. 성격 유형 검사하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/138476
 */

import java.util.*;

class Solution {

    public int solution(int k, int[] tangerine) {
        // 최대한 같은 크기의 귤을 상자에 담는다면 가장 적은 종류의 귤을 담을 수 있다.

        // 귤의 개수를 저장하는 해시맵
        Map<Integer, Integer> map = new HashMap<>();
        for (int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        // 반복문을 돌며 귤이 1개 이상인 경우 우선순위 큐에 넣는다.
        // 큐는 귤의 개수가 많은 순서대로 정렬된다.
        PriorityQueue<Tangerine> pq = new PriorityQueue<>((a, b) -> b.count - a.count);

        for (int key : map.keySet()) {
            pq.add(new Tangerine(key, map.get(key)));
        }

        int answer = 0;

        // 우선순위 큐에서 꺼내면서 상자에 담을 수 있는 귤의 개수를 계산한다.
        while (!pq.isEmpty()) {
            Tangerine t = pq.poll();
            k -= t.count;
            answer++;

            // 상자에 담을 수 있는 귤의 개수가 0 이하인 경우 반복문을 종료한다.
            if (k <= 0) {
                break;
            }
        }

        return answer;
    }

    class Tangerine {
        int size;
        int count;

        public Tangerine(int size, int count) {
            this.size = size;
            this.count = count;
        }
    }
}
