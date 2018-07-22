package com.gersonrs.game.entity.systems

import com.gersonrs.game.entity.components.InfoComponent
import com.gersonrs.game.entity.components.TextureComponent
import com.gersonrs.game.entity.components.TransformComponent
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array

class RenderingSystem(private val batch: SpriteBatch) : IteratingSystem(Family.all(TransformComponent::class.java, TextureComponent::class.java,InfoComponent::class.java).get()) {
    // debug stuff
    var shouldRenderNames = true
    var shouldRenderOrbits = true
    private val renderQueue: Array<Entity>
    val cam: OrthographicCamera
    val font = BitmapFont()
    val shapeRenderer = ShapeRenderer()

    private val textureM: ComponentMapper<TextureComponent> = ComponentMapper.getFor(TextureComponent::class.java)
    private val transformM: ComponentMapper<TransformComponent> = ComponentMapper.getFor(TransformComponent::class.java)
    private val infoM: ComponentMapper<InfoComponent> = ComponentMapper.getFor(InfoComponent::class.java)

    init {

        renderQueue = Array()
        cam = OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT)
        cam.position.set(FRUSTUM_WIDTH/9, 0f, 0f)
        font.data.scale(2f)
        shapeRenderer.setAutoShapeType(true)

    }


    override fun update(deltaTime: Float) {
        super.update(deltaTime)

        cam.update()

        shapeRenderer.projectionMatrix = cam.combined
        batch.projectionMatrix = cam.combined

        shapeRenderer.begin()
        shapeRenderer.set(ShapeRenderer.ShapeType.Point)
        batch.begin()

        for (entity in renderQueue) {
            val tex = textureM.get(entity)
            val t = transformM.get(entity)
            val info = infoM.get(entity)

            if (tex.region == null || t.isHidden) {
                continue
            }

            val width = tex.region!!.regionWidth.toFloat()
            val height = tex.region!!.regionHeight.toFloat()

            val originX = width / 2f
            val originY = height / 2f

            if (shouldRenderOrbits)
                for (v in info.arrayOrbit) {
                    shapeRenderer.point(v.x,v.y,0f)
                }



            batch.draw(tex.region!!,
                    t.position.x - originX + tex.offsetX,
                    t.position.y - originY + tex.offsetY,
                    originX, originY,
                    width, height,
                    PixelsToMeters(t.scale.x), PixelsToMeters(t.scale.y),
                    t.rotation)

            if (shouldRenderNames)
                font.draw(batch, info.name, info.position.x+font.data.scaleX*info.name.length*2, info.position.y+10,font.scaleX,100,true)


        }

        batch.end()
        shapeRenderer.end()
        renderQueue.clear()
    }

    public override fun processEntity(entity: Entity, deltaTime: Float) {
        renderQueue.add(entity)
    }

    companion object {

        val PPM = 0.5f
        internal val FRUSTUM_WIDTH = Gdx.graphics.width / PPM//37.5f;
        internal val FRUSTUM_HEIGHT = Gdx.graphics.height / PPM//.0f;

        val PIXELS_TO_METRES = 1.0f / PPM

        private val meterDimensions = Vector2()
        private val pixelDimensions = Vector2()
        val screenSizeInMeters: Vector2
            get() {
                meterDimensions.set(Gdx.graphics.width * PIXELS_TO_METRES,
                        Gdx.graphics.height * PIXELS_TO_METRES)
                return meterDimensions
            }

        val screenSizeInPixesl: Vector2
            get() {
                pixelDimensions.set(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
                return pixelDimensions
            }

        fun PixelsToMeters(pixelValue: Float): Float {
            return pixelValue * PIXELS_TO_METRES
        }

    }
}