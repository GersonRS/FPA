package com.gersonrs.game.loader

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Skin

class B2dAssetManager {

    val manager = AssetManager()

    // Skin
    private val skin = "skin/glassy-ui.json"
    private val skin1 = "skin1/glassy-ui.json"

    // Textures
    val gameImages = "images/objects.atlas"

    fun queueAddFonts() {

    }
    // a small set of images used by the loading screen
    fun queueAddLoadingImages() {
        manager.load("images/Stars.png",Texture::class.java)
        manager.load("images/logo.png",Texture::class.java)
        manager.load("images/background.png",Texture::class.java)
        manager.load("images/teluricos.jpg",Texture::class.java)
        manager.load("images/gigantes.jpg",Texture::class.java)
        manager.load("images/mercurio.jpg",Texture::class.java)
        manager.load("images/venus.jpg",Texture::class.java)
        manager.load("images/terra.jpg",Texture::class.java)
        manager.load("images/marte.jpg",Texture::class.java)
        manager.load("images/jupiter.jpg",Texture::class.java)
        manager.load("images/saturno.jpg",Texture::class.java)
        manager.load("images/urano.jpg",Texture::class.java)
        manager.load("images/netuno.jpg",Texture::class.java)
        manager.load("images/sol.jpg",Texture::class.java)
        manager.load("images/solar1.jpg",Texture::class.java)
        manager.load("images/trappist1.jpg",Texture::class.java)
        manager.load("images/trappist.jpg",Texture::class.java)
        manager.load("images/trappistB.png",Texture::class.java)
        manager.load("images/trappistC.png",Texture::class.java)
        manager.load("images/trappistD.png",Texture::class.java)
        manager.load("images/trappistE.png",Texture::class.java)
        manager.load("images/trappistF.png",Texture::class.java)
        manager.load("images/trappistG.png",Texture::class.java)
        manager.load("images/trappistH.png",Texture::class.java)
    }

    fun queueAddSkin() {
        val params1 = SkinParameter("skin1/glassy-ui.atlas")
        val params = SkinParameter("skin/glassy-ui.atlas")
        manager.load(skin1, Skin::class.java, params1)
        manager.load(skin, Skin::class.java, params)

    }

    fun queueAddImages() {
        manager.load(gameImages, TextureAtlas::class.java)
    }

    fun dispose() {
        manager.dispose()
    }
}
