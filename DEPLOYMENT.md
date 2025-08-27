# Инструкции по развертыванию CRM Agro Backend

## 🚀 Быстрый старт

### 1. Подготовка окружения

#### Требования к системе
- **ОС**: Linux (Ubuntu 20.04+), Windows 10+, macOS 10.15+
- **Java**: OpenJDK 17 или Oracle JDK 17
- **База данных**: PostgreSQL 12+
- **Память**: минимум 2GB RAM
- **Диск**: минимум 10GB свободного места

#### Установка Java 17
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install openjdk-17-jdk

# CentOS/RHEL
sudo yum install java-17-openjdk-devel

# macOS
brew install openjdk@17

# Windows
# Скачайте с https://adoptium.net/
```

#### Установка PostgreSQL
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install postgresql postgresql-contrib

# CentOS/RHEL
sudo yum install postgresql-server postgresql-contrib
sudo postgresql-setup initdb
sudo systemctl start postgresql
sudo systemctl enable postgresql

# macOS
brew install postgresql
brew services start postgresql
```

### 2. Настройка базы данных

#### Создание базы данных
```sql
-- Подключение к PostgreSQL
sudo -u postgres psql

-- Создание пользователя и базы данных
CREATE USER crm_user WITH PASSWORD 'your_secure_password';
CREATE DATABASE crm_agro OWNER crm_user;
GRANT ALL PRIVILEGES ON DATABASE crm_agro TO crm_user;
\q
```

#### Инициализация данных
```bash
# Подключение к базе данных и выполнение скрипта
psql -U crm_user -d crm_agro -f src/main/resources/db/init.sql
```

### 3. Настройка приложения

#### Клонирование репозитория
```bash
git clone https://github.com/your-username/crm-agro-backend.git
cd crm-agro-backend
```

#### Настройка конфигурации
Создайте файл `application-prod.properties`:
```properties
# Production Configuration
spring.profiles.active=prod

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/crm_agro
spring.datasource.username=crm_user
spring.datasource.password=your_secure_password

# JWT Configuration
jwt.secret=your-very-long-and-secure-secret-key-here-make-it-at-least-256-bits
jwt.expiration=86400000
jwt.refresh-expiration=604800000

# Server Configuration
server.port=8080
server.servlet.context-path=/api

# Logging Configuration
logging.level.dev.agro.crmagrobackend=INFO
logging.level.org.springframework.security=WARN
logging.file.name=logs/crm-agro-backend.log
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n

# File Upload Configuration
file.upload.path=/var/crm-agro/uploads/
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

# Email Configuration (опционально)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Integration Configuration
integration.1c.base-url=http://your-1c-server:8081
integration.1c.username=your-1c-username
integration.1c.password=your-1c-password

# Security Configuration
security.cors.allowed-origins=https://your-frontend-domain.com
```

### 4. Сборка и запуск

#### Сборка проекта
```bash
# Очистка и сборка
mvn clean package -DskipTests

# Создание JAR файла
mvn clean package -Pprod
```

#### Запуск приложения
```bash
# Запуск с профилем production
java -jar -Dspring.profiles.active=prod target/crm-agro-backend-0.0.1-SNAPSHOT.jar

# Запуск с настройками JVM
java -Xms512m -Xmx2g -jar -Dspring.profiles.active=prod target/crm-agro-backend-0.0.1-SNAPSHOT.jar
```

## 🔧 Развертывание в продакшене

### 1. Настройка системного сервиса

#### Создание systemd сервиса (Linux)
Создайте файл `/etc/systemd/system/crm-agro.service`:
```ini
[Unit]
Description=CRM Agro Backend
After=network.target postgresql.service

[Service]
Type=simple
User=crm-user
Group=crm-user
WorkingDirectory=/opt/crm-agro
ExecStart=/usr/bin/java -Xms1g -Xmx2g -jar crm-agro-backend.jar
ExecReload=/bin/kill -HUP $MAINPID
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
```

#### Управление сервисом
```bash
# Создание пользователя
sudo useradd -r -s /bin/false crm-user

# Создание директории
sudo mkdir -p /opt/crm-agro
sudo chown crm-user:crm-user /opt/crm-agro

# Копирование файлов
sudo cp target/crm-agro-backend-0.0.1-SNAPSHOT.jar /opt/crm-agro/crm-agro-backend.jar
sudo cp application-prod.properties /opt/crm-agro/

# Включение и запуск сервиса
sudo systemctl daemon-reload
sudo systemctl enable crm-agro
sudo systemctl start crm-agro
sudo systemctl status crm-agro
```

### 2. Настройка Nginx (опционально)

#### Установка Nginx
```bash
sudo apt install nginx
```

