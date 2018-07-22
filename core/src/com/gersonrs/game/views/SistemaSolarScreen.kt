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

class SistemaSolarScreen(private val app: Application) : Screen {
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
                app.changeScreen(Application.SIMULATION_SOLAR)
            }
        })

        stage = Stage(ScreenViewport())
        Gdx.input.inputProcessor = stage

        // create table to layout iutems we will add
        val table = Table(skin)

        val scrollPane = ScrollPane(table)
        scrollPane.setFillParent(true)

        //create a Labels showing the score and some credits
        val labelInfomativo = Label("O Sistema Solar compreende o conjunto constituído pelo Sol e todos os corpos celestes que estao sob seu domínio gravitacional. A estrela central, maior componente do sistema, respondendo por mais de 99,85% da massa total, gera sua energia através da fusão de hidrogênio em hélio, dois de seus principais constituintes. Os quatro planetas mais próximos do Sol (Mercúrio, Vênus, Terra e Marte) possuem em comum uma crosta sólida e rochosa, razão pela qual se classificam no grupo dos planetas telúricos, ou rochosos. Mais afastados, os quatro gigantes gasosos, Júpiter, Saturno, Urano e Netuno, são os componentes de maior massa do sistema logo após o próprio Sol. Dos cinco planetas anões, Ceres é o que se localiza mais próximo do centro do Sistema Solar, enquanto todos os outros, Plutão, Haumea, Makemake e Éris, se encontram além da órbita de Netuno.", skin!!)
        val labelComponentesText = Label("O Sistema Solar é constituído essencialmente pelo Sol e pelo conjunto de corpos que estao sob influência de seu campo gravitacional. Dentre estes, os oito planetas são os componentes mais massivos do sistema, divididos em planetas telúricos (os quatro menores e mais próximos do Sol, predominantemente rochosos) e gigantes gasosos (os quatro maiores e mais afastados do Sol). A maior parte exerce força gravitacional suficiente para manter uma camada de gases ao seu redor, ou seja, possuem atmosfera, e também satélites naturais orbitando-os. Enquanto a Terra e Marte apresentam somente um e dois satélites naturais respectivamente, os gigantes gasosos possuem dezenas cada um, nas mais variadas formas, composições e tamanhos. Existem ainda cinco corpos que, de acordo com os padrões da União Astronômica Internacional, se enquadram na categoria de planetas anões e que, na sua maioria, também exibem satélites naturais. Vários asteroides se fazem igualmente acompanhar por pequenas luas. Os quatro planetas gigantes possuem, ainda, sistemas de anéis planetários, formados essencialmente por partículas de gelo e poeira com dimensões máximas de alguns centímetros, que orbitam o planeta no plano de seu equador. Espalhados por toda extensão do Sistema Solar existem milhares de corpos menores, como asteroides e cometas, além da poeira interplanetária e de matéria proveniente do Sol que permeiam o espaço entre os corpos", skin!!)
        val labelSol = Label("O componente central e principal fonte de energia do Sistema Solar, o Sol, embora seja o astro mais luminoso quando visto do nosso planeta, é uma estrela relativamente pequena e comum na Via Láctea, com um raio de aproximadamente setecentos mil quilômetros. É constituído essencialmente por hidrogênio e hélio ionizados, mantidos coesos sob forma aproximadamente esférica graças à ação da gravidade. Consequentemente, a imensa pressão e temperatura em seu núcleo são suficientes para que ocorra o processo de fusão nuclear, no qual há a conversão de núcleos de hidrogênio em núcleos de hélio e liberação de energia. A estrela emite radiação em praticamente todo o espectro eletromagnético, sobretudo na forma de luz visível.", skin!!)
        val labelTeluricos = Label("Os quatro planetas mais próximos do Sol constituem o grupo dos planetas telúricos e têm como características comuns a presença de crostas sólidas formadas sobretudo por silicatos, além de núcleos cuja composição possui elevada porcentagem de ferro. Durante o período de formação planetária, a ausência de gelo na região mais interior do sistema e a massa modesta desses corpos não favoreceram a retenção de gases da nebulosa solar, razão pela qual são essencialmente rochosos. Nenhum apresenta um sistema de anéis planetários e somente a Terra e Marte possuem satélites naturais. Mercúrio tem uma atmosfera extremamente rarefeita, em contraste com a espessa camada de gases que envolve o planeta Vênus. A atmosfera terrestre, por sua vez, possui uma composição peculiar devido à presença de seres vivos que com ela interagem, transformando-a, enquanto a de Marte mostra-se bastante rarefeita, embora seja provável que outrora tenha sido espessa o suficiente para garantir a presença de água em estado líquido.", skin!!)
        val labelMercurio = Label("O planeta mais próximo do Sol, que gasta somente oitenta e oito dias para completar seu período de translação, possui uma aparência acinzentada com inúmeras marcas de impactos que lembram a superfície lunar. Na topografia de Mercúrio, destacam-se as áreas planas, as crateras de impacto e as cadeias montanhosas sinuosas, formadas pela contração da crosta durante o período de resfriamento do planeta. Mercúrio possui uma atmosfera extremamente rarefeita, formada somente de partículas retidas do vento solar que logo se perdem devido à intensa radiação oriunda da estrela. Por isso, a temperatura na superfície chega a ultrapassar 420 graus Celsius durante o dia e cai drasticamente durante a noite, atingindo -180°C. Também por causa da ausência de uma atmosfera substancial que pudesse desencadear processos erosivos, conservaram-se registros dos impactos de meteoroides, asteroides e cometas que ocorreram há bilhões de anos[nota 1] e que deixaram marcas por vezes extensas, como a bacia Caloris, com mais de 1 500 quilômetros de diâmetro. Mercúrio é o segundo planeta mais denso do Sistema Solar, com um núcleo metálico cujo raio equivale a 75% do total do planeta e que é responsável pela manutenção de um fraco campo magnético. Existem evidências da presença de água sob a forma de gelo em crateras profundas nos polos norte e sul que nunca recebem a luz do Sol diretamente.", skin!!)
        val labelVenus = Label("O segundo planeta a partir do Sol possui tamanho, composição e massa similares à Terra. Contudo, o seu período de rotação é de 243 dias, superior ao tempo que Vênus leva a completar uma órbita ao redor do Sol, pelo que um dia venusiano é mais longo que um ano venusiano. Apesar de o núcleo ferroso de Vênus ser similar ao da Terra, a rotação extremamente lenta de Vênus não permite a existência de um campo magnético. A atmosfera venusiana, extraordinariamente espessa e violenta, é composta primariamente por dióxido de carbono e vapores de ácido sulfúrico na forma de nuvens permanentes que envolvem todo o planeta. Como consequência, além de uma intensa pressão atmosférica (noventa vezes superior à pressão atmosférica terrestre), ocorre um superefeito estufa que faz com que a temperatura na superfície atinja mais de 470 graus Celsius.", skin!!)
        val labelTerra = Label("O maior planeta telúrico e o quinto maior do Sistema Solar, é o terceiro a contar do Sol. Seu núcleo é constituído principalmente por ferro, ao redor do qual encontra-se uma camada de rochas fundidas, por sua vez cercada por uma crosta relativamente fina e dividida em placas tectônicas em constante movimento, responsáveis pelas atividades sísmica e vulcânica na Terra. O núcleo metálico e a rotação do planeta permitem a formação de um substancial campo magnético. Com mais de setenta por cento de sua superfície coberta por água, a Terra apresenta uma peculiaridade em relação aos demais planetas, já que é o único conhecido a abrigar vida. Os seres que nele habitam influenciam a composição e a dinâmica da atmosfera terrestre, formada principalmente por nitrogênio e oxigênio. A inclinação do eixo de rotação é responsável pela ocorrência de estações que regulam o clima.", skin!!)
        val labelMarte = Label("O planeta telúrico mais afastado do Sol passou a ser um mundo intrigante a partir do advento das observações telescópicas. Exibindo calotas polares variáveis e características superficiais mutantes, levantava suspeitas da possível existência de vida fora da Terra. Contudo, após o envio de sondas e exploradores robóticos, descobriu-se que Marte é um planeta desértico e não se constatou a existência de seres vivos. Entretanto, a sonda Mars Reconnaissance Orbiter revelou veios de água salgada que fluem em regiões montanhosas nos meses mais quentes do planeta, aumentando a possibilidade da existência de vida micro-orgânica.[37] Com metade do tamanho da Terra, apresenta acidentes geográficos notáveis, como o Monte Olimpo, o maior vulcão extinto do Sistema Solar, com altitude três vezes maior do que a do Monte Everest, e o Valles Marineris, um sistema de cânions que se estende por mais de três mil quilômetros na região equatorial.", skin!!)
        val labelGigante = Label("Os quatro maiores e mais afastados planetas do Sistema Solar formam o grupo dos gigantes gasosos, todos com dimensões consideravelmente superiores às da Terra. Seu tamanho e constituição distinguem-nos dos telúricos, pelo que também recebem a denominação de planetas jovianos, em alusão ao maior componente deste conjunto, Júpiter (ou Jovis).[40] Formados principalmente por hidrogênio e hélio além de uma pequena fração de elementos mais pesados, possuem baixa densidade. Apesar de estarem afastados do Sol, o calor irradiado de seus interiores aliado a sua composição gasosa faz com que suas atmosferas sejam extremamente espessas e turbulentas, não existindo uma superfície definida em tais corpos. Também possuem em comum um núcleo rochoso, possivelmente com dimensões comparáveis ao da Terra, que seria o componente original dos planetas antes da absorção de gases e gelo durante sua formação. Todos eles apresentam igualmente numerosos satélites naturais e sistemas de anéis, além de campos magnéticos. Os dois mais distantes do Sol, Urano e Netuno, são por vezes denominados gigantes de gelo, dada a sua composição diferenciada em relação aos outros planetas gasosos.", skin!!)
        val labelJupiter = Label("O maior e mais massivo planeta do Sistema Solar exibe peculiares faixas multicoloridas criadas por fortíssimos ventos que percorrem faixas longitudinais na parte superior de sua atmosfera. Frequentemente surgem nessas bandas vórtices e sistemas de tempestades circulares, como a Grande Mancha Vermelha, uma tormenta maior que a Terra que já dura por séculos. Dentre os gases que compõem sua atmosfera, hidrogênio e hélio são os mais abundantes, seguidos por pequenas frações de vapor d'água, metano e amônia.[42] Nas camadas gasosas inferiores do planeta, a pressão atmosférica é suficiente para liquefazer o hidrogênio. Já nas camadas mais internas do planeta, o mesmo elemento adquire propriedades metálicas e se torna eletricamente condutivo, dando origem, através do fluxo de cargas elétricas, a um poderoso campo magnético cuja intensidade é vinte mil vezes superior ao que é produzido pela Terra.", skin!!)
        val labelSaturno = Label("O segundo maior planeta do Sistema Solar possui uma composição semelhante à de Júpiter, rica em hidrogênio e hélio. Sua atmosfera, em função do calor irradiado do interior de Saturno, apresenta-se em constante turbulência, com ventos de mais de 1 800 quilômetros por hora que criam bandas visíveis nas suas camadas superiores em tons de amarelo e dourado. Embora mais fraco que o de Júpiter, o campo magnético do planeta ainda é quinhentas vezes mais intenso que o terrestre. Contudo, a característica mais notável de Saturno é seu impressionante sistema de anéis, formado essencialmente por fragmentos de gelo que se espalham por faixas, com milhares de quilômetros de extensão e paralelo ao equador do planeta. Sua espessura média é de apenas dez metros, nunca excedendo 1,5 quilômetro, e a maioria dos corpos que o compõem apresentam tipicamente dimensões entre um centímetro e dez metros.", skin!!)
        val labelUrano = Label("O sétimo planeta a partir do Sol foi o primeiro a ser descoberto com o auxílio de um telescópio em 1781. À semelhança de Vênus, o sentido de rotação de Urano é retrógrado relativamente ao da maioria dos corpos do Sistema Solar. Além disso, seu eixo de rotação é extremamente inclinado, fazendo com que cada um dos polos do planeta fique diretamente voltado para o Sol durante um longo período. A atmosfera de Urano, formada principalmente de hidrogênio e hélio, além de uma pequena quantidade de metano (responsável pela coloração azul-esverdeada) e água, mostra-se dinâmica conforme as mudanças de estação do planeta. No seu interior, possivelmente se aloja uma camada líquida de água, metano e amônia. Também possui um sistema de anéis com faixas estreitas e composto por partículas escuras nos anéis mais internos e brilhantes nos mais externos.", skin!!)
        val labelNetuno = Label("O gigante e gelado Netuno é o planeta mais afastado do Sol e foi o primeiro a ser localizado a partir de cálculos matemáticos em vez de observações regulares do céu. Sua busca foi motivada por se terem constatado irregularidades na órbita de Urano que só poderiam ser explicadas pela interação com um corpo de massa considerável ainda desconhecido. Observações subsequentes da área onde Netuno se deveria encontrar, segundo os resultados calculados, vieram comprovar a sua existência. A extremamente violenta atmosfera netuniana, com ventos cuja velocidade máxima de 1200 km/h excede nove vezes a dos mais intensos que ocorrem na Terra, apresenta relevante porcentagem de metano, responsável por sua coloração azulada. Frequentemente surgem sistemas de tempestades circulares no planeta, como a grande mancha escura, um sistema anticiclônico maior que a Terra que desapareceu alguns anos após ser fotografado pela sonda Voyager 2. Presume-se que as camadas intermediárias de Netuno sejam formadas por compostos gelados, como amônia e água, ao redor de um núcleo rochoso.", skin!!)

        labelInfomativo.setWrap(true)
        labelSol.setWrap(true)
        labelTeluricos.setWrap(true)
        labelMercurio.setWrap(true)
        labelVenus.setWrap(true)
        labelTerra.setWrap(true)
        labelMarte.setWrap(true)
        labelGigante.setWrap(true)
        labelJupiter.setWrap(true)
        labelSaturno.setWrap(true)
        labelUrano.setWrap(true)
        labelNetuno.setWrap(true)
        labelComponentesText.setWrap(true)

        labelInfomativo.setAlignment(Align.center)
        labelSol.setAlignment(Align.center)
        labelTeluricos.setAlignment(Align.center)
        labelMercurio.setAlignment(Align.center)
        labelVenus.setAlignment(Align.center)
        labelTerra.setAlignment(Align.center)
        labelMarte.setAlignment(Align.center)
        labelGigante.setAlignment(Align.center)
        labelJupiter.setAlignment(Align.center)
        labelSaturno.setAlignment(Align.center)
        labelUrano.setAlignment(Align.center)
        labelNetuno.setAlignment(Align.center)
        labelComponentesText.setAlignment(Align.center)

        // add items to table
        table.align(Align.center)
        table.row().padTop(15f)
        table.add("Sistema Solar","big").colspan(2).align(Align.center)
        table.row().padTop(10f)
        table.add(labelInfomativo).colspan(2).width(Gdx.graphics.width*0.9f)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/solar1.jpg"))).colspan(2)
        table.row().padTop(15f)
        table.add("Componentes:","big").colspan(2).align(Align.center)
        table.row().padTop(10f)
        table.add(labelComponentesText).colspan(2).width(Gdx.graphics.width*0.9f)
        table.row().padTop(10f)
        table.add("Planetas teluricos","big").colspan(2).align(Align.center)
        table.row().padTop(10f)
        table.add(labelTeluricos).width(Gdx.graphics.width*0.9f).colspan(2)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/teluricos.jpg"))).colspan(2)

        table.row().padTop(50f)
        table.add("Mercurio","big").colspan(2).align(Align.left)
        table.row().padTop(10f)
        table.add(labelMercurio).width(Gdx.graphics.width*0.9f).colspan(2)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/mercurio.jpg"))).colspan(2)

        table.row().padTop(50f)
        table.add("Venus","big").colspan(2).align(Align.left)
        table.row().padTop(10f)
        table.add(labelVenus).width(Gdx.graphics.width*0.9f).colspan(2)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/venus.jpg"))).colspan(2)

        table.row().padTop(50f)
        table.add("Terra","big").colspan(2).align(Align.left)
        table.row().padTop(10f)
        table.add(labelTerra).width(Gdx.graphics.width*0.9f).colspan(2)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/terra.jpg"))).colspan(2)

        table.row().padTop(50f)
        table.add("Marte","big").colspan(2).align(Align.left)
        table.row().padTop(10f)
        table.add(labelMarte).width(Gdx.graphics.width*0.9f).colspan(2)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/marte.jpg"))).colspan(2)

        table.row().padTop(50f)
        table.add("Planetas gigantes","big").colspan(2).align(Align.left)
        table.row().padTop(10f)
        table.add(labelGigante).width(Gdx.graphics.width*0.9f).colspan(2)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/gigantes.jpg"))).colspan(2)

        table.row().padTop(50f)
        table.add("Jupiter","big").colspan(2).align(Align.left)
        table.row().padTop(10f)
        table.add(labelJupiter).width(Gdx.graphics.width*0.9f).colspan(2)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/jupiter.jpg"))).colspan(2)

        table.row().padTop(50f)
        table.add("Saturno","big").colspan(2).align(Align.left)
        table.row().padTop(10f)
        table.add(labelSaturno).width(Gdx.graphics.width*0.9f).colspan(2)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/saturno.jpg"))).colspan(2)

        table.row().padTop(50f)
        table.add("Urano","big").colspan(2).align(Align.left)
        table.row().padTop(10f)
        table.add(labelUrano).width(Gdx.graphics.width*0.9f).colspan(2)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/urano.jpg"))).colspan(2)

        table.row().padTop(50f)
        table.add("Netuno","big").colspan(2).align(Align.left)
        table.row().padTop(10f)
        table.add(labelNetuno).width(Gdx.graphics.width*0.9f).colspan(2)
        table.row().padTop(10f)
        table.add(Image(app.assetsManager.manager.get<Texture>("images/netuno.jpg"))).colspan(2)

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
