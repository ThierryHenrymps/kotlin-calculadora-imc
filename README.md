# Calculadora de IMC
# IMC Calculator By henry

## Tecnologias utilizadas
- Kotlin 
- Jetpack Compose 
- Android Studio
- jetbrains android studio

## Como compilar e executar

1. Clonar o repositorio usando `git clone URL_DO_REPOSITORIO`.
2. Abrir o projeto no Android Studio.
3. Esperar carregar e sincronizar, para nao ter nenhum erro com dependencias ou instalacao.
4. Abrir o Device Manager e iniciar um emulador Android de preferencia pixel 4.
5. Clicar em Run para executar o aplicativo.
6. Digitar o peso e a altura, escolher se quer arredondar e clicar em Calcular.

## 2. Descricao dos arquivos e funcoes

- MainActivity.kt: arquivo principal do aplicativo.
- MainActivity: inicia o app e carrega a tela.
- IMCLayout(): monta a tela com os campos de peso e altura, o Switch, o botao e o resultado.
- classificarIMC(): verifica o valor do IMC e retorna a classificacao.
- Theme.kt: configura o tema visual do aplicativo.

## 3. Diagrama da hierarquia

```text
MainActivity
└── IMCLayout()
            └── Column
                ├── Text (Titulo)
                ├── TextField (Peso)
                ├── TextField (Altura)
                ├── Row
                │   ├── Text (Arredondar resultado)
                │   └── Switch
                ├── Button (Calcular)
                ├── Text (Mensagem de erro)
                └── Resultado
                    ├── Text (Valor do IMC)
                    └── Text (Classificacao)
```

## 4. Como funciona

Apos inserir os dados pedidos podendo em Peso em Kg e Altura em M, podendo ser valor decimal. 

O app calcula o IMC usando peso dividido pela altura vezes a altura. Depois mostra o resultado e a classificacao. O Switch permite arredondar o valor gerado apos isso
mostrara o IMC e a classificacao feito pela formula peso kg, altura  m → IMC = kg / (m × m) = IMC

As classificacoes sao: abaixo do peso, peso normal, sobrepeso e obesidade.

## Feito por:

- Thierry Henry Moreira Pimenta Silva
- UFCI-MG 3º Periodo 
