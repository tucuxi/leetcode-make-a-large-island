class Solution {
    fun largestIsland(grid: Array<IntArray>): Int {
        val n = grid.size
        val firstLabel = 2
        val sizes = mutableMapOf<Int, Int>()
        val steps = arrayOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))

        fun label(row: Int, col: Int): Int =
            if (row < 0 || row >= n || col < 0 || col >= n) 0 else grid[row][col]

        fun conquerIsland(label: Int, row: Int, col: Int): Int {
            val queue = mutableListOf<Pair<Int, Int>>()
            var size = 1
            grid[row][col] = label
            queue.add(Pair(row, col))
            while (queue.isNotEmpty()) {
                val (r, c) = queue.removeAt(0)
                for (step in steps) {
                    val nextR = r + step.first
                    val nextC = c + step.second
                    if (label(nextR, nextC) == 1) {
                        size++
                        grid[nextR][nextC] = label
                        queue.add(Pair(nextR, nextC))
                    }
                }
            }
            return size
        }

        fun discoverIslands() {
            var k = firstLabel
            for (row in 0 until n) {
                for (col in 0 until n) {
                    if (grid[row][col] == 1) {
                        val size = conquerIsland(k, row, col)
                        sizes[k] = size
                        k++
                    }
                }
            }
        }

        fun largestCombinedIsland(): Int {
            var maxSize = sizes.getOrDefault(firstLabel, 0)
            for (row in 0 until n) {
                for (col in 0 until n) {
                    if (grid[row][col] == 0) {
                        val neighbors = steps.map { (dr, dc) -> label(row + dr, col + dc) }.toSet()
                        val combinedSize = neighbors.map { label -> sizes.getOrDefault(label, 0) }.sum()
                        maxSize = maxOf(combinedSize + 1, maxSize)
                    }
                }
            }
            return maxSize
        }

        discoverIslands()
        return largestCombinedIsland()
    }
}