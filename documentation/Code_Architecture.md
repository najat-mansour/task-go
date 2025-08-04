# Code Architecture 
**The project follows the standard Spring-Boot REST APIs architecture as the following:**

### <u>Production Code</u>
* **Entities**
* **DTOs** (Data Transfer Objects) 
* **Mappers** using `MapStruct` dependency.
* **Constants**
    * Enums 
    * Other Application Constants such as `URLs`. 
* **Exceptions**
* **Utilities** 
    * `RandomsGeneratorUtil`  
* **Repositories** 
* **Services**
* **Controllers** 
    * Standard Controllers
    * REST Controller Advice for handling the exceptions. 
* **configs**
    * `GlobalApiPrefixConfig`
    * `WebSecurityConfig`
    * `SwaggerConfig`
    * `CahceConfig`
* **security**
    * `JwtUtil`
    * `AuthEntryPointJwt`
    * `AuthTokenFilter`
* **aspects**
    * `LoggingAspect`  
* **events**
    * Different Types of Events to be Published.    
* **listeners**
    * `CacheEvictionListener`

### <u>Test Code</u>
* **Testing Repositories** using `DataJpaTest`.
* **Testing Services** using `MockitoExtension`.
* **Testing Controllers** using `MockMvc`. 