import java.util.PriorityQueue
import kotlin.math.min

/*
* 프로그래머스 172927번. 광물 캐기
* https://school.programmers.co.kr/learn/courses/30/lessons/172927
*/

class Solution {
    data class Time(
            val index: Int,
            val priority: Int
    ) : Comparable<Time> {
        override fun compareTo(other: Time): Int {
            return other.priority - priority
        }
    }

    fun solution(picks: IntArray, minerals: Array<String>): Int {
        val priority = IntArray(minerals.size / 5 + 1) { 0 }

        minerals.forEachIndexed { index, s ->
            when (s) {
                "diamond" -> priority[index / 5] += 25
                "iron" -> priority[index / 5] += 5
                "stone" -> priority[index / 5] += 1
            }
        }

        val queue = PriorityQueue<Time>()
        // 곡괭이 개수가 광물 개수보다 많거나 같은 경우
        if (minerals.size / 5 + 1 <= picks.sum()) {
            priority.forEachIndexed { index, value ->
                queue.add(Time(index, value))
            }
        }

        // 곡괭이 개수가 광물 개수보다 적은 경우
        else {
            for (i in 0 until picks.sum()) {
                queue.add(Time(i, priority[i]))
            }
        }

        val pickQueue = ArrayDeque<Int>()
        for (i in 0 until 3) {
            for (j in 0 until picks[i]) {
                pickQueue.add(i)
            }
        }

        var result = 0
        while (queue.isNotEmpty() && pickQueue.isNotEmpty()) {
            val pick = pickQueue.removeFirst()
            val start = queue.remove().index * 5

            for (i in start until min(start + 5, minerals.size)) {
                when (pick) {
                    // 다이아몬드 곡괭이
                    0 -> {
                        when (minerals[i]) {
                            "diamond" -> result += 1
                            "iron" -> result += 1
                            "stone" -> result += 1
                        }
                    }
                    // 철 곡괭이
                    1 -> {
                        when (minerals[i]) {
                            "diamond" -> result += 5
                            "iron" -> result += 1
                            "stone" -> result += 1
                        }
                    }
                    // 돌 곡괭이
                    2 -> {
                        when (minerals[i]) {
                            "diamond" -> result += 25
                            "iron" -> result += 5
                            "stone" -> result += 1
                        }
                    }
                }
            }
        }

        return result
    }
}
