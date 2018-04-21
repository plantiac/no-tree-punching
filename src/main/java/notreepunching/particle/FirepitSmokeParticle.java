package notreepunching.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class FirepitSmokeParticle extends Particle {

    public FirepitSmokeParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn){
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

        this.motionX = xSpeedIn;
        this.motionY = ySpeedIn;
        this.motionZ = zSpeedIn;

        float f = (float)(Math.random() * 0.30000001192092896D);
        this.particleRed = f;
        this.particleGreen = f;
        this.particleBlue = f;

        this.setParticleTextureIndex(1);

        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
        this.particleAge = particleMaxAge;
    }

    @Override
    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        move(motionX, motionY, motionZ);

        this.setParticleTextureIndex(1+6*this.particleAge/this.particleMaxAge);
        this.motionY *= 0.97d;
        if (this.particleAge-- <= 0) {
            this.setExpired();
        }
    }
}

