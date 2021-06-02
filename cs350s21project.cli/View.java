//TODO: LINK TO PARSER AND DELETE LAUNCH ALL WINDOWS METHOD AND MAIN AFTER LINKING.
package cs350s21project.cli;
import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.view.*;
import cs350s21project.datatype.AgentID;
import cs350s21project.datatype.Altitude;
import cs350s21project.datatype.Latitude;
import cs350s21project.datatype.Longitude;

public class View {

    //tester method please delete when interconnecting.
    public static void main(String[] args)
    {
        View ref = new View();
        ref.parseLongitudeString("(49*39'32#" );
        ref.parseLatitudeString("0*10'0#" );
        ref.parseLatitudeString("0*0'30#)" );
    }

    //For now this is a test method to help with testing createWindowMethods
    //note I guess can't have same id for two different views.
    public void launchAllWindows()
    {

        AgentID idTop = createNewAgentID("TopView");
        CommandManagers windowManager = new CommandManagers();

        Latitude latitudeOrigin = new Latitude(2, 4, 1.00);
        Latitude latitudeExtent = new Latitude(2, 4, 1.00);
        Latitude latitudeInterval = new Latitude(2, 4, 1.00);
        Longitude longitudeOrigin = new Longitude(2, 4, 1.00);
        Longitude longitudeExtent = new Longitude(2, 4, 1.00);
        Longitude longitudeInterval = new Longitude(2, 4, 1.00);

    }

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

    //temporary methods utilized for displaying what makes a latitude, longitude, and altitude object.
    public Altitude buildAltitudeCoordinate(double initialAltitude) {
        Altitude newAltitudeCoordinate = new Altitude(5.00);
        System.out.println("The following coordinate has been set for Altitude: " + newAltitudeCoordinate.toString());
        return newAltitudeCoordinate;
    }

    //temporary method utilized for displaying what makes a latitude object.
    public Latitude buildLatitudeCoordinate(int degrees, int minutes, double seconds) {
        Latitude newLatitude = new Latitude(degrees, minutes, seconds);
        System.out.println("The following coordinate has been set for Latitude: " + newLatitude.toString());
        return newLatitude;
    }

    public Longitude buildLongitudeCoordinate(int degrees, int minutes, double seconds) {
        Longitude newLongitude = new Longitude(degrees, minutes, seconds);
        System.out.println("The following data has been set for Longitude: " + newLongitude.toString());
        return newLongitude;
    }

    //misc.
    public AgentID createNewAgentID(String id)
    {
        return new AgentID(id);
    }

    //deletes a window
    //I'm assuming the text passed in will display something to the console.
    public void deleteWindow(AgentID windowId, String text, CommandManagers windowManager)
    {
        CommandViewDeleteWindow deleteWindow = new CommandViewDeleteWindow(windowManager, text, windowId);
        deleteWindow.execute();
    }

    //tested extracts variables and creates a longitude object just fine.

    public Longitude parseLongitudeString(String coordinatePreParse)
    {
        //TODO: make sure to remove the question mark and replace it with a single quote for checking minutes.
        //TODO: have to make a few edits to converting from string to doubles but working just needs to be duplicated for altitude as well.
        //set positionInArray longitude degrees*minutes'seconds"

        System.out.println("The passed in command is: " + coordinatePreParse);
        //converting input to params for the method being invoked.
        StringBuilder degrees = new StringBuilder();
        StringBuilder minutes = new StringBuilder();
        StringBuilder seconds = new StringBuilder();
        boolean deg = false;
        boolean min = false;
        boolean sec = false;

        for(int i =0; i < coordinatePreParse.length(); i++)
        {
            if(coordinatePreParse.charAt(i) != '*' && !deg && coordinatePreParse.charAt(i) != '(' )
            {
                degrees.append(coordinatePreParse.charAt(i));
            }else if(coordinatePreParse.charAt(i) == '*')
            {
                deg = true;
            }

            else if(coordinatePreParse.charAt(i) != '\'' && !min && coordinatePreParse.charAt(i) != '('  )
            {
                minutes.append(coordinatePreParse.charAt(i));
            }else if(coordinatePreParse.charAt(i) == '\'')
            {
                min = true;
            }

            else if(coordinatePreParse.charAt(i) != '#' && !sec && coordinatePreParse.charAt(i) != '(' )
            {
                seconds.append(coordinatePreParse.charAt(i));
            }else if(coordinatePreParse.charAt(i) == '#')
            {
                sec = true;
            }
        }
        System.out.println(degrees.toString() +" "+ minutes.toString() +" "+ seconds.toString());
        //invoking the method to set a longitude variable.
        int convSec = Integer.parseInt(seconds.toString());

        Longitude newLongitude = new Longitude(Integer.parseInt(degrees.toString()),Integer.parseInt(minutes.toString()), convSec);
        System.out.println(newLongitude.toString());

        return newLongitude;
    }

    public Latitude parseLatitudeString(String coordinatePreParse)
    {
        //TODO: make sure to remove the question mark and replace it with a single quote for checking minutes.
        //TODO: have to make a few edits to converting from string to doubles but working just needs to be duplicated for altitude as well.
        //set positionInArray longitude degrees*minutes'seconds"

        System.out.println("The passed in command is: "+ coordinatePreParse);
        //converting input to params for the method being invoked.
        StringBuilder degrees = new StringBuilder();
        StringBuilder minutes = new StringBuilder();
        StringBuilder seconds = new StringBuilder();

        boolean deg = false;
        boolean min = false;
        boolean sec = false;

        for(int i =0; i < coordinatePreParse.length(); i++)
        {

            if(coordinatePreParse.charAt(i) != '*' && !deg && coordinatePreParse.charAt(i) != '(' )
            {
                degrees.append(coordinatePreParse.charAt(i));
            }else if(coordinatePreParse.charAt(i) == '*')
            {
                deg = true;
            }

            else if(coordinatePreParse.charAt(i) != '\'' && !min && coordinatePreParse.charAt(i) != '('  )
            {
                minutes.append(coordinatePreParse.charAt(i));
            }else if(coordinatePreParse.charAt(i) == '\'')
            {
                min = true;
            }

            else if(coordinatePreParse.charAt(i) != '#' && !sec && coordinatePreParse.charAt(i) != '(' )
            {
                seconds.append(coordinatePreParse.charAt(i));
            }else if(coordinatePreParse.charAt(i) == '#')
            {
                sec = true;
            }
        }
        System.out.println(degrees.toString() +" "+ minutes.toString() +" "+ seconds.toString());
        //invoking the method to set a longitude variable.
        int convSec = Integer.parseInt(seconds.toString());

        Latitude newLatitude = new Latitude(Integer.parseInt(degrees.toString()),Integer.parseInt(minutes.toString()), convSec);
        System.out.println(newLatitude.toString());

        return newLatitude;
    }
}