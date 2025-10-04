package packageLPT

import robocode.JuniorRobot

object Estratega2 : Strategist {

    private val movediza: Strategy = EstrategiaMovediza()
    private val torreta: Strategy = EstrategiaTorreta()
    private var totalRobots = 0

    private class EstrategiaTorreta : Strategy {
        override fun run(robot: LPT) {
            robot.turnRight(360)
            robot.fire(3.0)
        }

        override fun onHitWall(robot: LPT) {
            robot.back(50)
            robot.turnRight(90)
        }

        override fun onScannedRobot(robot: LPT) {
            robot.turnTo(robot.scannedAngle)
            robot.fire(3.0)
        }

        override fun onHitByBullet(robot: LPT) {
            robot.ahead(40)
            robot.turnRight(30)
        }
    }

    private class EstrategiaMovediza : Strategy {
        override fun run(robot: LPT) {
            robot.ahead(80)
            robot.turnRight(60)
            robot.ahead(80)
            robot.turnLeft(120)
        }

        override fun onHitWall(robot: LPT) {
            robot.back(100)
            robot.turnRight(90)
        }

        override fun onScannedRobot(robot: LPT) {
            if (robot.scannedDistance < 120) {
                robot.fire(1.0)
            }
            robot.turnRight(30)
            robot.ahead(40)
        }

        override fun onHitByBullet(robot: LPT) {
            robot.turnLeft(90)
            robot.ahead(100)
        }
    }



    override fun returnStrategyRun(robot: JuniorRobot): Strategy {
        if (totalRobots == 0) totalRobots = robot.others

        return if (robot.energy < 35 && (robot.others > totalRobots / 3)) {
            movediza
        } else {
            torreta
        }
    }

    override fun returnStrategyOnScannedRobot(robot: JuniorRobot): Strategy {
        return if (robot.energy > 60) {
            torreta
        } else {
            movediza
        }
    }

    override fun returnStrategyOnHitWall(robot: JuniorRobot): Strategy {
        return if (robot.energy > 50) {
            torreta
        } else {
            movediza
        }
    }

    override fun returnStrategyOnHitByBullet(robot: JuniorRobot): Strategy {
        return if (robot.energy > 35) {
            torreta
        } else {
            movediza
        }
    }

}