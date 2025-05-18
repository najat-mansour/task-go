# Database Schema
**The selected database is a <u>MySQL</u> database:**

### <u>Tables</u>
#### <mark>users</mark>
| Field     | Data Type              | Notes                 | 
| --------- |------------------------| --------------------  |
| id        | VARCHAR(255)           | Primary-Key (UUID)    | 
| username  | VARCHAR(255)           | Unique                | 
| password  | VARCHAR(255)           |                       |
| first_name| VARCHAR(255)           |                       |
| last_name | VARCHAR(255)           |                       |
| email     | VARCHAR(255)           | Unique                |
| birthdate | DATE                   |                       |    
| gender    | ENUM('MALE', 'FEMALE') |                       |
| created_at| DATETIME               |                       |
| app_rate  | TINYINT UNSIGNED       | BETWEEN 1 AND 5       | 

#### <mark>addresses</mark>
| Field      | Data Type    | Notes                  |
| ---------- |--------------| ---------------------- |
| id         | VARCHAR(255) | Primary Key (UUID)     |
| user_id    | VARCHAR(255) | Foreign Key → users(id)|
| country    | VARCHAR(255) |                        |
| city       | VARCHAR(255) |                        |
| town       | VARCHAR(255) |                        |
| street     | VARCHAR(255) |                        |

#### <mark>workspaces</mark>
| Field       | Data Type    | Notes                  |
|-------------|--------------| ---------------------- |
| id          | VARCHAR(255) | Primary Key (UUID)     |
| owner_id    | VARCHAR(255) | Foreign Key → users(id)|
| name        | VARCHAR(255) |                        |
| description | TEXT         |                        |

#### <mark>workspaces_viewers</mark>
| Field        | Data Type     | Notes                        |
|--------------|---------------| ---------------------------- |
| id           | VARCHAR(255)  | Primary Key (UUID)           |
| workspace_id | VARCHAR(255)  | Foreign Key → workspaces(id) |
| viewer_id    | VARCHAR(255)  | Foreign Key → users(id)      |

#### <mark>groups</mark>
| Field       | Data Type      | Notes                        |
| ----------- |----------------| ---------------------------- |
| id          | VARCHAR(255)   | Primary Key (UUID)           |
| workspace_id| VARCHAR(255)   | Foreign Key → workspaces(id) |
| name        | VARCHAR(255)   |                              |
| color       | VARCHAR(255)   | HEX-Code for e.g. #123456  |
| description | TEXT           |                              |

#### <mark>tasks</mark>
| Field              | Data Type                                              | Notes                           |
|--------------------|--------------------------------------------------------| ------------------------------- |
| id                 | VARCHAR(255)                                           | Primary Key (UUID)              |
| group_id           | VARCHAR(255)                                           | Foreign Key → groups(id)        |
| name               | VARCHAR(255)                                           |                                 |
| description        | TEXT                                                   |                                 |
| status             | ENUM('NOT_STARTED','IN_PROGRESS','PENDING','FINISHED') | Default 'NOT_STARTED'           |
| priority           | ENUM('HIGH','MEDIUM','LOW')                            | Default 'LOW'                   |
| starting_timestamp | DATETIME                                               |                                 |
| ending_timestamp   | DATETIME                                               |                                 |
| is_favorite        | BOOLEAN                                                | Default false                   |
| assigned_to_id     | VARCHAR(255)                                           | Foreign Key → users(id)         |

#### <mark>sub_tasks</mark>
| Field               | Data Type                                              | Notes                           |
| ------------------- |--------------------------------------------------------| ------------------------------- |
| id                  | VARCHAR(255)                                           | Primary Key (UUID)              |
| task_id             | VARCHAR(255)                                           | Foreign Key → tasks(id)         |
| name                | VARCHAR(255)                                           |                                 |
| description         | TEXT                                                   |                                 |
| status              | ENUM('NOT_STARTED','IN_PROGRESS','PENDING','FINISHED') | Default 'NOT_STARTED'           |
| priority            | ENUM('HIGH','MEDIUM','LOW')                            | Default 'LOW'                   |
| starting_timestamp  | DATETIME                                               |                                 |
| ending_timestamp    | DATETIME                                               |                                 |

### <u>ERD (Entity Relational Diagram)</u>
<img src="https://i.postimg.cc/NFGqrdKC/ERD.png" alt="ERD"/>