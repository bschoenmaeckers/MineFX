package com.superbas11.MineFX;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class PlayerHurtEventHandler {
	
	@SubscribeEvent
	public void PlayerHurt(LivingHurtEvent event) {
		if(event.entityLiving instanceof EntityPlayer){
			EntityPlayer Player = (EntityPlayer) event.entityLiving;
			if(Player.getDisplayNameString()==Minecraft.getMinecraft().thePlayer.getDisplayNameString()){
				//System.out.println("Player got hurt.");
			}
		}
	}

}
