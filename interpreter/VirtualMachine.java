/**
 * DO NOT provide a method that returns components contained WITHIN the VM (this 
 * is the exact situation that will break encapsulation) - you should request 
 * that the VM performs operations on its components. This implies that the VM 
 * owns the components and is free to change them, as needed, without breaking 
 * clients' code (e.g., suppose I decide to change the name of the variable that 
 * holds my runtime stack - if your code had referenced that variable then your 
 * code would break. This is not an unusual situation - you can consider the 
 * names of methods in the Java libraries that have been deprecated).
 * 
 * Consider that the VM calls the individual ByteCodes' execute method and 
 * passes itself as a parameter. For the ByteCode to execute, it must invoke 
 * one or more methods in the runStack. It can do this by executing 
 * VM.runStack.pop(); however, this does break encapsulation. To avoid this, 
 * you'll need to have a corresponding set of methods within the VM that do 
 * nothing more than pass the call to the runStack. e.g., you would want to 
 * define a VM method:
 *     public int popRunStack() {
 *       return runStack.pop();
 *     }
 * called by, e.g.,
 *     int temp = VM.popRunStack();
 */
package interpreter;

import java.util.Stack;
import interpreter.bytecode.ByteCode;

public class VirtualMachine {

  private int pc;
  private RunTimeStack runTimeStack;
  private Stack<Integer> returnAddresses;
  private boolean isRunning;
  private Program program;
  public boolean dumpState = false;

  public VirtualMachine(Program program) {
    this.program = program;
  }

  public void executeProgram() {
    pc = 0;
    runTimeStack = new RunTimeStack();
    returnAddresses = new Stack<>();
    returnAddresses.push(0);
    isRunning = true;

    while (isRunning) {
      ByteCode code = program.getCode(pc);
      code.execute(this);
      // runStack.dump(); // check that the operation is correct
      pc++;
      if (pc == program.byteCodeList.size()) isRunning = false;
    }
  }
  public void newFrameAt(int n) {runTimeStack.newFrameAt(n);}
  public void bop(String op) { runTimeStack.bop(op); }
  public int getReturn() {return returnAddresses.pop();}
  public void haltProgram() {isRunning = false;}
  public void load(int n) {runTimeStack.load(n);}
  public void store(int n) {runTimeStack.store(n);}
  public void popN(int n) {runTimeStack.popN(n);}
  public int popRunStack() { return runTimeStack.pop();}
  public void popFrame() {runTimeStack.popFrame();}
  public void push(int n) {runTimeStack.push(n);}
  public void storeCurrentPC() {returnAddresses.add(pc);}
  public void setDumpState(boolean b) {this.dumpState = b;}
  public void setPC(int n) {pc = n;}
  public void write() {runTimeStack.write();}

}