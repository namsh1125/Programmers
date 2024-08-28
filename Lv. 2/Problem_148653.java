/*
 * 프로그래머스 148653번. 마법의 엘리베이터
 * https://school.programmers.co.kr/learn/courses/30/lessons/148653
 */

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    /**
     * 0층으로 가기 위해 필요한 마법의 돌의 최소값을 구하는 메소드
     *
     * @param storey 현재 층
     * @return 0층으로 가기 위해 필요한 마법의 돌의 최소값
     */
    public int solution(int storey) {
        boolean[] visited = new boolean[100_000_000 + 1];

        PriorityQueue<Position> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.count));
        queue.add(new Position(storey));

        while (!queue.isEmpty()) {
            Position current = queue.remove();

            // 100,000,000층 이상은 무시
            if (current.storey > 100_000_000) {
                continue;
            }

            // 방문 여부 확인
            if (visited[current.storey]) {
                continue;

            } else {
                visited[current.storey] = true;

                // 0층 도착여부 확인
                if (current.storey == 0) {
                    return current.count;
                }
            }

            // 0이 아닌 가장 큰 자리수 찾기
            int i = 0;
            while (true) {
                int value = (current.storey % (int) Math.pow(10, i + 1)) / (int) Math.pow(10, i); // 10^i자리의 수

                if (value != 0) {
                    queue.add(new Position(current.storey - value * (int) Math.pow(10, i), current.count + value)); // 내려오기
                    queue.add(new Position(current.storey + (10 - value) * (int) Math.pow(10, i), current.count + 10 - value)); // 올라가기
                    break;
                }

                i++;
            }
        }

        return -1;
    }

    class Position {
        int storey; // 현재 층
        int count; // 마법의 돌 사용 횟수

        public Position(int storey) {
            this.storey = storey;
            this.count = 0;
        }

        public Position(int storey, int count) {
            this.storey = storey;
            this.count = count;
        }
    }

}
