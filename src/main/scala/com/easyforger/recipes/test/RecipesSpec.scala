package com.easyforger.recipes.test

import com.easyforger.recipes._
import net.minecraft.init.Items._
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.{ShapelessRecipes, CraftingManager}
import org.specs2.mutable.Specification

import scala.collection.JavaConverters._

class RecipesSpec extends Specification {

  "a recipe" should {
    "register diamond + carrot to armor_stand" in {
      crafting(diamond + carrot to armor_stand)

      val recipesList = CraftingManager.getInstance().getRecipeList

      val recipes = recipesList.asScala
        .filter(_.isInstanceOf[ShapelessRecipes])
        .filter(_.asInstanceOf[ShapelessRecipes].getRecipeOutput.itemStack.getUnlocalizedName == "item.armorStand")

      // asserts that we found the recipe that results in an armorstand (assumes there is no vanilla recipe for that!)
      recipes must have size 1

      val addedRecipe = recipes.head.asInstanceOf[ShapelessRecipes]
      addedRecipe.recipeItems must have size 2

      val recipeItems = addedRecipe.recipeItems.asScala

      recipeItems must contain((itemStack: ItemStack) => itemStack.getUnlocalizedName === "item.carrots")
      recipeItems must contain((itemStack: ItemStack) => itemStack.getUnlocalizedName === "item.diamond")
    }
  }
}
