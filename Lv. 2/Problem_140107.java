/*
 * 프로그래머스 140107번. 점 찍기
 * https://school.programmers.co.kr/learn/courses/30/lessons/140107
 */

class Solution {

    public long solution(long k, long d) {
        // x^2 + y^2 <= d^2 내에 있는 점 (a, b)에서 a, b가 k의 배수여야 한다.
        long answer = 0;

        for (int a = 0; a <= d; a += k) {
            // x축에 있는 좌표 a라고 가정했을 때, y축에 있는 좌표 b를 구한다.
            long bk = (long) Math.floor(Math.sqrt(Math.pow(d, 2) - Math.pow(a, 2))); // b * k의 최대값. 다만, b * k는 정수
            answer += bk / k + 1; // 축에 있는 좌표도 포함해야 하므로 +1
        }

        return answer;
    }
}
