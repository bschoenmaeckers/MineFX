package com.superbas11.MineFX;

import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.*;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import com.google.gson.JsonObject;
import com.superbas11.MineFX.util.LogHelper;

public class ServerConnectionHandler {

	protected String Address;
	protected int port;
	private static PrintWriter outToServer;
	public static Thread Updater;
	private Socket clientSocket;
	
	
	public ServerConnectionHandler(String ip,int port){
		this.Address = ip;
		this.port=port;
	}

	public void Connect(){
	    Thread thread = new Thread("MineFX Connection Thread") {

			@Override
	        public void run() {
	            try {
	                //json.addProperty("Action", "Connect");
	                
	                
	                
	                //System.out.println("*********sending json.toString()*********");
	                
	                SocketAddress address = new InetSocketAddress(Address, port);
					clientSocket = new Socket();
	                clientSocket.connect(address, 20000); 
	                
	                MineFX.instance.Connection = clientSocket;
	                outToServer = new PrintWriter(clientSocket.getOutputStream(),true);
	                
	            } catch(Exception ex) {
	                ex.printStackTrace();
	            } finally {
	            	send();
	            }
	        }
	    }; thread.start();	 
	}
	
	public static void send(float Healt,int Hunger){
		LogHelper.debug("sending data");
        JsonObject json = new JsonObject();
		json.addProperty("Healt",Healt);
		json.addProperty("Hunger", Hunger);
		outToServer.print("-"+json.toString());
		outToServer.flush();
	}
	
	public static void send()
	{
		while(true)
		{
			
        	if (Minecraft.getMinecraft().thePlayer != null) {
        		send(Minecraft.getMinecraft().thePlayer.getHealth(),Minecraft.getMinecraft().thePlayer.getFoodStats().getFoodLevel());
        		break;
			}else{
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        	
			
		}
		
	}
}
