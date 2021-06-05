package cs350s21project.cli;
import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.actor.CommandActorCreateActor;
import cs350s21project.controller.command.actor.CommandActorDefineShip;
import cs350s21project.controller.command.actor.CommandActorSetAltitudeDepth;
import cs350s21project.controller.command.actor.CommandActorSetCourse;
import cs350s21project.datatype.*;

import java.util.ArrayList;
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

    public Course createCourse(double input)
    {
        return new Course(input);
    }

    public Groundspeed createGroundSpeed(double input)
    {
        return new Groundspeed(input);
    }

    //before creating a new coordinateWorld3D we need to create coordinate world object.
    public CoordinateWorld3D createCoordinateWorld3D(CoordinateWorld coordinateWorld, Altitude altitude)
    {
        return new CoordinateWorld3D(coordinateWorld, altitude);
    }

    //requires a Latitude object and a Longitude object.
    public CoordinateWorld createCoordinateWorldObj(Latitude latitude, Longitude longitude)
    {
        return new CoordinateWorld(latitude, longitude);
    }

    public ArrayList<AgentID> stringListToMunitionsList(ArrayList<String> munitions)
    {
        ArrayList<AgentID> target = new ArrayList<>();

        for(int i =0; i < munitions.size(); i++)
        {
            View view = new View();
            target.add(view.createNewAgentID(munitions.get(i)));
        }

        return target;
    }

    public CoordinateWorld3D parseActorsCoordinates(String input)
    {
        String[] coordinates = input.split("/");

        View view = new View();

        Latitude latitude = view.parseLatitudeString(coordinates[0]);
        Longitude longitude = view.parseLongitudeString(coordinates[1]);
        Altitude altitude = new Altitude(Double.parseDouble(coordinates[2]));

        return new CoordinateWorld3D(createCoordinateWorldObj(latitude, longitude), altitude);
    }
}
