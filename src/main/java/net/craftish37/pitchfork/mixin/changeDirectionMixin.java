package net.craftish37.pitchfork.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.class_3532;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.class_1297;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ class_1297.class })
public abstract class changeDirectionMixin
{
    @Shadow
    private float field_6031;
    @Shadow
    private float field_5965;
    @Shadow
    public float field_5982;
    @Shadow
    public float field_6004;
    
    @Inject(method = { "changeLookDirection" }, at = { @At("HEAD") }, cancellable = true)
    private void overridePitch(final double cursorDeltaX, final double cursorDeltaY, final CallbackInfo ci) {
        if (this instanceof class_746) {
            if (class_3532.method_15379(this.field_5965) > 180.0f) {
                this.field_5965 = -class_3532.method_17822((double)this.field_5965) * 180 + (this.field_5965 - class_3532.method_17822((double)this.field_5965) * 180);
                this.field_6004 = -class_3532.method_17822((double)this.field_6004) * 180 + (this.field_6004 - class_3532.method_17822((double)this.field_6004) * 180);
            }
            final float changePitch = (float)cursorDeltaY * 0.15f;
            final float changeYaw = (float)cursorDeltaX * 0.15f;
            this.field_5965 += changePitch;
            this.field_6031 += ((class_3532.method_15379(this.field_5965) % 360.0f > 90.0f) ? (-changeYaw) : changeYaw);
            this.field_6004 += changePitch;
            this.field_5982 += ((class_3532.method_15379(this.field_5965) % 360.0f > 90.0f) ? (-changeYaw) : changeYaw);
            ci.cancel();
        }
    }
}
