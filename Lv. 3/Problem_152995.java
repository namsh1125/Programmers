/*
 * 프로그래머스 152995번. 인사고과
 * https://school.programmers.co.kr/learn/courses/30/lessons/152995
 */

import java.util.Arrays;

class Solution {

    /**
     * 완호의 석차를 반환하는 함수
     *
     * @param scores 사원의 근무 태도 점수와 동료 평가 점수 목록 (scores[0]은 완호의 점수, 1 ≤ scores의 길이 ≤ 100,000, 0 <= scores[i][j] <= 100,000)
     * @return 완호의 석차 (인센티브가 없는 경우 -1)
     */
    public int solution(int[][] scores) {
        int whanhoWorkScore = scores[0][0];
        int whanhoPeerScore = scores[0][1];

        // 근무 태도 점수를 기준으로 내림차순, 동료 평가 점수를 기준으로 오름차순 정렬
        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) { // 근무 태도 점수가 같은 경우
                return o1[1] - o2[1]; // 동료 평가 점수 오름차순
            }
            return o2[0] - o1[0]; // 근무 태도 점수를 기준으로 내림차순
        });

        // 인센티브를 받지 못하는 사람 필터링
        int limitEvaluationScore = scores[0][1]; // 동료 평가 limit (해당 limit 이하인 사람은 인센티브를 받지 못함)

        for (int i = 1; i < scores.length; i++) {
            if (scores[i][1] < limitEvaluationScore) { // 인센티브를 받지 못하는 경우
                if (scores[i][0] == whanhoWorkScore && scores[i][1] == whanhoPeerScore) { // 인센티브를 받지 못하는 대상이 완호인 경우
                    return -1;
                }

                // 완호의 순서를 구하는데 필요없는 정보이므로 불필요한 정보로 변경
                scores[i][0] = -1;
                scores[i][1] = -1;

            } else { // 근무 태도 점수는 낮지만, 높은 동료 평가 점수를 가지고 있는 경우
                limitEvaluationScore = scores[i][1]; // 동료 평가 점수의 limit 갱신
            }
        }

        // 완호의 인센티브 순서를 구하기 위해 총 점수를 기준으로 내림차순 정렬
        Arrays.sort(scores, ((o1, o2) -> (o2[0] + o2[1]) - (o1[0] + o1[1])));

        // 완호의 순서를 구함
        int rank = 1;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i][0] + scores[i][1] > whanhoWorkScore + whanhoPeerScore) {
                rank++;
            } else {
                break;
            }
        }

        return rank;
    }
}
