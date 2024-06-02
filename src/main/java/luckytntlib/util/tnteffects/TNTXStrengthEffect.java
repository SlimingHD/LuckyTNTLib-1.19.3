package luckytntlib.util.tnteffects;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import luckytntlib.block.LTNTBlock;
import luckytntlib.entity.PrimedLTNT;
import luckytntlib.item.LDynamiteItem;
import luckytntlib.util.IExplosiveEntity;
import luckytntlib.util.explosions.ImprovedExplosion;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.RegistryObject;

/**
 * 
 * TNTXStrengthEffect is an extension of the {@link PrimedTNTEffect} and is an easy way to use an {@link ImprovedExplosion} as a TNT effect.
 * <p>
 * It offers all the customization needed to create small and large explosions for projectiles, TNT and {@link StackedPrimedTNTEffect}.
 */
public class TNTXStrengthEffect extends PrimedTNTEffect{
	
	@Nullable private final Supplier<RegistryObject<LTNTBlock>> TNT;
	@Nullable private final Supplier<RegistryObject<LDynamiteItem>> dynamite;
	private final int fuse;
	private final int strength;
	private final float xzStrength, yStrength;
	private final float resistanceImpact;
	private final float randomVecLength;
	private final boolean fire;
	private final float knockbackStrength;
	private final boolean isStrongExplosion;
	private final float size;
	private final boolean airFuse;
	private final boolean explodesOnImpact;
	
	private TNTXStrengthEffect(@Nullable Supplier<RegistryObject<LTNTBlock>> TNT, @Nullable Supplier<RegistryObject<LDynamiteItem>> dynamite, int fuse, int strength, float xzStrength, float yStrength, float resistanceImpact, float randomVecLength, boolean fire, float knockbackStrength, boolean isStrongExplosion, float size, boolean airFuse, boolean explodesOnImpact) {
		this.TNT = TNT;
		this.dynamite = dynamite;
		this.fuse = fuse;
		this.strength = strength;
		this.xzStrength = xzStrength;
		this.yStrength = yStrength;
		this.resistanceImpact = resistanceImpact;
		this.randomVecLength = randomVecLength;
		this.fire = fire;
		this.knockbackStrength = knockbackStrength;
		this.isStrongExplosion = isStrongExplosion;
		this.size = size;
		this.airFuse = airFuse;
		this.explodesOnImpact = explodesOnImpact;
	}

	@Override
	public void serverExplosion(IExplosiveEntity entity) {
		ImprovedExplosion explosion = new ImprovedExplosion(entity.getLevel(), (Entity) entity, entity.getPos().x, entity.getPos().y + 0.5f, entity.getPos().z, strength);
		explosion.doEntityExplosion(knockbackStrength, true);
		explosion.doBlockExplosion(xzStrength, yStrength, resistanceImpact, randomVecLength, fire, isStrongExplosion);
	}
	
	@Override
	public Block getBlock() {
		return TNT.get().get() == null ? Blocks.TNT : TNT.get().get();
	}
	
	@Override
	public Item getItem() {
		return dynamite.get().get() == null ? Items.AIR : dynamite.get().get();
	}
	
	@Override
	public int getDefaultFuse(IExplosiveEntity entity) {
		return fuse;
	}
	
	@Override
	public float getSize(IExplosiveEntity entity) {
		return size;
	}
		
	@Override
	public boolean airFuse() {
		return airFuse;
	}
	
	@Override
	public boolean explodesOnImpact() {
		return explodesOnImpact;
	}
	
	public static class Builder {
		
		private int fuse = 80;
		private int strength = 4;
		private float xzStrength = 1f, yStrength = 1f;
		private float resistanceImpact = 1f;
		private float randomVecLength = 1f;
		private boolean fire = false;
		private float knockbackStrength = 1f;
		private boolean isStrongExplosion = false;
		private float size = 1f;
		private boolean airFuse = false;
		private boolean explodesOnImpact = true;
		
		public Builder() {			
		}
		
