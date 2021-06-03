package cs350s21project.cli;
import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.munition.*;
import cs350s21project.datatype.*;
import cs350s21project.controller.command.actor.*;
//Todo:Not to sure if you need to execute any of these methods so bring that up in the team meeting. -LG june/1
public class Munitions {

	public CommandMunitionDefineBomb Define_Bomb(CommandManagers CM, String textCommand,String id)
	{
		AgentID AG = new AgentID(id);
		CommandMunitionDefineBomb CBomb = new CommandMunitionDefineBomb(CM,textCommand,AG);
		return CBomb;
	}

	public CommandMunitionDefineShell Define_Shell(CommandManagers CM, String textCommand,String id)
	{
		AgentID AG = new AgentID(id);
		CommandMunitionDefineShell CShell = new CommandMunitionDefineShell(CM,textCommand,AG);
		return CShell;
		
	}

	public CommandMunitionDefineDepthCharge DefineDepthCharge(CommandManagers CM, String textCommand,String IDMunition,String IDFuse)
	{
		AgentID AG1 = new AgentID(IDMunition);
		AgentID AG2 = new AgentID(IDFuse);
		CommandMunitionDefineDepthCharge CMDD = new CommandMunitionDefineDepthCharge(CM,textCommand,AG1,AG2);
		return CMDD;
	}

	public CommandMunitionDefineTorpedo DefineTorpedo(CommandManagers CM, String textCommand,String IDMunition,String IDSensor,String IDFuse,double Time1)
	{
		AgentID AG1 = new AgentID(IDMunition);
		AgentID AG2 = new AgentID(IDSensor);
		AgentID AG3 = new AgentID(IDFuse);
		Time time = new Time(Time1);
		CommandMunitionDefineTorpedo CMDT = new CommandMunitionDefineTorpedo(CM,textCommand,AG1,AG2,AG3,time);
		return CMDT;
	}

	public CommandMunitionDefineMissile DefineMissile(CommandManagers CM, String textCommand, String IDMunition,String IDSensor,String IDFuse,double distance)
	{
		AgentID AG1 = new AgentID(IDMunition);
		AgentID AG2 = new AgentID(IDSensor);
		AgentID AG3 = new AgentID(IDFuse);
		DistanceNauticalMiles DNM = new DistanceNauticalMiles(distance);
		CommandMunitionDefineMissile CMDM = new CommandMunitionDefineMissile(CM,textCommand,AG1,AG2,AG3,DNM);
		
		return CMDM;
	}

	public CommandActorLoadMunition LoadMunition(CommandManagers CM, String textCommand,String IDActor,String IDMunition)
	{
		AgentID AG1 = new AgentID(IDActor);
		AgentID AG2 = new AgentID(IDMunition);
		CommandActorLoadMunition CALM = new CommandActorLoadMunition(CM,textCommand,AG1,AG2);
		return CALM;
	}

	public CommandActorDeployMunition DeployMunition(CommandManagers CM, String textCommand,String IDActor,String IDMunition)
	{
		AgentID AG1 = new AgentID(IDActor);
		AgentID AG2 = new AgentID(IDMunition);
		CommandActorDeployMunition CADM = new CommandActorDeployMunition(CM,textCommand,AG1,AG2);
		return CADM;
	}

	public CommandActorDeployMunitionShell DeployMunitionShell(CommandManagers CM , String textCommand,String IDActor,String IDMunition,double azimuth, double elevation)
	{
		AgentID AG1 = new AgentID(IDActor);
		AgentID AG2 = new AgentID(IDMunition);
		AttitudeYaw Azimuth = new AttitudeYaw(azimuth);
		AttitudePitch Elevation = new AttitudePitch(elevation);
		CommandActorDeployMunitionShell CADMS = new CommandActorDeployMunitionShell(CM,textCommand,AG1,AG2,Azimuth,Elevation);
		return CADMS;
	}
}
