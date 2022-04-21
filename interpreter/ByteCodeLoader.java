package interpreter;

import interpreter.bytecode.ByteCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.HashMap;

public class ByteCodeLoader {
  private String byteCodeFile;
  public static HashMap<ByteCode, String[]> codeHashMap = new HashMap<>();

  public ByteCodeLoader(String byteCodeFile) throws IOException
  {
    this.byteCodeFile = byteCodeFile;
  }

  public Program loadCodes() throws ClassNotFoundException, InstantiationException, IllegalAccessException
  {
    try
    {
      File codeFile = new File(byteCodeFile);
      Scanner inputFile =  new Scanner(codeFile);

      while (inputFile.hasNext())
      {
        String nextLine = inputFile.nextLine();
        String[] readLine = nextLine.split("\\s+");
        String[] argsArray = new String[readLine.length-1];
        for (int i = 1; i < readLine.length; i++)
        {
          argsArray[i] = readLine[i];
        }

        String code_class = CodeTable.get(readLine[0]);
        ByteCode bytecode = (ByteCode) (Class.forName("interpreter.bytecode." + code_class)
                .getDeclaredConstructor().newInstance());
        codeHashMap.put(bytecode, argsArray);
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File" + byteCodeFile + "not found");
    } catch (InvocationTargetException e)
    {
      e.printStackTrace();
    } catch (NoSuchMethodException e)
    {
      System.err.println("Method does not exist");
    }

    Program program = new Program();
    program.addCodes(codeHashMap);
    return program;
  }
}