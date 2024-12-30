/*
 * 프로그래머스 42895번. N으로 표현
 * https://school.programmers.co.kr/learn/courses/30/lessons/42895
 */

import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    public int solution(int N, int number) {
        // N과 number가 같은 경우 1을 리턴
        if (N == number) {
            return 1;
        }

        // N과 number가 다른 경우
        ArrayList<HashSet<Integer>> dp = new ArrayList<>();
        for (int i = 0; i < 9; i++) { // 8번 이상인 경우는 -1을 리턴하므로
            dp.add(new HashSet<>());
        }

        // N을 i번 사용했을 때 만들 수 있는 수
        dp.get(1).add(N);

        // N을 i번 사용했을 때 만들 수 있는 수
        for (int i = 2; i < 9; i++) {
            // NNN...N을 i번 사용했을 때 만들 수 있는 수
            int num = 0;
            for (int j = 0; j < i; j++) {
                num = num * 10 + N;
            }
            dp.get(i).add(num);

            // N을 j번 사용했을 때 만들 수 있는 수와 N을 i-j번 사용했을 때 만들 수 있는 수로 만들 수 있는 수
            for (int j = 1; j < i; j++) {
                for (int a : dp.get(j)) {
                    for (int b : dp.get(i - j)) {
                        dp.get(i).add(a + b);
                        dp.get(i).add(a - b);
                        dp.get(i).add(a * b);
                        if (b != 0) {
                            dp.get(i).add(a / b);
                        }
                    }
                }
            }

            if (dp.get(i).contains(number)) {
                return i;
            }
        }

        return -1;
    }
}
