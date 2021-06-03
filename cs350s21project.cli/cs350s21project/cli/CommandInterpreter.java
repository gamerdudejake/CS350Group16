//ignore any package warnings from your IDE
//it works please do not touch I'm going to add it to every file.
package cs350s21project.cli;
import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.A_Command;

public class CommandInterpreter {
    public static void main(String[] args) {
<<<<<<< HEAD:cs350s21project.cli/CommandInterpreter.java
        CommandInterpreter ref = new CommandInterpreter();
        Scanner rf = new Scanner(System.in);
        String f = rf.nextLine();
        ref.evaluate(f);
=======
        CommandInterpreter CmdInterpreter = new CommandInterpreter();
        CmdInterpreter.evaluate("run");
>>>>>>> TestMasterBranch:cs350s21project.cli/cs350s21project/cli/CommandInterpreter.java
    }

    //when the input is passed in it will then launch the Commands console
    //Each new Command that is passed in will be entered into the body of the
    //evaluate and if the input is equal to the conditions of exectue then it will launch.
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
