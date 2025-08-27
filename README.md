# CRM Agro Backend

Полноценный бэкенд CRM системы для агробизнеса с реализацией всех бизнес-процессов, ролей, справочников, оперативного учета и интеграций.

## Возможности

### Роли пользователей
- **Администратор**: управление правами, справочниками, настройками и интеграциями
- **Агроном**: планирование работ, контроль выполнения, журнал работ, контроль использования материалов и ГСМ
- **Механик (главный инженер)**: парк техники, ТО и ремонт, запасные части, путевые листы
- **Механизатор/Водитель**: получение и выполнение задач, ведение путевого листа, фотофиксация, списание материалов и ГСМ
- **Бухгалтер**: сверка ГСМ, формирование документов для 1С, контроль себестоимости и расходов
- **Руководитель**: дашборды KPI, аналитика, согласование документов

### Функциональные модули
- **Справочники**: поля, техника, сотрудники, материалы (с историей цен), культуры/сезоны
- **Планирование и задачи**: календарь/Gantt, автогенерация задач, чек-листы, уведомления
- **Оперативный учет**: журнал работ и заправок, фиксация площади, моточасов, материалов
- **Путевые листы**: автогенерация по задачам, контроль маршрута, топлива, моточасов
- **ГСМ и топливные карты**: импорт транзакций, матчинг с работами, контроль лимитов
- **ТО и ремонт техники**: план ТО, заявки, резерв запчастей, дашборд доступности
- **Финансы**: калькуляция себестоимости, план–факт, интеграция с 1С
- **Аналитика и отчётность**: KPI, дашборды, фильтры, выгрузка PDF/Excel

### Интеграции
- 1С (ERP/УТ, Бухгалтерия, ЗУП)
- GPS/телематика
- Провайдеры топливных карт
- Погодные сервисы
- Дроны

## Технологический стек

- **Java 17**
- **Spring Boot 3.5.5**
- **Spring Security** с JWT
- **Spring Data JPA**
- **PostgreSQL**
- **Swagger/OpenAPI 3**
- **Maven**

## Требования

- Java 17 или выше
- Maven 3.6+
- PostgreSQL 12+
- Git

## Быстрый старт

### 1. Клонирование репозитория
```bash
git clone https://github.com/your-username/crm-agro-backend.git
cd crm-agro-backend
```

### 2. Настройка базы данных
```sql
-- Создание базы данных
CREATE DATABASE crm_agro;

-- Создание пользователя (опционально)
CREATE USER crm_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE crm_agro TO crm_user;
```

### 3. Настройка конфигурации
Отредактируйте файл `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/crm_agro
spring.datasource.username=your_username
spring.datasource.password=your_password

# JWT Configuration
jwt.secret=your-very-long-and-secure-secret-key-here
jwt.expiration=86400000
jwt.refresh-expiration=604800000

# Email Configuration (опционально)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
```

### 4. Запуск приложения
```bash
# Сборка проекта
mvn clean install

# Запуск
mvn spring-boot:run
```

Приложение будет доступно по адресу: http://localhost:8080

### 5. Документация API
Swagger UI доступен по адресу: http://localhost:8080/swagger-ui.html

## Структура проекта

```
src/main/java/dev/agro/crmagrobackend/
├── config/                 # Конфигурации
│   ├── SecurityConfig.java
│   └── OpenApiConfig.java
├── controller/             # REST контроллеры
│   ├── AuthController.java
│   ├── FieldController.java
│   └── ...
├── dto/                   # Data Transfer Objects
│   ├── AuthRequest.java
│   ├── AuthResponse.java
│   └── ...
├── model/                 # Сущности JPA
│   ├── User.java
│   ├── Field.java
│   ├── Task.java
│   └── ...
├── repository/            # Репозитории Spring Data
│   ├── UserRepository.java
│   ├── FieldRepository.java
│   └── ...
├── security/              # Безопасность
│   ├── JwtAuthenticationFilter.java
│   └── JwtAuthenticationEntryPoint.java
├── service/               # Бизнес-логика
│   ├── AuthService.java
│   └── ...
└── CrmAgroBackendApplication.java
```

