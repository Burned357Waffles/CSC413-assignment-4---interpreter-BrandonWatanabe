package interpreter;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;

public class CodeTable {
  private static HashMap<String, String> byteCodeMap = new HashMap<>(); // <ByteCode String, ByteCode Class>
  private static String byteCodeFile = "interpreter/bytecodes.txt";

  public static void init()
  {
    populateByteCodeMap();
  }

  private static void populateByteCodeMap()
  {
    try
    {
      File codeFile = new File(byteCodeFile);
      Scanner inputFile = inputFile = new Scanner(codeFile);
      while (inputFile.hasNext())
      {
        String nextLine = inputFile.nextLine();
        String[] readLine = nextLine.split(", ");
        byteCodeMap.put(readLine[0], readLine[1]);
      }
      inputFile.close();
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File " + byteCodeFile + " not found." );
    }
  }

  public static String get(String code) { return byteCodeMap.get(code); }
}