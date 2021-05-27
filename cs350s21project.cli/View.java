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
        AgentID idFront = createNewAgentID("FrontView");
        AgentID idSide = createNewAgentID("SideView");
        CommandManagers windowManager = new CommandManagers();

        Latitude latitudeOrigin = new Latitude(2, 4, 1.00);
        Latitude latitudeExtent = new Latitude(2, 4, 1.00);
        Latitude latitudeInterval = new Latitude(2, 4, 1.00);
        Longitude longitudeOrigin = new Longitude(2, 4, 1.00);
        Longitude longitudeExtent = new Longitude(2, 4, 1.00);
        Longitude longitudeInterval = new Longitude(2, 4, 1.00);

        Altitude altitudeOrigin = buildAltitudeCoordinate(3.00);
        Altitude altitudeExtent = buildAltitudeCoordinate(3.00);
        Altitude altitudeAGLInterval = buildAltitudeCoordinate(3.00);
        Altitude altitudeBGLInterval = buildAltitudeCoordinate(3.00);

        buildTopView(idTop, windowManager, latitudeOrigin, latitudeExtent, latitudeInterval, longitudeOrigin, longitudeExtent, longitudeInterval);
        buildFrontView(idFront, windowManager, longitudeOrigin, longitudeExtent, longitudeInterval, altitudeOrigin, altitudeExtent, altitudeAGLInterval, altitudeBGLInterval);
        buildSideView(idSide, windowManager, latitudeOrigin, latitudeExtent, latitudeInterval, altitudeOrigin, altitudeExtent,altitudeAGLInterval, altitudeBGLInterval);
    }

    public void buildTopView(AgentID idWindow, CommandManagers windowManager, Latitude latitudeOrigin, Latitude latitudeExtent, Latitude latitudeInterval,
                             Longitude longitudeOrigin, Longitude longitudeExtent, Longitude longitudeInterval)
    {
        //window sizing and name setup
        String windowName = "TopView";
        int size = 1920;

        //building
        CommandViewCreateWindowTop TopWindow = new CommandViewCreateWindowTop(windowManager, windowName, idWindow, size, latitudeOrigin, latitudeExtent,
                latitudeInterval, longitudeOrigin, longitudeExtent, longitudeInterval);

        //execute Window
        TopWindow.execute();
    }

    public void buildFrontView(AgentID idWindow, CommandManagers windowManager, Longitude longitudeOrigin, Longitude longitudeExtent, Longitude longitudeInterval,
                               Altitude altitudeOrigin, Altitude altitudeExtent, Altitude altitudeAGLInterval, Altitude altitudeBGLInterval)
    {
        //window sizing and name setup
        String windowName = "FrontView";
        int size = 1920;

        //building
        CommandViewCreateWindowFront frontWindow = new CommandViewCreateWindowFront(windowManager, windowName, idWindow, size,
                longitudeOrigin, longitudeExtent, longitudeInterval, altitudeOrigin, altitudeExtent, altitudeAGLInterval, altitudeBGLInterval);

        //execute Window
        frontWindow.execute();
    }

    public void buildSideView(AgentID idWindow, CommandManagers windowManager, Latitude latitudeOrigin, Latitude latitudeExtent, Latitude latitudeInterval,
                              Altitude altitudeOrigin, Altitude altitudeExtent, Altitude altitudeAGLInterval, Altitude altitudeBGLInterval)
    {
        //window sizing in pixels and name setup
        String windowName = "SideView";
        int size = 1920;
        //building
        CommandViewCreateWindowSide sideWindow = new CommandViewCreateWindowSide(windowManager, windowName, idWindow, size, latitudeOrigin, latitudeExtent,
                latitudeInterval, altitudeOrigin, altitudeExtent, altitudeAGLInterval, altitudeBGLInterval);

        //execute Window
        sideWindow.execute();
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

    public void unlockWindow(AgentID windowId, String text, CommandManagers windowManager)
    {
        CommandViewUnlockWindow unlockWindow = new CommandViewUnlockWindow(windowManager, text, windowId);
        unlockWindow.execute();
    }

    //utilizes two id's which are the windowId and an actorID like munitions.
    public void lockWindow(AgentID windowId, String text, CommandManagers windowManager, AgentID actorID)
    {
        CommandViewLockWindow lockWindow = new CommandViewLockWindow(windowManager, text, windowId, actorID);
        lockWindow.execute();
    }
}