package com.gersonrs.game.entity.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Pool.Poolable

class TransformComponent : Component, Poolable {
    val position = Vector3()
    val scale = Vector2(1.0f, 1.0f)
    var rotation = 0.0f
    var isHidden = false
    override fun reset() {
        rotation = 0.0f
        isHidden = false
    }
}