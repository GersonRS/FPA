package com.gersonrs.game.entity.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool.Poolable

class PlanetComponent : Component, Poolable {

    var distanceValue: Double = 0.0
    var distanceSpeed: Double = 0.0
    var angleValue: Double = 0.0
    var angleSpeed: Double = 0.0
    var massOfTheSunKg: Double = 0.0

    override fun reset() {
        distanceValue = 0.0
        distanceSpeed = 0.0
        angleValue = 0.0
        angleSpeed = 0.0
        massOfTheSunKg = 0.0

    }
}
