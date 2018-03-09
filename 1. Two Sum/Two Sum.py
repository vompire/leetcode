

"""Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice."""

class Solution1(object):
    """这里考虑存在多组的情况"""
    """有误"""
    def twoSum(self, nums, target):
        nums_1 = list(map(lambda x:x[0]-x[1], zip([target]*len(nums),nums)))
        intersection = list(set(nums_1).intersection(set(nums)))
        result = []
        if len(intersection) == 0:
            return False
        else:
            for item in intersection:
                if nums.index(item) != nums_1.index(item):
                    result.append([nums.index(item),nums_1.index(item)])
                    intersection.remove(target-item)
        return result


class Solution2(object):
    """这里不考虑存在多组的情况"""
    """有误"""
    def twoSum(self, nums, target):
        nums_1 = list(map(lambda x: x[0] - x[1], zip([target] * len(nums), nums)))
        intersection = list(set(nums_1).intersection(set(nums)))
        if len(intersection) == 0:
            return False
        else:
            for item in intersection:
                if nums.index(item) != nums_1.index(item):
                    return [nums.index(item),nums_1.index(item)]
            return False

class Solution3(object):
    def twoSum(self, nums, target):
        for i in range(len(nums)):
            item = target-nums[i]
            try:
                return [i,nums[i+1:].index(item)+i+1]
            except:
                pass
        return False

class Solution4(object):
    # @return a tuple, (index1, index2)
    # 8:42
    """利用字典来记录每一位所期望的元素"""
    def twoSum(self, num, target):
        map = {}
        for i in range(len(num)):
            if num[i] not in map:
                map[target - num[i]] = i
            else:
                return map[num[i]], i

        return False


if __name__ == '__main__':
    a = [3,5,3]
    s = Solution4()
    print(s.twoSum(a,6))