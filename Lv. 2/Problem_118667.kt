import kotlin.collections.ArrayDeque

/*
* 프로그래머스 118667번. 두 큐 합 같게 만들기
* https://school.programmers.co.kr/learn/courses/30/lessons/118667
*/

class Solution {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        val q1 = ArrayDeque<Long>()
        val q2 = ArrayDeque<Long>()
        var sum1 = 0L
        var sum2 = 0L

        queue1.forEach {
            q1.add(it.toLong())
            sum1 += it
        }

        queue2.forEach {
            q2.add(it.toLong())
            sum2 += it
        }

        val loopTime = q1.size * 4 // 다시 큐를 원상 복귀할 때까지
        var time = 0

        while (time < loopTime) {
            when {
                sum1 > sum2 -> {
                    sum1 -= q1.first()
                    sum2 += q1.first()
                    q2.add(q1.removeFirst())
                    time++
                }

                sum1 < sum2 -> {
                    sum1 += q2.first()
                    sum2 -= q2.first()
                    q1.add(q2.removeFirst())
                    time++
                }

                else -> return time
            }
        }

        return -1
    }
}
