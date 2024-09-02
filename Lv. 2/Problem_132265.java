/*
 * 프로그래머스 132265번. 롤케이크 자르기
 * https://school.programmers.co.kr/learn/courses/30/lessons/132265
 */

import java.util.*;

class Solution {

    /**
     * 롤케이크를 공평하게 자르는 방법의 수를 구하는 함수
     *
     * @param toppings 롤케이크에 올려진 토핑들의 번호를 저장한 정수 배열 (1 ≤ topping의 길이 ≤ 1,000,000, 1 ≤ topping의 원소 ≤ 10,000)
     * @return 롤케이크를 공평하게 자르는 방법의 수
     */
    public int solution(int[] toppings) {
        int answer = 0;

        // 토핑의 종류와 개수를 저장
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < toppings.length; i++) {
            map.put(toppings[i], map.getOrDefault(toppings[i], 0) + 1);
        }

        // 한 쪽의 토핑과 다른 쪽의 토핑이 같은 경우의 수를 구함
        int left = 0;
        int right = map.size();
        Set leftToppingSet = new HashSet();

        for (int topping : toppings) { // 잘려나간 조각의 토핑
            leftToppingSet.add(topping); // 왼쪽 조각에 토핑 추가
            map.put(topping, map.get(topping) - 1); // 오른쪽 토핑 개수 감소

            left = leftToppingSet.size(); // 왼쪽 조각의 토핑 종류 개수
            if (map.get(topping) == 0) { // 오른쪽 토핑이 없으면 개수 감소
                right--;
            }

            // 한 쪽의 토핑과 다른 쪽의 토핑이 같은지 확인
            if (left == right) {
                answer++;
            }
        }

        return answer;
    }
}
