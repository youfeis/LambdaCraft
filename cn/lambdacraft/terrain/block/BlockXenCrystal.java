package cn.lambdacraft.terrain.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
/**
 * Xen水晶方块 掉落水晶材料
 * @author F
 *
 */
public class BlockXenCrystal extends Block {

	public BlockXenCrystal() {
		super(Material.rock);
		this.setBlockName("xencrystal");
		this.setBlockTextureName("lambdacraft:xen_crystal");
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeStone);
		this.setLightLevel(0.5F);
		this.setHarvestLevel("pickaxe", 1);
	}

}
