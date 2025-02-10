# 📏 Руководство по стилю кода (Style Guide)

## 1️⃣ Форматирование кода
Для автоматического выравнивания синтаксиса используйте:

- **Windows/Linux**: `CTRL + ALT + L`
- **macOS**: `OPTION + COMMAND + L`

_(в IntelliJ IDEA)_

---

## 2️⃣ Именование

✅ **Классы и интерфейсы:** `UpperCamelCase`
```java
public class DataProcessor { ... }
public interface DataService { ... }
```
Классы именуем в соответствии с названием уязвимости.

✅ **Методы и переменные:** `lowerCamelCase`
```java
public void processData() { ... }
private int dataCount;
```

✅ **Константы (static final):** `UPPER_SNAKE_CASE`
```java
public static final int MAX_RETRIES = 5;
public static final String ERROR_MESSAGE = "Invalid input";
```

---

## 3️⃣ Импорты
🚫 Не использовать * в импортах

✅ Каждый класс импортировать отдельно

❌ Плохо:
```java
import java.util.*;
```

✅ Хорошо:
```java
import java.util.List;
import java.util.Map;
```

---
📌 Основано на [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)