package com.gersonrs.game.views

import com.gersonrs.game.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport

class SistemaTrappistScreen(private val app: Application) : Screen {
    private var skin: Skin? = null
    private var stage: Stage? = null

    override fun show() {
        // get skin
        skin = app.assetsManager.manager.get<Skin>("skin/glassy-ui.json")

        // create button to go back to manu
        val voltar = TextButton("Voltar", skin!!, "small")
        voltar.addListener(object : ChangeListener() {
            override fun changed(event: ChangeListener.ChangeEvent, actor: Actor) {
                app.changeScreen(Application.SISTEMAS)
            }
        })
        val simular = TextButton("Simular Sistema", skin!!, "small")
        simular.addListener(object : ChangeListener() {
            override fun changed(event: ChangeListener.ChangeEvent, actor: Actor) {
                app.changeScreen(Application.SIMULATION_TRAPPIST)
            }
        })

        stage = Stage(ScreenViewport())
        Gdx.input.inputProcessor = stage

        // create table to layout iutems we will add
        val table = Table(skin)
        val scrollPane = ScrollPane(table)
        scrollPane.setFillParent(true)

        //create a Labels showing the score and some credits
        val labelInfomativo = Label("TRAPPIST-1, também conhecida como 2MASS J23062928-0502285, é uma estrela anã vermelha fria, também conhecida como estrela anã superfria, localizada a 39 anos-luz do Sol na constelação Aquário.\n" +
                "\n" +
                "A estrela TRAPPIST-1 ganhou este nome pelo telescópio que a descobriu, localizado no deserto do Atacama e chamado Transiting Planets and Planetesimals Small Telescope (TRAPPIST).\n" +
                "\n" +
                "Em 22 de fevereiro de 2017 astrônomos anunciaram que TRAPPIST-1 tem o maior número de planetas de dimensões semelhantes aos da Terra já encontrados e o maior número de mundos com condições favoráveis à existência de água. Ao todo são sete exoplanetas, todos com condições de possuir água líquida. O sistema foi revelado através de observações do telescópio espacial Spitzer da NASA e do telescópio TRAPPIST, no Chile.\n" +
                "\n" +
                "Todos os planetas da TRAPPIST-1 orbitam muito mais perto que o planeta Mercúrio em relação ao Sol. A distância entre as órbitas do TRAPPIST-1b e TRAPPIST-1c é somente 1.6 vezes a distância entre a Terra e a Lua.", skin!!)
        val labelComponentesText = Label("Com base em medições de paralaxe, TRAPPIST-1 está localizada a uma distância de 39 anos-luz (12 parsecs) da Terra. Apesar da relativa proximidade, não é visível a olho nu, devido a seu brilho intrínseco extremamente baixo, tendo uma magnitude aappe visual de 18,8. Seu tipo espectral de M8V (M8.0 ± 0.5) indica que é uma anã vermelha, o menor e menos luminoso tipo de estrelas na sequência principal. Possui uma massa de 8% da massa solar, raio de 11,7% do raio solar e está brilhando com apenas 0,05% da luminosidade solar. Sua temperatura efetiva de 2 550 K a coloca em uma classe de estrelas conhecidas como anãs superfrias, que são estrelas de baixa massa e objetos sub-estelares (incluindo anãs marrons) com temperatura efetiva menor que 2 700 K. Esse grupo representa cerca de 15% dos objetos na vizinhança do Sol.\n" +
                "\n" +
                "TRAPPIST-1 tem uma idade de mais de 500 milhões de anos; o valor preciso é desconhecido.[3] Por serem totalmente convectivas, anãs vermelhas têm um tempo de vida muito maior que o Sol. Uma estrela com 8% da massa solar, como TRAPPIST-1, permanece na sequência principal por 12 trilhões de anos, e então evolui para uma anã azul, ao contrário de estrelas mais massivas que se tornam gigantes vermelhas.[13] TRAPPIST-1 tem uma metalicidade, a abundância de elementos que não são hidrogênio e hélio, parecida com a solar, com uma abundância de ferro de 109% do valor solar.", skin!!)
        val labelB = Label("TRAPPIST-1b, também designado como 2MASS J23062928-0502285 b, é um exoplaneta, possivelmente rochoso, orbitando em torno da estrela anã ultra-fria TRAPPIST-1, localizado a aproximadamente 39 anos-luz (12 parsecs) de distância, na constelação de Aquarius. O planeta foi detectado utilizando o método de trânsito, onde um planeta escurece a luz de sua estrela quando passa em frente a ela. Ele foi anunciado pela primeira vez em 2 de maio de 2016. No dia 23 de fevereiro, 2017, juntamente com quatro novos planetas anunciados no sistema, parâmetros físicos mais precisos para TRAPPIST-1b foram determinados.", skin!!)
        val labelC = Label("O TRAPPIST-1c , também designado como 2MASS J23062928-0502285 c , é um exoplaneta principalmente rochoso, semelhante a Vênus , que orbita em torno da estrela anã ultracool TRAPPIST-1 a aproximadamente 40 anos-luz de distância da Terra na constelação de Aquário . É o planeta mais massivo e terceiro maior do sistema, com cerca de 116% da massa e 110% do raio da Terra. Sua densidade indica uma composição primariamente rochosa com uma atmosfera muito espessa semelhante a Vênus, embora se espere que seja mais fina que a do TRAPPIST-1b .", skin!!)
        val labelD = Label("O TRAPPIST-1d , também designado como 2MASS J23062928-0502285d , é um pequeno exoplaneta primariamente rochoso , rico em água , orbitando dentro da zona habitável da estrela anã ultracola TRAPPIST-1 aproximadamente 40 anos-luz (12,1 parsecs , ou quase 3,7336 × 10 14 km ) da Terra na constelação de Aquário . O exoplaneta foi encontrado usando o método de trânsito , em que o efeito de escurecimento que um planeta causa quando se cruza na frente de sua estrela é medido. Os primeiros sinais do planeta foram anunciados em 2016, mas foi somente nos anos seguintes que a verdadeira natureza do planeta foi revelada. O TRAPPIST-1d é o planeta menos massivo do sistema e tem cerca de 5% de sua massa em água, provavelmente na forma de um enorme oceano líquido. Está confirmado que tem uma atmosfera compacta e terrestre e recebe apenas 4,3% mais luz solar do que a Terra, colocando-a dentro da zona habitável.", skin!!)
        val labelE = Label("TRAPPIST-1e, também designado como 2MASS J23062928-0502285 e, é um exoplaneta, provavelmente rochoso, orbitando dentro da zona habitável em torno da estrela anã ultrafria TRAPPIST-1 a aproximadamente 40 anos-luz (12.1 parsecs) de distância da Terra, na constelação de Aquarius. O exoplaneta foi encontrado usando-se o método de trânsito, em que o efeito de escurecimento de que um planeta faz com que, ao cruzar na frente de sua estrela é medido.\n" +
                "\n" +
                "Foi um dos sete novos exoplanetas descobertos orbitando a estrela usando dados do Telescópio Espacial Spitzer. O exoplaneta está dentro da zona habitável da estrela.", skin!!)
        val labelF = Label("TRAPPIST-1f, também designado como 2MASS J23062928-0502285 f, é um exoplaneta, provavelmente rochoso orbitando dentro da zona habitável em torno da estrela anã ultrafria  TRAPPIST-1 a 39 anos-luz (12 parsecs) de distância da Terra, na constelação de Aquarius. O exoplaneta foi encontrado usando-se o método de trânsito.\n" +
                "\n" +
                "Ele foi um dos quatro novos exoplanetas descobertos orbitando a estrela usando dados do Telescópio Espacial Spitzer.", skin!!)
        val labelG = Label("TRAPISTA-1g, também designado como 2MASS J23062928-0502285 g, é um exoplaneta orbitando a estrela anã ultrafria TRAPPIST-1 a 39 anos-luz (12 parsecs) de distância da Terra, na constelação de Aquarius. Ele foi um dos quatro novos exoplanetas descobertos orbitando a estrela usando dados do Telescópio Espacial Spitzer. O exoplaneta está dentro da zona habitável.", skin!!)
        val labelH = Label("TRAPPIST-1h , também designado como 2MASS J23062928-0502285 h , é um exoplaneta que orbita em torno da estrela anã ultra-cool TRAPPIST-1 a 39 anos-luz (12 parsecs) da Terra na constelação de Aquarius . Foi um dos quatro novos exoplanetas a serem descobertos orbitando a estrela usando observações do Telescópio Espacial Spitzer .", skin!!)

        labelInfomativo.setWrap(true)
        labelComponentesText.setWrap(true)
        labelB.setWrap(true)
        labelC.setWrap(true)
        labelD.setWrap(true)
        labelE.setWrap(true)
        labelF.setWrap(true)
        labelG.setWrap(true)
        labelH.setWrap(true)

        labelInfomativo.setAlignment(Align.center)
        labelComponentesText.setAlignment(Align.center)
        labelB.setAlignment(Align.center)
        labelC.setAlignment(Align.center)
        labelD.setAlignment(Align.center)
        labelE.setAlignment(Align.center)
        labelF.setAlignment(Align.center)
        labelG.setAlignment(Align.center)
        labelH.setAlignment(Align.center)

        // add items to table
        table.align(Align.center)
        table.row().padTop(15f)
        table.add("Sistema TRAPPIST-1","big").colspan(2).align(Align.center)
        table.row().padTop(10f)
        table.add(labelInfomativo).colspan(2).width(Gdx.graphics.width*0.9f)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/trappist.jpg"))).colspan(2)
        table.row().padTop(15f)
        table.add("Características estelares:","big").colspan(2).align(Align.center)
        table.row().padTop(10f)
        table.add(labelComponentesText).colspan(2).width(Gdx.graphics.width*0.9f)
        table.row().padTop(10f)
        table.add("Sistema planetario","big").colspan(2).align(Align.center)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/trappist1.jpg"))).colspan(2)

        table.row().padTop(50f)
        table.add("TRAPPIST-1b","big").colspan(2).align(Align.left)
        table.row().padTop(10f)
        table.add(labelB).width(Gdx.graphics.width*0.9f).colspan(2)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/trappistB.png"))).colspan(2)

        table.row().padTop(50f)
        table.add("TRAPPIST-1c","big").colspan(2).align(Align.left)
        table.row().padTop(10f)
        table.add(labelC).width(Gdx.graphics.width*0.9f).colspan(2)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/trappistC.png"))).colspan(2)

        table.row().padTop(50f)
        table.add("TRAPPIST-1d","big").colspan(2).align(Align.left)
        table.row().padTop(10f)
        table.add(labelD).width(Gdx.graphics.width*0.9f).colspan(2)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/trappistD.png"))).colspan(2)

        table.row().padTop(50f)
        table.add("TRAPPIST-1e","big").colspan(2).align(Align.left)
        table.row().padTop(10f)
        table.add(labelE).width(Gdx.graphics.width*0.9f).colspan(2)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/trappistE.png"))).colspan(2)

        table.row().padTop(50f)
        table.add("TRAPPIST-1f","big").colspan(2).align(Align.left)
        table.row().padTop(10f)
        table.add(labelF).width(Gdx.graphics.width*0.9f).colspan(2)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/trappistF.png"))).colspan(2)

        table.row().padTop(50f)
        table.add("TRAPPIST-1g","big").colspan(2).align(Align.left)
        table.row().padTop(10f)
        table.add(labelG).width(Gdx.graphics.width*0.9f).colspan(2)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/trappistG.png"))).colspan(2)

        table.row().padTop(50f)
        table.add("TRAPPIST-1h","big").colspan(2).align(Align.left)
        table.row().padTop(10f)
        table.add(labelH).width(Gdx.graphics.width*0.9f).colspan(2)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/trappistH.png"))).colspan(2)

        table.row().padTop(50f)
        table.add(voltar).uniform().fill()
        table.add(simular).uniform().fill()

        stage!!.addActor(scrollPane)

    }

    override fun render(delta: Float) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage!!.act()
        stage!!.draw()
        //app.changeScreen(BlackOrbit.MENU);
    }

    override fun resize(width: Int, height: Int) {
        // change the stage's viewport when teh screen size is changed
        stage!!.viewport.update(width, height, true)
    }

    override fun pause() {}

    override fun resume() {}

    override fun hide() {}

    override fun dispose() {}

}
