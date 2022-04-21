package interpreter.bytecode;

import interpreter.VirtualMachine;

public class StoreCode extends ByteCode
{
    private String byte_code;
    private String argument;
    private Integer value_to_store;
    private String id;

    public StoreCode(){}

    @Override
    public void init(String[] inputArgs)
    {
        this.byte_code = inputArgs[0];
        this.argument = inputArgs[1];
        this.value_to_store = Integer.parseInt(argument);
        this.id = inputArgs[2];
    }

    @Override
    public String getString(){
        return byte_code + " " + argument + " " + id;
    }

    @Override
    public String getByteCode(){
        return byte_code;
    }

    public String getArgument()
    {
        return argument;
    }

    @Override
    public void execute(VirtualMachine vm)
    {

    }
}
