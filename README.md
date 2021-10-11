Задание

1. Реализовать REST endpoint-ы:
- GET: /department/employees - получить список всех employees для каждого из департаментов в формате JSON
Response - структура JSON-a:
```json
[
  {
    "department": {
      "name": "название департамента",
      "manager":
          {
            "lastName": "Иванов",
            "firstName": "Виктор",
            "salary": null
          },
      "employees": [
              {
                  "lastName": "Иванов",
                  "firstName": "Виктор",
                  "salary": null
              },
              {
                  "lastName": "Сидоров",
                  "firstName": "Иван",
                  "salary": null
              }
          ]
      }
  }
]
```
- POST: /employee - сохранить нового employee. В теле метода передается JSON следующего формата:
```json
{
    "lastName": "Иванов",
    "firstName": "Виктор",
    "departmentId": "",
    "managerId": "",
    "salary": null
}
```
- GET: /employee/{id} - получить Employee по Id. Response:
```json
{
    "lastName": "Иванов",
    "firstName": "Виктор",
    "departmentId": "",
    "managerId": "",
    "salary": null
}
```
- PUT: /department/{id}?salaryIndex - индексирует заработное плату для всех employee департамента с id равным id в path variable на процент равный salaryIndex

2. Написать тесты на controller-ы
    - Проверить что get методы возвращают результат в формате json и что в json поля не пустые
    - Проверить что после выполнения метода post сохраняется новый объект в бд


**Полезные ссылки:**
- Spring-Data:
  - https://habr.com/ru/company/otus/blog/531332/ 
  - https://habr.com/ru/post/435114/
  - https://habr.com/ru/post/538860/
  - https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
- Hibernate mapping:
  - https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
  - https://vladmihalcea.com/manytoone-jpa-hibernate/
  - https://dou.ua/lenta/articles/jpa-fetch-types/
  - https://dou.ua/lenta/articles/hibernate-fetch-types/
  - https://habr.com/ru/post/416851/
- Testing:
  - https://www.baeldung.com/spring-boot-testing
  - https://www.baeldung.com/integration-testing-in-spring
- JSON:
  - https://habr.com/ru/post/554274/
- REST:
  - https://www.baeldung.com/building-a-restful-web-service-with-spring-and-java-based-configuration
  - https://www.baeldung.com/spring-controller-vs-restcontroller
  - https://javarush.ru/groups/posts/2488-obzor-rest-chastjh-3-sozdanie-restful-servisa-na-spring-boot
  - https://habr.com/ru/post/483202/
  - https://habr.com/ru/post/483204/
  - https://habr.com/ru/post/483374/
  - https://habr.com/ru/post/351890/
  - https://habr.com/ru/company/otus/blog/440046/
        
