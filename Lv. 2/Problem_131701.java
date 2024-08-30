/*
 * 프로그래머스 131701번. 연속 부분 수열 합의 개수
 * https://school.programmers.co.kr/learn/courses/30/lessons/131701
 */

import java.util.ArrayList;

class Solution {
    public int solution(int[] elements) {
        ArrayList<Integer> answer = new ArrayList<>();

        for (int length = 1; length <= elements.length; length++) {
            answer.addAll(getSum(elements, length));
        }

        return (int) answer.stream()
                .mapToInt(Integer::intValue)
                .distinct() // 중복 제거
                .count(); // 개수 반환
    }

    /**
     * 연속된 부분 수열의 합을 구하는 메소드
     *
     * @param elements 원형 수열의 모든 원소
     * @param length   구하고자 하는 연속된 부분 수열의 길이
     * @return 연속된 부분 수열의 합
     */
    private ArrayList<Integer> getSum(int[] elements, int length) {
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 0; i < elements.length; i++) {
            int sum = 0;
            for (int j = i; j < i + length; j++) {
                sum += elements[j % elements.length];
            }

            answer.add(sum);
        }

        return answer;
    }
}
