/*
 * 프로그래머스 178870번. 연속된 부분 수열의 합
 * https://school.programmers.co.kr/learn/courses/30/lessons/178870
 */

class Solution {
    public int[] solution(int[] sequence, int k) {
        int left = 0, right = sequence.length - 1;
        int sum = 0;

        for (int l = 0, r = 0; l < sequence.length; l++) {
            while (r < sequence.length && sum < k) {
                sum += sequence[r++];
            }

            // 위에서 r을 증가시켰기 때문에, 1을 빼줌
            if (sum == k && (r - 1) - l < right - left) { // 합이 k이고, 길이가 더 짧을 때
                left = l;
                right = r - 1;
            }

            sum -= sequence[l];
        }

        return new int[]{left, right};
    }
}
