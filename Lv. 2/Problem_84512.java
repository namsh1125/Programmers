/*
 * 프로그래머스 84512번. 모음사전
 * https://school.programmers.co.kr/learn/courses/30/lessons/84512
 */

class Solution {

    /**
     * 사전에서 단어를 찾아 몇 번째 단어인지 반환하는 함수
     *
     * @param target 단어
     * @return 이 단어가 사전에서 몇 번째 단어인지
     */
    public int solution(String target) {
        int[] weight = {781, 156, 31, 6, 1};

        int answer = 0;
        for (int i = 0; i < target.length(); i++) {
            answer += "AEIOU".indexOf(target.charAt(i)) * weight[i] + 1;
        }

        return answer;
    }

}
