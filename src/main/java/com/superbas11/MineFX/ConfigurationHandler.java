package com.superbas11.MineFX;

import java.io.File;

import com.superbas11.MineFX.reference.Reference;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {
	
	public static Configuration configuration;
	public static Boolean DebugLog;
	public static String Proxy_FileName;
	public static String Proxy_IP;
	public static int Proxy_port;
	
	public static void init(File configFile)
	{
		// Create the configuration object from the given configuration file
		if(configuration == null)
		{
			configuration = new Configuration(configFile);
			loadConfiguration();
		}		
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent (ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.modID.equalsIgnoreCase(Reference.MOD_ID))
		{
			loadConfiguration();
		}
	}
	
	private static void loadConfiguration()
	{
		DebugLog = configuration.getBoolean("Debug log", Configuration.CATEGORY_GENERAL, false, "show debug info/light events in console.");
		Proxy_FileName = configuration.getString("File name", "LightFX Proxy", "MineFX.exe", "File name of the LightFX proxy executible located in config/MineFX.");
		Proxy_IP = configuration.getString("Proxy IP", "LightFX Proxy", "127.0.0.1", "");
		Proxy_port = configuration.getInt("Proxy Port", "LightFX Proxy", 3000, 1, 65535, "");

        if (configuration.hasChanged())
        {
            configuration.save();
        }
	}
}
