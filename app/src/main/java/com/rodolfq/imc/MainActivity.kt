package com.rodolfq.imc

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.rodolfq.imc.databinding.ActivityMainBinding
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btn.setOnClickListener {

            val alturaText = binding.height.text.toString()
            val pesoText = binding.weight.text.toString()

            if (alturaText.isEmpty() || pesoText.isEmpty()) {
                binding.message.text = "Peencha todos os campos"
                return@setOnClickListener
            }

            val altura = alturaText.toDouble()
            val peso = pesoText.toDouble()
            val res = peso / (altura * altura)
            binding.result.text = "%.2f".format(res)

            val mensagem = when {
                res < 18.5 -> "Abaixo do peso"
                res < 25 -> "Peso normal"
                res < 30 -> "Sobrepeso"
                else -> "Obesidade"
            }

            val fraseMotivacional = when {
                res < 18.5 -> {
                    val frasesAbaixoDoPeso = arrayOf(
                        "Você está no caminho certo para uma vida saudável. Continue se cuidando!",
                        "Você é mais forte do que pensa. Mantenha-se comprometido com seu bem-estar.",
                        "Cada passo em direção à saúde é um passo na direção certa. Parabéns por estar cuidando de si mesmo!",
                        "Lembre-se de que sua jornada de saúde é única e pessoal. Continue se esforçando!",
                        "Sua dedicação à sua saúde é inspiradora. Continue assim e alcance seus objetivos!",
                        "Pequenas mudanças levam a grandes resultados. Continue a buscar um estilo de vida saudável.",
                        "A determinação que você tem em cuidar de si mesmo é admirável. Continue focado em sua saúde!",
                        "Você está investindo em seu bem-estar, e isso é uma conquista incrível. Continue assim!",
                        "Você tem o poder de criar a melhor versão de si mesmo. Continue a trilhar esse caminho.",
                        "Cada escolha saudável que você faz é um passo na direção de uma vida plena e vibrante. Parabéns!"
                    )
                    frasesAbaixoDoPeso.random()
                }

                res < 25 -> {
                    val frasesPesoNormal = arrayOf(
                        "Seu peso saudável é um reflexo do cuidado que você tem com seu corpo. Continue assim!",
                        "Sua dedicação à saúde está rendendo bons resultados. Continue a manter seu equilíbrio.",
                        "Você está no caminho certo para uma vida saudável. Continue se alimentando bem e se exercitando regularmente.",
                        "Continue a cuidar do seu corpo, pois ele é o seu maior aliado. Parabéns pelo peso saudável!",
                        "Seu peso está dentro da faixa saudável, o que mostra seu compromisso com uma vida equilibrada.",
                        "Sua disciplina em manter um peso normal é admirável. Continue a fazer escolhas saudáveis!",
                        "Você encontrou o equilíbrio perfeito. Continue cuidando de si mesmo e mantendo seu peso saudável.",
                        "Parabéns por alcançar e manter um peso saudável. Você é um exemplo de determinação e comprometimento.",
                        "Seu corpo e mente agradecem por sua dedicação à saúde. Continue nesse caminho!",
                        "Você está colhendo os benefícios de um estilo de vida saudável. Continue se cuidando e seja inspiração para os outros!"
                    )
                    frasesPesoNormal.random()
                }

                res < 30 -> {
                    val frasesSobrepeso = arrayOf(
                        "Você está no caminho certo para conquistar um peso saudável. Continue se dedicando!",
                        "Sua determinação em buscar um peso ideal é inspiradora. Continue a se esforçar!",
                        "Você tem o poder de transformar seu corpo e sua saúde. Continue a lutar pelo peso saudável.",
                        "Pequenas mudanças podem fazer uma grande diferença. Continue a adotar hábitos saudáveis!",
                        "Lembre-se de que cada esforço conta. Mantenha-se comprometido com sua saúde e bem-estar.",
                        "Você está trabalhando duro para alcançar seu peso ideal. Continue nessa jornada!",
                        "Sua determinação e perseverança irão levá-lo ao sucesso. Não desista!",
                        "Você tem o poder de mudar sua história. Continue firme em sua busca pelo peso saudável.",
                        "Sua determinação em alcançar um peso saudável é admirável. Continue se esforçando!",
                        "Você está no caminho certo para uma vida mais saudável. Continue se dedicando!"
                    )
                    frasesSobrepeso.random()
                }

                res > 31 -> {
                    val frasesObesidade = arrayOf(
                        "A obesidade pode levar a sérias complicações de saúde. Cuide de si mesmo!",
                        "A obesidade é um fator de risco para diversas doenças graves. É hora de tomar medidas!",
                        "A obesidade pode reduzir significativamente sua qualidade de vida. Procure ajuda e faça mudanças!",
                        "A obesidade pode sobrecarregar o coração e aumentar o risco de doenças cardiovasculares. Cuide do seu coração!",
                        "A obesidade está associada a um maior risco de desenvolver diabetes tipo 2. Aja agora para prevenir!",
                        "A obesidade pode causar problemas nas articulações e dificuldades de mobilidade. Faça escolhas saudáveis!",
                        "A obesidade afeta negativamente a saúde mental e emocional. Busque apoio e tratamento adequado!",
                        "A obesidade é um fator de risco para certos tipos de câncer. Esteja ciente dos perigos!",
                        "A obesidade pode reduzir a expectativa de vida e aumentar a chance de morte prematura. Priorize sua saúde!",
                        "A obesidade não deve ser subestimada. Procure orientação médica e adote um estilo de vida saudável!"
                    )
                    frasesObesidade.random()
                }


                else -> {}
            }


            binding.message.text = mensagem
            binding.frase.text = fraseMotivacional.toString()

            // Ocultar o teclado
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        text()

    }

    fun text() {
        val alturaEditText: EditText = findViewById(R.id.height)

        alturaEditText.addTextChangedListener(object : TextWatcher {
            private var isEditing: Boolean = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Nada a fazer aqui
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Nada a fazer aqui
            }

            override fun afterTextChanged(s: Editable?) {
                if (isEditing) return

                isEditing = true

                // Remove qualquer ponto existente
                val textWithoutDots = s.toString().replace(".", "")

                // Remove caracteres indesejados, exceto dígitos e ponto decimal
                val cleanText = textWithoutDots.replace(Regex("[^\\d.]"), "")

                // Verifica se há pelo menos um número antes do ponto
                if (cleanText.isNotEmpty()) {
                    val intValue = cleanText.toInt()
                    val formattedValue = intValue / 100.toFloat()

                    // Atualiza o texto com o ponto no lugar adequado
                    alturaEditText.setText(String.format(Locale.US, "%.2f", formattedValue))
                    alturaEditText.setSelection(alturaEditText.text.length)
                }

                isEditing = false
            }
        })
    }
}