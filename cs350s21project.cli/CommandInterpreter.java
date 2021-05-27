//ignore any package warnings from your IDE
//it works please do not touch I'm going to add it to every file.
package cs350s21project.cli;
import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.A_Command;

import java.util.Scanner;

public class CommandInterpreter {
    public static void main(String[] args) {

        CommandInterpreter CmdInterpartor = new CommandInterpreter();
        Scanner rf = new Scanner(System.in);
        String onStart = rf.nextLine();

        System.out.println("Please type the word: run in the console to launch app.");
        if(onStart.equalsIgnoreCase("run")) {
            CmdInterpartor.evaluate("hello");
        }
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
                getCommandText();
            }
        };

        newCommand.getCommandText();
        CommandManagers.getInstance().schedule(newCommand);
    }
}
