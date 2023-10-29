/*
 * 프로그래머스 60060번. 가사 검색
 * https://school.programmers.co.kr/learn/courses/30/lessons/60060
 */

import java.util.*;

class Solution {
    /**
     * 주어진 가사 단어들과 키워드들에 대해 각 키워드 별로 매치된 단어가 몇 개인지를 계산
     *
     * @param words   가사에 사용된 모든 단어들이 담긴 배열
     * @param queries 찾고자 하는 키워드가 담긴 배열
     * @return 각 키워드 별로 매치된 단어가 몇 개인지 순서대로 저장된 배열
     */
    public int[] solution(String[] words, String[] queries) {
        Trie front = new Trie(); // 가사 단어를 앞에서부터 저장할 Trie
        Trie back = new Trie(); // 가사 단어를 뒤에서부터 저장할 Trie

        // 가사 단어를 Trie에 저장
        for (String word : words) {
            front.insert(word); // 앞에서부터 보는 Trie에 단어 삽입
            back.insert(reverse(word)); // 뒤에서부터 보는 Trie에 단어 삽입
        }

        // 각 키워드에 대해 매치된 단어 개수를 계산하여 배열로 반환
        return Arrays.stream(queries).mapToInt(
                        query -> query.charAt(0) == '?' ?
                                back.find(reverse(query), 0) : // 키워드가 '?'로 시작하면 뒤에서부터 보는 Trie에서 검색
                                front.find(query, 0)) // 그렇지 않으면 앞에서부터 보는 Trie에서 검색
                .toArray();
    }

    /**
     * Trie 자료구조를 표현하는 내부 클래스
     */
    class Trie {
        Map<Integer, Integer> lenMap = new HashMap<>(); // 단어의 길이에 따른 개수를 저장하는 맵
        Trie[] child = new Trie[26]; // 알파벳 소문자에 대한 자식 노드 배열

        /**
         * 주어진 단어를 Trie에 삽입하는 메서드
         * 단어의 각 문자를 노드로 하는 트리를 생성하며, 단어의 길이에 따른 개수도 함께 저장한다
         *
         * @param str 삽입할 단어
         */
        void insert(String str) {
            Trie node = this; // 현재 노드를 가리키는 변수
            int len = str.length(); // 단어의 길이
            lenMap.put(len, lenMap.getOrDefault(len, 0) + 1); // 길이에 따른 단어 개수 증가

            for (char ch : str.toCharArray()) {
                int idx = ch - 'a';
                if (node.child[idx] == null) // 해당 노드의 자식이 없는 경우
                    node.child[idx] = new Trie(); // 자식 노드 생성

                node = node.child[idx]; // 자식 노드로 이동
                node.lenMap.put(len, node.lenMap.getOrDefault(len, 0) + 1);
            }
        }

        /**
         * 주어진 문자열에 매치되는 단어의 개수를 찾는 메서드
         * 문자열의 각 문자를 노드로 가지는 트리를 탐색하여 매치되는 단어의 개수를 찾는다
         *
         * @param str 찾을 문자열
         * @param i   탐색을 시작할 인덱스
         * @return 주어진 문자열에 매치되는 단어의 개수
         */
        int find(String str, int i) {
            if (str.charAt(i) == '?') // 키워드가 '?'로 시작하는 경우
                return lenMap.getOrDefault(str.length(), 0); // 해당 길이의 단어 개수 반환

            int idx = str.charAt(i) - 'a';
            return child[idx] == null ? 0 : child[idx].find(str, i + 1); // 다음 노드로 이동하여 재귀적으로 검색
        }
    }

    /**
     * 주어진 문자열을 뒤집는 메서드
     *
     * @param s 뒤집을 문자열
     * @return 뒤집힌 문자열
     */
    String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
