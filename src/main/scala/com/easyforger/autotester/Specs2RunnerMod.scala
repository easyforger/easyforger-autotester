package com.easyforger.autotester

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPostInitializationEvent

@Mod(modid = "easyforger-autotester", name = "EasyForger AutoTester", version = "0.1", modLanguage = "scala")
object Specs2RunnerMod {

  @EventHandler
  def postInit(event: FMLPostInitializationEvent) = {
    org.specs2.runner.files.run(
      Array(
        "html", "console",
        "filesrunner.basepath", "../src/main/scala",
        "stats.outdir", "../build/specs2-reports/stats",
        "html.outdir", "../build/specs2-reports",
        "html.template", "../build/specs2-reports/templates/specs2.html")
    )
  }
}
