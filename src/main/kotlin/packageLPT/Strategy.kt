package packageLPT

interface Strategy {

    fun run(robot: LPT)
    fun onHitWall(robot: LPT)
    fun onScannedRobot(robot: LPT)
    fun onHitByBullet(robot: LPT)

}