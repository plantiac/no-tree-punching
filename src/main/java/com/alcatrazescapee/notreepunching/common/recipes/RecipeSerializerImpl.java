package com.alcatrazescapee.notreepunching.common.recipes;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.Nullable;

/**
 * Platform independent implementation of {@link net.minecraft.world.item.crafting.RecipeSerializer}.
 */
public interface RecipeSerializerImpl<T extends Recipe<?>>// extends RecipeSerializer<T> // todo: extend once IForgeRegistry not in hierarchy
{
    // todo: add override
    //@Override
    default T fromJson(ResourceLocation recipeId, JsonObject json)
    {
        return fromJson(recipeId, json, Context.EMPTY);
    }

    T fromJson(ResourceLocation recipeId, JsonObject json, Context context);

    // todo: remove overrides
    @Nullable
    T fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer);

    void toNetwork(FriendlyByteBuf recipeId, T recipe);

    interface Context
    {
        Context EMPTY = new Context() {};

        default Recipe<?> fromJson(ResourceLocation recipeId, JsonObject json)
        {
            return RecipeManager.fromJson(recipeId, json);
        }
    }
}
