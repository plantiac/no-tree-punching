/*
 * Part of the No Tree Punching mod by AlcatrazEscapee.
 * Work under copyright. See the project LICENSE.md for details.
 */

package com.alcatrazescapee.notreepunching.common.recipes;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.alcatrazescapee.notreepunching.NoTreePunching;

public class ModRecipes
{
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, NoTreePunching.MOD_ID);

    public static final RegistryObject<ShapedToolDamagingRecipe.Serializer> TOOL_DAMAGING = RECIPE_SERIALIZERS.register("tool_damaging", ShapedToolDamagingRecipe.Serializer::new);
}