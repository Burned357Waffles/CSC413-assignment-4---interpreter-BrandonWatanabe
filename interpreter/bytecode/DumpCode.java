package interpreter.bytecode;

import interpreter.VirtualMachine;

public class DumpCode extends ByteCode
{
    private String byte_code;
    private String argument;

    public DumpCode(){}

    @Override
    public void init(String[] inputArgs)
    {
        this.byte_code = inputArgs[0];
        this.argument = inputArgs[1];
    }

    @Override
    public String getString()
    {
        return byte_code + " " + argument;
    }

    @Override
    public String getByteCode()
    {
        return byte_code;
    }

    @Override
    public void execute(VirtualMachine vm)
    {
        if (this.argument.equals("ON")) {
            vm.setDumpState(true);
        }else if(this.argument.equals("OFF"))
            vm.setDumpState(false);    }
}
