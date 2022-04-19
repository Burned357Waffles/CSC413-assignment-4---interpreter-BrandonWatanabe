package interpreter.bytecode;

import interpreter.VirtualMachine;

public class LoadCode extends ByteCode
{
    private String byte_code;
    private String argument;
    private Integer value_to_load;
    private String id;

    @Override
    public void init(String[] inputArgs)
    {
        this.byte_code = inputArgs[0];
        this.argument = inputArgs[1];
        this.value_to_load = Integer.parseInt(argument);
        this.id = inputArgs[2];
    }

    @Override
    public String getString(){ return byte_code + " " + argument + " " + id; }

    @Override
    public String getByteCode(){
        return byte_code;
    }


    @Override
    public void execute(VirtualMachine vm)
    {

    }
}
