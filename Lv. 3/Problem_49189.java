/*
 * 프로그래머스 49189번. 가장 먼 노드
 * https://school.programmers.co.kr/learn/courses/30/lessons/49189
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {

    private final int INF = (int) 1e9; // 10억

    /**
     * 1번 노드로부터 가장 멀리 떨어진 노드의 개수를 구하는 함수
     *
     * @param n     노드의 개수
     * @param edges 간선에 대한 정보가 담긴 2차원 배열
     * @return 1번 노드로부터 가장 멀리 떨어진 노드
     */
    public int solution(int n, int[][] edges) {
        // 그래프 정보를 저장할 리스트
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];

            graph.get(start).add(new Edge(start, end));
            graph.get(end).add(new Edge(end, start));
        }

        // 1번 노드에서부터의 거리를 저장할 배열
        int[] distance = new int[n + 1];
        Arrays.fill(distance, INF);
        distance[1] = 0;

        // 1번 노드에서부터 최소 거리를 구하기 (다익스트라 알고리즘)
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(1, 0));

        while (!queue.isEmpty()) {
            Node node = queue.remove();

            // 현재 노드까지 오는 거리가 저장된 거리보다 크다면 무시
            if (distance[node.index] < node.distance) {
                continue;
            }

            // 현재 노드와 연결된 노드들을 확인
            for (Edge edge : graph.get(node.index)) {
                int cost = distance[node.index] + 1;

                // 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
                if (cost < distance[edge.end]) {
                    distance[edge.end] = cost;
                    queue.add(new Node(edge.end, cost));
                }
            }
        }

        // 가장 멀리 떨어진 노드의 개수를 구하기
        return Arrays.stream(distance)
                .filter(d -> d == Arrays.stream(distance).filter(value -> value != INF).max().getAsInt())
                .toArray()
                .length;
    }

    public class Edge {
        int start;
        int end;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public class Node implements Comparable<Node> {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }
}
