/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes.test

// TODO: change the DSL to avoid this import to be even necessary at all
import com.easyforger.recipes._ //scalastyle:ignore
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import org.specs2.matcher.Matcher
import org.specs2.mutable.Specification

class ShapedRecipesSpec extends Specification {
  sequential
  isolated

  def beSameStackAs(itemStack: ItemStack): Matcher[ItemStack] =
    ((_: ItemStack).stackSize) ^^ ===(itemStack.stackSize) and
    ((_: ItemStack).getItem) ^^ ===(itemStack.getItem)

  "a dsl recipe" should {
    "have the components for the saddle" in {
      val recipe = (Items.leather + Items.iron_ingot) to Items.saddle withShape
        """
          |...
          |lll
          |i.i
        """.stripMargin

      val params = Crafting.calcParamsArrays(recipe)
      params(0) === "   "
      params(1) === "lll"
      params(2) === "i i"
      params(3) === 'l'
      params(4).asInstanceOf[ItemStack] must beSameStackAs(new ItemStack(Items.leather))
      params(5) === 'i'
      params(6).asInstanceOf[ItemStack] must beSameStackAs(new ItemStack(Items.iron_ingot))
    }

    "have the components for diamond + carrots to armor stand" in {
      val recipe = Items.diamond + Items.carrot to Items.armor_stand withShape
        """
          |.c.
          |.d.
          |.d.
        """.stripMargin

      val params = Crafting.calcParamsArrays(recipe)
      params(0) === " c "
      params(1) === " d " // scalastyle:ignore
      params(2) === " d "
      params(3) === 'd'
      params(4).asInstanceOf[ItemStack] must beSameStackAs(new ItemStack(Items.diamond))
      params(5) === 'c'
      params(6).asInstanceOf[ItemStack] must beSameStackAs(new ItemStack(Items.carrot))
    }
  }
}
