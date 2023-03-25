package application;

import java.util.Stack;

class LearnCoding{
    static boolean checkIfOperand(char ch)
    {
        return Character.isLetterOrDigit(ch);
    }

    // Function to compare precedence
    // If we return larger value means higher precedence
    static int precedence(char ch)
    {
        switch (ch)
        {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }


    static void covertInfixToPostfix(String expr)
    {
        int i;
        Stack <Character> s = new Stack<>();
        StringBuilder result = new StringBuilder(new String(""));

        for (i = 0; i < expr.length(); ++i)
        {
            // Here we are checking is the character we scanned is operand or not
            // and this adding to output.
            if (checkIfOperand(expr.charAt(i)))
                result.append(expr.charAt(i));

                // Here, if we scan the character ‘(‘, '[', '{' we need to push it to the stack.
            else if (expr.charAt(i) == '(' || expr.charAt(i) == '[' || expr.charAt(i) == '{')
                s.push(expr.charAt(i));

                // Here, if we scan character is an ‘)’, we need to pop and print from the stack
                // do this until an ‘(‘ is encountered in the stack.
            else if (expr.charAt(i) == ')' || expr.charAt(i) == ']' || expr.charAt(i) == '}')
            {
                if(expr.charAt(i) == ')'){
                    while (!s.empty() && s.peek() != '('){
                        result.append(s.peek());
                        s.pop();
                    }

                    s.pop();
                }

                if(expr.charAt(i) == ']'){
                    while (!s.isEmpty() && s.peek() != '['){
                        result.append(s.peek());
                        s.pop();
                    }

                    s.pop();
                }
                if(expr.charAt(i) == '}'){
                    while (!s.isEmpty() && s.peek() != '{'){
                        result.append(s.peek());
                        s.pop();
                    }

                    s.pop();
                }
            }
            else // if an operator
            {
                while (!s.isEmpty() && precedence(expr.charAt(i)) <= precedence(s.peek())){
                    result.append(s.peek());
                    s.pop();
                }
                s.push(expr.charAt(i));
            }

        }

        // Once all initial expression characters are traversed
        // adding all left elements from stack to exp
        while (!s.isEmpty()){
            result.append(s.peek());
            s.pop();
        }
        System.out.println(result);

    }
}
