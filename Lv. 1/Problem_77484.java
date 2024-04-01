/*
 * 프로그래머스 77484번. 로또의 최고 순위와 최저 순위
 * https://school.programmers.co.kr/learn/courses/30/lessons/77484
 */

import java.util.Arrays;

class Solution {

    /**
     * 로또의 최고 순위와 최저 순위를 구하는 메소드
     *
     * @param lottos   민우가 구매한 로또 번호를 담은 배열
     * @param win_nums 당첨 번호를 담은 배열
     * @return 당첨 가능한 최고 순위와 최저 순위가 담긴 배열
     */
    public int[] solution(int[] lottos, int[] win_nums) {
        // 로또와 당첨 번호가 일치하는 개수를 구한다.
        int match = getMatchCount(lottos, win_nums);

        // 0의 개수를 구한다.
        int zeroCount = (int) Arrays.stream(lottos)
                .filter(lotto -> lotto == 0)
                .count();

        // 최고 순위와 최저 순위를 구한다.
        int max = Math.min(6, match + zeroCount);
        int min = match;

        // 결과를 반환한다.
        return new int[]{getRank(max), getRank(min)};
    }

    /**
     * 맟춘 번호의 개수를 기반으로 순위를 반환하는 메소드
     *
     * @param match 맟춘 번호의 개수
     * @return 순위
     */
    private int getRank(int match) {
        return switch (match) {
            case 6 -> 1;
            case 5 -> 2;
            case 4 -> 3;
            case 3 -> 4;
            case 2 -> 5;
            default -> 6;
        };
    }

    /**
     * 로또와 당첨 번호가 일치하는 개수를 구하는 메소드
     *
     * @param lottos   민우가 구매한 로또 번호를 담은 배열
     * @param win_nums 당첨 번호를 담은 배열
     * @return 로또와 당첨 번호가 일치하는 개수
     */
    private int getMatchCount(int[] lottos, int[] win_nums) {
        int match = 0;

        for (int num : win_nums) {
            for (int lotto : lottos) {
                if (num == lotto && lotto != 0) {
                    match++;
                }
            }
        }

        return match;
    }

}
