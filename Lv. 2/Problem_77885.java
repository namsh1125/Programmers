/*
 * 프로그래머스 77885번. 2개 이하로 다른 비트
 * https://school.programmers.co.kr/learn/courses/30/lessons/77885
 */

class Solution {

    /**
     * 주어진 정수들에 대하여 각 수의 f 값을 구하는 메소드
     *
     * @param numbers 정수들이 담긴 배열 (1 ≤ numbers의 길이 ≤ 100,000, 1 ≤ numbers의 원소 ≤ 10^15)
     * @return numbers의 모든 수들에 대하여 각 수의 f 값을 구한 배열
     */
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            answer[i] = getF(numbers[i]);
        }

        return answer;
    }

    /**
     * 주어진 정수에 대하여 f 값을 구하는 메소드
     * f(x) = x보다 크고 x와 비트가 1~2개 다른 수들 중에서 제일 작은 수 (양의 정수 x에 대하여)
     *
     * @param x 정수
     * @return number의 f 값
     */
    private long getF(long x) {
        StringBuilder bit = new StringBuilder(Long.toBinaryString(x));

        // bit를 64비트로 맞춰준다.
        while (bit.length() < 64) {
            bit.insert(0, '0');
        }

        // 맨 오른쪽부터 0을 찾아 1로 바꾸고, 그 다음 비트를 0으로 바꾼다.
        for (int i = bit.length() - 1; i >= 0; i--) {
            if (bit.charAt(i) == '0') {
                bit.setCharAt(i, '1');
                if (i + 1 < bit.length()) {
                    bit.setCharAt(i + 1, '0');
                }
                break;
            }
        }

        return Long.parseLong(bit.toString(), 2);
    }
}
