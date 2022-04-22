package interpreter.bytecode;

import interpreter.VirtualMachine;

public class ReturnCode extends ByteCode
{
    private String byte_code;
    private String argument;
    private int args_length;

    public ReturnCode(){}

    @Override
    public void init(String[] inputArgs)
    {
        this.args_length = inputArgs.length;
        this.byte_code = inputArgs[0];
        if (args_length == 2)
        {
            this.hasId = true;
            this.argument = inputArgs[1];
        }
    }

    @Override
    public String getString(){
        if (args_length == 2) return byte_code + " " + argument;
        return byte_code;
    }

    @Override
    public String getByteCode(){
        return byte_code;
    }

    public String getArgument(){ return argument;}

    public boolean isId() { return hasId; }

    public String getId()
    {
        String[] stringSeparated = argument.split("<");
        return stringSeparated[0];
    }

    @Override
    public void execute(VirtualMachine vm)
    {
        int target = vm.getReturn();
        vm.popFrame();
        vm.setPC(target);
    }
}
