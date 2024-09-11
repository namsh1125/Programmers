/*
 * 프로그래머스 250135번. [PCCP 기출문제] 3번 / 아날로그 시계
 * https://school.programmers.co.kr/learn/courses/30/lessons/250135
 */

class Solution {

    /**
     * h1:m1:s1 시간과 h2:m2:s2 시간 사이에 초침과 분침, 시침이 만나는 횟수를 구하는 메소드
     *
     * @param h1 시작 시침
     * @param m1 시작 분침
     * @param s1 시작 초침
     * @param h2 종료 시침
     * @param m2 종료 분침
     * @param s2 종료 초침
     * @return 시작 시간과 종료 시간 사이에 초침과 분침, 시침이 만나는 횟수
     */
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0; // 초침과 분침, 시침이 만나는 횟수

        // 시작 시간과 종료 시간을 초로 환산
        int start = h1 * 3600 + m1 * 60 + s1;
        int end = h2 * 3600 + m2 * 60 + s2;

        if (h1 % 12 == 0 && m1 == 0 && s1 == 0) answer++; // 시작 시간이 00:00:00, 12:00:00인 경우 1회 더해줌 (초침과 시침, 분침이 만나는 경우이므로)

        // 시작 시간 이후로 1초씩 증가하면서 초침과 분침, 시침이 만나는지 확인
        while (start < end) {
            // 초침은 1초가 지날 때마다 6도씩 이동
            // 분침은 1초가 지날 때마다 1/10도씩 이동
            // 시침은 1초가 지날 때마다 1/120도씩 이동
            double hourHandDegree = (start / 120.0) % 360; // 시침의 각도
            double minuteHandDegree = (start / 10.0) % 360; // 분침의 각도
            double secondHandDegree = (start * 6) % 360; // 초침의 각도

            // 1초가 지난 이후 초침과 분침, 시침 각도
            double nextHourHandDegree = ((start + 1) / 120.0) % 360; // 시침의 각도
            double nextMinuteHandDegree = ((start + 1) / 10.0) % 360; // 분침의 각도
            double nextSecondHandDegree = (start + 1) * 6 % 360; // 초침의 각도

            // 1초가 지난 이후 각도가 0도인지 확인 (0도인 경우 360도로 변경)
            if (nextHourHandDegree == 0) nextHourHandDegree = 360;
            if (nextMinuteHandDegree == 0) nextMinuteHandDegree = 360;
            if (nextSecondHandDegree == 0) nextSecondHandDegree = 360;

            // 시침과 분침, 초침이 만나는지 확인
            if (secondHandDegree < minuteHandDegree && nextSecondHandDegree >= nextMinuteHandDegree) answer++;
            if (secondHandDegree < hourHandDegree && nextSecondHandDegree >= nextHourHandDegree) answer++;
            if (nextMinuteHandDegree == nextHourHandDegree) answer--; // 시침과 분침이 만나는 경우 제외 (중복 제거)

            start++; // 1초 증가
        }

        return answer;
    }
}
