/*
 * 프로그래머스 181188번. 요격 시스템
 * https://school.programmers.co.kr/learn/courses/30/lessons/181188
 */

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] targets) {
        // e를 기준으로 정렬
        Arrays.sort(targets, Comparator.comparingInt(o -> o[1]));

        int answer = 0; // 필요한 미사일 개수
        int missile = 0; // 요격 위치

        for (int[] target : targets) {
            if (missile <= target[0]) {
                missile = target[1];
                answer++;
            }
        }

        return answer;
    }
}
