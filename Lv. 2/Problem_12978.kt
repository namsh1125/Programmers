import java.util.*

/*
* 프로그래머스 12978번. 배달
* https://school.programmers.co.kr/learn/courses/30/lessons/12978
*/

const val INF = 500_001

class Solution {

    data class Node(val node: Int, val dist: Int) : Comparable<Node> {
        override fun compareTo(other: Node): Int {
            return dist - other.dist
        }
    }

    fun solution(N: Int, road: Array<IntArray>, k: Int): Int {
        var answer = 0
        val dp = IntArray(N + 1) { INF }

        val graph = Array(N + 1) { arrayListOf<Node>() }
        for (i in road.indices) {
            val node1 = road[i][0]
            val node2 = road[i][1]
            val dist = road[i][2]

            graph[node1].add(Node(node2, dist))
            graph[node2].add(Node(node1, dist))
        }

        dp[1] = 0

        val queue = PriorityQueue<Node>()
        queue.add(Node(1, 0))

        while (queue.isNotEmpty()) {
            val cur = queue.remove()

            for (neighbor in graph[cur.node]) {
                if (dp[neighbor.node] > dp[cur.node] + neighbor.dist) {
                    dp[neighbor.node] = dp[cur.node] + neighbor.dist
                    queue.add(Node(neighbor.node, dp[neighbor.node]))
                }
            }
        }

        for (i in 1 until dp.size) {
            if (dp[i] <= k) answer++
        }

        return answer
    }
}
