package packageLPT

import robocode.JuniorRobot

class LPT : JuniorRobot() {
    var robotOnSight: Boolean = false

    //    Estrategia estragia = new Agresivo(); Ejemplo de estrategia
    override fun run() {
        if (!robotOnSight) {
            turnGunLeft(5)
        } else {
            robotOnSight = false
        }
        //        estrategia.run(); Como correrla
    }


    override fun onScannedRobot() {
        robotOnSight = true
        fire(2.0)
        ahead(5)
    }


    override fun onHitWall() {
        back(10)
        turnLeft(45)
    }
}