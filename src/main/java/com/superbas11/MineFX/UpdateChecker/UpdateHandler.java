package com.superbas11.MineFX.UpdateChecker;

import com.superbas11.MineFX.reference.Reference;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class UpdateHandler {
	
	private static NBTTagCompound UpdateInfo;
	
	public static void init(){
		UpdateInfo = new NBTTagCompound();
		UpdateInfo.setString("curseProjectName", "228870-minefx");
		UpdateInfo.setString("curseFilenameParser", "MineFX-[].jar");
		
		FMLInterModComms.sendRuntimeMessage(Reference.MOD_ID, "VersionChecker", "addCurseCheck", UpdateInfo);
	}
	

}
