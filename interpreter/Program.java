package interpreter;

import interpreter.bytecode.ByteCode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
/*
1. store byte codes in the order they appear (arraylist) X
2. resolve any symbolic addresses
 */

public class Program {
  public ArrayList<ByteCode> byteCodeList;

  public void addCodes(HashMap<ByteCode,String[]> inputMap)
  {
    int line_num = 0;
      for (ByteCode key : inputMap.keySet()){
        line_num++;
      if(key.getClass().getName().equals("interpreter.bytecode.LabelCode")) key.setTarget(line_num);
      key.init(inputMap.get(key));
    }


  }

  public ByteCode getCode(int programCounter)
  {
    if (programCounter < byteCodeList.size()) return byteCodeList.get(programCounter);
    return null;
  }
}