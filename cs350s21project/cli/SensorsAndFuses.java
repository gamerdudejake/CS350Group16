package cs350s21project.cli;
import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.sensor.*;
import cs350s21project.datatype.*;
public class SensorsAndFuses {
	
	// (CM,String Commandtext,String IDSensor,double fov,double power,double sensitivity)
	public void Sensor_Radar(CommandManagers CM, String text, String IDSensor, double fov, double power, double sensitivity)
	{
		AgentID AG1 = new AgentID(IDSensor);
		AngleNavigational limit = new AngleNavigational(fov);
		FieldOfView FOV = new FieldOfView(limit);
		Power POWER = new Power(power);
		Sensitivity SENSITIVITY = new Sensitivity(sensitivity);
		CommandSensorDefineRadar CSDR = new CommandSensorDefineRadar(CM,text,AG1,FOV,POWER,SENSITIVITY);
		CSDR.execute();
	}
	// (CM,String Commandtext,String IDSensor,double fov,double sensitivity)
	public void Sensor_Thermal(CommandManagers CM,String text, String IDSensor, double fov, double sensitivity)
	{
		AgentID AG1 = new AgentID(IDSensor);
		AngleNavigational limit = new AngleNavigational(fov);
		FieldOfView FOV = new FieldOfView(limit);
		Sensitivity SENSITIVITY = new Sensitivity(sensitivity);
		CommandSensorDefineThermal CSDT = new CommandSensorDefineThermal(CM,text,AG1,FOV,SENSITIVITY);
		CSDT.execute();
	}
	//(CM,String CommandText,String IDSensor,double sensitivity)
	public void Sensor_Acoustic(CommandManagers CM, String text, String IDSensor, double sensitivity)
	{
		AgentID AG1 = new AgentID(IDSensor);
		Sensitivity SENSITIVITY = new Sensitivity(sensitivity);
		CommandSensorDefineAcoustic  CSDA = new CommandSensorDefineAcoustic(CM,text,AG1,SENSITIVITY);
		CSDA.execute();
	}
	//(CM,String CommandText,String IDSensor,double power,double sensitivity)
	public void Sensor_SonarActive(CommandManagers CM, String text, String IDSensor, double power, double sensitivity)
	{
		AgentID AG1 = new AgentID(IDSensor);
		Power POWER = new Power(power);
		Sensitivity SENSITIVITY = new Sensitivity(sensitivity);
		CommandSensorDefineSonarActive CSDSA = new CommandSensorDefineSonarActive(CM,text,AG1,POWER,SENSITIVITY);
		CSDSA.execute();
	}
	//(CM,String CommandText,String IDSensor,double sensitivity)
	public void Sensor_SonarPassive(CommandManagers CM,String text, String IDSensor,double sensitivity)
	{
		AgentID AG1 = new AgentID(IDSensor);
		Sensitivity SENSITIVITY = new Sensitivity(sensitivity);
		CommandSensorDefineSonarPassive CSDSP = new CommandSensorDefineSonarPassive(CM,text,AG1,SENSITIVITY);
		CSDSP.execute();
	}
	//(CM,String CommandText,String IDSensor, double altitude)
	public void Sensor_Depth(CommandManagers CM,String text,String IDSensor,double altitude)
	{
		AgentID AG1 = new AgentID(IDSensor);
		Altitude Depth = new Altitude(altitude);
		CommandSensorDefineDepth CSDD = new CommandSensorDefineDepth(CM,text,AG1,Depth);
		CSDD.execute();
	}
	//(CM,String CommandText,String IDSensor, double distance)
	public void Sensor_Distance(CommandManagers CM,String text,String IDSensor,double distance)
	{
		AgentID AG1 = new AgentID(IDSensor);
		DistanceNauticalMiles Distance = new DistanceNauticalMiles(distance);
		CommandSensorDefineDistance CSDD = new CommandSensorDefineDistance(CM,text,AG1,Distance);
		CSDD.execute();
	}
	//(CM,String CommandText,String IDSensor,double time) 
	public void Sensor_Time(CommandManagers CM,String text,String IDSensor,double time)
	{
		AgentID AG1 = new AgentID(IDSensor);
		Time TIME = new Time(time);
		CommandSensorDefineTime CSDT = new CommandSensorDefineTime(CM,text,AG1,TIME);
		CSDT.execute();
	}
}
