## a)Identify a couple of examples on the use of AssertJ expressive methods chaining.

AssertJ is a library used for writing assertions in Java tests.
EmployeeRepositoryTest line 65:
``` assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName()); ```

EmployeeService_UnitTest line 103:
``` assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName()); ```

## b)Identify an example in which you mock the behavior of the repository (and avoid involving a database).

In the test class "EmployeeService_UnitTest" the attribute employeeRepository we mock the behavior of the repository in the attribute employeeRepository to test only the "EmployeeServiceImpl".

## c)What is the difference between standard @Mock and @MockBean?

Both anotations are used to simulate the behaviour of a class or interface. But the main diference is that the @Mock anotation just creates a mock object that take the place of a real implementation, and it must be used in a Unit Test. The @MockBean anotation in addition to creating a mock object, this anotation also adds that object to a Spring test context and its usefull to mock objects in Integration Tests.

## d)What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

The role of the file "application-integrationtest.properties" is to contain some spring-boot properties. In this example it contains the information needed to connect our application to a database. This should be used when we want to do tests with a database, and for this we must use the @TestPropertySource anotation.

