@file:Suppress("NAME_SHADOWING")

package com.gersonrs.game

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Pixmap.Format
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2

object DFUtils {
    private var fnames: Array<String>? = null
    private var lnames: Array<String>? = null
    private var pmap: Pixmap? = null

    /**
     * Converts HSV values to RGBA
     * @param hue The hue input value
     * @param saturation The saturation of the colour
     * @param value the value of the colour
     * @param alpha the alpha to output with RGB
     * @return The RGBA value
     */
    fun hsvToRgba(hue: Float, saturation: Float, value: Float, alpha: Float): Color {

        val h = (hue * 6).toInt()
        val f = hue * 6 - h
        val p = value * (1 - saturation)
        val q = value * (1 - f * saturation)
        val t = value * (1 - (1 - f) * saturation)

        when (h) {
            0 -> return Color(value, t, p, alpha)
            1 -> return Color(q, value, p, alpha)
            2 -> return Color(p, value, t, alpha)
            3 -> return Color(p, q, value, alpha)
            4 -> return Color(t, p, value, alpha)
            5 -> return Color(value, p, q, alpha)
            else -> throw RuntimeException("Something went wrong when converting from HSV to RGB. Input was $hue, $saturation, $value")
        }
    }

    /**
     * Quick access to console logging
     * @param o
     */
    fun log(o: Any) {
        println(o)
    }

    fun makeTexture(width: Int, height: Int, hex: String): Texture {
        var hex = hex
        if (hex.length == 6) {
            hex += "FF"
        }
        return makeTexture(width, height, Color.valueOf(hex))
    }

    fun makeTextureRegion(width: Int, height: Int, hex: String): TextureRegion {
        var hex = hex
        if (hex.length == 6) {
            hex += "FF"
        }
        return makeTextureRegion(width, height, Color.valueOf(hex))
    }

    private fun makeTextureRegion(width: Int, height: Int, col: Color): TextureRegion {
        return TextureRegion(makeTexture(width, height, col))
    }

    fun makeTexture(width: Int, height: Int, col: Color): Texture {
        val tex: Texture = Texture(makePixMap(width, height, col))
        disposePmap()
        return tex
    }

    private fun makePixMap(width: Int, height: Int, fill: Color): Pixmap {
        pmap = Pixmap(width, height, Format.RGBA8888)
        pmap!!.setColor(fill)
        pmap!!.fill()
        return pmap as Pixmap
    }

    private fun disposePmap() {
        pmap!!.dispose()
    }

    fun spriteSheetToFrames(region: TextureRegion, FRAME_COLS: Int, FRAME_ROWS: Int): Array<TextureRegion?> {

        // split texture region
        val tmp = region.split(region.regionWidth / FRAME_COLS,
                region.regionHeight / FRAME_ROWS)

        // compact 2d array to 1d array
        val frames = arrayOfNulls<TextureRegion>(FRAME_COLS * FRAME_ROWS)
        var index = 0
        for (i in 0 until FRAME_ROWS) {
            for (j in 0 until FRAME_COLS) {
                frames[index++] = tmp[i][j]
            }
        }

        return frames
    }

    fun vectorToAngle(vector: Vector2): Float {
        return Math.atan2((-vector.x).toDouble(), vector.y.toDouble()).toFloat()
    }

    fun angleToVector(outVector: Vector2, angle: Float): Vector2 {
        outVector.x = -Math.sin(angle.toDouble()).toFloat()
        outVector.y = Math.cos(angle.toDouble()).toFloat()
        return outVector
    }

}
