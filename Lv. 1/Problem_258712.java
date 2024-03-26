/*
 * 프로그래머스 258712번. 가장 많이 받은 선물
 * https://school.programmers.co.kr/learn/courses/30/lessons/258712
 */

import java.util.*;

class Solution {

    private static Map<String, Integer> map = new HashMap<>();

    /**
     * 다음달에 가장 많은 선물을 받는 친구가 받을 선물의 수를 구하는 메소드
     *
     * @param friends 친구들의 이름을 담은 1차원 문자열 배열
     * @param gifts   이번 달까지 친구들이 주고받은 선물 기록
     * @return 다음달에 가장 많은 선물을 받는 친구가 받을 선물의 수
     */
    public int solution(String[] friends, String[] gifts) {
        // 친구들의 이름을 인덱스로 반환하기 위해 Map을 초기화한다.
        initMap(friends);

        // 친구들이 주고 받은 선물의 수를 가져온다
        int[][] giftCount = getGiftCount(gifts);

        // 주고받은 선물을 기준으로 선물 지수를 계산한다.
        int[] giftIndex = getGiftIndex(giftCount);

        // 다음 달에 주고 받을 선물의 수를 계산한다.
        int[][] nextGift = getNextGift(giftCount, giftIndex);

        // 다음달에 가장 많은 선물을 받는 친구가 받을 선물의 수를 구한다.
        return getMaxGiftCount(nextGift);
    }

    /**
     * 다음 달에 가장 많은 선물을 받는 친구가 받을 선물의 수를 구하는 메소드
     *
     * @param nextGift 다음 달에 주고 받을 선물의 수
     * @return 다음 달에 가장 많은 선물을 받는 친구가 받을 선물의 수
     */
    private int getMaxGiftCount(int[][] nextGift) {
        // 다음 달에 가장 많은 선물을 받는 친구가 받을 선물의 수를 저장할 변수
        int maxGiftCount = 0;

        // 다음 달에 가장 많은 선물을 받는 친구가 받을 선물의 수를 계산한다.
        for (int j = 0; j < map.size(); j++) {
            int giftCount = 0;

            for (int i = 0; i < map.size(); i++) {
                giftCount += nextGift[i][j];
            }

            maxGiftCount = Math.max(maxGiftCount, giftCount);
        }

        return maxGiftCount;
    }

    /**
     * 다음달에 주고 받을 선물의 수를 계산하는 메소드
     * 두 사람이 선물을 주고받은 기록이 있다면, 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받는다.
     * 두 사람이 선물을 주고받은 기록이 하나도 없거나 주고받은 수가 같다면, 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받는다.
     *
     * @param giftCount 친구들이 주고받은 선물의 수
     * @param giftIndex 친구들의 선물 지수
     * @return 다음 달에 주고 받을 선물의 수
     */
    private int[][] getNextGift(int[][] giftCount, int[] giftIndex) {
        // 주고 받을 선물의 수를 저장할 변수
        int[][] nextGift = new int[map.size()][map.size()];

        // 다음 달에 주고 받을 선물의 수를 계산한다.
        for (int i = 0; i < map.size(); i++) {
            for (int j = i + 1; j < map.size(); j++) {
                if (i == j) continue;

                if (giftCount[i][j] > giftCount[j][i]) {
                    nextGift[j][i] = 1;
                } else if (giftCount[i][j] < giftCount[j][i]) {
                    nextGift[i][j] = 1;
                } else {
                    if (giftIndex[i] > giftIndex[j]) {
                        nextGift[j][i] = 1;
                    } else if (giftIndex[i] < giftIndex[j]) {
                        nextGift[i][j] = 1;
                    }
                }
            }
        }

        return nextGift;
    }

    /**
     * 친구들이 주고 받은 선물을 기준으로 선물 지수를 계산하는 메소드
     *
     * @param giftCount 친구들이 주고받은 선물의 수
     * @return 친구들의 선물 지수
     */
    private int[] getGiftIndex(int[][] giftCount) {
        // 친구들의 선물 지수를 저장할 배열
        // 선물지수: 준 선물의 수 - 받은 선물의 수
        int[] giftIndex = new int[map.size()];

        // 친구들의 선물 지수를 계산한다.
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.size(); j++) {
                if (i == j) continue;
                giftIndex[i] += giftCount[i][j];
                giftIndex[j] -= giftCount[i][j];
            }
        }

        // 친구들의 선물 지수를 반환한다.
        return giftIndex;
    }

    /**
     * 친구들이 주고 받은 선물의 수를 반환하는 메소드
     *
     * @param gifts 이번 달까지 친구들이 주고받은 선물 기록
     * @return 친구들이 받은 선물의 수
     */
    private int[][] getGiftCount(String[] gifts) {
        // 친구들이 받은 선물의 수를 저장할 배열
        int[][] giftCount = new int[map.size()][map.size()];

        // 친구들이 받은 선물의 수를 저장한다.
        for (String s : gifts) {
            String[] gift = s.split(" ");
            giftCount[map.get(gift[0])][map.get(gift[1])]++;
        }

        // 친구들이 받은 선물의 수를 반환한다.
        return giftCount;
    }

    /**
     * 친구들의 이름을 인덱스로 반환하기 위해 Map을 초기화하는 메소드
     *
     * @param friends 친구들의 이름을 담은 1차원 문자열 배열
     */
    private void initMap(String[] friends) {
        for (int i = 0; i < friends.length; i++) {
            map.put(friends[i], i);
        }
    }
}
