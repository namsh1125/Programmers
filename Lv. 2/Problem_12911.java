/*
 * 프로그래머스 12911번. 다음 큰 숫자
 * https://school.programmers.co.kr/learn/courses/30/lessons/12911
 */

class Solution {
    public int solution(int n) {
        int k = n;

        do {
            k++;
        } while (Integer.bitCount(n) != Integer.bitCount(k));

        return k;
    }
}
