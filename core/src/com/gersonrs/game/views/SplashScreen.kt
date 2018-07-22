package com.gersonrs.game.views

import com.gersonrs.game.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable
import com.badlogic.gdx.utils.viewport.ScreenViewport

class SplashScreen(private val app: Application) : Screen {
    private var stage: Stage = Stage(ScreenViewport())

    private val background: TextureRegion = TextureRegion(app.assetsManager.manager.get("images/Stars.png", Texture::class.java))

    override fun show() {
        Gdx.input.inputProcessor = stage

        val transitionRunnable = Runnable { app.changeScreen(Application.MENU) }

        val splashTex = app.assetsManager.manager.get("images/logo.png", Texture::class.java)

        val splashImg = Image(splashTex)
        splashImg.setOrigin(splashImg.width / 2, splashImg.height / 2)
        splashImg.setPosition((stage.width / 2) - splashImg.width/2, (stage.height/2) - splashImg.height/2)
        splashImg.addAction(sequence(alpha(0f), scaleTo(.1f, .1f),
                parallel(fadeIn(2f, Interpolation.pow2),
                        scaleTo(1.8f, 1.8f, 2.5f, Interpolation.pow5)),
                delay(1.5f), fadeOut(1.25f), run(transitionRunnable)))

        val table = Table()
        table.background = TiledDrawable(background)
        table.setFillParent(true)
        stage.addActor(table)
        stage.addActor(splashImg)

    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        update(delta)

        stage.draw()
    }

    fun update(delta: Float) {
        stage.act(delta)
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, false)
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun dispose() {
        stage.dispose()
    }
}
