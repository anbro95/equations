package org.example;

import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;


public class Solver {

    public static void main(String[] args) throws ScriptException {
        String str = "(((()))";
        System.out.println(brackets(str));

        String equation = "5*2+10*x=20";
        System.out.println(checkRoot(equation, 2));
    }

    // FIXME
    private static boolean brackets(String input) {
        Deque<Character> deq = new ArrayDeque<>();
        char[] arr = input.toCharArray();
        if (arr[0] == ')' || arr[arr.length-1] == '(')
            return false;


        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];

            switch (ch) {
                case '(':
                    deq.push(ch);
                    break;
                case ')':
                try {
                    if ('(' != deq.pop()) {
                        return false;
                    }
                } catch (NoSuchElementException e) {
                    return false;
                }
                    break;
                default:
                    break;
            }

        }
        return true;
    }


    private static boolean checkRoot(String equation, int root) throws ScriptException {
        String left = equation.split("=")[0];
        String right = equation.split("=")[1];

        ScriptEngine nashornEngine = new NashornScriptEngineFactory().getScriptEngine();
        left = left.replace("x", String.valueOf(root));
        right = right.replace("x", String.valueOf(root));

        Object leftResult = nashornEngine.eval(left);
        Object rightResult = nashornEngine.eval(right);

        return leftResult.equals(rightResult);

    }

}
