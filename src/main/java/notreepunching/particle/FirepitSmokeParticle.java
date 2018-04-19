package notreepunching.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class FirepitSmokeParticle extends Particle {

    public FirepitSmokeParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn){
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

        //this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
        //setSize(0.6f,0.6f);

        this.motionX = xSpeedIn;
        this.motionY = ySpeedIn;
        this.motionZ = zSpeedIn;

        this.setParticleTextureIndex(7);

        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
        this.particleAge = particleMaxAge;

        //this.posX += 0.2*rand.nextFloat()-0.1;
        //this.posZ += 0.2*rand.nextFloat()-0.1;

        //prevPosX = posX;
        //prevPosY = posY;
        //prevPosZ = posZ;

        //particleMaxAge += 80;
    }

    @Override
    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        move(motionX, motionY, motionZ);  // simple linear motion.  You can change speed by changing motionX, motionY,
        // motionZ every tick.  For example - you can make the particle accelerate downwards due to gravity by
        // final double GRAVITY_ACCELERATION_PER_TICK = -0.02;
        // motionY += GRAVITY_ACCELERATION_PER_TICK;

        // collision with a block makes the ball disappear.  But does not collide with entities
        //if (onGround) {  // onGround is only true if the particle collides while it is moving downwards...
        //    this.setExpired();
        //}

        //if (prevPosY == posY && motionY > 0) {  // detect a collision while moving upwards (can't move up at all)
        //    this.setExpired();
        //}
        this.setParticleTextureIndex(7-6*this.particleAge/this.particleMaxAge);
        this.motionY *= 0.97d;
        if (this.particleAge-- <= 0) {
            this.setExpired();
        }
    }
}

