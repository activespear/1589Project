# 🔍 SVACE Vulnerability Examples

📢 **Проект для создания примеров конструкций уязвимостей в Java, обнаруживаемых с помощью SVACE.**  
Здесь собираются демонстрационные коды, описания и возможные исправления.

[Список детекторов](src/main/docs/vulnerabilities_list.pdf) находится в папке docs. Там же есть [методичка](src/main/docs/USER_JAVA%20(fin)%20Guide.pdf) с описанием
и небольшой [гайд](src/main/docs/style_guide.md) по стилизации кода. В папку [vulnerabilities_docs](src/main/docs/vulnerabilities_docs)
заносим свои оформленные примеры в виде docs сохраняя наименование документа.

В папке [src/main/java/vulnerabilities](../src/main/java/vulnerabilities) пишем
сами примеры конструкции. Каждая уязвимость - отдельный класс, в котором есть
метод main() (для централизованного тестирования возможно лучше сделать
один такой на весь проект), метод с ошибкой incorrectTest() и исправленный метод correctTest(). Используемая
версия java: 21. 

Распределение по детекторам:
`Дима: 1-188`,
`Юра: 189-376`,
`Вася: 377-564`,
`Вика 565-752`.
P.S. Пронумерованного списка не нашёл.

Каждый разработчик может создать свою рабочую ветку. Пример: feature/detectors/your_name.
После, находясь в своей ветке, мержим: 
```bash
git fetch origin
git merge origin/main
```
Затем push:
```bash
git push origin user/ivan/add-sql-injection-check
```
И pull Request (PR) через GitHub.
