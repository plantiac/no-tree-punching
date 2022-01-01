/*
 * Part of the No Tree Punching mod by AlcatrazEscapee.
 * Work under copyright. See the project LICENSE.md for details.
 */

package com.alcatrazescapee.notreepunching;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.ForgeRegistries;

import com.alcatrazescapee.notreepunching.common.blocks.ModBlocks;
import com.alcatrazescapee.notreepunching.common.blocks.PotteryBlock;

public final class Config
{
    public static final CommonConfig COMMON = register(ModConfig.Type.COMMON, CommonConfig::new);
    public static final ServerConfig SERVER = register(ModConfig.Type.SERVER, ServerConfig::new);

    public static void init() {}

    private static <T> T register(ModConfig.Type type, Function<ForgeConfigSpec.Builder, T> factory)
    {
        Pair<T, ForgeConfigSpec> configPair = new ForgeConfigSpec.Builder().configure(factory);
        ModLoadingContext.get().registerConfig(type, configPair.getRight());
        return configPair.getLeft();
    }

    public static final class CommonConfig
    {
        public final ForgeConfigSpec.BooleanValue enableLooseRocksWorldGen;

        private CommonConfig(ForgeConfigSpec.Builder builder)
        {
            enableLooseRocksWorldGen = builder.comment("Enables loose rock world gen added automatically to biomes.", "Note: Requires a world restart to take effect!").define("enableLooseRocksWorldGen", true);
        }
    }

    public static final class ServerConfig
    {
        public final ForgeConfigSpec.BooleanValue noMiningWithoutCorrectTool;
        public final ForgeConfigSpec.BooleanValue noBlockDropsWithoutCorrectTool;
        public final ForgeConfigSpec.BooleanValue doInstantBreakBlocksRequireTool;
        public final ForgeConfigSpec.BooleanValue doInstantBreakBlocksDropWithoutCorrectTool;
        public final ForgeConfigSpec.BooleanValue doInstantBreakBlocksDamageKnives;
        public final ForgeConfigSpec.DoubleValue flintKnappingConsumeChance;
        public final ForgeConfigSpec.DoubleValue flintKnappingSuccessChance;
        public final ForgeConfigSpec.DoubleValue fireStarterFireStartChance;
        public final ForgeConfigSpec.BooleanValue fireStarterCanMakeCampfire;
        public final ForgeConfigSpec.BooleanValue fireStarterCanMakeSoulCampfire;
        public final ForgeConfigSpec.BooleanValue largeVesselKeepsContentsWhenBroken;

        private final ForgeConfigSpec.ConfigValue<List<? extends String>> potteryBlockSequences;

        private ServerConfig(ForgeConfigSpec.Builder builder)
        {
            noMiningWithoutCorrectTool = builder.comment("Makes blocks take forever to mine if using the wrong tool").define("noMiningWithoutCorrectTool", true);
            noBlockDropsWithoutCorrectTool = builder.comment("Makes blocks not drop anything when broken with the wrong tool").define("noBlockDropsWithoutCorrectTool", true);

            doInstantBreakBlocksRequireTool = builder.comment("Makes blocks that would otherwise be broken instantly unbreakable if using the wrong tool.").define("doInstantBreakBlocksRequireTool", false);
            doInstantBreakBlocksDropWithoutCorrectTool = builder.comment("Makes blocks that would otherwise be broken instantly still drop, even when using an incorrect tool.").define("doInstantBreakBlocksDropWithoutCorrectTool", true);
            doInstantBreakBlocksDamageKnives = builder.comment("If blocks such as tall grass which break instantly consume durability when broken with a knife (only affects No Tree Punching knives)").define("doInstantBreakBlocksDamageKnives", true);

            flintKnappingConsumeChance = builder.comment("The chance to consume a piece of flint when knapping").defineInRange("flintKnappingConsumeChance", 0.4, 0, 1);
            flintKnappingSuccessChance = builder.comment("The chance to produce flint shards if a piece of flint has been consumed while knapping").defineInRange("flintKnappingSuccessChance", 0.7, 0, 1);

            fireStarterFireStartChance = builder.comment("The chance for a fire starter to start fires").defineInRange("fireStarterFireStartChance", 0.3, 0, 1);
            fireStarterCanMakeCampfire = builder.comment("If the fire starter can be used to make a campfire (with one '#notreepunching:fire_starter_logs' and three '#notreepunching:fire_starter_kindling'").define("fireStarterCanMakeCampfire", true);
            fireStarterCanMakeSoulCampfire = builder.comment("If the fire starter can be used to make a soul campfire (with one '#notreepunching:fire_starter_logs', three '#notreepunching:fire_starter_kindling', and one '#notreepunching:fire_starter_soul_fire_catalyst'").define("fireStarterCanMakeSoulCampfire", true);

            largeVesselKeepsContentsWhenBroken = builder.comment("If the large ceramic vessel block keeps it's contents when broken (as opposed to dropping them on the ground").define("largeVesselKeepsContentsWhenBroken", true);

            potteryBlockSequences = builder.comment(
                "The sequence of blocks that can be created with the clay tool.",
                "When the clay tool is used, if the block is present in this list, it may be converted to the next block in the list",
                "If the next block is minecraft:air, the block will be destroyed (the clay tool will never try and convert air into something)"
            ).defineList("potteryBlockSequences", defaultPotteryBlockSequence(), e -> e instanceof String && ForgeRegistries.BLOCKS.containsKey(new ResourceLocation((String) e)));
        }

        public List<Block> getPotteryBlockSequences()
        {
            return potteryBlockSequences.get()
                .stream()
                .map(id -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id)))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        }

        private List<? extends String> defaultPotteryBlockSequence()
        {
            final List<String> values = new ArrayList<>();
            values.add("minecraft:clay");
            for (PotteryBlock.Variant pottery : PotteryBlock.Variant.values())
            {
                values.add(ModBlocks.POTTERY.get(pottery).getId().toString());
            }
            values.add("minecraft:air");
            return values;
        }
    }
}