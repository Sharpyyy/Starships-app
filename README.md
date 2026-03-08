# SWAPI Starships — MOD_B8_SHARE_DETAIL

**VariantCode: SWAPI-STARSHIPS-MOD_B8_SHARE_DETAIL**

Приложение для просмотра списка звёздных кораблей (Starships) из [SWAPI](https://swapi.dev/) и детальной информации по каждому кораблю.

---

## VariantCode

Формат варианта: `<API_CODE>-<RESOURCE_CODE>-<MOD_CODE>`  
В данном проекте: **SWAPI** (API) + **STARSHIPS** (ресурс) + **MOD_B8_SHARE_DETAIL** (модификатор).

**Модификатор MOD_B8_SHARE_DETAIL:** экран деталей открывается по нажатию на элемент списка и загружает данные **отдельным запросом по ID** (не из списка). То есть список и детали получаются разными вызовами API: сначала список кораблей, затем при переходе — детали выбранного корабля по его `id`.

---

## Использованные endpoints

| Назначение | Метод | Endpoint | Пример |
|------------|--------|----------|--------|
| Список кораблей (первая страница) | GET | `/starships/?page=1` | `https://swapi.dev/api/starships/?page=1` |
| Детали корабля по ID | GET | `/starships/{id}/` | `https://swapi.dev/api/starships/10/` |

**Base URL:** `https://swapi.dev/api/`

---

## Стек

- **Kotlin** + **Jetpack Compose** + **Coroutines**
- **Retrofit** + **OkHttp** для сети
- **Hilt** для dependency injection
- **Architecture:** data (DTO) → domain/UI model + Repository + ViewModel (DTO не отображаются в UI)

---

## Структура приложения

- **Экран списка** — отображает элементы ресурса Starships (первая страница).
- **Экран деталей** — открывается по нажатию на элемент; загружает детали по ID отдельным запросом.
- **Состояния:** Loading, Content, Error (с текстом ошибки и кнопкой «Повторить»).

---

## Скриншоты

| Экран списка | Экран деталей |
|--------------|----------------|
| ![Список кораблей](screenshots/list.png) | ![Детали корабля](screenshots/detail.png) |



---

