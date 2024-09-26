/*
 * 프로그래머스 72411번. 메뉴 리뉴얼
 * https://school.programmers.co.kr/learn/courses/30/lessons/72411
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {

    /**
     * 새로 추가하게 될 코스요리의 메뉴 구성을 구하는 함수
     *
     * @param orders 각 손님들이 주문한 단품메뉴들이 문자열 형식으로 담긴 배열
     * @param course 추가하고 싶어하는 코스요리를 구성하는 단품메뉴들의 갯수 (1 <= course 배열의 크기 <= 10, 2 <= course의 원소 <= 10)
     * @return 새로 추가하게 될 코스요리의 메뉴 구성
     */
    public String[] solution(String[] orders, int[] course) {
        // orders 배열의 각 원소를 오름차순으로 정렬
        for (int i = 0; i < orders.length; i++) {
            char[] order = orders[i].toCharArray();
            Arrays.sort(order);
            orders[i] = new String(order);
        }

        // 각 주문을 기준으로, course 배열 안에 있는 갯수만큼의 조합을 구함
        ArrayList<String> answer = new ArrayList<>();
        for (int menuCount : course) {
            ArrayList<String> courseCandidates = new ArrayList<>();

            for (String order : orders) {
                courseCandidates.addAll(combination(order, 0, new boolean[order.length()], menuCount));
            }

            // 후보군을 해시맵으로 변환하여 각 메뉴의 주문 횟수를 계산
            HashMap<String, Integer> map = new HashMap<>();
            for (String courseCandidate : courseCandidates) {
                map.put(courseCandidate, map.getOrDefault(courseCandidate, 0) + 1);
            }

            // 현재의 메뉴 갯수로 코스 요리를 구성하지 못한 경우 다음 메뉴 갯수로 넘어감
            if (courseCandidates.isEmpty()) {
                continue;
            }

            // 코스 요리 후보군 중 가장 많이 주문된 메뉴 횟수를 찾음
            int maxCount = map.values().stream()
                    .max(Integer::compareTo)
                    .get();

            // 반복문을 돌며 가장 많이 주문된 메뉴 횟수와 일치하는 메뉴들을 찾아 정답에 추가
            for (String setMenu : map.keySet()) {
                if (map.get(setMenu) == maxCount && maxCount >= 2) {
                    answer.add(setMenu);
                }
            }
        }

        // 정답을 오름차순으로 정렬
        answer.sort(String::compareTo);

        return answer.toArray(new String[0]);
    }

    /**
     * 조합을 구하는 함수
     *
     * @param order         주문한 메뉴
     * @param depth         현재 탐색 깊이
     * @param visited       방문 여부
     * @param wantMenuCount 코스 요리로 구성되기를 원하는 메뉴 갯수
     * @return 조합된 메뉴들
     */
    private ArrayList<String> combination(String order, int depth, boolean[] visited, int wantMenuCount) {
        ArrayList<String> result = new ArrayList<>();

        if (depth == order.length()) {
            StringBuilder menu = new StringBuilder();

            for (int i = 0; i < order.length(); i++) {
                if (visited[i]) {
                    menu.append(order.charAt(i));
                }
            }

            if (menu.length() == wantMenuCount) {
                result.add(menu.toString());
            }

            return result;
        }

        visited[depth] = true;
        result.addAll(combination(order, depth + 1, visited, wantMenuCount));
        visited[depth] = false;
        result.addAll(combination(order, depth + 1, visited, wantMenuCount));

        return result;
    }
}
