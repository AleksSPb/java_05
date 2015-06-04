.\00_README.md


﻿Тонкости Java. Ввод/вывод. Параллельное выполнение
==================================================

Операторы и структура кода. Исключения
--------------------------------------

Исключения
----------

Маленькие хитрости Java. StringBuilder
--------------------------------------
Тест производительности.

Файловая система. Ввод-вывод
----------------------------
File
Scanner

Начало реализации DAO - хранение в файлах
-----------------------------------------

Модульное тестирование JUnit 4 - разобрали

Домашнее задание:
-----------------

Литература:
-----------
* Пакет java.io
* Потоки выполнения. Синхронизация.

.\01_Exceptions\pom.xml

.\01_Exceptions\src\main\java\ExceptionDemo.java

.\01_Exceptions\src\main\java\MyRuntimeException.java

.\01_Exceptions\src\main\java\MyException.java

.\01_Exceptions\src\main\java\RuntimeExceptionDemo.java

.\01_Exceptions\src\test\java\DivideByZero.java

int i = 10 / 0;
.\01_Exceptions\src\test\java\Exceptions.java

/....
...
throw ex;
ex.printStackTrace();
/....
...
.\02_StringBuilder\pom.xml

.\02_StringBuilder\src\main\java\Point.java

.\02_StringBuilder\src\test\java\StringBuilderTest.java



Вставляем подстроку в позицию 13
Удаляем кусок
Цепочка действий
.\02_StringBuilder\src\test\java\StringVsStringBuilder.java

.\02_StringBuilder\src\test\java\StringsTest.java

File f1 = new File(STRING_FILENAME);
.\03_SaveToFile\pom.xml

.\03_SaveToFile\src\main\java\MyClassHelper.java

.\03_SaveToFile\src\main\java\Person.java

.\03_SaveToFile\src\main\java\XMLSerialize.java

.\03_SaveToFile\src\main\java\dao\Entity.java

.\03_SaveToFile\src\main\java\dao\FileStorage.java

.\03_SaveToFile\src\main\java\dao\Storage.java

.\03_SaveToFile\src\main\java\dao\XMLFile.java

.\03_SaveToFile\src\main\java\model\Resume.java

.\03_SaveToFile\src\test\java\FileStorageTest.java

Обновляем
.\03_SaveToFile\src\test\java\MyClassXMLTest.java

Считываем обратно
.\04_ProblemFileWriteRead\pom.xml

.\04_ProblemFileWriteRead\src\main\java\MyClass.java

.\04_ProblemFileWriteRead\src\main\java\ObjectSaveLoad.java

Используйте: OutputStreamWriter
Через Reflection API получаем класс
Через Reflection API получаем список полей
Получаем значение поля
Закрываем файл
Имя класса
Загружаем класс по имени
Создаём экземпляр класса
Pattern hh = Pattern.compile("(\\w+):");
Пропускаем "="
.\04_ProblemFileWriteRead\src\main\java\TextSaveLoad.java

Используйте: OutputStreamWriter
.\04_ProblemFileWriteRead\src\test\java\TextSaveLoadTest.java

.\SpringMVC\README.md


Spring MVC приложения в Intellj IDEA
------------------------------------

Документация по Spring Framework: https://spring.io/docs

* Использование циклов в JSP: <http://localhost:8080/list>
* Таблица умножения: <http://localhost:8080/table?size=22>

Настройка Tomcat
----------------

Добавить в раздел **tomcat-users** в **<tomcat>/conf/tomcat-users.xml**:

``` xml
  <role rolename="manager-gui"/>
  <user username="admin" password="123" roles="manager-gui"/>
```

Запускаем Tomcat:
* **catalina.bat run** для Windows
* **catalina.sh run** для Linux/Mac

Открываем: <http://localhost:8080/manager/html>

Вводим логин и пароль: admin / 123

В разделе **Deploy / WAR file to deploy** выбираем наш **war**-файл и нажимаем Deploy.


.\SpringMVC\pom.xml

.\SpringMVC\src\main\java\ru\levelp\beans\BeanExample.java

.\SpringMVC\src\main\java\ru\levelp\mvc\controller\HelloController.java

``` java
@Controller
@RequestMapping("/")
public class HelloController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        // Добавляем аттрибут ${message} для hello.jsp
        model.addAttribute("message", "Добро пожаловать на наш сайт!");
        model.addAttribute("a", 2);
        model.addAttribute("b", 12);
        return "hello";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model, @RequestParam("a") String a,
                      @RequestParam("b") String b) {
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        return "add";
    }

    /**
     * http://localhost:8080/table - выведется таблица умножения
     * http://localhost:8080/table?size=5 - задаём размер
     */
    @RequestMapping(value = "/table", method = RequestMethod.GET)
    public String printMulTable(Model model, @RequestParam(value = "size", required = false) Integer size) {
        model.addAttribute("html", "This <b>is</b> <strike>HTML</strike> from controller!");
        // Размер таблицы по-умолчанию (значение по-умолчанию)
        if (size == null) {
            size = 10;
        }
        model.addAttribute("size", size);
        return "table";
    }

    /**
     * Вывод таблицы умножения
     * http://localhost:8080/table - выведется таблица умножения
     * size - размер таблицы
     */
    @RequestMapping(value = "/table/{size}", method = RequestMethod.GET)
    public String printMulTable2(Model model, @PathVariable("size") Integer size) {
        model.addAttribute("html", "size = " + size);
        model.addAttribute("size", size);
        return "table";
    }

    @RequestMapping(value = "/table/{size}", method = RequestMethod.GET)
    public String printMulTable22(Model model, @PathVariable("size") Integer size) {
        model.addAttribute("html", "size = " + size);
        model.addAttribute("size", size);
        return "table";
    }

    /**
     * Вывод списка
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showList() {
        List<String> list = new ArrayList<String>();
        list.add("List A");
        list.add("List B");
        list.add("List C");
        list.add("List D");
        list.add("List 1");
        list.add("List 2");
        list.add("List 3");

        //return back to index.jsp
        ModelAndView model = new ModelAndView("list");
        model.addObject("lists", list);

        return model;
    }
}
```
.\SpringMVC\src\main\java\ru\levelp\mvc\controller\ResumeController.java

resume.setName("Нужное нам резюме: " + query);
.\SpringMVC\src\main\java\ru\levelp\mvc\controller\UserController.java

.\SpringMVC\src\main\java\ru\levelp\mvc\forms\LoginForm.java

.\SpringMVC\src\main\java\ru\levelp\mvc\model\Name.java

.\SpringMVC\src\main\java\ru\levelp\mvc\model\Resume.java

.\SpringMVC\src\main\java\ru\levelp\mvc\model\User.java

.\SpringMVC\src\main\java\ru\levelp\mvc\storage\FileStorage.java

Сравнение с игнорированием
.\SpringMVC\src\test\java\jdbc\SQLiteJDBC.java

.\SpringMVC\src\test\java\ru\levelp\mvc\AppTests.java

.\SpringMVC\src\test\java\ru\levelp\mvc\FileStorageTest.java

.\SpringMVC\src\test\java\ru\levelp\mvc\UserTest.java

@Test
public void
.\pom.xml

