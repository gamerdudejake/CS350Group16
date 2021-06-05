package cs350s21project.cli;
import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.actor.CommandActorSetState;
import cs350s21project.controller.command.misc.*;
import cs350s21project.datatype.*;

public class Misc {

    //@load
    public void load(CommandManagers manager, String text, String filename)
    {
        CommandMiscLoad loadMisc = new CommandMiscLoad(manager, text, filename);
        loadMisc.execute();
    }

    //@pause
    public void pause(CommandManagers manager, String text)
    {
        CommandMiscPause pauseMisc = new CommandMiscPause(manager, text);
        pauseMisc.execute();
    }

    //@resume
    public void resume(CommandManagers manager, String text)
    {
        CommandMiscResume resumeMisc = new CommandMiscResume(manager, text);
        resumeMisc.execute();
    }

    //@set update time
    public void setUpdateTime(CommandManagers manager, String text, Time time )
    {
        CommandMiscSetUpdate updateTimeMisc = new CommandMiscSetUpdate(manager, text, time);
        updateTimeMisc.execute();
    }

    //@wait
    public void waitTime(CommandManagers manager, String text, Time time)
    {
        CommandMiscWait waitMisc = new CommandMiscWait(manager, text, time);
        waitMisc.execute();
    }
    //@force
    public void force(CommandManagers manager, String text, AgentID id, CoordinateWorld3D position, Course course, Groundspeed speed)
    {
        CommandActorSetState setStateMisc = new CommandActorSetState(manager, text, id, position, course, speed);
        setStateMisc.execute();
    }

    //@exit
    public void exit(CommandManagers manager, String text)
    {
        CommandMiscExit exitMisc = new CommandMiscExit(manager, text);
        exitMisc.execute();
    }

    //-------Misc. methods for executing the above methods.-----------

    public Time createNewTime(double seconds)
    {
        return new Time(seconds);
    }

    public Groundspeed createNewGroundSpeed(double speed)
    {
        return new Groundspeed(speed);
    }
}
