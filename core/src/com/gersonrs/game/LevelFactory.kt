package com.gersonrs.game


import com.gersonrs.game.entity.components.*
import com.gersonrs.game.entity.systems.PlanetSystem
import com.gersonrs.game.entity.systems.RenderingSystem
import com.gersonrs.game.loader.B2dAssetManager
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2

class LevelFactory(private val engine: PooledEngine, assman: B2dAssetManager, private val scale: Double) {

    private val secondsPerYear = 31558149.7635456
    private val terrestrialYear = 365.256363004
    private val ua = 149597870700.0
    private val atlas: TextureAtlas = assman.manager.get("images/objects.atlas", TextureAtlas::class.java)

    fun createStar(name: String, mass: Double, d: Double): Entity {
        val entity = engine.createEntity()
        val position = engine.createComponent(TransformComponent::class.java)
        val texture = engine.createComponent(TextureComponent::class.java)
        val star = engine.createComponent(StarComponent::class.java)
        val info = engine.createComponent(InfoComponent::class.java)

        star.mass = mass

        info.name = name

        texture.region = atlas.findRegion(name)
        position.position.set(0f,0f,0f)
        position.scale.set(d.toFloat(),d.toFloat())

        entity.add(position)
        entity.add(texture)
        entity.add(star)
        entity.add(info)

        engine.addEntity(entity)
        return entity

    }

    fun createPlanet(star: Entity, orbitalPeriod: Double, semiMajorAxis: Double, name: String, d: Double): Entity {

        val s = star.getComponent(StarComponent::class.java)

        val entity = engine.createEntity()
        val position = engine.createComponent(TransformComponent::class.java)
        val texture = engine.createComponent(TextureComponent::class.java)
        val planet = engine.createComponent(PlanetComponent::class.java)
        val info = engine.createComponent(InfoComponent::class.java)

        planet.distanceValue = semiMajorAxis* ua
        planet.angleSpeed = (2*Math.PI)/(secondsPerYear *(orbitalPeriod/ terrestrialYear))
        planet.massOfTheSunKg = s.mass

        info.name = name
        val p = PlanetComponent()
        p.distanceValue = planet.distanceValue
        p.distanceSpeed = planet.distanceSpeed
        p.angleValue = planet.angleValue
        p.angleSpeed = planet.angleSpeed
        p.massOfTheSunKg = planet.massOfTheSunKg
        val deltaTime = scaledDistance(p.distanceValue)*scale*500


        while (p.angleValue < 2 * Math.PI) {
            val distanceAcceleration = PlanetSystem.calculateDistanceAcceleration(p)
            p.distanceSpeed = PlanetSystem.newValue(p.distanceSpeed, deltaTime, distanceAcceleration)
            p.distanceValue = PlanetSystem.newValue(p.distanceValue, deltaTime, p.distanceSpeed)
            val angleAcceleration = PlanetSystem.calculateAngleAcceleration(p)
            p.angleSpeed = PlanetSystem.newValue(p.angleSpeed, deltaTime, angleAcceleration)
            p.angleValue = PlanetSystem.newValue(p.angleValue, deltaTime, p.angleSpeed)
            val pos = Vector2()
            pos.x = (Math.cos(p.angleValue) * scaledDistance(p.distanceValue)).toFloat()
            pos.y = (Math.sin(-p.angleValue) * scaledDistance(p.distanceValue)).toFloat()
            info.arrayOrbit.add(pos)
            if(p.angleValue >= Math.PI*1.45000 && p.angleValue <= Math.PI*1.510000)
                info.position = pos
        }
        position.position.set(10f, 1f, 0f)
        position.scale.set(d.toFloat(),d.toFloat())
        texture.region = atlas.findRegion(name.toLowerCase())

        entity.add(position)
        entity.add(texture)
        entity.add(planet)
        entity.add(info)

        engine.addEntity(entity)
        s.planets.add(entity)
        return entity
    }


    fun resetWorld() {
    }

    private val earthSunDistanceMeters = 1.496 * Math.pow(10.0, 11.0) * scale

    // The distance that is used for drawing on screen
    private fun scaledDistance(distance: Double): Double {
        // A factor by which we scale the distance between the Sun and the Earth
        // in order to show it on screen
        val scaleFactor = earthSunDistanceMeters / RenderingSystem.screenSizeInPixesl.y
        return distance / scaleFactor
    }
}