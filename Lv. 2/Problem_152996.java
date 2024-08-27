/*
 * 프로그래머스 152996번. 시소 짝꿍
 * https://school.programmers.co.kr/learn/courses/30/lessons/152996
 */

class Solution {
    public long solution(int[] weights) {
        // 입력받은 무게들을 저장할 배열을 생성한다.
        long[] count = new long[100_001]; // weights의 최대 크기는 100,000
        for (int value : weights) {
            count[value]++;
        }

        // 시소 짝꿍의 수를 구한다.
        long answer = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) { // i에 해당하는 무게가 존재한다면
                // i에 해당하는 무게들 중 2개를 선택하는 경우의 수를 더한다. (같은 거리)
                answer += count[i] * (count[i] - 1) / 2; // nC2

                // i에 해당하는 무게와 4/3 (1.333...)배의 무게를 가진 시소 짝궁의 수를 구한다. (3m, 4m)
                if (i * 4 % 3 == 0 && i * 4 / 3 < count.length) {
                    answer += count[i] * count[i * 4 / 3];
                }

                // i에 해당하는 무게와 3/2 (1.5)배의 무게를 가진 시소 짝궁의 수를 구한다. (2m, 3m)
                if (i * 3 % 2 == 0 && i * 3 / 2 < count.length) {
                    answer += count[i] * count[i * 3 / 2];
                }

                // i에 해당하는 무게와 2배의 무게를 가진 시소 짝궁의 수를 구한다. (2m, 4m)
                if (i * 2 < count.length) {
                    answer += count[i] * count[i * 2];
                }
            }
        }

        // 시소 짝궁의 수를 반환한다.
        return answer;
    }
}
