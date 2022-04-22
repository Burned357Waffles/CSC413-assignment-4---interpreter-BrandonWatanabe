package interpreter;

import interpreter.bytecode.ByteCode;
import java.util.ArrayList;
import java.util.HashMap;

public class Program {
  public ArrayList<ByteCode> byteCodeList = new ArrayList<>();

  public void addCodes(HashMap<Integer,ByteCode> inputMap, ArrayList<String[]> argsList)
  {
      int saveLineNum = 0;
      String check = "";
      for (Integer key : inputMap.keySet()){
        byteCodeList.add(inputMap.get(key));
        if (argsList.get(key).length > 1) check = argsList.get(key)[1];
        if (check.contains("<<")) {
            if(!inputMap.get(key).getClass().getName().equals("interpreter.bytecode.LabelCode")) saveLineNum = key;
        }
        if(inputMap.get(key).getClass().getName().equals("interpreter.bytecode.LabelCode"))
        {
            inputMap.get(key).setTarget(key);
            if (inputMap.get(saveLineNum).getArgument().equals(check))
                inputMap.get(saveLineNum).setTarget(key);
        }
      inputMap.get(key).init(argsList.get(key));
    }
  }

  public ByteCode getCode(int programCounter)
  {
    if (programCounter < byteCodeList.size()) return byteCodeList.get(programCounter);
    return null;
  }
}