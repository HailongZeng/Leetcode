package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/16/20 12:38 下午
 */

import java.util.Stack;

/**
 * Design a max stack that supports push, pop, top, peekMax and popMax.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Remove the element on top of the stack and return it.
 * top() -- Get the element on the top.
 * peekMax() -- Retrieve the maximum element in the stack.
 * popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
 * Example 1:
 *
 * MaxStack stack = new MaxStack();
 * stack.push(5);
 * stack.push(1);
 * stack.push(5);
 * stack.top(); -> 5
 * stack.popMax(); -> 5
 * stack.top(); -> 1
 * stack.peekMax(); -> 5
 * stack.pop(); -> 1
 * stack.top(); -> 5
 * Note:
 *
 * -1e7 <= x <= 1e7
 * Number of operations won't exceed 10000.
 * The last four operations won't be called when stack is empty.
 */
//leetcode716
public class No57_Max_Stack {

    public static class MaxStack{

        Stack<Integer> numStack = new Stack<>();//store the element pushed
        Stack<Integer> maxStack = new Stack<>();//store the maxElement in the stack
        public MaxStack(){}

        public void push(int x){
            numStack.push(x);
            if (maxStack.isEmpty()){
                maxStack.push(x);
            }else{
                if (maxStack.peek() > x){
                    maxStack.push(maxStack.peek());
                }else{
                    maxStack.push(x);
                }
            }
        }

        public int pop(){
            maxStack.pop();
            return numStack.pop();
        }

        public int top(){
            return numStack.pop();
        }

        public int peekMax(){
            return maxStack.peek();
        }

        public int popMax(){
            int max = maxStack.peek();
            Stack<Integer> buffer = new Stack<>();
            while (top() != max) buffer.push(pop());
            pop();
            while (!buffer.isEmpty()) push(buffer.pop());
            return max;
        }
    }
}
