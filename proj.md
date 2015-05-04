# O Projeto #

**[<<Voltar](https://code.google.com/p/rnp-videoplayer/)**

## 1. Introdução ##
Este é um subprojeto faz para do Projeto Serviço Experimental EDAD, desenvolvido pelo LAND/UFRJ e financiado pela RNP. Ele tem como objeto criar uma ferramenta multiplataforma que auxilie os usuários a gerar os elementos de sincronização que formam uma videoaula, sem que esses usuários necessitem de conhecimentos avançados sobre a estruturação dos arquivos a serem gerados no processo de sincronização.


## 2. Especificação da Ferramenta de Sincronização ##
A Ferramenta de Sincronização de videoaulas para o Sistema RIO tem como objetivo principal, facilitar a sincronização entre as mídias (vídeo, tópicos, e transparências) e gerar os arquivos de sincronização entre as mídias utilizando uma interface gráfica integrada onde o usuário vai especificar os pontos de sincronização. Deve ser multiplataforma, focada principalmente na sua usabilidade. Para tal, foi definida como linguagem de desenvolvimento Java.
A Ferramenta de Sincronização terá as seguintes funcionalidades:
•	Ser capaz de importar e tocar um vídeo em diferentes formatos e sincronizar com as demais mídias seguindo o tempo do vídeo.
•	Gerar arquivos de sincronização com as transparências, sincronização com tópicos e informações gerais da aula (formato Sistema RIO em XML). Ao todo, são três arquivos XML que compõem uma videoaula.
•	Importar arquivos de sincronização gerados pela própria ferramenta (formato Sistema RIO em XML).
•	Importar um arquivo ASCII que contenha os tópicos da aula (um por linha, sem informação de hierarquia, que deverá ser informada posteriormente através da interface gráfica).
A Figura abaixo ilustra a proposta inicial de interface para a Ferramenta, gerada numa das reuniões do projeto no LAND/UFRJ.

Além da implementação das funcionalidades, será criado um tutorial de uso da ferramenta e toda a documentação do seu código, sendo que o mesmo será disponibilizado através de uma Licença GPL.
> Como atividade complementar, pretende-se que a ferramenta faça a conversão do  vídeo para o formato suportado pelo sistema cliente, o Codec h.264, com container FLV.

## 3. Equipe ##
A equipe envolvida no desenvolvimento deste subprojeto é Coordenada pelo Professor Eduardo Barrére (UFJF)(40h/mês) e pelos desenvolvedores Alexandre Bisaggio Ligorio (160h/mês) e Daves Marcio Silva Martins (40h/mês).


---

**[<<Voltar](https://code.google.com/p/rnp-videoplayer/)**