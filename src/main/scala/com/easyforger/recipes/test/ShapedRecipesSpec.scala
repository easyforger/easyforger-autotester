package com.easyforger.recipes.test

import com.easyforger.recipes._
import net.minecraft.init.Items._
import net.minecraft.item.ItemStack
import org.specs2.mutable.Specification

class ShapedRecipesSpec extends Specification {
  sequential
  isolated

  def beSameStackAs(itemStack: ItemStack) =
    ((_: ItemStack).stackSize) ^^ ===(itemStack.stackSize) and
    ((_: ItemStack).getItem) ^^ ===(itemStack.getItem)

  "a dsl recipe" should {
    "have the components for the saddle" in {
      val recipe = (leather + iron_ingot) to saddle withShape
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
      params(4).asInstanceOf[ItemStack] must beSameStackAs(new ItemStack(leather))
      params(5) === 'i'
      params(6).asInstanceOf[ItemStack] must beSameStackAs(new ItemStack(iron_ingot))
    }

    "have the components for diamond + carrots to armor stand" in {
      val recipe = diamond + carrot to armor_stand withShape
        """
          |.c.
          |.d.
          |.d.
        """.stripMargin

      val params = Crafting.calcParamsArrays(recipe)
      params(0) === " c "
      params(1) === " d "
      params(2) === " d "
      params(3) === 'd'
      params(4).asInstanceOf[ItemStack] must beSameStackAs(new ItemStack(diamond))
      params(5) === 'c'
      params(6).asInstanceOf[ItemStack] must beSameStackAs(new ItemStack(carrot))
    }
  }
}
