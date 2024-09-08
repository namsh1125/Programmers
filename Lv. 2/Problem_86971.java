/*
 * 프로그래머스 86971번. 전력망을 둘로 나누기
 * https://school.programmers.co.kr/learn/courses/30/lessons/86971
 */

import java.util.ArrayList;

class Solution {

    /**
     * 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때, 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 반환하는 메소드
     *
     * @param n     송전탑의 개수
     * @param wires 전선 정보
     * @return 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때, 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)
     */
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < wires.length; i++) { // i: 끊을 전선
            // i번째 전선을 제외한 나머지 전선들로 graph 생성
            ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
            for (int j = 0; j <= n; j++) {
                graph.add(new ArrayList<>());
            }

            // 전선 정보로 graph 생성
            for (int j = 0; j < wires.length; j++) {
                if (i == j) {
                    continue;
                }
                int from = wires[j][0];
                int to = wires[j][1];
                graph.get(from).add(new Edge(from, to));
                graph.get(to).add(new Edge(to, from));
            }

            // DFS로 연결된 송전탑 개수 구하기
            boolean[] visited = new boolean[n + 1];
            int count1 = dfs(1, graph, visited); // 1번 송전탑과 연결된 송전탑 개수
            for (int j = 1; j <= n; j++) {
                if (!visited[j]) {
                    int count2 = dfs(j, graph, visited); // 1번 송전탑과 연결되지 않은 송전탑과 연결된 송전탑 개수
                    answer = Math.min(answer, Math.abs(count1 - count2));
                }
            }

        }

        return answer;
    }

    /**
     * DFS로 연결된 송전탑 개수 구하기
     *
     * @param node    탐색하고자 하는 송전탑
     * @param graph   그래프
     * @param visited 특정 송전탑의 방문 여부가 저장된 배열
     * @return
     */
    private int dfs(int node, ArrayList<ArrayList<Edge>> graph, boolean[] visited) {
        visited[node] = true;
        int count = 1;

        for (Edge edge : graph.get(node)) {
            if (!visited[edge.to]) {
                count += dfs(edge.to, graph, visited);
            }
        }

        return count;
    }

    public class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}
