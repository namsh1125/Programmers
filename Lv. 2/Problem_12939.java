/*
 * 프로그래머스 12939번. 최댓값과 최솟값
 * https://school.programmers.co.kr/learn/courses/30/lessons/12939
 */

import java.util.Arrays;

class Solution {
    public String solution(String s) {
        int[] intArr = Arrays.stream(s.split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        int min = intArr[0];
        int max = intArr[intArr.length - 1];

        return min + " " + max;
    }
}
