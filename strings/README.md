# Desafio 1: Strings

Após ler o coding style do kernel Linux, você descobre a mágica que é 
ter linhas de código com no máximo 80 caracteres cada uma.

Assim, você decide que de hoje em diante seus e-mails enviados também 
seguirão um padrão parecido e resolve desenvolver um plugin para te ajudar
com isso. Contudo, seu plugin aceitará no máximo 40 caracteres por linha.

Implemente uma função que receba: 
1. um texto qualquer e 
2. um limite de
comprimento e seja capaz de gerar os outputs dos desafios abaixo.

## Exemplo input

`In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.`

`And God said, "Let there be light," and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light "day," and the darkness he called "night." And there was evening, and there was morning - the first day.`

O texto deve ser parametrizável e se quiser, pode utilizar um texto de input de sua preferência.

### Parte 1 (Básico) - limite 40 caracteres
Você deve seguir o exemplo de output [deste arquivo](https://github.com/idwall/desafios/blob/master/strings/output_parte1.txt), onde basta o texto possuir, no máximo, 40 caracteres por linha. As palavras não podem ser quebraadas no meio.

### Parte 2 (Intermediário) - limite 40 caracteres
O exemplo de output está [neste arquivo](https://github.com/idwall/desafios/blob/master/strings/output-parte2.txt), onde além de o arquivo possuir, no máximo, 40 caracteres por linha, o texto deve estar **justificado**.

### Extras

- Tratamento de erros e exceções. Fica a seu critério quais casos deseja tratar e como serão tratados.
- Testes unitários ou de integração.
- Uso do Docker.
- Parametrização da quantidade de caracteres por linha.
- Utilizar as versões mais atuais da linguagem que escolher para desenvolver (JavaScript ES6+; Java 8; Python 3, etc).
