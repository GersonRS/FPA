package com.gersonrs.game.entity.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.utils.Pool.Poolable

class StarComponent : Component, Poolable {

    var mass: Double = 0.0
    var planets = ArrayList<Entity>()

    override fun reset() {
        mass = 0.0
        planets = ArrayList<Entity>()
    }
}
