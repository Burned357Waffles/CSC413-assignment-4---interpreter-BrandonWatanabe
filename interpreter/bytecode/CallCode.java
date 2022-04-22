package interpreter.bytecode;

import interpreter.VirtualMachine;

public class CallCode extends ByteCode
{
    private String byte_code;
    private String argument;
    private int target;

    public CallCode(){}

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

    public String getArgument(){ return argument;}

    public String getId()
    {
        String[] stringSeparated = argument.split("<");
        return stringSeparated[0];
    }

    public String getNumber() { return argument.replaceAll("[^0-9]", ""); }

    public void setTarget(int target){ this.target = target; }

    @Override
    public void execute(VirtualMachine vm)
    {
        vm.storeCurrentPC();
        vm.setPC(target);
    }
}
