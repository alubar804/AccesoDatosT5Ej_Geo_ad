package com.example.tema5_postgressql_geo_ad

import classes.ComarcaEntity
import org.hibernate.cfg.Configuration
import java.util.logging.Level
import java.util.logging.LogManager

fun main(args: Array<String>) {
    LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE)
    val sessio = Configuration().configure().buildSessionFactory().openSession()
    val q = sessio.createQuery ("from ComarcaEntity")

    for (c in q.list()) {
        c as ComarcaEntity
        println(c.nomC + " --- " + c.provincia)
    }
}