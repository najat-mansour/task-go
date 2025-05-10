# Code Architecture 
**The project follows the standard Spring-Boot REST APIs architecture as the following:**

### <u>Production Code</u>
* **Entities**
* **DTOs** (Data Transfer Objects) 
* **Mappers** using <u>MapStruct</u> dependency
* **Constants**
    * Enums 
    * Other Application Constants such as URIs. 
* **Exceptions**
* **Utilities** 
* **Repositories** 
* **Services**
* **Controllers** 
    * Standard Controllers
    * Controller Advice for handling the exceptions. 

### <u>Test Code</u>
* **Testing Repositories** using <u>DataJpaTest</u> 
* **Testing Services** using <u>MockitoExtension</u>
* **Testing Controllers** using <u>MockMvc</u>