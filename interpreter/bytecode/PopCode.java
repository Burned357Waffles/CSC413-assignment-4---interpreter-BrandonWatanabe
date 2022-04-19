package interpreter.bytecode;

import interpreter.VirtualMachine;

public class PopCode extends ByteCode
{
    private String byte_code;
    private String argument;
    private Integer args_count;

    @Override
    public void init(String[] input_args)
    {
        this.byte_code = input_args[0];
        this.argument = input_args[1];
        this.args_count = Integer.parseInt(argument);
    }

    @Override
    public String getString(){
        return byte_code + " " + argument;
    }

    @Override
    public String getByteCode(){
        return byte_code;
    }

    @Override
    public void execute(VirtualMachine vm)
    {

    }


}
