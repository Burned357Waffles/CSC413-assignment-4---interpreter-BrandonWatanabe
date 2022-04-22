package interpreter.bytecode;

import interpreter.VirtualMachine;

public class LitCode extends ByteCode
{
    private String byte_code;
    private Integer argument;
    private String id = "";
    private int args_count;

    public LitCode(){}

    @Override
    public void init(String[] inputArgs)
    {
        this.args_count = inputArgs.length;
        this.byte_code = inputArgs[0];
        this.argument = Integer.parseInt(inputArgs[1]);
        if (args_count == 3) this.id = inputArgs[2];
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
        vm.push(argument);
    }
}
