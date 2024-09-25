/*
 * 프로그래머스 12985번. 예상 대진표
 * https://school.programmers.co.kr/learn/courses/30/lessons/12985
 */

class Solution {

    /**
     * a와 b가 몇 번째 라운드에서 만나는지 반환하는 함수
     *
     * @param n 게임 참가자 수
     * @param a 참가자 번호
     * @param b 참가자 번호
     * @return a와 b가 몇 번째 라운드에서 만나는지
     */
    public int solution(int n, int a, int b) {
        // 고려하기 쉽게 0부터 시작하도록 변경 (1번 참가자 -> 0번 참가자)
        a -= 1;
        b -= 1;

        // 0번 참가자 & 1번 참가자 / 2번 참가자 & 3번 참가자 / ... / n-2번 참가자 & n-1번 참가자
        // n을 2로 나누었을 때 몫이 같은 두 참가자가 만남

        int round = 1;
        while (a / 2 != b / 2) {
            round++;
            a /= 2;
            b /= 2;
        }

        return round;
    }
}
