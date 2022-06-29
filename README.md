# Api Pagamento - Redis Json

## Sumário 

- [Conceito: Redis](#conceito-redis)
- [Conceito: Redis Json](#conceito-redis_json)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Configuração](#configuração)

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






## Tecnologias Utilizadas

- Java
- Spring Boot
- Docker
- Lombok
- PostgreSQL
- Redis
- Redis Json

## Configuração


