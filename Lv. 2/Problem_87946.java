/*
 * 프로그래머스 87946번. 피로도
 * https://school.programmers.co.kr/learn/courses/30/lessons/87946
 */

import java.util.ArrayList;

class Solution {

    /**
     * 유저가 탐험할수 있는 최대 던전 수를 구하는 함수
     *
     * @param k        유저의 현재 피로도
     * @param dungeons 각 던전별 "최소 필요 피로도", "소모 피로도"가 담긴 2차원 배열
     * @return 유저가 탐험할수 있는 최대 던전 수
     */
    public int solution(int k, int[][] dungeons) {
        // 던전의 최대 갯수는 8개 -> DFS로 모든 경우의 수를 탐색해도 시간복잡도가 크지 않다.
        // DFS로 모든 경우의 수를 탐색하면서 최대 던전 수를 구한다.

        // 던전 정보를 담을 리스트
        ArrayList<Dungeon> dungeonList = new ArrayList<>();
        for (int[] dungeon : dungeons) {
            dungeonList.add(new Dungeon(dungeon[0], dungeon[1]));
        }

        // 던전 방문 여부를 체크할 배열
        boolean[] visited = new boolean[dungeonList.size()];

        // DFS로 모든 경우의 수를 탐색하면서 최대 탐험할 수 있는 던전 수를 구한다.
        return dfs(dungeonList, visited, 0, k);
    }

    /**
     * DFS로 모든 경우의 수를 탐색하면서 최대 탐험할 수 있는 던전 수를 구하는 함수
     *
     * @param dungeons 던전 정보 리스트
     * @param visited  던전 방문 여부를 체크할 배열
     * @param depth    현재 탐색 깊이
     * @param fatigue  현재 피로도
     * @return 최대 탐험할 수 있는 던전 수
     */
    private int dfs(ArrayList<Dungeon> dungeons, boolean[] visited, int depth, int fatigue) {
        if (depth == dungeons.size()) { // 모든 던전을 탐험한 경우
            return 0;
        }

        int maxDungeon = 0; // 최대 탐험할 수 있는 던전 수
        for (int i = 0; i < dungeons.size(); i++) {
            // 이미 방문한 던전인 경우
            if (visited[i]) {
                continue;
            }

            // 던전을 방문하는 경우
            Dungeon dungeon = dungeons.get(i);
            if (fatigue < dungeon.minFatigue) {
                continue;
            } else {
                visited[i] = true;
                maxDungeon = Math.max(maxDungeon, 1 + dfs(dungeons, visited, depth + 1, fatigue - dungeon.consumeFatigue));
            }

            // 던전을 방문하지 않은 경우
            visited[i] = false;
            maxDungeon = Math.max(maxDungeon, dfs(dungeons, visited, depth + 1, fatigue));
        }

        return maxDungeon;
    }

    public class Dungeon {
        int minFatigue; // 최소 필요 피로도
        int consumeFatigue; // 소모 피로도

        public Dungeon(int minFatigue, int consumeFatigue) {
            this.minFatigue = minFatigue;
            this.consumeFatigue = consumeFatigue;
        }
    }
}
