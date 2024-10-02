/*
 * 프로그래머스 12941번. 최솟값 만들기
 * https://school.programmers.co.kr/learn/courses/30/lessons/12941
 */

import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int answer = 0;
        for (int i = 0; i < A.length; i++) {
            answer += A[i] * B[A.length - i - 1];
        }

        return answer;
    }
}
