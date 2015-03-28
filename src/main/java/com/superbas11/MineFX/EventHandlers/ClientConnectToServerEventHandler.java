package com.superbas11.MineFX.EventHandlers;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;
import net.minecraftforge.fml.relauncher.Side;

import com.superbas11.MineFX.ServerConnectionHandler;
import com.superbas11.MineFX.util.LogHelper;

public class ClientConnectToServerEventHandler {
	@SubscribeEvent
	public void ClientConnectToServer(final ClientConnectedToServerEvent event) {
		
		float PrevHealt = 0;
		int PrevHunger = 0;
		
		ServerConnectionHandler.Updater = new Thread("MineFX Data Updater") 
		{
		    public void run() {
		    	if(event.isLocal)
		    	{
		    		LogHelper.info("joining local");
		    		ServerConnectionHandler.send();
		    	}else
		    	{
		    		LogHelper.info("joining extern server");
		    		while(true){
		    			
		    			ServerConnectionHandler.send();
		    			
		    			try {
							sleep(100);
						} catch (InterruptedException e) {
						}
		    		}
		    	}
		    	
		    }
		};
		
		ServerConnectionHandler.Updater.start();
	}
}
