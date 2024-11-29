/*
 * 프로그래머스 131129번. 카운트 다운
 * https://school.programmers.co.kr/learn/courses/30/lessons/131129
 */

import java.util.PriorityQueue;
import java.util.TreeSet;

class Solution {
    public int[] solution(int target) {
        // 한 번의 던지기로 만들 수 있는 점수의 경우의 수
        TreeSet<Integer> possibleScores = new TreeSet<>();
        for (int i = 1; i <= 20; i++) {
            possibleScores.add(i);
            possibleScores.add(i * 2);
            possibleScores.add(i * 3);
        }
        possibleScores.add(50);

        return bfs(target, possibleScores);
    }


    private int[] bfs(int target, TreeSet<Integer> possibleScores) {
        boolean[] visited = new boolean[target + 1]; // 방문 여부

        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(0, 0, 0));

        int minCount = Integer.MAX_VALUE;
        int maxSingleOrBullCount = Integer.MIN_VALUE;

        while (!pq.isEmpty()) {
            Info info = pq.remove();
            int score = info.getScore();
            int count = info.getCount();
            int singleOrBullCount = info.getSingleOrBullCount();

            // 현재 점수가 target 점수인지 확인
            if (score == target) {
                // 최소의 던진 횟수이며 싱글 또는 불을 던진 횟수가 높은 경우를 찾음
                if (count < minCount) { // 최소의 던진 횟수인 경우
                    minCount = count;
                    maxSingleOrBullCount = singleOrBullCount;
                }

                if (count == minCount) { // 최소의 던진 횟수가 같은 경우
                    maxSingleOrBullCount = Math.max(maxSingleOrBullCount, singleOrBullCount);
                }
            }

            // 추가로 던질 필요가 있는지 확인
            if (minCount != Integer.MAX_VALUE && minCount != count) {
                continue; // 최소의 던진 횟수보다 더 많이 던진 경우는 추가 진행할 필요가 없음
            }

            // 방문 여부 처리
            if (visited[score]) {
                continue; // 이전에 만든 점수인 경우 추가 진행할 필요가 없음
            } else {
                visited[score] = true;
            }

            // 가능한 점수를 던짐
            for (int throwScore : possibleScores) {
                int nextScore = score + throwScore;
                int nextCount = count + 1;
                int nextSingleOrBullCount;

                // 싱글 또는 불을 던진 횟수를 확인
                if (isSingleOrBull(throwScore)) {
                    nextSingleOrBullCount = info.getSingleOrBullCount() + 1;
                } else {
                    nextSingleOrBullCount = info.getSingleOrBullCount();
                }

                // target 점수를 넘어가는 지 확인
                if (nextScore > target) {
                    break; // TreeSet은 정렬되어 있어, 이후에 나올 점수들은 target 점수를 넘어감
                }

                // 이전에 만든 점수인지 확인
                if (visited[nextScore]) {
                    continue; // 던진 횟수가 최소인게 중요하므로 추가 진행할 필요가 없음
                }

                // 경우의 수 추가
                pq.add(new Info(nextScore, nextCount, nextSingleOrBullCount));
            }
        }

        return new int[]{minCount, maxSingleOrBullCount};
    }

    /**
     * 던진 다트가 싱글 또는 불인지 확인하는 함수
     *
     * @param score 다트로 던진 점수
     * @return 싱글 또는 불이면 true, 아니면 false
     */
    private boolean isSingleOrBull(int score) {
        return 1 <= score && score <= 20 || score == 50;
    }
}

class Info implements Comparable<Info> {
    private int score; // 현재 점수
    private int count; // 던진 횟수
    private int singleOrBullCount; // 싱글 또는 불을 던진 횟수

    public Info(int score, int count, int singleOrBullCount) {
        this.score = score;
        this.count = count;
        this.singleOrBullCount = singleOrBullCount;
    }

    public int getScore() {
        return score;
    }

    public int getCount() {
        return count;
    }

    public int getSingleOrBullCount() {
        return singleOrBullCount;
    }

    @Override
    public int compareTo(Info o) {
        if (this.count == o.count) { // 던진 횟수가 같다면
            if (this.singleOrBullCount == o.singleOrBullCount) {
                return o.score - this.score; // 점수가 높은 순서로 정렬 (점수가 높을 수록 싱글 혹은 불을 던진 횟수가 높음)
            }

            return o.singleOrBullCount - this.singleOrBullCount; // 싱글 또는 불을 던진 횟수가 높은 순서로 정렬
        }

        return this.count - o.count; // 던진 횟수가 낮은 순서로 정렬
    }
}
