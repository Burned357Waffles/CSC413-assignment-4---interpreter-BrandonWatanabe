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
      if (dumpState) handleDump(code);
      pc++;
      if (pc == program.byteCodeList.size()) isRunning = false;
    }
  }

  public void handleDump(ByteCode code){
    if(!code.getByteCode().equals("DUMP"))
    {
      System.out.printf("%-25s", code.getString());
      if (code.getByteCode().equals("LIT")){
        if(code.isId()) {
          String output =  "int " + code.getId();
          System.out.printf("%-26s", output);
        }
      }
      else if (code.getByteCode().equals("LOAD")){
        if(code.isId()) {
          String output =  "<load " + code.getId() + ">";
          System.out.printf("%-26s", output);
        }
      }
      else if (code.getByteCode().equals("STORE")){
        String output =  code.getId() + " = " + runTimeStack.peek();
        System.out.printf("%-26s", output);
      }
      else if (code.getByteCode().equals("RETURN")){
        if(code.isId()) {
          String output =  code.getId() + " = " + runTimeStack.peek();
          System.out.printf("%-26s", output);
        }
      }
      else if (code.getByteCode().equals("ARGS")){
        ByteCode callCode = program.getCode(pc + 1);
        String output =  callCode.getId() + "(" + code.getArgument() + "," + callCode.getNumber() + ")";
        System.out.printf("%-26s", output);
        }

      System.out.println();
      System.out.println(runTimeStack.dump());
      }
  }

  public void newFrameAt(int n) {runTimeStack.newFrameAt(n);}
  public void bop(String op) { runTimeStack.bop(op); }
  public void storeCurrentPC() {returnAddresses.add(pc);}
  public void setPC(int n) {pc = n;}
  public void setDumpState(boolean b) {this.dumpState = b;}
  public int popRunStack() { return runTimeStack.pop();}
  public void haltProgram() {isRunning = false;}
  public void push(int n) {runTimeStack.push(n);}
  public void load(int n) {runTimeStack.load(n);}
  public void popN(int n) {runTimeStack.popN(n);}
  public int getReturn() {return returnAddresses.pop();}
  public void popFrame() {runTimeStack.popFrame();}
  public void store(int n) {runTimeStack.store(n);}
  public void write() {runTimeStack.write();}
}