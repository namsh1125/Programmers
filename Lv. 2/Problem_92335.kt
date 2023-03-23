import kotlin.math.sqrt

/*
* 프로그래머스 92335번. k진수에서 소수 개수 구하기
* https://school.programmers.co.kr/learn/courses/30/lessons/92335
*/

class Solution {
    fun solution(n: Int, k: Int): Int {
        var answer: Int

        val num = convert(n, k)
        val extractList = extract(num)
        answer = getResult(extractList)

        return answer
    }

    private fun convert(n: Int, k: Int): String {
        var num = n
        var result = ""

        while (num != 0) {
            result = "${num % k}" + result
            num /= k
        }

        return result
    }

    private fun extract(num: String): ArrayList<Long> {
        val result = ArrayList<Long>()

        var index = 0
        var tmp = ""
        while (index != num.length) {
            if (num[index] != '0') tmp += num[index]
            if (num[index] == '0' || index == num.lastIndex) {
                if (tmp != "") {
                    result.add(tmp.toLong())
                    tmp = ""
                }
            }

            index++
        }

        return result
    }

    private fun getResult(arr: ArrayList<Long>): Int {
        val isPrime = checkPrime(arr)

        var result = 0
        for (i in isPrime.indices) {
            if (isPrime[i]) result++
        }

        return result
    }

    private fun checkPrime(arr: ArrayList<Long>): BooleanArray {
        val isPrime = BooleanArray(arr.size) { true }

        for (i in arr.indices) {
            if (arr[i] == 1L) isPrime[i] = false
            else {
                val j = sqrt(arr[i].toDouble()).toInt()

                for (k in 2..j) {
                    if (arr[i] % k == 0L) {
                        isPrime[i] = false
                        break
                    }
                }
            }
        }

        return isPrime
    }
}
