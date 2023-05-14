package test;

import java.io.*;
import java.util.Stack;

public class Calculator {
	// write your code here
        private static String[] op = { "+", "-", "*", "/" };// Operation set
        public static void main(String[] args) {
            String question = MakeFormula();
            System.out.println(question);
            String ret = Solve(question);
            System.out.println(ret);
            try {
                File file = new File("E:\\data\\1.txt");
                if(!file.exists()) {
                    file.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
                }
                FileOutputStream fos = new FileOutputStream(file,true);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(ret);
                bw.newLine();
                bw.flush();
                bw.close();
                osw.close();
                fos.close();
            }catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        public static String MakeFormula(){
            StringBuilder build = new StringBuilder();
            int count = (int) (Math.random() * 2) + 1; // generate random count
            int start = 0;
            int number1 = (int) (Math.random() * 99) + 1;
            build.append(number1);
            while (start <= count){
                int operation = (int) (Math.random() * 3); // generate operator
                int number2 = (int) (Math.random() * 99) + 1;
                build.append(op[operation]).append(number2);
                start ++;
            }
            return build.toString();
        }

        public static String Solve(String formula){
            Stack<String> tempStack = new Stack<>();//Store number or operator
            Stack<Character> operatorStack = new Stack<>();//Store operator
            int len = formula.length();
            int k = 0;
            for(int j = -1; j < len - 1; j++){
                char formulaChar = formula.charAt(j + 1);
                if(j == len - 2 || formulaChar == '+' || formulaChar == '-' || formulaChar == '/' || formulaChar == '*') {
                    if (j == len - 2) {
                        tempStack.push(formula.substring(k));
                    }
                    else {
                        if(k < j){
                            tempStack.push(formula.substring(k, j + 1));
                        }
                        if(operatorStack.empty()){
                            operatorStack.push(formulaChar); //if operatorStack is empty, store it
                        }else{
                            char stackChar = operatorStack.peek();
                            if ((stackChar == '+' || stackChar == '-')
                                    && (formulaChar == '*' || formulaChar == '/')){
                                operatorStack.push(formulaChar);
                            }else {
                                tempStack.push(operatorStack.pop().toString());
                                operatorStack.push(formulaChar);
                            }
                        }
                    }
                    k = j + 2;
                }
            }
            while (!operatorStack.empty()){ // Append remaining operators
                tempStack.push(operatorStack.pop().toString());
            }
            Stack<String> calcStack = new Stack<>();
            for(String peekChar : tempStack){ // Reverse traversing of stack
                if(!peekChar.equals("+") && !peekChar.equals("-") && !peekChar.equals("/") && !peekChar.equals("*")) {
                    calcStack.push(peekChar); // Push number to stack
                }else{
                    int a1 = 0;
                    int b1 = 0;
                    if(!calcStack.empty()){
                        b1 = Integer.parseInt(calcStack.pop());
                    }
                    if(!calcStack.empty()){
                        a1 = Integer.parseInt(calcStack.pop());
                    }
                    switch (peekChar) {
                        case "+":
                            calcStack.push(String.valueOf(a1 + b1));
                            break;
                        case "-":
                            calcStack.push(String.valueOf(a1 - b1));
                            break;
                        case "*":
                            calcStack.push(String.valueOf(a1 * b1));
                            break;
                        default:
                            calcStack.push(String.valueOf(a1 / b1));
                            break;
                    }
                }
            }
            return formula + "=" + calcStack.pop();
        }
    }
