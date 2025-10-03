package packageLPT

import robocode.JuniorRobot

interface Strategist {

    fun returnStrategyRun(robot: JuniorRobot): Strategy
    fun returnStrategyOnScannedRobot(robot: JuniorRobot): Strategy
    fun returnStrategyOnHitWall(robot: JuniorRobot): Strategy
    fun returnStrategyOnHitByBullet(robot: JuniorRobot): Strategy

}