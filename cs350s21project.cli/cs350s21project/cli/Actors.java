package cs350s21project.cli;
import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.actor.CommandActorCreateActor;
import cs350s21project.controller.command.actor.CommandActorDefineShip;
import cs350s21project.controller.command.actor.CommandActorSetAltitudeDepth;
import cs350s21project.controller.command.actor.CommandActorSetCourse;
import cs350s21project.datatype.*;
import java.util.List;

public class Actors {

    public void defineShip(CommandManagers managers, String text, AgentID idActor, List<AgentID> idMunitions)
    {
        CommandActorDefineShip defineShip = new CommandActorDefineShip(managers, text, idActor, idMunitions);
        defineShip.execute();
    }

    public void createActor(CommandManagers managers, String text, AgentID idActor, AgentID idFamily, CoordinateWorld3D position, Course course, Groundspeed speed)
    {
        CommandActorCreateActor createActor = new CommandActorCreateActor(managers, text, idActor,idFamily, position, course, speed);
        createActor.execute();
    }

    public void setCourseId(CommandManagers managers, String text, AgentID idActor, Course course)
    {
        CommandActorSetCourse setCourse = new CommandActorSetCourse(managers, text, idActor, course);
        setCourse.execute();
    }

    public void setSpeedId(CommandManagers managers, String text, AgentID idActor, Course course)
    {
       CommandActorSetCourse setCourse = new CommandActorSetCourse(managers, text, idActor, course);
       setCourse.execute();
    }

    public void setAltitude(CommandManagers managers, String text, AgentID idActor, Altitude altitude)
    {
        CommandActorSetAltitudeDepth setAltitudeDepth = new CommandActorSetAltitudeDepth(managers, text, idActor, altitude);
        setAltitudeDepth.execute();
    }

    //------Misc----------
    //before creating a new coordinateWorld3D we need to create coordinate world object.
    public void createCoordinateWorld3D(CoordinateWorld coordinateWorld, Altitude altitude)
    {
        CoordinateWorld3D newCorWorld = new CoordinateWorld3D(coordinateWorld, altitude);
    }

    //requires a Latitude object and a Longitude object.
    public CoordinateWorld createCoordinateWorldObj(Latitude latitude, Longitude longitude)
    {
        return new CoordinateWorld(latitude, longitude);
    }
}
