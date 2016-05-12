/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes.test

import com.easyforger.base.EasyForger
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.FurnaceRecipes
import org.specs2.mutable.Specification

class SmeltingRecipesSpec extends Specification with EasyForger {
  "creating smelting recipes" should {
    "register baking apple resulting in a cake" in {
      smelting(Items.apple to Items.cake)

      val smeltingResult = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(Items.apple))

      smeltingResult.getItem === Items.cake
    }
  }
}
