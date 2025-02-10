/*
 * 프로그래머스 12979번. 기지국 설치
 * https://school.programmers.co.kr/learn/courses/30/lessons/12979
 */

class Solution {

    /**
     * @param n        아파트의 개수
     * @param stations 현재 기지국이 설치된 아파트의 번호가 담긴 1차원 배열
     * @param w        전파의 도달 거리
     * @return 모든 아파트에 전파를 전달하기 위해 증설해야 할 기지국 개수의 최솟값
     */
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int start = 1; // 시작 위치

        for (int station : stations) {
            int end = station - w - 1; // 전파가 닿지 않는 구간의 끝

            if (end >= start) {
                int length = end - start + 1;
                answer += Math.ceil((double) length / (2 * w + 1));
            }

            start = station + w + 1; // 다음 시작 위치 갱신
        }

        // 마지막 기지국 이후의 구간 처리
        if (start <= n) {
            int length = n - start + 1;
            answer += Math.ceil((double) length / (2 * w + 1));
        }

        return answer;
    }
}
