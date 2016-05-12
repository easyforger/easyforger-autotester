/*
 * This file is part of EasyForger which is released under GPLv3 License.
 * See file LICENSE.txt or go to http://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 */
package com.easyforger.recipes.test

import com.easyforger.base.EasyForger
import com.easyforger.recipes.RecipeSupport
import net.minecraft.block.Block
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.Item
import org.specs2.matcher.Matcher
import org.specs2.mutable.Specification

class RecipeSupportSpec extends Specification with EasyForger {
  "RecipeSupport.shortForItemStack" should {
    "get the short for Items" in {
      "'a' for apple" >> (Items.apple must haveItemShort('a'))
      "'i' for iron_axe" >> (Items.iron_axe must haveItemShort('i'))
      "'s' for sign" >> (Items.sign must haveItemShort('s'))
      "'s' for wheat_seeds" >> (Items.wheat_seeds must haveItemShort('s'))
      "'a' for armor_stand" >> (Items.armor_stand must haveItemShort('a'))
      "'c' for item cake" >> (Items.cake must haveItemShort('c'))
      "'a' for item acacia_door" >> (Items.acacia_door must haveItemShort('a'))
      "'j' for item jungle_door" >> (Items.jungle_door must haveItemShort('j'))
      "'b' for item bed" >> (Items.bed must haveItemShort('b'))
    }

    "get the short for Blocks" in {
      "'d' for dirt" >> (Blocks.dirt must haveBlockShort('d'))
      "'f' for furnace" >> (Blocks.furnace must haveBlockShort('f'))
      "'g' for grass" >> (Blocks.grass must haveBlockShort('g'))
      "'b' for brewing_stand" >> (Blocks.brewing_stand must haveBlockShort('b'))
      "'r' for reeds" >> (Blocks.reeds must haveBlockShort('r'))
      "'a' for acacia_fence" >> (Blocks.acacia_fence must haveBlockShort('a'))
      "'a' for acacia_fence_gate" >> (Blocks.acacia_fence_gate must haveBlockShort('a'))
      "'a' for acacia_stairs" >> (Blocks.acacia_stairs must haveBlockShort('a'))
      "'b' for birch_stairs" >> (Blocks.birch_stairs must haveBlockShort('b'))
      "'c' for block cake" >> (Blocks.cake must haveBlockShort('c'))
      "'a' for block acacia_door" >> (Blocks.acacia_door must haveBlockShort('a'))
      "'j' for block jungle_door" >> (Blocks.jungle_door must haveBlockShort('j'))
      "'b' for block birch_door" >> (Blocks.birch_door must haveBlockShort('b'))
      "'o' for block oak_door" >> (Blocks.oak_door must haveBlockShort('o'))
      "'d' for block dark_oak_door" >> (Blocks.dark_oak_door must haveBlockShort('d'))
      "'b' for block bed" >> (Blocks.bed must haveBlockShort('b'))
    }

    "get the short for special items" in {
      "'b' for black dye" >> ((blackDye.getItem, blackDye.getMetadata) must haveSpecialItemShort('b'))
      "'m' for magenta dye" >> ((magentaDye.getItem, magentaDye.getMetadata) must haveSpecialItemShort('m'))
      "'y' for yellow dye" >> ((yellowDye.getItem, yellowDye.getMetadata) must haveSpecialItemShort('y'))
    }
  }

  def haveBlockShort(c: Char): Matcher[Block] = { block: Block =>
    RecipeSupport.shortForBlock(block) === c
  }

  def haveItemShort(c: Char): Matcher[Item] = { item: Item =>
    RecipeSupport.shortForItem(item) === c
  }

  def haveSpecialItemShort(c: Char): Matcher[(Item, Int)] = { params: (Item, Int) =>
    val (item, meta) = params
    RecipeSupport.shortForSpecialItem(item, meta) === c
  }
}
