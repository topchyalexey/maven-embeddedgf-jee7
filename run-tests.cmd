

set RUN_CLASSPATH=./target/hibernate-jpa-2.0-api-1.0.0.Final.jar;./target/junit-4.8.1.jar;./target/slf4j-api-1.6.1.jar;^
./target/javassist-3.12.0.GA.jar;./target/log4j-1.2.16.jar;./target/commons-io-2.4.jar;./target/antlr-2.7.6.jar;^
./target/dom4j-1.6.1.jar;./target/cglib-2.2.jar;./target/slf4j-log4j12-1.6.1.jar;./target/commons-collections-3.1.jar;^
./target/javax.ejb-3.0.jar;./target/hibernate-entitymanager-3.6.4.Final.jar;./target/javax.annotation-3.0.jar;^
./target/hibernate-core-3.6.4.Final.jar;./target/hsqldb-2.0.0.jar;./target/persistence-api-1.0.jar;^
./target/glassfish-embedded-all-3.2-b06.jar;./target/jta-1.1.jar;./target/hibernate-commons-annotations-3.2.0.Final.jar;^
./target/javax.transaction-3.0.jar

goto run

:test
call java -cp ./target/test-classes;./target/junit-4.11.jar;./target/guava-io-r03.jar;^
./target/classes;./target/hamcrest-core-1.3.jar;target/guava-base-r03.jar -Dverbose=true^
  org.junit.runner.JUnitCore com.egartech.hcfbmon.cem.test.TestRMISCServer

:run  
call java -cp ./target/classes;./target/test-classes;%RUN_CLASSPATH% com.company.SimpleCMSAppTest 