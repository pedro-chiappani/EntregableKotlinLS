package packageLPT

import robocode.JuniorRobot

class LPT : JuniorRobot() {
    //private var estratega: Strategist = Defensivo
    private var estratega: Strategist = Estratega2

    override fun run(){
        estratega.returnStrategyRun(this).run(this)
    }

    override fun onScannedRobot(){
        estratega.returnStrategyOnScannedRobot(this).onScannedRobot(this)
    }

    override fun onHitWall(){
        estratega.returnStrategyOnHitWall(this).onHitWall(this)
    }

    override fun onHitByBullet(){
        estratega.returnStrategyOnHitByBullet(this).onHitByBullet(this)
    }
}