package cs350s21project.cli;
import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.A_Command;

public class CommandInterpreter {
    /*the application does not take in input from the command line.
     instead the string is a place holder so it can take in and replace input string to whatever is passed into
     the application. */
    public static void main(String[] args) {
        CommandInterpreter CmdInterpreter = new CommandInterpreter();
        CmdInterpreter.evaluate("run");
    }

    public void evaluate (String input)
    {
        if(input == null || input.isEmpty()){
            throw  new RuntimeException();
        }

        Parser parser = new Parser();
        CommandManagers newManager = new CommandManagers();

        A_Command newCommand = new A_Command(newManager, input)
        {
            //when a value is passed into the evaluate via
            @Override
            public void execute()
            {
                //need to pass in the input from a new command into the parser
                parser.runParser(input);
            }
        };

        newCommand.getCommandText();
        CommandManagers.getInstance().schedule(newCommand);
    }
}