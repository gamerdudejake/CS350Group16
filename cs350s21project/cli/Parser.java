package cs350s21project.cli;
import cs350s21project.controller.CommandManagers;
import cs350s21project.datatype.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Parser {
    private CommandManagers universalWindowManager = new CommandManagers();
    private HashSet<String> keyWords = new HashSet<>();
    private String userInput;
    private String[] words;
    private String fileName;

    // Universal Variables
    private String id;
    private String id2;
    private String id3;
    private ArrayList<String> idN;

    // Actors variables
    private int coordinates; // TODO: datatype will be CoordinateWorld3D 45*30'15"/110*30'10"/200
    private int course; // TODO: datatype will be Course 090
    private int speed; // TODO: datatype will be Groundspeed 25
    private int altitude; // TODO: datatype will be Altitude 1000

    // Munitions Variables
    private double time;// TODO: datatype will be Time 10.8
    private double distance; // TODO: datatype will be DistanceNauticalMiles 25.3
    private double azimuth; // TODO: datatype will be AttitudeYaw 10
    private double elevation; // TODO: datatype will be AttitudePitch 25

    // Sensors/Fuzes Variables
    private double fov;// TODO: datatype will be FieldOfView 10
    private double power;// TODO: datatype will be Power 10
    private double sensitivity;// TODO: datatype will be Sensitivity 10
    // altitude // already used in Actor
    // distance
    // time

    public void runParser(String command) {
        this.userInput = command;
        loadKeyWords();
        parseInput();
        compareToKeyWords();
        determineCommand();
    }

    public void compareToKeyWords() {
        for (int i = 0; i <= (this.words.length -1); i++) {
            String lowerWord = this.words[i].toLowerCase();
            if (this.keyWords.contains(lowerWord)) {
                this.words[i] = lowerWord;
            }
        }
    }

    public void idNParse(int start) {
        ArrayList<String> tempIDN = new ArrayList<>();
        for (int i = start; i <= (this.words.length -1); i++) {
            String temp = this.words[i];
            temp = temp.replace("(", "");
            temp = temp.replace(")", "");
            temp = temp.replace(",", "");
            tempIDN.add(temp);
        }
        this.idN = tempIDN;
    }

    public void parseInput() {
        this.words = this.userInput.split(" ");
    }

    public void determineCommand() {
        if (this.words.length >= 1) {
            if (this.words[0].charAt(0) == '@'){
                miscellaneous();
            }
        }
        if (this.words.length >= 3) {
            views();
        }
        if (this.words.length >= 4) {
            actors();
            munitions();
            sensorsFuzes();
        }
    }

    public void views() {
        // I. VIEWS
        if (this.words[1].equals("window")) {
            switch(this.words[0]) {
                case "create":
                    if(words[3].equals("top")) {
                        View newView = new View();

                        AgentID id = newView.createNewAgentID(words[2]);
                        int size = Integer.parseInt(words[6]);
                        Latitude latitude1 = newView.parseLatitudeString(words[7]);
                        Latitude latitude2 = newView.parseLatitudeString(words[8]);
                        Latitude latitude3 = newView.parseLatitudeString(words[9]);
                        Longitude longitude1 = newView.parseLongitudeString(words[10]);
                        Longitude longitude2 = newView.parseLongitudeString(words[11]);
                        Longitude longitude3 = newView.parseLongitudeString(words[12]);

                        newView.buildTopView(id, universalWindowManager, size, latitude1, latitude2, latitude3, longitude1, longitude2, longitude3);
                        System.out.println("Top window set with attributes id:  " + id.toString() + " Size: " + size + " latitude1: " + latitude1.toString()+ " latitude2: " + latitude2.toString() + " latitude3: " + latitude3.toString() + " longitude1: " + longitude1.toString() + " longitude2: " + longitude2.toString() + " longitude3: " + longitude3.toString());
                    }
                    break;
                case "delete":
                    View newView = new View();
                    AgentID id = newView.createNewAgentID(this.words[2]);

                    //invoking the delete method.
                    newView.deleteWindow(id,this.userInput, this.universalWindowManager);
                    System.out.println("The window " +id.toString() + " has been deleted.");
                    break;
            }
        }
    }

    public void actors() {
        // II. ACTORS
        if (this.words[1].equals("ship")) {
            Actors actor = new Actors();
            View view = new View();

            this.id = this.words[2];
            AgentID ID = view.createNewAgentID(this.id);
            idNParse(5);

            actor.defineShip(universalWindowManager,this.userInput, ID, actor.stringListToMunitionsList(this.idN));
            System.out.println("The ship with id: " + ID.toString() + " has been defined");
        }
        else if (this.words[1].equals("actor")) {
            View view = new View();
            Actors actor = new Actors();

            this.id = this.words[2];
            this.id2 = this.words[4];
            String unparsedCor = this.words[6];
            AgentID id1 = view.createNewAgentID(this.id);
            AgentID id2 = view.createNewAgentID(this.id2);
            double crse = Double.parseDouble(this.words[9]);
            double spd = Double.parseDouble(this.words[11]);

            actor.createActor(universalWindowManager,this.userInput, id1, id2, actor.parseActorsCoordinates(unparsedCor), actor.createCourse(crse), actor.createGroundSpeed(spd));
            System.out.println("Create Actor has been successfully invoked.");
        }
        else if (this.words[0].equals("set") &&
                (this.words[2].equals("course") || this.words[2].equals("speed")
                        || this.words[2].equals("depth") || this.words[2].equals("altitude"))){
            switch(this.words[2]) {
                case "course":
                    View v = new View();
                    Actors a = new Actors();

                    this.id = this.words[1];
                    double crse =  Double.parseDouble(this.words[3]);
                    Course c = a.createCourse(crse);
                    AgentID Id = v.createNewAgentID(this.id);

                    a.setCourseId(universalWindowManager,this.userInput, Id, c);
                    System.out.println("The set course command with id: " + Id.toString() + " course: " + c.toString() + " has been invoked." );
                    break;
                case "speed":
                    v = new View();
                    a = new Actors();

                    this.id = this.words[1];
                    double speed =  Double.parseDouble(this.words[3]);
                    AgentID spId = v.createNewAgentID(this.id);
                    Course spCrs = a.createCourse(speed);

                    a.setSpeedId(universalWindowManager,this.userInput, spId, spCrs);
                    System.out.println("The set speed command with id: " + spId.toString() + " Course: " + spCrs.toString() + " has been invoked.");
                    break;
                case "depth" :
                case "altitude" :
                    v = new View();
                    a = new Actors();

                    this.id = this.words[1];
                    double altitud =  Double.parseDouble(this.words[3]);
                    AgentID aId = v.createNewAgentID(this.id);
                    Altitude altitude = new Altitude(altitud);

                    a.setAltitude(universalWindowManager,this.userInput, aId, altitude);
                    System.out.println("The set altitude has with id: " + aId.toString() + " with altitude: " + altitude.toString() + " hase been invoked.") ;
                    break;
            }
        }
    }

    public void munitions() {
        // III. MUNITIONS
        if (this.words[1].equals("munition")) {
            // define
            switch(this.words[2]) {
                case "bomb":
                    Munitions m = new Munitions();

                    this.id = this.words[3];

                    m.Define_Bomb(universalWindowManager,this.userInput, this.id);
                    System.out.println("Define bomb with variables: ID: " + this.id + " has been invoked.");
                    break;
                case "shell":
                    m = new Munitions();

                    this.id = this.words[3];

                    m.Define_Shell(universalWindowManager,this.userInput, this.id);
                    System.out.println("Define munition with variables: ID: " + this.id + " has been invoked.");
                    break;
                case "depth_charge":
                    m = new Munitions();

                    this.id = this.words[3];
                    this.id2 = this.words[6];

                    System.out.println("define depth_charge with variables: ID: " + this.id + " ID2: " + this.id2 + " has been invoked.");
                    m.DefineDepthCharge(universalWindowManager,this.userInput,this.id, this.id2);
                    break;
                case "torpedo":
                    m = new Munitions();

                    this.id = this.words[3];
                    this.id2 = this.words[6];
                    this.id3 = this.words[8];
                    this.time = Double.parseDouble(this.words[11]);

                    System.out.println("The command define torpedo with variables: ID: " + this.id + " ID2: " + this.id2 + " ID3: " +
                            this.id3 + " Time: " + this.time + " has been invoked.");
                    m.DefineTorpedo(universalWindowManager,this.userInput,this.id, this.id2, this.id3, this.time );
                    break;
                case "missile":
                    m = new Munitions();

                    this.id = this.words[3];
                    this.id2 = this.words[6];
                    this.id3 = this.words[8];
                    this.distance = Double.parseDouble(this.words[11]);

                    System.out.println("The command define missile with variables: ID: " + this.id + " ID2: " + this.id2 + " ID3: " +
                            this.id3 + " Distance: " + this.distance + " has been invoked.");
                    m.DefineMissile(universalWindowManager,this.userInput, this.id, this.id2, this.id3, this.distance);
                    break;
            }
            // set
            if (this.words[2].equals("load")) {
                Munitions m = new Munitions();

                this.id = this.words[1];
                this.id2 = this.words[4];

                System.out.println("The command set load with Variables: ID: " + this.id + " ID2: " + this.id2 + " has been invoked.");
                m.LoadMunition(universalWindowManager, this.userInput ,this.id, this.id2);
            }
            else if (this.words[2].equals("deploy") && this.words.length == 5) {
                Munitions m = new Munitions();
                // set id1 deploy munition id2
                this.id = this.words[1];
                this.id2 = this.words[4];

                System.out.println("The command set deploy munitions with variables: ID: " + this.id + " ID2: " + this.id2 + " has been invoked.");
                m.DeployMunition(universalWindowManager, this.userInput, this.id, this.id2);
            }
            else if (this.words[2].equals("deploy") && this.words.length > 5) {
                Munitions m = new Munitions();
                // set id1 deploy munition id2 at azimuth azimuth elevation elevation
                this.id = this.words[1];
                this.id2 = this.words[4];
                this.azimuth = Double.parseDouble(this.words[7]);
                this.elevation = Double.parseDouble(this.words[9]);

                System.out.println("The command set deploy munitions with variables: ID: " + this.id + " ID2: " + this.id2 + " Azimuth: " +
                        this.azimuth + " Elevation: " + this.elevation +" has been invoked.");
                m.DeployMunitionShell(universalWindowManager, this.userInput, this.id, this.id2, this.azimuth, this.elevation);
            }
        }
    }

    public void sensorsFuzes() {
        // IV. SENSORS/FUZES
		SensorsAndFuses SF = new SensorsAndFuses();
        if (this.words[1].equals("sensor")) {
            switch(this.words[2]) {
                case "radar":
                    // define sensor radar id with field of view fov power power sensitivity sensitivity
                    this.id = this.words[3];
                    this.fov = Double.parseDouble(this.words[8]);
                    this.power = Double.parseDouble(this.words[10]);
                    this.sensitivity = Double.parseDouble(this.words[12]);
                    System.out.println("Variables: ID: " + this.id + " FOV: " + this.fov + " Power: " +
                            this.power + " Sensitivity: " + this.sensitivity);
					SF.Sensor_Radar(this.universalWindowManager,this.userInput,this.id,this.fov,this.power,this.sensitivity);
                    break;
                case "thermal":
                    // define sensor thermal id with field of view fov sensitivity sensitivity
                    this.id = this.words[3];
                    this.fov = Double.parseDouble(this.words[8]);
                    this.sensitivity = Double.parseDouble(this.words[10]);
                    System.out.println("Variables: ID: " + this.id + " FOV: " + this.fov +
                            " Sensitivity: " + this.sensitivity);
					SF.Sensor_Thermal(this.universalWindowManager,this.userInput, this.id, this.fov, this.sensitivity);
                    break;
                case "acoustic":
                    // define sensor acoustic id with sensitivity sensitivity
                    this.id = this.words[3];
                    this.sensitivity = Double.parseDouble(this.words[6]);
                    System.out.println("Variables: ID: " + this.id + " Sensitivity: " + this.sensitivity);
					SF.Sensor_Acoustic(this.universalWindowManager, this.userInput, this.id, this.sensitivity);
                    break;
                case "depth":
                    // define sensor depth id with trigger depth altitude
                    this.id = this.words[3];
                    this.altitude = Integer.parseInt(this.words[7]);
                    System.out.println("Variables: ID: " + this.id + " Depth: " + this.altitude);
					SF.Sensor_Depth(this.universalWindowManager, this.userInput, this.id, this.altitude);
                    break;
                case "distance":
                    // define sensor distance id with trigger distance distance
                    this.id = this.words[3];
                    this.distance = Integer.parseInt(this.words[7]);
                    System.out.println("Variables: ID: " + this.id + " Distance: " + this.distance);
					  SF.Sensor_Distance(this.universalWindowManager, this.userInput, this.id, this.distance);
                    break;
                case "time":
                    // define sensor time id with trigger time time
                    this.id = this.words[3];
                    this.time = Double.parseDouble(this.words[7]);
                    System.out.println("Variables: ID: " + this.id + " Time: " + this.time);
					 SF.Sensor_Time(this.universalWindowManager, this.userInput, this.id, this.time);
                    break;
            }
            if (this.words[3].equals("active")) {
                // define sensor sonar active id with power power sensitivity sensitivity
                this.id = this.words[4];
                this.power = Double.parseDouble(this.words[7]);
                this.sensitivity = Double.parseDouble(this.words[9]);
                System.out.println("Variables: ID: " + this.id + " Power: " +
                        this.power + " Sensitivity: " + this.sensitivity);
				SF.Sensor_SonarActive(this.universalWindowManager, this.userInput, this.id, this.power, this.sensitivity);
            }
            else if (this.words[3].equals("passive")) {
                this.id = this.words[4];
                this.sensitivity = Double.parseDouble(this.words[7]);
                System.out.println("Variables: ID: " + this.id + " Sensitivity: " + this.sensitivity);
				SF.Sensor_SonarPassive(this.universalWindowManager,this.userInput,this.id,this.sensitivity);
            }
        }
    }

    public void miscellaneous() {
        // VII. MISCELLANEOUS
        if (this.words[0].charAt(0) == '@'){
            switch(this.words[0]) {
                case "@load":
                    this.fileName = this.words[1];
                    Misc myMiscLoad = new Misc();
                    myMiscLoad.load(universalWindowManager, this.userInput, this.fileName);
                    break;
                case "@pause":
                    Misc myMiscPause = new Misc();
                    myMiscPause.pause(universalWindowManager, this.userInput);
                    break;
                case "@resume":
                    Misc myMiscResume = new Misc();
                    myMiscResume.resume(universalWindowManager, this.userInput);
                    break;
                case "@set":
                    double secondsSet = Double.parseDouble(this.words[2]);
                    Time myTimeSet = new Time(secondsSet);
                    Misc myMiscSet = new Misc();
                    myMiscSet.setUpdateTime(universalWindowManager, this.userInput, myTimeSet);
                    break;
                case "@wait":
                    double secondsWait = Double.parseDouble(this.words[1]);
                    Time myTimeWait = new Time(secondsWait);
                    Misc myMiscWait = new Misc();
                    myMiscWait.waitTime(universalWindowManager, this.userInput, myTimeWait);
                    break;
                case "@force":
                    Actors a = new Actors();
                    Misc myMiscForce = new Misc();
                    this.id = this.words[1];
                    String coordinateValues = this.words[4];
                    this.course = Integer.parseInt(this.words[7]);
                    this.speed = Integer.parseInt(this.words[9]);
                    CoordinateWorld3D cordinate = a.parseActorsCoordinates(coordinateValues);
                    myMiscForce.force(universalWindowManager,this.userInput, new AgentID(this.id), cordinate, new Course(this.course), new Groundspeed(this.speed));
                    break;
                case "@exit":
                    Misc myMiscExit= new Misc();
                    myMiscExit.exit(universalWindowManager, this.userInput);
                    break;
            }
        }
    }

    public void loadKeyWords() {
        keyWords.add("@load");
        keyWords.add("@pause");
        keyWords.add("@resume");
        keyWords.add("@set");
        keyWords.add("@wait");
        keyWords.add("@force");
        keyWords.add("@exit");
        keyWords.add("acoustic");
        keyWords.add("active");
        keyWords.add("actor");
        keyWords.add("airplane");
        keyWords.add("altitude");
        keyWords.add("arming");
        keyWords.add("at");
        keyWords.add("azimuth");
        keyWords.add("bomb");
        keyWords.add("command");
        keyWords.add("coordinates");
        keyWords.add("course");
        keyWords.add("create");
        keyWords.add("define");
        keyWords.add("delete");
        keyWords.add("deploy");
        keyWords.add("depth");
        keyWords.add("depth_charge");
        keyWords.add("distance");
        keyWords.add("elevation");
        keyWords.add("execute");
        keyWords.add("field");
        keyWords.add("fov");
        keyWords.add("from");
        keyWords.add("front");
        keyWords.add("fuze");
        keyWords.add("id");
        keyWords.add("latitude");
        keyWords.add("load");
        keyWords.add("lock");
        keyWords.add("longitude");
        keyWords.add("maneuver");
        keyWords.add("missile");
        keyWords.add("munition");
        keyWords.add("munitions");
        keyWords.add("munition[s]");
        keyWords.add("of");
        keyWords.add("passive");
        keyWords.add("power");
        keyWords.add("radar");
        keyWords.add("sensitivity");
        keyWords.add("sensor");
        keyWords.add("set");
        keyWords.add("side");
        keyWords.add("shell");
        keyWords.add("ship");
        keyWords.add("sonar");
        keyWords.add("speed");
        keyWords.add("state");
        keyWords.add("submarine");
        keyWords.add("thermal");
        keyWords.add("time");
        keyWords.add("to");
        keyWords.add("top");
        keyWords.add("torpedo");
        keyWords.add("trigger");
        keyWords.add("undefine");
        keyWords.add("unlock");
        keyWords.add("update");
        keyWords.add("view");
        keyWords.add("window");
        keyWords.add("with");
    }
}
