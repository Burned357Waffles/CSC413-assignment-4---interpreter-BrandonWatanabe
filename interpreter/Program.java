package interpreter;

import interpreter.bytecode.ByteCode;
import java.util.ArrayList;

/*
1. store byte codes in the order they appear (arraylist)
2. resolve any symbolic addresses
 */

public class Program {
  public static ArrayList<ByteCode> byteCodeList = new ArrayList<ByteCode>();

  public void addCode(ByteCode byteCode)
  {
    byteCodeList.add(byteCode);
  }

  public ByteCode getCode(int programCounter)
  {
    if (programCounter < byteCodeList.size()) return byteCodeList.get(programCounter);
    return null;
  }
}