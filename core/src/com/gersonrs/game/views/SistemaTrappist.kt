package com.gersonrs.game.views


import com.gersonrs.game.Application
import com.gersonrs.game.LevelFactory
import com.gersonrs.game.entity.systems.PhysicsPlanetSystem
import com.gersonrs.game.entity.systems.PlanetSystem
import com.gersonrs.game.entity.systems.RenderingSystem
import com.gersonrs.game.entity.systems.StarSystem
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable

class SistemaTrappist (app: Application) : Screen {
    private val cam: OrthographicCamera
    private val sb: SpriteBatch
    private val engine: PooledEngine
    private val lvlFactory: LevelFactory
    private val stage: Stage
    private var sun: Entity
    private val scale = 0.02
    private val deltaT = 1000.0
    private val planetSystem = PlanetSystem(deltaT)

    init {
        app.assetsManager.manager.finishLoading()
        engine = PooledEngine()
        lvlFactory = LevelFactory(engine, app.assetsManager,scale)

        sb = SpriteBatch()

        val renderingSystem = RenderingSystem(sb)
        cam = renderingSystem.cam
        sb.enableBlending()

        sun = lvlFactory.createStar("Trappist-1",(1.98855 * Math.pow(10.0, 30.0))*0.080,0.2)
        lvlFactory.createPlanet(sun, 1.51087081, 0.01111, "b", 1.086)
        lvlFactory.createPlanet(sun,2.4218233,0.01521,"c",1.056)
        lvlFactory.createPlanet(sun, 4.049610, 0.02144, "d", 0.772)
        lvlFactory.createPlanet(sun, 6.099615, 0.02817, "e", 0.918)
        lvlFactory.createPlanet(sun, 9.206690, 0.0371 , "f", 1.0)
        lvlFactory.createPlanet(sun, 12.35294, 0.0451, "g", 1.127)
        lvlFactory.createPlanet(sun, 18.767953, 0.0597, "h", 0.773)

        engine.addSystem(PhysicsPlanetSystem(scale))
        engine.addSystem(renderingSystem)
        engine.addSystem(planetSystem)
        engine.addSystem(StarSystem())

        stage = Stage()
        val table = Table()
        stage.addActor(table)

        val background = TextureRegion(app.assetsManager.manager.get("images/background.png", Texture::class.java))
        table.background = TiledDrawable(background)

        val skin: Skin = app.assetsManager.manager.get("skin/glassy-ui.json")
        val skin1: Skin = app.assetsManager.manager.get("skin1/glassy-ui.json")

        val accelerationLabel = Label("Velocidade: 1,0", skin)
        val zoomLabel = Label("Zoom: 1,0 UA", skin)

        val zoom = Slider(1f, 4.0f, 0.01f, true, skin)
        zoom.value = 1f
        zoom.addListener {
            cam.zoom = zoom.value
            val effectiveViewportWidth = cam.viewportWidth * cam.zoom
            cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth/9, (effectiveViewportWidth / 9))
            renderingSystem.font.data.setScale(zoom.value*2)
            zoomLabel.setText("Zoom: ${String.format("%.2f",zoom.value * scale)} UA")
            false
        }
        zoom.height = Gdx.graphics.height*0.30f
        renderingSystem.font.data.setScale(zoom.value*2)

        val acceleration = Slider(500f, 5000f, 0.1f, true, skin)
        acceleration.value = 1000f
        acceleration.addListener {
            planetSystem.deltaT = acceleration.value.toDouble()
            accelerationLabel.setText("Velocidade: ${String.format("%.1f",acceleration.value/ 500)}")
            false
        }
        acceleration.height = Gdx.graphics.height*0.30f

        val back = TextButton("Voltar", skin, "small")
        back.addListener(object : ChangeListener() {
            override fun changed(event: ChangeListener.ChangeEvent, actor: Actor) {
                app.changeScreen(Application.SISTEMAS)
            }
        })

        val names = TextButton("Nomes", skin1, "small")
        names.addListener(object : ChangeListener() {
            override fun changed(event: ChangeListener.ChangeEvent, actor: Actor) {
                if (names.style != skin.get<TextButton.TextButtonStyle>("small", TextButton.TextButtonStyle::class.java)) {
                    names.style = skin.get<TextButton.TextButtonStyle>("small", TextButton.TextButtonStyle::class.java)
                    renderingSystem.shouldRenderNames = false
                } else {
                    names.style = skin1.get<TextButton.TextButtonStyle>("small", TextButton.TextButtonStyle::class.java)
                    renderingSystem.shouldRenderNames = true
                }
            }
        })

        val orbit = TextButton("Orbita", skin1, "small")
        orbit.addListener(object : ChangeListener() {
            override fun changed(event: ChangeListener.ChangeEvent, actor: Actor) {
                if (orbit.style != skin.get<TextButton.TextButtonStyle>("small", TextButton.TextButtonStyle::class.java)) {
                    orbit.style = skin.get<TextButton.TextButtonStyle>("small", TextButton.TextButtonStyle::class.java)
                    renderingSystem.shouldRenderOrbits = false
                } else {
                    orbit.style = skin1.get<TextButton.TextButtonStyle>("small", TextButton.TextButtonStyle::class.java)
                    renderingSystem.shouldRenderOrbits = true
                }
            }
        })


        table.add(back).colspan(2)
        table.row().padTop(20f)
        table.add<Label>(zoomLabel).colspan(2)
        table.row()
        table.add(zoom).width(100f).height(zoom.height).colspan(2)
        table.row().padTop(20f)
        table.add<Label>(accelerationLabel).colspan(2)
        table.row()
        table.add(acceleration).width(100f).height(acceleration.height).colspan(2)
        table.row().padTop(20f)
        table.add(names).width(80f).height(45f).padRight(10f)
        table.add(orbit).width(80f).height(45f).padLeft(10f)

        table.width = Gdx.graphics.width*0.2f
        table.height = Gdx.graphics.height.toFloat()

        table.setPosition(Gdx.graphics.width - table.width,0f)
    }


    // reset world or start world again
    fun resetWorld() {
//        engine.removeAllEntities()
        lvlFactory.resetWorld()
    }


    override fun show() {
        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        engine.update(delta)
        stage.act(Math.min(delta, 1 / 30f))
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun pause() {}

    override fun resume() {
    }

    override fun hide() {}

    override fun dispose() {
        stage.dispose()
        sb.dispose()
        engine.clearPools()
    }

}
