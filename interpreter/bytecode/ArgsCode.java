package interpreter.bytecode;

import interpreter.VirtualMachine;

public class ArgsCode extends ByteCode
{
    private String byte_code;
    private int argument;
    private int target;

    public ArgsCode(){}

    @Override
    public void init(String[] inputArgs)
    {
        this.byte_code = inputArgs[0];
        this.argument = Integer.parseInt(inputArgs[1]);
    }

    @Override
    public String getString(){
        return byte_code + " " + argument;
    }

    @Override
    public String getByteCode(){
        return byte_code;
    }

    public void setTarget(int target){ this.target = target; }

    @Override
    public void execute(VirtualMachine vm)
    {
        vm.newFrameAt(argument);
    }
}
