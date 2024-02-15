package kakao.winterinternship2024

class Solution {

    class GiftStat {
        var send = 0
        var receive = 0
        var index = 0
    }

    fun solution(friends: Array<String>, gifts: Array<String>): Int {

        val giftTable: HashMap<String, HashMap<String, Int>> = hashMapOf()
        val giftStatTable: HashMap<String, GiftStat> = hashMapOf()

        friends.forEach { i ->
            val temp = hashMapOf<String, Int>()
            friends.forEach { j ->
                temp[j] = 0
            }
            giftTable[i] = temp
            giftStatTable[i] = GiftStat()
        }

        gifts.forEach {
            val tempPair = it.split(" ")
            val a = tempPair[0]
            val b = tempPair[1]

            giftTable[a]!![b] = giftTable[a]!![b]!! + 1
            giftStatTable[a]!!.send += 1
            giftStatTable[a]!!.index += 1
            giftStatTable[b]!!.receive += 1
            giftStatTable[b]!!.index += -1
        }

        val friendsExpectation = hashMapOf<String, Int>()

        friends.forEach {
            friendsExpectation[it] = 0
        }

        giftTable.forEach { i ->
            i.value.forEach { j ->
                if (i.key != j.key) {
                    val temp = j.value - giftTable[j.key]!![i.key]!!
                    if (temp > 0) {
                        friendsExpectation[i.key] = friendsExpectation[i.key]!! + 1
                    } else if (temp < 0) {
                        friendsExpectation[j.key] = friendsExpectation[j.key]!! + 1
                    } else {
                        if (giftStatTable[i.key]!!.index > giftStatTable[j.key]!!.index) {
                            friendsExpectation[i.key] = friendsExpectation[i.key]!! + 1
                        } else if (giftStatTable[i.key]!!.index < giftStatTable[j.key]!!.index) {
                            friendsExpectation[j.key] = friendsExpectation[j.key]!! + 1
                        }
                    }
                }

            }
        }

        return friendsExpectation.values.maxOrNull()!! / 2
    }
}