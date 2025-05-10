# Database Schema 
**The selected database is a <u>MySQL</u> database:**

### <u>Tables</u>
#### <mark>users</mark>
| Field     | Data Type                 | Notes                 | 
| --------- | ------------------------- | --------------------  |
| id        | CHAR(36)                  | Primary-Key (UUID)    | 
| username  | VARCHAR(50)               | Unique                | 
| password  | VARCHAR(60)               |                       |
| first_name| VARCHAR(50)               |                       |
| last_name | VARCHAR(50)               |                       |
| email     | VARCHAR(100)              | Unique                |
| birthdate | DATE                      |                       |    
| gender    | ENUM('MALE', 'FEMALE')    |                       |
| created_at| DATETIME                  |                       |
| app_rate  | TINYINT UNSIGNED          | BETWEEN 1 AND 5       | 

#### <mark>addresses</mark>
| Field      | Data Type                 | Notes                  |
| ---------- | ------------------------- | ---------------------- |
| id         | CHAR(36)                  | Primary Key (UUID)     |
| user_id    | CHAR(36)                  | Foreign Key → users(id)|
| country    | VARCHAR(100)              |                        |
| city       | VARCHAR(100)              |                        |
| town       | VARCHAR(100)              |                        |
| street     | VARCHAR(150)              |                        |

#### <mark>workspaces</mark>
| Field       | Data Type    | Notes                  |
| ----------- | ------------ | ---------------------- |
| id          | CHAR(36)     | Primary Key (UUID)     |
| user_id     | CHAR(36)     | Foreign Key → users(id)|
| name        | VARCHAR(100) |                        |
| description | TEXT         |                        |

#### <mark>workspaces_users</mark>
| Field         | Data Type  | Notes                        |
| ------------- | ---------- | ---------------------------- |
| id            | CHAR(36)   | Primary Key (UUID)           |
| workspace_id  | CHAR(36)   | Foreign Key → workspaces(id) |
| user_id       | CHAR(36)   | Foreign Key → users(id)      |

#### <mark>groups</mark>
| Field       | Data Type    | Notes                        |
| ----------- | ------------ | ---------------------------- |
| id          | CHAR(36)     | Primary Key (UUID)           |
| workspace_id| CHAR(36)     | Foreign Key → workspaces(id) |
| name        | VARCHAR(100) |                              |
| color       | VARCHAR(7)   | HEX-Code for e.g. #123456  |
| description | TEXT         |                              |

#### <mark>tasks</mark>
| Field               | Data Type                                               | Notes                           |
| ------------------- | ------------------------------------------------------- | ------------------------------- |
| id                  | CHAR(36)                                                | Primary Key (UUID)              |
| group_id            | CHAR(36)                                                | Foreign Key → groups(id)        |
| name                | VARCHAR(100)                                            |                                 |
| description         | TEXT                                                    |                                 |
| status              | ENUM('NOT_STARTED','IN_PROGRESS','PENDING','FINISHED')  | Default 'NOT_STARTED'           |
| priority            | ENUM('HIGH','MEDIUM','LOW')                             | Default 'LOW'                   |
| starting_timestamp  | DATETIME                                               |                                 |
| ending_timestamp    | DATETIME                                               |                                 |
| owner_id            | CHAR(36)                                                | Foreign Key → users(id)         |
| is_favorite         | BOOLEAN                                                 | Default false                   |

#### <mark>sub_tasks</mark>
| Field               | Data Type                                               | Notes                           |
| ------------------- | ------------------------------------------------------- | ------------------------------- |
| id                  | CHAR(36)                                                | Primary Key (UUID)              |
| task_id             | CHAR(36)                                                | Foreign Key → tasks(id)         |
| name                | VARCHAR(100)                                            |                                 |
| description         | TEXT                                                    |                                 |
| status              | ENUM('NOT_STARTED','IN_PROGRESS','PENDING','FINISHED')  | Default 'NOT_STARTED'           |
| priority            | ENUM('HIGH','MEDIUM','LOW')                             | Default 'LOW'                   |
| starting_timestamp  | DATETIME                                                |                                 |
| ending_timestamp    | DATETIME                                                |                                 |

### <u>ERD (Entity Relational Diagram)</u>
<img src="https://i.postimg.cc/TPZ9PLc6/ERD.png" alt="ERD"/>