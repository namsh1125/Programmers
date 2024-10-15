/*
 * 프로그래머스 12981번. 영어 끝말잇기
 * https://school.programmers.co.kr/learn/courses/30/lessons/12981
 */

import java.util.ArrayList;
import java.util.HashMap;

class Solution {

    public int[] solution(int n, String[] words) {
        HashMap<Character, ArrayList<String>> map = new HashMap<>();

        // a부터 z까지 map에 저장한다.
        for (char c = 'a'; c <= 'z'; c++) {
            map.put(c, new ArrayList<>());
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            // 이전 단어의 끝 자리와 일치하는지 확인한다.
            if (i >= 1) {
                String before = words[i - 1];
                if (before.charAt(before.length() - 1) != word.charAt(0)) {
                    return new int[]{(i % n) + 1, (i / n) + 1};
                }
            }

            // 이전에 나온 단어인지 확인한다.
            ArrayList<String> list = map.get(word.charAt(0));
            if (list.contains(word)) {
                return new int[]{(i % n) + 1, (i / n) + 1};

            } else {
                list.add(word);
            }
        }

        return new int[]{0, 0};
    }
}
