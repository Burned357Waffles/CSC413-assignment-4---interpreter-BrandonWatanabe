package interpreter.bytecode;

import interpreter.VirtualMachine;

public class HaltCode extends ByteCode
{
    private String byte_code;

    public HaltCode(){}

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
        vm.haltProgram();
    }
}
