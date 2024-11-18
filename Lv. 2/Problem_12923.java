/*
 * 프로그래머스 12923번. 숫자 블록
 * https://school.programmers.co.kr/learn/courses/30/lessons/12923
 */

import java.util.ArrayList;

class Solution {
    public int[] solution(long begin, long end) {
        ArrayList<Integer> answer = new ArrayList<>();
        for (long i = begin; i <= end; i++) {
            answer.add(getBlock(i));
        }

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public int getBlock(long n) {
        if (n == 1) {
            return 0;
        }

        int block = 1;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                if (n / i > 10_000_000) {
                    block = i;
                } else {
                    return (int) n / i;
                }
            }
        }

        return block;
    }

}
