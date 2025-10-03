package packageLPT

import java.awt.Color

object Defensivo : Strategist {
    private val corredora = EstrategiaCorredora()
    private val ganadora = EstrategiaGanadora()
    private var totalRobots = 0

    private class EstrategiaCorredora : Strategy {
        override fun run(robot: LPT) {
            robot.turnAheadRight(100, 45)
        }

        override fun onHitWall(robot: LPT) {
            robot.turnBackRight(100, 45)
        }

        override fun onScannedRobot(robot: LPT) {
            if (robot.gunReady) {
                robot.fire(0.5)
            }

            val escapeAngle = (robot.scannedBearing + 180) % 360

            robot.turnTo(escapeAngle)
            robot.ahead(70)
        }

        override fun onHitByBullet(robot: LPT) {
            val bearing = robot.hitByBulletBearing
            val escapeAngle = (bearing + 150) % 360

            robot.turnTo(escapeAngle)
            val escapeDistance = robot.scannedDistance.coerceAtLeast(50)
            robot.ahead(escapeDistance)
        }

    }

    private class EstrategiaGanadora: Strategy {
        override fun run(robot: LPT) {
            robot.turnGunRight(360)
        }

        override fun onHitWall(robot: LPT) {
            robot.turnBackLeft(50,135)
        }
        override fun onScannedRobot(robot: LPT) {
            val distance = robot.scannedDistance
            val bearing = robot.scannedBearing

            if(distance > 200) {
                robot.fire(1)
            } else if (distance > 50) {
                robot.fire(2)
            } else {
                robot.fire(3)
            }

            robot.turnRight(bearing)
            robot.ahead(distance.coerceAtMost(100))
        }

        override fun onHitByBullet(robot: LPT) {
            robot.turnAheadLeft(120, 60)
        }
    }
    override fun returnStrategyRun(robot: JuniorRobot): Strategy {
        robot.setColors(Color.blue.blue, Color.yellow.hashCode(), Color.yellow.hashCode())

        if(totalRobots == 0){
            totalRobots = robot.others
        }
        return if (robot.energy < 35 && (robot.others > totalRobots /3)) {
            corredora
        } else {
            ganadora
        }
    }

    override fun returnStrategyOnScannedRobot(robot: JuniorRobot): Strategy {
        return if (robot.energy > 60 || (robot.scannedDistance < (robot.fieldHeight * robot.fieldWidth / 500))){
            ganadora
        } else {
            corredora
        }
    }

    override fun returnStrategyOnHitWall(robot: JuniorRobot): Strategy {
        return if (robot.energy > 50){
            ganadora
        } else {
            corredora
        }
    }

    override fun returnStrategyOnHitByBullet(robot: JuniorRobot): Strategy {
        return if (robot.energy > 35) {
            ganadora
        } else {
            corredora
        }
    }
}