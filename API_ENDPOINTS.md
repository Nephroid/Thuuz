# API Endpoints in Thuuz Android Application

## Base URLs

The application uses multiple base URLs depending on the selected environment (Production, Dev, Stage, etc.). This is configured via `ServerChoice.java`.

### Default Production Base URLs:
- **Mobile API Server** (`ServerChoice.mServer`): `mobile.thuuz.com/`
- **Web Server** (`ServerChoice.webServer`): `www.thuuz.com/`
- **Content Server** (`ServerChoice.contentServer`): `content.thuuz.com/`

### Prefix variables in `ServerChoice.java`:
- `ServerChoice.m`: `http://mobile.thuuz.com/`
- `ServerChoice.n`: `https://mobile.thuuz.com/` or `http://mobile.thuuz.com/` (depending on environment `isSecure` flag)
- `ServerChoice.o`: `[ServerChoice.n]/2.4/`
- `ServerChoice.p`: `http://mobile.thuuz.com/2.4/`
- `ServerChoice.q`: `http://www.thuuz.com/`
- `ServerChoice.r`: `http://content.thuuz.com/`

## Endpoints and Request Types

### User & Authentication
- **User Picture:**
  - Method: GET
  - URL: `[ServerChoice.q]user/{user_id}/picture/`
  - URL: `http://graph.facebook.com/{user_id}/picture`

- **User Profile/Settings:**
  - Method: GET
  - URL: `[ServerChoice.n]app/2.3/user/{user_id}/?page_params=true`
  - Response Models: User settings/preferences payload

### Games & Feed
- **Game Details/Friends:**
  - Method: GET
  - URL: `[ServerChoice.n]app/2.3/game/{game_id}/friends/`
  - URL: `[ServerChoice.q]{path_k}/{path_l}/game/{game_id}/`
  - Response Models: `GameData` JSON containing standard game details like scores, linescores, tiebreaker, broadcast schedules.
  
- **Feed:**
  - Method: GET
  - URL: `[ServerChoice.n]app/2.3/feed/?type={type}{additional_params}`

### Standings
- **Standings HTML:**
  - Method: GET
  - URL: `[ServerChoice.r]{sport}/{league}/standings/index.html`

### Fantasy Sports
- **Fantasy Team Web Fragment:**
  - Method: GET
  - URL: `[ServerChoice.n]app/2.3/fantasy/`
  - Response Models: `FantasySite`, `FantasySettingsCustomizations`, containing fantasy settings, site details, and alert configuration.
  
- **Player Details:**
  - Method: GET
  - URL: `[ServerChoice.n]app/2.3/player/{player_id}/`

- **Fantasy Help/Info:**
  - Method: GET
  - URL: `[ServerChoice.q]app/2.3/fantasy/help/import/`
  - URL: `[ServerChoice.q]app/2.3/fantasy/help/intro/`
  - URL: `[ServerChoice.q]app/2.3/fantasy/help/invite/`
  - URL: `[ServerChoice.q]apps/{app_id}/fantasy/`

### Miscellaneous Apps/Web Views
- **About Page:**
  - Method: GET
  - URL: `[ServerChoice.q]apps/about/?version={version}&build={build}&server={server}`
  
- **FAQ Sidebar:**
  - Method: GET
  - URL: `[ServerChoice.q]apps/faq/sidebar/`

- **2D Graph:**
  - Method: GET
  - URL: `[ServerChoice.q]2dgraph/{id}/?`
  
- **Get App (Invite):**
  - Method: GET
  - URL: `http://www.thuuz.com/apps/get/`

## Data Request Enums (`IService.API_REQUEST_GAME_STATUS`)
Used when fetching game statuses, typical parameters indicate which games to retrieve:
- `LIVE (1)`
- `COMPLETED (2)`
- `UPCOMING (4)`
- `UPCOMING_AND_LIVE (5)`
- `ALL (7)`

## HTTP Method and Payload Notes
- Majority of the requests use `DefaultHttpClient` (HTTP 1.1) with basic setup (`HttpGet`).
- App core implements simple generic `HttpGet` for resources mapping onto generic JSON object parsers that initialize data objects like `GameData`, `Locations`, `AlertSettings`, etc.
- Post requests (`HttpPost`) are primarily utilized for tracking, severe logging (`[ServerChoice.m]severe_logging`), session initialization, and submitting settings or invites where payloads are standard URL encoded strings (`application/x-www-form-urlencoded; charset=UTF-8`) or JSON strings.
