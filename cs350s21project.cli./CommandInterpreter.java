//Thi
import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.A_Command;

import java.util.Scanner;

public class CommandInterpreter {
    public static void main(String[] args) {
        CommandInterpreter ref = new CommandInterpreter();
        Scanner rf = new Scanner(System.in);
        String f = rf.nextLine();
        ref.evaluate(f);
    }

    public void evaluate (String input)
    {
        if(input == null || input.isEmpty()){
            throw  new RuntimeException();
        }

        CommandManagers newManager = new CommandManagers();
        String buildCommand = "//" + input;

        A_Command nc = new A_Command(newManager, buildCommand) {
            //insert functionality
            //the steps are wrapped in the object
            //when the object
            @Override
            public void execute() {
                System.out.println("hello world");
            }
        };

        //this will pop up with a gui where you input code and then you can change
        CommandManagers.getInstance().schedule(nc);
    }
}
