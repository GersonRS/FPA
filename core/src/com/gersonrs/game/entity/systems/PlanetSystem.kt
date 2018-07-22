package com.gersonrs.game.entity.systems

import com.gersonrs.game.entity.components.PlanetComponent
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem

class PlanetSystem(deltaTime: Double): IteratingSystem(Family.all(PlanetComponent::class.java).get()) {

    private val pm = ComponentMapper.getFor(PlanetComponent::class.java)

    var deltaT = deltaTime

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val planet = pm.get(entity)

        calculateNewPosition(planet,deltaT)
    }

    companion object {

        val gravitationalConstant = 6.67408 * Math.pow(10.0, -11.0)

        fun calculateDistanceAcceleration(planet: PlanetComponent): Double {
            // [acceleration of distance] = [distance][angular velocity]^2 - G * M / [distance]^2
            return planet.distanceValue * Math.pow(planet.angleSpeed, 2.0) -
                    (gravitationalConstant * planet.massOfTheSunKg) / Math.pow(planet.distanceValue, 2.0)
        }

        fun calculateAngleAcceleration(planet: PlanetComponent): Double {
            // [acceleration of angle] = - 2[speed][angular velocity] / [distance]
            return -2.0 * planet.distanceSpeed * planet.angleSpeed / planet.distanceValue
        }

        fun newValue(currentValue: Double, deltaT: Double, derivative: Double): Double {
            return currentValue + deltaT * derivative
        }

        fun calculateNewPosition(planet: PlanetComponent, deltaT: Double) {
            // Calculate new distance
            val distanceAcceleration = calculateDistanceAcceleration(planet)
            planet.distanceSpeed = newValue(planet.distanceSpeed, deltaT, distanceAcceleration)
            planet.distanceValue = newValue(planet.distanceValue, deltaT, planet.distanceSpeed)

            // Calculate new angle
            val angleAcceleration = calculateAngleAcceleration(planet)
            planet.angleSpeed = newValue(planet.angleSpeed, deltaT, angleAcceleration)
            planet.angleValue = newValue(planet.angleValue, deltaT, planet.angleSpeed)

            if (planet.angleValue > 2 * Math.PI) {
                planet.angleValue = planet.angleValue % (2 * Math.PI)
            }
        }
    }
}
