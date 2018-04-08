package test;

import java.util.HashMap;
import java.util.Map;

import com.sun.javafx.scene.paint.GradientUtils.Point;

//Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
	public static int maxPoints(Point[] points) {
		//斜率问题，有的斜率无法表示
		//由于是通过类来作为key，不能很好的确定是否属于同一类（存在类的复写和更新）
		if(points == null) return 0;
		if(points.length<=2) return points.length;
		Map<line,Integer> m = new HashMap<>();
		int res = 0;
		line tmp = new line(0.0,0.0);
        for(int i=0;i<points.length;i++)
        {
        	int coincide = 0;
        	int max = 0;
        	for(int j=i+1;j<points.length;j++)
        	{
        		if((points[i].x == points[j].x)&&(points[i].y == points[j].y))
        		{
        			coincide++;
        		}else if(points[i].x == points[j].x)
        		{
        			tmp.slope = Double.MAX_VALUE;
        			tmp.intercept = points[i].x*1.0;
        			m.put(tmp, m.getOrDefault(tmp, 0) + 1);
        		}else if(points[i].y == points[j].y)
        		{
        			tmp.slope = 0.0;
        			tmp.intercept = points[i].y*1.0;
        			m.put(tmp, m.getOrDefault(tmp, 0) + 1);
        		}else
        		{
        			tmp = getline(points[i],points[j],tmp);
        			System.out.println(m.getOrDefault(tmp, 0));
        			m.put(tmp, m.getOrDefault(tmp, 0) + 1);
        		}
        		max = Math.max(max, m.get(tmp).intValue());
        	}
        	res = Math.max(res, max+coincide);
        }
        return res;
    }
	
	 static class Point {
		     int x;
		     int y;
		     Point() { x = 0; y = 0; }
		     Point(int a, int b) { x = a; y = b; }
		 }
	
	static class line
	{
		double slope;
		double intercept;
		line() {slope=0;intercept=0;}
		line(double a, double b) { slope = a; slope = b; }
	}
	
	public static line getline(Point a,Point b,line test)
	{
		double c = (a.y-b.y)*1.0/(a.x-b.x);
		double d = a.y*1.0-test.slope*a.x;
		if((c==test.slope)&&(d==test.intercept))
		{
			return test;
		}else
		{
			line res = new line(c,d);
			res.slope = c;
			res.intercept = d;
			return res;
		}
		
	}
	
	public int maxPoints2(Point[] points) {
        /*
        遍历每个点，看它和后面的每个点构成的直线上有多少个点
        对每个点建立map，斜率是key
        斜率要用分数的形式，不要用double的形式存
        计算分数时先求分子分母的最大公约数gcd，再都除以gcd
        重合的点特殊处理
        */
        int l = points.length;
        if (l == 0) return 0;
        if (l <= 2) return l;
        int res = 0;
        for (int i = 0; i < l - 1; i++) {
            Map<String, Integer> map = new HashMap<>();
            int overlap = 0;
            int lineMax = 0;
            for (int j = i + 1; j < l; j++) {
                int x = points[i].x - points[j].x;
                int y = points[i].y - points[j].y;
                if (x == 0 && y == 0) {
                    overlap++;
                    continue;
                }
                int gcd = generateGcd(x, y);
                x /= gcd;
                y /= gcd;
                // 用string来存储斜率
                String slope = String.valueOf(x) + String.valueOf(y);
                int count = map.getOrDefault(slope, 0);
                count++;
                map.put(slope, count);
                lineMax = Math.max(lineMax, count);
            }
            res = Math.max(res, lineMax + overlap + 1);
        }
        return res;
    }
    
    public int generateGcd(int x, int y) {
        if (y == 0) return x;
        return generateGcd(y, x % y);
    }
    
    public int maxPoints3(Point[] points) {
        //关键在于判断三点共线，两平行直线有且只有一个交点，所以有一个中间点，这个中间点与另外两个端点的连线的斜率相等
        //由比率的性质
    	//多点共线类比于三点共线
        int ABx;
        int ABy;
        int BCx;
        int BCy;
         
        if(points.length<=2) return points.length;
        int max=2;//用来记录最大个数
         
        for(int i=0;i<points.length;i++){
            int num=0;
            int temp=1;
     
            for(int j=i+1;j<points.length;j++){
                ABx=points[i].x-points[j].x;
                ABy=points[i].y-points[j].y;
                if(ABx==0 && ABy==0)//表示出现重复点
                {
                    num++;
                }else{
                    temp++;
                    for(int k=j+1;k<points.length;k++){
                        BCx=points[j].x-points[k].x;
                        BCy=points[j].y-points[k].y;
                        if(ABx*BCy==BCx*ABy){//表示两个斜率相等，转化为乘积的形式可以避免分母为0的情况
                            temp++;
                        }
                    }
                }
                if(max<(num+temp)){
                  max=num+temp;
                }
                temp=1;
            }
                             
        }
        return max;
    }
	
	public static void main(String[] args)
	{
		Point[] test = new Point[3];
		test[0] = new Point(0,0);
		test[1] = new Point(1,1);
		test[2] = new Point(0,0);
		System.out.println(maxPoints(test));
//		System.out.println(getline(test[0],test[1]).slope+" "+getline(test[0],test[1]).intercept);
//		System.out.println(getline(test[0],test[2]).slope+" "+getline(test[0],test[2]).intercept);
//		System.out.println(getline(test[2],test[1]).slope+" "+getline(test[2],test[1]).intercept);
	}
}
