package interpreter.bytecode;

import interpreter.VirtualMachine;

public class StoreCode extends ByteCode
{
    private String byte_code;
    private Integer argument;
    private String id = "";

    public StoreCode(){}

    @Override
    public void init(String[] inputArgs)
    {
        this.byte_code = inputArgs[0];
        this.argument = Integer.parseInt(inputArgs[1]);
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

    public String getId() {return id;}

    @Override
    public void execute(VirtualMachine vm)
    {
        vm.store(argument);
    }
}
