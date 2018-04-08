package test;

public class Solution {
	//try会减缓运行速度，且try包含的操作越多，减缓的越严重
	//另一个考察点就是Int的取值范围问题
	public static int reverse(int x) {
		int res = 0;
		if(x==0)
		{
			return x;
		}
        int flag = Math.abs(x)/x;
        x = Math.abs(x);
        char[] tmp = String.valueOf(x).toCharArray();
        StringBuffer sb = new StringBuffer();
        for(int i=tmp.length-1;i>=0;i--)
        {
        	sb.append(tmp[i]);
        }
        try{
        	res = Integer.valueOf(sb.toString())*flag;
        }catch(Exception e)
        {
        	res = 0;
        }
        
        return res;
    }
	
	public int reverse2(int x)
	{
	    int result = 0;

	    while (x != 0)
	    {
	        int tail = x % 10;
	        int newResult = result * 10 + tail;
	        if ((newResult - tail) / 10 != result)
	        { return 0; }
	        result = newResult;
	        x = x / 10;
	    }

	    return result;
	}
	
	public static void main(String[] args)
	{
		int a = 1534236469;
		System.out.println(reverse(a));
	}
}
