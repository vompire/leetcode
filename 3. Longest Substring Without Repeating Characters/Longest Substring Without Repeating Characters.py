

"""Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring,

"pwke" is a subsequence and not a substring."""

class Solution(object):
    """每次查询最长不同子序列，都利用之前的子序列的一部分"""
    def lengthOfLongestSubstring(self, s):
        index = 0
        length = 0
        if len(s) == 1:
            return 1
        for i in range(len(s)-1):
            if s[i+1] not in s[index:i+1]:
                if i+2 == len(s):
                    if i - index + 2 > length:
                        length = i - index + 2
                else:
                    pass
            else:
                if i-index+1>length:
                    length = i-index+1
                index = s[index:i+1].index(s[i+1])+1+index
        return length


class Solution2(object):
    """巧妙地利用字典来记录每一个字符的位置"""
    def lengthOfLongestSubstring(self, s):
        dic, res, start, = {}, 0, 0
        for i, ch in enumerate(s):
            # when char already in dictionary
            if ch in dic:
                # check length from start of string to index
                res = max(res, i-start)
                # update start of string index to the next index
                start = max(start, dic[ch]+1)
            # add/update char to/of dictionary
            dic[ch] = i
        # answer is either in the begining/middle OR some mid to the end of string
        return max(res, len(s)-start)

s = Solution()
a = "abcabcbb"
print(s.lengthOfLongestSubstring(a))


