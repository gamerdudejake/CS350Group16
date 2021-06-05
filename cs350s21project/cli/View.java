package cs350s21project.cli;
import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.view.*;
import cs350s21project.datatype.AgentID;
import cs350s21project.datatype.Latitude;
import cs350s21project.datatype.Longitude;

public class View {

    public void buildTopView(AgentID idWindow, CommandManagers windowManager, int size, Latitude latitudeOrigin, Latitude latitudeExtent, Latitude latitudeInterval,
                             Longitude longitudeOrigin, Longitude longitudeExtent, Longitude longitudeInterval)
    {
        //window sizing and name setup
        String windowName = "TopView";

        //building
        CommandViewCreateWindowTop TopWindow = new CommandViewCreateWindowTop(windowManager, windowName, idWindow, size, latitudeOrigin, latitudeExtent,
                latitudeInterval, longitudeOrigin, longitudeExtent, longitudeInterval);

        //execute Window
        TopWindow.execute();
        System.out.println("The buildTopView method has been invoked.");
    }

    public AgentID createNewAgentID(String id)
    {
        return new AgentID(id);
    }

    public void deleteWindow(AgentID windowId, String text, CommandManagers windowManager)
    {
        CommandViewDeleteWindow deleteWindow = new CommandViewDeleteWindow(windowManager, text, windowId);
        deleteWindow.execute();
    }

    public Longitude parseLongitudeString(String coordinatePreParse)
    {
        StringBuilder degrees = new StringBuilder();
        StringBuilder minutes = new StringBuilder();
        StringBuilder seconds = new StringBuilder();
        boolean deg = false;
        boolean min = false;
        boolean sec = false;

        for(int i =0; i < coordinatePreParse.length(); i++)
        {
            if(coordinatePreParse.charAt(i) != '*' && !deg && coordinatePreParse.charAt(i) != '(' && coordinatePreParse.charAt(i) != ')')
            {
                degrees.append(coordinatePreParse.charAt(i));
            }else if(coordinatePreParse.charAt(i) == '*')
            {
                deg = true;
            }

            else if(coordinatePreParse.charAt(i) != '\'' && !min && coordinatePreParse.charAt(i) != '(' && coordinatePreParse.charAt(i) != ')')
            {
                minutes.append(coordinatePreParse.charAt(i));
            }else if(coordinatePreParse.charAt(i) == '\'')
            {
                min = true;
            }

            else if(coordinatePreParse.charAt(i) != '#' && !sec && coordinatePreParse.charAt(i) != '(' && coordinatePreParse.charAt(i) != ')' )
            {
                seconds.append(coordinatePreParse.charAt(i));
            }else if(coordinatePreParse.charAt(i) == '#')
            {
                sec = true;
            }
        }

        int convSec = Integer.parseInt(seconds.toString());
        Longitude newLongitude = new Longitude(Integer.parseInt(degrees.toString()),Integer.parseInt(minutes.toString()), convSec);

        return newLongitude;
    }

    public Latitude parseLatitudeString(String coordinatePreParse)
    {
        StringBuilder degrees = new StringBuilder();
        StringBuilder minutes = new StringBuilder();
        StringBuilder seconds = new StringBuilder();

        boolean deg = false;
        boolean min = false;
        boolean sec = false;

        for(int i =0; i < coordinatePreParse.length(); i++)
        {

            if(coordinatePreParse.charAt(i) != '*' && !deg && coordinatePreParse.charAt(i) != '(' && coordinatePreParse.charAt(i) != ')')
            {
                degrees.append(coordinatePreParse.charAt(i));
            }else if(coordinatePreParse.charAt(i) == '*')
            {
                deg = true;
            }

            else if(coordinatePreParse.charAt(i) != '\'' && !min && coordinatePreParse.charAt(i) != '(' && coordinatePreParse.charAt(i) != ')')
            {
                minutes.append(coordinatePreParse.charAt(i));
            }else if(coordinatePreParse.charAt(i) == '\'')
            {
                min = true;
            }

            else if(coordinatePreParse.charAt(i) != '#' && !sec && coordinatePreParse.charAt(i) != '(' && coordinatePreParse.charAt(i) != ')')
            {
                seconds.append(coordinatePreParse.charAt(i));
            }else if(coordinatePreParse.charAt(i) == '#')
            {
                sec = true;
            }
        }

        int convSec = Integer.parseInt(seconds.toString());
        Latitude newLatitude = new Latitude(Integer.parseInt(degrees.toString()),Integer.parseInt(minutes.toString()), convSec);

        return newLatitude;
    }
}