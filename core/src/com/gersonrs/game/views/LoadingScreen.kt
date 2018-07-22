package com.gersonrs.game.views

import com.gersonrs.game.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils

class LoadingScreen(private val app: Application) : Screen {

    private val shapeRenderer: ShapeRenderer = ShapeRenderer()
    private var progress: Float = 0.toFloat()

    override fun show() {
        shapeRenderer.projectionMatrix = app.camera.combined
        this.progress = 0f
        app.assetsManager.queueAddImages()
        app.assetsManager.queueAddLoadingImages()
        app.assetsManager.queueAddSkin()
        app.assetsManager.queueAddFonts()
    }

    private fun update() {
        progress = MathUtils.lerp(progress, app.assetsManager.manager.progress, .1f)
        if (app.assetsManager.manager.update() && progress >= app.assetsManager.manager.progress - .001f) {
            app.changeScreen(Application.SPLASH)
        }
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        update()

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.color = Color.BLACK
        shapeRenderer.rect(32f, app.camera.viewportHeight / 2 - 8, app.camera.viewportWidth - 64, 16f)

        shapeRenderer.color = Color.BLUE
        shapeRenderer.rect(32f, app.camera.viewportHeight / 2 - 8, progress * (app.camera.viewportWidth - 64), 16f)
        shapeRenderer.end()
    }

    override fun resize(width: Int, height: Int) {

    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun dispose() {
        shapeRenderer.dispose()
    }
}
