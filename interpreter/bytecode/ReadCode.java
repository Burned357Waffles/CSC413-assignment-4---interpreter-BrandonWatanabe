package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.Scanner;

public class ReadCode extends ByteCode
{
    private String byte_code;

    public ReadCode(){}

    @Override
    public void init(String[] inputArgs)
    {
        this.byte_code = inputArgs[0];
    }

    @Override
    public String getString()
    {
        return byte_code;
    }

    @Override
    public String getByteCode()
    {
        return byte_code;
    }

    @Override
    public void execute(VirtualMachine vm)
    {
        System.out.println("Please enter an integer: ");
        Scanner readLine = new Scanner(System.in);
        vm.push(readLine.nextInt());
    }
}
