Ejercicio ADN Mutantes

# Enunciado:

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar
contra los X-Men.

Te ha contratado a ti para que desarrolles un proyecto que detecte si un
humano es mutante basándose en su secuencia de ADN.

Para eso te ha pedido crear un programa con un método o función con la siguiente firma (En
alguno de los siguiente lenguajes: Java / Golang / C-C++ / Javascript (node) / Python / Ruby):

# boolean isMutant(String[] dna); // Ejemplo Java

En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las
cuales representa cada base nitrogenada del ADN.

No-Mutante

| A | T | G | C | G | A |
|---|---|---|---|---|---|
| C | A | G | T | G | C |
| T | T | A | T | T | T |
| A | G | A | C | G | G |
| G | C | G | T | C | A |
| T | C | A | C | T | G |

Mutante

| A | T | G | C | G | A |
|---|---|---|---|---|---|
| C | A | G | T | G | C |
| T | T | A | T | G | T |
| A | G | A | A | G | G |
| C | C | C | C | T | A |
| T | C | A | C | T | G |

Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras
iguales, de forma oblicua, horizontal o vertical.

Ejemplo (Caso mutante):

String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

En este caso el llamado a la función isMutant(dna) devuelve "true".
Desarrolla el algoritmo de la manera más eficiente posible.

# SOLUCION

# API-REST en AWS
		
http://apirestmutant-env.eba-em2fbrig.us-east-1.elasticbeanstalk.com/mutant-api/api
		
# API-REST SERVICES

POST: http://apirestmutant-env.eba-em2fbrig.us-east-1.elasticbeanstalk.com/mutant-api/mutant/

GET: http://apirestmutant-env.eba-em2fbrig.us-east-1.elasticbeanstalk.com/mutant-api/stats

# Code coverage

![image](https://user-images.githubusercontent.com/86935152/124404776-f6c8e980-dd01-11eb-9306-d9a5ed7f5a14.png)

# DATOS DE CONEXION BASE DE DATOS MYSQL en API REST - AWS

HOSTNAME: aazl4x93emmjno.cczlsdoqgt4x.us-east-1.rds.amazonaws.com

PORT: 3306

USER: admin

PASSWORD: Mutant993$

SCHEMA: mutant_schema
