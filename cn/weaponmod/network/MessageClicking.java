/** 
 * Copyright (c) Lambda Innovation Team, 2013
 * 版权许可：LambdaCraft 制作小组， 2013.
 * http://lambdacraft.cn/
 * 
 * The mod is open-source. It is distributed under the terms of the
 * Lambda Innovation Open Source License. It grants rights to read, modify, compile
 * or run the code. It does *NOT* grant the right to redistribute this software
 * or its modifications in any form, binary or source, except if expressively
 * granted by the copyright holder.
 *
 * 本Mod是完全开源的，你允许参考、使用、引用其中的任何代码段，但不允许将其用于商业用途，在引用的时候，必须注明原作者。
 */
package cn.weaponmod.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cn.weaponmod.WeaponMod;
import cn.weaponmod.api.feature.ISpecialUseable;
import cn.weaponmod.events.ItemControlHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * Message for player control keys.
 * @author WeAthFolD
 */
public class MessageClicking implements IMessage {
	
	public int type;

	/**
	 * 1 : leftUse, 2 : leftStop
	 * -1 : rightUse, -2 : rightStop
	 * @param type
	 * @param additional
	 */
	public MessageClicking(int type) {
		this.type = type;
	}
	
	public MessageClicking() {}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		type = buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(type);
	}
	
	public static class Handler implements IMessageHandler<MessageClicking, IMessage> {

		@Override
		public IMessage onMessage(MessageClicking message, MessageContext ctx) {
			int type = message.type;
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			boolean wrong = false;
			
			if(type == 1 || type == -1) { //use
				boolean left = type > 0;
				ItemStack stack = player.getCurrentEquippedItem();
				if(stack != null) {
					Item item = stack.getItem();
					if(item instanceof ISpecialUseable) {
						((ISpecialUseable)item).onItemClick(player.worldObj, player, stack, left);
					} else wrong = true;
				} else wrong = true;
				
			} else { //stop
				
				ItemControlHandler.stopUsingItem(player, type > 0);
				
			}
			if(wrong)
				WeaponMod.log.info("Coudn't find the correct player-controlling Item instance...");
			return null;
		}
		
	}
	
}
