# Medical Reservation System

System do zarządzania rejestracją i umawianiem wizyt medycznych. Aplikacja backendowa stworzona w Javie z wykorzystaniem Spring Boot, JPA, JWT, MySQL, Docker i GitHub Actions.

## Spis treści

- [Opis projektu](#opis-projektu)
- [Technologie](#technologie)
- [Funkcjonalności](#funkcjonalności)
- [Struktura projektu](#struktura-projektu)
- [Jak uruchomić](#jak-uruchomić)
- [Swagger UI](#swagger-ui)
- [Automatyzacja CI/CD](#automatyzacja-cicd)
- [Autorzy](#autorzy)

---

## Opis projektu

Medical Reservation System to backendowa aplikacja webowa pozwalająca użytkownikom na:

- rejestrację i logowanie,
- przeglądanie dostępnych lekarzy,
- umawianie wizyt,
- zarządzanie lekarzami (dodawanie przez administratora).

System nie zawiera warstwy frontendowej – komunikacja odbywa się poprzez REST API.

---

## Technologie

| Technologia        | Wersja | Opis |
|--------------------|--------|------|
| Java               | 17     | Język programowania |
| Spring Boot        | 3.5.0  | Framework aplikacji |
| Spring Web         | 6.x    | REST API |
| Spring Data JPA    | 3.x    | Operacje na bazie danych |
| Hibernate          | 6.x    | ORM |
| MySQL              | 8.x    | Relacyjna baza danych |
| JWT (Tokeny)       | –      | Autoryzacja |
| Maven              | 3.9.x  | System budowania |
| Docker             | 24.x   | Konteneryzacja |
| GitHub Actions     | –      | CI/CD |
| Swagger UI         | 3.x    | Dokumentacja API |

---


## Struktura projektu

```
medical-reservation-system/
├── src/
│   └── main/
│       ├── java/com/example/medicalreservationsystem/
│       │   ├── appointments/              # Moduł wizyt
│       │   │   ├── Appointment.java
│       │   │   ├── AppointmentController.java
│       │   │   ├── AppointmentRepository.java
│       │   │   └── AppointmentService.java
│       │   ├── config/                    # Konfiguracja bezpieczeństwa i JWT
│       │   ├── doctors/                   # Moduł lekarzy
│       │   │   ├── Doctor.java
│       │   │   ├── DoctorController.java
│       │   │   ├── DoctorRepository.java
│       │   │   └── DoctorService.java
│       │   ├── enums/                     # Enumy (role, statusy itp.)
│       │   ├── security/                  # Konfiguracje JWT i filtry
│       │   │   ├── AuthController.java
│       │   │   ├── AuthRequest.java
│       │   │   ├── AuthResponse.java
│       │   │   ├── CustomUserDetailsService.java
│       │   │   ├── JwtAuthenticationFilter.java
│       │   │   ├── JwtUtils.java
│       │   │   └── RegisterRequest.java
│       │   ├── users/                     # Moduł użytkowników
│       │   │   ├── User.java
│       │   │   ├── UserController.java
│       │   │   ├── UserRepository.java
│       │   │   └── UserService.java
│       │   └── MedAppApplication.java     # Klasa uruchamiająca aplikację
│       └── resources/
│           ├── static/
│           ├── templates/
│           ├── application.properties             # Główna konfiguracja
│           ├── application-dev.properties         # Profil lokalny
│           └── application-prod.properties        # Profil produkcyjny
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── .github/
    └── workflows/
        └── ci.yml                        # Konfiguracja CI/CD
```
## Funkcjonalności

#! Wszystkie endpointy działają na procie: 8081

### 🔐 Rejestracja

- **Endpoint:** `POST /auth/register`
- **Body:**
  ```json
  {
    "username": "user1",
    "password": "password123",
    "role": "PATIENT" (Rolą może też być ADMIN, DOCTOR)
  }
  ```

### 🔐 Logowanie

- **Endpoint:** `POST /auth/login`
- **Body:**

```json
{
  "username": "user1",
  "password": "password123"
}
```

- **Zwraca:** JWT token

---

### 👨‍⚕️ Dodawanie lekarza (ADMIN)

- **Endpoint:** `POST /doctors`
- **Header:** `Authorization: Bearer <token>`
- **Body:**

```json
{
  "name": "Dr. Jan Kowalski",
  "specialization": "Ortopeda"
}
```

---

### 📄 Lista lekarzy

- **Endpoint:** `GET /doctors`
- **Header:** `Authorization: Bearer <token>`

---

### 📅 Umawianie wizyty

- **Endpoint:** `POST /appointments`
- **Header:** `Authorization: Bearer <token>`
- **Body:**

```json
{
    "dateTime": "2025-07-20T14:30:00",
    "doctor": {
        "id": 2
    },
    "patient": {
        "id": 5
    }
}
```

---

## Jak uruchomić

### Pierwsza Opcja: Budowanie projektu lokalnie

> **Uwaga:** Upewnij się, że w pliku `application.properties` jest ustawione:
>
> ```
> spring.profiles.active=dev
> ```
> jeśli uruchamiasz aplikację lokalnie z MySQL poza Dockerem.

---

### Uruchamianie w Dockerze (ręcznie)

#### 1. Stwórz kontener bazy danych:

```bash
docker run --name medical-mysql \
  -e MYSQL_ROOT_PASSWORD=haslo \
  -e MYSQL_DATABASE=medical_reservations_db \
  -p 3306:3306 \
  -d mysql:8
```

#### 2. Ustaw profil `dev` w `application.properties`:

```
spring.profiles.active=dev
```

#### 3. Uruchom aplikację lokalnie:

![image](https://github.com/user-attachments/assets/70177224-42d3-4469-a75e-6a9407c01adc)


---

### Druga opcja:  Docker Compose

**Uwaga:** Jeśli aplikacja nie zadziała za pierwszym razem zalecane jest zatrzymanie pracy programu i ponowne załączenie komendami:

```bash
docker compose stop

docker compose up
```
---

```bash
docker compose up --build
```

> Upewnij się, że plik `.jar` został zbudowany (znajduje się w `target/`) i jego nazwa jest zgodna z tą w `Dockerfile`.

---

## Swagger UI

Po uruchomieniu aplikacji wejdź w przeglądarce na:

```
http://localhost:8080/swagger-ui/index.html
```

Znajdziesz tam dokumentację API i możliwość jego testowania.

---

## Automatyzacja CI/CD

GitHub Actions skonfigurowane w:

```
.github/workflows/ci.yml
```

Po **każdym pushu** do gałęzi `main` automatycznie uruchamiane są:

- budowanie aplikacji (`mvn clean install`)
- testy jednostkowe (JUnit + Mockito)

---

## Autorzy

**Przemysław Orzechowski**

**Szymon Kuś**




