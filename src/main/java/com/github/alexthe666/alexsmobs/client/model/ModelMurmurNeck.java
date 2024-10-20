package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.LivingEntity;

public class ModelMurmurNeck extends AdvancedEntityModel<LivingEntity> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox neckPivot;
    private final AdvancedModelBox neck;
    private float stretch;
    public static boolean THIN = false;
    public static boolean HIDE = false;

    public ModelMurmurNeck() {
        texWidth = 128;
        texHeight = 128;
        root = new AdvancedModelBox(this, "root");
        root.setRotationPoint(0.0F, 12.0F, 0.0F);
        neckPivot = new AdvancedModelBox(this, "neckPivot");
        neckPivot.setRotationPoint(0.0F, 0.0F, 0.0F);
        root.addChild(neckPivot);
        neck = new AdvancedModelBox(this, "neck");
        neck.setRotationPoint(0.0F, 0.0F, 0.0F);
        neck.setTextureOffset(0, 60).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 16.0F, 4.0F, 0.0F, false);
        neckPivot.addChild(neck);
        this.updateDefaultPose();
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(root, neckPivot, neck);
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(root);
    }

    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    
    public void setAttributes(float f, float rotX, float rotY, float additionalYaw){
        this.resetToDefaultPose();
        this.stretch = f;
        float f1 = THIN ? 0.75F : 1.0F;
        this.neck.setScale(f1, this.stretch, f1);
        this.neckPivot.rotateAngleX = (float)Math.toRadians(rotX);
        this.neckPivot.rotateAngleY = (float)Math.toRadians(rotY);
        this.neck.rotateAngleY = (float)Math.toRadians(-additionalYaw);
        this.neckPivot.showModel = !HIDE;
        this.root.showModel = !HIDE;
        this.neck.showModel = !HIDE;
    }
}
