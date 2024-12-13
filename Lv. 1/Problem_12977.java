/*
 * 프로그래머스 12977번. 소수 만들기
 * https://school.programmers.co.kr/learn/courses/30/lessons/12977
 */

import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int solution(int[] nums) {
        ArrayList<Integer> sumList = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    sumList.add(nums[i] + nums[j] + nums[k]);
                }
            }
        }

        boolean[] isPrime = new boolean[3000];
        Arrays.fill(isPrime, true);

        for (int i = 2 ; i < Math.sqrt(isPrime.length); i++) {
            for (int j = 2; i * j < 3000; j++) {
                isPrime[i * j] = false;
            }
        }

        int answer = 0;
        for (int num :sumList) {
            if (isPrime[num]) {
                answer++;
            }
        }

        return answer;
    }
}
