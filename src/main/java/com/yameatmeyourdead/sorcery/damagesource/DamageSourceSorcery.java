package com.yameatmeyourdead.sorcery.damagesource;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class DamageSourceSorcery extends DamageSource {

    public static DamageSource demonic = new DamageSourceSorcery("demonic").bypassArmor().setMagic();
    public static DamageSource magic = new DamageSourceSorcery("magic").setMagic();
    public static DamageSource obama = new DamageSourceSorcery("obama").bypassArmor().bypassInvul().bypassMagic().setMagic();

    protected DamageSourceSorcery(String p_i1566_1_) {
        super(p_i1566_1_);
    }
    
    public static DamageSource causeDemonicDamage(LivingEntity source) {
        return new EntityDamageSource("demonic", source);
    }

    public static DamageSource causeMagicDamage(LivingEntity source) {
        return new EntityDamageSource("magic", source);
    }

    public static DamageSource causeObamaDamage(LivingEntity source) {
        return new EntityDamageSource("obama", source);
    }
}
