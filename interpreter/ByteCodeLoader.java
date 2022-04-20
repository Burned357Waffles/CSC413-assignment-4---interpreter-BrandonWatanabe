package interpreter;

import interpreter.bytecode.ByteCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ByteCodeLoader {
  private String byteCodeFile;

  public ByteCodeLoader(String byteCodeFile) throws IOException
  {
    this.byteCodeFile = byteCodeFile;
  }

  public Program loadCodes() throws ClassNotFoundException, InstantiationException, IllegalAccessException
  {
    Program program = new Program();
    try
    {
      File codeFile = new File(byteCodeFile);
      Scanner inputFile =  new Scanner(codeFile);

      while (inputFile.hasNext())
      {
        String nextLine = inputFile.nextLine();
        String[] readLine = nextLine.split(" ");
        String code_class = CodeTable.get(readLine[0]);
        ByteCode bytecode = (ByteCode) (Class.forName("interpreter.bytecode." + code_class).newInstance());
        program.addCode(bytecode);
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File" + byteCodeFile + "not found");
    }
    return program;
  }
}