# story-challenge

O app foi desenvolvido utilizando MVVM com Clean Architecture, está separado em quatro camadas: **Data, Domain, Presentation e DI (dependency injection)**

<img src="/CA-MVVM.png" alt="MVVM com Clean Architecture"/>

  ## Camada Data: 
  responsável por decidir qual a fonte em que devem ser recuperados os dados (o aplicativo utiliza somente a rede, mas poderia ser incluído facilmente um banco local)
  
  ## Camada Domain:
  contém os casos de uso da aplicação e é responsável por qualquer futura regra de negócio a ser implementada, contém as interfaces para diálogo com a camada de Data
  
  ## Camada Presentation:
  responsável por como os dados são apresentados na tela do celular
  
  ## Camada DI:
  responsável por injeção de dependências
  
## TODO list:
  
  - Inserir loading
  - Criar splash
  - Inserir ícones
  - Inserir videos no formato m3u8 para melhor visualização (streaming)


