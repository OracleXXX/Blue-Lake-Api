## Blue Lake Api

#### Environment
- SDK: 1.8 java
- Language level: 8
- Springboot 2.3.0 Release
- Database:Mysql 
  - url: jdbc:mysql://localhost:3306/bluelake?useSSL=false&useUnicode=true&characterEncoding=utf8 
  - username: root
  - password: 123456
#### Development
1. Create table in database
2. Generate DAO related files via [BlueLakeGenerator](src/test/java/com/bluelakeapi/mybatis/BLueLakeGenerator.java)
3. Clean [DAO](src/main/java/com/bluelakeapi/model/test/TestDataDO.java) file 
4. Create CRUD methods in [Manager](src/main/java/com/bluelakeapi/manager/test/TestDataManager.java)
5. Create [business service](src/main/java/com/bluelakeapi/service/TestService.java) and [implement method](src/main/java/com/bluelakeapi/service/impl/TestServiceImpl.java) in Service
6. Create [Controller](src/main/java/com/bluelakeapi/controller/TestDataController.java) 
7. Run [BlueLakeApiApplication](src/main/java/com/bluelakeapi/BlueLakeApiApplication.java)