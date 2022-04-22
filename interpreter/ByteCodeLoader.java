package interpreter;

import interpreter.bytecode.ByteCode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class ByteCodeLoader {
  private String byteCodeFile;
  public static HashMap<Integer, ByteCode> codeHashMap = new HashMap<>();
  public static ArrayList<String[]> argsList = new ArrayList<>();

  public ByteCodeLoader(String byteCodeFile) throws IOException
  {
    this.byteCodeFile = byteCodeFile;
  }

  public Program loadCodes()
  {
    try
    {
      int counter = 0;
      File codeFile = new File(byteCodeFile);
      Scanner inputFile =  new Scanner(codeFile);

      while (inputFile.hasNext())
      {
        String nextLine = inputFile.nextLine();
        String[] readLine = nextLine.split("\\s+");

        String code_class = CodeTable.get(readLine[0]);
        ByteCode bytecode = (ByteCode) (Class.forName("interpreter.bytecode." + code_class)
                .getDeclaredConstructor().newInstance());
        argsList.add(readLine);
        codeHashMap.put(counter, bytecode);
        counter++;
      }
    }
    catch (FileNotFoundException e)
    {
      System.err.println("File" + byteCodeFile + "not found");
    }
    catch (InvocationTargetException e)
    {
      System.err.println("Invocation Target Exception");
    } catch (NoSuchMethodException e)
    {
      System.err.println("Method does not exist");
    }
    catch (ClassNotFoundException e){
      System.err.println("Class does not exist");
    }
    catch (InstantiationException e){
      System.err.println("Instantiation exception");
    }
    catch (IllegalAccessException e){
      System.err.println("Illegal Access");
    }
    Program program = new Program();
    program.addCodes(codeHashMap, argsList);
    return program;
  }
}