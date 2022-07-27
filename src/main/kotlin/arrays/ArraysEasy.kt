package arrays

class Arrays {
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var count = 0
        var max = 0
        for (i in nums) {
            if (i > 0) count++
            else count = 0
            max = max.coerceAtLeast(count)
        }
        return max
    }

    fun findEvenNumberedDigits(nums: IntArray): Int {
        var count = 0
        for (i in nums) if (findDigits(i) % 2 == 0) count++
        return count
    }

    fun sortedSquares(nums: IntArray): IntArray {
        for ((idx, i) in nums.withIndex()) nums[idx] = i * i
        return mergeSort(nums)
    }

    /**
     * INSERTION
     * */

    //move the array to the Right to make space for duplicate 0
    fun duplicateZeros(nums: IntArray): IntArray {
        var idx = 0
        while (idx < nums.size) {
            if (nums[idx] == 0) {
                var lastIndex = nums.size - 1
                while (lastIndex > idx) {
                    nums[lastIndex] = nums[lastIndex - 1]
                    lastIndex--
                }
                if (idx < nums.size - 1) nums[idx + 1] = 0
                idx++
            }
            idx++
        }
        return nums
    }

    fun mergeSortedArrays(nums1: IntArray, m: Int, nums2: IntArray, n: Int): IntArray {
        var totalSize = m + n - 1
        var i = m - 1
        var j = n - 1

        while (totalSize >= 0) {
            val num1 = if (i < 0) Int.MIN_VALUE else nums1[i]
            val num2 = if (j < 0) Int.MIN_VALUE else nums2[j]

            nums1[totalSize] = if (num1 > num2) {
                --i
                num1

            } else {
                --j
                num2

            }
            --totalSize
        }
        return nums1
    }

    /**
     * DELETION
     * */

    fun removeElement(nums: IntArray, n: Int): Int {
        var index = 0
        for (i in nums) {
            if (i != n) {
                nums[index] = i
                index++
            }
        }
        return index
    }


    fun removeDuplicates(nums: IntArray): Int {
        var uniqueIndex = 0
        for (i in 1 until nums.size) {
            if (nums[uniqueIndex] != nums[i]) {
                nums[++uniqueIndex] = nums[i]
            }
        }
        println(nums.contentToString())
        return ++uniqueIndex
    }

    /**
     * SEARCH
     * */

    //when x = 0; x * 2 = 0 so exclude x's index from the search
    //from start to x's index - 1 and x's index + 1 to end to search for another 0,
    fun checkIfExist(nums: IntArray): Boolean {
        nums.sort()
        for ((idx, i) in nums.withIndex())
            if(binarySearch(nums, 0, idx - 1, i * 2) >= 0 ||
                    binarySearch(nums, idx + 1, nums.size - 1, i * 2) >= 0) return true
        return false
    }

    fun checkIfMountain(arr: IntArray): Boolean {
        if (arr.size < 3) return false
        var index = 1
        //while runs from start to peak
        while (index < arr.size && arr[index - 1] < arr[index]) {
            index++
        }
        //if no peak found i.e index reached the end of array return false
        if (index == 1 || (index == arr.size)) return false
        //while runs from peak to end
        while (index < arr.size && arr[index - 1] > arr[index]) {
            index++
        }
        //if end found it is a valid mountain array
        return index == arr.size
    }

    /**
     * IN-PLACE OPERATIONS
     * */

    //helper methods
    private fun findDigits(num: Int): Int = (Math.log10(num.toDouble()) + 1).toInt()

    fun mergeSort(nums: IntArray): IntArray {
        if (nums.size < 2) return nums
        val mid = nums.size / 2
        val nums1 = nums.copyOfRange(0, mid)
        val nums2 = nums.copyOfRange(mid, nums.size)
        mergeSort(nums1)
        mergeSort(nums2)
        merge(nums1, nums2, nums)
        return nums
    }

    private fun merge(nums1: IntArray, nums2: IntArray, nums: IntArray): IntArray {
        var i = 0;
        var j = 0;
        var k = 0
        while (i < nums1.size && j < nums2.size) {
            if (nums1[i] < nums2[j]) {
                nums[k] = nums1[i]
                i++
            } else {
                nums[k] = nums2[j]
                j++
            }
            k++
        }

        while (i < nums1.size) {
            nums[k] = nums1[i]
            i++
            k++
        }

        while (j < nums2.size) {
            nums[k] = nums2[j]
            j++
            k++
        }

        return nums
    }

    private fun binarySearch(nums: IntArray, low: Int, high: Int, n: Int): Int{
        if(low > high) return -1
        val mid = (low + high) / 2
        return if (nums[mid] == n) mid
        else if (n < nums[mid]) binarySearch(nums, low, mid - 1, n)
        else binarySearch(nums, mid + 1, high, n)

    }
}


fun main() {
    val arrays = Arrays()
    var nums: IntArray

    nums = intArrayOf(1, 1, 0, 1, 1, 1, 0, 1)
    println("\nReturn Max Consecutive Ones")
    println(nums.contentToString())
    println(arrays.findMaxConsecutiveOnes(nums))

    nums = intArrayOf(34, 678, 19003, 7902)
    println("\n\nReturn how many of them contain an even number of digits.")
    println(nums.contentToString())
    println(arrays.findEvenNumberedDigits(nums))

    nums = intArrayOf(-7, -3, 2, 3, 11)
    println("\n\nReturn an array of the squares of each number sorted in non-decreasing order.")
    println(nums.contentToString())
    println(arrays.sortedSquares(nums).contentToString())

    nums = intArrayOf(1, 0, 2, 3, 0, 4, 5, 0)
    println("\n\nDuplicate each occurrence of zero, shifting the remaining elements to the right.")
    println(nums.contentToString())
    println(arrays.duplicateZeros(nums).contentToString())

    nums = intArrayOf(1, 2, 3, 0, 0, 0)
    var nums2 = intArrayOf(2, 5, 6)
    println("\n\nMerge arr1 and arr2 into a single array sorted in non-decreasing order.")
    println(nums.contentToString())
    println(nums2.contentToString())
    println(arrays.mergeSortedArrays(nums, 3, nums2, 3).contentToString())

    nums = intArrayOf(3, 2, 2, 3)
    val n = 3
    println("\n\nRemove all occurrences of $n in nums in-place.")
    println(nums.contentToString())
    println(arrays.removeElement(nums, n))

    nums = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
    println("\n\nRemove Duplicates from Sorted Array")
    println(nums.contentToString())
    println(arrays.removeDuplicates(nums))

    nums = intArrayOf(-10,12,-20,-8,15)
    println("\n\nCheck if there exists two integers N and M such that N is the double of M")
    println(nums.contentToString())
    println(arrays.checkIfExist(nums))

    nums = intArrayOf(0, 2, 3, 4, 5, 2, 1, 0, 7, 9, 8, 6, 5, 2, 1)
    println("\n\nReturn true if and only if it is a valid mountain array.")
    println(nums.contentToString())
    println(arrays.checkIfMountain(nums))

}