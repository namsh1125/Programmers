/*
 * 프로그래머스 42842번. 카펫
 * https://school.programmers.co.kr/learn/courses/30/lessons/42842
 */

import java.util.ArrayList;

class Solution {

    /**
     * 카펫의 가로, 세로 크기를 구하는 메소드
     *
     * @param brown  카펫의 갈색 격자 수
     * @param yellow 카펫의 노란색 격자 수
     * @return 카펫의 가로, 세로 크기
     */
    public int[] solution(int brown, int yellow) {
        // 전체 카펫의 격자 수
        int total = brown + yellow;

        // 카펫이 될 수 있는 후보의 가로, 세로 크기
        // 가로 크기가 세로 크기보다 크거나 같다.
        ArrayList<Integer> heightCandidates = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(total); i++) {
            if (total % i == 0) {
                heightCandidates.add(i);
            }
        }

        // 세로 후보군을 순회하며, 카펫의 가로, 세로 크기를 찾는다.
        for (int height : heightCandidates) {
            int width = total / height;

            if (width < 2 || height < 2) { // 내부에 노란색 격자가 존재하지 않는 경우
                continue;
            }

            if ((width - 2) * (height - 2) == yellow) {
                return new int[]{width, height};
            }

        }

        return new int[]{-1, -1};
    }
}
