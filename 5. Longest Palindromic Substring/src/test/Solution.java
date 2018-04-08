package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
*/
public class Solution {
	public static String longestPalindrome(String s) {
		//粗暴解法
        int length = 1;
        int flag = 2;
        List<Integer> index = new ArrayList<>();
        for(int i=1;i<s.length()-1;i++)
        {
        	if(s.substring(i-1, i).equals(s.substring(i+1, i+2)))
        	{
        		index.add(i);
        	}
        }
        int core = 0;
        int maxlen_core = 0;
        if(index.size()>0)
        {
        	length = 3;
        	for(int i=0;i<index.size();i++)
            {
        		flag = 0;
        		core = index.get(i);
            	int begin = index.get(i)-2;
            	int end = index.get(i)+2;
            	while(begin>=0&&end<s.length())
            	{
            		if(s.substring(begin,begin+1).equals(s.substring(end,end+1)))
            		{
            			if(length<end-begin+1)
            			{
            				length = end-begin+1;
            				maxlen_core = index.get(i);
            				flag = 0;
            			}
            			
            		}
            		else
            		{
            			break;
            		}
            		end = end+1;
            		begin = begin-1;
            	}
            }
        	if(maxlen_core==0)
        	{
        		maxlen_core = core;
        	}
        }
        int tmp_len;
        for(int i=0;i<s.length()-1;i++)
        {
        	if(s.substring(i, i+1).equals(s.substring(i+1, i+2)))
        	{
        		tmp_len = 2;
        		int begin = i;
            	int end = i+1;
            	if(flag == 2)
            	{
            		flag = 1;
            	}
            	if(length == 1)
            	{
            		length = 2;
            	}
            	core = i;
            	while(begin-1>=0&&end+1<s.length())
            	{
            		if(s.substring(begin-1,begin).equals(s.substring(end+1,end+2)))
            		{
            			if(length<end+1-begin+1+1)
            			{
            				length = end+1-begin+1+1;
            				flag = 1;
            				maxlen_core = i;
            			}
            		}
            		else
            		{
            			break;
            		}
            		end = end+1;
            		begin = begin-1;
            	}
        	}
        }
        if(maxlen_core == 0)
    	{
    		maxlen_core = core;
    	}
        String res;
        if(flag == 0)
        {
        	res = s.substring(maxlen_core-(length-1)/2,maxlen_core+(length-1)/2+1);
        }else if(flag==1)
        {
        	res = s.substring(maxlen_core-length/2+1,maxlen_core+length/2+1);
        }else
        {
        	res = s.substring(0,1);
        }
        return res;
    }
	
	
	public static String longestPalindrome1(String s)
	{
		//简便的书写方式
		int start = 0, end = 0;
	    for (int i = 0; i < s.length(); i++) {
	        int len1 = expandAroundCenter(s, i, i);
	        int len2 = expandAroundCenter(s, i, i + 1);
	        int len = Math.max(len1, len2);
	        if (len > end - start) {
	            start = i - (len - 1) / 2;
	            end = i + len / 2;
	        }
	    }
	    return s.substring(start, end + 1);
	}
	
	private static int expandAroundCenter(String s, int left, int right) {
	    int L = left, R = right;
	    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
	        L--;
	        R++;
	    }
	    return R - L - 1;
	}
	
	public static String longestPalindrome2(String s)
	{
		//Example: "xxxbcbxxxxxa", (x is random character, not all x are equal) now we 
        //are dealing with the last character 'a'. The current longest palindrome
        //is "bcb" with length 3.
			//1. check "xxxxa" so if it is palindrome we could get a new palindrome of length 5.
			//2. check "xxxa" so if it is palindrome we could get a new palindrome of length 4.
			//3. do NOT check "xxa" or any shorter string since the length of the new string is 
			//no bigger than current longest length.
//			4. do NOT check "xxxxxa" or any longer string because if "xxxxxa" is palindrome 
//				then "xxxx" got  from cutting off the head and tail is also palindrom. It has 
//				length > 3 which is impossible.'
		String res = "";
        int currLength = 0;
        for(int i=0;i<s.length();i++){
            if(isPalindrome(s,i-currLength-1,i)){
                res = s.substring(i-currLength-1,i+1);
                currLength = currLength+2;
            }
            else if(isPalindrome(s,i-currLength,i)){
                res = s.substring(i-currLength,i+1);
                currLength = currLength+1;
            }
        }
        return res;
	}
	
	public static boolean isPalindrome(String s, int begin, int end){
        if(begin<0) return false;
        while(begin<end){
        	if(s.charAt(begin++)!=s.charAt(end--)) return false;
        }
        return true;
    }
	
	
	public static void main(String[] args)
	{
		String test = "babadada";
		String res = longestPalindrome1(test);
		System.out.println(res);
	}
}
