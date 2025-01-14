/*
 * 프로그래머스 12936번. 줄 서는 방법
 * https://school.programmers.co.kr/learn/courses/30/lessons/12936
 */

import java.util.ArrayList;

class Solution {

    public int[] solution(int n, long k) {
        long[] fact = new long[n + 1];
        fact[0] = 1;

        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
        }

        ArrayList<Integer> left = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            left.add(i);
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            long idx = (k - 1) / fact[n - 1 - i];
            result[i] = left.get((int) idx);
            left.remove((int) idx);

            k -= idx * fact[n - 1 - i];
        }

        return result;
    }

}