#### Конфигурация Nginx
Создайте файл `/etc/nginx/sites-available/crm-agro`:
```nginx
server {
    listen 80;
    server_name your-domain.com;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }

    location /api {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

#### Активация конфигурации
```bash
sudo ln -s /etc/nginx/sites-available/crm-agro /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl reload nginx
```

### 3. Настройка SSL (HTTPS)

#### Установка Certbot
```bash
sudo apt install certbot python3-certbot-nginx
```

#### Получение SSL сертификата
```bash
sudo certbot --nginx -d your-domain.com
```

### 4. Настройка брандмауэра

#### Настройка UFW (Ubuntu)
```bash
sudo ufw allow 22/tcp
sudo ufw allow 80/tcp
sudo ufw allow 443/tcp
sudo ufw enable
```

#### Настройка firewalld (CentOS/RHEL)
```bash
sudo firewall-cmd --permanent --add-service=ssh
sudo firewall-cmd --permanent --add-service=http
sudo firewall-cmd --permanent --add-service=https
sudo firewall-cmd --reload
```

## 📊 Мониторинг и логирование

### 1. Настройка логирования

#### Ротация логов
Создайте файл `/etc/logrotate.d/crm-agro`:
```
/var/log/crm-agro-backend.log {
    daily
    missingok
    rotate 30
    compress
    delaycompress
    notifempty
    create 644 crm-user crm-user
    postrotate
        systemctl reload crm-agro
    endscript
}
```

### 2. Мониторинг

#### Проверка статуса
```bash
# Статус сервиса
sudo systemctl status crm-agro

# Проверка портов
sudo netstat -tlnp | grep 8080

# Проверка логов
sudo journalctl -u crm-agro -f

# Проверка здоровья приложения
curl http://localhost:8080/actuator/health
```

#### Метрики
```bash
# Основные метрики
curl http://localhost:8080/actuator/metrics

# Метрики JVM
curl http://localhost:8080/actuator/metrics/jvm.memory.used

# Метрики HTTP
curl http://localhost:8080/actuator/metrics/http.server.requests
```

## 🔒 Безопасность

### 1. Настройка безопасности

#### Обновление паролей
```sql
-- Изменение пароля администратора
UPDATE users SET password_hash = '$2a$10$new_hashed_password' WHERE username = 'admin';
```

#### Настройка CORS
```properties
# В application-prod.properties
security.cors.allowed-origins=https://your-frontend-domain.com
security.cors.allowed-methods=GET,POST,PUT,DELETE,PATCH,OPTIONS
security.cors.allowed-headers=*
security.cors.allow-credentials=true
```

### 2. Резервное копирование

#### Скрипт резервного копирования
Создайте файл `/opt/crm-agro/backup.sh`:
```bash
#!/bin/bash
BACKUP_DIR="/var/backups/crm-agro"
DATE=$(date +%Y%m%d_%H%M%S)

# Создание директории для бэкапов
mkdir -p $BACKUP_DIR

# Резервное копирование базы данных
pg_dump -U crm_user -h localhost crm_agro > $BACKUP_DIR/crm_agro_$DATE.sql

# Резервное копирование файлов
tar -czf $BACKUP_DIR/uploads_$DATE.tar.gz /var/crm-agro/uploads/

# Удаление старых бэкапов (старше 30 дней)
find $BACKUP_DIR -name "*.sql" -mtime +30 -delete
find $BACKUP_DIR -name "*.tar.gz" -mtime +30 -delete
```

#### Настройка автоматического резервного копирования
```bash
# Добавление в crontab
sudo crontab -e

# Добавить строку для ежедневного бэкапа в 2:00
0 2 * * * /opt/crm-agro/backup.sh
```

## 🚀 Обновление системы

### 1. Процедура обновления

#### Подготовка к обновлению
```bash
# Остановка сервиса
sudo systemctl stop crm-agro

# Резервное копирование
/opt/crm-agro/backup.sh

# Создание бэкапа текущей версии
sudo cp /opt/crm-agro/crm-agro-backend.jar /opt/crm-agro/crm-agro-backend.jar.backup
```

#### Обновление приложения
```bash
# Копирование новой версии
sudo cp target/crm-agro-backend-0.0.1-SNAPSHOT.jar /opt/crm-agro/crm-agro-backend.jar

# Запуск сервиса
sudo systemctl start crm-agro

# Проверка статуса
sudo systemctl status crm-agro
curl http://localhost:8080/actuator/health
```

### 2. Откат изменений
```bash
# В случае проблем с новой версией
sudo systemctl stop crm-agro
sudo cp /opt/crm-agro/crm-agro-backend.jar.backup /opt/crm-agro/crm-agro-backend.jar
sudo systemctl start crm-agro
```

## 📋 Чек-лист развертывания

- [ ] Установлена Java 17
- [ ] Установлен PostgreSQL 12+
- [ ] Создана база данных и пользователь
- [ ] Выполнен скрипт инициализации
- [ ] Настроена конфигурация приложения
- [ ] Собран проект
- [ ] Настроен системный сервис
- [ ] Настроен Nginx (опционально)
- [ ] Настроен SSL (опционально)
- [ ] Настроен брандмауэр
- [ ] Настроено логирование
- [ ] Настроено резервное копирование
- [ ] Протестированы основные функции
- [ ] Настроен мониторинг

## 🆘 Устранение неполадок

### Частые проблемы

#### Приложение не запускается
```bash
# Проверка логов
sudo journalctl -u crm-agro -n 50

# Проверка портов
sudo netstat -tlnp | grep 8080

# Проверка прав доступа
ls -la /opt/crm-agro/
```

#### Проблемы с базой данных
```bash
# Проверка подключения
psql -U crm_user -d crm_agro -c "SELECT version();"

# Проверка статуса PostgreSQL
sudo systemctl status postgresql
```

#### Проблемы с памятью
```bash
# Увеличение heap size
java -Xms1g -Xmx4g -jar crm-agro-backend.jar
```

### Контакты для поддержки
- Email: support@crmagro.com
- Документация: https://docs.crmagro.com
- Issues: https://github.com/your-username/crm-agro-backend/issues
