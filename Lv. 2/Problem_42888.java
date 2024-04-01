/*
 * 프로그래머스 42888번. 오픈채팅방
 * https://school.programmers.co.kr/learn/courses/30/lessons/42888
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Solution {

    private static final String ENTER = "Enter";
    private static final String LEAVE = "Leave";
    private static final String CHANGE = "Change";

    /**
     * 모든 기록이 처리된 후, 최종적으로 방을 개설한 사람이 보게 되는 메시지를 반환하는 메소드
     *
     * @param record 채팅방에 들어오고 나가거나, 닉네임을 변경한 기록이 담긴 문자열 배열
     * @return 모든 기록이 처리된 후, 최종적으로 방을 개설한 사람이 보게 되는 메시지
     */
    public String[] solution(String[] record) {
        // 유저 아이디와 닉네임을 저장할 맵
        Map<String, String> user = new HashMap<>();

        // 들어오고 나가는 기록을 저장할 리스트
        ArrayList<Log> list = new ArrayList<>();

        // record를 순회하며 기록을 저장
        for (String r : record) {
            String[] split = r.split(" ");

            switch (split[0]) {
                case CHANGE -> user.put(split[1], split[2]);
                case ENTER -> {
                    user.put(split[1], split[2]);
                    list.add(new Log(split[1], ENTER));
                }
                case LEAVE -> list.add(new Log(split[1], LEAVE));
            }
        }

        return getMessage(list, user)
                .toArray(String[]::new);
    }

    /**
     * 들어오고 나가는 기록을 순회하며 메시지를 생성하는 메소드
     *
     * @param list 들어오고 나가는 기록이 담긴 로그 정보 (userId, action)
     * @param user 사용자 정보 (userId, 닉네임)
     * @return 메시지가 담긴 리스트
     */
    private static ArrayList<String> getMessage(ArrayList<Log> list, Map<String, String> user) {
        ArrayList<String> answer = new ArrayList<>();

        for (Log log : list) {
            switch (log.action) {
                case ENTER -> answer.add(user.get(log.userId) + "님이 들어왔습니다.");
                case LEAVE -> answer.add(user.get(log.userId) + "님이 나갔습니다.");
            }
        }

        return answer;
    }

    static class Log {
        String userId;
        String action;

        public Log(String userId, String action) {
            this.userId = userId;
            this.action = action;
        }
    }
}
