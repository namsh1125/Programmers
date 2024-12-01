/*
 * 프로그래머스 42861번. 섬 연결하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/42861
 */

import java.util.PriorityQueue;

class Solution {

    /**
     * 최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용을 구하는 함수
     *
     * @param n     섬의 개수
     * @param costs n개의 섬 사이에 다리를 건설하는 비용
     * @return 최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용
     */
    public int solution(int n, int[][] costs) {
        // 크루스칼 알고리즘을 사용하기 위해 비용을 기준으로 오름차순 정렬
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int[] cost : costs) {
            pq.offer(new Edge(cost[0], cost[1], cost[2]));
        }

        // 부모 노드를 저장할 배열 생성
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 크루스칼 알고리즘 수행
        return kruskal(n, pq, parent);
    }

    /**
     * 크루스칼 알고리즘을 수행하여 최소 비용을 구하는 함수
     *
     * @param n      섬의 개수
     * @param pq     비용을 기준으로 오름차순 정렬된 간선 정보
     * @param parent 부모 노드를 저장할 배열
     * @return 최소 비용
     */
    public int kruskal(int n, PriorityQueue<Edge> pq, int[] parent) {
        int answer = 0;
        int edgeCount = 0;

        while (edgeCount < n - 1) {
            Edge edge = pq.poll();
            int start = edge.getStart();
            int end = edge.getEnd();
            int cost = edge.getCost();

            if (findParent(parent, start) != findParent(parent, end)) {
                unionParent(parent, start, end);
                answer += cost;
                edgeCount++;
            }
        }

        return answer;
    }

    /**
     * 두 노드의 부모 노드를 찾아 두 노드를 연결하는 함수
     *
     * @param parent 부모 노드를 저장할 배열
     * @param a      노드 1
     * @param b      노드 2
     */
    public void unionParent(int[] parent, int a, int b) {
        a = findParent(parent, a);
        b = findParent(parent, b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    /**
     * 노드의 부모 노드를 찾는 함수
     *
     * @param parent 부모 노드를 저장할 배열
     * @param x      노드
     * @return 부모 노드
     */
    public int findParent(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = findParent(parent, parent[x]);
        }
        return parent[x];
    }
}

class Edge implements Comparable<Edge> {
    private int start;
    private int end;
    private int cost;

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost; // 비용 순으로 오름차순
    }
}
