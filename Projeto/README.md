# Objetivos

Neste projeto, abordamos o Problema de Aquisição de Contratos do Mercado Livre de Energia, um problema de otimização de grande relevância prática no contexto atual, visto sua importância econômica e sua relação com o desenvolvimento sustentável. Propõe-se este problema como mais uma ação da trajetória de formação discente em programação de computadores, com o objetivo de abordar conceitos e técnicas de construção e análise de algoritmos, fazendo uso de algoritmos de busca e de ordenação, e empregando diferentes estratégias de resolução de problemas para tratar situações reais.

# Contextualização

Com a abundância de sol em nosso planeta e seu enorme potencial para geração elétrica, cultivar a produção de energia solar se tornou tão vantajoso que se criou uma nova modalidade no mundo: a fazenda solar. Com a alta aplicabilidade da tecnologia fotovoltaica e a oferta da luz do sol em todo lugar, qualquer pessoa ou empresa pode gerar a sua energia e utilizá-la para diversos fins, seja economizar na conta de luz, seja revendê-la.

No Brasil, enquanto o número de pessoas gerando energia em suas casas para desconto na conta de luz sobe ano a ano, projetos de usinas solares também começam a ganhar ritmo e se espalham pelo país, principalmente no Nordeste. Devido às últimas regulamentações do segmento de geração distribuída, criou-se um novo modelo de comercialização de energia elétrica gerada por placas solares.

Vale ainda ressaltar que fazendas solares ganharam destaque devido ao uso de tecnologias de ponta e por deixarem 100% limpo o sistema elétrico de comunidades inteiras, tornando-se exemplo de sustentabilidade. 

# Mercado Livre de Energia

O Mercado Livre de Energia funciona como um ambiente de contratação livre, portanto, mais independente. Isto quer dizer que ele permite a comercialização de energia e estimula a livre concorrência entre produtores e geradores, tornando os custos para compra de energia elétrica mais acessíveis. Nesse modelo de contratação, o consumidor pode escolher seu fornecedor de energia de acordo com o SIN (Sistema Interligado Nacional) e negociar seus preços, preferências e conveniências.

Na verdade, os consumidores do mercado livre podem adquirir energia diretamente das fontes geradoras, como hidrelétricas e termelétricas, ou das comercializadoras, como concessionárias e permissionárias, através de contratos pelos quais são negociados o valor da tarifa, o prazo e o volume. Assim, esses consumidores podem pagar duas ou mais contas de energia, sendo uma pela distribuição (devida à distribuidora local) e as outras pelo valor da energia que comprou dos diferentes fornecedores.

O mercado livre conta assim com diversas vantagens, tais como poder de escolha entre diferentes opções de fornecedores, flexibilidade de escolha entre fontes de energia do SIN, concorrência que estimula a redução de preços e o gerenciamento do seu negócio de forma mais independente.

# Problematização

Considere que a sua equipe está sendo contratada por uma indústria com a finalidade de realizar, de forma econômica, a compra de contratos de energia para os próximos n meses (1, 2, …, n). No mercado, atuam m fornecedores de energia (1, 2, …, m) que oferecem contratos com diferentes horizontes de tempo e preços diversos. A sua equipe está encarregada em definir, dentre os contratos ofertados no mercado, um subconjunto que atenda de forma econômica a demanda de energia da indústria no período integral de n meses, sabendo que é possível manter contratos com diferentes fornecedores, ainda que se pague uma taxa t, paga à companhia de distribuição de energia, a cada mudança de fornecedor. Considere ainda que o consumo da empresa não varia ao longo dos meses. Assim, um contrato pode ser caracterizado por um fornecedor, um mês de início do fornecimento, um mês de fim do fornecimento e o valor do contrato. Como exemplo, o contrato <1; 1; 5; 10.0> refere-se a um contrato do fornecedor 1 que se compromete a fornecer energia durante os meses de 1 a 5 com um valor total de $10,0. Para um mesmo fornecedor, os contratos seguem as regras de formação de preço:

- Um contrato referente ao período completo do mês i ao mês j jamais possui valor inferior ao valor de um contrato cuja abrangência está contida entre o período do mês i ao mês j. Por exemplo, o contrato
<1; 1; 3; 108.0> tem obrigatoriamente valor maior ou igual ao valor dos contratos:
<1; 1; 1; 10.0>; ou
<1; 1; 2; 105.0>; ou
<1; 2; 3; 102.0>

- Um contrato referente ao período completo do mês i ao mês j jamais possui valor superior à soma dos valores de contratos que, em conjunto, perfazem o mesmo período do mês i ao mês j. Por exemplo, o contrato <1; 1; 3; 108.0> tem obrigatoriamente valor menor ou igual aos valores dos contratos:
<1; 1; 1; 10.0> + <1; 2; 2; 100.0> + <1; 3; 3; 10.0>, cujo valor total é $120; ou
<1; 1; 1; 10.0> + <1; 2; 3; 102.0>, cujo valor total é $112; ou
<1; 1; 2; 105.0> + <1; 3; 3; 10.0>, cujo valor total é $115