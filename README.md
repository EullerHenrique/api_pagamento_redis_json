# Api Pagamento - Redis Json

## Sumário 

- [Conceito: Redis](#conceito-redis)
- [Conceito: Redis Json](#conceito-redis-json)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Configuração](#configuração)
- [EndPoints](#endpoints)
## Conceito: Redis

"
O Redis, que significa Remote Dictionary Server, é um datastore de chave-valor rápido e de código aberto na memória. O projeto começou quando Salvatore Sanfilippo, o desenvolvedor original do Redis, quis melhorar a escalabilidade de sua startup italiana. A partir daí, ele desenvolveu o Redis, que agora é usado como banco de dados, cache, agente de mensagens e fila.

O Redis oferece tempos de resposta inferiores a um milissegundo, permitindo milhões de solicitações por segundo para aplicações em tempo real em setores como jogos, tecnologia de anúncios, serviços financeiros, saúde e IoT. Hoje, o Redis é um dos mecanismos de código aberto mais usados, chamado de o banco de dados “preferido” pelo Stack Overflow por cinco anos consecutivos. Por causa de sua rápida performance, o Redis é uma opção popular para armazenamento em cache, gerenciamento de sessão, jogos, placares, análises em tempo real, dados geoespaciais, chamada de corrida, conversa/sistema de mensagens, transmissão de mídia e aplicações pub/sub.

Benefícios do Redis

Performance

Todos os dados do Redis residem na memória, o que permite acesso a dados de baixa latência e alta taxa de transferência. Diferentemente dos bancos de dados tradicionais, os datastores na memória não exigem uma viagem ao disco/SSD, reduzindo a latência do mecanismo para microssegundos. Por causa disso, os datastores na memória podem suportar uma ordem de magnitude a mais de operações e tempos de resposta mais rápidos. O resultado é uma performance excepcional, com operações de leitura ou gravação demorando em média menos de um milissegundo e suporte a milhões de operações por segundo.

Estruturas de dados flexíveis

Ao contrário de outros datastores de chave-valor que oferecem estruturas de dados limitadas, o Redis tem uma grande variedade de estruturas de dados para atender às necessidades de suas aplicações. Os tipos de dados do Redis incluem:

  Strings – dados em texto ou binários com tamanho de até 512 MB
  Listas – uma coleção de strings na ordem em que foram adicionadas
  Conjuntos: uma coleção não ordenada de strings com a capacidade de fazer a intersecção, união e diferenciação de outros tipos de conjunto
  Conjuntos ordenados: conjuntos ordenados por um valor
  Hashes: uma estrutura de dados para armazenar uma lista de campos e valores
  Bitmaps: um tipo de dados que oferece operações de nível de bits
  HyperLogLogs: uma estrutura de dados probabilística para estimar os itens únicos em um conjunto de dados
  Streams: uma fila de mensagens de estrutura de dados de log
  Dados geoespaciais: um mapa de registros com base em longitude/latitude, “proximidades”
  JSON - um objeto aninhado e semiestruturado de valores nomeados que suportam números, strings, booleanos, matrizes e outros objetos
 
Simplicidade e facilidade de uso

O Redis permite que você escreva código tradicionalmente complexo com linhas mais simples e em menor quantidade. Com o Redis, você escreve menos linhas de código para armazenar, acessar e usar dados em suas aplicações. A diferença é que os desenvolvedores que usam o Redis podem usar uma estrutura de comando simples, em oposição às linguagens de consulta de bancos de dados tradicionais. Por exemplo, você pode usar a estrutura de dados de hash do Redis para migrar dados para um datastore com apenas uma linha de código. Uma tarefa similar, em um datastore sem estruturas de dados de hash, exigiria muitas linhas de código para a conversão de um formato para o outro. O Redis é fornecido com estruturas de dados nativas e várias opções para manipular e interagir com dados. Mais de cem clientes de código aberto estão disponíveis para os desenvolvedores do Redis. As linguagens compatíveis incluem Java, Python, PHP, C, C++, C#, JavaScript, Node.js, Ruby, R, Go e muitas outras.

Replicação e persistência

O Redis emprega uma arquitetura de réplica principal e oferece suporte à replicação assíncrona, o que permite a replicação de dados para vários servidores de réplica. Essa arquitetura oferece maior performance de leitura (com a distribuição das solicitações entre vários servidores) e recuperação quando o servidor primário sofre uma interrupção. Para proporcionar persistência, o Redis oferece suporte a backups em um ponto anterior no tempo (cópia do conjunto de dados do Redis para o disco). 

Para disponibilizar durabilidade, o Redis oferece compatibilidade com snapshots point-in-time (copiando o conjunto de dados do Redis no disco) e criando um Append Only File (AOF) para armazenar cada alteração de dados no disco conforme elas vão sendo gravadas. Os dois métodos permitem a restauração rápida dos dados do Redis no caso de uma interrupção.

Alta disponibilidade e escalabilidade

O Redis oferece uma arquitetura de réplica principal em um único nó principal ou em uma topologia de clusters. Dessa forma, você pode criar soluções altamente disponíveis que oferecem performance e confiabilidade consistentes. Quando for necessário ajustar o tamanho do cluster, você poderá usar uma das diversas opções de escalabilidade vertical ou horizontal disponíveis. Assim, o cluster pode crescer com a demanda.

Versatilidade e facilidade de uso

O Redis é disponibilizado com várias ferramentas que tornam o desenvolvimento e as operações mais rápidas e fáceis, inclusive o PUB/SUB para publicar mensagens nos canais que são entregues para os assinantes, o que é ótimo para sistemas de mensagens e chat, as chaves com TTL podem ter um tempo de vida útil determinado, após a qual elas se autoexcluem, o que ajuda a evitar sobrecarregar o banco de dados com itens desnecessários, os contadores atômicos garantem que condições de corrida não criem resultados incompatíveis, além da Lua, uma linguagem de script potente, porém leve.

Código aberto

O Redis é um projeto de código aberto que conta com o suporte de uma comunidade vibrante, incluindo a AWS. Não há aprisionamento a nenhum fornecedor ou tecnologia, pois o Redis é baseado em padrões abertos, compatível com formatos de dados abertos e disponibiliza um rico conjunto de clientes.

Casos de uso populares do Redis

Armazenamento em cache

O Redis é uma excelente escolha para a implementação de caches de memória altamente disponíveis para diminuir a latência de acesso aos dados, aumentar a produtividade e aliviar a sobrecarga de aplicativos e bancos de dados relacionais ou NoSQL. O Redis pode fornecer itens frequentemente solicitados com um tempo de resposta inferior a um milissegundo. Além disso, pode escalar facilmente para processar cargas maiores sem necessidade de aumentar o back-end de alto custo. Entre os exemplos mais comuns de armazenamento em cache com o Redis, estão resultados de consultas de banco de dados, sessões persistentes, páginas web e objetos frequentemente usados como imagens, arquivos e metadados.

Chat, sistemas de mensagens e filas

O Redis oferece suporte a Pub/Sub com correspondência de padrões e a várias estruturas de dados como listas, conjuntos ordenados e hashes. Assim, o Redis pode oferecer suporte a salas de chat de alta performance, streams de comentários em tempo real, feeds de mídia social e intercomunicação de servidores. A estrutura de dados Redis List facilita a implementação de uma fila leve. As listas oferecem operações atômicas e recursos de bloqueio e são adequadas para vários aplicativos que exigem um agente de mensagens ou uma lista circular confiável.

Placares de jogos

O Redis é uma escolha comum de desenvolvedores de jogos que precisam criar placares em tempo real. Basta usar a estrutura de dados Sorted Set do Redis que disponibiliza a especificidade de elementos enquanto mantém a lista classificada de acordo com suas pontuações. A criação de uma lista classificada em tempo real é tão fácil quanto atualizar a pontuação de um usuário toda vez que ela muda. Você também pode usar conjuntos classificados para processar dados de séries temporais usando time stamps como pontuação.

Armazenamento de sessões

O Redis é um datastore na memória com alta disponibilidade e persistência, escolhido frequentemente por desenvolvedores de aplicativos para armazenar e gerenciar dados de sessão para aplicativos na escala da Internet. O Redis oferece a latência inferior a um milissegundo, a escala e a resiliência necessárias para gerenciar dados de sessão como perfis de usuário, credenciais, estados de sessão e personalizações específicas de usuários.

O Redis é altamente indicado para tarefas de gerenciamento de sessões. Basta usar o Redis como um armazenamento de chave-valor com o tempo de vida (TTL) correto nas chaves de sessão para gerenciar suas informações de sessão. O gerenciamento de sessões é comumente exigido para aplicações on-line, como jogos, sites de comércio eletrônico e plataformas de mídia social.

Streaming de mídia avançada

O Redis oferece um datastore rápido na memória para viabilizar casos de uso de streaming ao vivo. O Redis pode ser usado para armazenar metadados sobre perfis de usuários, visualização de históricos e informações/tokens de autenticação para milhões de usuários, bem como armazenar arquivos manifesto para possibilitar que CDNs façam streaming de vídeo para milhões de usuários de dispositivos móveis e desktops ao mesmo tempo.

Dados geoespaciais

O Redis oferece estruturas e operadores de dados na memória para uso específico, o que permite gerenciar em tempo real dados geoespaciais em grande escala e velocidade. Comandos como GEOADD, GEODIST, GEORADIUS e GEORADIUSBYMEMBER para armazenar, processar e analisar dados geoespaciais em tempo real facilitam e agilizam o uso de recursos geoespaciais com o Redis. Você pode usar o Redis para adicionar aos aplicativos recursos baseados em localização como tempo de percurso, distância do percurso e pontos de interesse.

Machine Learning

Aplicativos modernos e orientados a dados exigem machine learning para processar rapidamente um grande volume e variedade de dados e automatizar a tomada de decisões. Para casos de uso como detecção de fraudes em jogos e serviços financeiros, fazer ofertas em tempo real em tecnologia de anúncios e matchmaking para encontros e transporte solidário, a capacidade de processar dados ao vivo e tomar decisões em dezenas de milissegundos é fundamental. O Redis oferece um datastore ágil na memória para criar, treinar e implantar rapidamente modelos de Machine Learning.

Análise em tempo real

O Redis pode ser usado com soluções de streaming como Apache Kafka e Amazon Kinesis, atuando como datastore na memória para consumir, processar e analisar dados em tempo real com latência inferior a um milissegundo. O Redis é uma escolha ideal para casos de uso de análises em tempo real, como análises de mídia social, direcionamento de anúncios, personalização e IoT.

Limite de taxa

O Redis pode calcular e, quando necessário, acelerar a taxa dos eventos. Ao usar um contador do Redis associado a uma chave de API do cliente, você poderá contar o número de solicitações de acesso dentro de um determinado período e tomar as ações necessárias, caso um limite seja excedido. Os limitadores de taxa são usados comumente para limitar o número de publicações em um fórum, limitar a utilização de recursos e conter o impacto de remetentes de spam.

" - Fonte:  https://aws.amazon.com/pt/redis/
            https://aws.amazon.com/pt/elasticache/what-is-redis/
            
## Conceito: Redis Json

"

Por padrão o formato JSON é armazenado no Redis como strings escapadas, essa característica pode não atrapalhar se você deseja somente adicionar e recuperar essas informações em sua totalidade, os problemas realmente começam quando precisamos fazer uma busca em JSONs aninhados ou atualizar campos dentro de um JSON já persistido no Redis. Os problemas ocorrem pois para fazer buscas mais complexas ou atualizar informações dentro de um JSON salvo como string, precisamos recuperá-las no Redis e fazer com que essas manipulações aconteçam no lado da aplicação.

Aplicação   <-  Redis

A aplicação deve recuperar a string escapada do Redis e transformá-la em formato JSON para entào a manipular

Aplicação -> Redis

As buscas e as mudanças ocorrem no lado da aplicação, e caso necessário a aplicação deve enviar o dado novamente para que o Redis possa persistir

A abordagem acima apresenta dois grandes problemas que podem impactar sua aplicação.

A primeira delas é que sua aplicação deve buscar os dados e reenviar ao Redis caso seja necessário, isso implica em aumentar o tráfego de dados na rede, o que por consequência acaba por aumentar a latência da rede como um todo, esse problema pode se agravar ainda mais caso o JSON recuperado seja grande, com muitos níveis ou se essa manipulação ocorrer com frequência.

O outro problema é em relação à performance da sua aplicação, pois no esquema acima estamos delegando a ela a obrigação de executar ações que normalmente os bancos de dados deveriam executar, além claro, de aumentarmos a complexidade do código, visto que estamos adicionando mais responsabilidades a ele.

RedisJSON

RedisJSON é um módulo para o Redis que nos permite tratar dados em formato JSON como uma tipo nativo de estrutura de dados do Redis, muito similar a um banco não relacional baseado em documentos, porém com bem menos funcionalidades que o mesmo pode oferecer.

O RedisJSON apresenta uma sintaxe simples de entender até mesmo por desenvolvedores menos experientes.

Basicamente o RedisJSON adiciona uma série de comandos que facilitam a manipulação de dados em formato JSON dentro do Redis, todos os comando existentes podem ser encontrados na documentação oficial do módulo (https://redis.io/commands/?group=json). A ideia de funcionamento é simples, o JSON passado para o Redis é analisado pela json-sl, uma lib que executa análises léxicas no JSON em questão e contrói uma árvore a partir dele. Demonstrado pelo esquema a baixo:

{ 
  "foo": "bar",
  "ans": 42
}

   |
   v

  root
       
   |
   v

Type: object
     
   foo           ans
    |             |
    v             v
Type: string  Type: number
   "bar"        42

   
" - Fonte:  https://medium.com/@1fabiopereira/redisjson-manipulando-json-como-tipo-nativo-no-redis-3736e1fba832

"
RedisJSON é um módulo Redis que fornece suporte JSON no Redis. O RedisJSON permite que você armazene, atualize e recupere valores JSON no Redis da mesma forma que faria com qualquer outro tipo de dados do Redis. O RedisJSON também funciona perfeitamente com o RediSearch para permitir que você indexe e consulte seus documentos JSON.

Recursos principais

Suporte completo para o padrão JSON
Uma sintaxe JSONPath para selecionar/atualizar elementos dentro de documentos
Documentos armazenados como dados binários em uma estrutura de árvore, permitindo acesso rápido aos subelementos
Operações atômicas tipadas para todos os tipos de valores JSON

" - Fonte: https://redis.io/docs/stack/json/

"

"

Limite de tamanho do documento
Os documentos JSON são armazenados internamente em um formato otimizado para acesso e modificação rápidos. Esse formato geralmente resulta em consumir um pouco mais de memória do que a representação serializada equivalente do mesmo documento. O consumo de memória por um único documento JSON é limitado a 64 MB, que é o tamanho da estrutura de dados na memória, não a string JSON. A quantidade de memória consumida por um documento JSON pode ser inspecionada usandoJSON.DEBUG MEMORYcomando.

Limite de profundidade de agrupamento
Quando um objeto ou matriz JSON tem um elemento que é outro objeto ou matriz JSON, esse objeto interno ou matriz é dito “aninhar” dentro do objeto ou matriz externa. O limite máximo de profundidade de aninhamento é 128. Qualquer tentativa de criar um documento que contenha uma profundidade de aninhamento maior que 128 será rejeitada com um erro.

" - Fonte: https://docs.aws.amazon.com/pt_br/memorydb/latest/devguide/json-document-overview.html#json-nesting-depth-limit


## Tecnologias Utilizadas

- Java
- Spring Boot
- JPA
- Hibernate
- Postgresql
- Lombok
- Swagger
- ModelMapper
- Gson
- Docker
- Redis
- Redis Json

## Configuração

### Docker
  
  1. Clone o repósitorio
  2. Instale o docker (https://www.docker.com/products/docker-desktop/)
  3. Abra o docker
  4. Abra o terminal
  5. Navegue até a pasta src/main/resource
  6. Digite docker-compose up -d
  
### Spring Boot
  
  1. Abra a pasta login_back-end em uma IDE (Ex: IntelliJ IDEA) 
  2. Navegue pela IDE até ApiPagamentoRedisJsonApplicatio
  3. Aperte o botão play localizado ao lado de "public class ApiPagamentoRedisJsonApplication"

### Swagger

  1. Acesse o swagger por meio do link: http://localhost:8080/swagger-ui/index.html ou realize as requisições por meio do postman
  
### Redis/Redis Json

  1. Abra o terminal
  2. Digite docker exec -it redis-json bash para abrir o terminal do container redis-json
  3. Digite redis-cli --raw (--raw: exibe os dados codificados em UTF8)
  4. Digite keys * para exibir as keys salvas
  5. Digite JSON.GET <-nome-da-key-> para exibir o valor de determinada key

## EndPoints
  
  ### localhost:8080/transacao/v1/pagamento
  
  Request:
  
  ```
  { 
   "cartao": "4444********1234",
   "descricao": {
      "valor": "500.50",
      "dataHora": "01/05/2021 18:00:00",
      "estabelecimento": "PetShop Mundo cão"
    },
    "formaPagamento":{
        "tipo":"AVISTA",
        "parcelas": "1"
    }
  }
  ```

  Response:
  
  ```
  {
    "id": 1,
    "cartao": "4444********1234",
    "descricao": {
        "valor": "500.50",
        "dataHora": "01/05/2021 18:00:00",
        "estabelecimento": "PetShop Mundo cão",
        "nsu": "1234567890",
        "codigoAutorizacao": "147258369",
        "status": "AUTORIZADO"
    },
    "formaPagamento": {
        "tipo": "AVISTA",
        "parcelas": "1"
    }
  }
  ```
  
  ### localhost:8080/transacao/v1/estorno/1
  
  Response: 
  
  ```
  {
    "id": 1,
    "cartao": "4444********1234",
    "descricao": {
        "valor": "500.50",
        "dataHora": "01/05/2021 18:00:00",
        "estabelecimento": "PetShop Mundo cão",
        "nsu": "1234567890",
        "codigoAutorizacao": "147258369",
        "status": "NEGADO"
    },
    "formaPagamento": {
        "tipo": "AVISTA",
        "parcelas": "1"
    }
  }
  ```
  
  ### localhost:8080/transacao/v1/1
  
  Response:
  
  ```
  {
    "id": 1,
    "cartao": "4444********1234",
    "descricao": {
        "valor": "500.50",
        "dataHora": "01/05/2021 18:00:00",
        "estabelecimento": "PetShop Mundo cão",
        "nsu": "1234567890",
        "codigoAutorizacao": "147258369",
        "status": "NEGADO"
    },
    "formaPagamento": {
        "tipo": "AVISTA",
        "parcelas": "1"
    }
  }
  ```
  
  ### localhost:8080/transacao/v1
  
  Response:
  
  ```
  [
    {
        "id": 1,
        "cartao": "4444********1234",
        "descricao": {
            "valor": "500.50",
            "dataHora": "01/05/2021 18:00:00",
            "estabelecimento": "PetShop Mundo cão",
            "nsu": "1234567890",
            "codigoAutorizacao": "147258369",
            "status": "NEGADO"
        },
        "formaPagamento": {
            "tipo": "AVISTA",
            "parcelas": "1"
        }
    },
    {
        "id": 2,
        "cartao": "4444********1234",
        "descricao": {
            "valor": "500.50",
            "dataHora": "01/05/2021 18:00:00",
            "estabelecimento": "PetShop Mundo cão",
            "nsu": "1234567890",
            "codigoAutorizacao": "147258369",
            "status": "AUTORIZADO"
        },
        "formaPagamento": {
            "tipo": "AVISTA",
            "parcelas": "1"
        }
    }
  ]
  ```




