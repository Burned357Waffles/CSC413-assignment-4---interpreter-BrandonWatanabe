package interpreter.bytecode;

import interpreter.VirtualMachine;

public class LoadCode extends ByteCode
{
    private String byte_code;
    private int argument;
    private boolean hasId = false;
    private String id = "";

    public LoadCode(){}

    @Override
    public void init(String[] inputArgs)
    {
        this.byte_code = inputArgs[0];
        this.argument = Integer.parseInt(inputArgs[1]);
        if (inputArgs.length == 3)
        {
            this.hasId = true;
            this.id = inputArgs[2];
        }
    }

    @Override
    public String getString(){ return byte_code + " " + argument + " " + id; }

    @Override
    public String getByteCode(){
        return byte_code;
    }

    public boolean isId() { return hasId; }

    public String getId() {return id;}

    @Override
    public void execute(VirtualMachine vm)
    {
        vm.load(argument);
    }
}
