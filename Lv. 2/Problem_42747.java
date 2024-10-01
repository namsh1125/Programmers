/*
 * 프로그래머스 42747번. H-Index
 * https://school.programmers.co.kr/learn/courses/30/lessons/42747
 */

class Solution {

    /**
     * H-Index를 구하는 메소드
     *
     * @param citations 논문의 인용 횟수 배열
     * @return H-Index
     */
    public int solution(int[] citations) {
        // 인용 횟수와 인용한 논문의 수를 저장하는 배열
        int[] citationCount = new int[10001];
        for (int citation : citations) {
            citationCount[citation]++;
        }

        // 배열을 돌며 H-Index를 구한다.
        int hIndex = 0;
        int paperCount = 0; // h번 이상 인용된 논문의 수
        for (int i = citationCount.length - 1; i >= 0; i--) {
            paperCount += citationCount[i];
            if (paperCount >= i && citations.length - paperCount <= i) {
                hIndex = i;
                break;
            }
        }

        return hIndex;
    }
}
