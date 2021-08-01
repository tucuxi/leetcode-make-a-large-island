class Solution {
    fun largestIsland(grid: Array<IntArray>): Int {
        val n = grid.size
        val sizes = mutableMapOf<Int, Int>()

        fun label(i: Int, j: Int): Int =
            if (i < 0 || i >= n || j < 0 || j >= n) 0 else grid[i][j]

        fun discoverIslands(firstLabel: Int) {
            require(firstLabel > 1)
            var k = firstLabel
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (grid[i][j] == 1) {
                        val above = label(i - 1, j)
                        val left = label(i, j - 1)
                        if (above == 0 && left == 0) {
                            grid[i][j] = k
                            sizes[k] = 1
                            k++
                        } else if (above == 0) {
                            grid[i][j] = left
                            sizes.merge(left, 1, Int::plus)
                        } else if (left == 0) {
                            grid[i][j] = above
                            sizes.merge(above, 1, Int::plus)
                        } else if (above == left) {
                            grid[i][j] = above
                            sizes.merge(above, 1, Int::plus)
                        } else {
                            grid[i][j] = above
                            sizes[above] = sizes[above]!! + sizes[left]!! + 1
                            sizes.remove(left)
                            for (p in i downTo 0) {
                                var changed = false
                                for (q in 0 until n) {
                                    if (grid[p][q] == left) {
                                        grid[p][q] = above
                                        changed = true
                                    }
                                }
                                if (!changed) break
                            }
                        }
                    }
                }
            }
        }

        fun largestCombinedIslandSize(): Int {
            var maxSize = 0
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (grid[i][j] == 0) {
                        val neighbors = setOf(
                            label(i - 1, j), label(i + 1, j),
                            label(i, j - 1), label(i, j + 1)
                        )
                        val combinedSize = neighbors.map { label -> sizes.getOrDefault(label, 0) }.sum()
                        maxSize = maxOf(combinedSize + 1, maxSize)
                    }
                }
            }
            return maxSize
        }

        discoverIslands(2)
        val largestSingleIslandSize = sizes.values.fold(0) { acc, value -> maxOf(acc, value) }
        return maxOf(largestCombinedIslandSize(), (largestSingleIslandSize + 1).coerceAtMost(n * n))
    }
}