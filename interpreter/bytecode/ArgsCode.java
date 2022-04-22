package interpreter.bytecode;

import interpreter.VirtualMachine;

public class ArgsCode extends ByteCode
{
    private String byte_code;
    private String argument = "";
    private int target;
    private int offset;

    public ArgsCode(){}

    @Override
    public void init(String[] inputArgs)
    {
        this.byte_code = inputArgs[0];
        this.offset = Integer.parseInt(inputArgs[1]);
        this.argument = inputArgs[1];
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

    public String getArgument(){ return argument;}

    @Override
    public void execute(VirtualMachine vm)
    {
        vm.newFrameAt(offset);
    }
}
