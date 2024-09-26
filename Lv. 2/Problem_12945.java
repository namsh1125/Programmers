/*
 * 프로그래머스 12945번. 피보나치 수
 * https://school.programmers.co.kr/learn/courses/30/lessons/12945
 */

class Solution {

    /**
     * n번째 피보나치 수를 1234567로 나눈 나머지를 반환하는 함수
     *
     * @param n 구하고자 하는 n번째 피보나치 수
     * @return n번째 피보나치 수를 1234567로 나눈 나머지
     */
    public int solution(int n) {
        long[] fibo = new long[n + 1];
        fibo[0] = 0;
        fibo[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 1234567;
        }

        return (int) fibo[n];
    }
}
