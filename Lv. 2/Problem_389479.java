/*
 * 백준 389479번. 서버 증설 횟수
 * https://school.programmers.co.kr/learn/courses/30/lessons/389479
 */

import java.util.ArrayDeque;

class Solution {

    /**
     * 서버 증설 횟수를 구하는 메소드
     *
     * @param players 0시에서 23시까지의 시간대별 게임 이용자의 수를 나타내는 1차원 정수 배열 (1 <= players.length <= 24, 1 <= players[i] <= 1000)
     * @param m       서버 한 대로 감당할 수 있는 최대 이용자의 수 (1 <= m <= 1000)
     * @param k       서버 한 대가 운영 가능한 시간 (1 <= k <= 24)
     * @return 모든 게임 이용자를 감당하기 위한 최소 서버 증설 횟수
     */
    public int solution(int[] players, int m, int k) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int answer = 0;

        for (int i = 0; i < players.length; i++) {
            int player = players[i]; // i시 ~ i+1시까지의 이용자 수

            while (!queue.isEmpty() && queue.peek() + k <= i) { // 서버가 운영 가능한 시간을 넘어간 경우
                queue.poll(); // 서버를 제거
            }

            int workingServer = queue.size(); // 현재 서버가 돌아가고 있는 서버의 갯수
            if ((workingServer + 1) * m <= player) { // 현재 가동중인 서버로 이용자를 처리할 수 없는 경우
                while ((workingServer + 1) * m <= player) { // 부족한 서버의 갯수만큼 서버를 추가
                    queue.add(i); // 서버 추가 시간 추가
                    workingServer++; // 서버 추가
                    answer++; // 서버 추가 횟수 증가
                }
            }
        }

        return answer;
    }
}
