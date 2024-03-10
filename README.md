Movie app that enables to:
  - See popular movies
  - Now playing movies
  - Your favourite saved movies

Popular/Now playing movies relay on the movie db service.
  - You can toggle between a grid/list view to see results accordingly.
  - You can sort results by release date and name.
  - The results are chached in memory within the viewModelScope, taking advantage of the Paging Android component.
  - The recents movies ONLY caches the response from the service for 5 minues to improve performance and experience.
  - For each view type (grid and list) a specific UI item was created to improve the user experience.
  - Api calls are wrapped within and extension functions that maps HttpErrots to KueskiHttp errors to be consumed from the business/presentation layer if required.

You can click on any movie to see their details, and within that screen you can mark/unmark a movie as a favourite one 
(those ones will be saved in a db and persisted even if the app is closed)

*Authentication is done via an Authentication Interceptor which is injected to the NetworkFactory (incharge of creating instances of a http service)
*The app uses a clean architecture with a MVVM presentation layer to take advantage of Android wired components.
  - Model: Several models are used at different layers in order not to couple the solution and provide scalability (DTO, Entity, Business)
  - VM: Each view has their own view model which is provided and injected accordingly. Helper abstractions such as managers and repositories are injected to delegate operations.
  - View: Views interact with view models and observe results using livedata and the viewModelScope. ViewDatabinding was used XMLs
*To handle the data operations the repository pattern is used, where multiple data sources are injected (dao and apiClient abstraction).
*Dependency injection is handled with the library Koin
*Database is handled with the library Room
*Networking is handled with the library Retrofit
*A special set of components from Android for Paging was used (PagingDataAdapter, Paging, PagingData, etc)

Everything is unit tested
API_KEY is set locally for security reasons.


