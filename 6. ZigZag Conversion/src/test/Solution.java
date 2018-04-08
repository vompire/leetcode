package test;

import java.util.ArrayList;
import java.util.List;

/**
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/

public class Solution {
	public static String convert(String s, int numRows) {
		String res = "";
		if(numRows == 1)
		{
			return s;
		}
        for(int i=0;i<numRows;i++)
        {
        	if((i==0)||(i==numRows-1))
        	{
        		int tmpValue = 0;
        		while(i+tmpValue<s.length())
            	{
        			res = res+s.charAt(i+tmpValue);
            		if(i+tmpValue+numRows*2-2<s.length())
            		{
            			tmpValue = tmpValue+numRows*2-2;
            		}else
            		{
            			break;
            		}
            	}
        	}else
        	{
        		int tmpValue = 0;
        		if(i+tmpValue<s.length())
        		{
            		res = res+s.charAt(i+tmpValue);
            		while(true)
            		{
            			if(i+tmpValue+numRows*2-2<s.length())
            			{
            				res = res+s.charAt(i+tmpValue+(numRows-i)*2-2);
            				res = res+s.charAt(i+tmpValue+(numRows)*2-2);
            				tmpValue = tmpValue+numRows*2-2;
            			}else if(i+tmpValue+(numRows-i)*2-2<s.length())
            			{
            				res = res+s.charAt(i+tmpValue+(numRows-i)*2-2);
            				break;
            			}else
            			{
            				break;
            			}
            		}
        		}
        	}
        }
        return res;
    }
	
	public static String convert2(String s, int numRows)
	{
	    char[] c = s.toCharArray();
	    int len = c.length;
	    StringBuffer[] sb = new StringBuffer[numRows];
	    for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();
	    
	    int i = 0;
	    while (i < len) {
	        for (int idx = 0; idx < numRows && i < len; idx++) // vertically down
	            sb[idx].append(c[i++]);
	        for (int idx = numRows-2; idx >= 1 && i < len; idx--) // obliquely up
	            sb[idx].append(c[i++]);
	    }
	    for (int idx = 1; idx < sb.length; idx++)
	        sb[0].append(sb[idx]);
	    return sb[0].toString();
	}
	
	public static void main(String[] args)
	{
		String res = convert2("Gee", 2);
		System.out.println(res);
	}
}

