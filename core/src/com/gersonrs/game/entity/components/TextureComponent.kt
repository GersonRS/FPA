package com.gersonrs.game.entity.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Pool.Poolable

class TextureComponent : Component, Poolable {
    var region: TextureRegion? = null
    var offsetX = 0f
    var offsetY = 0f

    override fun reset() {
        region = null
    }
}
