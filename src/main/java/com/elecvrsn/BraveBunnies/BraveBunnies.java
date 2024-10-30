package com.elecvrsn.BraveBunnies;

import mokiyoki.enhancedanimals.ai.general.EnhancedAvoidEntityGoal;
import mokiyoki.enhancedanimals.entity.EnhancedRabbit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("bravebunnies")
public class BraveBunnies
{

//    private static final Logger LOGGER = LogUtils.getLogger();
    public BraveBunnies()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void removeRabbitGoals(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof EnhancedRabbit) {
            EnhancedRabbit rabbitEntity = (EnhancedRabbit)(event.getEntity());
            rabbitEntity.goalSelector.removeAllGoals(l -> l instanceof EnhancedAvoidEntityGoal);
        }
    }

}
