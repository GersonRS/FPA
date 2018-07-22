package com.gersonrs.game.entity.systems


import com.gersonrs.game.entity.components.StarComponent
import com.gersonrs.game.entity.components.TransformComponent
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem

class StarSystem: IteratingSystem(Family.all(StarComponent::class.java,TransformComponent::class.java).get()) {

    private val tm = ComponentMapper.getFor(TransformComponent::class.java)

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val t =  tm.get(entity)
        t.rotation+=2

    }
}
