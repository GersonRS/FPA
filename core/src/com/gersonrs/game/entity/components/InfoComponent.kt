package com.gersonrs.game.entity.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Pool.Poolable

class InfoComponent : Component, Poolable {

    var name = ""
    var position = Vector2(0f,0f)
    var arrayOrbit = ArrayList<Vector2>()

    override fun reset() {
        name = ""
        position = Vector2(0f,0f)
        arrayOrbit = ArrayList<Vector2>()
    }
}
