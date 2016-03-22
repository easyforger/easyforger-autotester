/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes.test

// TODO: change the DSL to avoid this import to be even necessary at all
import com.easyforger.recipes._ //scalastyle:ignore
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.{ShapelessRecipes, CraftingManager}
import org.specs2.mutable.Specification

import scala.collection.JavaConverters.asScalaBufferConverter

class RecipesSpec extends Specification {

  "a recipe" should {
    "register diamond + carrot to armor_stand" in {
      crafting(Items.diamond + Items.carrot to Items.armor_stand)

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