		private Builder(int fuse, int strength, float xzStrength, float yStrength, float resistanceImpact, float randomVecLength, boolean fire, float knockbackStrength, boolean isStrongExplosion, float size, boolean airFuse,  boolean explodesOnImpact) {
			this.fuse = fuse;
			this.strength = strength;
			this.xzStrength = xzStrength;
			this.yStrength = yStrength;
			this.resistanceImpact = resistanceImpact;
			this.randomVecLength = randomVecLength;
			this.fire = fire;
			this.knockbackStrength = knockbackStrength;
			this.isStrongExplosion = isStrongExplosion;
			this.size = size;
			this.airFuse = airFuse;
			this.explodesOnImpact = explodesOnImpact;
		}

		/**
		 * Determines the time in ticks the Explosive will wait before exploding
		 * @implNote defaults to 80 Ticks (4 Seconds)
		 * @param fuse in ticks
		 */
		public Builder fuse(int fuse) {
			return new Builder(fuse, strength, xzStrength, yStrength, resistanceImpact, randomVecLength, fire, knockbackStrength, isStrongExplosion, size, airFuse, explodesOnImpact);
		}
		
		/**
		 * Determines the base strength/radius of the explosion
		 * @implNote defaults to 4 (just like TNT)
		 * @param strength
		 */
		public Builder strength(int strength) {
			return new Builder(fuse, strength, xzStrength, yStrength, resistanceImpact, randomVecLength, fire, knockbackStrength, isStrongExplosion, size, airFuse, explodesOnImpact);
		}
		
		/**
		 * This is a specific value that scales the explosion in the x and z direction
		 * @implNote defaults to 1f
		 * @param xzStrength
		 */
		public Builder xzStrength(float xzStrength) {
			return new Builder(fuse, strength, xzStrength, yStrength, resistanceImpact, randomVecLength, fire, knockbackStrength, isStrongExplosion, size, airFuse, explodesOnImpact);
		}
		
		/**
		 * This is a specific value that scales the explosion in the y direction
		 * @implNote defaults to 1f
		 * @param yStrength
		 */
		public Builder yStrength(float yStrength) {
			return new Builder(fuse, strength, xzStrength, yStrength, resistanceImpact, randomVecLength, fire, knockbackStrength, isStrongExplosion, size, airFuse, explodesOnImpact);
		}
		
		/**
		 * This value determines the impact that explosion resistance of blocks has on the explosion. Higher values equal weaker explosions
		 * @implNote defaults to 1f
		 * @param resistanceImpact
		 */
		public Builder resistanceImpact(float resistanceImpact) {
			return new Builder(fuse, strength, xzStrength, yStrength, resistanceImpact, randomVecLength, fire, knockbackStrength, isStrongExplosion, size, airFuse, explodesOnImpact);
		}
		
		/**
		 * This value is a multiplier to the random vector length of explosion vectors shot by the {@link ImprovedExplosion}, 
		 * which is also affected by the base strength/radius. Higher values give more noisy explosion edges
		 * @implNote defaults to 1f
		 * @param randomVecLength
		 */
		public Builder randomVecLength(float randomVecLength) {
			return new Builder(fuse, strength, xzStrength, yStrength, resistanceImpact, randomVecLength, fire, knockbackStrength, isStrongExplosion, size, airFuse, explodesOnImpact);
		}

		/**
		 * This boolean determines whether or not this explosion should spawn fire, similar to a Ghast fireball
		 * @implNote defaults to false
		 * @param fire
		 */
		public Builder fire(boolean fire) {
			return new Builder(fuse, strength, xzStrength, yStrength, resistanceImpact, randomVecLength, fire, knockbackStrength, isStrongExplosion, size, airFuse, explodesOnImpact);
		}

		/**
		 * This value is a multiplier to the knockback inflicted on entities by the explosion
		 * @implNote defaults to 1f
		 * @param knockbackStrength
		 */
		public Builder knockbackStrength(float knockbackStrength) {
			return new Builder(fuse, strength, xzStrength, yStrength, resistanceImpact, randomVecLength, fire, knockbackStrength, isStrongExplosion, size, airFuse, explodesOnImpact);
		}
		
