/*
 * 프로그래머스 12944번. 평균 구하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/12944
 */

import java.util.Arrays;

class Solution {
    public double solution(int[] arr) {
        return Arrays.stream(arr).average().getAsDouble();
    }
}
