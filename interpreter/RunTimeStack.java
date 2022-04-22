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

  public String dump()
  {
    Vector<Integer>[] dumpVector = new Vector[framePointers.size()];
    Vector<Integer> runStackCopy = new Vector<>(runStack);
    String dumpString = "";

    for( int i = framePointers.size()-1; i >= 0 ; i-- ){
      dumpVector[i] = new Vector<>();
      int startIndex = framePointers.get(i);
      int stackSize = runStackCopy.size();
      for (int j=startIndex; j < stackSize; j++) {
        dumpVector[i].add(runStackCopy.remove(startIndex));
      }
    }
    for ( int i=0; i<framePointers.size(); i++){
      dumpString = dumpString + dumpVector[i].toString();
      System.out.print(dumpVector[i].toString());
    }

    return dumpString;
  }

  public int peek()
  {
    return runStack.lastElement();
  }

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

  public int push(int item)
  {
    runStack.add(item);
    return item;
  }

  public void newFrameAt(int offset)
  {
    framePointers.push(runStack.size() - offset);
  }

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

  public void store(int offset)
  {
    int top = pop();
    int val = framePointers.peek() + offset;
    runStack.add(val, top);
    runStack.removeElementAt(framePointers.peek() + offset + 1);
  }

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