/*
 * 프로그래머스 12912번. 두 정수 사이의 합
 * https://school.programmers.co.kr/learn/courses/30/lessons/12912
 */

class Solution {
    public long solution(int a, int b) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        long sumA = (long) a * (a - 1) / 2; // 1 ~ a-1까지의 합
        long sumB = (long) b * (b + 1) / 2; // 1 ~ b까지의 합

        return sumB - sumA;
    }
}
