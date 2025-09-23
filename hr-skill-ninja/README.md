# HRSkillNinja

Легковесный микросервис для отслеживания кандидатов для внутреннего использования HR-специалистами и Экспертами-интервьюерами.

Учебное приложение.

## Описание

Приложение позволяет управлять информацией о кандидате на заявленную позицию.

Основная информация по кандидату содержит следующее:

- ФИО кандидата
- Возраст
- Позиция, на которую претендует кандидат
- Информация по резюме (в тектовом формате)
- Комментарий для внутреннего пользования
- Текущий статус кандидата

## Функционал

- REST/JSON API для управления информацией о кандидата:
  * Добавление нового кандидата
  * Обновление данных уже заведенного в систему кандидата
  * Обновление статуса кандидата в соответствии со статусной моделью
  * Добавление комментария к кандидатуре
  * Чтение данных по кандидату
  * Чтение данных всех кандидатов
- Полнотекстовый поиск (по ФИО, статусу и позиции)
- Валидация входных данных
- Обработка ошибок

## Технологический стек

- Java 21 (LTS)
- Spring Boot 3.x
- PostgreSQL 15+
- Maven 3.9+

## Требования

- Java 21 или новее
- Maven 3.9 или новее
- PostgreSQL 15 или новее

## Конфигурация

Приложение настраивается через `application.yaml`. Основные настройки:

```yaml
server:
  port: 8090
```

## Сборка

```bash
mvn clean package
```

## API Endpoints

Базовый URL: `http://localhost:8080/api/v1/candidates`

### Создание кандидата
```
POST /
Content-Type: application/json

{
  "fio": "Ivanov Ivan Ivanovich",
  "age": 32,
  "position": "Senior Java Developer",
  "cvInfo": "..."
}
```

### Обновление кандидата
```
PUT /{id}
Content-Type: application/json

{
  "fio": "Ivanov Ivan Ivanovich",
  "age": 33,
  "position": "Senior Java Developer",
  "cvInfo": "..."
}
```

### Изменение статуса
```
PUT /{id}/status
Content-Type: application/json

{
  "status": "INTERVIEW"
}
```

### Изменение комментария
```
PUT /{id}/comment
Content-Type: application/json

{
  "comment": "Passed tech screen"
}
```

### Получение всех кандидатов
```
GET /
```

### Получение кандидата по ID
```
GET /{id}
```

### Поиск кандидатов
```
GET /search?fio=Ivanov&status=NEW,CV_REVIEW&position=Developer
```

## Статусная модель

```
NEW → CV_REVIEW → SCHEDULED_FOR_INTERVIEW → INTERVIEW → OFFER → ACCEPTED → STARTED_WORKING
   ↘ DECLINED
```

Любой статус может перейти в DECLINED.
