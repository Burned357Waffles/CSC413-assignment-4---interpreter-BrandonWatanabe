package interpreter;

import java.util.Stack;
import java.util.Vector;

public class RunTimeStack
{

  private Stack<Integer> framePointers;
  private Vector<Integer> runStack;

  public RunTimeStack()
  {
    framePointers = new Stack<>();
    runStack = new Vector<>();
    framePointers.push(0);
  }

  /**
   * The purpose of this function is to dump the RunTimeStack for the
   * purpose of debugging.
   */
  public void dump()
  {

  }

  /**
   * Returns the top item on the runtime stack.
   */
  public int peek()
  {
    return runStack.lastElement();
  }

  /**
   * Pops the top item from the runtime stack, returning the item.
   */
  public int pop()
  {
    int valuePopped = runStack.lastElement();
    runStack.remove(runStack.size() - 1);
    return valuePopped;
  }

  public void popN(int n){
    for (int i = 0; i < n; i++){
      this.pop();
    }
  }
  /**
   * Push an item on to the runtime stack, returning the item that was just
   * pushed.
   */
  public int push(int item)
  {
    runStack.add(item);
    return item;
  }

  /**
   * Start a new frame, where the parameter offset is the number of slots
   * down from the top of the RunTimeStack for starting the new frame.
   */
  public void newFrameAt(int offset)
  {
    framePointers.push(runStack.size() - offset);
  }

  /**
   * We pop the top frame when we return from a function; before popping, the
   * functions’ return value is at the top of the stack so we’ll save the value,
   * pop the top frame, and then push the return value.
   */
  public void popFrame()
  {
    int top = this.pop();
    int firstValue = framePointers.pop();
    for (int i = firstValue; i < runStack.size(); i++)
    {
      runStack.remove(firstValue);
    }
    this.push(top);
  }

  /**
   * Used to store into variables.
   */
  public void store(int offset)
  {
    int top = pop();
    int val = framePointers.peek() + offset;
    runStack.add(val, top);
    runStack.removeElementAt(framePointers.peek() + offset + 1);
  }

  /**
   * Used to load variables onto the stack.
   */
  public void load(int offset)
  {
    if (framePointers.isEmpty())
    {
      runStack.add(offset, runStack.get(offset));
    } else
    {
      offset += framePointers.lastElement();
      runStack.add(runStack.get(offset));

    }
  }

  public void bop(String op)
  {
    int secondValue = pop();
    int firstValue = pop();
    switch (op)
    {
      case "+":
        push(firstValue + secondValue);
        break;
      case "-":
        push(firstValue - secondValue);
        break;
      case "*":
        push(firstValue * secondValue);
        break;
      case "/":
        push(firstValue / secondValue);
        break;
      case "==":
        if (firstValue == secondValue) push(1);
        else push(0);
        break;
      case "!=":
        if (firstValue == secondValue) push(0);
        else push(1);
        break;
      case "<":
        if (firstValue < secondValue) push(1);
        else push(0);
        break;
      case ">":
        if (firstValue > secondValue) push(1);
        else push(0);
        break;
      case "<=":
        if (firstValue <= secondValue) push(1);
        else push(0);
        break;
      case ">=":
        if (firstValue >= secondValue) push(1);
        else push(0);
        break;
      case "|":
        if (firstValue == 0 && secondValue == 0) push(0);
        else push(1);
        break;
      case "&":
        if (firstValue == 1 && secondValue == 1) push(1);
        else push(0);
        break;
    }
  }

  public void write(){
    System.out.println(this.peek());
  }
}