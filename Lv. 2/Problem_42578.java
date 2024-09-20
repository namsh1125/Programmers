/*
 * 프로그래머스 42578번. 의상
 * https://school.programmers.co.kr/learn/courses/30/lessons/42578
 */

import java.util.ArrayList;
import java.util.HashMap;

class Solution {

    /**
     * 코니가 가진 의상들의 조합의 수를 구하는 메소드
     *
     * @param clothes 코니가 가진 의상들이 담긴 2차원 배열
     * @return 서로 다른 옷의 조합의 수
     */
    public int solution(String[][] clothes) {
        // 의상을 담을 HashMap
        HashMap<String, ArrayList<String>> map = new HashMap<>();

        // 의상을 HashMap에 담는다.
        for (String[] cloth : clothes) {
            String name = cloth[0];
            String type = cloth[1];

            if (map.containsKey(type)) {
                map.get(type).add(name);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(name);
                map.put(type, list);
            }
        }

        int answer = 1;
        for (String key : map.keySet()) {
            answer *= map.get(key).size() + 1;
        }

        return answer - 1;
    }
}
