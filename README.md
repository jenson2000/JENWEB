JENWEB
===

JENWEB is a ***J2EE*** project framework,it used ***spring MVC,hibernate,shiro,redis*** etc. it develop on ***jdk1.8,tomcat8***,maven. you can use it for free.

build and running
---
<br>
<table>
<tr>
<td></td>
<td>version</td>
</tr>
<tr>
<td>spring</td>
<td>4.2.7</td>
</tr>
<tr>
<td>hibernate</td>
<td>4.3.11</td>
</tr>
<tr>
<td>shiro</td>
<td>1.3.2</td>
</tr>
<tr>
<td>mysql</td>
<td>5.7</td>
</tr>
<tr>
<td>redis</td>
<td>3.2.X</td>
</tr>
</table>
<br>
build and running
---
![image](https://github.com/jenson2000/JENWEB/blob/master/JEN-STATIC/WebContent/staticinfo/images/git002.jpg)

File Hierarchy of the Source Code Package
---
JENWEN used maven to manage third-party components.
it has five project
 > JEN-STATIC <br>
 >> JEN-PARENT
 >>> JEN-COMMON <br>
 >>> JEN-DAO <br>
 >>> JENWEB <br>
 
 1. JEN-STATIC is a static project. it includes static recourses, such as css,images,js,etc.
 2. JEN-PARENT is maven parent project.
 3. JEN-COMMON is tools package project.include some constant bean,tools bean,etc.
 4. JEN-DAO is DAO project. include pojo,DAO etc. depends on JEN-COMMON
 5. JENWEB is web project,depends on JEN-DAO,JEN-COMMON

Getting Started
---
/jenweb/doc/sendb.sql is sql scripts, you can use it to create database.<br>
