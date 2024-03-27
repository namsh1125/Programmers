/*
 * 프로그래머스 150370번. 개인정보 수집 유효기간
 * https://school.programmers.co.kr/learn/courses/30/lessons/150370
 */

import java.util.*;

class Solution {

    private Map<String, Integer> mapTerm = new HashMap<>();

    /**
     * 개인정보 수집 유효기간을 확인하여 파기해야 할 개인정보의 번호를 반환하는 함수
     *
     * @param today     오늘 날짜를 의미하는 문자열
     * @param terms     약관의 유효기간을 담은 1차원 문자열 배열
     * @param privacies 수집된 개인정보의 정보를 담은 1차원 문자열 배열
     * @return 파기해야 할 개인정보의 번호를 오름차순으로 1차원 정수 배열
     */
    public int[] solution(String today, String[] terms, String[] privacies) {
        // 약관을 map 객체에 저장
        setTerm(terms);

        ArrayList<Integer> answer = new ArrayList<>();

        // privacy 배열을 돌며 개인정보의 유효기간을 확인
        for (int i = 0; i < privacies.length; i++) {
            String[] s = privacies[i].split(" ");

            // 날짜와 약관정보를 분리
            String date = s[0];
            String term = s[1];

            // 오늘 날짜와 비교하여 유효기간이 지났는지 확인
            // 지났다면 answer 배열에 추가
            if (convertDate(today) - convertDate(date) >= mapTerm.get(term) * 28) {
                answer.add(i + 1);
            }
        }

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    /**
     * String 형식의 날짜를 long 형식으로 변환하는 함수
     * 1달은 28일
     *
     * @param date 변환할 날짜
     */
    private long convertDate(String date) {
        String[] s = date.split("\\.");
        return Long.parseLong(s[0]) * 28 * 12 + Long.parseLong(s[1]) * 28 + Long.parseLong(s[2]);
    }

    /**
     * 약관을 map 객체에 저장하는 함수
     *
     * @param terms 약관의 유효기간을 담은 1차원 문자열 배열
     */
    private void setTerm(String[] terms) {
        for (String term : terms) {
            String[] s = term.split(" ");
            mapTerm.put(s[0], Integer.parseInt(s[1]));
        }
    }
}
