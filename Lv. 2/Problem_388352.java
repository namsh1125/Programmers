/*
 * 백준 388352번. 비밀 코드 해독
 * https://school.programmers.co.kr/learn/courses/30/lessons/388352
 */

import java.util.ArrayList;
import java.util.List;

class Solution {

    private int answer;

    public int solution(int n, int[][] q, int[] ans) {
        answer = 0;
        dfs(1, n, 0, new ArrayList<>(), q, ans);
        return answer;
    }

    /**
     * 비밀 코드 조합의 경우의 수를 구하고 검증하는 함수
     *
     * @param start   시작 정수
     * @param end     끝 정수
     * @param count   현재 조합에 포함된 정수 개수
     * @param current 현재 조합에 포함된 정수 리스트
     * @param q       입력한 정수를 담은 2차원 정수 배열 (1 <= q[i][j] <= n)
     * @param ans     시스템 응답을 담은 1차원 정수 배열 (0 <= ans[i] <= 5)
     */
    private void dfs(int start, int end, int count, List<Integer> current, int[][] q, int[] ans) {
        if (count == 5) { // 5개 조합이 완성되면
            if (isValid(current, q, ans)) { // 조합이 유효한지 검증
                answer++;
            }

            return;
        }

        for (int i = start; i <= end; i++) {
            current.add(i);
            dfs(i + 1, end, count + 1, current, q, ans);
            current.remove(current.size() - 1);
        }
    }

    /**
     * 비밀 코드 조합이 유효한지 검증하는 함수
     *
     * @param current 현재 조합에 포함된 정수 리스트
     * @param q       입력한 정수를 담은 2차원 정수 배열 (1 <= q[i][j] <= n)
     * @param ans     시스템 응답을 담은 1차원 정수 배열 (0 <= ans[i] <= 5)
     * @return 조합이 유효한지 여부
     */
    private boolean isValid(List<Integer> current, int[][] q, int[] ans) {
        for (int i = 0; i < q.length; i++) {
            int count = 0;

            // 현재 조합에서 q[i]에 포함된 정수 개수 세기
            for (int j = 0; j < q[i].length; j++) {
                if (current.contains(q[i][j])) {
                    count++;
                }
            }

            // 시스템 응답과 일치하는지 확인
            if (count != ans[i]) {
                return false;
            }
        }

        return true;
    }
}
