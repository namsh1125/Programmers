/*
 * 프로그래머스 131130번. 혼자 놀기의 달인
 * https://school.programmers.co.kr/learn/courses/30/lessons/131130
 */

class Solution {

    /**
     * 상자 안에 들어있는 카드 번호가 순서대로 담긴 배열 cards가 매개변수로 주어질 때, 범희가 이 게임에서 얻을 수 있는 최고 점수를 구하는 메소드
     *
     * @param cards 상자 안에 들어있는 카드 번호가 순서대로 담긴 배열
     * @return 범희가 이 게임에서 얻을 수 있는 최고 점수
     */
    public int solution(int[] cards) {
        int answer = 0;

        for (int i = 1; i <= cards.length; i++) {
            boolean[] visited = new boolean[cards.length + 1]; // i번째 상자를 뽑았는지 확인하는 배열

            // 1번 상자 그룹의 점수를 구한다
            int score1 = getScore(cards, i, visited);

            // 1번 상자 그룹에서 모든 카드를 뽑지 않았을 경우 2번 상자 그룹의 점수를 구하고 게임의 점수를 구한다
            if (score1 != cards.length) {
                // 방문하지 않은 카드를 뽑아 2번 상자 그룹의 점수를 구한다
                for (int j = 1; j <= cards.length; j++) {
                    if (!visited[j]) {
                        int score2 = getScore(cards, j, visited.clone()); // 2번 상자 그룹의 점수를 구한다
                        answer = Math.max(answer, score1 * score2); // 게임의 점수를 구한다
                    }
                }
            }
        }

        return answer;
    }

    // n번째 상자를 뽑았을 때 얻을 수 있는 점수
    public int getScore(int[] cards, int n, boolean[] visited) {
        int score = 0;

        while (!visited[n]) {
            visited[n] = true; // n번째 상자를 뽑았음을 표시
            score++; // 점수를 1점 올림
            n = cards[n - 1]; // 다음 상자 번호로 이동
        }

        return score;
    }
}