		/**
		 * This boolean decides whether or not the explosion resistance of fluids should be taken into account.
		 * If turned on, water or lava will have huge effects on the looks of the explosion and will make even the strongest of explosions very tiny
		 * @implNote defaults to false
		 * @param isStrongExplosion
		 */
		public Builder isStrongExplosion(boolean isStrongExplosion) {
			return new Builder(fuse, strength, xzStrength, yStrength, resistanceImpact, randomVecLength, fire, knockbackStrength, isStrongExplosion, size, airFuse, explodesOnImpact);
		}

		/**
		 * This value defines the size of the rendered Block/Item
		 * @implNote defaults to 1f
		 * @param size
		 */
		public Builder size(float size) {
			return new Builder(fuse, strength, xzStrength, yStrength, resistanceImpact, randomVecLength, fire, knockbackStrength, isStrongExplosion, size, airFuse, explodesOnImpact);
		}
		
		/**
		 * This boolean decides whether or not an explosive projectile should be allowed to tick down their fuse in the air.
		 * If enabled, the projectile can explode in the air. Otherwise it will only tick down as long as it is grounded
		 * @implNote only works for explosive projectiles
		 * @implNote defaults to false
		 * @param airFuse
		 */
		public Builder airFuse(boolean airFuse) {
			return new Builder(fuse, strength, xzStrength, yStrength, resistanceImpact, randomVecLength, fire, knockbackStrength, isStrongExplosion, size, airFuse, explodesOnImpact);
		}
		
		/**
		 * This boolean determines whether or not an explosive projectile explodes immediately upon impact.
		 * If enabled, the projectile will ignore any fuse the moment it hits an entity or a block
		 * @implNote only works for explosive projectiles
		 * @implNote defaults to true
		 * @param explodesOnImpact
		 */
		public Builder explodesOnImpact(boolean explodesOnImpact) {
			return new Builder(fuse, strength, xzStrength, yStrength, resistanceImpact, randomVecLength, fire, knockbackStrength, isStrongExplosion, size, airFuse, explodesOnImpact);
		}
		
		/**
		 * Builds a new {@link TNTXStrengthEffect} without a TNT Block or Dynamite Item.
		 * @implNote Should only be used in secondary effects for {@link StackedPrimedTNTEffect}
		 * @return new TNTXStrengthEffect
		 */
		public TNTXStrengthEffect build() {
			return new TNTXStrengthEffect(null, null, fuse, strength, xzStrength, yStrength, resistanceImpact, randomVecLength, fire, knockbackStrength, isStrongExplosion, size, airFuse, explodesOnImpact);
		}
		
		/**
		 * Builds a new {@link TNTXStrengthEffect} with a TNT Block.
		 * @param TNT  a {@link Supplier} of a {@link RegistryObject} of a {@link PrimedLTNT}
		 * @return new TNTXStrengthEffect
		 */
		public TNTXStrengthEffect buildTNT(Supplier<RegistryObject<LTNTBlock>> TNT) {
			return new TNTXStrengthEffect(TNT, null, fuse, strength, xzStrength, yStrength, resistanceImpact, randomVecLength, fire, knockbackStrength, isStrongExplosion, size, airFuse, explodesOnImpact);
		}
		
		/**
		 * Builds a new {@link TNTXStrengthEffect} with a Dynamite Item.
		 * @param dynamite  a {@link Supplier} of a {@link RegistryObject} of a {@link LDynamiteItem}
		 * @return new TNTXStrengthEffect
		 */
		public TNTXStrengthEffect buildDynamite(Supplier<RegistryObject<LDynamiteItem>> dynamite) {
			return new TNTXStrengthEffect(null, dynamite, fuse, strength, xzStrength, yStrength, resistanceImpact, randomVecLength, fire, knockbackStrength, isStrongExplosion, size, airFuse, explodesOnImpact);
		}
	}
}
