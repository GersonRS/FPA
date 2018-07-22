package com.gersonrs.game.entity.systems

import com.gersonrs.game.entity.components.PlanetComponent
import com.gersonrs.game.entity.components.TransformComponent
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.Array


class PhysicsPlanetSystem (scale: Double) : IteratingSystem(Family.all(TransformComponent::class.java,PlanetComponent::class.java).get()) {
    private val bodiesQueue: Array<Entity> = Array()

    private val tm = ComponentMapper.getFor(TransformComponent::class.java)
    private val pm = ComponentMapper.getFor(PlanetComponent::class.java)

    override fun update(deltaTime: Float) {
        super.update(deltaTime)
        val frameTime = Math.min(deltaTime, 0.25f)
        accumulator += frameTime
        if (accumulator >= MAX_STEP_TIME) {
            accumulator -= MAX_STEP_TIME

            //Entity Queue
            for (entity in bodiesQueue) {
                val tfm = tm.get(entity)
                val planet = pm.get(entity)
                tfm.position.x = (Math.cos(planet.angleValue) * scaledDistance(planet.distanceValue)).toFloat()
                tfm.position.y = (Math.sin(-planet.angleValue) * scaledDistance(planet.distanceValue)).toFloat()
                tfm.rotation = (planet.angleValue * MathUtils.radiansToDegrees).toFloat()
            }
        }
        bodiesQueue.clear()
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        bodiesQueue.add(entity)
    }

    private val earthSunDistanceMeters = 1.496 * Math.pow(10.0, 11.0) * scale

    // The distance that is used for drawing on screen
    private fun scaledDistance(distance: Double): Double {
        // A factor by which we scale the distance between the Sun and the Earth
        // in order to show it on screen
        val scaleFactor = earthSunDistanceMeters / RenderingSystem.screenSizeInPixesl.y
        return distance / scaleFactor
    }

    companion object {
        private val MAX_STEP_TIME = 1 / 60f
        private var accumulator = 0f
    }
}
