/*
 * 프로그래머스 135807번. 숫자 카드 나누기
 * https://school.programmers.co.kr/learn/courses/30/lessons/135807
 */

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    /**
     * 다음 두 조건 중 하나를 만족하는 가장 큰 양의 정수 값을 반환하는 메소드
     * 1. 철수가 가진 카드들에 적힌 모든 숫자를 나눌 수 있고 영희가 가진 카드들에 적힌 모든 숫자들 중 하나도 나눌 수 없는 양의 정수
     * 2. 영희가 가진 카드들에 적힌 모든 숫자를 나눌 수 있고, 철수가 가진 카드들에 적힌 모든 숫자들 중 하나도 나눌 수 없는 양의 정수
     *
     * @param arrayA 철수가 가진 카드에 적힌 숫자들을 나타내는 정수 배열
     * @param arrayB 영희가 가진 카드에 적힌 숫자들을 나타내는 정수 배열
     * @return 특정 조건을 만족하는 가장 큰 양의 정수 값
     */
    public int solution(int[] arrayA, int[] arrayB) {
        return Math.max(getLargestNumber(arrayA, arrayB), getLargestNumber(arrayB, arrayA));
    }


    /**
     * 주어진 카드들이 다른 카드들의 최대 공약수로 나누어 떨어지지 않는 가장 큰 양의 정수를 반환하는 메소드
     *
     * @param cards        철수나 영희가 가진 카드들을 나타내는 정수 배열
     * @param anotherCards 철수나 영희가 가진 카드들을 나타내는 정수 배열 (다른 사람의 카드들)
     * @return 주어진 카드들이 다른 카드들의 최대 공약수로 나누어 떨어지지 않는 가장 큰 양의 정수
     */
    private int getLargestNumber(int[] cards, int[] anotherCards) {
        // 다른 카드들의 최대 공약수를 구한다.
        int gcd = gcd(anotherCards);
        PriorityQueue<Integer> divisor = getDivisor(gcd); // 내림차순

        // 다른 카드들의 최대 공약수로 나누어 떨어지지 않는 가장 큰 양의 정수를 구한다.
        while (!divisor.isEmpty()) {
            int num = divisor.remove();

            boolean flag = true;
            for (int card : cards) {
                if (card % num == 0) { // 하나라도 나누어 떨어지면 안됨
                    flag = false;
                    break;
                }
            }

            if (flag) {
                return num;
            }
        }

        return 0;
    }

    /**
     * 최대 공약수의 약수를 구하는 메소드
     *
     * @param gcd 최대 공약수
     * @return 최대 공약수의 약수가 내림차순으로 들어있는 우선순위 큐
     */
    private PriorityQueue<Integer> getDivisor(int gcd) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 1; i <= gcd; i++) {
            if (gcd % i == 0) {
                queue.add(i);
            }
        }

        return queue;
    }

    /**
     * 여러 수의 최대 공약수를 구하는 메소드
     *
     * @param nums 여러 수를 나타내는 정수 배열
     * @return 여러 수의 최대 공약수
     */
    private int gcd(int[] nums) {
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            result = gcd(result, nums[i]);
        }

        return result;
    }

    /**
     * 두 수의 최대 공약수를 구하는 메소드
     *
     * @param a
     * @param b
     * @return
     */
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }
}
