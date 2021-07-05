# mutant
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


URL de la API:

API-REST:
		
http://apirestmutant-env.eba-em2fbrig.us-east-1.elasticbeanstalk.com/mutant-api/api
		
API-REST SERVICES:

POST: http://apirestmutant-env.eba-em2fbrig.us-east-1.elasticbeanstalk.com/mutant-api/mutant/

GET: http://apirestmutant-env.eba-em2fbrig.us-east-1.elasticbeanstalk.com/mutant-api/stats
