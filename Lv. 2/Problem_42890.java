/*
 * 프로그래머스 42890번. 후보키
 * https://school.programmers.co.kr/learn/courses/30/lessons/42890
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

class Solution {

    /**
     * 입력받은 릴레이션에서 후보 키의 개수를 반환하는 함수
     *
     * @param relation 릴레이션을 나타내는 문자열 배열
     * @return 릴레이션에서 후보 키의 개수
     */
    public int solution(String[][] relation) {
        ArrayList<String> superKeys = dfs(relation, new ArrayDeque<>(), 0); // 유일성을 만족하는 키를 구함 (슈퍼키)
        ArrayList<String> candidateKeys = selectCandidateKeys(superKeys); // 유일성을 만족하는 키 중에서 최소성을 만족하는 후보키를 구함

        return candidateKeys.size();
    }

    /**
     * 슈퍼키에서 후보키를 선별하는 함수
     *
     * @param superKeys 슈퍼키 집합
     * @return 후보키 집합
     */
    private ArrayList<String> selectCandidateKeys(ArrayList<String> superKeys) {
        // 슈퍼키를 길이가 짧은 순으로 정렬
        superKeys.sort(Comparator.comparingInt(String::length));

        // 최소성을 만족하는 후보키를 찾음
        ArrayList<String> candidateKeys = new ArrayList<>();
        for (String superKey : superKeys) {
            boolean isCandidateKey = true;

            // 후보키가 슈퍼키의 부분집합인지 확인
            for (String candidateKey : candidateKeys) {
                int count = 0;
                for (char c : superKey.toCharArray()) {
                    if (candidateKey.contains(String.valueOf(c))) {
                        count++;
                    }
                }

                if (count == candidateKey.length()) {
                    isCandidateKey = false;
                    break;
                }
            }

            // 후보키가 아니라면 후보키 집합에 추가
            if (isCandidateKey) {
                candidateKeys.add(superKey);
            }
        }

        return candidateKeys;
    }

    /**
     * 입력받은 릴레이션에서 슈퍼키를 찾는 함수
     *
     * @param relation   릴레이션을 나타내는 문자열 배열
     * @param attributes 현재 탐색 중인 속성
     * @param index      현재 탐색 중인 속성의 인덱스
     * @return 슈퍼키 집합
     */
    private ArrayList<String> dfs(String[][] relation, ArrayDeque<Integer> attributes, int index) {
        ArrayList<String> superKeys = new ArrayList<>();

        if (isSuperKey(relation, attributes)) {
            superKeys.add(dequeToString(attributes));
            return superKeys;
        }

        for (int i = index; i < relation[0].length; i++) {
            attributes.add(i);
            superKeys.addAll(dfs(relation, attributes, i + 1));
            attributes.removeLast();
        }

        return superKeys;
    }

    /**
     * ArrayDeque을 문자열로 변환하는 함수
     *
     * @param deque 문자열로 변환할 ArrayDeque
     * @return 변환된 문자열
     */
    private String dequeToString(ArrayDeque<Integer> deque) {
        return deque.stream()
                .sorted() // 오름차순 정렬
                .map(String::valueOf) // 문자열로 변환
                .collect(Collectors.joining()); // 문자열로 합치기
    }

    /**
     * 슈퍼키인지 확인하는 함수
     *
     * @param relation   릴레이션을 나타내는 문자열 배열
     * @param attributes 슈퍼키인지 확인할 속성
     * @return 슈퍼키인지 여부
     */
    private boolean isSuperKey(String[][] relation, ArrayDeque<Integer> attributes) {
        HashSet<String> set = new HashSet<>();

        for (String[] tuple : relation) {
            // tuple에서 슈퍼키 속성만 추출
            StringBuilder sb = new StringBuilder();
            for (int index : attributes) {
                sb.append(tuple[index]); // tuble[index]는 value라고 생각하면 됨
                sb.append(" ");
            }

            set.add(sb.toString());
        }

        // set의 크기와 relation의 길이 비교 (set의 크기와 relation의 길이가 다르다면, 값이 중복되어 Set에 추가되지 않았음 -> 유일성 만족 X)
        return set.size() == relation.length;
    }
}

