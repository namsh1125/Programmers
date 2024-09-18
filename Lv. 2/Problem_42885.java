/*
 * 프로그래머스 42885번. 구명보트
 * https://school.programmers.co.kr/learn/courses/30/lessons/42885
 */

import java.util.Arrays;

class Solution {

    /**
     * 구명보트에 탈 수 있는 사람의 최소 수를 구하는 메소드
     *
     * @param people 사람들의 몸무게를 담은 배열
     * @param limit  구명보트의 무게 제한
     * @return 모든 사람을 구출하기 위해 필요한 구명보트 개수의 최솟값
     */
    public int solution(int[] people, int limit) {
        // 몸무게 순으로 정렬
        Arrays.sort(people);

        // 구명보트에 태울 수 있는 사람의 최소 수를 구함
        int start = 0, end = people.length - 1;
        int answer = 0;

        while (start <= end) {
            if (people[start] + people[end] <= limit) { // 가장 가벼운 사람과 가장 무거운 사람이 같이 탈 수 있는 경우
                start++;
                end--;

            } else { // 가장 가벼운 사람과 가장 무거운 사람이 같이 탈 수 없는 경우
                end--; // 가장 무거운 사람만 태움
            }
            
            answer++;
        }

        return answer;
    }
}
