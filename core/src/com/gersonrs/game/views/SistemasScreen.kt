package com.gersonrs.game.views


import com.gersonrs.game.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable
import com.badlogic.gdx.utils.viewport.ScreenViewport

class SistemasScreen(private val app: Application) : Screen {
    private val stage: Stage = Stage(ScreenViewport())
    private val skin: Skin = app.assetsManager.manager.get("skin/glassy-ui.json")
    private val background: TextureRegion = TextureRegion(app.assetsManager.manager.get("images/Stars.png",Texture::class.java))

    override fun show() {
        Gdx.input.inputProcessor = stage
        stage.clear()
        val table = Table()
        table.setFillParent(true)
        stage.addActor(table)

        table.background = TiledDrawable(background)

        //create buttons
        val solarSystem = TextButton("Sistema Solar", skin)
        solarSystem.addAction(Actions.sequence(Actions.alpha(0f), Actions.parallel(Actions.fadeIn(.5f), Actions.moveBy(0f, -20f, .5f, Interpolation.pow5Out))))
        val trappistSystem = TextButton("Sistema Trappist", skin)
        trappistSystem.addAction(Actions.sequence(Actions.alpha(0f), Actions.parallel(Actions.fadeIn(.5f), Actions.moveBy(0f, -20f, .5f, Interpolation.pow5Out))))
        val voltar = TextButton("Voltar", skin)
        voltar.addAction(Actions.sequence(Actions.alpha(0f), Actions.parallel(Actions.fadeIn(.5f), Actions.moveBy(0f, -20f, .5f, Interpolation.pow5Out))))

        table.add(solarSystem).fillX().uniformX()
        table.row()
        table.add(trappistSystem).fillX().uniformX()
        table.row()
        table.add(voltar).fillX().uniformX()

        // create button listeners
        voltar.addListener(object : ChangeListener() {
            override fun changed(event: ChangeListener.ChangeEvent, actor: Actor) {
                app.changeScreen(Application.MENU)
            }
        })

        solarSystem.addListener(object : ChangeListener() {
            override fun changed(event: ChangeListener.ChangeEvent, actor: Actor) {
                app.changeScreen(Application.SOLAR)
            }
        })

        trappistSystem.addListener(object : ChangeListener() {
            override fun changed(event: ChangeListener.ChangeEvent, actor: Actor) {
                app.changeScreen(Application.TRAPPIST)
            }
        })

    }

    override fun render(delta: Float) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.deltaTime, 1 / 30f))
        stage.draw()

        // temp debug stuff(ignore menu  go straight to play..saves time)
        //parent.changeScreen(BlackOrbit.APPLICATION);
    }

    override fun resize(width: Int, height: Int) {
        // change the stage's viewport when the screen size is changed
        stage.viewport.update(width, height, true)
    }

    override fun pause() {}

    override fun resume() {}

    override fun hide() {}

    override fun dispose() {
        // dispose of assets when not needed anymore
        stage.dispose()
    }

}
