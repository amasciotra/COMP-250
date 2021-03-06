package assignment2;

/*
Alex Masciotra
260746829
 */

import java.util.Stack;
import java.util.ArrayList;

public class Expression  {
	private ArrayList<String> tokenList;

	//  Constructor    
	/**
	 * The constructor takes in an expression as a string
	 * and tokenizes it (breaks it up into meaningful units)
	 * These tokens are then stored in an array list 'tokenList'.
	 */

	Expression(String expressionString) throws IllegalArgumentException{
		tokenList = new ArrayList<String>();
		StringBuilder token = new StringBuilder();

		//ADD YOUR CODE BELOW HERE
		int i; //i will go through each element
		for (i = 0 ; i < expressionString.length(); i++){
			


			if (Character.isDigit(expressionString.charAt(i)) == true){

				token.append(expressionString.charAt(i));


				while (Character.isDigit(expressionString.charAt(i+1)) == true ){
					token.append(expressionString.charAt(i+1));
					i++;
				}
				
				tokenList.add(token.toString());
				token.delete(0, token.length());
				

			}


			else if (expressionString.charAt(i) == ' '){
				continue;

			}



			else if (expressionString.charAt(i) == '*' || expressionString.charAt(i) == '/' || 
					expressionString.charAt(i) == '(' || expressionString.charAt(i) == ')' || 
					expressionString.charAt(i) == '[' || expressionString.charAt(i) == ']'){
				token.append(expressionString.charAt(i));
				tokenList.add(token.toString());
				token.delete(0, token.length());

			}


			else if (expressionString.charAt(i) == '+' || expressionString.charAt(i) == '-'){
				token.append(expressionString.charAt(i));
				if (expressionString.charAt(i+1) == expressionString.charAt(i)){
					token.append(expressionString.charAt(i+1));	
					i++;
				}
				tokenList.add(token.toString());
				token.delete(0, token.length());
				
				

			}	
			


		}
		//ADD YOUR CODE ABOVE HERE
	}
	

	/**
	 * This method evaluates the expression and returns the value of the expression
	 * Evaluation is done using 2 stack ADTs, operatorStack to store operators
	 * and valueStack to store values and intermediate results.
	 * - You must fill in code to evaluate an expression using 2 stacks
	 */
	public Integer eval(){
		Stack<String> operatorStack = new Stack<String>();
		Stack<Integer> valueStack = new Stack<Integer>();
		
		//ADD YOUR CODE BELOW HERE
		//..
		

		int i;

		for (i = 0 ; i < tokenList.size(); i++){ 

			if (isInteger(tokenList.get(i)) == true){

				valueStack.push(Integer.parseInt(tokenList.get(i)));			

			}
			//attempt number 2 at this eval	
			else if (tokenList.get(i).equals("+") || tokenList.get(i).equals("++") ||
					tokenList.get(i).equals("-") || tokenList.get(i).equals("--") || tokenList.get(i).equals("*") ||
					tokenList.get(i).equals("/")){// || tokenList.get(i).equals("]")){

				operatorStack.push(tokenList.get(i));

			}

			else if (tokenList.get(i).equals("(") || tokenList.get(i).equals("[")){
				continue;
			}
			else if(tokenList.get(i).equals(")")){
				if(!operatorStack.isEmpty()){

					compute(valueStack,operatorStack);
				}
			}
			else if (tokenList.get(i).equals("]")){

				Integer num2 = valueStack.pop();
				if (num2 < 0){
					num2 = -num2;				

				}

				valueStack.push(num2);



			}	

		}// close of for loop



		//..
		//ADD YOUR CODE ABOVE HERE

		return valueStack.pop();
	}

	//Helper methods

	// adding compute helper method

	private static void compute ( Stack<Integer> valueStack, Stack<String> operatorStack ){


		String operator = operatorStack.pop();// pops operator on peek of operator stack
		Integer result = 0; // initialize result

		if ("+".equals(operator)){
			Integer num2 = valueStack.pop(); //number on peek of stack
			Integer num1 = valueStack.pop(); //number under peek
			result = num1 + num2;
		}else if ("-".equals(operator)){	
			Integer num2 = valueStack.pop(); //number on peek of stack
			Integer num1 = valueStack.pop(); //number under peek
			result = num1 - num2;
		}else if ("++".equals(operator)){
			Integer num2 = valueStack.pop(); //number on peek of stack
			result = ++num2;
		}else if ("--".equals(operator)){
			Integer num2 = valueStack.pop(); //number on peek of stack
			result = --num2;
		}else if ("*".equals(operator)){
			Integer num2 = valueStack.pop(); //number on peek of stack
			Integer num1 = valueStack.pop(); //number under peek
			result = num1 * num2;			
		}else if ("/".equals(operator)){
			Integer num2 = valueStack.pop(); //number on peek of stack
			Integer num1 = valueStack.pop(); //number under peek
			result = num1 / num2;
		}


		valueStack.push(result);
	}


	
	/**
	 * Helper method to test if a string is an integer
	 * Returns true for strings of integers like "456"
	 * and false for string of non-integers like "+"
	 * - DO NOT EDIT THIS METHOD
	 */
	private boolean isInteger(String element){
		try{
			Integer.valueOf(element);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}

	/**
	 * Method to help print out the expression stored as a list in tokenList.
	 * - DO NOT EDIT THIS METHOD    
	 */

	@Override
	public String toString(){	
		String s = new String(); 
		for (String t : tokenList )
			s = s + "~"+  t;
		return s;		
	}

}

