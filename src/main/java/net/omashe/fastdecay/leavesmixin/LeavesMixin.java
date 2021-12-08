package net.omashe.fastdecay.leavesmixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

/**
 * Copyright 2021 BradBot_1
 *
 * Licensed under the Apache License, Version 2.0 (the "License", "");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@Mixin(LeavesBlock.class)
public abstract class LeavesMixin {

	@Shadow public abstract void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random);

	@Inject(method = "scheduledTick", at = @At("TAIL"))
	public void forceScheduledTickToCallRandomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo callbackInfo) {
		randomTick(state, world, pos, random);
	}

}
