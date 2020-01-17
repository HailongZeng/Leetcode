package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/13/20 8:03 下午
 */

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 *
 * Example:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 */
public class No27_Min_Stack {
    public static class MinStack {

        Stack<Integer> numStack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();
        /** initialize your data structure here. */
        public MinStack() {

        }

        public void push(int x) {
            numStack.push(x);
            if (minStack.isEmpty()){
                minStack.push(x);
            }else{
                minStack.push(Math.min(x, minStack.peek()));
            }
        }

        public void pop() {
            if (numStack.isEmpty()) throw new RuntimeException("Your stack is empty.");
            numStack.pop();
            minStack.pop();
        }

        public int top() {
            if (numStack.isEmpty()) throw new RuntimeException("Your stack is empty.");
            return numStack.peek();
        }

        public int getMin() {
            if (minStack.isEmpty()) throw new RuntimeException("Your stack is empty.");
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); //--> Returns -3
        minStack.pop();
        System.out.println(minStack.top());      //--> Returns 0.
        System.out.println(minStack.getMin());   //--> Returns -2.
    }
}
