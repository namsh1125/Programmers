/*
 * 프로그래머스 134239번. 우박수열 정적분
 * https://school.programmers.co.kr/learn/courses/30/lessons/134239
 */

import java.util.ArrayList;

class Solution {

    public double[] solution(int k, int[][] ranges) {
        // 우박수열을 구한다.
        ArrayList<Integer> collatz = getCollatz(k);

        // 정적분을 구한다.
        ArrayList<Double> answer = new ArrayList<>();

        for (int[] range : ranges) {
            // 정적분 범위를 갱신한다.
            // range[1]이 음수인 경우, range[1]의 값을 갱신한다.
            // 또한 [0, 0]인 경우, [0, n]으로 갱신한다.
            int n = collatz.size();
            if (range[1] <= 0) {
                range[1] = n + range[1];
            }

            // 주어진 구간의 시작점이 끝점보다 커서 유효하지 않은 구간인 경우 정적분 결과는 -1로 처리한다.
            if (range[0] >= range[1]) {
                answer.add(-1.0);
                continue;
            }

            // 정적분을 구한다.
            double sum = 0;
            for (int i = range[0]; i < range[1] - 1; i++) {
                sum += (collatz.get(i) + collatz.get(i + 1)) / 2.0;
            }

            // 정적분 결과를 저장한다.
            answer.add(sum);
        }

        return answer.stream()
                .mapToDouble(Double::doubleValue)
                .toArray();
    }


    /**
     * 우박수열을 구하는 함수
     *
     * @param n 우박수열의 시작점
     * @return 우박수열
     */
    public ArrayList<Integer> getCollatz(int n) {
        ArrayList<Integer> collatz = new ArrayList<>();

        while (n != 1) {
            collatz.add(n);

            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = n * 3 + 1;
            }
        }
        collatz.add(1);

        return collatz;
    }

}
