package com.elecvrsn.BraveBunnies;

import com.mojang.logging.LogUtils;
import mokiyoki.enhancedanimals.ai.general.*;
import mokiyoki.enhancedanimals.ai.rabbit.EnhancedRabbitPanicGoal;
import mokiyoki.enhancedanimals.entity.EnhancedRabbit;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.slf4j.Logger;

import java.util.LinkedHashSet;
import java.util.Set;

@Mod("bravebunnies")
public class BraveBunnies
{

    private static final Logger LOGGER = LogUtils.getLogger();
    public BraveBunnies()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void removeRabbitGoals(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EnhancedRabbit) {
            EnhancedRabbit rabbitEntity = (EnhancedRabbit)(event.getEntity());
            Set<WrappedGoal> availableGoals = rabbitEntity.goalSelector.getAvailableGoals();
            Set<WrappedGoal> goalsToKeep = new LinkedHashSet<WrappedGoal>();
            for (WrappedGoal goal : availableGoals) {
                if (goal.getGoal() instanceof EnhancedAvoidEntityGoal) {
                    //LOGGER.info("SKIPPING FEAR");
                } else {
                    goalsToKeep.add(goal);
                }
            }
            rabbitEntity.goalSelector.removeAllGoals();
            for (WrappedGoal goal : goalsToKeep) {
                rabbitEntity.goalSelector.addGoal(goal.getPriority(), goal.getGoal());
            }
        }
    }

}
