# EvalJDBC
<h1>User Manual</h1>
<p>This application is used to manage a list of teachers and a list of students.<br/>
For every teacher, you can see the subject that he teach. And for every student, you can see in which class he is.
To use this, select what you want in the menus and do what is displayed.</p>

<h1>Deployment Manual</h1>
<p>Before launching the application, you must create a database on postgresdl and indicate your url, user and password in file "DaoConnection.java"<br/>
More later we can update the connection with a file properties for the users access.
To beginning you must create 4 tables in use the file "dbSql.sql" and copy the request who are included.
</p>

<P>
Actually, the optional module to manage classes and teaches informations is not made.<br/>
So, before to use application you must insert some classes and teaches element like these examples :<br/>
INSERT INTO class (name) VALUES ('Terminal B');<br/>
INSERT INTO teach (name) VALUES ('French');<br/>
...
</p>
<p>When your tables and your inserts were made, you can start the application.<br/>
you must use an IDE and launch the application from the file "SchoolManagement.java".
</p>

<h1>Development Manual</h1>
<p>
To do this application, the choice who was made is to use :
- a main class to manage the different menu.<br/>
- a class to manage teachers and a class to manage student.<br/>
To simplify menu, there is an abstract class parent of student and teacher (Character) who we help to manage the same orders (add, update, delete and display).<br/>
- a DAO classes to manage and save the data without impact on classical classes if one day we should do some changes.<br/>
- a specific class DaoConnection.java to manage specially the connection at database without impact on the others DAO classes if <br/>
one day we should do some changes (example : manage multiple database system).
</p>