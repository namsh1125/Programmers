/*
 * 프로그래머스 148652번. 유사 칸토어 비트열
 * https://school.programmers.co.kr/learn/courses/30/lessons/148652
 */

class Solution {

    public int solution(int n, long l, long r) {
        int answer = 0;
        for (long i = l - 1; i < r; i++) {
            if (!isContainTwo(i)) {
                answer++;
            }
        }

        return answer;
    }

    /**
     * 5진수로 변환했을 때 2가 포함되어 있는지 확인
     *
     * @param num 5진수로 변환할 숫자
     * @return 5진수로 변환했을 때 2가 포함되어 있으면 true, 아니면 false
     */
    public boolean isContainTwo(long num) {
        while (num > 0) {
            if (num % 5 == 2) {
                return true;
            }
            num /= 5;
        }
        return false;
    }

}
