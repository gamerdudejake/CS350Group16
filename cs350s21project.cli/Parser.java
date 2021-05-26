import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Parser {
    // Custom Variables, not called for in specs
    String command;
    HashSet<String> keyWords = new HashSet<>();
    String userInput;
    String[] words;
    String fileName;

    // Universal Variables
    String id;
    String id2;
    String id3;
    ArrayList<String> idN;

    // Views Variables
    int size;
    String latitude; // TODO: datatype will be Latitude 45*30'15"
    String longitude; // TODO: datatype will be Longitude 110*30'10.3"

    // Actors variables
    int coordinates; // TODO: datatype will be CoordinateWorld3D 45*30'15"/110*30'10"/200
    int course; // TODO: datatype will be Course 090
    int speed; // TODO: datatype will be Groundspeed 25
    int altitude; // TODO: datatype will be Altitude 1000

    // Munitions Variables
    double time;// TODO: datatype will be Time 10.8
    double distance; // TODO: datatype will be DistanceNauticalMiles 25.3
    double azimuth; // TODO: datatype will be AttitudeYaw 10
    double elevation; // TODO: datatype will be AttitudePitch 25

    // Sensors/Fuzes Variables
    double fov;// TODO: datatype will be FieldOfView 10
    double power;// TODO: datatype will be Power 10
    double sensitivity;// TODO: datatype will be Sensitivity 10
    // altitude // already used in Actor
    // distance
    // time

    public void runParser() {
        loadKeyWords();
        getUserInput();
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

    public void getUserInput() {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        this.userInput = scanner.nextLine();
    }

    public void parseInput() {
        this.words = this.userInput.split(" ");
    }

    public void determineCommand() {
        if (this.words[0].charAt(0) == '@') {
            miscellaneous();
        }
        else if (this.words.length >= 3) {
            views();
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
                    // create window id top view with size (latitude1 latitude2 latitude3) (longitude longitude2 longitude3)
                    // TODO: NICK - parse out variables // needs clarification from Luis first
                    System.out.println("Use CommandViewCreateWindowTop");
                    // TODO: Use CommandViewCreateWindowTop
                    break;
                case "delete":
                    // delete window id
                    // TODO: NICK - parse out variables // needs clarification from Luis first
                    System.out.println("Use CommandViewDeleteWindow");
                    // TODO: Use CommandViewDeleteWindow
                    break;
            }
        }
    }

    public void actors() {
        // II. ACTORS
        if (this.words[1].equals("ship")) {
            // define ship id1 with munition[s] (idn+)
            this.id = this.words[2];
            idNParse(5);
            System.out.println("Use CommandActorDefineShip");
            System.out.println("Variables: ID: " + this.id + " IDN: " + this.idN);
            // TODO: Use CommandActorDefineShip
        }
        else if (this.words[1].equals("actor")) {
            // create actor id1 from id2 at coordinates with course course speed speed
            this.id = this.words[2];
            this.id2 = this.words[4];
            this.coordinates = Integer.parseInt(this.words[6]);
            this.course = Integer.parseInt(this.words[9]);
            this.speed = Integer.parseInt(this.words[11]);
            System.out.println("Use CommandActorCreateActor");
            System.out.println("Variables: ID1: " + this.id + " ID2: " + this.id2 + " Coordinates: " +
                    this.coordinates + " Course: " + this.course + " Speed: " + this.speed);
            // TODO: Use CommandActorCreateActor
        }
        else if (this.words[0].equals("set") &&
                (this.words[2].equals("course") || this.words[2].equals("speed")
                        || this.words[2].equals("depth") || this.words[2].equals("altitude"))) {
            switch(this.words[2]) {
                case "course":
                    // set id course course
                    this.id = this.words[1];
                    this.course =  Integer.parseInt(this.words[3]);
                    System.out.println("Use CommandActorSetCourse");
                    System.out.println("Variables: ID: " + this.id + " Course: " + this.course);
                    // TODO: Use CommandActorSetCourse
                    break;
                case "speed":
                    // set id speed speed
                    this.id = this.words[1];
                    this.speed =  Integer.parseInt(this.words[3]);
                    System.out.println("Use CommandActorSetSpeed");
                    System.out.println("Variables: ID: " + this.id + " Speed: " + this.speed);
                    // TODO: Use CommandActorSetSpeed
                    break;
                case "depth" :
                case "altitude" :
                    // set id altitude|depth altitude
                    this.id = this.words[1];
                    this.altitude =  Integer.parseInt(this.words[3]);
                    System.out.println("Use CommandActorSetAltitudeDepth");
                    System.out.println("Variables: ID: " + this.id + " Altitude: " + this.altitude);
                    // TODO: Use CommandActorSetAltitudeDepth
                    break;
            }
        }
    }

    public void munitions() {
        // III. MUNITIONS
        if (this.words[1].equals("munition") || this.words[3].equals("munition")) {
            // define
            switch(this.words[2]) {
                case "bomb":
                    // define munition bomb id
                    this.id = this.words[3];
                    System.out.println("Use CommandMunitionDefineBomb");
                    System.out.println("Variables: ID: " + this.id);
                    // TODO: Use CommandMunitionDefineBomb
                    break;
                case "shell":
                    // define munition shell id
                    this.id = this.words[3];
                    System.out.println("Use CommandMunitionDefineShell");
                    System.out.println("Variables: ID: " + this.id);
                    // TODO: Use CommandMunitionDefineShell
                    break;
                case "depth_charge":
                    // define munition depth_charge id1 with fuze id2
                    this.id = this.words[3];
                    this.id2 = this.words[6];
                    System.out.println("Use CommandMunitionDefineDepthCharge");
                    System.out.println("Variables: ID: " + this.id + " ID2: " + this.id2);
                    // TODO: Use CommandMunitionDefineDepthCharge
                    break;
                case "torpedo":
                    // define munition torpedo id1 with sensor id2 fuze id3 arming time time
                    this.id = this.words[3];
                    this.id2 = this.words[6];
                    this.id3 = this.words[8];
                    this.time = Double.parseDouble(this.words[11]);
                    System.out.println("Use CommandMunitionDefineTorpedo");
                    System.out.println("Variables: ID: " + this.id + " ID2: " + this.id2 + " ID3: " +
                            this.id3 + " Time: " + this.time);
                    // TODO: Use CommandMunitionDefineTorpedo
                    break;
                case "missile":
                    // define munition missile id1 with sensor id2 fuze id3 arming distance distance
                    this.id = this.words[3];
                    this.id2 = this.words[6];
                    this.id3 = this.words[8];
                    this.distance = Double.parseDouble(this.words[11]);
                    System.out.println("Use CommandMunitionDefineMissile");
                    System.out.println("Variables: ID: " + this.id + " ID2: " + this.id2 + " ID3: " +
                            this.id3 + " Distance: " + this.distance);
                    // TODO: Use CommandMunitionDefineMissile
                    break;
            }
            // set
            if (this.words[2].equals("load")) {
                // set id1 load munition id2
                this.id = this.words[1];
                this.id2 = this.words[4];
                System.out.println("Use CommandActorLoadMunition");
                System.out.println("Variables: ID: " + this.id + " ID2: " + this.id2);
                // TODO: Use CommandActorLoadMunition
            }
            else if (this.words[2].equals("deploy") && this.words.length == 5) {
                // set id1 deploy munition id2
                this.id = this.words[1];
                this.id2 = this.words[4];
                System.out.println("Use CommandActorDeployMunition");
                System.out.println("Variables: ID: " + this.id + " ID2: " + this.id2);
                // TODO: Use CommandActorDeployMunition
            }
            else if (this.words[2].equals("deploy") && this.words.length > 5) {
                // set id1 deploy munition id2 at azimuth azimuth elevation elevation
                this.id = this.words[1];
                this.id2 = this.words[4];
                this.azimuth = Double.parseDouble(this.words[7]);
                this.elevation = Double.parseDouble(this.words[9]);
                System.out.println("Use CommandActorDeployMunitionShell");
                System.out.println("Variables: ID: " + this.id + " ID2: " + this.id2 + " Azimuth: " +
                        this.azimuth + " Elevation: " + this.elevation);
                // TODO: Use CommandActorDeployMunitionShell
            }
        }
    }

    public void sensorsFuzes() {
        // IV. SENSORS/FUZES
        if (this.words[1].equals("sensor")) {
            switch(this.words[2]) {
                case "radar":
                    // define sensor radar id with field of view fov power power sensitivity sensitivity
                    this.id = this.words[3];
                    this.fov = Double.parseDouble(this.words[8]);
                    this.power = Double.parseDouble(this.words[10]);
                    this.sensitivity = Double.parseDouble(this.words[12]);
                    System.out.println("Use CommandSensorDefineRadar");
                    System.out.println("Variables: ID: " + this.id + " FOV: " + this.fov + " Power: " +
                            this.power + " Sensitivity: " + this.sensitivity);
                    // TODO: Use CommandSensorDefineRadar
                    break;
                case "thermal":
                    // define sensor thermal id with field of view fov sensitivity sensitivity
                    this.id = this.words[3];
                    this.fov = Double.parseDouble(this.words[8]);
                    this.sensitivity = Double.parseDouble(this.words[10]);
                    System.out.println("Use CommandSensorDefineRadar");
                    System.out.println("Variables: ID: " + this.id + " FOV: " + this.fov +
                            " Sensitivity: " + this.sensitivity);
                    // TODO: Use CommandSensorDefineThermal
                    break;
                case "acoustic":
                    // define sensor acoustic id with sensitivity sensitivity
                    this.id = this.words[3];
                    this.sensitivity = Double.parseDouble(this.words[6]);
                    System.out.println("Use CommandSensorDefineAcoustic");
                    System.out.println("Variables: ID: " + this.id + " Sensitivity: " + this.sensitivity);
                    // TODO: Use CommandSensorDefineAcoustic
                    break;
                case "depth":
                    // define sensor depth id with trigger depth altitude
                    this.id = this.words[3];
                    this.altitude = Integer.parseInt(this.words[7]);
                    System.out.println("Use CommandSensorDefineDepth");
                    System.out.println("Variables: ID: " + this.id + " Depth: " + this.altitude);
                    // TODO: Use CommandSensorDefineDepth
                    break;
                case "distance":
                    // define sensor distance id with trigger distance distance
                    this.id = this.words[3];
                    this.distance = Integer.parseInt(this.words[7]);
                    System.out.println("Use CommandSensorDefineDistance");
                    System.out.println("Variables: ID: " + this.id + " Distance: " + this.distance);
                    // TODO: Use CommandSensorDefineDistance
                    break;
                case "time":
                    // define sensor time id with trigger time time
                    this.id = this.words[3];
                    this.time = Double.parseDouble(this.words[7]);
                    System.out.println("Use CommandSensorDefineTime");
                    System.out.println("Variables: ID: " + this.id + " Time: " + this.time);
                    // TODO: Use CommandSensorDefineTime
                    break;

            }
            if (this.words[3].equals("active")) {
                // define sensor sonar active id with power power sensitivity sensitivity
                this.id = this.words[4];
                this.power = Double.parseDouble(this.words[7]);
                this.sensitivity = Double.parseDouble(this.words[9]);
                System.out.println("Use CommandSensorDefineSonarActive");
                System.out.println("Variables: ID: " + this.id + " Power: " +
                        this.power + " Sensitivity: " + this.sensitivity);
                // TODO: Use CommandSensorDefineSonarActive
            }
            else if (this.words[3].equals("passive")) {
                // define sensor sonar passive id with sensitivity sensitivity
                this.id = this.words[4];
                this.sensitivity = Double.parseDouble(this.words[7]);
                System.out.println("Use CommandSensorDefineSonarPassive");
                System.out.println("Variables: ID: " + this.id + " Sensitivity: " + this.sensitivity);
                // TODO: Use CommandSensorDefineSonarPassive
            }
        }
    }

    public void miscellaneous() {
        // VII. MISCELLANEOUS
        if (this.words[0].charAt(0) == '@'){
            switch(this.words[0]) {
                case "@load":
                    // @load filename
                    this.fileName = this.words[1];
                    System.out.println("Use CommandMiscLoad");
                    System.out.println("Variables: Filename: " + this.fileName);
                    // TODO: Use CommandMiscLoad
                    break;
                case "@pause":
                    // @pause
                    System.out.println("Use CommandMiscPause");
                    // TODO: Use CommandMiscPause
                    break;
                case "@resume":
                    // @resume
                    System.out.println("Use CommandMiscResume");
                    // TODO: Use CommandMiscResume
                    break;
                case "@set":
                    // @set update time
                    this.time = Double.parseDouble(this.words[2]);
                    System.out.println("Use CommandMiscSetUpdate");
                    System.out.println("Variables: Time: " + this.time);
                    // TODO: Use CommandMiscSetUpdate
                    break;
                case "@wait":
                    // @wait time
                    this.time = Double.parseDouble(this.words[1]);
                    System.out.println("Use CommandMiscWait");
                    // TODO: Use CommandMiscWait
                    break;
                case "@force":
                    // @force id state to coordinates with course course speed speed
                    this.id = this.words[1];
                    this.coordinates = Integer.parseInt(this.words[4]);
                    this.course = Integer.parseInt(this.words[7]);
                    this.speed = Integer.parseInt(this.words[9]);
                    System.out.println("Use CommandActorSetState");
                    System.out.println("Variables: ID: " + this.id + " Coordinates: " +
                            this.coordinates + " Course: " + this.course + " Speed: " + this.speed);
                    // TODO: Use CommandActorSetState.
                    break;
                case "@exit":
                    // @exit
                    System.out.println("Use CommandMiscExit");
                    // TODO: Use CommandMiscExit
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