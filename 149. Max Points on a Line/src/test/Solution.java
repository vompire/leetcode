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
		//б�����⣬�е�б���޷���ʾ
		//������ͨ��������Ϊkey�����ܺܺõ�ȷ���Ƿ�����ͬһ�ࣨ������ĸ�д�͸��£�
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
        ����ÿ���㣬�����ͺ����ÿ���㹹�ɵ�ֱ�����ж��ٸ���
        ��ÿ���㽨��map��б����key
        б��Ҫ�÷�������ʽ����Ҫ��double����ʽ��
        �������ʱ������ӷ�ĸ�����Լ��gcd���ٶ�����gcd
        �غϵĵ����⴦��
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
                // ��string���洢б��
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
        //�ؼ������ж����㹲�ߣ���ƽ��ֱ������ֻ��һ�����㣬������һ���м�㣬����м�������������˵�����ߵ�б�����
        //�ɱ��ʵ�����
    	//��㹲����������㹲��
        int ABx;
        int ABy;
        int BCx;
        int BCy;
         
        if(points.length<=2) return points.length;
        int max=2;//������¼������
         
        for(int i=0;i<points.length;i++){
            int num=0;
            int temp=1;
     
            for(int j=i+1;j<points.length;j++){
                ABx=points[i].x-points[j].x;
                ABy=points[i].y-points[j].y;
                if(ABx==0 && ABy==0)//��ʾ�����ظ���
                {
                    num++;
                }else{
                    temp++;
                    for(int k=j+1;k<points.length;k++){
                        BCx=points[j].x-points[k].x;
                        BCy=points[j].y-points[k].y;
                        if(ABx*BCy==BCx*ABy){//��ʾ����б����ȣ�ת��Ϊ�˻�����ʽ���Ա����ĸΪ0�����
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
