package cs350s21project.cli;
import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.munition.*;
import cs350s21project.datatype.*;
import cs350s21project.controller.command.actor.*;
//Todo:Not to sure if you need to execute any of these methods so bring that up in the team meeting. -LG june/1
public class Munitions {

	public void Define_Bomb(CommandManagers CM, String textCommand,String id)
	{
		AgentID AG = new AgentID(id);
		CommandMunitionDefineBomb CBomb = new CommandMunitionDefineBomb(CM,textCommand,AG);
		CBomb.execute();
	}

	public void Define_Shell(CommandManagers CM, String textCommand,String id)
	{
		AgentID AG = new AgentID(id);
		CommandMunitionDefineShell CShell = new CommandMunitionDefineShell(CM,textCommand,AG);
		CShell.execute();
	}

	public void DefineDepthCharge(CommandManagers CM, String textCommand,String IDMunition,String IDFuse)
	{
		AgentID AG1 = new AgentID(IDMunition);
		AgentID AG2 = new AgentID(IDFuse);
		CommandMunitionDefineDepthCharge CMDD = new CommandMunitionDefineDepthCharge(CM,textCommand,AG1,AG2);
		CMDD.execute();
	}

	public void DefineTorpedo(CommandManagers CM, String textCommand,String IDMunition,String IDSensor,String IDFuse,double Time1)
	{
		AgentID AG1 = new AgentID(IDMunition);
		AgentID AG2 = new AgentID(IDSensor);
		AgentID AG3 = new AgentID(IDFuse);
		Time time = new Time(Time1);
		CommandMunitionDefineTorpedo CMDT = new CommandMunitionDefineTorpedo(CM,textCommand,AG1,AG2,AG3,time);
		CMDT.execute();
	}

	public void DefineMissile(CommandManagers CM, String textCommand, String IDMunition,String IDSensor,String IDFuse,double distance)
	{
		AgentID AG1 = new AgentID(IDMunition);
		AgentID AG2 = new AgentID(IDSensor);
		AgentID AG3 = new AgentID(IDFuse);
		DistanceNauticalMiles DNM = new DistanceNauticalMiles(distance);
		CommandMunitionDefineMissile CMDM = new CommandMunitionDefineMissile(CM,textCommand,AG1,AG2,AG3,DNM);
		CMDM.execute();
	}

	public void LoadMunition(CommandManagers CM, String textCommand,String IDActor,String IDMunition)
	{
		AgentID AG1 = new AgentID(IDActor);
		AgentID AG2 = new AgentID(IDMunition);
		CommandActorLoadMunition CALM = new CommandActorLoadMunition(CM,textCommand,AG1,AG2);
		CALM.execute();
	}

	public void DeployMunition(CommandManagers CM, String textCommand,String IDActor,String IDMunition)
	{
		AgentID AG1 = new AgentID(IDActor);
		AgentID AG2 = new AgentID(IDMunition);
		CommandActorDeployMunition CADM = new CommandActorDeployMunition(CM,textCommand,AG1,AG2);
		CADM.execute();
	}

	public void DeployMunitionShell(CommandManagers CM , String textCommand,String IDActor,String IDMunition,double azimuth, double elevation)
	{
		AgentID AG1 = new AgentID(IDActor);
		AgentID AG2 = new AgentID(IDMunition);
		AttitudeYaw Azimuth = new AttitudeYaw(azimuth);
		AttitudePitch Elevation = new AttitudePitch(elevation);
		CommandActorDeployMunitionShell CADMS = new CommandActorDeployMunitionShell(CM,textCommand,AG1,AG2,Azimuth,Elevation);
		CADMS.execute();
	}
}
