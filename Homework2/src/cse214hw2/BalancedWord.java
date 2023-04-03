package cse214hw2;

import java.util.Stack;

public class BalancedWord {
    private final String word;

    public BalancedWord(String word) {
        if (isBalanced(word))
            this.word = word;
        else
            throw new IllegalArgumentException(String.format("%s is not a balanced word.", word));
    }

    private static boolean isBalanced(String word) {
        int total = 0;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < word.length(); i++) {
            stack.add(word.charAt(i));
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            while (!stack.isEmpty()) {
                Character a = stack.pop();
                if (a == ('}') || a == (')') || a == (']')) {
                    total++;
                    if (stack.isEmpty()) {
                        break;
                    }
                } else if (a == ('{') || a == ('(') || a == ('[')) {
                    total--;
                    if (stack.isEmpty()) {
                        break;
                    }
                }
            }

            return total == 0;
        }

    }

    public String getWord() {
        return word;
    }
}