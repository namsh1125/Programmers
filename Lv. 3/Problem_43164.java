/*
 * 프로그래머스 43164번. 여행경로
 * https://school.programmers.co.kr/learn/courses/30/lessons/43164
 */

import java.util.ArrayList;

class Solution {

    /**
     * 항공권 정보를 이용하여 방문하는 공항 경로를 구하는 함수
     *
     * @param tickets 항공권 정보가 담긴 2차원 배열
     * @return 방문하는 공항 경로
     */
    public String[] solution(String[][] tickets) {
        ArrayList<Ticket> ticketList = new ArrayList<>();
        for (String[] ticket : tickets) {
            ticketList.add(new Ticket(ticket[0], ticket[1]));
        }

        // 알파벳 순서가 앞서는 경로를 구하기 위해 정렬
        ticketList.sort(Ticket::compareTo);

        // DFS 탐색을 통해 경로를 구한다
        ArrayList<String> result = dfs(ticketList, new boolean[tickets.length], "ICN", 0);
        return result.toArray(new String[0]);
    }

    /**
     * DFS 탐색을 통해 경로를 구하는 함수
     *
     * @param tickets 항공권 정보
     * @param used    티켓 사용 여부
     * @param start   출발지
     * @param depth   탐색 깊이
     * @return 경로
     */
    private ArrayList<String> dfs(ArrayList<Ticket> tickets, boolean[] used, String start, int depth) {
        if (depth == tickets.size()) {
            ArrayList<String> result = new ArrayList<>();
            result.add(start);
            return result;
        }

        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < tickets.size(); i++) {
            if (used[i]) { // 이미 사용한 티켓이라면
                continue; // 건너뛴다
            }

            Ticket ticket = tickets.get(i);
            if (ticket.getStart().equals(start)) {
                used[i] = true; // 해당 티켓 사용 처리
                ArrayList<String> next = dfs(tickets, used, ticket.getEnd(), depth + 1); // 이후 경로 탐색

                // 만약 경로가 탐색된다면
                if (next.size() == tickets.size() - depth) {
                    result.add(start);
                    result.addAll(next);
                    return result;
                }

                used[i] = false; // 해당 티켓 사용 해제 처리
            }
        }

        return result;
    }

}

class Ticket implements Comparable<Ticket> {
    private String start;
    private String end;

    public Ticket(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    @Override
    public int compareTo(Ticket o) {
        if (this.start.equals(o.start)) { // 출발지가 같다면
            return this.end.compareTo(o.end); // 도착지로 비교 (오름차순)
        }
        return this.start.compareTo(o.start); // 출발지로 비교 (오름차순)
    }
}
