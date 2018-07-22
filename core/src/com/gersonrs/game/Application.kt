package com.gersonrs.game

import com.gersonrs.game.loader.B2dAssetManager
import com.gersonrs.game.views.*
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera

class Application : Game() {

    var camera: OrthographicCamera = OrthographicCamera()
    val assetsManager = B2dAssetManager()

    private lateinit var loadingScreen: LoadingScreen
    private var menuScreen: MenuScreen? = null
    private var sistemaSolar: SistemaSolar? = null
    private var sistemaTrappist: SistemaTrappist? = null
    private var sistemasScreen: SistemasScreen? = null
    private var solar: SistemaSolarScreen? = null
    private var trappist: SistemaTrappistScreen? = null
    private var creditsScreen: CreditsScreen? = null
    private var splashScreen: SplashScreen? = null

    override fun create() {
        camera.setToOrtho(false, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

        loadingScreen = LoadingScreen(this)
        this.setScreen(loadingScreen)
    }

    override fun render() {
        super.render()

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
        }
    }

    override fun dispose() {
        assetsManager.dispose()
        loadingScreen.dispose()
        splashScreen!!.dispose()
    }

    fun changeScreen(screen: Int) {
        when (screen) {
            SPLASH -> {
                if (splashScreen == null) splashScreen = SplashScreen(this)
                this.setScreen(splashScreen)
            }
            MENU -> {
                if (menuScreen == null) menuScreen = MenuScreen(this)
                this.setScreen(menuScreen)
            }
            CREDITS -> {
                if (creditsScreen == null) creditsScreen = CreditsScreen(this)
                this.setScreen(creditsScreen)
            }
            SIMULATION_TRAPPIST -> {
                if (sistemaTrappist == null) {
                    sistemaTrappist = SistemaTrappist(this)
                } else {
                    sistemaTrappist!!.resetWorld()
                }

                this.setScreen(sistemaTrappist)
            }
            SISTEMAS -> {
                if (sistemasScreen == null) sistemasScreen = SistemasScreen(this)
                this.setScreen(sistemasScreen)
            }
            SIMULATION_SOLAR -> {
                // always make new game screen so game can't start midway
                if (sistemaSolar == null) {
                    sistemaSolar = SistemaSolar(this)
                } else {
                    sistemaSolar!!.resetWorld()
                }

                this.setScreen(sistemaSolar)

            }
            SOLAR -> {
                if (solar == null) solar = SistemaSolarScreen(this)
                this.setScreen(solar)
            }
            TRAPPIST -> {
                if (trappist == null) trappist = SistemaTrappistScreen(this)
                this.setScreen(trappist)
            }
        }
    }

    companion object {
        const val MENU = 0
        const val TRAPPIST = 1
        const val SOLAR = 2
        const val SIMULATION_SOLAR = 3
        const val SIMULATION_TRAPPIST = 4
        const val SISTEMAS = 5
        const val CREDITS = 6
        const val SPLASH = 7
    }
}
