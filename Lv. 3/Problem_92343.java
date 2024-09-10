/*
 * 프로그래머스 92343번. 양과 늑대
 * https://school.programmers.co.kr/learn/courses/30/lessons/92343
 */

import java.util.ArrayList;

class Solution {

    private static final int SHEEP = 0;
    private static final int WOLF = 1;

    /**
     * 각 노드를 방문하면서 모을 수 있는 최대 양의 수를 구하는 메소드
     *
     * @param info  각 노드에 있는 양 또는 늑대에 대한 정보가 담긴 배열 (0: 양, 1: 늑대)
     * @param edges 2진 트리의 각 노드들의 연결 관계를 담은 2차원 배열
     * @return 각 노드를 방문하면서 모을 수 있는 최대 양의 수
     */
    public int solution(int[] info, int[][] edges) {
        // 입력받은 간선 정보를 사용해 그래프를 생성한다.
        ArrayList<ArrayList<Edge>> graph = getGraph(edges, info.length);

        // 방문할 수 있는 노드를 저장하는 리스트를 생성한다.
        ArrayList<Integer> visitable = new ArrayList<>();
        visitable.add(0); // 루트 노드

        return dfs(getGraph(edges, info.length), info, 0, 1, 0, visitable);
    }

    /**
     * 각 노드를 방문하면서 모을 수 있는 최대 양의 수를 구하는 메소드
     *
     * @param graph      2진 트리의 각 노드들의 연결 관계를 담은 그래프
     * @param info       각 노드에 있는 양 또는 늑대에 대한 정보가 담긴 배열 (0: 양, 1: 늑대)
     * @param nodeIndex  현재 노드의 인덱스
     * @param sheepCount 현재 노드까지 모은 양의 수
     * @param wolfCount  현재 노드까지 모은 늑대의 수
     * @param visitable  각 노드를 다음에 방문할 수 있는지 여부를 저장하는 배열
     * @return 각 노드를 방문하면서 모을 수 있는 최대 양의 수
     */
    private int dfs(ArrayList<ArrayList<Edge>> graph, int[] info, int nodeIndex, int sheepCount, int wolfCount, ArrayList<Integer> visitable) {
        if (sheepCount <= wolfCount) {
            return -1;
        }

        if (visitable.isEmpty()) { // 더이상 방문할 수 있는 노드가 없는 경우
            return sheepCount;
        }

        // 방문 가능한 노드에서 현재 노드를 제외한다.
        visitable.remove(Integer.valueOf(nodeIndex));

        // 주변 노드를 방문할 수 있는지 확인한다.
        for (Edge edge : graph.get(nodeIndex)) { // 현재 노드 기준에서 움직일 수 있는 간선을 확인한다.
            visitable.add(edge.to); // 다음 노드를 방문할 수 있는지 여부를 추가한다.
        }

        // 현재 노드에서 방문 가능한 노드로 이동한다.
        int maxSheepCount = sheepCount;
        for (int nextIndex : visitable) {
            int nextSheepCount = sheepCount + (info[nextIndex] == SHEEP ? 1 : 0);
            int nextWolfCount = wolfCount + (info[nextIndex] == WOLF ? 1 : 0);

            maxSheepCount = Math.max(maxSheepCount, dfs(graph, info, nextIndex, nextSheepCount, nextWolfCount, new ArrayList<>(visitable)));
        }

        return maxSheepCount;
    }

    /**
     * 간선 정보를 사용해 그래프를 생성하는 메소드
     *
     * @param edges     2차원 배열로 표현된 간선 정보
     * @param nodeCount 노드의 개수
     * @return 간선 정보 기반의 그래프
     */
    private ArrayList<ArrayList<Edge>> getGraph(int[][] edges, int nodeCount) {
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보를 사용해 그래프를 생성한다.
        for (int[] edge : edges) {
            int from = edge[0]; // 부모 노드
            int to = edge[1]; // 자식 노드
            graph.get(from).add(new Edge(from, to)); // 부모 노드에서 자식 노드로 가는 간선 추가
        }

        return graph;
    }

    /**
     * 간선 정보를 담는 클래스
     */
    public class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}
