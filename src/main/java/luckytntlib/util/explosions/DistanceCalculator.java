package luckytntlib.util.explosions;

import org.joml.Vector3f;

/**
 * The DistanceCalculator is a {@link FunctionalInterface} used to calculate the maximum y distance for a pair of x and z coordinates in the context of explosions.
 * It is mainly used in the {@link ExplosionHelper}.
 */
@FunctionalInterface
public interface DistanceCalculator {
	
	/**
	 * Calculates the absolute maximum y offset from the y-coordinate of the center of the explosion for a specific pair of x and z offsets
	 * @param x  the x offset from the center of the explosion
	 * @param z  the z offset from the center of the explosion
	 * @param radius  the radius of the explosion
	 * @param scaling  a {@link Vector3f} that contains the scalings for all axes
	 * @return the maximum y distance from the xz-plane the explosion it was used to calculate originated from
	 */
	public int getMaxYDistance(int x, int z, int radius, Vector3f scaling);
}
