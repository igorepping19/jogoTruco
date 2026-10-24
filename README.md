# 🃏 Jogo de Truco em Java

## 📌 Sobre o projeto

Este projeto consiste no desenvolvimento de um jogo de Truco utilizando a linguagem Java, com foco na aplicação dos conceitos de programação orientada a objetos, organização de classes e implementação das regras do jogo.

O jogo utiliza cartas, jogadores, rodadas e um sistema de pontuação para controlar o andamento da partida.

## 🎮 Funcionalidades

- Criação de um baralho com 44 cartas.
- Embaralhamento aleatório das cartas.
- Distribuição de três cartas para cada jogador.
- Revelação da carta vira para determinar as manilhas.
- Cálculo da força das cartas.
- Comparação das cartas para determinar a dupla vencedora da rodada.
- Sistema de pontuação com opções de Truco, Seis, Nove e Doze.
- Controle da pontuação até 12 pontos.

## 🛠️ Tecnologias utilizadas

- **Java** — linguagem de programação utilizada no desenvolvimento.
- **Programação Orientada a Objetos (POO)** — organização do código em classes e objetos.

## 📂 Estrutura do projeto

O projeto é organizado nas seguintes classes:

| Classe | Responsabilidade |
|---|---|
| `Carta.java` | Representa uma carta, armazenando seu número e naipe. |
| `Baralho.java` | Cria, embaralha e distribui as cartas, além de revelar a carta vira. |
| `Jogador.java` | Armazena as informações do jogador e controla suas cartas. |
| `Rodada.java` | Determina as manilhas, calcula a força das cartas e compara as cartas jogadas. |
| `Placar.java` | Controla a pontuação das duplas e identifica quando uma delas atinge 12 pontos. |
| `Partida.java` | Controla o fluxo principal da partida. |

## ⚙️ Como executar

### Pré-requisitos

- Java JDK instalado.
- Uma IDE compatível com Java, como IntelliJ IDEA, Eclipse ou Visual Studio Code, ou um terminal com acesso ao compilador Java.

### Executando pela IDE

1. Clone ou baixe este repositório.
2. Abra o projeto na IDE.
3. Verifique se todos os arquivos `.java` estão organizados corretamente.
4. Execute a classe `Partida.java`, que contém o fluxo principal do jogo.

### Executando pelo terminal

Caso os arquivos estejam no mesmo diretório e não utilizem declarações de `package`, siga estes passos:

1. Abra o terminal na pasta do projeto.
2. Compile os arquivos Java:

   ```bash
   javac *.java
   ```

3. Execute a classe principal:

   ```bash
   java Partida
   ```

## 📚 Objetivo acadêmico

O projeto foi desenvolvido com o objetivo de praticar conceitos fundamentais da programação em Java, incluindo:

- Criação e utilização de classes e objetos.
- Atributos e métodos.
- Construtores.
- Arrays e manipulação de dados.
- Estruturas condicionais (`if`, `else` e `switch`).
- Estruturas de repetição (`for`).
- Métodos com parâmetros e retornos.
- Organização e divisão de responsabilidades entre classes.

## 👨‍💻 Autor

Desenvolvido como projeto de estudo em Java.