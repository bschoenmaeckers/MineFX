package com.superbas11.MineFX.EventHandlers;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;

import com.superbas11.MineFX.ServerConnectionHandler;
import com.superbas11.MineFX.util.LogHelper;

public class ClientDisconnectToServerEventHandler {
	@SubscribeEvent
	public void ClientDisConnectToServer(ClientDisconnectionFromServerEvent event) {
		ServerConnectionHandler.Updater.interrupt();
		ServerConnectionHandler.send(20,20);
	}

}
