# HiddenNorthNorfolk — Backend Architecture

> **Status:** Initial draft based on the repository folder and Kotlin source listings, `AdminController.kt`, `AdminService.kt`, and `SecurityConfig.kt`. Update this document as implementation and deployment details are verified.

## 1. Purpose and technology

The HiddenNorthNorfolk backend provides a REST API for heritage landmarks, points of interest (POIs), images, and bibliographic sources.

- **Language:** Kotlin
- **Framework:** Spring Boot, including Spring Web, Spring Data repositories, and Spring Security
- **Build:** Gradle multi-module project
- **Database:** Supabase-hosted PostgreSQL (based on project deployment context; connection details not verified from configuration files)
- **Image storage:** Supabase Storage, accessed through `SupabaseStorageService`
- **Deployment:** Hetzner server behind Nginx (based on project deployment context; deployment configuration not inspected for this draft)

## 2. Repository structure

The repository contains these top-level modules:

| Module | Observed role |
| --- | --- |
| `app` | Spring Boot application entry point (`HiddenNorthNorfolkApplication.kt`), security configuration, and database migration resources |
| `village` | Admin API, domain models, DTOs, services, repositories, and storage integration |
| `common` | Module present; responsibilities not yet verified |
| `notification` | Module present; responsibilities not yet verified |

The repository also contains Gradle wrapper/build logic. The `village` module is organized approximately as follows:

```text
village/src/main/kotlin/com/riprojects/hiddennorthnorfolk/
├── api/
│   ├── controllers/
│   ├── exception_handling/
│   └── mappers/
├── domain/
│   ├── exception/
│   └── model/
├── dto/
├── infra/
│   ├── config/
│   ├── database/
│   │   ├── entities/
│   │   ├── mappers/
│   │   └── repositories/
│   ├── rate_limiting/
│   ├── storage/
│   └── web/
└── service/
```

## 3. Request flow and responsibilities

The inspected admin request path is:

```text
HTTP request
    → AdminController
    → AdminService
    → Spring Data repositories
    → Database
```

`AdminController` handles HTTP mappings, request validation, and conversion of domain objects to DTOs. `AdminService` coordinates data operations, uses repositories, and maps database entities into domain models. Image endpoints also call `SupabaseStorageService` directly to upload/delete files.

Relevant implementation files:

- `app/src/main/kotlin/com/riprojects/hiddennorthnorfolk/HiddenNorthNorfolkApplication.kt`
- `village/src/main/kotlin/com/riprojects/hiddennorthnorfolk/api/controllers/AdminController.kt`
- `village/src/main/kotlin/com/riprojects/hiddennorthnorfolk/service/AdminService.kt`
- `village/src/main/kotlin/com/riprojects/hiddennorthnorfolk/infra/storage/SupabaseStorageService.kt`

## 4. Core data concepts

### Landmarks and POIs

Landmarks and points of interest are represented by separate domain models, DTOs, database entities, and repositories.

`AdminService.recordLandmark()` creates a landmark **and an associated placeholder POI** in one transaction. The new POI initially receives the landmark's name and description. Subsequent POI changes use the update operation.

The older `recordPoi` controller endpoint is commented out; it is **not an active route** in the inspected controller.

### Images

Landmark images and POI images have separate entities and repositories. Image records include a storage path and optional caption. Files are uploaded using `SupabaseStorageService`, while the corresponding database records are created by `AdminService`.

When image records are fetched, stored paths are converted into public URLs. Dedicated image deletion endpoints delete the storage file and then the database record. Deleting a landmark itself is **not documented as deleting associated storage files**; the controller comment explicitly excludes those files.

### POI sources and further reading

POI bibliographic references are represented by `PoiSource`, `PoiSourceEntity`, `PoiSourceRepository`, and `PoiSourceDto`.

The individual POI GET endpoint loads the POI and its sources, then includes the sources in the returned DTO. `AdminService.getPoiSources()` sorts references by:

1. `SOURCE` before `FURTHER_READING` (other types last)
2. `displayOrder`
3. `title`

The database schema and migration details should be checked against the migration files before documenting exact constraints.

## 5. Admin REST API

The inspected `AdminController` has the base path `/api/admin`.

| Method | Path | Purpose |
| --- | --- | --- |
| `POST` | `/landmarks/recordLandmark` | Create landmark and placeholder POI |
| `GET` | `/landmarks/{landmarkId}` | Get landmark |
| `GET` | `/landmarks` | List landmarks |
| `PUT` | `/landmarks/{landmarkId}` | Update landmark |
| `DELETE` | `/landmarks/{landmarkId}` | Delete landmark record |
| `GET` | `/landmarks/poi/{landmarkId}` | Get POI by landmark ID, including sources |
| `GET` | `/landmarks/poi/` | List POIs |
| `PUT` | `/landmarks/poi/{poiId}` | Update POI by POI ID |
| `DELETE` | `/landmarks/poi/{poiId}` | Delete POI by POI ID |
| `POST` | `/landmarks/{landmarkId}/images` | Upload landmark image |
| `GET` | `/landmarks/{landmarkId}/images` | List landmark images |
| `DELETE` | `/landmark-images/{landmarkImageId}` | Delete landmark image and storage file |
| `POST` | `/landmarks/poi/{poiId}/images` | Upload POI image |
| `GET` | `/landmarks/poi/{poiId}/images` | List POI images |
| `DELETE` | `/landmarks/poi/{poiImageId}/images` | Delete POI image and storage file |

Paths above are relative to `/api/admin`. Note the distinction between **landmark ID** and **POI ID** in the POI routes. No active source-management endpoints were present in the inspected controller; do not assume this is an exhaustive list of all controllers or external APIs.

## 6. Security — known issue before public release

**Admin API access requires review before public release.**

The inspected `SecurityConfig.kt` disables CSRF protection and configures `anyRequest().permitAll()`. Therefore, the Spring Security filter chain shown does **not** require authentication or authorization, including for `/api/admin/**`.

It has not yet been verified whether Nginx, firewall rules, or other infrastructure restrict these endpoints.

**TODO:** Review and implement appropriate protection for administrative endpoints before publicly releasing HiddenNorthNorfolk. Do not treat the `/api/admin` URL prefix itself as an access control mechanism.

## 7. Deployment and configuration — to verify

The project is understood to run on a Hetzner server with Nginx in front of the Spring Boot application and Supabase services for data/storage. The following have **not** been verified against configuration in this documentation pass:

- Nginx routing, access restrictions, TLS settings, and upload limits
- Database connection settings and schema migrations
- Runtime environment variables and secret management
- Deployment/build commands and operational monitoring
- Any public/non-admin API endpoints outside `AdminController`

Do not add passwords, access tokens, service-role keys, or other secrets to this document.

## 8. Follow-up documentation tasks

- [ ] Verify all controllers and document any public API endpoints.
- [ ] Inspect entity relationships, repository interfaces, and database migrations.
- [ ] Verify the image storage implementation and failure-handling behavior.
- [ ] Verify the purpose of `common` and `notification` modules.
- [ ] Document deployment and environment configuration without secrets.
- [ ] Review admin endpoint authentication and authorization before release.

---

*This is a living architecture reference. Confirm behavior against source code when changing APIs, storage, security, or deployment.*
