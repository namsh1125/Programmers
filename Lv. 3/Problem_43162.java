/*
 * 프로그래머스 43162번. 네트워크
 * https://school.programmers.co.kr/learn/courses/30/lessons/43162
 */

import java.util.HashSet;

class Solution {

    /**
     * @param n         컴퓨터의 개수
     * @param computers 연결에 대한 정보가 담긴 2차원 배열
     * @return 네트워크의 개수
     */
    public int solution(int n, int[][] computers) {
        int[] parent = new int[n]; // 부모 노드를 저장할 배열

        // 부모 노드 초기화
        for (int i = 0; i < n; i++) {
            parent[i] = i; // 초기에는 자기 자신이 부모 노드
        }

        // 네트워크 연결
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1) {
                    unionParent(parent, i, j);
                }
            }
        }

        // 서로 다른 네트워크 개수 구하기
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(findParent(parent, i));
        }

        return set.size();
    }

    private void unionParent(int[] parent, int node1, int node2) {
        int parent1 = findParent(parent, node1);
        int parent2 = findParent(parent, node2);

        if (parent1 < parent2) {
            parent[parent2] = parent1;
        } else {
            parent[parent1] = parent2;
        }
    }

    private int findParent(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = findParent(parent, parent[node]);
        }

        return parent[node];
    }

}