## Аутентификация

### Регистрация администратора
После первого запуска создайте администратора через API:

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "email": "admin@example.com",
    "password": "admin123",
    "role": "ADMIN",
    "phone": "+79001234567"
  }'
```

### Вход в систему
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

## Основные API endpoints

### Аутентификация
- `POST /api/auth/login` - Вход в систему
- `POST /api/auth/register` - Регистрация пользователя
- `POST /api/auth/refresh` - Обновление токена
- `GET /api/auth/me` - Информация о текущем пользователе

### Справочники
- `GET /api/fields` - Список полей
- `POST /api/fields` - Создание поля
- `GET /api/machines` - Список техники
- `GET /api/employees` - Список сотрудников
- `GET /api/materials` - Список материалов

### Задачи и работы
- `GET /api/tasks` - Список задач
- `POST /api/tasks` - Создание задачи
- `PATCH /api/tasks/{id}/status` - Изменение статуса задачи
- `GET /api/work-entries` - Журнал работ
- `POST /api/work-entries` - Создание записи о работе

### Путевые листы
- `GET /api/waybills` - Список путевых листов
- `POST /api/waybills` - Создание путевого листа
- `PATCH /api/waybills/{id}` - Обновление путевого листа

### ГСМ
- `GET /api/fuel-cards` - Топливные карты
- `POST /api/fuel-txns/import` - Импорт транзакций
- `GET /api/fuel-txns/anomalies` - Аномалии ГСМ

### ТО и ремонт
- `GET /api/maintenance` - Список ТО
- `POST /api/maintenance` - Создание ТО
- `PATCH /api/maintenance/{id}` - Обновление ТО

### Отчёты
- `GET /api/reports/cost-per-ha` - Себестоимость на га
- `GET /api/reports/fuel-plan-fact` - План-факт ГСМ
- `GET /api/reports/kpi` - KPI показатели

## Разработка

### Запуск в режиме разработки
```bash
# С профилем dev
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# С отладкой
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

### Тестирование
```bash
# Запуск тестов
mvn test

# Запуск с покрытием
mvn test jacoco:report
```

### Сборка для продакшена
```bash
# Создание JAR файла
mvn clean package -Pprod

# Запуск JAR
java -jar target/crm-agro-backend-0.0.1-SNAPSHOT.jar
```

## Логирование

Логи приложения сохраняются в:
- Консоль (по умолчанию)
- Файл `logs/crm-agro-backend.log` (если настроено)

Уровни логирования настраиваются в `application.properties`:
```properties
logging.level.dev.agro.crmagrobackend=DEBUG
logging.level.org.springframework.security=DEBUG
```

## Безопасность

- JWT токены для аутентификации
- Ролевая модель доступа (RBAC)
- Валидация входных данных
- Аудит действий пользователей
- CORS настройки
- Защита от SQL инъекций

## Мониторинг

Actuator endpoints доступны по адресу: http://localhost:8080/actuator

- `/actuator/health` - Статус здоровья приложения
- `/actuator/metrics` - Метрики
- `/actuator/info` - Информация о приложении

## Вклад в проект

1. Форкните репозиторий
2. Создайте ветку для новой функции (`git checkout -b feature/amazing-feature`)
3. Зафиксируйте изменения (`git commit -m 'Add amazing feature'`)
4. Отправьте в ветку (`git push origin feature/amazing-feature`)
5. Откройте Pull Request

## Лицензия

Этот проект лицензирован под MIT License - см. файл [LICENSE](LICENSE) для деталей.

## Поддержка

- Email: support@crmagro.com
- Документация: https://docs.crmagro.com
- Issues: https://github.com/your-username/crm-agro-backend/issues

## Roadmap

- [ ] Интеграция с 1С
- [ ] GPS/телематика
- [ ] Мобильное приложение
- [ ] Дашборды в реальном времени
- [ ] Машинное обучение для прогнозирования
- [ ] Интеграция с дронами
- [ ] Многоязычность
- [ ] Облачное развертывание
