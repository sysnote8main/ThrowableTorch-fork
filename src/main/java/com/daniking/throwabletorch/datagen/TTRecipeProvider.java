package com.daniking.throwabletorch.datagen;

import com.daniking.throwabletorch.ObjectRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class TTRecipeProvider extends FabricRecipeProvider {
    public TTRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        createTorchRecipe(ObjectRegistry.THROWABLE_CLAY_TORCH_ITEM, Items.CLAY_BALL).offerTo(exporter);
        createTorchRecipe(ObjectRegistry.THROWABLE_HONEYCOMB_TORCH_ITEM, Items.HONEYCOMB).offerTo(exporter);
        createTorchRecipe(ObjectRegistry.THROWABLE_MAGMA_TORCH_ITEM, Items.MAGMA_CREAM).offerTo(exporter);
        createTorchRecipe(ObjectRegistry.THROWABLE_SLIME_TORCH_ITEM, Items.SLIME_BALL).offerTo(exporter);
    }

    private static ShapedRecipeJsonBuilder setHaveItemCriterion(ShapedRecipeJsonBuilder builder, Item... items) {
        for (Item item : items) {
            builder.criterion(hasItem(item), conditionsFromItem(item));
        }
        return builder;
    }

    private static ShapedRecipeJsonBuilder createTorchRecipe(Item outputItem, Item connectorItem) {
        return setHaveItemCriterion(ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, outputItem)
                .pattern("T")
                .pattern("A")
                .input('T', Items.TORCH)
                .input('A', connectorItem)
        , Items.TORCH, connectorItem);
    }
}
