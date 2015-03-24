package com.superbas11.MineFX;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

import com.superbas11.MineFX.reference.Reference;
import com.superbas11.MineFX.util.Downloader;
import com.superbas11.MineFX.util.LogHelper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Reference.MOD_ID,name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory=Reference.GUI_FACTORY_CLASS)
public class MineFX {

	public static Socket Connection;
	
    // The instance of your mod that Forge uses.
    @Instance(Reference.MOD_ID)
    public static MineFX instance;

	private Process FXServer;
    protected InputStream in;
    protected OutputStream out;
    protected InputStream err;
    protected Thread Logger;
    private String configPath = "config/";
	
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    	//make config dirs
    	configPath = event.getModConfigurationDirectory().getPath()+"/"+Reference.MOD_ID+"/";
    	File configFile = new File(new File(configPath), Reference.MOD_ID+".cfg");
    	new File(configPath).mkdirs();
    	
    	//download proxy
    	//TODO
    	if(!configFile.exists())
    	{
    		try {
				Downloader.Download("http://www.inter.net/download", configPath);
			} catch (IOException e) {
				LogHelper.error("Error on downloading LightFX proxy from server!");
				e.printStackTrace();
			}
    	}
    	
    	//init config handler
    	ConfigurationHandler.init(configFile);
    	FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
    	
    	//start proxy
    	try {
    		FXServer = Runtime.getRuntime().exec(ConfigurationHandler.Proxy_FileName, null, new File(configPath));
    	    in = FXServer.getInputStream();
    	    out = FXServer.getOutputStream();
    	    err = FXServer.getErrorStream();
    	    
    	    Logger = new Thread("MineFX Logger Thread") {
    	    	public void run(){
    	    		System.out.println("Starting debug logger");
    	    		
    	    			try {
    	    				
    	    				// Read console stream
    	    	            final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    	    	            
    	    	            String line = null;
    	    	            while ((line = reader.readLine()) != null) {
    	    	                System.out.println(line);
    	    	            }
    	    	            reader.close();
    	    	            
    	    	            
						} catch (IOException e) {
							e.printStackTrace();
						}
    	    	}
    	    };
    	    
    	    //start Logger
    	    if(ConfigurationHandler.DebugLog){
    	    	Logger.start();
    	    }
    	    
    		} catch (IOException e) {
			e.printStackTrace();
		}
    	LogHelper.info("PreInitialization complete!");
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    	//connect to proxy
    	ConnectToServer Connection = new ConnectToServer(ConfigurationHandler.Proxy_IP, ConfigurationHandler.Proxy_port);
    	Connection.Connect();    	       
    	
    	//register events
    	MinecraftForge.EVENT_BUS.register(new PlayerHurtEventHandler());
    	MinecraftForge.EVENT_BUS.register(new PlayerHealEventHandler());
    	
    	//close connection when minecraft closes
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Closing MineFX connection...");
                try {
					MineFX.instance.Connection.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
    	
    	LogHelper.info("Post Initialization complete!");
    }
	
}
