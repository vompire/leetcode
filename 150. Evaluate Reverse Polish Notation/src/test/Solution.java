package test;

import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Pattern;

//Evaluate the value of an arithmetic expression in Reverse Polish Notation.
//
//Valid operators are +, -, *, /. Each operand may be an integer or another expression.
//
//Some examples:
//  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
//  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

public class Solution {
    public static int evalRPN(String[] tokens) {
    	LinkedList<String> list = new LinkedList<String>();
    	for(int i=0;i<tokens.length;i++)
    	{
    		if(!check(tokens[i]))
    		{
    			int a = Integer.valueOf(list.removeLast());//removeLast�õ�����һ��Ԫ�ز���LinkedList���Ƴ�
    			int b = Integer.valueOf(list.removeLast());
    			list.add(String.valueOf(eval(tokens[i],b,a)));
    		}else
    		{
    			list.add(tokens[i]);
    		}
    	}
    	return Integer.valueOf(list.getFirst());
    }
    
    public static int eval(String op, int val1, int val2)
    {
    	if (op.equals("+")) return val1 + val2;  
        if (op.equals("-")) return val1 - val2;  
        if (op.equals("/")) return val1 / val2;  
        if (op.equals("*")) return val1 * val2; 
        return 0;
    }
    
    public static boolean check(String s)
    {
    	if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) return false;  
        return true; 
    }
    
    public static int evalRPN2(String[] tokens) {
    	//Stackʹ��
        Stack<Integer> intStack = new Stack<Integer>();
        for(int i = 0; i < tokens.length; i++){
            if(tokens[i].equals("+") || tokens[i].equals("-") ||
               tokens[i].equals("*") || tokens[i].equals("/")){
                int b = intStack.pop();//�Ƴ�ջ�����󣨼��������Ԫ�أ������õ�
                int a = intStack.pop();
                intStack.push(eval(tokens[i], a, b));
            }
            else{
                intStack.push(Integer.parseInt(tokens[i]));//��Ԫ��ѹ��ջ��
            }
        }
        return intStack.pop();
    }
    
    public static void main(String[] args)
    {
    	String[] test = {"4", "-13", "5", "/", "+"};
    	System.out.println(evalRPN(test));
    }
}
