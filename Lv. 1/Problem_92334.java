/*
 * 프로그래머스 92334번. 신고 결과 받기
 * https://school.programmers.co.kr/learn/courses/30/lessons/92334
 */

import java.util.*;

class Solution {

    /**
     * 각 유저별로 처리 결과 메일을 받은 횟수를 구하는 메소드
     *
     * @param idList  이용자의 ID가 담긴 문자열 배열
     * @param reports 각 이용자가 신고한 이용자의 ID 정보가 담긴 문자열 배열
     * @param k       정지 기준이 되는 신고 횟수
     * @return 각 유저별로 처리 결과 메일을 받은 횟수
     */
    public int[] solution(String[] idList, String[] reports, int k) {
        // 각 유저별로 신고한 사람을 추출
        Map<String, Set<String>> mapReport = extractReport(reports);

        // 각 유저별로 신고당한 횟수를 구함
        Map<String, Integer> mapCount = countReport(mapReport);

        // 각 유저가 정지되었는지 확인
        Map<String, Boolean> mapStop = stopReport(mapCount, k);

        // 각 유저별로 처리 결과 메일을 받은 횟수를 구함
        return countMail(mapReport, mapStop, idList);
    }

    /**
     * 각 유저별로 처리 결과 메일을 받은 횟수를 구하는 메소드
     *
     * @param mapReport 각 유저별로 신고한 사람을 저장한 Map
     * @param mapStop   각 유저별로 정지 여부를 저장한 Map
     * @param idList    이용자의 ID가 담긴 문자열 배열
     */
    private int[] countMail(Map<String, Set<String>> mapReport, Map<String, Boolean> mapStop, String[] idList) {
        // 각 유저별로 처리 결과 메일을 받은 횟수를 저장하는 Map
        int[] answer = new int[idList.length];

        for (String user : idList) {
            // 유저가 신고한 사람
            Set<String> set = mapReport.getOrDefault(user, new HashSet<>());

            // 유저가 정지되었는지 확인
            for (String reportee : set) {
                if (mapStop.get(reportee)) {
                    answer[Arrays.asList(idList).indexOf(user)]++;
                }
            }
        }

        return answer;
    }

    /**
     * 각 유저별로 정지 여부를 확인하는 메소드
     *
     * @param mapCount 각 유저별로 신고당한 횟수를 저장한 Map
     * @param k        정지 기준이 되는 신고 횟수
     */
    private Map<String, Boolean> stopReport(Map<String, Integer> mapCount, int k) {
        // 각 유저별로 정지 여부를 저장하는 Map
        Map<String, Boolean> mapStop = new HashMap<>();

        for (String key : mapCount.keySet()) {
            if (mapCount.get(key) >= k) {
                mapStop.put(key, true);
            } else {
                mapStop.put(key, false);
            }
        }

        return mapStop;
    }

    /**
     * 사용자별로 신고당한 횟수를 구하는 메소드
     *
     * @param mapReport 각 유저별로 신고한 사람을 저장한 Map
     */
    private Map<String, Integer> countReport(Map<String, Set<String>> mapReport) {
        // 각 유저별로 신고당한 횟수를 저장하는 Map
        Map<String, Integer> mapCount = new HashMap<>();

        for (String key : mapReport.keySet()) {
            // 신고 당한 사람
            Set<String> set = mapReport.get(key);

            // 신고당한 횟수 저장
            for (String user : set) {
                mapCount.put(user, mapCount.getOrDefault(user, 0) + 1);
            }
        }

        return mapCount;
    }

    /**
     * 유저가 신고한 사람을 추출하는 메소드
     *
     * @param reports 각 이용자가 신고한 이용자의 ID 정보가 담긴 문자열 배열
     */
    private Map<String, Set<String>> extractReport(String[] reports) {
        // 각 유저별로 신고한 사람을 저장하는 Map
        Map<String, Set<String>> mapReport = new HashMap<>();

        for (String report : reports) {
            String[] s = report.split(" ");
            String reporter = s[0];
            String reportee = s[1];

            // 유저가 신고한 사람 저장
            if (mapReport.containsKey(reporter)) {
                mapReport.get(reporter).add(reportee);
            } else {
                Set<String> set = new HashSet<>();
                set.add(reportee);
                mapReport.put(reporter, set);
            }
        }

        return mapReport;
    }

}
