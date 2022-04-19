package interpreter.bytecode;

import interpreter.VirtualMachine;

public class LabelCode extends ByteCode
{
    private String byte_code;
    private String argument;
    private int target;

    @Override
    public void init(String[] inputArgs)
    {
        this.byte_code = inputArgs[0];
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

    public String getArgument(){ return argument; }

    @Override
    public void execute(VirtualMachine vm)
    {

    }
}
