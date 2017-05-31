# Desafio 1: Strings

Após ler o coding style do kernel Linux, você descobre a mágica que é 
ter linhas de código com, no máximo, 80 caracteres cada uma.

Assim, você decide que de hoje em diante, seus e-mails enviados também 
seguirão este padrão e resolve desenvolver um plugin para te ajudar
com isso.

Implemente uma função que receba a. um texto qualquer e b. um limite de
comprimento e seja capaz de gerar os outputs dos desafios abaixo.

## Exemplo input

>In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.

>And God said, "Let there be light," and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light "day," and the darkness he called "night." And there was evening, and there was morning - the first day.


### Parte 1 (Básico) - limite 40 caracteres
Nesta parte, basta limitar em 40 caracteres por linha.

```
In the beginning God created the heavens
and the earth. Now the earth was
formless and empty, darkness was over
the surface of the deep, and the Spirit
of God was hovering over the waters.
```

```
And God said, "Let there be light," and
there was light. God saw that the light
was good, and he separated the light
from the darkness. God called the light
"day," and the darkness he called
"night." And there was evening, and
there was morning - the first day.
 ```

### Parte 2 (Intermediário) - limite 40 caracteres
Nesta parte, o output do texto deve estar **justificado**.

```
In the beginning God created the heavens
and   the  earth.   Now  the  earth  was
formless  and empty,  darkness was  over
the  surface of the deep, and the Spirit
of  God was  hovering over  the  waters.

And  God said, "Let there be light," and
there  was light. God saw that the light
was  good, and  he separated  the  light
from  the darkness. God called the light
"day,"   and  the   darkness  he  called
"night."  And  there  was  evening,  and
 there  was  morning  -  the  first  day.
```
