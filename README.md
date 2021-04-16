# story-challenge

The application was developed using MVVM with Clean Architecture, separated into four layers: **Data, Domain, Presentation and DI (dependency injection)**

<img src="/CA-MVVM.png" alt="MVVM com Clean Architecture"/>

  ## Layer Date:
  responsible for deciding which source the data should be retrieved from (the application uses only the network, but a local database could easily be included)
  
  ## Domain layer:
  contains the use cases of the application and is responsible for any future business rules to be implemented, contains the interfaces wich the Data layer implements
  
  ## Presentation layer:
  responsible for how the data is presented on the mobile screen
  
  ## DI layer:
  responsible for the injection of the dependencies
  
## TODO list:
  
  - Insert loading
  - Create splash
  - Insert icons
  - Insert videos in m3u8 format for better viewing (streaming)


