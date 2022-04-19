package interpreter;

import java.util.HashMap;
import java.util.Scanner;

public class CodeTable {
  private static HashMap<String, String> byteCodeMap = new HashMap<>(); // <ByteCode String, ByteCode Class>
  private static String byteCodeFile = "bytecodes.txt";

  public static void init()
  {
    populateByteCodeMap();
  }

  private static void populateByteCodeMap()
  {
    Scanner inputFile = new Scanner(byteCodeFile);
    while (inputFile.hasNext())
    {
      String nextLine = inputFile.nextLine();
      String[] readLine = nextLine.split(", ");
      byteCodeMap.put(readLine[0], readLine[1]);
    }
    inputFile.close();
  }

  public static String get(String code) { return byteCodeMap.get(code); }
}