package com.superbas11.MineFX;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerHealEventHandler {
		
	@SubscribeEvent
	public void PlayerHeal(LivingHealEvent event) {
		if(event.entityLiving instanceof EntityPlayer){
			EntityPlayer Player = (EntityPlayer) event.entityLiving;
			if(Player.getDisplayNameString()==Minecraft.getMinecraft().thePlayer.getDisplayNameString()){
				//System.out.println("Player got healed");
			}
		}
	}

}
	
