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
        ref.launchAllWindows();
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

        buildTopView(idTop, windowManager, latitudeOrigin, latitudeExtent, latitudeInterval, longitudeOrigin, longitudeExtent, longitudeInterval);
    }

    public void buildTopView(AgentID idWindow, CommandManagers windowManager, Latitude latitudeOrigin, Latitude latitudeExtent, Latitude latitudeInterval,
                             Longitude longitudeOrigin, Longitude longitudeExtent, Longitude longitudeInterval)
    {
        //window sizing and name setup
        String windowName = "TopView";
        int size = 1920 * 1080;

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
}