/*
 * 프로그래머스 131127번. 할인 행사
 * https://school.programmers.co.kr/learn/courses/30/lessons/131127
 */

import java.util.HashMap;

class Solution {

    /**
     * 회원등록시 정현이가 원하는 제품을 모두 할인 받을 수 있는 회원등록 날짜의 총 일수를 구하는 메소드
     *
     * @param want     정현이가 원하는 제품을 나타내는 문자열 배열
     * @param number   정현이가 원하는 제품의 수량을 나타내는 정수 배열
     * @param discount XYZ 마트에서 할인하는 제품을 나타내는 문자열 배열
     * @return 회원등록시 정현이가 원하는 제품을 모두 할인 받을 수 있는 회원등록 날짜의 총 일수
     */
    public int solution(String[] want, int[] number, String[] discount) {
        // 정현이가 원하는 제품을 저장하는 Map
        HashMap<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        // 10일동안 할인하는 제품을 저장하는 Map
        HashMap<String, Integer> discountMap = new HashMap<>();

        // 10일동안 할인하는 제품을 Map에 저장
        for (int i = 0; i < 10; i++) {
            discountMap.put(discount[i], discountMap.getOrDefault(discount[i], 0) + 1);
        }

        int day = 0; // 회원 등록 날짜
        int answer = 0; // 정현이가 원하는 제품을 모두 할인 받을 수 있는 회원등록 날짜의 총 일수
        while (day + 9 < discount.length) {
            // 10일동안 할인하는 제품 갱신
            if (day != 0) {
                discountMap.put(discount[day + 9], discountMap.getOrDefault(discount[day + 9], 0) + 1);
                discountMap.put(discount[day - 1], discountMap.get(discount[day - 1]) - 1);
            }

            // 정현이가 원하는 제품을 모두 할인 받을 수 있는지 확인
            boolean isPossible = true;
            for (String key : wantMap.keySet()) {
                if (wantMap.get(key) > discountMap.getOrDefault(key, 0)) {
                    isPossible = false;
                    break;
                }
            }

            if (isPossible) {
                answer++;
            }

            day++;
        }

        return answer;
    }
}
