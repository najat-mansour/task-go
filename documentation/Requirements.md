# Requirements 
**<u>TaskGo</u>** is a task management <u>Back-End REST API</u> developed in <u>Spring-Boot</u> framework that allows you to manage your tasks. 

### <u>Register (Sign Up)</u> 
**A user can register (sign up) using these fields:**
* username
* password 
* name (first name, last name)
* email 
* birthdate
* address (country, city, town, street)
* gender 
* image 

**NOTEs:** 
* The system generates an **UUID** for each user.
* Both **username** and **email** must be unique. 
* The system records the **creation date** for each user account. 
* The **password** must be at least <u>8-characters</u> and contains at least <u>one capital-letter</u>, <u>one small-letter</u>, <u>one digit</u> and <u>one special character</u>. 
* The **password** must be encrypted using <u>BCrypt</u>.
* The user can handle **forget password** case by <u>receiving a new password via his/her email</u>. 
* An **email verification** is required by entering <u>4-digit code</u> sent via the user's email. 
* The **user images** will be stored either in a <u>folder in the server side</u> or on a <u>cloud service such as AWS S3</u>. 

### <u>Login</u> 
**The user can login using his/her credentials:**
* username
* password

**NOTEs:**
* The authentication will be implemented using JWT (JSON Web Token). 

### <u>Editing Personal Information</u>
**The user can edit his/her personal information:**
* password 
* name (first name, last name)
* birthdate
* address (country, city, town, street)
* gender (MALE, FEMALE)

### <u>Workspaces</u> 
**The user can add a new workspace using these fields:**
* name
* description 

**NOTEs:**
* The system generates an **UUID** for each workspace. 
* The user can edit, delete and filter based on any field his/her workspaces. 
* The user can add other users to a workspace.

### <u>Groups of Tasks</u>
**The user can add a new group of tasks using these fields:**
* name
* color: just and indication of the group
* description 

**NOTEs:**
* The system generates an **UUID** for each group of tasks. 
* The user can edit, delete and filter based on any field his/her groups of tasks. 

### <u>Tasks</u>
**The user can add a new task using these fields:**
* name
* description 
* status (NOT STARTED, IN PROGRESS, PENDING, FINISHED)
* priority (HIGH, MEDIUM, LOW)
* starting timestamp 
* ending timestamp 
* group 
* owner: the user who is selected to do the task

**NOTEs:**
* The system generates an **UUID** for each task. 
* The user can add any task to the favorite ones. 
* The user can edit, delete and filter based on any field his/her tasks. 

### <u>Sub-Tasks</u>
**The user can add sub-tasks for any task using these fields:**
* name
* description 
* status (NOT STARTED, IN PROGRESS, PENDING, FINISHED)
* priority (HIGH, MEDIUM, LOW)
* starting timestamp 
* ending timestamp 
* task 

**NOTEs:**
* The system generates an **UUID** for each sub-task. 
* The user can edit, delete and filter based on any field his/her sub-tasks. 

<img 
    src="https://i.postimg.cc/NjbPzrcj/Hierarchy-Figure.png" 
    alt="Hierarchy_Figure"
/>

### <u>Other Requirements</u>
**The system handles also the following requirements:**
* The user will be notified via his/her email when:
    * Another user added his/her to a workspace. 
    * Another user assign a task to his/her. 
* The users can rate the application out of 5. 
* The user can get a statistics about his/her usage such as: 
    * Number of workspaces he/she created as well as joined to.
    * Number of tasks he/she created or assigned to.
* Adding logging messages in both the terminal and a log file. 
* Enable all the actuators.