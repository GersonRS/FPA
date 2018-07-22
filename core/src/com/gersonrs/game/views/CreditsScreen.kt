package com.gersonrs.game.views


import com.gersonrs.game.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport


class CreditsScreen(val app: Application) : Screen {
    private val stage: Stage = Stage(ScreenViewport())
    private val skin: Skin = app.assetsManager.manager.get("skin/glassy-ui.json")

    override fun show() {
        Gdx.input.inputProcessor = stage
        stage.clear()
        val table = Table()
        table.setFillParent(true)
        stage.addActor(table)

        val labelInformative = Label("O Aplicativo BlackOrbit é um simulador de orbitas planetarias construido com o intuito da obtenção da nota da disciplina de Fundamentos em Programação Aplicada do Mestrado em Informatica aplicada da Universidade Federal Rural de Pernambuco no semestre 2018.1", skin, "big")
        val labelCredits = Label("Creditos:", skin ,"big")
        val labelCredits1 = Label("Game Design by", skin,"big")
        val labelCredits2 = Label("Gerson Santos", skin,"big")
        val labelCredits3 = Label("Art Design by", skin,"big")
        val labelCredits4 = Label("Gerson Santos", skin,"big")

        val back = TextButton("Voltar", skin)
        back.addListener(object : ChangeListener() {
            override fun changed(event: ChangeListener.ChangeEvent, actor: Actor) {
                app.changeScreen(Application.MENU)
            }
        })

        labelInformative.setWrap(true)
        labelInformative.setAlignment(Align.center)

        table.add(labelInformative).colspan(2).width(Gdx.graphics.width*0.9f)
        table.row().padTop(50f)
        table.add(labelCredits).colspan(2)
        table.row().padTop(10f)
        table.add(labelCredits1).uniformX().align(Align.right).padRight(20f)
        table.add(labelCredits2).uniformX().align(Align.left).padLeft(20f)
        table.row().padTop(10f)
        table.add(labelCredits3).uniformX().align(Align.right).padRight(20f)
        table.add(labelCredits4).uniformX().align(Align.left).padLeft(20f)
        table.row().padTop(50f)
        table.add(back).colspan(2)

        table.addAction(Actions.sequence(Actions.alpha(0f), Actions.parallel(Actions.fadeIn(.5f), Actions.moveBy(0f, -20f, .5f, Interpolation.pow5Out))))

        stage.addActor(table)

    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act(Math.min(Gdx.graphics.deltaTime, 1 / 30f))
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun pause() {}

    override fun resume() {}

    override fun hide() {}

    override fun dispose() {
        stage.dispose()
    }

}
