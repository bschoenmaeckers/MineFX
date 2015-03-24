package com.superbas11.MineFX;

import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.*;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import com.google.gson.JsonObject;

public class ConnectToServer {

	protected String Address;
	protected int port;
	private Thread Thread;
	
	
	public ConnectToServer(String ip,int port){
		this.Address = ip;
		this.port=port;
	}

	public Thread Connect(){
	    Thread thread = new Thread() {
	        @Override
	        public void run() {
	            try {
	                JsonObject json = new JsonObject();
	                //json.addProperty("Action", "Connect");
	                
	                
	                
	                //System.out.println("*********sending json.toString()*********");
	                
	                SocketAddress address = new InetSocketAddress(Address, port);
					Socket clientSocket = new Socket();
	                clientSocket.connect(address, 20000); 
	                
	                MineFX.Connection = clientSocket;
	                
	                
	                PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(),true);
	                //BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	                //outToServer.writeBytes(json.toString() + '\n');
	                
	                while(true){
	                	//System.out.println("...");
	                	if (Minecraft.getMinecraft().thePlayer != null) {
	                		//System.out.println("sending data");
							json.addProperty("Healt",Minecraft.getMinecraft().thePlayer.getHealth() / Minecraft.getMinecraft().thePlayer.getMaxHealth() * 20);
							json.addProperty("Hunger", Minecraft.getMinecraft().thePlayer.getFoodStats().getFoodLevel());
							outToServer.print("-"+json.toString());
							outToServer.flush();
						}else{
							//System.out.println("...");
						}
	                	sleep(100);
	                }
	                //clientSocket.close();
	            } catch(Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	    }; thread.start();
	    
	    Thread = thread;
	    return thread;
	}
}
