import java.lang.Integer.max
import java.util.PriorityQueue

/*
* 프로그래머스 176962번. 과제 진행하기
* https://school.programmers.co.kr/learn/courses/30/lessons/176962
*/

class Solution {
    data class Study(
            val subject: String,
            val start: Int,
            var todo: Int
    ) : Comparable<Study> {
        override fun compareTo(other: Study): Int {
            return start - other.start
        }
    }

    fun solution(plans: Array<Array<String>>): Array<String> {
        val studyList = PriorityQueue<Study>()
        for (plan in plans) {
            studyList.add(Study(plan[0], changeTime(plan[1]), plan[2].toInt()))
        }

        val result = arrayListOf<String>() // 공부를 마치는 과목 순서
        var time = studyList.first().start // 현재 시간
        val left = ArrayDeque<Study>() // 공부를 하다 만 과목
        var cur: Study // 현재 공부하는 과목

        while (studyList.isNotEmpty()) {
            cur = studyList.remove()

            // 현재 과목 이후 더 공부할 과목이 없다면
            if (studyList.isEmpty()) {
                result.add(cur.subject)

                // 가장 최근에 멈춘 과목부터 순차로 공부
                while (left.isNotEmpty()) {
                    result.add(left.removeLast().subject)
                }
            }

            // 현재 과목 이후 더 공부해야 할 과목이 있다면
            else {
                val end = studyList.first().start // 현재 과목을 최대로 공부할 수 있는 시간
                val studyTime = end - time // 현재 과목을 공부할 수 있는 시간

                when {
                    // 현재 과목을 다 공부하지 못 한 경우
                    studyTime < cur.todo -> {
                        left.add(Study(cur.subject, cur.start, cur.todo - studyTime))
                        time += studyTime
                    }

                    // 현재 과목을 다 공부한 경우
                    else -> {
                        result.add(cur.subject)
                        time += cur.todo

                        var spareTime = end - time // 자투리 시간. 못 한 과목을 더 공부할 수 있는 시간
                        while (left.isNotEmpty()) {
                            cur = left.removeLast() // 가장 최근에 멈춘 과제

                            when {
                                // 자투리 시간 안에 공부할 수 있는 경우
                                cur.todo <= spareTime -> {
                                    result.add(cur.subject)
                                    time += cur.todo
                                    spareTime -= cur.todo
                                }

                                // 자투리 시간 안에 공부할 수 없는 경우
                                else -> {
                                    cur.todo -= spareTime
                                    left.add(cur)
                                    time += spareTime
                                    break
                                }
                            }
                        }

                        time = max(time, end)
                    }
                }
            }
        }

        return result.toTypedArray()
    }

    fun changeTime(time: String): Int {
        val (h, m) = time.split(':').map { it.toInt() }
        return h * 60 + m
    }
}
